package com.epmrdpt.smoke.learning;

import static com.epmrdpt.bo.user.UserFactory.withDepartmentTraining;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_ALL_TASKS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_DEADLINE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_EPAM_TRAINING_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_GROUP_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_MARK_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_START_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_STATUS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_TASK_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_URGENT_TAB;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12974_VerifyTheContentOfTheLearningTaskPage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_12974_VerifyTheContentOfTheLearningTaskPage {

  private static final String RED_COLOR_DEADLINE_SECTION = "rgba(211, 93, 71, 1)";
  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;
  private SoftAssert softAssert;
  private Pattern markPattern = Pattern.compile("^\\d{1,3}/\\d{1,3}( \\(\\d{1,3}\\))?$");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(withDepartmentTraining())
        .clickLearningButton()
        .waitCalendarTabDisplayed();
  }

  @Test(priority = 1)
  public void checkLearningPageLoading() {
    assertTrue(tasksTabOnLearningPageScreen
            .clickTasksTab()
            .isScreenLoaded(),
        "Task tab isn't loaded!");
  }

  @Test(priority = 2)
  public void checkUrgentTabDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isUrgentTabDisplayed(),
        "'Urgent' tab isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getUrgentTabText(),
        getValueOf(LEARNING_TASK_TAB_URGENT_TAB),
        "'Urgent' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkAllTasksTabDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllTasksTabDisplayed(),
        "'All tasks' tab isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getAllTasksTabText(),
        getValueOf(LEARNING_TASK_TAB_ALL_TASKS_TAB),
        "'All tasks' has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkDepartmentAffiliateTabDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isDepartmentAffiliateTabDisplayed(),
        "'Department Affiliate' tab isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getDepartmentAffiliateTabText(),
        getValueOf(LocalePropertyConst.LEARNING_TASK_TAB_DEPARTMENT_AFFILIATE_TAB),
        "'Department Affiliate' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkClassColumnDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isGroupLabelDisplayed(),
        "'Class' label isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getGroupLabelText(),
        getValueOf(LocalePropertyConst.LEARNING_TASK_TAB_CLASS_LABEL),
        "'Class' label has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkEpamTrainingTabDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen
            .clickEpamTrainingTab()
            .isEpamTrainingTabDisplayed(),
        "'Epam training' tab isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getEpamTrainingTabText(),
        getValueOf(LEARNING_TASK_TAB_EPAM_TRAINING_TAB),
        "'Epam training' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkGroupLabelDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isGroupLabelDisplayed(),
        "'Group' label isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getGroupLabelText(),
        getValueOf(LEARNING_TASK_TAB_GROUP_LABEL),
        "'Group' label has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTaskLabelDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isTaskLabelDisplayed(),
        "'Task' label isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getTaskLabelText(),
        getValueOf(LEARNING_TASK_TAB_TASK_LABEL),
        "'Task' label has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkStartDateLabelDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isStartDateLabelDisplayed(),
        "'Start date' label isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getStartDateText(),
        getValueOf(LEARNING_TASK_TAB_START_DATE_LABEL),
        "'Start date' label has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkDeadlineLabelDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isDeadlineLabelDisplayed(),
        "'Deadline' isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getDeadlineLabelText(),
        getValueOf(LEARNING_TASK_TAB_DEADLINE_LABEL),
        "'Deadline' label has incorrect text!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isWarningIconDisplayed(),
        "Warning icon in deadline section isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getDeadlineColor(),
        RED_COLOR_DEADLINE_SECTION, "Deadline section color isn't red!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTaskMarks() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isMarkLabelDisplayed(),
        "'Mark' label isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getMarkLabelText(),
        getValueOf(LEARNING_TASK_TAB_MARK_LABEL),
        "'Mark' label has incorrect text!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllTaskMarkDisplayed(),
        "Not all marks are displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.getTaskMarksList().stream()
            .peek(mark -> System.err.println("MARK:" + mark))
            .allMatch(e -> markPattern.matcher(e).find()),
        "Marks have incorrect pattern!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkStatusLabelDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isStatusLabelDisplayed(),
        "'Status' label isn't displayed!");
    softAssert.assertEquals(tasksTabOnLearningPageScreen.getStatusLabelText(),
        getValueOf(LEARNING_TASK_TAB_STATUS_LABEL),
        "'Status' label has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTaskInformationDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllSortedItemDisplayed(),
        "Sort items aren't displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllTaskIconsDisplayed(),
        "Tasks icons aren't displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllGroupNamesDisplayed(),
        "Group names aren't displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllTaskNamesDisplayed(),
        "Tasks names aren't displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllStartDateDisplayed(),
        "Start dates aren't displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllDeadlineDisplayed(),
        "Deadline aren't displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isTaskStatusDisplayed(),
        "Task status isn't displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isStatusIconDisplayed(),
        "Status icon isn't displayed!");
    softAssert.assertAll();
  }
}
