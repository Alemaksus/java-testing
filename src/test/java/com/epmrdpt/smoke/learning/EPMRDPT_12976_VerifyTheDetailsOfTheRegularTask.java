package com.epmrdpt.smoke.learning;

import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_POPUP_START_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGULAR_POPUP_MARK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_TRAINER_ROLE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12976_VerifyTheDetailsOfTheRegularTask",
    groups = {"full", "student", "smoke", "critical_path"})
public class EPMRDPT_12976_VerifyTheDetailsOfTheRegularTask {

  private final String REGULAR_TASK_ICON_PATH =
      "/Content/themes/Redesign/ico/tasks-regular-white.png";
  private String markRegex = LocaleProperties.getValueOf(REGULAR_POPUP_MARK)
      + ": (0|[1-9]\\d*)([.,]\\d+)? / (0|[1-9]\\d*)([.,]\\d+)?";
  private final Pattern datePattern = Pattern.compile(
      "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2})");
  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;
  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  private SoftAssert softAssert;

  private final String TASK_NAME = "AUTO_TEST_REGULAR_TASK";
  private final String GROUP_NAME = "AutoTest_GroupForLearningPage";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asStudentForLearningPage())
        .waitProfileMenuDisplayed()
        .clickLearningButton();
    new LearningService().navigateToTaskOfEpamTrainingInUrgentTab(GROUP_NAME, TASK_NAME);
  }

  @Test(priority = 1)
  public void checkRegularTaskPopUpTitle() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Title of 'Regular task' pop-up isn't displayed!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getPopUpTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.REGULAR_POPUP_HEADER_TEXT),
        "Title of 'Regular task' pop-up has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkRegularTaskPopHeader() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isHeaderIconDisplayed(),
        "Icon of 'Regular task' pop-up isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .getHeaderIconBackground()
            .contains(REGULAR_TASK_ICON_PATH),
        "Icon of 'Regular task' pop-up has incorrect image!");
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isCloseButtonDisplayed(),
        "Close button of 'Regular task' isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkRegularTaskNameDisplayed() {
    assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTaskNameDisplayed(),
        "Task name of 'Regular task' pop-up isn't displayed!");
  }

  @Test(priority = 3)
  public void checkRegularTaskGroupNameDisplayed() {
    assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isGroupNameDisplayed(),
        "Group name of 'Regular task' pop-up isn't displayed!");
  }

  @Test(priority = 3)
  public void checkRegularTaskStartDateDisplayedAndCorrect() {
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

  @Test(priority = 3)
  public void checkRegularTaskMarkDisplayedAndCorrect() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isMarkDisplayed(),
        "Mark of 'Regular task' pop-up isn't displayed!");
    softAssert.assertTrue(
        Pattern.compile(markRegex).matcher(tasksPopUpScreenOnLearningPageScreen.getMarkText())
            .find(),
        "Mark is displayed incorrectly!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkRegularTaskTrainerNameDisplayed() {
    Assert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerNameDisplayed(),
        "Trainer's name of 'Regular task' pop-up isn't displayed!");
  }

  @Test(priority = 3)
  public void checkRegularTaskTrainerRoleDisplayedAndCorrect() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerRoleDisplayed(),
        "Trainer's role of 'Regular task' pop-up isn't displayed!");
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getTrainerRoleText(),
        LocaleProperties.getValueOf(TASKS_TAB_TRAINER_ROLE),
        "Trainer's role has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkRegularTaskTrainerPhotoDisplayed() {
    Assert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerPhotoDisplayed(),
        "Trainer's photo of 'Regular task' pop-up isn't displayed!");
  }

  @Test(priority = 3)
  public void checkTaskDescriptionIsUnclickable() {
    String urlBeforeClick = tasksPopUpScreenOnLearningPageScreen.getCurrentWindowUrl();
    tasksPopUpScreenOnLearningPageScreen.clickOnTaskDescriptionArea();
    String urlAfterClick = tasksPopUpScreenOnLearningPageScreen.getCurrentWindowUrl();
    Assert.assertEquals(urlBeforeClick, urlAfterClick,
        "Task description area is clickable!");
  }
}
