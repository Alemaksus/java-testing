package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_26754_VerifyStudentIsAbleToSeeMarksOnlineTasksOnTheRelevantPage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_26754_VerifyStudentIsAbleToSeeMarksOnlineTasksOnTheRelevantPage {

  private HeaderScreen headerScreen;
  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkUserLogIn() {
    assertTrue(new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asStudentForLearningPage())
        .isProfilePhotoImageDisplayed(), "User doesn't Log In!");
  }

  @Test(priority = 3)
  public void checkTrainersTabDisplayed() {
    headerScreen.clickLearningButton();
    assertTrue(tasksTabOnLearningPageScreen.clickTasksTab().isScreenLoaded(),
        "Tasks page isn't displayed!");
  }

  @Test(priority = 4)
  public void checkAllMarksForRegularTasksDisplayed() {
    assertTrue(tasksTabOnLearningPageScreen.clickEpamTrainingTab()
            .isAllMarkForOnlineTasksDisplayed(),
        "Some marks for online task isn't displayed!");
  }
}
