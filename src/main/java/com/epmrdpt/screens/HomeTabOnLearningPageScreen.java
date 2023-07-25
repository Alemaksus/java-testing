package com.epmrdpt.screens;

import static java.lang.String.format;
import static javax.swing.text.html.HTML.Attribute.CLASS;
import static javax.swing.text.html.HTML.Attribute.COLOR;
import static javax.swing.text.html.HTML.Attribute.STYLE;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.openqa.selenium.WebElement;

public class HomeTabOnLearningPageScreen extends AbstractScreen {

  private static final String TITLE_TAB_CURRENT_GROUPS_LOCATOR_PATTERN =
      "//div[@*='templateUrls.desktop']//div[contains(@ng-click, '%s')]";
  private static final String SORT_ARROW_LOCATOR_PATTERN =
      "./div[contains(@class,'rd-table__sorting-arrow')]";
  private static final String ACTIVE_TAB_HTML_VALUE = "active-tab";
  private static final String DIV_1_LOCATOR_PATTERN = "./div[1]";
  private static final String DIV_2_LOCATOR_PATTERN = "./div[2]";
  private static final String DIV_3_LOCATOR_PATTERN = "./div[3]";
  private static final String DIV_4_LOCATOR_PATTERN = "./div[4]";
  private static final String DATE_BOX_LOCATOR = "//div[contains(@class,'cal-cell')][not(contains(@style,'rgb(236, 236, 236)'))]//div[contains(@class,'month-calendar-day')][text()='%s']";
  private static final String LESSON_IN_DATE_BOX_LOCATOR = "//div[text()='%s']";
  private static final String TOPIC_TIME_IN_WEEK_TAB_BY_TOPIC_NAME = "//div[text()='%s']/following-sibling::div[contains(@class,'week-time')]";
  private static final String LEARNING_CONTAINER_LINK_PATTERN = "//a[text()='%s']";

