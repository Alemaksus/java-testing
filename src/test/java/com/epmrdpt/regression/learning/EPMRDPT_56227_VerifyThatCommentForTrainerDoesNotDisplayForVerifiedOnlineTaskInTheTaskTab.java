package com.epmrdpt.regression.learning;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56227_VerifyThatCommentForTrainerDoesNotDisplayForVerifiedOnlineTaskInTheTaskTab",
    groups = {"full", "regression", "student"})
public class EPMRDPT_56227_VerifyThatCommentForTrainerDoesNotDisplayForVerifiedOnlineTaskInTheTaskTab {

  private static final String GROUP_NAME = "AutoTest Student Java Group";
  private static final String TASK_NAME = "AutoTest_CommentHiddenTask";
  private static final String TASK_VISIBLE_COMMENT = "AutoTest_VisibleComment";
  private static final String TASK_HIDDEN_COMMENT = "AutoTest_HiddenComment";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asLearningStudent())
        .clickLearningButton()
        .clickTasksTab()
        .waitTaskSectionForVisibility()
        .clickAllTasksTab()
        .clickEpamTrainingTab()
        .clickGroupByName(GROUP_NAME)
        .clickRegularTaskByNameInAllTasksTab(TASK_NAME)
        .waitPopUpForVisibility();
  }

  @Test
  public void checkCommentHiddenTaskPresent() {
    SoftAssert softAssert = new SoftAssert();
    TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Task pop up isn't displayed!");
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.getCommentFromRegularTask()
        .contains(TASK_VISIBLE_COMMENT), "Visible Comment isn't present in task details pop up!");
    softAssert.assertFalse(tasksPopUpScreenOnLearningPageScreen.getCommentFromRegularTask()
        .contains(TASK_HIDDEN_COMMENT), "Hidden comment is present in task details pop up!");
    softAssert.assertAll();
  }
}
