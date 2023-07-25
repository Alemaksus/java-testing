package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NO_ATTEMPTS_LEFT_ONLINE_TASK_POPUP_ERROR_MESSAGE;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56231_VerifyThatTheStudentIsNotAbleToSubmitTheOnlineTaskIfNoAttemptsLeft",
    groups = {"full", "regression", "student"})
public class EPMRDPT_56231_VerifyThatTheStudentIsNotAbleToSubmitTheOnlineTaskIfNoAttemptsLeft {

  private final String taskName = "AutoTest_NoAttemptsLeftOnlineTask";
  private final String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";
  private final String errorMessage = getValueOf(NO_ATTEMPTS_LEFT_ONLINE_TASK_POPUP_ERROR_MESSAGE);
  private final String trainerComments = "Rejected for AutoTest";

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
  public void checkErrorMessage() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isErrorMessageDisplayed(),
        "Error message isn't displayed!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getErrorMessageText(),
        errorMessage, "Error message is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkTrainerComments() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isTrainerCommentsMessageDisplayed(),
        "Trainer comment isn't displayed!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getTrainerCommentsMessageText(),
        trainerComments, "Trainer comment is incorrect!");
    softAssert.assertAll();
  }
}