  private Element listTab = Element.byXpath("//tabs[contains(@class,'calendar-tabs')]/"
      + "div[contains(@class,'tabs__info-tab')][2]");
  private Element currentGroupsContainerElement = Element.byXpath("//*[@items='currentGroups']");
  private Element homeTab = Element.byCss("a.tabs__info-tab[href*='Home']");
  private Element calendarTabsSection = Element.byCss("div.calendar-tab");
  private Element calendarDaysSection = Element.byXpath(
      "//div[@class='cal-row-fluid cal-row-head']");
  private Element groupNameTitleElement =
      Element.byXpath(format(TITLE_TAB_CURRENT_GROUPS_LOCATOR_PATTERN, "GroupName"));
  private Element locationTitleElement =
      Element.byXpath(format(TITLE_TAB_CURRENT_GROUPS_LOCATOR_PATTERN, "LabAddress"));
  private Element startDateTitleElement =
      Element.byXpath(format(TITLE_TAB_CURRENT_GROUPS_LOCATOR_PATTERN, "StartDate"));
  private Element endDateTitleElement =
      Element.byXpath(format(TITLE_TAB_CURRENT_GROUPS_LOCATOR_PATTERN, "FinishDate"));
  private Element groupElement = Element.byXpath(
      "//*[@*='templateUrls.desktop']//*[@*='color-code']//ancestor::div[@ng-repeat]");
  private Element squareGroupNameElement =
      Element.byXpath("//*[@*='templateUrls.desktop']//div[@*='color-code']");
  private Element calendarButton = Element
      .byCss("tabs.calendar-tabs div.tabs__info-tab:first-child");
  private Element listButton = Element.byCss("tabs.calendar-tabs div.tabs__info-tab:last-child");
  private Element epamTrainingTitle = Element.byCss("tabs.tabs-header div.tabs__info-tab");
  private Element headerColumnLessonDateElement = Element
      .byCss("div#list-tab div.rd-table--desktop div.rd-table__header:nth-child(1)");
  private Element headerColumnGroupNameElement = Element
      .byCss("div#list-tab div.rd-table--desktop div.rd-table__header:nth-child(2)");
  private Element headerColumnLessonName = Element
      .byCss("div#list-tab div.rd-table--desktop div.rd-table__header:nth-child(3)");
  private Element rowLessonElement = Element
      .byXpath("//div[@*='list-tab']//div[@*='templateUrls.desktop']/div[@*='ng-scope']/div");
  private Element averageMarkForTasksArea = Element
      .byCss("div.tab-content div[ng-class*='AvgMarkPercent']");
  private Element averageMarkForClassesArea = Element
      .byXpath("(//div[contains(@class,'average-mark-value')])[2]");
  private Element averageMarkForClassesTextLocator = Element
      .byXpath("(//div[contains(@class,'average-mark-value')])[2]/following-sibling::div");
  private Element averageMarkForTasksTextLocator = Element
      .byCss("div.tab-content div[ng-class*='AvgMarkPercent'] + div");
  private Element attendanceMarkArea = Element
      .byCss("div.tab-content div[ng-class*='AvgAttendance']");
  private Element attendanceMarkTextLocator = Element
      .byCss("div.tab-content div[ng-class*='AvgAttendance'] + div");
  private Element taskCompletedArea = Element
      .byCss("div.tab-content div[ng-class*='CompletedTasksPercent']");
  private Element taskCompletedTextLocator = Element
      .byCss("div.tab-content div[ng-class*='CompletedTasksPercent'] + div");
  private Element calendarNavigationTodayLabel = Element.byClassName("calendar-nav");
  private Element calendarNavigationRightArrow = Element.byClassName("calendar-nav-arrow-right");
  private Element calendarNavigationLeftArrow = Element.byClassName("calendar-nav-arrow-left");
  private Element calendarCurrentDate = Element.byClassName("current-date");
  private Element calendarModeSection = Element.byClassName("calendar-mode");
  private Element calendarMonthBox = Element.byClassName("cal-month-box");
  private Element daysOfWeekHeader = Element.byCss("div.cal-row-fluid.cal-row-head");
  private Element calendarCellDates = Element
      .byXpath("//div[contains(@class,'month-calendar-day')]");
  private Element eventTitleInCalendar = Element
      .byXpath("//div[contains(@class,'month-event-title')]");
  private Element eventTimeInCalendar = Element.byXpath("//div[contains(@class,'month-time')]");
  private Element eventContainerInCalendar = Element
      .byXpath("//div[@class='month-event-container']");
  private Element calendarCell = Element
      .byXpath("//div[contains(@class,'cal-cell1') and contains(@class,'cal-cell ')]");
  private Element groupName = Element.byXpath(
      "//*[@*='templateUrls.desktop']//*[@*='color-code']//ancestor::div[@ng-repeat]/div[1]");
  private Element epamTrainingTab = Element
      .byXpath("//tabs[contains(@class,'tabs-header')]/div[2]");
  private Element departmentAffiliateTab = Element
      .byXpath("//tabs[contains(@class,'tabs-header')]/div[1]");
  private Element tasksTab = Element.byCss("a.tabs__info-tab[href*='Tasks']");
  private Element monthTabInCalendar = Element.byXpath("//div[contains(@ng-click,'month')]");
  private Element weekTabInCalendar = Element.byXpath("//div[contains(@ng-click,'week')]");
  private Element dayTabInCalendar = Element
      .byXpath("//div[contains(@class,'calendar-mode-item') and contains(@ng-click,'day')]");
  private Element displayedDateInCalendar = Element
      .byXpath("//div[contains(@class,'current-date')]");
  private Element nextButtonInCalendar = Element
      .byXpath("//div[contains(@class,'calendar-nav-arrow-right')]");
  private Element previousButtonInCalendar = Element
      .byXpath("//div[contains(@class,'calendar-nav-arrow-left')]");
  private Element todayButtonInCalendar = Element
      .byXpath("//div[contains(@class,'calendar-nav-today')]");
  private Element weekDates = Element.byXpath("//span[@ng-bind='day.dayLabel']");
  private Element hoursInDayTab = Element.byXpath("//div[@class='cal-day-hour-part-time']/strong");

  @Override
  public boolean isScreenLoaded() {
    return isGroupNameTitleIsDisplayed();
  }

  public boolean isCalendarTabsSectionDisplayed() {
    return calendarTabsSection.isDisplayed();
  }

