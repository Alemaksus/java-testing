package com.epmrdpt.smoke.center_management;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34203_VerifyThatUserCanOpenPreviewPage",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34203_VerifyThatUserCanOpenPreviewPage {

  private final String countryOfCenter = "AutoTestCountry";
  private final String cityOfCenter = "AutoTestCity";

  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34203_VerifyThatUserCanOpenPreviewPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
  }

  @Test
  public void checkOpenScreen() {
    CenterScreen centerScreen = new TrainingCenterManagementService()
        .openEditCenterScreen(countryOfCenter, cityOfCenter)
        .waitEditAreaForVisibility()
        .waitForTrainingCenterNameVisibility()
        .clickPreviewButton()
        .waitCenterItemForVisibility();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isTitlePresent(), "Title not present!");
    softAssert.assertTrue(centerScreen.isDescriptionPresent(), "Description not present!");
    softAssert.assertTrue(centerScreen.isSkillsPresent(), "Skills not present!");
    softAssert.assertAll();
  }
}
