package com.epmrdpt.regression.settings;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithoutTrainings;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77706_VerifyPressingCogwheelInMainMenuOpensSettingsPage",
    groups = {"full", "regression"})
public class EPMRDPT_77706_VerifyPressingCogwheelInMainMenuOpensSettingsPage {

  private SettingsScreen settingsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToSettings() {
    settingsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermissionAndWithoutTrainings())
        .clickSettingsButton();
  }

  @Test
  public void checkSettingsPageIsLoaded() {
    assertTrue(settingsScreen.isScreenLoaded(),
        "The settings screen is not loaded!");
  }
}
