package com.epmrdpt.smoke.training_management;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77732_VerifyThatIndividualReviewersAreAssignedAutomaticallyWhenMakingBulkAssignmentOfOnlineTaskToStudents",
    groups = {"full", "smoke"})
public class EPMRDPT_77732_VerifyThatIndividualReviewersAreAssignedAutomaticallyWhenMakingBulkAssignmentOfOnlineTaskToStudents {

  private static final String TRAINING_NAME = "AutoTest_LearningStudent_DOTNET_Tasks";
  private static final String STUDENT_NAME = "Student AutoTest";
  private static final String TASK_NAME = "AutoTest_ReassignedOnlineTask";
  private static final String REVIEWER_NAME = "AutoTrainer AutoTrainer";
  private final User user;
  private TrainingOnlineTask trainingOnlineTask;
  private ReactTasksJournalScreen reactTasksJournalScreen;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_77732_VerifyThatIndividualReviewersAreAssignedAutomaticallyWhenMakingBulkAssignmentOfOnlineTaskToStudents(
      User user) {
    this.user = user;
  }

  @BeforeClass
  public void loginToJournalTab() {
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(TASK_NAME);
    reactTasksJournalScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingScreenLink()
        .clickMyGroupsTab()
        .typeGroupNameInput(TRAINING_NAME)
        .clickSearchIcon()
        .waitGroupsTableFieldForVisible()
        .switchToGroupJournal(TRAINING_NAME)
        .switchToTaskJournalTab()
        .waitGroupJournalTableForVisibility()
        .clickAllPeriodButton()
        .clickTaskStatusByStudentName(STUDENT_NAME, trainingOnlineTask)
        .clickEditButtonOnTaskStatusEditPopUp();
  }

  @Test
  public void checkReviewersField() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactTasksJournalScreen.getReviewerName(), REVIEWER_NAME,
        "Reviewer name is not correct!");
    softAssert.assertTrue(reactTasksJournalScreen.isReviewerAvatarDisplayed(),
        "Reviewer avatar is not displayed!");
    softAssert.assertAll();
  }
}
