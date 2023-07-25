package com.epmrdpt.regression.settings;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.SettingsService;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14339_VerifyThatSettingOfCheckboxForUnsubscribingIsSavedIfUserSavesChangesOnSettingsPage",
    groups = {"full", "regression"})
public class EPMRDPT_14339_VerifyThatSettingOfCheckboxForUnsubscribingIsSavedIfUserSavesChangesOnSettingsPage {

  private SoftAssert softAssert;
  private SettingsScreen settingsScreen;
  private SettingsService settingsService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    settingsScreen = new SettingsScreen();
    settingsService = new SettingsService();
  }

  @BeforeMethod(inheritGroups = false, alwaysRun = true)
  private void uncheckEmailNotificationCheckboxIfSetAlready(Method method) {
    if (method.getName().equals("checkEmailNotificationCheckboxSaving")) {
      if (settingsScreen.isEmailNotificationCheckboxChecked()) {
        settingsScreen.clickEmailNotificationCheckbox()
            .clickApplySettingsButton()
            .waitScreenLoading();
      }
    }
  }

  @Test(priority = 1)
  public void checkSettingsScreenLoading() {
    assertTrue(new LoginService()
        .loginAndSetLanguage(UserFactory.withSimplePermissionAndWithoutTrainings())
        .clickProfileMenu()
        .clickSettingsButton()
        .isScreenLoaded(), "Settings page hasn't loaded!");
  }

  @Test(priority = 2)
  public void checkEmailNotificationCheckboxSaving() {
    softAssert = new SoftAssert();
    settingsScreen.waitEmailNotificationCheckboxVisibility().clickEmailNotificationCheckbox();
    softAssert.assertTrue(settingsScreen.isEmailNotificationCheckboxChecked(),
        "'Email Notification' checkbox isn't set!");
    settingsScreen.clickApplySettingsButton().waitScreenLoading();
    softAssert.assertTrue(settingsScreen.waitEmailNotificationCheckboxVisibility()
            .isEmailNotificationCheckboxChecked(),
        "'Email Notification' checkbox isn't set after saving!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void uncheckEmailNotificationCheckbox(Method method, ITestResult result) {
    if (method.getName().equals("checkEmailNotificationCheckboxSaving") && result.isSuccess()) {
      settingsService.untickEmailNotificationCheckbox()
          .clickApplySettingsButton()
          .waitScreenLoading();
    }
  }
}
