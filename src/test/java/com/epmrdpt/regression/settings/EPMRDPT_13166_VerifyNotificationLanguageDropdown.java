package com.epmrdpt.regression.settings;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.SettingsService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13166_VerifyNotificationLanguageDropdown",
    groups = {"full", "regression"})
public class EPMRDPT_13166_VerifyNotificationLanguageDropdown {

  private User user;
  private SettingsScreen settingsScreen;
  private SettingsService settingsService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    settingsService = new SettingsService();
    settingsScreen = new SettingsScreen();
    user = UserFactory.withDepartmentTraining();
  }

  @Test(priority = 1)
  public void checkIfSettingsPageIsLoaded() {
    new LoginService().login(user);
    assertTrue(settingsService.navigateToSettingsScreen().isScreenLoaded(),
        "Settings page is not loaded!");
  }

  @Test(priority = 2)
  public void checkIfNotificationLanguageDropdownIsDisplayed() {
    settingsScreen.scrollToBottom();
    settingsService.untickEmailNotificationCheckbox()
        .clickNotificationLanguageDropDownButton()
        .waitNotificationLanguageDropDownMenuVisibility();
    assertTrue(settingsScreen.isNotificationLanguageDropDownMenuDisplayed(),
        "Notification language dropdown Menu is not displayed!");
  }
}
