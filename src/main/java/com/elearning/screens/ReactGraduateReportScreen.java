package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst
    .REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_LAB_IN_PROGRESS;
import static com.epmrdpt.framework.properties.LocalePropertyConst
    .REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_TRAINING_IN_PROGRESS;

import com.epmrdpt.bo.Student;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.utils.DoubleUtils;
import com.epmrdpt.utils.IntegerUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReactGraduateReportScreen extends AbstractScreen {

  private static final String STUDENT_FROM_GRADUATE_REPORT_PATTERN =
      "//*[@class='section-tabs__content']//span[contains(text(),'%s')]";
  private static final String STUDENT_STATUS_PATTERN =
      "//span[text()='%s']/ancestor::div[contains(@class,'uui-table-row')]"
          + "//div[contains(@class,'table-body-row-student-statuses')]";
  private static final String STUDENT_STATUS_IN_POPOVER_PATTERN =
      "//*[@class='react-tiny-popover-container']//*[contains(text(),'%s')]";

  private Element sectionTabs = Element.byClassName("section-tabs__content");
  private Element studentStatusPopover = Element.byXpath(
      "//*[@class='react-tiny-popover-container']//*[@color]/parent::div");
  private Element graduateReportTable = Element.byXpath(
      "//div[@class='section-tabs__content']");
  private Element breadCrumbsLevel = Element.byXpath(
      "//*[@class='bread-crumbs__left']/div/child::*");
  private Element breadCrumbsClassPath = Element.byXpath(
      "//div[@class='bread-crumbs__left']");
  private Element homeIcon = Element.byCss("a.home-page svg");
  private Element groupSwitcher = Element.byXpath("//div[text()='/']/parent::div");
  private Element trainingNameFromGraduateReportTab = Element.byXpath(
      "//*[contains(@class,' home-page')]/../following-sibling::div");
  private Element groupNameFromSwitcher = Element.byXpath(
      "//div[text()='/']/parent::div/a[contains(@class,' active')]");
  private Element scrollBar = Element.byXpath("//*[@class='uui-table-scroll-bar']");
  private Element firstStudentsCommentIconButton = Element.byXpath(
      "//*[local-name()='svg' and @role='button']");
  private Element student = Element.byXpath(
      "//*[contains(@class,'ui-table-row-container uui-table-row')]");
  private Element studentsNumber = Element.byXpath("./div[2]//span/preceding-sibling::div");
  private Element studentsName = Element.byXpath("./div[2]//span");
  private Element studentsAttendance = Element.byXpath(
      "./div[3]/div[1]/div[1]/div[1]/div[1]//div");
  private Element studentsAverageTaskMark = Element.byXpath(".//div[10]/div/div");
  private Element studentsAverageMark = Element.byXpath(".//div[11]/div/div");
  private Element spinnerContainer = Element.byXpath(
      "//div[contains(@class,'spinner-container')]");
  private Element statusHeader = Element.byXpath("//div[@data-id='student-status']");
  private Element commentHeader = Element.byXpath("//div[@data-id='comment']");
  private Element averageTaskMarkHeader = Element.byXpath("//div[@data-id='average-task']");
  private Element tasksTotalHeader = Element.byXpath("//div[@data-id='total']");
  private Element externalTaskHeader = Element.byXpath("//div[@data-id='external']");
  private Element onlineTaskPassedHeader = Element.byXpath("//div[@data-id='passed']");
  private Element onlineTaskExpiredHeader = Element.byXpath("//div[@data-id='expired']");
  private Element onlineTaskRejectedHeader = Element.byXpath("//div[@data-id='rejected']");
  private Element onlineTaskSubmittedHeader = Element.byXpath("//div[@data-id='submitted']");
  private Element onlineTaskAssignedHeader = Element.byXpath("//div[@data-id='assigned']");
  private Element onlineTaskHeader = Element.byXpath("//div[@data-id='online-task']/div");
  private Element offlineTaskHeader = Element.byXpath("//div[@data-id='offline-task']");
  private Element attendanceHeader = Element.byXpath("//div[@data-id='attendance']");
  private Element studentsHeader = Element.byXpath(
      "//div[@data-id='students']//div[contains(@class,'text')]");
  private Element averageMarkForClassesHeader = Element.byXpath(
      "//div[@data-id='average-mark']");
  private Element studentsList = Element.byXpath(
      "//div[contains(@class,'student-name-column')]//span");
  private Element checkBoxColumnHeader = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]//*[contains(@class,'uui-checkbox')]");

  @Override
  public boolean isScreenLoaded() {
    return isSectionTabsDisplayed();
  }

  public boolean isSectionTabsDisplayed() {
    return sectionTabs.isDisplayed();
  }

  public boolean isGroupSwitcherDisplayed() {
    return groupSwitcher.isDisplayed();
  }

  public boolean isGroupSwitcherInvisibility() {
    return groupSwitcher.waitForInvisibility();
  }

  public ReactGraduateReportScreen waitSectionTabsVisibility() {
    sectionTabs.waitForVisibility();
    return this;
  }

  public boolean isStudentStatusPopoverDisplayed() {
    return studentStatusPopover.isDisplayed();
  }

  public ReactGraduateReportScreen clickOnStudentStatus(String studentName) {
    Element.byXpath(STUDENT_STATUS_PATTERN, studentName).mouseOverAndClick();
    return this;
  }

  public ReactStudentCardPopUpScreen clickStudentByName(String studentName) {
    Element.byXpath(STUDENT_FROM_GRADUATE_REPORT_PATTERN, studentName).click();
    return new ReactStudentCardPopUpScreen();
  }

  public ReactGraduateReportScreen clickGroupNameFromSwitcher() {
    groupNameFromSwitcher.click();
    return this;
  }

  public ReactGraduateReportScreen clickTrainingNameFromGraduateReport() {
    trainingNameFromGraduateReportTab.click();
    return this;
  }

  public ReactGraduateReportScreen selectStatusLabInProgressInStudentStatusPopover() {
    Element.byXpath(STUDENT_STATUS_IN_POPOVER_PATTERN,
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_LAB_IN_PROGRESS)).click();
    return this;
  }

  public ReactGraduateReportScreen selectStatusTrainingInProgressInStudentStatusPopover() {
    Element.byXpath(STUDENT_STATUS_IN_POPOVER_PATTERN,
        getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_TRAINING_IN_PROGRESS)).click();
    return this;
  }

  public String getStudentStatusText(String studentName) {
    return Element.byXpath(STUDENT_STATUS_PATTERN, studentName).getText();
  }

  public int getBreadCrumbsLevelCount() {
    return breadCrumbsLevel.getElements().size();
  }

  public String getTextFromBredCrumbs() {
    return breadCrumbsClassPath.getAttributeValue("innerText");
  }

  public String getGroupNameFromBredCrumbs() {
    String textFromBreadCrumbs = getTextFromBredCrumbs();
    return textFromBreadCrumbs.substring(textFromBreadCrumbs.lastIndexOf("\n") + 1);
  }

  public ReactGraduateReportScreen waitUntilStudentsStatusChanged(String studentName,
      String studentStatus) {
    Element.byXpath(STUDENT_STATUS_PATTERN, studentName)
        .waitTextToBePresentInElement(studentStatus);
    return this;
  }

  public ReactGraduateReportScreen waitGraduateReportForVisibility() {
    graduateReportTable.waitForVisibility();
    return this;
  }

  public boolean isHomeIconDisplayed() {
    return homeIcon.isDisplayed();
  }

  public boolean isCheckboxColumnHeaderDisplayed() {
    return checkBoxColumnHeader.isDisplayed();
  }

  public String getStudentColumnHeaderText() {
    return studentsHeader.getText();
  }

  public String getAttendanceColumnHeaderText() {
    return attendanceHeader.getText();
  }

  public String getOfflineTasksColumnHeaderText() {
    return offlineTaskHeader.getText();
  }

  public String getOnlineTasksColumnHeaderText() {
    return onlineTaskHeader.getText();
  }

  public String getOnlineTasksAssignedColumnHeaderText() {
    return onlineTaskAssignedHeader.getText();
  }

  public String getOnlineTasksSubmittedColumnHeaderText() {
    return onlineTaskSubmittedHeader.getText();
  }

  public String getOnlineTasksRejectedColumnHeaderText() {
    return onlineTaskRejectedHeader.getText();
  }

  public String getOnlineTasksExpiredColumnHeaderText() {
    return onlineTaskExpiredHeader.getText();
  }

  public String getOnlineTasksPassedColumnHeaderText() {
    return onlineTaskPassedHeader.getText();
  }

  public String getExternalTasksColumnHeaderText() {
    return externalTaskHeader.getText();
  }

  public String getTasksTotalColumnHeaderText() {
    return tasksTotalHeader.getText();
  }

  public String getAverageTaskMarkColumnHeaderText() {
    return averageTaskMarkHeader.getText();
  }

  public String getAverageMarkForClassesColumnHeaderText() {
    return averageMarkForClassesHeader.getText();
  }

  public String getStudentStatusColumnHeaderText() {
    statusHeader.mouseOver();
    return statusHeader.getText();
  }

  public String getCommentColumnHeaderText() {
    commentHeader.mouseOver();
    return commentHeader.getText();
  }

  public ReactGraduateReportScreen clickAfterScrollBar() {
    scrollBar.mouseOverCoordinatesAndClick(scrollBar.getWidth() / 2 - 10, 0);
    return new ReactGraduateReportScreen();
  }

  public String getFirstStudentsCommentIconButtonColor() {
    return firstStudentsCommentIconButton.getCssValue("fill");
  }

  public ReactCommentPopUpScreen clickFirstStudentsCommentIconButton() {
    firstStudentsCommentIconButton.mouseOverAndClick();
    return new ReactCommentPopUpScreen();
  }

  public boolean isFirstStudentsCommentIconButtonDisplayed() {
    return firstStudentsCommentIconButton.isDisplayedShortTimeOut();
  }

  private List<Element> getStudentElements() {
    return student.getElements();
  }

  public List<Student> getStudentsDataList() {
    return getStudentElements().stream()
        .map(studentElement -> new Student()
            .withNumber(getStudentsNumber(studentElement))
            .withName(getStudentsName(studentElement))
            .withAttendance(getStudentAttendance(studentElement))
            .withAverageTaskMark(getStudentsAverageTaskMark(studentElement))
            .withAverageMark(getStudentsAverageMark(studentElement)))
        .collect(Collectors.toList());
  }

  private int getStudentsNumber(Element studentElement) {
    return Integer.parseInt(studentElement.findChild(studentsNumber).getText());
  }

  private String getStudentsName(Element studentElement) {
    return studentElement.findChild(studentsName).getText();
  }

  private List<Integer> getStudentAttendance(Element studentElement) {
    return Arrays.stream(studentElement
            .findChild(studentsAttendance)
            .getText()
            .split("/"))
        .map(IntegerUtils::parseStringToInt)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private double getStudentsAverageTaskMark(Element studentElement) {
    return DoubleUtils.parseStringToDouble(
        studentElement.findChild(studentsAverageTaskMark).getText());
  }

  private double getStudentsAverageMark(Element studentElement) {
    return DoubleUtils.parseStringToDouble(
        studentElement.findChild(studentsAverageMark).getText());
  }

  public ReactGraduateReportScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public String getStudentsTextByIndex(int index) {
    return getListStudentsText().get(index);
  }

  public List<String> getListStudentsText() {
    return studentsList.getElementsText();
  }
}
