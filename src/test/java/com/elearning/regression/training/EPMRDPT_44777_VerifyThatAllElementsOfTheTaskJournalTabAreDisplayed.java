package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_ADD_EXTERNAL_TASK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_ADD_TASK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_COLUMN_HEADER_EXTERNAL_TASK_STATISTICS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_COLUMN_HEADER_STUDENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_COLUMN_HEADER_TASK_STATISTICS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_COPY_TASKS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_EXPORT_EXCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_IMPORT_EXCEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_MARK_REPORT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_MONTH_PERIOD;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_TWO_WEEKS_PERIOD;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_WEEK_PERIOD;
import static com.epmrdpt.utils.StringUtils.isDateMatchExpectedPattern;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_44777_VerifyThatAllElementsOfTheTaskJournalTabAreDisplayed",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44777_VerifyThatAllElementsOfTheTaskJournalTabAreDisplayed {

  private final String groupName = "AutoTest_affiliate";
  private final String taskName = "AutoTest_ReactOnlineTask_" + LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("y.M.d_H:m:s"));
  private final String dateFormat = "dd.MM.yyyy";

  private ReactTasksJournalScreen reactTasksJournalScreen;
  private SoftAssert softAssert;
  private TrainingOnlineTask trainingOnlineTask;
  private ReactTaskJournalService reactTaskJournalService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTaskJournalTab() {
    reactTaskJournalService = new ReactTaskJournalService();
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(taskName);
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility()
        .clickMyGroupsTab();
    reactTasksJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(groupName)
        .clickTaskJournalTab()
        .waitGroupJournalTableForVisibility()
        .waitTableHeaderForVisibility();
    reactTaskJournalService.addOnlineTaskIfNoTasksOnTable(trainingOnlineTask);
  }

  @Test
  public void checkAddTaskButtonsHaveCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactTasksJournalScreen.getAddTaskButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_ADD_TASK_BUTTON),
        "'Add task' button on 'Task journal' tab has incorrect text!");
    softAssert.assertEquals(reactTasksJournalScreen.getAddExternalTaskButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_ADD_EXTERNAL_TASK_BUTTON),
        "'Add External task' button on 'Task journal' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test
  public void checkMarkReportButtonHasCorrectText() {
    assertEquals(reactTasksJournalScreen.getMarkReportButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_MARK_REPORT_BUTTON),
        "'Mark report' button on 'Task journal' tab has incorrect text!");
  }

  @Test
  public void checkCopyTasksButtonHasCorrectText() {
    assertEquals(reactTasksJournalScreen.getCopyTasksButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_COPY_TASKS_BUTTON),
        "'Copy tasks' button on 'Task journal' tab has incorrect text!");
  }

  @Test
  public void checkExcelButtonsHaveCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactTasksJournalScreen.getExportExcelButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_EXPORT_EXCEL_BUTTON),
        "'Export excel' button on 'Task journal' tab has incorrect text!");
    softAssert.assertEquals(reactTasksJournalScreen.getImportExcelButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_IMPORT_EXCEL),
        "'Import excel' button on 'Task journal' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test
  public void checkDailyFilterButtonsHaveCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactTasksJournalScreen.getWeekPeriodButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_WEEK_PERIOD),
        "'Week' button on 'Task journal' tab has incorrect text!");
    softAssert.assertEquals(reactTasksJournalScreen.getTwoWeekPeriodButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_TWO_WEEKS_PERIOD),
        "'Two week' button on 'Task journal' tab has incorrect text!");
    softAssert.assertEquals(reactTasksJournalScreen.getMonthPeriodButtonText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_MONTH_PERIOD),
        "'Month' button on 'Task journal' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test
  public void checkThatGearMenuButtonDisplayed() {
    assertTrue(reactTasksJournalScreen.isGearMenuButtonDisplayed(),
        "'Gear menu' button on 'Task journal' is not displayed!");
  }

  @Test
  public void checkHeaderOfFirstColumnInTaskJournalTable() {
    assertEquals(reactTasksJournalScreen.getStudentsColumnHeaderText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_COLUMN_HEADER_STUDENTS),
        "Header of the first column in 'Task Journal' table has incorrect text!");
  }

  @Test
  public void checkStudentsList() {
    softAssert = new SoftAssert();
    int numberOfStudents = reactTasksJournalScreen
        .getStudentRowsInTableCount();
    for (int studentIndex = 0; studentIndex < numberOfStudents; studentIndex++) {
      softAssert.assertFalse(
          reactTasksJournalScreen.getStudentNameInTableByIndex(studentIndex + 1).isEmpty(),
          format("Student name in row %s in 'Task Journal' table is empty!", studentIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkHeaderOfTaskColumnInTaskJournalTable() {
    softAssert = new SoftAssert();
    int numberOfTasks = reactTasksJournalScreen
        .getTaskHeaderColumnsInTableCount();
    for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex++) {
      softAssert.assertTrue(
          reactTasksJournalScreen
              .mouseOverTaskIconInTableByIndex(taskIndex + 1)
              .isTaskIconInTableDisplayedByIndex(taskIndex + 1),
          format("Task icon in column %s in 'Task Journal' table isn't displayed!", taskIndex + 1));
      softAssert.assertFalse(
          reactTasksJournalScreen
              .mouseOverTaskNameInTableByIndex(taskIndex + 1)
              .getTaskNameInTableByIndex(taskIndex + 1).isEmpty(),
          format("Task name in column %s in 'Task Journal' table is empty!", taskIndex + 1));
      softAssert.assertTrue(
          isDateMatchExpectedPattern(
              reactTasksJournalScreen
                  .getDateByTaskInTable(taskIndex + 1), dateFormat),
          format("'Date of task' %s value in 'Task Journal' has incorrect format!", taskIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkHeaderOfTaskStaticsColumnInTaskJournalTable() {
    assertEquals(reactTasksJournalScreen.getTaskStatisticsColumnHeaderText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_COLUMN_HEADER_TASK_STATISTICS),
        "Header of the 'TASK STATISTICS' column in 'Task Journal' table has incorrect text!");
  }

  @Test
  public void checkHeaderOfExternalTaskStaticsColumnInTaskJournalTable() {
    assertEquals(reactTasksJournalScreen.getExternalTaskStatisticsColumnHeaderText(),
        getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_COLUMN_HEADER_EXTERNAL_TASK_STATISTICS),
        "Header of the 'EXTERNAL TASK STATISTICS' column in 'Task Journal' table has incorrect text!");
  }
}

