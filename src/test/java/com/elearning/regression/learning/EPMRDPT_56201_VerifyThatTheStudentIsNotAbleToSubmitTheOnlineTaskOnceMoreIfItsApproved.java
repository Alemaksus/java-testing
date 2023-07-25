package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56201_VerifyThatTheStudentIsNotAbleToSubmitTheOnlineTaskOnceMoreIfItsApproved",
    groups = {"full", "regression", "student"})
public class EPMRDPT_56201_VerifyThatTheStudentIsNotAbleToSubmitTheOnlineTaskOnceMoreIfItsApproved {

  private final String taskName = "AutoTest_PassedOnlineTask";
  private final String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";
  private final String trainerComments = "Passed for AutoTest";
  private final String studentComments = "Passed Online Task Solution for AutoTest";

  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService().navigateToTaskInAllTasksTab(groupName, taskName);
  }

  @Test
  public void checkTrainerComments() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isTrainerCommentsMessageDisplayed(),
        "Trainer comments isn't displayed!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getTrainerCommentsMessageText(),
        trainerComments, "Trainer comments is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkStudentComments() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isUserCommentsMessageDisplayed(),
        "Student comments isn't displayed!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getUserCommentsMessageText(),
        studentComments, "Student comments is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkSubmitButtonNotDisplaying() {
    assertFalse(tasksPopUpScreenOnLearningPageScreen.isSubmitButtonDisplayedNoWait(),
        "Submit button is displayed!");
  }
}
