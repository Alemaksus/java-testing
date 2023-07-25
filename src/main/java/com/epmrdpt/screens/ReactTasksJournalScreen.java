package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CALENDAR_FROM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CALENDAR_TO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_STATUS_ASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_STATUS_NOT_ASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_STATUS_REASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_TASK_JOURNAL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ADD_ASSIGNMENT_POPUP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ALL_PERIOD_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_OFFLINE_TASK_INFO_REVIEWER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_EDIT_STATUS_POPUP_UNASSIGN_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_ADD_EXTERNAL_TASK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_ADD_TASK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_FROM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_TO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_CHECK_TASK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_COPY_TASKS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_EXPORT_EXCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_IMPORT_EXCEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_MARK_REPORT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_MONTH_PERIOD;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_SUCCESSFUL_IMPORT_EXCEL_NOTIFICATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_TWO_WEEKS_PERIOD;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_WEEK_PERIOD;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_NOTIFICATION_TASK_ADDED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_NOTIFICATION_TASK_ASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_NOTIFICATION_TASK_ASSIGNMENT_CANCEL_EVERYONE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_NOTIFICATION_TASK_DELETED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_NOTIFICATION_TASK_UNASSIGNED;

import com.epmrdpt.bo.Student;
import com.epmrdpt.bo.TrainingOfflineTask;
import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.text.html.CSS;
import javax.swing.text.html.CSS.Attribute;
import javax.swing.text.html.HTML;

public class ReactTasksJournalScreen extends AbstractScreen {

  private static final String COLOR_FOR_PASSED_TASK_BUTTON = "rgba(111, 173, 47, 1)";
  private static final String DATE_PATTERN = "dd.MM.yy";
  private static final String DATE_OF_TASK_IN_TABLE = "(//div[contains(@class,'time-to-deadline-text')]//span[2])[%s]";

  private static final String TASK_JOURNAL_SECTION_LOCATOR = "//div[@class='section-tabs__content']";
  private static final String STUDENT_NAME_IN_TABLE_BY_INDEX_PATTERN = TASK_JOURNAL_SECTION_LOCATOR
      + "/div[2]/div[1]/div[1]/div[not(contains(@class,'header'))][not(contains(@class,'scroll'))][%d]//span[2]";
  private static final String TASK_COLUMN_HEADER_IN_TABLE_LOCATOR =
      "//div[contains(@class,'uui-table-header-row')]/div[2]/descendant::div[2]/child::div";
  private static final String TASK_TYPE_ICON_IN_TABLE_BY_INDEX_PATTERN =
      TASK_COLUMN_HEADER_IN_TABLE_LOCATOR + "[%s]//div[1]/div/*/*[@d][1]";
  private static final String TASK_TOPIC_NAME_IN_TABLE_BY_INDEX_PATTERN =
      TASK_TYPE_ICON_IN_TABLE_BY_INDEX_PATTERN + "/../..";
  private static final String STUDENT_FROM_TASKS_JOURNAL_TAB_BY_NAME_PATTERN =
      "//span[2][text()='%s']";
  private static final String TASK_BY_NAME_PATTERN = "//div[contains(text(),'%s')]";
  private static final String MESSAGE_NOTIFICATION_POP_UP_PATTERN =
      "//div[@class='uui-snackbar-item-self']//div[2]/div[text()='%s']";
  private static final String ONLINE_TASK_STATUS_PATTERN =
      "//div[contains(@class,'uui-table-row')]//div[@id]//div[@role='button'][text()='%s']";
  private static final String ALL_PERIOD_BUTTON_PATTERN = "//div[@class='section-tabs__content']//button[contains(text(),'%s')]";
  private static final String TASK_STATUS_BY_STUDENT_NAME_AND_TASK_ID_PATTERN =
      "//span[text()='%s']/ancestor::div[contains(@class,'uui-table-row-container')]/child::div//div[@id='%s']";
  private static final String TASK_STATUS_TEXT_BY_STUDENT_NAME_AND_TASK_ID_PATTERN =
      TASK_STATUS_BY_STUDENT_NAME_AND_TASK_ID_PATTERN + "//div[@tabindex='-1']";
  private static final String STUDENTS_MARK_BY_STUDENT_NAME_AND_TASK_ID_PATTERN =
      TASK_STATUS_BY_STUDENT_NAME_AND_TASK_ID_PATTERN + "/div[text()='%s']";
  private static final String SWITCHER_BETWEEN_GROUPS = "//*[@class='active']/..";
  private static final String STUDENT_MARK_FIELD_BY_NAME_AND_COLUMN_NUMBER_PATTERN =
      STUDENT_FROM_TASKS_JOURNAL_TAB_BY_NAME_PATTERN
          + "//ancestor::div[3]//following-sibling::div//descendant::div[2]/div[%s]";
  private static final String STUDENT_TASKS_JOURNAL_MARK_PATTERN = "//span[text()='%s']//following::div[@id='%s']/div";
  private static final String STUDENT_TASKS_JOURNAL_COMMENT_PATTERN = "//span[text()='%s']//following::div[@id='%s']/following-sibling::*";
  private static final String CONTAINS_TEXT_PATTERN = "[contains(text(), '%s')]";
  private static final String TASK_STATUS_BY_STUDENT_NAME_AND_ID_PATTERN =
      STUDENT_FROM_TASKS_JOURNAL_TAB_BY_NAME_PATTERN +
          "/ancestor::div[contains(@class,'uui-table-row-container')]//div[@id='%d']/*[2][text() or ancestor::div]";
  private static final String PERIOD_BUTTONS_PATTERN =
      TASK_JOURNAL_SECTION_LOCATOR + "//div[contains(text(),'%s')]/..";
  private static final String CALENDAR_PATTERN = "//input[@placeholder='%s']";
  private static final String STUDENT_BY_INDEX_PATTERN = "(//*[contains(@class,'ui-table-row-container uui-table-row')])[%s]";
  private static final String STUDENT_TASK_MARK_PATTERN = "//div[@id]/div[not(*[contains(@role, '')])]";
  private static final String STUDENT_TASK_STATUS_BY_NAME_AND_ID_PATTERN =
      "//span[text()='%s']/ancestor::div[contains(@class,'uui-table-row-container')]//div[@id='%s']//div[@tabindex='-1']";

