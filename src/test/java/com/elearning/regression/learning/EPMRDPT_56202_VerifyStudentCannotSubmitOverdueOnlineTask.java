package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_56202_VerifyStudentCannotSubmitOverdueOnlineTask",
    groups = {"full", "regression", "student"})
public class EPMRDPT_56202_VerifyStudentCannotSubmitOverdueOnlineTask {

  private final String taskName = "AutoTest_ExpiredOnlineTask";
  private final String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService().navigateToTaskInAllTasksTab(groupName, taskName);
  }

  @Test
  public void checkSubmitButtonNotDisplaying() {
    assertFalse(new TasksPopUpScreenOnLearningPageScreen().isSubmitButtonDisplayedNoWait(),
        "Submit button is displayed!");
  }
}
