package com.epmrdpt.regression.profile;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13001_VerifyThatUserCanSeeEnglishLevelInTheProfilePage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_13001_VerifyThatUserCanSeeEnglishLevelInTheProfilePage {

  @Test(priority = 1)
  public void checkProfilePageLoading() {
    assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                UserFactory.asLearningStudent())
            .clickProfileMenu()
            .clickProfileButton()
            .isScreenLoaded(),
        "Profile page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkEnglishLanguageTestResultDisplaying() {
    assertTrue(new ProfileScreen().isEnglishTestResultDisplayed(),
        "English language test result is not displayed!");
  }
}
