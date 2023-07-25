package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asStudentForTrainingPage;
import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_REJECTED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_CHECKED;
import static com.epmrdpt.utils.RandomUtils.getRandomNumberInInterval;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactHomeTaskPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(
    description = "EPMRDPT_54105_VerifyThatUserCanRejectSubmittedTaskUsingCheckPopUpWindow",
    groups = {"full", "react", "regression"})
public class EPMRDPT_54105_VerifyThatUserCanRejectSubmittedTaskUsingCheckPopUpWindow {

  private final String studentsName = "Studentka ReactTrainingPage";
  private final String comment = "Answer from Studentka ReactTrainingPage";
  private final String tasksName = "AutoTest_ReactOnlineTask_" +
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
  private final String groupsName = "AutoTest_GroupForRejectSubmittedTask";
  private final String expectedRejectedStatusContent = getValueOf(REACT_TRAINING_TASK_CHECKED)
      .split(":")[0];
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private ReactHomeTaskPopUpScreen reactHomeTaskPopUpScreen;
  private TrainingOnlineTask trainingOnlineTask;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupNewOnlineTask() {
    int passMark = getRandomNumberInInterval(2, 10);
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(tasksName)
        .withGroup(groupsName)
        .withCommentForStudentOnReview(comment)
        .withMarkOnReviewTask(getRandomNumberInInterval(1, passMark - 1))
        .withPassMarkOnReviewTask(passMark);
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactHeaderScreen()
        .waitTrainingTabForVisibility();
    new ReactTrainingService()
        .openTaskJournalByGroupName(trainingOnlineTask.getGroup())
        .addOnlineTaskAndFillAllRequiredFieldsInTaskJournal(trainingOnlineTask)
        .clickCloseNotificationButton();
    new ReactTaskJournalService()
        .addAssignmentByOnlineTaskName(trainingOnlineTask.getTaskName());
    new ReactLoginService()
        .signOut();
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true, dependsOnMethods = "setupNewOnlineTask")
  public void setupStudentAnswer() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentForTrainingPage())
        .clickLearningButton();
    new LearningService()
        .navigateToTaskOfEpamTrainingInUrgentTab(trainingOnlineTask.getGroup(),
            trainingOnlineTask.getTaskName())
        .typeComment(trainingOnlineTask.getCommentForStudentOnReview())
        .clickSubmitButton();
    new TasksTabOnLearningPageScreen()
        .waitSubmittedStatusForVisibility
            (trainingOnlineTask.getGroup(), trainingOnlineTask.getTaskName());
    new HeaderScreen()
        .clickProfileMenu()
        .signOut();
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true, dependsOnMethods = "setupStudentAnswer")
  public void setupCheckingOnlineTask() {
    reactHomeTaskPopUpScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickCheckButtonInTasksSectionByTaskName(trainingOnlineTask.getTaskName())
        .fillMarkAndPassMarkToCheckTaskTicketInScheduleTab(trainingOnlineTask);
  }

  @Test(priority = 1)
  public void checkPopUpScreensChanges() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactHomeTaskPopUpScreen.getMarkValue(),
        trainingOnlineTask.getMarkOnReviewTask(),
        "'Mark' field on the 'Home task' pop up has incorrect value!");
    softAssert.assertTrue(
        reactHomeTaskPopUpScreen.getStatusBlockText().contains(expectedRejectedStatusContent),
        "'Status Block' field on the 'Home task' have not 'Checked' value!");
    reactHomeTaskPopUpScreen.clickClosePopUpButton();
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkStatusRejected() {
    softAssert = new SoftAssert();
    new ReactTrainingScreen()
        .clickMyGroupsTab();
    reactTasksJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(trainingOnlineTask.getGroup())
        .clickTaskJournalTab();
    softAssert.assertEquals(reactTasksJournalScreen.
            getTaskStatusTextByStudentNameAndTaskId(studentsName, trainingOnlineTask),
        getValueOf(ONLINE_POPUP_STATUS_REJECTED),
        "Task status in the Task Journal is not 'Rejected'!");
    deleteCheckedOnlineTask();
    softAssert.assertAll();
  }

  private void deleteCheckedOnlineTask() {
    new ReactTaskJournalService()
        .cancelTaskCheck(studentsName, trainingOnlineTask)
        .reassignTask(studentsName, trainingOnlineTask)
        .unassignTask(trainingOnlineTask.getTaskName())
        .deleteOnlineTaskByName(trainingOnlineTask.getTaskName());
  }
}
