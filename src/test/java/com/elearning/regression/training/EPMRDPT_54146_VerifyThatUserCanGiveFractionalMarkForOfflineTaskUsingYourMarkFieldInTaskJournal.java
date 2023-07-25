package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.utils.DoubleUtils.roundToTwoDecimalPlaceDouble;
import static com.epmrdpt.utils.RandomUtils.getRandomDoubleMarkInInterval;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingOfflineTask;
import com.epmrdpt.screens.ReactJournalMarkPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.EColorUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_54146_VerifyThatUserCanGiveFractionalMarkForOfflineTaskUsingYourMarkFieldInTaskJournal",
    groups = {"student", "full", "react", "regression"})
public class EPMRDPT_54146_VerifyThatUserCanGiveFractionalMarkForOfflineTaskUsingYourMarkFieldInTaskJournal {

  private final String groupName = "AutoTest Student Java Group";
  private final String taskName = "OFFLINE TASK WITH FRACTIONAL";
  private final int taskId = 11303;
  private final String studentName = "Student AutoTest";
  public static final String COLOR_SAVE_BUTTON_ACTIVE = EColorUtils.GREEN_ACTIVE.getColorRgbaFormat();
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private ReactJournalMarkPopUpScreen reactJournalMarkPopUpScreen;
  private final TrainingOfflineTask trainingOfflineTask = new TrainingOfflineTask();

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupNewOfflineTask() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer()).clickMyGroupsTab();
    trainingOfflineTask
        .withStudent(studentName)
        .withTaskName(taskName)
        .withTaskId(taskId)
        .withGroup(groupName)
        .withMark(roundToTwoDecimalPlaceDouble(getRandomDoubleMarkInInterval(1, 9)));
    reactTasksJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(trainingOfflineTask.getGroup())
        .clickTaskJournalTab()
        .waitForLoadingSpinnerInvisibility()
        .clickAllPeriodButton()
        .waitTableHeaderForVisibility();
    trainingOfflineTask.withTaskId(reactTasksJournalScreen.getTaskId(trainingOfflineTask));
    reactTasksJournalScreen.clickMarkFieldInJournalTask(trainingOfflineTask);
    reactJournalMarkPopUpScreen = new ReactJournalMarkPopUpScreen();
    if (reactJournalMarkPopUpScreen.isDisplayedCleanMarkButton()) {
      reactJournalMarkPopUpScreen.cleanOldMark();
    }
    reactJournalMarkPopUpScreen.typeMark(trainingOfflineTask.getFractionalMark());
  }

  @Test(priority = 1)
  public void validationOfDecimalValueInputField() {
    assertEquals(reactJournalMarkPopUpScreen.getCurrentMark(),
        trainingOfflineTask.getFractionalMark(),
        "There is no 'Mark' in the input field");
  }

  @Test(priority = 2)
  public void checkSaveButtonIsActive() {
    assertEquals(reactJournalMarkPopUpScreen.getSaveButtonColor(), COLOR_SAVE_BUTTON_ACTIVE,
        "'Save button' not active on the 'Offline task info pop up'!");
  }

  @Test(priority = 3)
  public void checkForStudentInTheGroupJournal() {
    reactJournalMarkPopUpScreen.clickSaveButton();
    assertTrue(reactTasksJournalScreen.isExpectedFractionalMarkToBePresent(trainingOfflineTask),
        "The resulting estimate does not match the entered one.");
  }
}
