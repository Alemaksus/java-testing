package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.ReactHomeTaskPopUpScreen;
import com.epmrdpt.screens.ReactOnlineTaskInfoPopUpScreen;
import com.epmrdpt.screens.ReactTaskHistoryPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_59312_VerifyThatTheTaskHistoryPopUpDisplaysDataWhichMatchesToTheCertainStudentInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_59312_VerifyThatTheTaskHistoryPopUpDisplaysDataWhichMatchesToTheCertainStudentInTheTaskJournal {

  private ReactTasksJournalScreen reactTasksJournalScreen;
  private ReactOnlineTaskInfoPopUpScreen reactOnlineTaskInfoPopUpScreen;
  private ReactHomeTaskPopUpScreen reactHomeTaskPopUpScreen;
  private ReactTaskHistoryPopUpScreen reactTaskHistoryPopUpScreen;
  private String taskStatusForChosenStudent;
  private final TrainingOnlineTask trainingOnlineTask = new TrainingOnlineTask()
      .withTaskName("TEST_TAKS")
      .withGroup("test_task_journal")
      .withStudent("AutotestViktor AutotestPotapov");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTasksJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab()
        .typeGroupNameInput(trainingOnlineTask.getGroup())
        .clickSearchIcon()
        .waitPageNumbersForDisappear()
        .clickGroupNameButton()
        .clickTaskJournalTab()
        .clickAllPeriodButton();
    taskStatusForChosenStudent = reactTasksJournalScreen
        .getTaskStatusTextByStudentNameAndTaskId(
            trainingOnlineTask.getStudent(), trainingOnlineTask);
  }

  @Test(priority = 1)
  public void verifyThatTheGeneralInfoPopoverIsOpened() {
    reactOnlineTaskInfoPopUpScreen = reactTasksJournalScreen.clickOnlineTaskInTaskJournalByName(
        trainingOnlineTask.getTaskName());
    Assert.assertTrue(reactOnlineTaskInfoPopUpScreen.isScreenLoaded(),
        "The General info popover is not opened");
  }

  @Test(priority = 2)
  public void verifyThatTheTaskPopUpIsOpened() {
    reactHomeTaskPopUpScreen = reactOnlineTaskInfoPopUpScreen.waitOnlineTaskNameOnPopUpVisibility()
        .moveTaskInfoPopUpToTheTopOfTheScreen().clickCheckButton().waitSubmitButtonIsPresented();
    Assert.assertTrue(reactHomeTaskPopUpScreen.isScreenLoaded(), "Task pop-up is not opened");
  }

  @Test(priority = 3)
  public void verifyThatTheTaskHistoryPopUpIsOpenedForSelectedStudent() {
    reactTaskHistoryPopUpScreen = reactHomeTaskPopUpScreen.clickTaskHistoryButton();
    Assert.assertTrue(reactTaskHistoryPopUpScreen.waitScreenLoaded()
            .isHistoryPopUpOpenedForSelectedStudent(trainingOnlineTask.getStudent()),
        "Task history pop-up is not opened for selected student");
  }

  @Test(priority = 4)
  public void verifyThatTheTaskHistoryPopUpDisplaysDataOfCertainStudent() {
    List<String> taskHistoryList = reactTaskHistoryPopUpScreen.getAllHistoryTextFromTaskJournal();
    String lastStatus = taskHistoryList.get(taskHistoryList.size() - 1);
    Assert.assertEquals(lastStatus.substring(lastStatus.lastIndexOf('\n') + 1),
        taskStatusForChosenStudent, "Expected status for selected student not equals to actual");
  }
}
