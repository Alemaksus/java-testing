package com.epmrdpt.regression.settings;

import static com.epmrdpt.bo.user.UserFactory.asStudentWithoutNotifications;

import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_55996_VerifyThatCheckboxForUnsubscribingIsUncheckedByDefault",
    groups = {"full", "regression"})
public class EPMRDPT_55996_VerifyThatCheckboxForUnsubscribingIsUncheckedByDefault {

  private SettingsScreen settingsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToSettings() {
    settingsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asStudentWithoutNotifications())
        .clickSettingsButton()
        .waitScreenLoading();
  }

  @Test
  public void checkCheckboxForEmailNotifications() {
    Assert.assertFalse(settingsScreen.isEmailNotificationCheckboxChecked(),
        "The checkbox for email notifications is not unchecked!");
  }
}