  public HomeTabOnLearningPageScreen waitForVisibilityOfDaysInCalendarSection() {
    calendarDaysSection.waitForVisibility(LONG_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public HomeTabOnLearningPageScreen waitCurrentGroupsTabDisplayed() {
    currentGroupsContainerElement.waitForVisibility();
    return this;
  }

  public boolean isGroupNameTitleIsDisplayed() {
    return groupNameTitleElement.isDisplayed();
  }

  public boolean isLocationTitleIsDisplayed() {
    return locationTitleElement.isDisplayed();
  }

  public boolean isStartDateTitleIsDisplayed() {
    return startDateTitleElement.isDisplayed();
  }

  public boolean isEndDateTitleIsDisplayed() {
    return endDateTitleElement.isDisplayed();
  }

  public boolean isGroupNameHasSortArrowItem() {
    return groupNameTitleElement
        .findChild(Element.byXpath(SORT_ARROW_LOCATOR_PATTERN))
        .isDisplayed();
  }

  public boolean isLocationHasSortArrowItem() {
    return locationTitleElement
        .findChild(Element.byXpath(SORT_ARROW_LOCATOR_PATTERN))
        .isDisplayed();
  }

  public boolean isStartDateHasSortArrowItem() {
    return startDateTitleElement
        .findChild(Element.byXpath(SORT_ARROW_LOCATOR_PATTERN))
        .isDisplayed();
  }

  public boolean isEndDateHasSortArrowItem() {
    return endDateTitleElement.findChild(Element.byXpath(SORT_ARROW_LOCATOR_PATTERN)).isDisplayed();
  }

  private List<Element> getAllGroups() {
    return groupElement.getElements();
  }

  public int getQuantityGroup() {
    return getAllGroups().size();
  }

  public boolean isAllGroupDisplayed() {
    return groupElement.isAllElementsDisplayed();
  }

  public boolean isGroupHasNameByIndex(int groupIndex) {
    return getChildOfParentFromList(getAllGroups(), groupIndex, DIV_1_LOCATOR_PATTERN)
        .isDisplayed();
  }

  public boolean isGroupHasSquareByIndex(int groupIndex) {
    return squareGroupNameElement
        .getElements()
        .get(groupIndex)
        .getAttributeValue(STYLE.toString())
        .contains("rgb");
  }

  public String getGroupLocationTextByIndex(int groupIndex) {
    return getChildOfParentFromList(getAllGroups(), groupIndex, DIV_2_LOCATOR_PATTERN).getText();
  }

  public String getGroupStartDateTextByIndex(int groupIndex) {
    return getChildOfParentFromList(getAllGroups(), groupIndex, DIV_3_LOCATOR_PATTERN).getText();
  }

  public String getGroupEndDateTextByIndex(int groupIndex) {
    return getChildOfParentFromList(getAllGroups(), groupIndex, DIV_4_LOCATOR_PATTERN).getText();
  }

  private WebElement getChildOfParentFromList(List<Element> listElements, int indexParent,
      String xPathSelector) {
    return listElements
        .get(indexParent)
        .findChild(Element.byXpath(xPathSelector));
  }

  public HomeTabOnLearningPageScreen clickListRadioButton() {
    listButton.click();
    return this;
  }

  public HomeTabOnLearningPageScreen waitCalendarTabDisplayed() {
    calendarButton.waitNeedValueAttributeContaining(CLASS.toString(),
        ACTIVE_TAB_HTML_VALUE);
    return this;
  }

  public boolean isCalendarButtonDisplayed() {
    return calendarButton.isDisplayed();
  }

  public boolean isListButtonDisplayed() {
    return listButton.isDisplayed();
  }

  public boolean isHomeTabDisplayed() {
    return homeTab.isDisplayed();
  }

  public HomeTabOnLearningPageScreen clickHomeTab() {
    homeTab.click();
    return this;
  }

  public boolean isHeaderColumnLessonDateDisplayed() {
    return headerColumnLessonDateElement.isDisplayed();
  }

  public boolean isHeaderColumnLessonNameDisplayed() {
    return headerColumnLessonName.isDisplayed();
  }

  public boolean isHeaderColumnGroupNameDisplayed() {
    return headerColumnGroupNameElement.isDisplayed();
  }

  private List<Element> getAllLessons() {
    return rowLessonElement.getElements();
  }

  public int getQuantityLessons() {
    return getAllLessons().size();
  }

  public String getLessonDateByIndex(int lessonIndex) {
    return getChildOfParentFromList(getAllLessons(), lessonIndex, DIV_1_LOCATOR_PATTERN)
        .getText();
  }

  public String getGroupNameFromLessonListByIndex(int lessonIndex) {
    return getChildOfParentFromList(getAllLessons(), lessonIndex, DIV_2_LOCATOR_PATTERN)
        .getText();
  }

  public String getLessonNameByIndex(int lessonIndex) {
    return getChildOfParentFromList(getAllLessons(), lessonIndex, ".//a")
        .getText();
  }

  public boolean isAverageMarkForTasksAreaDisplayed() {
    return averageMarkForTasksArea.isDisplayed();
  }

  public boolean isAverageMarkForClassesAreaDisplayed() {
    return averageMarkForClassesArea.isDisplayed();
  }

  public boolean isAttendanceMarkAreaDisplayed() {
    return attendanceMarkArea.isDisplayed();
  }

  public boolean isTaskCompletedAreaDisplayed() {
    return taskCompletedArea.isDisplayed();
  }

  public String getAverageMarkForTasksText() {
    return averageMarkForTasksTextLocator.getText();
  }

  public String getAverageMarkForTasksAreaText() {
    return averageMarkForTasksArea.getText();
  }

  public String getAverageMarkForClassesText() {
    return averageMarkForClassesTextLocator.getText();
  }

  public String getAttendanceMarkText() {
    return attendanceMarkTextLocator.getText();
  }

  public String getTaskCompletedText() {
    return taskCompletedTextLocator.getText();
  }

  public ListTabScreenOnLearningPageScreen clickListTab() {
    listTab.clickJs();
    return new ListTabScreenOnLearningPageScreen();
  }

  public String getCalendarNavigationTodayText() {
    return calendarNavigationTodayLabel.getText();
  }

  public boolean isCalendarNavigationTodayLabelDisplayed() {
    return calendarNavigationTodayLabel.isDisplayed();
  }

  public boolean isCalendarNavigationLeftArrowDisplayed() {
    return calendarNavigationLeftArrow.isDisplayed();
  }

  public boolean isCalendarNavigationRightArrowDisplayed() {
    return calendarNavigationRightArrow.isDisplayed();
  }

  public boolean isCurrentDateDisplayed() {
    return calendarCurrentDate.isDisplayed();
  }

  public String getCalendarCurrentDate() {
    return calendarCurrentDate.getText();
  }

  public String getCalendarModeSectionText() {
    return calendarModeSection.getText();
  }

  public boolean isCalendarModeSectionDisplayed() {
    return calendarModeSection.isDisplayed();
  }

  public boolean isCalendarMonthBoxDisplayed() {
    return calendarMonthBox.isDisplayed();
  }

  public boolean isDaysOfWeekDisplayed() {
    return daysOfWeekHeader.isDisplayed();
  }

  public String getDaysOfWeekText() {
    return daysOfWeekHeader.getText();
  }

  public List<String> getCalendarCellText() {
    return calendarCellDates.getElementsText();
  }

  public boolean isEventTitleInCalendarDisplayed() {
    return eventTitleInCalendar.isAllElementsDisplayed();
  }

  public boolean isEventTimeInCalendarDisplayed() {
    return eventTimeInCalendar.isAllElementsDisplayed();
  }

  public List<String> getEventTimeFromCalendarList() {
    return eventTimeInCalendar.getElementsText();
  }

  public boolean isEventContainerInCalendarDisplayed() {
    return eventContainerInCalendar.isAllElementsDisplayed();
  }

  public boolean isCalendarCellDisplayed() {
    return calendarCell.isAllElementsDisplayed();
  }

  public String getGroupName() {
    return groupName.getText().trim();
  }

  public HomeTabOnLearningPageScreen waitEpamTrainingTitleVisibility() {
    epamTrainingTitle.waitForVisibility();
    return this;
  }

  public TasksTabOnLearningPageScreen clickTasksTab() {
    tasksTab.click();
    return new TasksTabOnLearningPageScreen();
  }

  public HomeTabOnLearningPageScreen clickMonthTabInCalendar() {
    monthTabInCalendar.clickJs();
    return this;
  }

  public HomeTabOnLearningPageScreen clickWeekTabInCalendar() {
    weekTabInCalendar.click();
    return this;
  }

  public HomeTabOnLearningPageScreen clickDayTabInCalendar() {
    dayTabInCalendar.click();
    return this;
  }

  public String getDisplayedDateInCalendar() {
    return displayedDateInCalendar.getText();
  }

  public HomeTabOnLearningPageScreen clickNextButtonInCalendar() {
    nextButtonInCalendar.click();
    return this;
  }

  public HomeTabOnLearningPageScreen clickPreviousButtonInCalendar() {
    previousButtonInCalendar.click();
    return this;
  }

  public HomeTabOnLearningPageScreen clickTodayInCalendar() {
    todayButtonInCalendar.click();
    return this;
  }

  public String getColorOfTextOfDayOfMonth(String dayOfMonth) {
    return Element.byXpath(DATE_BOX_LOCATOR, dayOfMonth).getCssValue(COLOR.toString());
  }

  public HomeTabOnLearningPageScreen clickDayOfMonthDisplayedInCalendar(int dayOfMonth) {
    List<Element> dateBox = Element.byXpath(DATE_BOX_LOCATOR, dayOfMonth).getElements();
    dateBox.get(dateBox.size() - 1).clickJs();
    return this;
  }

  public List<String> getWeekDatesTextInCalendar() {
    return weekDates.getElementsText();
  }

  public List<String> getHoursTextInDayTab() {
    return hoursInDayTab.getElementsText();
  }

  public HomeTabOnLearningPageScreen goToScheduledLessonMonth(LocalDate currentDate,
      LocalDate toDate) {
    long monthsBetween = ChronoUnit.MONTHS.between(
        YearMonth.from(currentDate),
        YearMonth.from(toDate));
    if (monthsBetween < 0) {
      clickUntilSameMonthIsDisplayed(Math.abs(monthsBetween), previousButtonInCalendar);
    } else {
      clickUntilSameMonthIsDisplayed(monthsBetween, nextButtonInCalendar);
    }
    return this;
  }

  private void clickUntilSameMonthIsDisplayed(long monthsBetween, Element arrowToBeClicked) {
    for (long i = 0; i < monthsBetween; i++) {
      arrowToBeClicked.click();
    }
  }

  public boolean isTopicNameDisplayed(String lessonName) {
    return Element.byXpath(LESSON_IN_DATE_BOX_LOCATOR, lessonName).isDisplayed();
  }

  public boolean isTimeDisplayedForTopicByTopicName(String topicName) {
    return Element.byXpath(TOPIC_TIME_IN_WEEK_TAB_BY_TOPIC_NAME, topicName).isDisplayed();
  }

  public LessonDetailPopUpScreenOnLearningPageScreen clickTopicByName(String lessonName) {
    Element.byXpath(LESSON_IN_DATE_BOX_LOCATOR, lessonName).click();
    return new LessonDetailPopUpScreenOnLearningPageScreen();
  }

  public HomeTabOnLearningPageScreen clickEpamLearningTab() {
    epamTrainingTab.click();
    return this;
  }

  public boolean isLearningContainerLinkDisplayed(String learningContainerLink) {
    return Element.byXpath(LEARNING_CONTAINER_LINK_PATTERN, learningContainerLink).isDisplayed();
  }

  public boolean isEpamTrainingTabDisplayed() {
    return epamTrainingTab.isDisplayed();
  }

  public String getEpamTrainingTabText() {
    return epamTrainingTab.getText();
  }

  public boolean isDepartmentAffiliateTabDisplayed() {
    return departmentAffiliateTab.isDisplayed();
  }

  public String getDepartmentAffiliateTabText() {
    return departmentAffiliateTab.getText();
  }

  public HomeTabOnLearningPageScreen clickCalendarNavigationLeftArrow() {
    calendarNavigationLeftArrow.click();
    return this;
  }
}
