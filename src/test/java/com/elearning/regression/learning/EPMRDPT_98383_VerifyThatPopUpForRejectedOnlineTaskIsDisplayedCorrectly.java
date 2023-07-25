package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ALL_COMMENTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ASSIGNMENT_NOT_PASSED_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_NO_ATTEMPTS_ALERT_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_OPEN_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_REJECTED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_DESCRIPTION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_DEADLINE_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_HEADER_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_START_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_WORK_COMMENTS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_MARK_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_REVIEWER_ROLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_TRAINER_ROLE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_98383_VerifyThatPopUpForRejectedOnlineTaskIsDisplayedCorrectly",
    groups = {"full", "regression", "student"})
public class EPMRDPT_98383_VerifyThatPopUpForRejectedOnlineTaskIsDisplayedCorrectly {

  private final String trainerReviewComment = "Rejected_Two";
  private final String taskName = "AutoTest_RejectedOnlineTask";
  private final String className = "AutoTest_LearningStudent_DepartmentAffiliate_Tasks";
  private final String taskDescription = "Rejected Online Task for AutoTest";
  private final String attachedTaskFileName = "RejectedOnlineTask.docx";
  private final String trainerName = "ManagerTest ManagerTest";
  private final String reviewerName = "AutoTrainer AutoTrainer";
  private final String secondReviewerName = "ManagerTest ManagerTest";
  private final String studentName = "Student AutoTest";
  private static final String GLOBE_ICON_PATH = "/Content/themes/Redesign/ico/tasks-online-white.png";
  private static final Pattern DATE_PATTERN = Pattern.compile(
      "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2}), (([0,1][0-9])|(2[0-3])):[0-5][0-9]");
  private static final String ATTEMPTS_REGEX = getValueOf(ONLINE_POPUP_ATTEMPTS_LABEL)
      + " \\d+ " + getValueOf(ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN);
  private static final String MARK_REGEX =
      getValueOf(TASKS_TAB_MARK_TEXT) + ": \\d{1,2} / \\d{1,2} \\(\\d{1,2}\\)";

  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    tasksPopUpScreenOnLearningPageScreen = new LearningService()
        .navigateToTaskOfDepartmentAffiliateInUrgentTab(className, taskName);
  }

  @Test
  public void checkPopUpTitle() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isOnlineTaskIconDisplayed(),
            "Task icon isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .getOnlineTaskIconBackground()
            .contains(GLOBE_ICON_PATH),
        "Task icon is incorrect, globe icon isn't present!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getPopUpTitleText(),
            getValueOf(ONLINE_TASK_POPUP_HEADER_TEXT),
            "Task pop-up title is incorrect!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isCloseButtonDisplayed(),
            "Cross mark for close isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkPopUpHeader() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getStatusLabelText(),
            getValueOf(ONLINE_POPUP_STATUS_REJECTED),
            "Task status is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getTaskNameText(), taskName,
            "Task name is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getGroupNameText(), className,
            "Class name is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkStartDateAndDeadlineDate() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getStartDateLabelText(),
            getValueOf(ONLINE_TASK_POPUP_START_DATE),
            "'Start date' label text is incorrect!");
    softAssert
        .assertTrue(
            DATE_PATTERN.matcher(tasksPopUpScreenOnLearningPageScreen.getStartDateText()).find(),
            "'Start date' is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getDeadlineDateLabelText(),
            getValueOf(ONLINE_TASK_POPUP_DEADLINE_DATE),
            "'Deadline date' label text is incorrect!");
    softAssert
        .assertTrue(
            DATE_PATTERN.matcher(tasksPopUpScreenOnLearningPageScreen.getDeadlineDateText()).find(),
            "'Deadline date' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkAttempts() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        Pattern.compile(ATTEMPTS_REGEX)
            .matcher(tasksPopUpScreenOnLearningPageScreen.getAttemptsText()).find(),
        "Attempts text is incorrect!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getNoAttemptsLeftAlertMessageText(),
        getValueOf(ONLINE_POPUP_NO_ATTEMPTS_ALERT_MESSAGE),
        "'No attempts left' Alert message isn't displayed with correct text!"
    );
    softAssert.assertAll();
  }

  @Test
  public void checkTrainerInformation() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getTrainerNameText(), trainerName,
            "'Trainer name' is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getTrainerRoleText(),
            getValueOf(TASKS_TAB_TRAINER_ROLE),
            "'Trainer role' is incorrect!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isTrainerPhotoDisplayed(),
            "Trainer photo isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkReviewerInformation() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getReviewerNameText(), secondReviewerName,
            "'Reviewer name' is incorrect!");
    softAssert
        .assertEquals(tasksPopUpScreenOnLearningPageScreen.getReviewerRoleText(),
            getValueOf(TASKS_TAB_REVIEWER_ROLE),
            "'Reviewer role' is incorrect!");
    softAssert
        .assertTrue(tasksPopUpScreenOnLearningPageScreen.isReviewerPhotoDisplayed(),
            "Reviewer photo isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkTaskDescription() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.getCompleteTaskDescriptionText()
                .contains(getValueOf(ONLINE_POPUP_TASK_DESCRIPTION_LABEL)),
            "'Task description' label text is incorrect!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.getCompleteTaskDescriptionText()
                .contains(taskDescription),
            "'Task description' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkAttachedTaskFiles() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen
                .isAttachedTaskFileByNameDisplayed(attachedTaskFileName),
            "Attached task file isn't displayed!");
    softAssert
        .assertFalse(
            tasksPopUpScreenOnLearningPageScreen.isAttachedTaskFileDisabled(),
            "Attached task file is clickable!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getOpenButtonForAttachedTaskFileText(),
            getValueOf(ONLINE_POPUP_OPEN_BUTTON),
            "'Open' button for attached task-file isn't displayed with correct text!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkAllCommentsSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getAllCommentsLabelText(),
        getValueOf(ONLINE_POPUP_ALL_COMMENTS_LABEL),
        "'All Comments' label text is not displayed with proper text!");
    softAssert
        .assertTrue(tasksPopUpScreenOnLearningPageScreen.isAllCommentsToggleDisplayed(),
            "All Comments '+' toggle is not displayed");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkStudentInfoInAllCommentsSection() {
    softAssert = new SoftAssert();
    tasksPopUpScreenOnLearningPageScreen.clickAllCommentsToggle();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isStudentPhotoInAllCommentsSectionDisplayed(),
            "Student Photo in All comments section isn't displayed!");
    softAssert
        .assertEquals(tasksPopUpScreenOnLearningPageScreen.getStudentNameInAllCommentsSectionText(),
            studentName, "Student name in All comments section isn't displayed!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isStudentCommentInAllCommentsSectionDisplayed(),
            "Student comment in All comments section isn't displayed");
    softAssert
        .assertTrue(
            DATE_PATTERN.matcher(tasksPopUpScreenOnLearningPageScreen
                .getCommentSubmissionDateInAllCommentsSectionText()).find(),
            "Student comment submission date in All comments section is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkReviewerInfoInAllCommentsSection() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isReviewerPhotoInAllCommentsSectionDisplayed(),
            "Trainer Photo in All comments section isn't displayed!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getReviewerNameInAllCommentsSectionText(),
            reviewerName, "Trainer name in All comments section isn't displayed!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isReviewerCommentInAllCommentsSectionDisplayed(),
            "Trainer comment in All comments section isn't displayed");
    softAssert
        .assertTrue(
            DATE_PATTERN.matcher(tasksPopUpScreenOnLearningPageScreen
                .getCommentReviewDateInAllCommentsSectionText()).find(),
            "Trainer comment review date in All comments section is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkWorkCommentField() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getWorkCommentsTitleText(),
            getValueOf(ONLINE_TASK_POPUP_WORK_COMMENTS_TITLE),
            "'Work Comments' is incorrect!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getTrainerCommentsMessageText(),
        trainerReviewComment, "Trainer Review comments are not displayed with correct text!");
    softAssert.assertAll();
  }

  @Test
  public void checkMarks() {
    assertTrue(
        Pattern.compile(MARK_REGEX)
            .matcher(tasksPopUpScreenOnLearningPageScreen.getMarkText()).find(),
        "Mark text isn't correct!");
  }

  @Test
  public void checkAssignmentStatusAboutMessage() {
    assertEquals(tasksPopUpScreenOnLearningPageScreen.getAssignmentStatusAboutMessageText(),
        getValueOf(ONLINE_POPUP_ASSIGNMENT_NOT_PASSED_MESSAGE),
        "'You have not passed online test' message is not displayed!");
  }
}
