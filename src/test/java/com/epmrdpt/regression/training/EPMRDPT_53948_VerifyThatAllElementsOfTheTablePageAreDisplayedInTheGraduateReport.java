package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_ATTENDANCE_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_AVERAGE_MARK_FOR_CLASSES_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_AVERAGE_TASK_MARK_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_COMMENT_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_EXTERNAL_TASKS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_OFFLINE_TASKS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_ASSIGNED_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_EXPIRED_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_PASSED_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_REJECTED_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_SUBMITTED_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_STUDENTS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_TASKS_TOTAL_COLUMN_HEADER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_53948_VerifyThatAllElementsOfTheTablePageAreDisplayedInTheGraduateReport",
    groups = {"full", "react", "regression"})
public class EPMRDPT_53948_VerifyThatAllElementsOfTheTablePageAreDisplayedInTheGraduateReport {

  private ReactGraduateReportScreen reactGraduateReportScreen;
  private final String groupName = "GroupForDeletingStudentFromGroup";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenGraduateJournal() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer()).clickMyGroupsTab();
    new ReactHeaderScreen().waitTrainingTabForVisibility();
    reactGraduateReportScreen = new ReactTrainingService()
        .openTrainingJournalByGroupName(groupName)
        .clickGraduateReportTab()
        .waitSectionTabsVisibility();
  }

  @Test(priority = 2)
  public void verifyCheckboxColumnHeader() {
    assertTrue(reactGraduateReportScreen.isCheckboxColumnHeaderDisplayed(),
        "Checkbox column header isn't displayed!");
  }

  @Test(priority = 2)
  public void verifyStudentsColumnHeader() {
    assertEquals(reactGraduateReportScreen.getStudentColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_STUDENTS_COLUMN_HEADER),
        "'Students' column header has incorrect text!");
  }

  @Test(priority = 1)
  public void verifyAttendanceColumnHeader() {
    assertEquals(reactGraduateReportScreen.getAttendanceColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_ATTENDANCE_COLUMN_HEADER),
        "'Attendance' column header has incorrect text!");
  }

  @Test(priority = 1)
  public void verifyOfflineTasksColumnHeader() {
    assertEquals(reactGraduateReportScreen.getOfflineTasksColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_OFFLINE_TASKS_COLUMN_HEADER),
        "'Offline tasks' column header has incorrect text!");
  }

  @Test(priority = 2)
  public void verifyOnlineTasksColumnHeader() {
    assertEquals(reactGraduateReportScreen.getOnlineTasksColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_COLUMN_HEADER),
        "'Online tasks' column header has incorrect text!");
  }

  @Test(priority = 2)
  public void verifyOnlineTasksAssignedColumnHeader() {
    assertEquals(reactGraduateReportScreen.getOnlineTasksAssignedColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_ASSIGNED_COLUMN_HEADER),
        "Online tasks 'Assigned' column header has incorrect text!");
  }

  @Test(priority = 2)
  public void verifyOnlineTasksSubmittedColumnHeader() {
    assertEquals(reactGraduateReportScreen.getOnlineTasksSubmittedColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_SUBMITTED_COLUMN_HEADER),
        "Online tasks 'Submitted' column header has incorrect text!");
  }

  @Test(priority = 2)
  public void verifyOnlineTasksRejectedColumnHeader() {
    assertEquals(reactGraduateReportScreen.getOnlineTasksRejectedColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_REJECTED_COLUMN_HEADER),
        "Online tasks 'Rejected' column header has incorrect text!");
  }

  @Test(priority = 2)
  public void verifyOnlineTasksExpiredColumnHeader() {
    assertEquals(reactGraduateReportScreen.getOnlineTasksExpiredColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_EXPIRED_COLUMN_HEADER),
        "Online tasks 'Expired' column header has incorrect text!");
  }

  @Test(priority = 2)
  public void verifyOnlineTasksPassedColumnHeader() {
    assertEquals(reactGraduateReportScreen.getOnlineTasksPassedColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_ONLINE_TASKS_PASSED_COLUMN_HEADER),
        "Online tasks 'Passed' column header has incorrect text!");
  }

  @Test(priority = 2)
  public void verifyExternalTasksColumnHeader() {
    assertEquals(reactGraduateReportScreen.getExternalTasksColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_EXTERNAL_TASKS_COLUMN_HEADER),
        "'External tasks column header has incorrect text!");
  }

  @Test(priority = 3)
  public void verifyTasksTotalColumnHeader() {
    assertEquals(reactGraduateReportScreen.getTasksTotalColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_TASKS_TOTAL_COLUMN_HEADER),
        "'Tasks total' column header has incorrect text!");
  }

  @Test(priority = 3)
  public void verifyAverageTaskMarkColumnHeader() {
    assertEquals(reactGraduateReportScreen.getAverageTaskMarkColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_AVERAGE_TASK_MARK_COLUMN_HEADER),
        "'Average task mark' column header has incorrect text!");
  }

  @Test(priority = 3)
  public void verifyAverageMarkForClassesColumnHeader() {
    assertEquals(reactGraduateReportScreen.getAverageMarkForClassesColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_AVERAGE_MARK_FOR_CLASSES_COLUMN_HEADER),
        "'Average mark for classes' column header has incorrect text!");
  }

  @Test(priority = 4)
  public void verifyStudentStatusColumnHeader() {
    assertEquals(reactGraduateReportScreen.getStudentStatusColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_COLUMN_HEADER),
        "'Student status' column header has incorrect text!");
  }

  @Test(priority = 4)
  public void verifyCommentColumnHeader() {
    assertEquals(reactGraduateReportScreen.getCommentColumnHeaderText(),
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_COMMENT_COLUMN_HEADER),
        "'Comment' column header has incorrect text!");
  }
}
