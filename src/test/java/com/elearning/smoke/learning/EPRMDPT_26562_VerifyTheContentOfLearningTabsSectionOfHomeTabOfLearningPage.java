package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.screens.TrainersTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPRMDPT_26562_VerifyTheContentOfLearningTabsSectionOfHomeTabOfLearningPage",
    groups = {"full", "student", "smoke"})
public class EPRMDPT_26562_VerifyTheContentOfLearningTabsSectionOfHomeTabOfLearningPage {

  private SoftAssert softAssert;
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
  public void checkTabsFromLearningPageScreenDisplayed() {
    headerScreen.clickLearningButton();
    softAssert = new SoftAssert();
    softAssert.assertTrue(new HomeTabOnLearningPageScreen().isHomeTabDisplayed(),
        "Home tab isn't displayed!");
    softAssert.assertTrue(new TasksTabOnLearningPageScreen().isTasksTabDisplayed(),
        "Tasks tab isn't displayed!");
    softAssert.assertTrue(new TrainersTabOnLearningPageScreen().isTrainersTabDisplayed(),
        "Trainers tab isn't displayed!");
  }
}
