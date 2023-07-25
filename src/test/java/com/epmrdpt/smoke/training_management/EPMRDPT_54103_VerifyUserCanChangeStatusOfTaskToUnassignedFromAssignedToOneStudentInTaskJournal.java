package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_STATUS_ASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_STATUS_NOT_ASSIGNED;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.ReactAddAssignmentPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_54103_VerifyUserCanChangeStatusOfTaskToUnassignedFromAssignedToOneStudentInTaskJournal",
    groups = {"full", "smoke"})
public class EPMRDPT_54103_VerifyUserCanChangeStatusOfTaskToUnassignedFromAssignedToOneStudentInTaskJournal {

  private final String groupName = "test_task_journal";
  private final String taskName = "AutoTest_React_Online_Task_";
  private String studentName = "AutotestViktor AutotestPotapov";
  private boolean onlineTaskCreatedAndReadyForDeletion = false;
  private TrainingOnlineTask trainingOnlineTask;
  private ReactTaskJournalService reactTaskJournalService;
  private ReactTasksJournalScreen reactTasksJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTestData() {
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(taskName + LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("y.M.d_H:m:s")))
        .withGroup(groupName)
        .withStudent(studentName);
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactTrainingService()
        .openTaskJournalByGroupName(groupName);
    reactTaskJournalService = new ReactTaskJournalService();
    reactTaskJournalService
        .addOnlineTaskWithoutAssistantAndFillAllRequiredFieldsInTaskJournal(trainingOnlineTask);
    reactTasksJournalScreen = new ReactTasksJournalScreen()
        .clickCloseNotificationButton()
        .waitTableHeaderForVisibility();
  }

  @Test(priority = 1)
  public void checkThatOnlineTaskIsAssignedForStudent() {
    reactTasksJournalScreen.clickTaskStatus(trainingOnlineTask);
    new ReactAddAssignmentPopUpScreen()
        .clickDeadlineNotificationCheckbox()
        .clickAssignButton();
    Assert.assertEquals(reactTasksJournalScreen
            .clickCloseNotificationButton()
            .getTaskStatusTextByStudentNameAndTaskId(studentName, trainingOnlineTask),
        getValueOf(ONLINE_TASK_STATUS_ASSIGNED),
        "Online task status hasn't changed to 'Assigned'");
  }

  @Test(priority = 2)
  public void checkThatOnlineTaskIsUnassignedForStudent() {
    reactTaskJournalService.unassignOnlineTask(trainingOnlineTask);
    onlineTaskCreatedAndReadyForDeletion = true;
    Assert.assertEquals(reactTasksJournalScreen
            .clickCloseNotificationButton()
            .getTaskStatusTextByStudentNameAndTaskId(studentName, trainingOnlineTask),
        getValueOf(ONLINE_TASK_STATUS_NOT_ASSIGNED),
        "Online task status hasn't changed to 'Not assigned'");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteOnlineTask() {
    if (onlineTaskCreatedAndReadyForDeletion) {
      reactTaskJournalService
          .deleteOnlineTask(trainingOnlineTask);
    }
  }
}
