package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ADD_FILE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_COMMENT_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_OPEN_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_REASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_SUBMIT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_CHAT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_DESCRIPTION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_DEADLINE_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_HEADER_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_START_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_MARK_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_REVIEWER_ROLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_TRAINER_ROLE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56210_VerifiedTheFeaturesOfDisplayingTheReassignOnlineTask",
    groups = {"full", "regression", "student"})
public class EPMRDPT_56210_VerifiedTheFeaturesOfDisplayingTheReassignOnlineTask {

  private final String taskStatus = getValueOf(ONLINE_POPUP_STATUS_REASSIGNED);
  private final String taskName = "AutoTest_ReassignedOnlineTask";
  private final String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";
  private final String taskDescription = "Reassigned Online Task for AutoTest";
  private final String attachedTaskFileName = "ReassignedOnlineTask.docx";
  private final String trainerName = "ManagerTest ManagerTest";
  private final String reviewerName = "AutoTrainer AutoTrainer";
  private final String studentName = "Student AutoTest";
  private final String addFileIcon = "+";
  private final String globeIconPath = "/Content/themes/Redesign/ico/tasks-online-white.png";
  private final String userComment = "The task is ready to submit!";
  private final Pattern datePattern = Pattern.compile(
      "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2}), (([0,1][0-9])|(2[0-3])):[0-5][0-9]");
  private final String markRegex = getValueOf(TASKS_TAB_MARK_TEXT)
      + ": \\d{1,2} / \\d{1,2} \\(\\d{1,2}\\)";
  private final String attemptsRegex = getValueOf(ONLINE_POPUP_ATTEMPTS_LABEL)
      + " \\d+ " + getValueOf(ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN);

  protected TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  protected SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService().navigateToTaskOfEpamTrainingInUrgentTab(groupName, taskName);
  }

  @Test(priority = 1)
  public void checkPopUpTitle() {
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
            getValueOf(ONLINE_TASK_POPUP_HEADER_TEXT),
            "Task pop-up title is incorrect!");
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isCloseButtonDisplayed(),
            "Cross mark for close isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkPopUpHeader() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getStatusLabelText(), taskStatus,
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

  @Test(priority = 3)
  public void checkStartDateAndDeadlineDate() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getStartDateLabelText(),
            getValueOf(ONLINE_TASK_POPUP_START_DATE),
            "'Start date' label text is incorrect!");
    softAssert
        .assertTrue(
            datePattern.matcher(tasksPopUpScreenOnLearningPageScreen.getStartDateText()).find(),
            "'Start date' is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getDeadlineDateLabelText(),
            getValueOf(ONLINE_TASK_POPUP_DEADLINE_DATE),
            "'Deadline date' label text is incorrect!");
    softAssert
        .assertTrue(
            datePattern.matcher(tasksPopUpScreenOnLearningPageScreen.getStartDateText()).find(),
            "'Deadline date' is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkMarks() {
    assertTrue(
        Pattern.compile(markRegex).matcher(tasksPopUpScreenOnLearningPageScreen.getMarkText())
            .find(),
        "Mark text isn't correct!");
  }

  @Test(priority = 3)
  public void checkAttempts() {
    assertTrue(
        Pattern.compile(attemptsRegex)
            .matcher(tasksPopUpScreenOnLearningPageScreen.getAttemptsText())
            .find(),
        "Attempts text is incorrect!");
  }

  @Test(priority = 4)
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

  @Test(priority = 4)
  public void checkReviewerInformation() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getReviewerNameText(), reviewerName,
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

  @Test(priority = 5)
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

  @Test(priority = 5)
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

  @Test(priority = 6)
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

  @Test(priority = 6)
  public void checkTaskChatDescriptionAndCommentField() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getTaskChatDescriptionText(),
            getValueOf(ONLINE_POPUP_TASK_CHAT_LABEL),
            "'Task chat description' is incorrect!");
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getCommentFieldPlaceHolder(),
            getValueOf(ONLINE_POPUP_COMMENT_PLACEHOLDER),
            "Comment field 'placeholder' is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
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

  @Test(priority = 7)
  public void checkSubmitButton() {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            tasksPopUpScreenOnLearningPageScreen.getSubmitButtonText(),
            getValueOf(ONLINE_POPUP_SUBMIT_BUTTON),
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
}
