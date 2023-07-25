package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ADD_FILE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_COMMENT_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_OPEN_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_SUBMITTED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_SUBMIT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_CHAT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_DESCRIPTION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_SUBMISSION_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_USER_COMMENT_SHOW_LESS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_USER_COMMENT_SHOW_MORE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_DEADLINE_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_HEADER_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_START_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_MARK_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_REVIEWER_ROLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_TRAINER_ROLE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56209_VerifiedTheFeaturesOfDisplayingTheSubmittedOnlineTask",
    groups = {"full", "regression", "student"})
public class EPMRDPT_56209_VerifiedTheFeaturesOfDisplayingTheSubmittedOnlineTask {

  private final String addFileIcon = "+";
  private final String taskName = "AutoTest_SubmittedOnlineTask";
  private final String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";
  private final String taskDescription = "Submitted Online Task for AutoTest";
  private final String attachedTaskFileName = "SubmittedOnlineTask.docx";
  private final String trainerName = "ManagerTest ManagerTest";
  private final String reviewerName = "AutoTrainer AutoTrainer";
  private final String secondReviewerName = "ManagerTest ManagerTest";
  private final String studentName = "Student AutoTest";
  private final String globeIconPath = "/Content/themes/Redesign/ico/tasks-online-white.png";
  private final String userComment = "The task is ready to submit!";
  private final String showMoreText = LocaleProperties
      .getValueOf(ONLINE_POPUP_USER_COMMENT_SHOW_MORE);
  private final String showLessText = LocaleProperties
      .getValueOf(ONLINE_POPUP_USER_COMMENT_SHOW_LESS);
  private final String studentSubmittedAnswerWithMoreThan320Chars =
      "AutoTest Answer for AutoTest_SubmittedOnlineTask"
          + "  task with class name as AutoTest_LearningStudent_DepartmentAffiliate_Tasks with start date as 20.08.2020, 08:00"
          + " and deadline date as 31.05.2040, 08:00 with maximum marks 10 and pass mark 6. It has 3 attempts. It has ManagerTest ManagerTest"
          + " as Trainer and AutoTrainer AutoTrainer as Reviewer. And attached file below with name as SolutionSubmittedOnlineTask.";
  private final String dateAndTimeRegex = "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2}), (([0,1][0-9])|(2[0-3])):[0-5][0-9]";
  private final Pattern datePattern = Pattern.compile(dateAndTimeRegex);
  private final String attemptsRegex = LocaleProperties.getValueOf(ONLINE_POPUP_ATTEMPTS_LABEL)
      + " \\d+ " + LocaleProperties.getValueOf(ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN);
  private final String markRegex = getValueOf(TASKS_TAB_MARK_TEXT)
      + ": \\d{1,2} / \\d{1,2} \\(\\d{1,2}\\)";
  private final String submissionTaskRegex =
      String.format(LocaleProperties.getValueOf(ONLINE_POPUP_TASK_SUBMISSION_MESSAGE),
          dateAndTimeRegex);

  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService().navigateToTaskOfEpamTrainingInUrgentTab(groupName, taskName);
  }

  @Test
  public void checkPopUpHeader() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isOnlineTaskIconDisplayed(),
            "Task icon isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .getOnlineTaskIconBackground()
            .contains(globeIconPath),
        "Task icon is incorrect, globe icon isn't present!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getPopUpTitleText(),
            LocaleProperties.getValueOf(ONLINE_TASK_POPUP_HEADER_TEXT),
            "Task pop-up title is incorrect!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isCloseButtonDisplayed(),
            "Cross mark for close isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkPopUpTitle() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getStatusLabelText(),
            LocaleProperties.getValueOf(ONLINE_POPUP_STATUS_SUBMITTED),
            "Task status is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getTaskNameText(), taskName,
            "Task name is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getGroupNameText(), groupName,
            "Group name is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkStartDateAndDeadlineDate() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getStartDateLabelText(),
            LocaleProperties.getValueOf(ONLINE_TASK_POPUP_START_DATE),
            "'Start date' label text is incorrect!");
    softAssert
        .assertTrue(
            datePattern.matcher(tasksPopUpScreenOnLearningPageScreen.getStartDateText()).find(),
            "'Start date' is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getDeadlineDateLabelText(),
            LocaleProperties.getValueOf(ONLINE_TASK_POPUP_DEADLINE_DATE),
            "'Deadline date' label text is incorrect!");
    softAssert
        .assertTrue(
            datePattern.matcher(tasksPopUpScreenOnLearningPageScreen.getDeadlineDateText()).find(),
            "'Deadline date' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkMarks() {
    assertTrue(
        Pattern.compile(markRegex).matcher(tasksPopUpScreenOnLearningPageScreen.getMarkText())
            .find(),
        "Mark text isn't correct!");
  }

  @Test
  public void checkAttempts() {
    assertTrue(
        Pattern.compile(attemptsRegex)
            .matcher(tasksPopUpScreenOnLearningPageScreen.getAttemptsText())
            .find(),
        "Attempts text is incorrect!");
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
            LocaleProperties.getValueOf(TASKS_TAB_TRAINER_ROLE),
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
            LocaleProperties.getValueOf(TASKS_TAB_REVIEWER_ROLE),
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
                .contains(LocaleProperties.getValueOf(ONLINE_POPUP_TASK_DESCRIPTION_LABEL)),
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
            LocaleProperties.getValueOf(ONLINE_POPUP_OPEN_BUTTON),
            "'Open' button for attached task-file isn't displayed with correct text!");
    softAssert.assertAll();
  }

  @Test
  public void checkUserInformation() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isUserPhotoDisplayed(),
            "'User photo' isn't displayed!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getUserNameText(), studentName,
            "'User name' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkStudentSubmittedAnswer() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isUserCommentsMessageDisplayed(),
        "User's comment message isn't displayed!");
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isShowMoreOfUserCommentDisplayed(),
        "Show more text isn't displayed for user's comment!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getShowMoreTextOfUserComment(),
        showMoreText, "Show more text isn't displayed correctly!");
    String usersCommentMessage = getUserCommentTextUpToIndexOfSubString(showMoreText);
    softAssert.assertTrue(studentSubmittedAnswerWithMoreThan320Chars
            .contains(usersCommentMessage)
            && studentSubmittedAnswerWithMoreThan320Chars.length()
            > usersCommentMessage.length(),
        "User's comment is displayed fully!");
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.clickShowMoreTextOfUserComment()
        .isShowLessOfUserCommentDisplayed(), "Show less text button isn't displayed!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getShowLessTextOfUserComment(),
        showLessText, "Show less text isn't displayed correctly!");
    softAssert.assertEquals(getUserCommentTextUpToIndexOfSubString(showLessText),
        studentSubmittedAnswerWithMoreThan320Chars, "Student's answer isn't displayed fully!");
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.clickShowLessTextOfUserComment()
            .isShowMoreOfUserCommentDisplayed(),
        "Show more isn't displayed after show less is clicked!");
    softAssert.assertAll();
  }

  @Test
  public void checkAddFileButton() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.getAddFileIconAndLabelText().contains(addFileIcon),
            "Add file button 'icon' isn't displayed!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.getAddFileIconAndLabelText()
                .contains(getValueOf(ONLINE_POPUP_ADD_FILE_LABEL)),
            "Add file button 'label' is incorrect!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isAddFileButtonClickable(),
            "Add file button isn't clickable!");
    softAssert.assertAll();
  }

  @Test
  public void checkTaskChatDescriptionAndCommentField() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getTaskChatDescriptionText(),
            LocaleProperties.getValueOf(ONLINE_POPUP_TASK_CHAT_LABEL),
            "'Task chat description' is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getCommentFieldPlaceHolder(),
            LocaleProperties.getValueOf(ONLINE_POPUP_COMMENT_PLACEHOLDER),
            "Comment field 'placeholder' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkSubmissionMessage() {
    assertTrue(Pattern.compile(submissionTaskRegex)
        .matcher(tasksPopUpScreenOnLearningPageScreen.getMessageToUserBasedTaskStatus())
        .find(), "Submission message displayed incorrectly!");
  }

  @Test
  public void checkSubmitButton() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getSubmitButtonText(),
            LocaleProperties.getValueOf(ONLINE_POPUP_SUBMIT_BUTTON),
            "Submit button text is incorrect!");
    softAssert.assertFalse(
        tasksPopUpScreenOnLearningPageScreen.isSubmitButtonEnabled(),
        "The text has not been entered yet but the button is already clickable!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .typeComment(userComment)
            .isSubmitButtonEnabled(),
        "The text is already entered but the button is not clickable!");
    softAssert.assertAll();
  }

  private String getUserCommentTextUpToIndexOfSubString(String subString) {
    String usersCommentMessage = tasksPopUpScreenOnLearningPageScreen.getUserCommentsMessageText();
    int indexOfChildTagText = usersCommentMessage.indexOf(subString);
    return usersCommentMessage.substring(0, indexOfChildTagText - 1);
  }
}
