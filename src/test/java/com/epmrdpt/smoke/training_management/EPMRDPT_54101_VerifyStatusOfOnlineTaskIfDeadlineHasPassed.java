package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_EXPIRATION_STATUS;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_54101_VerifyStatusOfOnlineTaskIfDeadlineHasPassed",
    groups = {"full", "smoke"})
public class EPMRDPT_54101_VerifyStatusOfOnlineTaskIfDeadlineHasPassed {

  private final String groupName = "test_task_journal";
  private final String taskName = "Online_Task_For_54101_Do_Not_Delete";
  private String studentName = "AutotestViktor AutotestPotapov";
  private String orangeColor = "rgba(244, 105, 2, 1)";
  private TrainingOnlineTask trainingOnlineTask;
  private ReactTasksJournalScreen reactTasksJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTaskAndLoginAndGoToGroup() {
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(taskName)
        .withGroup(groupName)
        .withStudent(studentName);
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab()
        .waitGroupsTableFieldForVisible();
    new ReactTrainingService()
        .openTaskJournalByGroupName(trainingOnlineTask.getGroup());
    reactTasksJournalScreen = new ReactTasksJournalScreen()
        .clickAllPeriodButton()
        .waitTableHeaderForVisibility();
  }

  @Test(priority = 1)
  public void checkThatStatusButtonIsExpired() {
    Assert.assertEquals(
        reactTasksJournalScreen.getTaskStatusByOnlineTask(trainingOnlineTask),
        LocaleProperties.getValueOf(ONLINE_TASK_EXPIRATION_STATUS),
        "The status of the online task is not correct!");
  }

  @Test(priority = 2)
  public void checkThatStatusButtonIsActiveAndOrange() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactTasksJournalScreen
            .isTaskStatusByStudentNameAndIdEnabled(trainingOnlineTask),
        "The status button is not enabled!");
    softAssert.assertEquals(
        reactTasksJournalScreen
            .getColorOfTaskStatusByStudentNameAndId(trainingOnlineTask),
        orangeColor,
        "The color of the status button is not orange!");
    softAssert.assertAll();
  }
}
