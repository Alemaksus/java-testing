package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_SET_DATE_IN_DEADLINE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_APPLY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_RESET_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_DOWNLOAD_SCHEDULE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK;
import static javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.services.ReactTrainingService;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.HTML;

public class ReactTrainingScreen extends AbstractScreen {

  private static final int MAX_ITERATION_COUNT = 10;

  private static final String ACTIVE_BUTTON_COLOR = "rgba(48, 182, 221, 1)";
  private static final String ACTIVE_BUTTON_COLORS = "rgba(103, 163, 0, 1)";
  private static final String CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN = "//div[contains(@class,'event-content__col')][1]/div[contains(text(),'%s')]";
  private static final String CLASS_TIME_BY_NAME_PATTERN =
      CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN + "/parent::div/div[2]";
  private static final String CLASS_TYPE_BY_NAME_PATTERN =
      CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN + "/parent::div/div[3]";
  private static final String CLASS_TYPE_ICON_BY_NAME_PATTERN =
      CLASS_TYPE_BY_NAME_PATTERN + "//*[@fill-rule='evenodd']";
  private static final String CLASS_LOCATION_BY_NAME_PATTERN =
      CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN + "/parent::div/div[4]";
  private static final String CLASS_GO_TO_JOURNAL_BUTTON_BY_NAME_PATTERN =
      CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN + "/parent::*/following-sibling::*";
  private static final String TRAINING_NAME_IN_MY_GROUPS_TAB_PATTERN = "//div[contains(@class,'uui-table-row')]//button[text()='%s']";
  private static final String TASKS_SECTION_LOCATOR = "//div[@class='react-calendar']/../../div";
  private static final String TASK_TICKET_AT_TASKS_SECTION =
      TASKS_SECTION_LOCATOR + "/div[not(contains(text(),.))]";
  private static final String TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION + "[%s]";
  private static final String TASK_TICKET_AT_TASKS_SECTION_BY_NAME_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION + "//div[text()='%s']";
  private static final String CLOCK_ICON_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN + "/div[1]//*[@fill-rule='evenodd']";
  private static final String DEADLINE_TITLE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN + "//span[contains(@class, 'deadline-title')]";
  private static final String DEADLINE_DATE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN + "//span[@class='deadline-time']";
  private static final String GROUP_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN + "/div[1]/div[1]/div[2]/span";
  private static final String TASK_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN + "/div[2]";
  private static final String SUBMITTED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN + "//span[contains(@class,'status-passed')]";
  private static final String CHECKED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN
          + "//span[contains(@class,'status-passed')]//following-sibling::span";
  private static final String CHECK_BUTTON_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_AT_TASKS_SECTION_BY_INDEX_PATTERN + "//div[contains(@class,'uui-caption')]";
  private static final String DAY_OF_WEEK_IN_CALENDAR_PATTERN =
      "//div[@class='react-calendar__month-view__weekdays__weekday']/abbr[text()='%s']";
  private static final String SCHEDULE_VIEW_MODE_PATTERN = "//div[@class='uui-input-label'][text()='%s']";
  private static final String DAY_OF_WEEK_IN_WEEK_SCHEDULE_PATTERN = "//span[@class='day-of-week-label'][text()='%s']";
  private static final String CHECK_BUTTON_IN_TASKS_SECTION_BY_TASK_TICKET_NAME =
      "//div[text()='%s']/../../..//div[contains(@class,'uui-caption')]";
  private static final String LINE_TABLE_IN_MY_GROUPS_TAB_LOCATOR =
      "//div[contains(@class,'uui-table-row-container')]";
  private static final String FOCUS_CLASS_VALUE = "uui-focus";
  private static final String SCHEDULE_DATE_PICKER_LOCATOR = "//div[@id='schedule-calendar-date-picker']";
  private static final String DEADLINE_DATE_DELIMITER = ",";
  private static final String CLASS_CLOCK_ICON_BY_NAME_PATTERN =
      CLASS_TIME_BY_NAME_PATTERN + "//*[@fill-rule='evenodd']";
  private static final String YEAR_IN_CALENDAR_PATTERN =
      "//div[@class='react-calendar__decade-view__years']/button[text()='%s']";
  private static final String MONTH_IN_CALENDAR_PATTERN =
      "//div[@class='react-calendar__year-view__months']/button/*[starts-with(text(),'%s')]";
  private static final String DAY_IN_CALENDAR_PATTERN =
      "//button[contains(@class,'react-calendar__month-view__days__day')]/*[text()='%s']";
  private static final String DATE_INDICATING_DOT_BY_DAY_PATTERN =
      "//button[contains(@class,'react-calendar__month-view__days__day')]/*[text()='%s']/../div";
  private static final String CLASS_IN_MONTH_SCHEDULE_TAB_BY_NAME_PATTERN =
      "//div[text()='%s']/ancestor::div[@class='rbc-event-content']";
  private static final String ELEMENT_OF_PERIOD_FROM_DDL_PATTERN = "//div[@class='uui-input-label' and text()='%s']";
  private static final String GROUP_NAME_BUTTON_BY_NAME_LOCATOR = "//a[contains(@href,'group-journal') and not (contains(@href,'trainingId')) and text()='%s']";

