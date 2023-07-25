package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_56215_VerifyThatStudentCannotSeeMarksForOnlineTasksWhenTheTrainerSelectCorrespondedOptionOnTheTaskJournal",
    groups = {"full", "student", "regression"})
public class EPMRDPT_56215_VerifyThatStudentCannotSeeMarksForOnlineTasksWhenTheTrainerSelectCorrespondedOptionOnTheTaskJournal {

  private static final String GROUP_NAME = "AutoTest Student Java Group";
  private static final String TASK_NAME = "AutoTest_HideMarksTask";

  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asLearningStudent())
        .waitLinksToRedirectOnOtherSectionsDisplayed().clickLearningButton();
    tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkAllTasksIsOpened() {
    assertTrue(tasksTabOnLearningPageScreen.clickTasksTab()
            .waitTaskSectionForVisibility()
            .clickAllTasksTab()
            .isTaskCountInAllTasksTabDisplayed(),
        "All tasks tab isn't opened!");
  }

  @Test(priority = 2)
  public void checkMarksAreNotVisible() {
    assertTrue(tasksTabOnLearningPageScreen.clickEpamTrainingTab()
            .clickGroupByName(GROUP_NAME)
            .isTaskMarksHiddenIconDisplayed(GROUP_NAME, TASK_NAME),
        String.format("Marks aren't invisible for task %s!", TASK_NAME));
  }
}
