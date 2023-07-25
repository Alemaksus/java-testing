package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_FROM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_TO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_MONTH_PERIOD;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_TWO_WEEKS_PERIOD;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_WEEK_PERIOD;
import static com.epmrdpt.framework.ui.element.Element.DISABLED_ATTRIBUTE;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.utils.StringUtils.extractNumbersFromString;

import com.epmrdpt.bo.Student;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.utils.DoubleUtils;
import com.epmrdpt.utils.IntegerUtils;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.CSS.Attribute;
import javax.swing.text.html.HTML;

public class ReactGroupJournalScreen extends AbstractScreen {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);

  public static final String COLOR_SYMBOL_OF_ABSENCE = "rgb(255, 164, 139)";
  private static final String PATTERN_DATE = "dd.MM.yy";
  private static final String STUDENT_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN =
      "//*[@class='section-tabs__content']//span[2][text()='%s']";
  private static final String HEADER_DATE_FROM_GROUP_JOURNAL_TAB_BY_DATE_PATTERN =
      "//*[contains(@class,'uui-table-header-row')]//div[@tabindex='-1']/div/div[1][text()='%s']/..";
  private static final String CELL_MARK_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN =
      "//span[text()='%s']//following::div[3]/descendant::div[@data-cell='today'][1]/div";
  private static final String CELL_COMMENT_ICON_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN =
      "//span[text()='%s']//following::div[3]/descendant::div[@data-cell='today'][1]//*[@fill-rule='evenodd']";
  private static final String RED_SYMBOL_X_IN_CELL_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN =
      CELL_MARK_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN + "//*[@fill-rule='evenodd']";
  private static final String HEADER_DATE_FROM_GROUP_JOURNAL_TAB_BY_INDEX_PATTERN =
      "//*[contains(@class,'uui-table-header-row')]//div[2]/div/div/div[%s]//div[@tabindex='-1']/div/div[1]";
  private static final String STUDENT_MARK_FIELD_BY_NAME_AND_DATE_INDEX_PATTERN =
      "//span[contains(text(),'%s')]/ancestor::div[contains(@class,'uui-table-row-containe')]/div[2]/div/div/div[%s]";
  private static final String STUDENT_MARK_FIELD_BY_NAME_DATE_INDEX_AND_MARK_PATTERN =
      "//span[contains(text(),'%s')]/ancestor::div[contains(@class,'uui-table-row-containe')]/div[2]/div/div/div[%s]//div[text()='%s']";
  private static final String MARKS_BY_STUDENT_PATTERN =
      "//span[contains(text(),'%s')]/ancestor::div[contains(@class,'uui-table-row-containe')]//div[@role='button']";
  private static final String AVERAGE_MARK_BY_STUDENT_PATTERN =
      "//span[contains(text(),'%s')]/ancestor::div[contains(@class,'uui-table-row-containe')]/div[3]/div";
  private static final String ABSENCE_COUNT_BY_STUDENT_PATTERN =
      "//span[contains(text(),'%s')]/../../../../../div[4]/div";
  public static final String CELLS_MARKED_ABSENT_BY_STUDENT_PATTERN =
      "//span[contains(text(),'%s')]/../../../../..//*[local-name()='svg' and  not(@role='button')]";
  private static final String EMPTY_AVERAGE_MARK = "-";
  private static final String STUDENT_ABSENCE_SYMBOL_BY_NAME_AND_DATE_INDEX_PATTERN =
      STUDENT_MARK_FIELD_BY_NAME_AND_DATE_INDEX_PATTERN + "//*[@fill-rule='evenodd']";
  private static final String POPUP_COMMENT_FOR_STUDENT_BY_DATE_PATTERN =
      "//div[text()='%s']/../../div[2]/div[1]/div/span";
  private static final String POPUP_COMMENT_FOR_TRAINER_BY_DATE_PATTERN =
      "//div[text()='%s']/../../div[2]/div[2]/div/span";
  private static final String SWITCHER_BETWEEN_GROUPS =
      "//div[text()='/']/..";
  private static final String TAB_OF_NAVIGATION_MENU_BUTTONS_BY_NAME_PATTERN =
      "//section[@class='tabs-section']//div[text()='%s']";
  private static final String ALL_PERIOD_BUTTON_PATTERN = "//button[text()='%s']";
  private static final String GROUP_JOURNAL_SECTION_LOCATOR = "//div[@class='section-tabs__content']";
  private static final String REACT_TRAINING_ALL_PERIOD_BUTTON_TEXT = "react.training.all_period_button";
  private static final String PERIOD_BUTTONS_PATTERN =
      GROUP_JOURNAL_SECTION_LOCATOR + "//div[contains(text(),'%s')]/..";
  private static final String CALENDAR_PATTERN = "//input[@placeholder='%s']";
  private static final String TASK_MARK_BY_TASK_ID_PATTERN = "//*[@id=%d]/div[1]";
  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";

  private Element headerFirstColumn = Element.byXpath(
      "//div[contains(@class,'table-header-cell')]");
  private Element breadCrumbsClassPath = Element.byXpath("//div[@class='bread-crumbs__left']");
  private Element taskJournalTab = Element.byXpath("//*[@class='tabs-section']/div[1]/a[2]/div[1]");
  private Element scheduleTab = Element.byXpath("//*[@class='tabs-section']/div[1]/a[3]/div[1]");
  private Element graduateReportTab = Element.byXpath(
      "//*[@class='tabs-section']/div[1]/a[4]/div[1]");
  private Element breadCrumbs = Element.byClassName("bread-crumbs__left");
  private Element breadCrumbsLevel = Element
      .byXpath("//*[@class='bread-crumbs__left']/div/child::*");
  private Element trainersIcons = Element.byXpath(
      "//*[@class='bread-crumbs__left']/following-sibling::div");
  private Element navigationMenuButtons = Element.byXpath(
      "//*[@class='tabs-section']/div[1]//div[contains(@class,'uui-button-box')]");
  private Element addClassButton = Element.byXpath(
      "//div[@class='section-tabs__content']//div//*[contains(@class,'uui-caption')]");
  private Element groupTableGrid = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]/ancestor::div[2]");
  private Element studentsList = Element.byXpath("//div[@class='section-tabs__content']" +
      "//div[contains(@class,'uui-table-row-container uui-table-row')]//span[2]");
  private Element headerDates = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]//div[@tabindex='-1']/div/div[1]");
  private Element headerClassType = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]//div[@tabindex='-1']/div/div[2]");
  private Element headerAverageMark = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]/div[3]/descendant::div[4]");
  private Element headerAbsence = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]/div[4]/descendant::div[4]");
  private Element scrollBar = Element.byXpath("//*[@class='uui-table-scroll-bar']/div[2]");
  private Element confirmDeleteClassButton = Element.byXpath(
      "//div[contains(@class,'uui-modal-window')]//div[2]/div[contains(@class,'uui-caption')]");
  private Element warningMessage = Element.byXpath(
      "//div[contains(@class,'uui-modal-window')]/div[2]");
  private Element descriptionNotification = Element.byXpath(
      "//div[@class='uui-snackbar-item-self']//div[2]/div");
  private Element closeNotificationButton = Element.byXpath(
      "//div[@class='uui-snackbar-item-self']/div/div/div[3]");
  private Element homeIcon = Element.byCss("a.home-page svg");
  private Element trainingNameFromGroupJournal = Element.byXpath(
      "//*[@class='bread-crumbs__left']/div/*[2]");
  private Element groupSwitcher = Element.byXpath(SWITCHER_BETWEEN_GROUPS);
  private Element theEntireSeries = Element.byXpath(
      "//div[@class='uui-input-group vertical']/div[2]//div[contains(@class,'uui-radioinput')]");
  private Element arrowsOfSwitcherBetweenGroups = Element.byXpath(SWITCHER_BETWEEN_GROUPS +
      "//div[contains(@class, 'uui-button-box')]");
  private Element groupNameFromSwitcher = Element.byXpath(
      SWITCHER_BETWEEN_GROUPS + "//a[contains(@class,' active')]");
  private Element loadingSpinner = Element.byXpath("//div[@class='section-tabs__content']/div"
      + "/following-sibling::div[contains(@class,'uui-spinner-container')]");
  private Element avergeMarkHeader = Element.byXpath(
      "//div[contains(@class,'table-header-row')]/div[3]");
  private Element absenceHeader = Element.byXpath(
      "//div[contains(@class,'table-header-row')]/div[4]");
  private Element allPeriodButton = Element
      .byXpath(ALL_PERIOD_BUTTON_PATTERN, getValueOf(REACT_TRAINING_ALL_PERIOD_BUTTON_TEXT));
  private Element student = Element.byXpath(
      "//*[contains(@class,'ui-table-row-container uui-table-row')]");
  private Element studentsNumber = Element.byXpath("./div[1]//span[1]");
  private Element studentsName = Element.byXpath("./div[1]//span[2]");
  private Element studentsAbsence = Element.byXpath("./div[4]/div");
  private Element studentsAverageMark = Element.byXpath("./div[3]/div");
  private Element calendarFirstDayOfSelectedPeriodInput = Element.byXpath(CALENDAR_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_FROM));
  private Element calendarLastDayOfSelectedPeriodInput = Element.byXpath(CALENDAR_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_TO));
  private Element weekPeriodButton = Element.byXpath(PERIOD_BUTTONS_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_WEEK_PERIOD));
  private Element twoWeekPeriodButton = Element.byXpath(PERIOD_BUTTONS_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_TWO_WEEKS_PERIOD));
  private Element monthPeriodButton = Element.byXpath(PERIOD_BUTTONS_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_MONTH_PERIOD));

  @Override
  public boolean isScreenLoaded() {
    return isTableHeaderDisplayed();
  }

  public boolean isTableHeaderDisplayed() {
    return headerFirstColumn.isDisplayed();
  }

  public ReactGroupJournalScreen waitTableHeaderForVisibility() {
    headerFirstColumn.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public String getTextFromBredCrumbs() {
    return breadCrumbsClassPath.getAttributeValue("innerText");
  }

  public String getGroupNameFromBredCrumbs() {
    String textFromBreadCrumbs = getTextFromBredCrumbs();
    return textFromBreadCrumbs.substring(textFromBreadCrumbs.lastIndexOf("\n") + 1);
  }

  public ReactTasksJournalScreen clickTaskJournalTab() {
    taskJournalTab.click();
    return new ReactTasksJournalScreen();
  }

  public ReactScheduleTabScreen clickScheduleTab() {
    scheduleTab.click();
    return new ReactScheduleTabScreen();
  }

  public ReactGraduateReportScreen clickGraduateReportTab() {
    graduateReportTab.waitForClickableAndClick();
    return new ReactGraduateReportScreen();
  }

  public ReactStudentCardPopUpScreen clickStudentByName(String studentName) {
    Element.byXpath(STUDENT_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName).click();
    return new ReactStudentCardPopUpScreen();
  }

  public boolean isBreadCrumbsDisplayed() {
    return breadCrumbs.isDisplayed();
  }

  public boolean isTrainersIconsDisplayed() {
    return trainersIcons.isDisplayed();
  }

  public List<String> getListOfNavigationMenuButtonsText() {
    return navigationMenuButtons.getElementsText();
  }

  public String getAddClassButtonText() {
    return addClassButton.getText();
  }

  public boolean isGroupTableGridDisplayed() {
    return groupTableGrid.isDisplayed();
  }

  public String getHeaderOfTheFirstColumnText() {
    return headerFirstColumn.getText();
  }

  public boolean isStudentsListDisplayed() {
    return studentsList.isAllElementsDisplayed();
  }

  public ReactGroupJournalScreen moveScrollBarToBeginning() {
    scrollBar.mouseOverCoordinatesAndClick(-scrollBar.getWidth() / 2, 0);
    return this;
  }

  public boolean isHeaderDatesDisplayed() {
    return headerDates.isDisplayed();
  }

  public boolean isHeaderClassTypeDisplayed() {
    return headerClassType.isDisplayed();
  }

  public String getHeaderAverageMarkText() {
    return headerAverageMark.getText();
  }

  public String getHeaderAbsenceText() {
    return headerAbsence.getText();
  }

  public boolean isClassInTableByDateDisplayed(LocalDate date) {
    return Element.byXpath(
            HEADER_DATE_FROM_GROUP_JOURNAL_TAB_BY_DATE_PATTERN, formatter.format(date))
        .isDisplayedShortTimeOut();
  }

  public ReactLessonInfoPopUpScreen clickInTableClassCardByDate(LocalDate date) {
    Element.byXpath(
        HEADER_DATE_FROM_GROUP_JOURNAL_TAB_BY_DATE_PATTERN, formatter.format(date)).click();
    return new ReactLessonInfoPopUpScreen();
  }

  public ReactGroupJournalScreen clickTheEntireSeries() {
    theEntireSeries.click();
    return this;
  }

  public ReactGroupJournalScreen clickConfirmDeleteButton() {
    confirmDeleteClassButton.click();
    return this;
  }

  public ReactGroupJournalScreen clickCloseNotificationButton() {
    closeNotificationButton.click();
    return this;
  }

  public ReactAddClassPopUpScreen clickAddClassButton() {
    addClassButton.mouseOverAndClick();
    return new ReactAddClassPopUpScreen();
  }

  public ReactJournalMarkPopUpScreen clickCellMarkByName(String studentName) {
    Element.byXpath(CELL_MARK_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName).click();
    return new ReactJournalMarkPopUpScreen();
  }

  public boolean isSymbolOfAbsenceInCellByNameDisplayed(String studentName) {
    return Element.byXpath(RED_SYMBOL_X_IN_CELL_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName)
        .isDisplayed();
  }

  public boolean isSymbolOfAbsenceInCellByNameDisplayedShortTimeOut(String studentName) {
    return Element.byXpath(RED_SYMBOL_X_IN_CELL_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName)
        .isDisplayedShortTimeOut();
  }

  public boolean isNotSymbolOfAbsenceInCellByNameDisplayed(String studentName) {
    return Element.byXpath(RED_SYMBOL_X_IN_CELL_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName)
        .isNotDisplayed();
  }

  public String getColorOfSymbolOfAbsenceInCellByName(String studentName) {
    return Element.byXpath(RED_SYMBOL_X_IN_CELL_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName)
        .getCssValue(FILL_CSS_PROPERTY);
  }

  public ReactGroupJournalScreen waitMarkOfAbsenceInCellByNameForDisappear(String studentName) {
    Element.byXpath(RED_SYMBOL_X_IN_CELL_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName)
        .waitForDisappear();
    return this;
  }

  public ReactGroupJournalScreen clickHeaderAverageMarkColumnAndWaitValueVisibility(
      String studentName) {
    avergeMarkHeader.click();
    Element.byXpath(AVERAGE_MARK_BY_STUDENT_PATTERN, studentName).waitForVisibility();
    return this;
  }

  public double getStudentAverageMark(String studentName) {
    double averageMark = 0.0;
    String averageMarkText = Element.byXpath(AVERAGE_MARK_BY_STUDENT_PATTERN, studentName)
        .getText();
    if (!averageMarkText.equals(EMPTY_AVERAGE_MARK)) {
      averageMark = Double.parseDouble(averageMarkText);
    }
    return averageMark;
  }

  public double calculateStudentAverageMark(String studentName) {
    List<String> marksStrings = Element.byXpath(MARKS_BY_STUDENT_PATTERN, studentName)
        .getElements().stream().map(x -> x.getAttributeValue("innerText")).collect(Collectors.toList());
    List<Integer> marksOfStudent = extractNumbersFromString(marksStrings);
    return (double) marksOfStudent.stream().mapToInt(mark -> mark).sum() / marksOfStudent.size();
  }

  public ReactGroupJournalScreen clickHeaderAbsenceColumnAndWaitValueVisibility(
      String studentName) {
    absenceHeader.click();
    Element.byXpath(ABSENCE_COUNT_BY_STUDENT_PATTERN, studentName).waitForVisibility();
    return this;
  }

  public int getStudentAbsenceCount(String studentName) {
    int absenceCount = 0;
    String absenceCountText = Element.byXpath(ABSENCE_COUNT_BY_STUDENT_PATTERN, studentName)
        .getText();
    if (!absenceCountText.equals(EMPTY_AVERAGE_MARK)) {
      absenceCount = Integer.parseInt(absenceCountText);
    }
    return absenceCount;
  }

  public int countStudentAbsentCells(String studentName) {
    Element absentCells = Element.byXpath(CELLS_MARKED_ABSENT_BY_STUDENT_PATTERN, studentName);
    if (absentCells.isPresent()) {
      return absentCells.getElements().size();
    }
    return 0;
  }

  public int getCurrentDateIndex() {
    int index = -1;
    for (int i = 0; i < headerDates.getElements().size(); i++) {
      if (isGroupJournalDateByIndexDisplayed(i) && getGroupJournalDateByIndex(i)
          .equals(LocalDate.now())) {
        index = i;
        break;
      }
    }
    return index;
  }

  public boolean isGroupJournalDateByIndexDisplayed(int index) {
    return Element.byXpath(HEADER_DATE_FROM_GROUP_JOURNAL_TAB_BY_INDEX_PATTERN, index)
        .isDisplayedNoWait();
  }

  public LocalDate getGroupJournalDateByIndex(int index) {
    return LocalDate
        .parse(
            Element.byXpath(HEADER_DATE_FROM_GROUP_JOURNAL_TAB_BY_INDEX_PATTERN, index).getText(),
            formatter);
  }

  public ReactJournalMarkPopUpScreen clickStudentMarkFieldByNameAndDateIndex(String student,
      int index) {
    Element.byXpath(STUDENT_MARK_FIELD_BY_NAME_AND_DATE_INDEX_PATTERN, student, index).click();
    return new ReactJournalMarkPopUpScreen();
  }

  public ReactGroupJournalScreen waitMarkForVisibility(int mark, String student, int index) {
    Element.byXpath(STUDENT_MARK_FIELD_BY_NAME_DATE_INDEX_AND_MARK_PATTERN, student, index, mark)
        .waitForVisibility();
    return this;
  }

  public ReactGroupJournalScreen waitAbsenceSymbolForVisibility(String student, int index) {
    Element.byXpath(STUDENT_ABSENCE_SYMBOL_BY_NAME_AND_DATE_INDEX_PATTERN, student, index)
        .waitForVisibility();
    return this;
  }

  public String getCellMarkByNameText(String studentName) {
    return Element.byXpath(CELL_MARK_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName).getText();
  }

  public boolean isCommentIconByStudentNameDisplayed(String studentName) {
    return Element.byXpath(CELL_COMMENT_ICON_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName)
        .isDisplayed();
  }

  public int getBreadCrumbsLevelCount() {
    return breadCrumbsLevel.getElements().size();
  }

  public boolean isHomeIconDisplayed() {
    return homeIcon.isDisplayed();
  }

  public String getTrainingNameFromGroupJournalText() {
    return trainingNameFromGroupJournal.getText();
  }

  public ReactGroupJournalScreen clickTrainingNameFromGroupJournal() {
    trainingNameFromGroupJournal.click();
    return this;
  }

  public boolean isGroupSwitcherDisplayed() {
    return groupSwitcher.isDisplayed();
  }

  public ReactTrainingScreen clickHomeIcon() {
    homeIcon.click();
    return new ReactTrainingScreen();
  }

  public String getDeleteSeriesWarningMessageText() {
    return warningMessage.getText();
  }

  public String getDescriptionNotificationText() {
    return descriptionNotification.getText();
  }

  public ReactGroupJournalScreen waitWarningMessageDeleteSeriesForVisibility() {
    warningMessage.waitForVisibility();
    return this;
  }

  public ReactGroupJournalScreen clickCommentIconByStudentName(String studentName) {
    Element.byXpath(CELL_COMMENT_ICON_FROM_GROUP_JOURNAL_TAB_BY_NAME_PATTERN, studentName).click();
    return this;
  }

  public String getPopUpCommentForStudentText(LocalDate date) {
    return Element.byXpath(POPUP_COMMENT_FOR_STUDENT_BY_DATE_PATTERN, formatter.format(date))
        .getText();
  }

  public String getPopUpCommentForTrainerText(LocalDate date) {
    return Element.byXpath(POPUP_COMMENT_FOR_TRAINER_BY_DATE_PATTERN, formatter.format(date))
        .getText();
  }

  public List<Element> getArrowsOfSwitcherBetweenGroupsList() {
    return arrowsOfSwitcherBetweenGroups.getElements();
  }

  public ReactGroupJournalScreen clickOnEnabledArrowSwitcherBetweenGroups() {
    if (getArrowsOfSwitcherBetweenGroupsList().get(1).getAttributeValue(Element.ATTRIBUTE_CLASS)
        .contains(DISABLED_ATTRIBUTE)) {
      getArrowsOfSwitcherBetweenGroupsList().get(0).click();
    } else {
      getArrowsOfSwitcherBetweenGroupsList().get(1).click();
    }
    return this;
  }

  public boolean isArrowsOfSwitcherBetweenGroupsListDisplayed() {
    return arrowsOfSwitcherBetweenGroups.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public List<String> getListStudentsText() {
    return studentsList.getElementsText();
  }

  public String getStudentsTextByIndex(int index) {
    return getListStudentsText().get(index);
  }

  public String getGroupNameFromSwitcherText() {
    return groupNameFromSwitcher.getText();
  }

  public ReactGroupJournalScreen clickTabOfNavigationMenuButtonsByName(String tabName) {
    Element.byXpath(TAB_OF_NAVIGATION_MENU_BUTTONS_BY_NAME_PATTERN, tabName).click();
    return this;
  }

  public ReactGroupJournalScreen waitForLoadingSpinnerInvisibility() {
    loadingSpinner.waitForDisappear();
    return this;
  }

  public ReactGroupJournalScreen clickAllPeriodButton() {
    allPeriodButton.waitForClickableAndClick();
    return this;
  }

  private List<Element> getStudentElements() {
    return student.getElements();
  }

  public List<Student> getStudentsDataList() {
    return getStudentElements().stream()
        .map(studentElement -> new Student()
            .withNumber(getStudentsNumber(studentElement))
            .withName(getStudentsName(studentElement))
            .withAbsence(getStudentAbsence(studentElement))
            .withAverageMark(getStudentsAverageMark(studentElement)))
        .collect(Collectors.toList());
  }

  private int getStudentsNumber(Element studentElement) {
    return Integer.parseInt(studentElement.findChild(studentsNumber).getText());
  }

  private String getStudentsName(Element studentElement) {
    return studentElement.findChild(studentsName).getText();
  }

  private int getStudentAbsence(Element studentElement) {
    return IntegerUtils.parseStringToInt(studentElement.findChild(studentsAbsence).getText());
  }

  private double getStudentsAverageMark(Element studentElement) {
    return DoubleUtils.parseStringToDouble(studentElement.findChild(studentsAverageMark).getText());
  }

  public ReactGroupJournalScreen clickWeekPeriodButton() {
    weekPeriodButton.waitForClickableAndClick();
    return this;
  }

  public String getWeekPeriodButtonColour() {
    return weekPeriodButton.getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public ReactGroupJournalScreen clickTwoWeekPeriodButton() {
    twoWeekPeriodButton.click();
    return this;
  }

  public ReactGroupJournalScreen clickMonthPeriodButton() {
    monthPeriodButton.click();
    return this;
  }

  public LocalDate getFirstDayOfSelectedPeriodFromCalendarField() {
    return StringUtils.getLocaleDateFromString(
        calendarFirstDayOfSelectedPeriodInput.getAttributeValue(HTML.Attribute.VALUE.toString()),
        PATTERN_DATE);
  }

  public LocalDate getLastDayOfSelectedPeriodFromCalendarField() {
    return StringUtils.getLocaleDateFromString(
        calendarLastDayOfSelectedPeriodInput.getAttributeValue(HTML.Attribute.VALUE.toString()),
        PATTERN_DATE);
  }

  public List<String> getTaskMarksByTaskName(String taskName) {
    return Element.byXpath(TASK_MARK_BY_TASK_ID_PATTERN, getTaskIdByTaskName(taskName))
        .getElementsText();
  }

  public int getTaskIdByTaskName(String taskName) {
    return Integer.parseInt(Element.byXpath(TEXT_LOCATOR_PATTERN,taskName)
        .getAttributeValue("data-id"));
  }
}
