package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13137_VerifyThatStudentCanNotSeeTrainerCommentForRejectedOnlineTask",
    groups = {"full", "regression", "student", "deprecated"})
public class EPMRDPT_13137_VerifyThatStudentCanNotSeeTrainerCommentForRejectedOnlineTask {

  private final String groupName = "AutoTest Student Java Group";
  private final String taskName = "AutoTest_HiddenCommentForRejectedOnlineTask";
  private final String taskVisibleComment = "Autotest_VisibleComment";
  private final String taskHiddenComment = "AutoTest_HiddenCommentForRejectedOnlineTask";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService().navigateToTaskInAllTasksTab(groupName, taskName);
  }

  @Test
  public void checkTrainersCommentForRejectedOnlineTask() {
    SoftAssert softAssert = new SoftAssert();
    TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Task details pop up isn't displayed!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getTaskNameText(), taskName,
        String.format("%s task name pop up isn't opened!", taskName));
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getTrainersComment(),
        taskVisibleComment, "Visible comment isn't present!");
    softAssert.assertNotEquals(tasksPopUpScreenOnLearningPageScreen.getTrainersComment(),
        taskHiddenComment, "Hidden comment is present!");
    softAssert.assertAll();
  }
}
