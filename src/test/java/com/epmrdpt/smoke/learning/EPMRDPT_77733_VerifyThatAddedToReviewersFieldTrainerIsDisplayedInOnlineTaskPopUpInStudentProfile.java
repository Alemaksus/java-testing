package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77733_VerifyThatAddedToReviewersFieldTrainerIsDisplayedInOnlineTaskPopUpInStudentProfile",
    groups = {"full", "smoke"})
public class EPMRDPT_77733_VerifyThatAddedToReviewersFieldTrainerIsDisplayedInOnlineTaskPopUpInStudentProfile {

  private static final String TASK_NAME = "Online_Task_For_Check_Bulk_Operation";
  private static final String GROUP_NAME = "AutoTest_GroupWithEverydayClasses";
  private static final String REVIEWER_NAME = "AutoTrainer AutoTrainer";
  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginToTaskTab() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.withSimplePermission())
        .clickLearningButton()
        .clickTasksTab()
        .waitTaskSectionForVisibility()
        .clickAllTasksTab()
        .clickEpamTrainingTab()
        .clickGroupByName(GROUP_NAME)
        .clickRegularTaskByNameInAllTasksTab(TASK_NAME)
        .waitPopUpForVisibility();
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
  }

  @Test
  public void checkTaskTabOpened() {
    assertTrue(tasksPopUpScreenOnLearningPageScreen.isScreenLoaded(), "'Tasks' tab fails to open!");
  }

  @Test
  public void checkReviewerName() {
    assertEquals(tasksPopUpScreenOnLearningPageScreen.getReviewerNameText(), REVIEWER_NAME,
        "The name of Reviewer is displayed incorrectly!");
  }

  @Test
  public void checkReviewerAvatarDisplayed() {
    assertTrue(tasksPopUpScreenOnLearningPageScreen.isReviewerPhotoDisplayed(),
        "'Reviewer's' Avatar is not displayed!");
  }
}
