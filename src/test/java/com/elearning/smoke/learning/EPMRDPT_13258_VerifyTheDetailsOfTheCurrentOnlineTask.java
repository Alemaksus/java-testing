package com.epmrdpt.smoke.learning;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_ASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_CHAT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_TASK_DESCRIPTION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_DEADLINE_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_HEADER_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_START_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_MARK_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_REVIEWER_ROLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_TRAINER_ROLE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13258_VerifyTheDetailsOfTheCurrentOnlineTask",
    groups = {"full", "student", "smoke", "critical_path"})
public class EPMRDPT_13258_VerifyTheDetailsOfTheCurrentOnlineTask {

  private final String TASK_NAME = "AUTO_TEST_TASK_FOR_SUBMIT";
  private final String GROUP_NAME = "AutoTest_GroupForLearningPage";
  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  private SoftAssert softAssert;
  private String ONLINE_TASK_ICON_PATH = "/Content/themes/Redesign/ico/tasks-online-white.png";
  private final String markRegex = getValueOf(TASKS_TAB_MARK_TEXT)
      + ": \\d{1,2} / \\d{1,2} \\(\\d{1,2}\\)";
  private final String attemptsRegex = getValueOf(ONLINE_POPUP_ATTEMPTS_LABEL)
      + " \\d+ " + getValueOf(ONLINE_POPUP_ATTEMPTS_TEXT_PATTERN);
  private final Pattern datePattern = Pattern.compile(
      "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2}), (([0,1][0-9])|(2[0-3])):[0-5][0-9]");
  private String comment = "The online task is ready.";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asStudentForLearningPage());
    new HeaderScreen().clickLearningButton();
    new LearningService().navigateToTaskOfEpamTrainingInUrgentTab(GROUP_NAME, TASK_NAME);
  }

  @Test(priority = 1)
  public void checkOnlineTaskPopUpHeader() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Title of 'Online task' pop-up isn't displayed!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getPopUpTitleText(),
        LocaleProperties.getValueOf(ONLINE_TASK_POPUP_HEADER_TEXT),
        "Title of 'Online task' pop-up has incorrect text!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isOnlineTaskIconDisplayed(),
        "Icon of 'Online task' pop-up isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .getOnlineTaskIconBackground()
            .contains(ONLINE_TASK_ICON_PATH),
        "Icon of 'Online task' pop-up has incorrect image!");
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isCloseButtonDisplayed(),
        "Close button of 'Online task' isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkOnlineTaskNameDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTaskNameDisplayed(),
        "Task's name of 'Online task' pop-up isn't displayed!");
    softAssert.assertEquals(TASK_NAME, tasksPopUpScreenOnLearningPageScreen.getTaskNameText(),
        "Online task name is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkOnlineTaskGroupNameDisplayed() {
    assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isGroupNameDisplayed(),
        "Group's name isn't displayed!");
  }

  @Test(priority = 4)
  public void checkOnlineTaskMarkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isMarkDisplayed(), "Mark isn't displayed!");
    softAssert.assertTrue(
        Pattern.compile(markRegex).matcher(tasksPopUpScreenOnLearningPageScreen.getMarkText())
            .find(),
        "Mark is displayed incorrectly!");
    softAssert.assertTrue(Pattern.compile(attemptsRegex)
            .matcher(tasksPopUpScreenOnLearningPageScreen.getAttemptsText()).find(),
        "Attempts are displayed incorrectly!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkOnlineTaskTrainerInformation() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerNameDisplayed(),
        "Trainer's name isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerRoleDisplayed(),
        "Trainer's role isn't displayed!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getTrainerRoleText(),
        LocaleProperties.getValueOf(TASKS_TAB_TRAINER_ROLE),
        "Trainer's role has incorrect text!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerPhotoDisplayed(),
        "Trainer's photo isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkOnlineTaskStartDateDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getStartDateLabelText(),
        LocaleProperties.getValueOf(ONLINE_TASK_POPUP_START_DATE),
        "Start date text isn't correct!");
    softAssert.assertTrue(
        datePattern.matcher(tasksPopUpScreenOnLearningPageScreen.getStartDateText()).find(),
        "Start date pattern isn't correct!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkOnlineTaskDeadLineDateDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getDeadlineDateLabelText(),
        LocaleProperties.getValueOf(ONLINE_TASK_POPUP_DEADLINE_DATE),
        "Deadline text isn't correct!");
    softAssert.assertTrue(
        datePattern.matcher(tasksPopUpScreenOnLearningPageScreen.getDeadlineDateText()).find(),
        "Deadline date pattern isn't correct!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkUserInformation() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isUserNameDisplayed(), "User's name isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isUserPhotoDisplayed(),
        "User's photo isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkReviewerInformation() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isReviewerNameDisplayed(),
        "Reviewer's Name isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isReviewerRoleDisplayed(),
        "Reviewer's role isn't displayed!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getReviewerRoleText(),
        LocaleProperties.getValueOf(TASKS_TAB_REVIEWER_ROLE),
        "Reviewer's role has incorrect text!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isReviewerPhotoDisplayed(),
        "Reviewer's Photo isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkStatusDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isAssignedStatusIconDisplayed(),
        "Status icon isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isStatusLabelDisplayed(),
        "Status label isn't displayed!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getStatusLabelText(),
        LocaleProperties.getValueOf(ONLINE_POPUP_STATUS_ASSIGNED),
        "Status text is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkSubmitButton() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isSubmitButtonDisplayed(),
        "Submit button isn't displayed!");
    softAssert.assertFalse(
        tasksPopUpScreenOnLearningPageScreen.isSubmitButtonEnabled(),
        "The text has not been entered yet but the button is already clickable!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .typeComment(comment)
            .isSubmitButtonEnabled(),
        "The text is already entered but the button is not clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkCommentTextField() {
    assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isCommentFieldDisplayed(),
        "Comment text field isn't displayed!");
  }

  @Test(priority = 4)
  public void checkAddFileButton() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isAddFileButtonDisplayed(),
        "Add file button isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isAddFileButtonClickable(),
        "Add file button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTaskChat() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTaskChatDescriptionDisplayed(),
        "Description in task chat isn't displayed!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getTaskChatDescriptionText(),
        LocaleProperties.getValueOf(ONLINE_POPUP_TASK_CHAT_LABEL),
        "Description in task chat is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTaskDescriptionSection() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isCompleteTaskDescriptionDisplayed(),
        "Task description isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .getCompleteTaskDescriptionText()
            .contains(LocaleProperties.getValueOf(ONLINE_POPUP_TASK_DESCRIPTION_LABEL)),
        "Task description label is incorrect!");
    softAssert.assertAll();
  }
}
