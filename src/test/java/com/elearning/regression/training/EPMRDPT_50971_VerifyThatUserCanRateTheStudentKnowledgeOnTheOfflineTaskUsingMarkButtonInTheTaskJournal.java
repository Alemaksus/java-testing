package com.epmrdpt.regression.training;

import static com.epmrdpt.utils.RandomUtils.getRandomNumberInInterval;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingOfflineTask;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactJournalMarkPopUpScreen;
import com.epmrdpt.screens.ReactTaskMarkPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(
    description = "EPMRDPT_50971_VerifyThatUserCanRateTheStudentKnowledgeOnTheOfflineTaskUsingMarkButtonInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50971_VerifyThatUserCanRateTheStudentKnowledgeOnTheOfflineTaskUsingMarkButtonInTheTaskJournal {

  private ReactTasksJournalScreen reactTasksJournalScreen;
  private TrainingOfflineTask trainingOfflineTask;
  private ReactTaskJournalService reactTaskJournalService;
  private ReactTaskMarkPopUpScreen reactTaskMarkPopUpScreen;
  private ReactJournalMarkPopUpScreen reactJournalMarkPopUpScreen;
  private int studentMarkValue;
  private User user;
  private final String studentName = "Student AutoTest";
  private final String groupName = "AutoTest Student Java Group";

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_50971_VerifyThatUserCanRateTheStudentKnowledgeOnTheOfflineTaskUsingMarkButtonInTheTaskJournal(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupNewOfflineTask() {
    reactTaskJournalService = new ReactTaskJournalService();
    reactTaskMarkPopUpScreen = new ReactTaskMarkPopUpScreen();
    trainingOfflineTask = new TrainingOfflineTask()
        .withTaskName("AutoTest_ReactOfflineTask_1")
        .withGroup(groupName)
        .withDate(LocalDate.now())
        .withStudent(studentName);
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .clickMyGroupsTab();
    new ReactHeaderScreen().waitTrainingTabForVisibility();
    reactTasksJournalScreen = new ReactTrainingService()
        .openTaskJournalByGroupName(trainingOfflineTask.getGroup())
        .addOfflineTaskAndFillAllRequiredFieldsInTaskJournal(trainingOfflineTask)
        .clickCloseNotificationButton();
    trainingOfflineTask.withTaskId(reactTasksJournalScreen.getTaskId(trainingOfflineTask));
  }

  @Test(priority = 1)
  public void checkJournalMarkPopUpDisplayed() {
    reactTasksJournalScreen.clickMarkFieldInJournalTask(trainingOfflineTask);
    reactJournalMarkPopUpScreen = new ReactJournalMarkPopUpScreen()
        .waitMarkPopUpForVisibility();
    assertTrue(reactJournalMarkPopUpScreen.isScreenLoaded(),
        "'Mark' pop-up is not displayed!");
  }

  @Test(priority = 2)
  public void checkChosenMarkButtonIsBlue() {
    studentMarkValue = getRandomNumberInInterval(1, 10);
    reactJournalMarkPopUpScreen.clickMarkButtonByValue(studentMarkValue);
    assertEquals(reactTaskMarkPopUpScreen.getBackgroundColorMarkButtonByValue(studentMarkValue),
        ReactTaskMarkPopUpScreen.COLOR_CHOSEN_BUTTON_ACTIVE,
        "The chosen mark does not become blue!");
  }

  @Test(priority = 3)
  public void checkStudentMarkDisplayed() {
    reactJournalMarkPopUpScreen
        .clickSaveButton()
        .clickTwoWeekPeriodButton();
    reactTasksJournalScreen.waitGroupJournalTableForVisibility();
    assertEquals(reactTasksJournalScreen
            .getStudentMarkByStudentNameAndTrainingTaskId(trainingOfflineTask),
        (String.valueOf(studentMarkValue)), "Chosen student's mark is not displayed!");
    deleteCheckedTask();
  }

  private void deleteCheckedTask() {
    reactTaskJournalService.deleteOfflineTaskByName(trainingOfflineTask.getTaskName());
    reactTasksJournalScreen.waitTaskForInvisibility(trainingOfflineTask.getTaskName());
  }
}