  private Element scheduleForDay = Element.byXpath("//div[contains(@class,'rbc-calendar')]");
  private Element calendar = Element.byCss("div.react-calendar");
  private Element monthAndYearInCalendar = Element.byCss(
      "span.react-calendar__navigation__label__labelText");
  private Element monthAndYearInSchedule = Element
      .byXpath("//div[@id='schedule-calendar-date-picker']/span[1]");
  private Element datesOfMonthInCalendar = Element.byCss("div.react-calendar__month-view__days");
  private Element dayOfMonthInCalendar = Element.byXpath(
      "//button[contains(@class,'react-calendar__tile')]");
  private Element selectedDayInCalendar = Element.byCss("button.react-calendar__tile--active abbr");
  private Element dayOfMonthInSchedule = Element
      .byXpath("//div[@id='schedule-calendar-current-day']/span[1]");
  private Element dayOfWeekInSchedule = Element
      .byXpath("//div[@id='schedule-calendar-current-day']/span[2]");
  private Element rightArrowButtonInCalendar = Element
      .byCss("button.react-calendar__navigation__next-button");
  private Element myScheduleTab = Element.byXpath("//div[@id='trainer-tab-switcher']/div[1]");
  private Element myGroupsTab = Element.byXpath("//div[@id='trainer-tab-switcher']/div[2]");
  private Element classCard = Element.byCss("div.rbc-event");
  private Element scheduleButton = Element.byXpath("//div[@id='schedule-tab-switcher']/div[2]");
  private Element tasksButton = Element.byXpath("//div[@id='schedule-tab-switcher']/div[3]");
  private Element goToJournalButton = Element.byCss("div.event-content__col a");
  private Element myGroupsSearchInput = Element.byCss("input.uui-input");
  private Element groupsTable = Element.byXpath(LINE_TABLE_IN_MY_GROUPS_TAB_LOCATOR + "/..");
  private Element trainingButton = Element.byCss("div.uui-table-row-container button");
  private Element pageNumbers = Element.byXpath(
      "//*[@class='section-tabs__content']//div[3]/div[2]");
  private Element addClassButton = Element.byXpath("//button[@id='add-class-button']/div/div");
  private Element confirmDeleteClassButton = Element
      .byXpath(
          "//div[contains(@class,'uui-modal-window')]//div[2]/div[contains(@class,'uui-caption')]");
  private Element closeNotificationButton = Element
      .byXpath("//div[@class='uui-snackbar-item-self']/div/div/div[3]");
  private Element groupNameButton = Element.byXpath(
      "//a[contains(@href,'group-journal') and not (contains(@href,'trainingId'))]");
  private Element groupsSearchBar = Element.byId("groups-search-bar");
  private Element searchIcon = Element.byXpath("//div[@id='groups-search-bar']/div[1]/div[2]");
  private Element groupsSearchBarField = Element.byXpath(
      "//*[@id='groups-search-bar']//input");
  private Element headerFieldsInGroupsList = Element.byXpath(
      "//*[contains(@class,'uui-has-left-icon')]/preceding-sibling::div/div");
  private Element locationIcon = Element.byXpath(LINE_TABLE_IN_MY_GROUPS_TAB_LOCATOR +
      "//button//following-sibling::div//*[@fill-rule='evenodd']");
  private Element journalIcon = Element.byXpath(LINE_TABLE_IN_MY_GROUPS_TAB_LOCATOR +
      "//button//following-sibling::div//a//*[@fill-rule='evenodd']");
  private Element tasksSection = Element.byXpath(TASKS_SECTION_LOCATOR);
  private Element taskTicketAtTaskSection = Element.byXpath(TASK_TICKET_AT_TASKS_SECTION);
  private Element groupJournalButton = Element
      .byCss("a[href*='group-journal?group']:not([href*='training'])");
  private Element checkButton = Element
      .byXpath("//span[contains(@class,'status-passed')]/../following-sibling::div");
  private Element advancedSearchButton = Element
      .byXpath("//div[@id='groups-search-bar']/div[2]");
  private Element resetButton = Element.byXpath(
      "//div[contains(@class, 'button-box ')]//div[text()='%s']",
      getValueOf(REACT_TRAINING_MY_GROUPS_TAB_RESET_BUTTON));
  private Element enableApplyButton = Element.byXpath(
      "//div[contains(@class,'enabled')]//div[text()='%s']",
      getValueOf(REACT_TRAINING_MANAGEMENT_APPLY_BUTTON));
  private Element applyButton = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_TRAINING_MANAGEMENT_APPLY_BUTTON));
  private Element onlyMyGroupsCheckBox = Element.byXpath("//*[contains(@class,'checkbox')]");
  private Element calendarIconInSchedule = Element
      .byXpath(SCHEDULE_DATE_PICKER_LOCATOR + "/div[1]/div[1]");
  private Element todayFieldInSchedule = Element
      .byXpath(SCHEDULE_DATE_PICKER_LOCATOR + "/div[1]/div[2]");
  private Element leftArrowButtonInSchedule = Element
      .byXpath(SCHEDULE_DATE_PICKER_LOCATOR + "/div[2]");
  private Element rightArrowButtonInSchedule = Element
      .byXpath(SCHEDULE_DATE_PICKER_LOCATOR + "/div[3]");
  private Element scheduleDayViewModeField = Element
      .byXpath(SCHEDULE_DATE_PICKER_LOCATOR + "/span[2]/div/div[1]");
  private Element viewArrowButtonInSchedule = Element
      .byXpath(SCHEDULE_DATE_PICKER_LOCATOR + "/span[2]/div/div[2]/..");
  private Element downViewArrowButtonInSchedule = Element
      .byXpath(SCHEDULE_DATE_PICKER_LOCATOR + "/span[2]/div");
  private Element calendarGridInSchedule = Element.byCss("div.rbc-day-slot");
  private Element timeGridInSchedule = Element.byCss("div.rbc-time-gutter");
  private Element timeSlotInSchedule = Element
      .byXpath("//div[@class='rbc-time-view']//div[@class='rbc-time-slot']/span");
  private Element currentTimeLineInSchedule = Element.byCss("div.rbc-current-time-indicator");
  private Element scheduleWeekViewModeField = Element.byXpath(SCHEDULE_VIEW_MODE_PATTERN,
      getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK));
  private Element scheduleMonthViewModeField = Element.byXpath(SCHEDULE_VIEW_MODE_PATTERN,
      getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH));
  private Element scheduleViewModeSelection = Element.byXpath("//div[@class='uui-popper']");
  private Element classInSchedule = Element.byXpath("//div[@class='rbc-event']");
  private Element classNameInSelectedEvent = Element
      .byXpath(
          "//div[contains(@class,'rbc-selected')]//div[contains(@class,'event-content__col')]/div[1]");
  private Element monthSelectionContainer = Element.byCss("div.react-calendar__year-view__months");
  private Element yearSelectionContainer = Element.byCss("div.react-calendar__decade-view__years");
  private Element showElementsSwitcher = Element.byCss("div.uui-label-top div.uui-input-box");
  private Element showElementsMaxCount = Element
      .byXpath("//div[@class='uui-popper']//div[text()='100']");
  private Element trainingJournalLink = Element.byCss("a[href*='trainingId']");
  private Element monthScheduleCell = Element.byXpath("//div[contains(@class,'rbc-day-bg')]");
  private Element classTicketInMonthViewSchedule = Element
      .byXpath("//div[contains(text(),'')]/ancestor::div[@class='rbc-event-content']");
  private Element spinnerOfLoading = Element.byXpath("//div[contains(@class,'spinner-container')]");
  private Element advancedSearchElements = Element
      .byXpath("//*[@class='search-select__input']//input");
  private Element selectDateAdvancedSearchInput = Element.byXpath("//input[@placeholder='%s']",
      getValueOf(REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_SET_DATE_IN_DEADLINE_INPUT));
  private Element downloadMyScheduleButton = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_TRAINING_SCHEDULE_DOWNLOAD_SCHEDULE));
  private Element currentDayInSchedule = Element
      .byXpath("//div[@id='schedule-calendar-current-day']//span");
  private Element currentDayOfWeekInSchedule = Element
      .byXpath("//div[@id='schedule-calendar-current-day']//span[2]");
  private Element elementsOfPeriodFromDDL = Element.byXpath("//div[@class='uui-input-label']");
  private Element oneOfDayFromCalendar = Element
      .byXpath("//*[contains(@class,'uui-calendar-clickable-day')]//div");
  private Element myGroupsSearchField = Element.byXpath("//input[contains(@class,'uui-input')]/..");
  private Element cancelButton = Element.byXpath("//div[contains(@class,'uui-icon-cancel')]");
  private Element onlyMyGroups = Element.byXpath("//div[@class='uui-checkbox uui-checked']");
  Element applyButtonWithoutText = Element.byXpath("//div[@id='groups-search-footer']/div[2]/div[2]");

  @Override
  public boolean isScreenLoaded() {
    return isScheduleForDayDisplayed();
  }

  public boolean isScheduleForDayDisplayed() {
    return scheduleForDay.isDisplayed();
  }

  public ReactTrainingScreen waitCalendarForVisibility() {
    calendar.waitForVisibility();
    return this;
  }

  public ReactLessonInfoPopUpScreen clickClassCard() {
    classCard.click();
    return new ReactLessonInfoPopUpScreen();
  }

  public ReactTrainingScreen waitScheduleForVisibility() {
    scheduleForDay.waitForVisibility();
    return this;
  }

  public ReactTrainingScreen waitPageNumbersForDisappear() {
    pageNumbers.waitForDisappear();
    return this;
  }

  public ReactTrainingScreen waitPageNumbersForVisibility() {
    pageNumbers.waitForVisibility();
    return this;
  }

  public boolean isCalendarDisplayed() {
    return calendar.isDisplayed();
  }

  public boolean isMyScheduleTabDisplayed() {
    return myScheduleTab.isDisplayed();
  }

  public boolean isMyGroupsTabDisplayed() {
    return myGroupsTab.isDisplayed();
  }

  public boolean isScheduleButtonDisplayed() {
    return scheduleButton.isDisplayed();
  }

  public boolean isTasksButtonDisplayed() {
    return tasksButton.isDisplayed();
  }

  public ReactTrainingScreen clickRightArrowButtonInCalendar() {
    rightArrowButtonInCalendar.click();
    return this;
  }

  public List<Element> getDaysOfMonthList() {
    return dayOfMonthInCalendar.getElements();
  }

  public ReactTrainingScreen clickOnDayByIndex(int index) {
    getDaysOfMonthList().get(index).click();
    return this;
  }

  public int getSelectedDayInCalendarValue() {
    return Integer.parseInt(selectedDayInCalendar.getText());
  }

  public int getDayOfMonthInScheduleValue() {
    return Integer.parseInt(dayOfMonthInSchedule.getText());
  }

  public String getDayOfWeekInScheduleText() {
    return dayOfWeekInSchedule.getText();
  }

  public String getMonthAndYearInCalendarText() {
    return monthAndYearInCalendar.getText().contains("АЙ") ?
        monthAndYearInCalendar.getText().replace("АЙ", "АЯ") : monthAndYearInCalendar.getText();
  }

  public String getMonthAndYearInCalendar() {
    return monthAndYearInCalendar.getText();
  }

  public String getMonthAndYearInScheduleText() {
    return monthAndYearInSchedule.getText();
  }

  public ReactTrainingTaskScreen clickTasksButton() {
    tasksButton.click();
    return new ReactTrainingTaskScreen();
  }

  public boolean isTasksButtonHighlighted() {
    return tasksButton.getCssValue(BACKGROUND_COLOR.toString())
        .equals(ACTIVE_BUTTON_COLOR);
  }

  public boolean isClassTicketInScheduleByNameDisplayed(String className) {
    return Element.byXpath(CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN, className)
        .isDisplayedShortTimeOut();
  }

  public boolean isClassTicketTimeByNameDisplayed(String className) {
    return Element.byXpath(CLASS_TIME_BY_NAME_PATTERN, className).isDisplayed();
  }

  public String getClassTicketTimeByNameText(String className) {
    return Element.byXpath(CLASS_TIME_BY_NAME_PATTERN, className).getText();
  }

  public boolean isClassTicketTypeByNameDisplayed(String className) {
    return Element.byXpath(CLASS_TYPE_BY_NAME_PATTERN, className).isDisplayed();
  }

  public String getClassTicketTypeByNameText(String className) {
    return Element.byXpath(CLASS_TYPE_BY_NAME_PATTERN, className).getText();
  }

  public boolean isClassTicketTypeIconByNameDisplayed(String className) {
    return Element.byXpath(CLASS_TYPE_ICON_BY_NAME_PATTERN, className).isDisplayed();
  }

  public boolean isClassTicketLocationByNameDisplayed(String className) {
    return Element.byXpath(CLASS_LOCATION_BY_NAME_PATTERN, className).isDisplayed();
  }

  public String getGoToJournalButtonText() {
    return goToJournalButton.getText();
  }

  public ReactLessonInfoPopUpScreen clickInScheduleClassCardByTopic(String classTopic) {
    Element.byXpath(CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN, classTopic).waitForVisibility();
    Element.byXpath(CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN, classTopic).mouseOver();
    Element.byXpath(CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN, classTopic).waitForClickableAndClick();
    return new ReactLessonInfoPopUpScreen();
  }

  public ReactTrainingScreen clickMyGroupsTab() {
    myGroupsTab.mouseOverAndClick();
    return this;
  }

  public ReactTrainingScreen typeGroupNameInput(String groupName) {
    myGroupsSearchInput.click();
    myGroupsSearchInput.type(groupName);
    return this;
  }

  public ReactTrainingScreen clickSearchIcon() {
    searchIcon.clickJs();
    return this;
  }

  public ReactTrainingScreen waitGroupsTableFieldForVisible() {
    groupsTable.waitForVisibility();
    return this;
  }

  public ReactTrainingScreen clickTrainingButton() {
    trainingButton.clickJs();
    return this;
  }

  public ReactTrainingScreen clickMyScheduleTab() {
    myScheduleTab.click();
    return this;
  }

  public ReactTrainingScreen clickTrainingByNameInMyGroupsTab(String trainingName) {
    Element.byXpath(TRAINING_NAME_IN_MY_GROUPS_TAB_PATTERN, trainingName)
        .waitForClickableAndClick();
    return this;
  }

  public ReactTrainingScreen waitAddClassButtonForVisibility() {
    addClassButton.waitForVisibility();
    return this;
  }

  public ReactAddClassPopUpScreen clickAddClassButton() {
    addClassButton.click();
    return new ReactAddClassPopUpScreen();
  }

  public ReactTrainingScreen clickConfirmDeleteButton() {
    confirmDeleteClassButton.click();
    return this;
  }

  public ReactTrainingScreen clickCloseNotificationButton() {
    closeNotificationButton.click();
    return this;
  }

  public String getGroupNameButtonText() {
    return groupNameButton.getText();
  }

  public List<String> getListOfGroupNameButtonsText() {
    return groupNameButton.getElementsText();
  }

  public String getMyScheduleTabText() {
    return myScheduleTab.getText();
  }

  public String getMyGroupsTabText() {
    return myGroupsTab.getText();
  }

  public String getScheduleButtonText() {
    return scheduleButton.getText();
  }

  public String getTasksButtonText() {
    return tasksButton.getText();
  }

  public boolean isGroupsSearchBarDisplayed() {
    return groupsSearchBar.isDisplayed();
  }

  public boolean isTasksSectionDisplayed() {
    return tasksSection.isDisplayed();
  }

  public ReactTrainingScreen waitTasksSectionForVisibility() {
    tasksSection.waitForVisibility();
    return this;
  }

  public List<Element> getTaskTicketsAtTasksSectionList() {
    return taskTicketAtTaskSection.getElements();
  }

  public int getTaskTicketsAtTasksSectionCount() {
    return getTaskTicketsAtTasksSectionList().size();
  }

  public boolean isDeadlineTitleInTaskTicketDisplayedByIndex(int index) {
    return Element.byXpath(DEADLINE_TITLE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index)
        .isDisplayed();
  }

  public String getDeadlineTitleInTaskTicketByIndex(int index) {
    return Element.byXpath(DEADLINE_TITLE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isDeadlineDateInTaskTicketDisplayedByIndex(int index) {
    return Element.byXpath(DEADLINE_DATE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index)
        .isDisplayed();
  }

  public String getDeadlineDateInTaskTicketByIndex(int index) {
    return Element.byXpath(DEADLINE_DATE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public String getDateOfDeadlineTaskByIndex(int index) {
    String taskDeadLine = getDeadlineDateInTaskTicketByIndex(index);
    return taskDeadLine.substring(0, taskDeadLine.indexOf(DEADLINE_DATE_DELIMITER));
  }

  public boolean isGroupNameInTaskTicketDisplayedByIndex(int index) {
    return Element.byXpath(GROUP_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public String getGroupNameInTaskTicketByIndex(int index) {
    return Element.byXpath(GROUP_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isTaskNameInTaskTicketDisplayedByIndex(int index) {
    return Element.byXpath(TASK_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public String getTaskNameInTaskTicketByIndex(int index) {
    return Element.byXpath(TASK_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isSubmittedTaskStatusDisplayedByIndex(int index) {
    return Element.byXpath(SUBMITTED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN, index)
        .isDisplayed();
  }

  public String getSubmittedTaskStatusInTaskTicketByIndex(int index) {
    return Element.byXpath(SUBMITTED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isCheckedTaskStatusDisplayedByIndex(int index) {
    return Element.byXpath(CHECKED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN, index)
        .isDisplayed();
  }

  public String getCheckedTaskStatusInTaskTicketByIndex(int index) {
    return Element.byXpath(CHECKED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isCheckButtonDisplayedByIndex(int index) {
    return Element.byXpath(CHECK_BUTTON_IN_TASK_TICKET_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public String getCheckButtonTextInTaskTicketByIndex(int index) {
    return Element.byXpath(CHECK_BUTTON_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public ReactGroupJournalScreen clickGroupJournalButton() {
    groupJournalButton.click();
    return new ReactGroupJournalScreen();
  }

  public ReactHomeTaskPopUpScreen clickCheckButton() {
    checkButton.click();
    return new ReactHomeTaskPopUpScreen();
  }

  public ReactGroupJournalScreen clickGroupNameButton() {
    groupNameButton.click();
    return new ReactGroupJournalScreen();
  }

  public ReactGroupJournalScreen clickGroupByName(String groupName) {
    Element.byXpath(GROUP_NAME_BUTTON_BY_NAME_LOCATOR, groupName).waitForVisibility().click();
    return new ReactGroupJournalScreen();
  }

  public boolean isDayOfWeekDisplayed(String dayOfWeek) {
    return Element.byXpath(DAY_OF_WEEK_IN_CALENDAR_PATTERN, dayOfWeek).isDisplayed();
  }

  public String getGroupsSearchValue() {
    return groupsSearchBarField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getAdvancedSearchText() {
    return advancedSearchButton.getText();
  }

  public List<String> getHeaderGroupsText() {
    return headerFieldsInGroupsList.getElementsText();
  }

  public List<String> getAdvancedSearchElementsText() {
    return advancedSearchElements.getElements().stream()
        .map(element -> element.getAttributeValue(PLACEHOLDER_CSS_PROPERTY))
        .collect(Collectors.toList());
  }

  public List<String> getElementsOfPeriodFromDDLText() {
    elementsOfPeriodFromDDL.waitForPresenceOfAllElements(SHORT_TIME_OUT_IN_SECONDS);
    return elementsOfPeriodFromDDL.getElementsText();
  }

  public ReactTrainingScreen clickElementOfPeriodFromDDL(String period) {
    Element.byXpath(ELEMENT_OF_PERIOD_FROM_DDL_PATTERN, period).waitForClickableAndClick();
    return this;
  }

  public boolean isLocationIconDisplayed() {
    return locationIcon.isDisplayed();
  }

  public boolean isJournalIconDisplayed() {
    return journalIcon.isDisplayed();
  }

  public boolean isSelectedDayHighlightedInBlue() {
    return selectedDayInCalendar.getCssValue(BACKGROUND_COLOR.toString())
        .equals(ACTIVE_BUTTON_COLOR);
  }

  public boolean isCurrentDayInScheduleHighlightedInBlue() {
    return currentDayInSchedule.getCssValue(BACKGROUND_COLOR.toString())
        .equals(ACTIVE_BUTTON_COLOR);
  }

  public ReactTrainingScreen clickMyGroupsSearchInput() {
    myGroupsSearchInput.click();
    return this;
  }

  public ReactTrainingScreen clickEnterInMyGroupsSearchInput() {
    int clickAttempt = 0;
      while (clickAttempt <= MAX_ITERATION_COUNT && getNumberOfSelectedGroups() > 1) {
        myGroupsSearchInput.enter();
        clickAttempt++;
      }
    return this;
  }

  public boolean isMyGroupsSearchFieldActivated() {
    return myGroupsSearchField.getAttributeValue(HTML.Attribute.CLASS.toString())
        .contains(FOCUS_CLASS_VALUE);
  }

  public String getMyGroupsSearchInputText() {
    return myGroupsSearchInput.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public ReactTrainingScreen clickAdvancedSearchButton() {
    advancedSearchButton.click();
    return this;
  }

  public ReactTrainingScreen clickApplyButton() {
    enableApplyButton.waitForClickableAndClick();
    return this;
  }

  public boolean isClockIconInTaskTicketDisplayedByIndex(int index) {
    return Element.byXpath(CLOCK_ICON_IN_TASK_TICKET_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public boolean isAddClassButtonDisplayed() {
    return addClassButton.isDisplayed();
  }

  public ReactTrainingScreen waitClassTicketInScheduleByNameForVisibility(String className) {
    Element.byXpath(CLASS_IN_SCHEDULE_TAB_BY_NAME_PATTERN, className).waitForVisibility();
    return this;
  }

  public String getGoToJournalButtonTextByClassName(String className) {
    return Element.byXpath(CLASS_GO_TO_JOURNAL_BUTTON_BY_NAME_PATTERN, className).getText();
  }

  public boolean isCalendarIconInScheduleDisplayed() {
    return calendarIconInSchedule.isDisplayed();
  }

  public String getTodayFieldText() {
    todayFieldInSchedule.waitForPresence();
    return todayFieldInSchedule.getText();
  }

  public boolean isLeftArrowButtonInScheduleDisplayed() {
    return leftArrowButtonInSchedule.isDisplayed();
  }

  public boolean isRightArrowButtonInScheduleDisplayed() {
    return rightArrowButtonInSchedule.isDisplayed();
  }

  public String getScheduleDayViewModeText() {
    return scheduleDayViewModeField.getText();
  }

  public boolean isViewArrowButtonDownDirectionByDefaultInScheduleDisplayed() {
    return downViewArrowButtonInSchedule.isDisplayed();
  }

  public ReactTrainingScreen clickDownViewArrowButtonInSchedule() {
    downViewArrowButtonInSchedule.waitForClickableAndClick();
    return this;
  }

  public boolean isCalendarGridInScheduleDisplayed() {
    return calendarGridInSchedule.isDisplayed();
  }

  public boolean isTimeGridInFirstColumnAtScheduleDisplayed() {
    return timeGridInSchedule.isDisplayed();
  }

  public boolean isCurrentTimeLineInScheduleDisplayed() {
    return currentTimeLineInSchedule.isDisplayed();
  }

  public List<String> getTimeSlotInScheduleList() {
    return timeSlotInSchedule.getElementsText();
  }

  public boolean isClassTicketClockIconByNameDisplayed(String className) {
    return Element.byXpath(CLASS_CLOCK_ICON_BY_NAME_PATTERN, className).isDisplayed();
  }

  public ReactTrainingScreen clickMonthAndYearInCalendar() {
    monthAndYearInCalendar.click();
    return this;
  }

  public ReactTrainingScreen waitDatesOfMonthInCalendarForVisibility() {
    datesOfMonthInCalendar.waitForVisibility();
    return this;
  }

  public ReactTrainingScreen waitMonthSelectionContainerForVisibility() {
    monthSelectionContainer.waitForVisibility();
    return this;
  }

  public ReactTrainingScreen waitYearSelectionContainerForVisibility() {
    yearSelectionContainer.waitForVisibility();
    return this;
  }

  public ReactTrainingScreen clickYearInCalendar(int year) {
    Element.byXpath(YEAR_IN_CALENDAR_PATTERN, year).click();
    return this;
  }

  public ReactTrainingScreen clickMonthInCalendar(String month) {
    Element.byXpath(MONTH_IN_CALENDAR_PATTERN, month).click();
    return this;
  }

  public ReactTrainingScreen clickDayInCalendar(int day) {
    Element.byXpath(DAY_IN_CALENDAR_PATTERN, day).click();
    return this;
  }

  public boolean isClassCardDisplayed() {
    return classCard.isDisplayedShortTimeOut();
  }

  public boolean isDateIndicatingDotDisplayed(int day) {
    return Element.byXpath(DATE_INDICATING_DOT_BY_DAY_PATTERN, day).isDisplayedShortTimeOut();
  }

  public String getDateIndicatingDotColorByDay(int day) {
    return Element.byXpath(DATE_INDICATING_DOT_BY_DAY_PATTERN, day).getCssValue(FILL_CSS_PROPERTY);
  }

  public String getCurrentTimeLineColor() {
    return currentTimeLineInSchedule.getCssValue(BACKGROUND_COLOR.toString());
  }

  public double getDayFromScheduleExpirationPercent() {
    char beginIndex = ' ';
    char endIndex = '%';
    String percent = currentTimeLineInSchedule.getAttributeValue(HTML.Attribute.STYLE.toString());
    return Double
        .parseDouble(percent.substring(percent.indexOf(beginIndex) + 1, percent.indexOf(endIndex)));
  }

  public ReactGroupJournalScreen clickGoToJournalButtonByClassName(String className) {
    Element.byXpath(CLASS_GO_TO_JOURNAL_BUTTON_BY_NAME_PATTERN, className)
        .waitForClickableAndClick();
    return new ReactGroupJournalScreen();
  }

  public ReactTrainingScreen clickShowElementsSwitcher() {
    showElementsSwitcher.click();
    return this;
  }

  public ReactTrainingScreen clickShowElementsMaxCount() {
    showElementsMaxCount.click();
    return this;
  }

  public ReactGroupJournalScreen clickTrainingJournalLink() {
    trainingJournalLink.click();
    return new ReactGroupJournalScreen();
  }

  public boolean isScheduleViewModeSelectionDisplayed() {
    return scheduleViewModeSelection.isDisplayed();
  }

  public ReactTrainingScreen clickViewArrowButtonInSchedule() {
    viewArrowButtonInSchedule.waitForClickable();
    viewArrowButtonInSchedule.mouseOverAndClick();
    return this;
  }

  public ReactTrainingScreen clickScheduleWeekViewModeField() {
    scheduleWeekViewModeField.waitForClickableAndClick();
    return this;
  }

  public boolean isDayOfWeekInScheduleWeekViewModeDisplayed(String dayOfWeek) {
    return Element.byXpath(DAY_OF_WEEK_IN_WEEK_SCHEDULE_PATTERN, dayOfWeek).isDisplayed();
  }

  public ReactTrainingScreen waitUntilAllClassesInWeeklyScheduleDisplayed() {
    int size = classInSchedule.getElements().size();
    classInSchedule.waitNumberOfElementsToBeMoreThan(size - 1);
    return this;
  }

  public int getClassInScheduleCount() {
    return classInSchedule.getElements().size();
  }

  public ReactLessonInfoPopUpScreen clickClassInScheduleByIndex(int index) {
    classInSchedule.getElements().get(index).click();
    return new ReactLessonInfoPopUpScreen();
  }

  public String getClassNameInSelectedEvent() {
    return classNameInSelectedEvent.getText();
  }

  public ReactTrainingService clickCheckButtonInTasksSectionByTaskName(String taskName) {
    Element.byXpath(CHECK_BUTTON_IN_TASKS_SECTION_BY_TASK_TICKET_NAME, taskName).click();
    return new ReactTrainingService();
  }

  public ReactTrainingScreen waitTaskTicketInTaskSectionForInvisibility(String taskName) {
    Element.byXpath(TASK_TICKET_AT_TASKS_SECTION_BY_NAME_PATTERN, taskName).waitForInvisibility();
    return this;
  }

  public boolean isTaskTicketInTasksSectionByNameDisplayed(String taskName) {
    return Element.byXpath(TASK_TICKET_AT_TASKS_SECTION_BY_NAME_PATTERN, taskName).isDisplayed();
  }

  public ReactTrainingScreen waitScheduleViewModeSelectionForVisibility() {
    scheduleViewModeSelection.waitForVisibility();
    return this;
  }

  public ReactTrainingScreen waitScheduleViewModeSelectionForInvisibility() {
    scheduleViewModeSelection.waitForInvisibility();
    return this;
  }

  public ReactTrainingScreen clickScheduleMonthViewModeField() {
    scheduleMonthViewModeField.waitForClickableAndClick();
    return this;
  }

  public boolean isClassNameInMonthScheduleTabDisplayed(String className) {
    return Element.byXpath(CLASS_IN_MONTH_SCHEDULE_TAB_BY_NAME_PATTERN, className).isDisplayed();
  }

  public int getMonthScheduleCellWidth() {
    return monthScheduleCell.getWidth();
  }

  public int getClassNameWidth(String className) {
    return Element.byXpath(CLASS_IN_MONTH_SCHEDULE_TAB_BY_NAME_PATTERN, className).getWidth();
  }

  public ReactTrainingScreen waitUntilAllClassTicketsInMonthViewScheduleAreVisible() {
    classTicketInMonthViewSchedule.waitUntilAllElementsLocatedByAreVisible();
    return this;
  }

  public ReactLessonInfoPopUpScreen clickClassInMonthViewScheduleByName(String className) {
    Element.byXpath(CLASS_IN_MONTH_SCHEDULE_TAB_BY_NAME_PATTERN, className).click();
    return new ReactLessonInfoPopUpScreen();
  }

  public ReactTrainingScreen waitScreenLoading() {
    spinnerOfLoading.waitForInvisibility();
    return this;
  }

  public boolean isPageNumbersDisplayed() {
    return pageNumbers.isDisplayed();
  }

  public boolean isMyGroupsSearchInputDisplayed() {
    return myGroupsSearchInput.isDisplayed();
  }

  public boolean isAdvancedSearchButtonDisplayed() {
    return advancedSearchButton.isDisplayed();
  }

  public boolean isOnlyMyGroupsCheckBoxDisplayed() {
    return onlyMyGroupsCheckBox.isDisplayed();
  }

  public boolean isApplyButtonDisplayed() {
    return applyButton.isDisplayed();
  }

  public boolean isResetButtonDisplayed() {
    return resetButton.isDisplayed();
  }

  public boolean isDownloadMyScheduleButtonDisplayed() {
    return downloadMyScheduleButton.isDisplayed();
  }

  public String getCurrentDayOfWeekInScheduleText() {
    return currentDayOfWeekInSchedule.getText();
  }

  public ReactTrainingScreen clickSelectDateAdvancedSearchInput() {
    selectDateAdvancedSearchInput.click();
    return this;
  }

  public ReactTrainingScreen clickOneOfDayFromCalendar() {
    oneOfDayFromCalendar.click();
    return this;
  }

  public String getSelectDateAdvancedSearchInputValue() {
    return selectDateAdvancedSearchInput.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public ReactTrainingScreen clickCancelButton() {
    cancelButton.click();
    return this;
  }


  public int getNumberOfSelectedGroups() {
    return groupNameButton.getElements().size();
  }

  public ReactTrainingScreen waitForNumberOfSelectedTrainingsToBeEqualOne() {
    groupNameButton.waitNumberOfElementsToBeLessThan(2);
    return this;
  }

  public ReactTrainingScreen waitForNumberOfSelectedTrainingsToBeMoreThanOne() {
    groupNameButton.waitNumberOfElementsToBeMoreThan(1);
    return this;
  }

  public ReactTrainingScreen clickOnlyMyGroupsCheckBox() {
    onlyMyGroupsCheckBox.click();
    return this;
  }

  public void selectAllGroups() {
    clickAdvancedSearchButton();
    onlyMyGroups.click();
    applyButtonWithoutText.waitUntilAttributeGetsValue(
        BACKGROUND_COLOR.toString(), ACTIVE_BUTTON_COLORS, DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    applyButton.clickJs();
  }

  public ReactTasksJournalScreen switchToGroupJournal(String trainingName){
    Element.byXpath("//a[text() = '%s']", trainingName).clickJs();
    return new ReactTasksJournalScreen();
  }
}
