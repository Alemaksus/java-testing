package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.UserProperty.getValueOf;
import static com.epmrdpt.framework.properties.UserPropertyConst.EXCEL_FILE_NAME;
import static com.epmrdpt.utils.FileUtils.getImportDocumentPath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingOfflineTask;
import com.epmrdpt.screens.ReactJournalMarkPopUpScreen;
import com.epmrdpt.screens.ReactOfflineTaskCommentPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_59306_VerifyThatUserCanUploadDataForOfflineTaskUsingExcelTemplateInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_59306_VerifyThatUserCanUploadDataForOfflineTaskUsingExcelTemplateInTheTaskJournal {

  private static final String GROUP_REPORT_PATH = getImportDocumentPath(
      getValueOf(EXCEL_FILE_NAME));
  private static final String GROUP_NAME = "test_task_journal";
  private static final String TASK_NAME = "Offline_task_for_edit";
  private static final String FIRST_STUDENT_NAME = "Auto test user first";
  private static final String SECOND_STUDENT_NAME = "Autotest user second";
  private static final String THIRD_STUDENT_NAME = "Autotest user third";
  private static final String FOURTH_STUDENT_NAME = "AutotestViktor AutotestPotapov";
  private static final String COMMENT = "Auto_comment";
  private static final int FIRST_STUDENT_MARK = 1;
  private static final int SECOND_STUDENT_MARK = 2;
  private static final int THIRD_STUDENT_MARK = 3;
  private static final int FOURTH_STUDENT_MARK = 4;
  private static final int TEMP_MARK_VALUE = 10;
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private ReactJournalMarkPopUpScreen reactJournalMarkPopUpScreen;
  private ReactOfflineTaskCommentPopUpScreen reactOfflineTaskCommentPopUpScreen;
  private List<TrainingOfflineTask> trainingOfflineTaskList;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupOfflineTask() {
    trainingOfflineTaskList = new ArrayList<>(Arrays.asList(
        new TrainingOfflineTask().withStudent(FIRST_STUDENT_NAME).withTaskName(TASK_NAME)
            .withGroup(GROUP_NAME).withMark(FIRST_STUDENT_MARK),
        new TrainingOfflineTask().withStudent(SECOND_STUDENT_NAME).withTaskName(TASK_NAME)
            .withGroup(GROUP_NAME).withMark(SECOND_STUDENT_MARK),
        new TrainingOfflineTask().withStudent(THIRD_STUDENT_NAME).withTaskName(TASK_NAME)
            .withGroup(GROUP_NAME).withMark(THIRD_STUDENT_MARK),
        new TrainingOfflineTask().withStudent(FOURTH_STUDENT_NAME).withTaskName(TASK_NAME)
            .withGroup(GROUP_NAME).withMark(FOURTH_STUDENT_MARK)));
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true, dependsOnMethods = "setupOfflineTask")
  public void setup() {
    reactTasksJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab()
        .typeGroupNameInput(GROUP_NAME)
        .clickSearchIcon()
        .clickGroupNameButton()
        .clickTaskJournalTab()
        .clickAllPeriodButton()
        .waitForLoadingSpinnerInvisibility()
        .waitTableHeaderForVisibility();
    trainingOfflineTaskList.forEach(trainingOfflineTask -> trainingOfflineTask.withTaskId(
        reactTasksJournalScreen.getTaskId(trainingOfflineTask)));
  }

  @Test(priority = 1)
  public void VerifyExcelTemplateImportedInTheTaskJournal() {
    assertTrue(
        reactTasksJournalScreen.clickImportExcelButton().waitScreenLoading().importExcelReport(
            GROUP_REPORT_PATH).isSucesfullExcelImportNotificationDisplayed(),
        "Excel template did not imported to the task journal");
  }

  @Test(priority = 2)
  public void VerifyImportedTaskMarksFromExcelTemplate() {
    reactJournalMarkPopUpScreen = new ReactJournalMarkPopUpScreen();
    reactTasksJournalScreen.clickCloseNotificationButton();
    for (TrainingOfflineTask trainingOfflineTask : trainingOfflineTaskList) {
      assertEquals(
          Double.parseDouble(reactTasksJournalScreen.getStudentMarkByStudentNameAndTrainingTaskId(
              trainingOfflineTask)), trainingOfflineTask.getFractionalMark(),
          String.format("%s mark in offline task did not equals to mark from Excel template",
              trainingOfflineTask.getStudent()));
      reactTasksJournalScreen.clickMarkFieldInJournalTask(trainingOfflineTask);
      reactJournalMarkPopUpScreen.waitMarkPopUpForVisibility()
          .clickMarkButtonByValue(TEMP_MARK_VALUE).clickSaveButton();
    }
  }

  @Test(priority = 3)
  public void VerifyImportedTaskCommentsFromExcelTemplate() {
    reactOfflineTaskCommentPopUpScreen = new ReactOfflineTaskCommentPopUpScreen();
    for (TrainingOfflineTask trainingOfflineTask : trainingOfflineTaskList) {
      Assert.assertTrue(reactTasksJournalScreen.clickCommentFieldInJournalTask(trainingOfflineTask)
          .waitCommentsVisible().moveTaskCommentPopUpToTheTopOfTheScreen()
          .isCommentDisplayed(COMMENT), String.format(
          "Comment for %s student in offline task did not equals to comment from Excel template",
          trainingOfflineTask.getStudent()));
      reactOfflineTaskCommentPopUpScreen.deleteComment().waitGroupJournalTableForVisibility();
    }
  }
}