  private Element tableHeader = Element.byXpath("//*[contains(@class,'table-header-cell')]");
  private Element homeIcon = Element.byCss("a.home-page svg");
  private Element trainingNameFromTasksJournal = Element.byXpath(
      "//*[@class='bread-crumbs__left']/div/*[2]");
  private Element groupSwitcher = Element.byXpath(SWITCHER_BETWEEN_GROUPS);
  private Element textFromBredCrumbs = Element.byXpath("//div[@class='bread-crumbs__left']");
  private Element breadCrumbsLevel = Element.byXpath(
      "//*[@class='bread-crumbs__left']/div/child::*");
  private Element taskJournalTable = Element.byXpath(TASK_JOURNAL_SECTION_LOCATOR + "/div[2]");
  private Element addTaskButton = Element.byXpath(TASK_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_ADD_TASK_BUTTON));
  private Element addExternalTaskButton = Element.byXpath(TASK_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_ADD_EXTERNAL_TASK_BUTTON));
  private Element markReportButton = Element.byXpath(TASK_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_MARK_REPORT_BUTTON));
  private Element copyTasksButton = Element.byXpath(TASK_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_COPY_TASKS_BUTTON));
  private Element exportExcelButton = Element.byXpath(TASK_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_EXPORT_EXCEL_BUTTON));
  private Element importExcelButton = Element.byXpath(TASK_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_IMPORT_EXCEL));
  private Element gearMenuButton = Element.byXpath(TASK_JOURNAL_SECTION_LOCATOR
      + "/div[1]//div[@role='button' and descendant::*[@fill-rule='evenodd']]");
  private Element firstDateForTaskInTable = Element
      .byXpath("//div[contains(@class,'time-to-deadline-text')]//span[2]");
  private Element studentsColumnHeader = Element
      .byXpath("//*[contains(@class,'table-header-row')]/div[1]");
  private Element studentRowInTable = Element
      .byXpath(TASK_JOURNAL_SECTION_LOCATOR
          + "/div[2]/div[1]/div[1]/div[not(contains(@class,'header'))][not(contains(@class,'scroll'))]");
  private Element taskHeaderColumnInTable = Element.byXpath(TASK_COLUMN_HEADER_IN_TABLE_LOCATOR);
  private Element taskStatisticsColumnHeader = Element
      .byXpath("//*[contains(@class,'table-header-row')]/div[3]");
  private Element externalTaskStatisticsColumnHeader = Element
      .byXpath(
          "//*[contains(@class,'table-header-row')]/div[last()]//div[@class='tooltip-container']/div[text()]");
  private Element addTaskNotificationPopup = Element.byXpath(MESSAGE_NOTIFICATION_POP_UP_PATTERN,
      getValueOf(REACT_TRAINING_TASK_NOTIFICATION_TASK_ADDED));
  private Element deleteTaskNotificationPopup = Element.byXpath(MESSAGE_NOTIFICATION_POP_UP_PATTERN,
      getValueOf(REACT_TRAINING_TASK_NOTIFICATION_TASK_DELETED));
  private Element assignCancelEveryoneTaskNotificationPopup = Element
      .byXpath(MESSAGE_NOTIFICATION_POP_UP_PATTERN,
          getValueOf(REACT_TRAINING_TASK_NOTIFICATION_TASK_ASSIGNMENT_CANCEL_EVERYONE));
  private Element taskHasBeenUnassignedPopup = Element.byXpath(MESSAGE_NOTIFICATION_POP_UP_PATTERN,
      getValueOf(REACT_TRAINING_TASK_NOTIFICATION_TASK_UNASSIGNED));
  private Element assignedNotificationPopup = Element.byXpath(MESSAGE_NOTIFICATION_POP_UP_PATTERN,
      getValueOf(REACT_TRAINING_TASK_NOTIFICATION_TASK_ASSIGNED));
  private Element closeNotificationButton = Element
      .byXpath(
          "//div[@class='uui-snackbar-item-self']//div[contains(@class,'uui-button-box')]/div");
  private Element notAssignedStatusButton = Element
      .byXpath(ONLINE_TASK_STATUS_PATTERN, getValueOf(ONLINE_TASK_STATUS_NOT_ASSIGNED));
  private final Element assignedStatusButton = Element
          .byXpath(ONLINE_TASK_STATUS_PATTERN, getValueOf(ONLINE_TASK_STATUS_ASSIGNED));
  private Element reassignButton = Element.byXpath("//div[@style]/div[@role='button'][2]");
  private Element saveChangesButton = Element
      .byXpath("//div[contains(@class,'uui-modal-window')]/div[3]/div[2]");
  private Element allPeriodButton = Element.byXpath(ALL_PERIOD_BUTTON_PATTERN,
      getValueOf(REACT_TRAINING_ALL_PERIOD_BUTTON));
  private Element periodFromInput = Element.byXpath("//input[@placeholder='%s:']",
      getValueOf(CALENDAR_FROM));
  private Element periodToInput = Element.byXpath("//input[@placeholder='%s:']",
      getValueOf(CALENDAR_TO));
  private Element groupNameFromSwitcher = Element.byXpath(
      SWITCHER_BETWEEN_GROUPS + "//a[contains(@class,' active')]");
  private Element unassignTaskButtonOnTaskStatusEditPopUp = Element.byXpath(
      "//div[@role='button' and text()='%s']",
      getValueOf(REACT_TRAINING_ONLINE_TASK_EDIT_STATUS_POPUP_UNASSIGN_BUTTON));
  private final Element editTaskButtonOnTaskStatusEditPopUp = Element.byXpath(
          "//div[@role='button' and text()='%s']", getValueOf(EDIT_BUTTON));
  private Element loadingSpinner = Element.byXpath("//div[@class='section-tabs__content']/div"
      + "/following-sibling::div[contains(@class,'uui-spinner-container')]");
  private Element student = Element.byXpath(
      "//*[contains(@class,'ui-table-row-container uui-table-row')]");
  private Element studentsNumber = Element.byXpath("./div[1]//span[1]");
  private Element studentsName = Element.byXpath("./div[1]//span[2]");
  private Element studentsAverageMark = Element.byXpath("./div[3]/div");
  private static final String TASK_STATISTICS_BY_STUDENT_PATTERN =
      "//*[text()='%s']//ancestor::div[4]/following-sibling::div[2]/div";
  private Element calendarFirstDayOfSelectedPeriodField = Element.byXpath(CALENDAR_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_FROM));
  private Element calendarLastDayOfSelectedPeriodField = Element.byXpath(CALENDAR_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_TO));
  private Element weekPeriodButton = Element.byXpath(PERIOD_BUTTONS_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_WEEK_PERIOD));
  private Element twoWeekPeriodButton = Element.byXpath(PERIOD_BUTTONS_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_TWO_WEEKS_PERIOD));
  private Element monthPeriodButton = Element.byXpath(PERIOD_BUTTONS_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_MONTH_PERIOD));
  private Element successfulExcelImportNotification = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_SUCCESSFUL_IMPORT_EXCEL_NOTIFICATION));
  private Element scrollBar = Element.byXpath("//div[@class='uui-table-scroll-bar']");
  private Element checkOnlineTaskButton = Element
      .byXpath(String.format("//div[@role='button' and text()='%s']",
          getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CHECK_TASK_BUTTON)));
  private Element reviewerColumn = Element
      .byXpath("//div[contains(@class,'uui-table-row-container')]//div[text()='%s']",
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_REVIEWER));
  private Element reviewersInputField = Element.byXpath
      ("//div[contains(@class, 'uui-input-box')]//span");
  private final Element reviewerAvatar = Element.byXpath
          ("//div[contains(@class, 'uui-input-box')]//img");
  private Element assignmentPopUp = Element.byXpath(TASK_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_ADD_ASSIGNMENT_POPUP));
  private Element taskJournalTab = Element.byXpath
      (TASK_BY_NAME_PATTERN, getValueOf(REACT_GROUP_JOURNAL_MENU_TASK_JOURNAL_BUTTON));
  private Element scheduleTab =Element.byXpath
      (TASK_BY_NAME_PATTERN, getValueOf(REACT_TRAINING_SCHEDULE_BUTTON));
  private final Element reassignedStatusButton = Element
      .byXpath(ONLINE_TASK_STATUS_PATTERN, getValueOf(ONLINE_TASK_STATUS_REASSIGNED));

  @Override
  public boolean isScreenLoaded() {
    return isTableHeaderDisplayed();
  }

  public boolean isTableHeaderDisplayed() {
    return tableHeader.isDisplayed();
  }

  public ReactTasksJournalScreen waitTableHeaderForVisibility() {
    tableHeader.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public ReactTasksJournalScreen waitGroupJournalTableForVisibility() {
    taskJournalTable.waitForVisibility();
    return this;
  }

  public ReactTasksJournalScreen waitAssignmentCancelEveryonePopUpVisibility() {
    assignCancelEveryoneTaskNotificationPopup.waitForVisibility();
    return this;
  }

  public ReactTasksJournalScreen waitAssignedNotificationPopupVisibility() {
    assignedNotificationPopup.waitForVisibility();
    return this;
  }

  public ReactTasksJournalScreen waitPassedStatusChangeByStudentName(String studentName,
      TrainingOnlineTask task) {
    Element.byXpath(String
            .format(TASK_STATUS_BY_STUDENT_NAME_AND_TASK_ID_PATTERN,
                studentName,
                getTaskId(task)))
        .waitUntilAttributeChangeCssValue(CSS.Attribute.COLOR.toString(),
            COLOR_FOR_PASSED_TASK_BUTTON);
    return this;
  }

  public ReactTasksJournalScreen waitMarkChangeToDefaultByStudentName(String studentName,
      TrainingOnlineTask task) {
    String defaultStudentMark = "0";
    Element.byXpath(String
            .format(STUDENTS_MARK_BY_STUDENT_NAME_AND_TASK_ID_PATTERN,
                studentName,
                getTaskId(task),
                defaultStudentMark))
        .waitForVisibility();
    return this;
  }

  public String getTextFromBredCrumbs() {
    return textFromBredCrumbs.getAttributeValue("innerText");
  }

  public String getGroupNameFromBredCrumbs() {
    String textFromBreadCrumbs = getTextFromBredCrumbs();
    return textFromBreadCrumbs.substring(textFromBreadCrumbs.lastIndexOf("\n") + 1);
  }

  public int getBreadCrumbsLevelCount() {
    return breadCrumbsLevel.getElements().size();
  }

  public boolean isGroupSwitcherDisplayed() {
    return groupSwitcher.isDisplayed();
  }

  public boolean isGroupSwitcherInvisibility() {
    return groupSwitcher.waitForInvisibility();
  }

  public boolean isHomeIconDisplayed() {
    return homeIcon.isDisplayed();
  }

  public String getAddTaskButtonText() {
    return addTaskButton.getText();
  }

  public String getAddExternalTaskButtonText() {
    return addExternalTaskButton.getText();
  }

  public String getMarkReportButtonText() {
    return markReportButton.getText();
  }

  public String getCopyTasksButtonText() {
    return copyTasksButton.getText();
  }

  public String getExportExcelButtonText() {
    return exportExcelButton.getText();
  }

  public String getImportExcelButtonText() {
    return importExcelButton.getText();
  }

  public String getWeekPeriodButtonText() {
    return weekPeriodButton.getText();
  }

  public String getTwoWeekPeriodButtonText() {
    return twoWeekPeriodButton.getText();
  }

  public String getMonthPeriodButtonText() {
    return monthPeriodButton.getText();
  }

  public boolean isGearMenuButtonDisplayed() {
    return gearMenuButton.isDisplayed();
  }

  public String getStudentsColumnHeaderText() {
    return studentsColumnHeader.getText();
  }

  public int getStudentRowsInTableCount() {
    return studentRowInTable.getElements().size();
  }

  public String getStudentNameInTableByIndex(int index) {
    return Element.byXpath(STUDENT_NAME_IN_TABLE_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isTaskHeaderColumnInTableDisplayed() {
    return taskHeaderColumnInTable.isDisplayed();
  }

  public int getTaskHeaderColumnsInTableCount() {
    return taskHeaderColumnInTable.getElements().size();
  }

  public ReactTasksJournalScreen mouseOverTaskIconInTableByIndex(int index) {
    if (!Element.byXpath(TASK_TYPE_ICON_IN_TABLE_BY_INDEX_PATTERN, index)
        .isDisplayed(SHORT_TIME_OUT_IN_SECONDS)) {
      moveScrollBarWhileElementIsNotVisibility(Element
          .byXpath(TASK_TYPE_ICON_IN_TABLE_BY_INDEX_PATTERN, index));
    }
    Element.byXpath(TASK_TYPE_ICON_IN_TABLE_BY_INDEX_PATTERN, index).mouseOver();
    return this;
  }

  public boolean isTaskIconInTableDisplayedByIndex(int index) {
    return Element.byXpath(TASK_TYPE_ICON_IN_TABLE_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public ReactTasksJournalScreen mouseOverTaskNameInTableByIndex(int index) {
    Element.byXpath(TASK_TOPIC_NAME_IN_TABLE_BY_INDEX_PATTERN, index).mouseOver();
    return this;
  }

  public String getFirstDateForTaskInTable() {
    return firstDateForTaskInTable.getText();
  }


  public String getTaskNameInTableByIndex(int index) {
    return Element.byXpath(TASK_TOPIC_NAME_IN_TABLE_BY_INDEX_PATTERN, index).getText();
  }

  public String getTaskStatisticsColumnHeaderText() {
    return taskStatisticsColumnHeader.getText();
  }

  public String getExternalTaskStatisticsColumnHeaderText() {
    return externalTaskStatisticsColumnHeader.getText();
  }

  public String getStudentAverageMarkText(String studentName) {
    return Element.byXpath(TASK_STATISTICS_BY_STUDENT_PATTERN, studentName)
        .getText();
  }

  public ReactStudentCardPopUpScreen clickStudentByName(String studentName) {
    Element.byXpath(STUDENT_FROM_TASKS_JOURNAL_TAB_BY_NAME_PATTERN, studentName).click();
    return new ReactStudentCardPopUpScreen();
  }

  public ReactOfflineTaskInfoPopUpScreen clickOfflineTaskInTaskJournalByName(String taskName) {
    Element.byXpath(TASK_BY_NAME_PATTERN, taskName).clickJs();
    return new ReactOfflineTaskInfoPopUpScreen();
  }

  public ReactAddOfflineTaskPopUpScreen clickAddTaskButton() {
    addTaskButton.click();
    return new ReactAddOfflineTaskPopUpScreen();
  }

  public ReactTasksJournalScreen clickCloseNotificationButton() {
    closeNotificationButton.waitForClickableAndClick();
    return this;
  }

  public ReactTasksJournalScreen clickTrainingNameFromTasksJournal() {
    trainingNameFromTasksJournal.click();
    return this;
  }

  public ReactTasksJournalScreen clickGroupNameFromSwitcher() {
    groupNameFromSwitcher.click();
    return this;
  }

  public ReactTaskMarkPopUpScreen clickCellByStudentNameAndColumnNumber(String studentName,
      int columnNumber) {
    Element.byXpath(STUDENT_MARK_FIELD_BY_NAME_AND_COLUMN_NUMBER_PATTERN, studentName, columnNumber)
        .click();
    return new ReactTaskMarkPopUpScreen();
  }

  public boolean isExpectedFractionalMarkToBePresent(TrainingOfflineTask trainingOfflineTask) {
    Element mark = Element.byXpath(STUDENT_TASKS_JOURNAL_MARK_PATTERN + CONTAINS_TEXT_PATTERN,
        trainingOfflineTask.getStudent(), trainingOfflineTask.getTaskId(),
        trainingOfflineTask.getFractionalMark());
    mark.mouseOver();
    return mark.isDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public ReactAddAssignmentPopUpScreen clickReassignPopUpButton() {
    reassignButton.click();
    return new ReactAddAssignmentPopUpScreen();
  }

  public ReactTasksJournalScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public ReactOnlineTaskInfoPopUpScreen clickOnlineTaskInTaskJournalByName(String taskName) {
    moveScrollBarWhileElementIsNotVisibility(Element.byXpath(TASK_BY_NAME_PATTERN, taskName));
    Element.byXpath(TASK_BY_NAME_PATTERN, taskName).mouseOverAndClick();
    return new ReactOnlineTaskInfoPopUpScreen();
  }

  public ReactTasksJournalScreen moveScrollBarWhileElementIsNotVisibility(Element element) {
    int x = -450;
    while (!element.isDisplayed(SHORT_TIME_OUT_IN_SECONDS) && x <= 450) {
      scrollBar.mouseOverCoordinatesAndClick(x, 0);
      x += 50;
    }
    return new ReactTasksJournalScreen();
  }

  public boolean isTaskDisplayedByName(String taskName) {
    return Element.byXpath(TASK_BY_NAME_PATTERN, taskName).isDisplayed();
  }

  public ReactTasksJournalScreen waitTaskForInvisibility(String taskName) {
    Element.byXpath(TASK_BY_NAME_PATTERN, taskName).waitForInvisibility();
    return this;
  }

  public ReactTasksJournalScreen waitTaskStatisticsForReCalculated(
      String taskStatistics, String studentName) {
    Element.byXpath(TASK_STATISTICS_BY_STUDENT_PATTERN, studentName)
        .waitTextNotToBePresentInElement(taskStatistics);
    return new ReactTasksJournalScreen();
  }

  public boolean isAddTaskNotificationPopUpDisplayed() {
    return addTaskNotificationPopup.isDisplayed();
  }

  public boolean isDeleteTaskNotificationPopupDisplayed() {
    return deleteTaskNotificationPopup.isDisplayed();
  }

  public boolean isAssignedNotificationPopupDisplayed() {
    return assignedNotificationPopup.isDisplayed();
  }

  public ReactTasksJournalScreen clickUnassignButtonOnTaskStatusEditPopUp() {
    unassignTaskButtonOnTaskStatusEditPopUp.click();
    return this;
  }

  public ReactTasksJournalScreen clickEditButtonOnTaskStatusEditPopUp() {
    editTaskButtonOnTaskStatusEditPopUp.click();
    return this;
  }

  public boolean isNotAssignedStatusButtonDisplayed() {
    return notAssignedStatusButton.isDisplayed();
  }

  public ReactTasksJournalScreen clickAllPeriodButton() {
    allPeriodButton.click();
    return this;
  }

  public ReactTasksJournalScreen typePeriodFrom(String periodFrom) {
    periodFromInput.clearInputUsingBackspace();
    periodFromInput.type(periodFrom);
    return this;
  }

  public ReactTasksJournalScreen typePeriodToAndSubmit(String periodTo) {
    periodToInput.clearInputUsingBackspace();
    periodToInput.type(periodTo);
    return this;
  }

  public ReactTasksJournalScreen clickGearMenuButton() {
    gearMenuButton.click();
    return this;
  }

  public String getTaskStatusTextByStudentNameAndTaskId(String studentName,
      TrainingOnlineTask task) {
    Element taskStatus = Element.byXpath(TASK_STATUS_TEXT_BY_STUDENT_NAME_AND_TASK_ID_PATTERN,
        studentName, getTaskId(task));
    taskStatus.mouseOver();
    return taskStatus.getText();
  }

  public String getTaskStatusByOnlineTask(TrainingOnlineTask task) {
    Element taskStatus = Element.byXpath(TASK_STATUS_BY_STUDENT_NAME_AND_ID_PATTERN,
        task.getStudent(),
        getTaskId(task));
    taskStatus.mouseOver();
    return taskStatus.getText();
  }

  public ReactTasksJournalScreen clickTaskStatusByStudentName(String studentName,
      TrainingOnlineTask task) {
    Element.byXpath(TASK_STATUS_BY_STUDENT_NAME_AND_TASK_ID_PATTERN,
            studentName,
            getTaskId(task))
        .mouseOverAndClick();
    return this;
  }

  public ReactTasksJournalScreen clickTaskStatus(TrainingOnlineTask trainingOnlineTask) {
    Element.byXpath(TASK_STATUS_BY_STUDENT_NAME_AND_ID_PATTERN, trainingOnlineTask.getStudent(),
            getTaskId(trainingOnlineTask))
        .mouseOverAndClick();
    return this;
  }

  public int getTaskId(TrainingOfflineTask task) {
    return Integer.parseInt(Element.byXpath(TASK_BY_NAME_PATTERN, task.getTaskName())
        .getAttributeValue("data-id"));
  }

  public int getTaskIdByTaskName(String taskName) {
    return Integer.parseInt(Element.byXpath(TASK_BY_NAME_PATTERN, taskName)
        .getAttributeValue("data-id"));
  }

  public ReactTasksJournalScreen waitForLoadingSpinnerInvisibility() {
    loadingSpinner.waitForDisappear();
    return this;
  }

  private List<Element> getStudentElements() {
    return student.getElements();
  }

  public ArrayList<Student> getStudentsDataList() {
    ArrayList<Student> studentsList = new ArrayList<>();
    List<Element> studentsElements = getStudentElements();
    for (Element studentElement : studentsElements) {
      studentsList.add(new Student()
          .withNumber(getStudentsNumber(studentElement))
          .withName(getStudentsName(studentElement))
          .withAverageMark(getStudentsAverageMark(studentElement))
          .withTaskMarksList(getStudentListTaskMarks(studentsElements.indexOf(studentElement)))
          .withAverageMarkDependsOnTaskWeight(
              getStudentsAverageMarkDependsOnTaskWeight(studentElement)));
    }
    return studentsList;
  }

  public String getStudentMarkByStudentNameAndTrainingTaskName(String studentName,
      String trainingTaskName) {
    return Element.byXpath(STUDENT_TASKS_JOURNAL_MARK_PATTERN, studentName,
        getTaskIdByTaskName(trainingTaskName)).getText();
  }

  private double getStudentsAverageMarkDependsOnTaskWeight(Element studentElement) {
    return Double.parseDouble(
        studentElement
            .findChild(studentsAverageMark)
            .getText()
            .replaceAll(".*/", ""));
  }

  private int getStudentsNumber(Element studentElement) {
    return Integer.parseInt(studentElement.findChild(studentsNumber).getText());
  }

  private String getStudentsName(Element studentElement) {
    return studentElement.findChild(studentsName).getText();
  }

  private double getStudentsAverageMark(Element studentElement) {
    return Double.parseDouble(
        studentElement.findChild(studentsAverageMark).getText().replaceAll("\\/.*", ""));
  }

  private List<Double> getStudentListTaskMarks(int studentElementIndex) {
    List<Double> studentsTaskMarks = new ArrayList<>();
    List<Element> studentsTaskValues = Element.byXpath(String.format(STUDENT_BY_INDEX_PATTERN +
        STUDENT_TASK_MARK_PATTERN, studentElementIndex + 1)).getElements();
    for (Element studentTaskValue : studentsTaskValues) {
      if (!studentTaskValue.isDisplayed(SHORT_TIME_OUT_IN_SECONDS)) {
        moveScrollBarWhileElementIsNotVisibility(studentTaskValue);
      }
      if (!studentTaskValue.getText().equals("-")) {
        studentTaskValue.mouseOver();
        studentsTaskMarks.add(Double.parseDouble(studentTaskValue.getText()));
      }
    }
    return studentsTaskMarks;
  }

  public ReactTasksJournalScreen waitTaskHasBeenUnassignedPopUpVisibility() {
    taskHasBeenUnassignedPopup.waitForVisibility();
    return this;
  }

  public void clickMarkFieldInJournalTask(TrainingOfflineTask trainingOfflineTask) {
    Element markField = Element.byXpath(STUDENT_TASKS_JOURNAL_MARK_PATTERN,
        trainingOfflineTask.getStudent(),
        trainingOfflineTask.getTaskId());
    moveScrollBarWhileElementIsNotVisibility(markField);
    markField.click();
  }

  public ReactOfflineTaskCommentPopUpScreen clickCommentFieldInJournalTask(
      TrainingOfflineTask trainingOfflineTask) {
    Element.byXpath(STUDENT_TASKS_JOURNAL_COMMENT_PATTERN, trainingOfflineTask.getStudent(),
        trainingOfflineTask.getTaskId()).click();
    return new ReactOfflineTaskCommentPopUpScreen();
  }

  public String getStudentMarkByStudentNameAndTrainingTaskId(
      TrainingOfflineTask trainingOfflineTask) {
    return Element.byXpath(STUDENT_TASKS_JOURNAL_MARK_PATTERN, trainingOfflineTask.getStudent(),
        trainingOfflineTask.getTaskId()).getText();
  }

  public Map<String,Integer> getTasksMarkWeight(List<String> taskName){
    Map<String, Integer> tasksMarkWeight = new HashMap<>();
    taskName.forEach(task->{
      Element.byXpath(TASK_BY_NAME_PATTERN, task).click();
      if(isTaskOnline()){
        tasksMarkWeight.put(task, new ReactOnlineTaskInfoPopUpScreen()
            .getSpecificWeightValue());
      }
      if(!isTaskOnline()) {
        tasksMarkWeight.put(task,new ReactOfflineTaskInfoPopUpScreen()
            .getSpecificWeightValue());
      }
    });
    return tasksMarkWeight;
  }

  public boolean isTaskOnline() {
    return Element.byCss("div.task-event-reviewers-photo")
        .isDisplayedShortTimeOut();
  }

  public ReactTasksJournalScreen clickWeekPeriodButton() {
    weekPeriodButton.waitForClickableAndClick();
    return this;
  }

  public String getWeekPeriodButtonColour() {
    return weekPeriodButton.getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public ReactTasksJournalScreen clickTwoWeekPeriodButton() {
    twoWeekPeriodButton.click();
    return this;
  }

  public ReactTasksJournalScreen clickMonthPeriodButton() {
    monthPeriodButton.click();
    return this;
  }

  public LocalDate getFirstDayOfSelectedPeriodFromCalendarField() {
    return StringUtils.getLocaleDateFromString(
        calendarFirstDayOfSelectedPeriodField.getAttributeValue(HTML.Attribute.VALUE.toString()),
        DATE_PATTERN);
  }

  public LocalDate getLastDayOfSelectedPeriodFromCalendarField() {
    return StringUtils.getLocaleDateFromString(
        calendarLastDayOfSelectedPeriodField.getAttributeValue(HTML.Attribute.VALUE.toString()),
        DATE_PATTERN);
  }

  public ReactImportDataPopUpScreen clickImportExcelButton() {
    importExcelButton.mouseOverAndClick();
    return new ReactImportDataPopUpScreen();
  }

  public boolean isSucesfullExcelImportNotificationDisplayed() {
    return successfulExcelImportNotification.isDisplayed();
  }

  public ReactHomeTaskPopUpScreen clickCheckOnlineTaskButton() {
    checkOnlineTaskButton.click();
    return new ReactHomeTaskPopUpScreen();
  }

  public ReactHomeTaskPopUpScreen clickTaskByStatus(String status) {
    Element.byXpath(String.format(ONLINE_TASK_STATUS_PATTERN, status))
        .mouseOverAndClick();
    return clickCheckOnlineTaskButton();
  }

  public boolean isReviewerColumnDisplayed() {
    return reviewerColumn.isDisplayed();
  }

  public String getDateByTaskInTable(int index) {
    return Element.byXpath(DATE_OF_TASK_IN_TABLE, index).getText();
  }

  public boolean isTaskStatusByStudentNameAndIdEnabled(TrainingOnlineTask trainingOnlineTask) {
    return Element.byXpath(
            String.format(
                STUDENT_TASK_STATUS_BY_NAME_AND_ID_PATTERN,
                trainingOnlineTask.getStudent(),
                getTaskId(trainingOnlineTask)))
        .isEnabled();
  }

  public String getColorOfTaskStatusByStudentNameAndId(TrainingOnlineTask trainingOnlineTask) {
    return Element.byXpath(
            String.format(
                STUDENT_TASK_STATUS_BY_NAME_AND_ID_PATTERN,
                trainingOnlineTask.getStudent(),
                getTaskId(trainingOnlineTask)))
        .getCssValue(Attribute.COLOR.toString());
  }

  public ReactScheduleTabScreen switchToScheduleTab() {
    scheduleTab.click();
    return new ReactScheduleTabScreen();
  }

  public ReactTasksJournalScreen switchToTaskJournalTab(){
    taskJournalTab.click();
    return new ReactTasksJournalScreen();
  }

  public ReactTasksJournalScreen clickNotAssignedStatusButton() {
    notAssignedStatusButton.click();
    return this;
  }

  public ReactTasksJournalScreen clickAssignedStatusButton() {
    assignedStatusButton.click();
    return this;
  }

  public ReactTasksJournalScreen clickReassignedStatusButton() {
    reassignedStatusButton.click();
    return this;
  }

  public boolean isAddAssignmentPopUpDisplayed() {
    return assignmentPopUp.isDisplayed();
  }

  public boolean checkReviewersFieldText() {
    return reviewersInputField.getText().equals("");
  }

  public String getReviewerName() {
    return reviewersInputField.getText();
  }

  public boolean isReviewerAvatarDisplayed() {
    return reviewerAvatar.isDisplayed();
  }
}
