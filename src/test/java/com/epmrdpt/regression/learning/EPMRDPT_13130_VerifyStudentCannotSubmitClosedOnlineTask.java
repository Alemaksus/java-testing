package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13130_VerifyStudentCannotSubmitClosedOnlineTask",
    groups = {"full", "regression", "student", "deprecated"})
public class EPMRDPT_13130_VerifyStudentCannotSubmitClosedOnlineTask {

  private final String taskName = "AutoTest_ClosedOnlineTask";
  private final String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService().navigateToTaskInAllTasksTab(groupName, taskName);
  }

  @Test
  public void checkSubmitButtonIsNotFound() {
    assertFalse(new TasksPopUpScreenOnLearningPageScreen().isSubmitButtonDisplayedNoWait(),
        "Submit button is displayed!");
  }
}
