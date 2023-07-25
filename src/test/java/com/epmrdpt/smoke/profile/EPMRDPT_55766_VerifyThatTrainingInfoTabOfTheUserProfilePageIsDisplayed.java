package com.epmrdpt.smoke.profile;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithoutTrainings;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_55766_VerifyThatTrainingInfoTabOfTheUserProfilePageIsDisplayed",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_55766_VerifyThatTrainingInfoTabOfTheUserProfilePageIsDisplayed {

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermissionAndWithoutTrainings())
        .clickProfileMenu()
        .clickProfileButton()
        .waitTrainingInfoDisplayed();
  }

  @Test
  public void checkInfoTabIsDisplayed() {
    assertTrue(new ProfileScreen()
            .isTrainingInfoButtonDisplayed(),
        "Training info Tab is not displayed!");
  }
}
