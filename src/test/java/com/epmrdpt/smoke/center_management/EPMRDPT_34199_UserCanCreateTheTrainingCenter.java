package com.epmrdpt.smoke.center_management;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.bo.training_center.TrainingCenterFactory;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34199_UserCanCreateTheTrainingCenter",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34199_UserCanCreateTheTrainingCenter {

  private final String trainingCenterCountry = "AutoTestCountry";
  private String trainingCenterCity;
  private String expectedText = LocaleProperties
      .getValueOf(LocalePropertyConst.CENTER_MANAGEMENT_EDIT_STATUS);
  private User user;
  private TrainingCenter trainingCenter;

  @Factory(dataProvider = "Provider of users with different cities options",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34199_UserCanCreateTheTrainingCenter(User user, String trainingCenterCity) {
    this.user = user;
    this.trainingCenterCity = trainingCenterCity;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    trainingCenter = TrainingCenterFactory
        .createTrainingCenterWithCountryAndCityName(trainingCenterCountry, trainingCenterCity);
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickCenterManagementLink();
    new TrainingCenterManagementService()
        .deleteTrainingCenterIfPresent(trainingCenter);
  }

  @Test
  public void checkCreationTrainingCenter() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        new TrainingCenterManagementService()
            .createTrainingCenter(trainingCenter)
            .waitCreateOrEditTrainingCenterHeaderText(expectedText)
            .getHeaderText(),
        expectedText, "Edit page doesn't opened!");
    softAssert.assertTrue(new HeaderScreen()
            .clickCenterManagementLink()
            .clickCountryDropDown()
            .selectCountryFromDDLByName(trainingCenterCountry)
            .clickCityDropDown()
            .selectCityFromDDLByName(trainingCenterCity)
            .clickApplyButton()
            .isCenterThreeDotsContextMenuByCityNameDisplayedShortTimeOut(trainingCenterCity),
        "Training Center isn't displayed!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteTrainingCenter() {
    new TrainingCenterManagementService()
        .deleteTrainingCenterIfPresent(trainingCenter);
  }
}
