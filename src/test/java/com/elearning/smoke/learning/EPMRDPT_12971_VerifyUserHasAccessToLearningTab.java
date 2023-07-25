package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_12971_VerifyUserHasAccessToLearningTab",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_12971_VerifyUserHasAccessToLearningTab {

  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(headerScreen.isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkUserLogIn() {
    assertTrue(new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asStudentForLearningPage())
        .isProfilePhotoImageDisplayed(), "User doesn't Log In!");
  }

  @Test(priority = 3)
  public void checkLearningButtonDisplayed() {
    assertTrue(headerScreen.isLearningButtonDisplayed(), "Learning button doesn't displayed!");
  }

  @Test(priority = 4)
  public void checkUserHasAccessToLearningTab() {
    headerScreen.clickLearningButton();
    assertTrue(new HomeTabOnLearningPageScreen().isScreenLoaded(),
        "User hasn't access to learning page!");
  }
}
