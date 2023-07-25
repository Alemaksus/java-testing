package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_STATUS_DRAFT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_STATUS_PUBLISHED;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.bo.training_center.TrainingCenterFactory;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;


@Test(description = "EPMRDPT_34252_VerifyThatSkillsAndStatusDropDownsArePrefilled",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34252_VerifyThatSkillsAndStatusDropDownsArePrefilled {

  private final String trainingCenterCountry = "AutoTestCountry";
  private final String firstStatus = LocaleProperties.getValueOf(NEWS_MANAGEMENT_STATUS_DRAFT);
  private final String secondStatus = LocaleProperties.getValueOf(NEWS_MANAGEMENT_STATUS_PUBLISHED);
  private String trainingCenterCityName;
  private TrainingCenterManagementService trainingCenterManagementService;
  private User user;

  @Factory(dataProvider = "Provider of users with different cities options",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34252_VerifyThatSkillsAndStatusDropDownsArePrefilled(
      User user, String city) {
    this.user = user;
    this.trainingCenterCityName = city;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupCreateOrEditTrainingCenterManagementScreen() {
    TrainingCenter trainingCenter = TrainingCenterFactory
        .createTrainingCenterWithCountryAndCityName(trainingCenterCountry,
            trainingCenterCityName);
    trainingCenterManagementService = new TrainingCenterManagementService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink();
    trainingCenterManagementService.deleteTrainingCenterIfPresent(trainingCenter);
    trainingCenterManagementService.createTrainingCenter(trainingCenter);
  }

  @Test(priority = 1)
  public void checkThatSkillsDownsIsPrefilled() {
    assertNotNull(new CreateOrEditTrainingCenterScreen().getSelectedSkillsText(),
        "Skills aren't prefilled!");
  }

  @Test(priority = 2)
  public void checkThatStatusDropDownIsPrefilled() {
    String currentStatus = new CreateOrEditTrainingCenterScreen().getChooseStatusText();
    assertTrue(currentStatus.equals(firstStatus) || currentStatus.equals(secondStatus),
        "Status is not draft or published!");
  }
}
