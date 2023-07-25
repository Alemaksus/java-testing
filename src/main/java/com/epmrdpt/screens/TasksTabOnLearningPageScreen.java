package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

import java.util.List;
import java.util.regex.Pattern;

import static com.epmrdpt.framework.ui.element.Element.DISABLED_ATTRIBUTE;
import static javax.swing.text.html.CSS.Attribute.COLOR;

public class TasksTabOnLearningPageScreen extends AbstractScreen {

  private static final String GENERAL_TASKS_LOCATOR_PATTERN = "//div[contains(@class,'container')]"
      + "//div[contains(@class,'subject')]//div[contains(@class,'rd-table--desktop') and contains(@class,'ng-scope')]"
      + "//div[contains(@class,'subject__tasks-icon-%s')]";
  private static final String GENERAL_TASKS_MARK_LOCATOR_PATTERN = "/../..//div[@ng-if='!item.IsMarksHidden']";
  private static final String TRAINING_TABLE_PATTERN = "//div[@class='subject']/rd-table"
      + "//div[contains(@class,'rd-table--desktop')]";
  private static final String TASK_NAME_IN_URGENT_TAB_LOCATOR_PATTERN =
      "//div/text()[contains(.,'%s')]//..//..//div[contains(text(),'%s')]";
  private static final String TASKS_GROUP_BY_NAME_LOCATOR_PATTERN =
      "//div[@class='subject-info']/div[contains(@class,'subject-info__title') and text()='%s']";
  private static final String TASK_NAME_IN_ALL_TASKS_TAB_BY_GROUP_LOCATOR_PATTERN =
      "//div[text()='%s']/../..//div[contains(@class,'subject__tasks-icon-online')]"
          + "/parent::div[text()[contains(.,'%s')]]";
  private static final String TASK_MARKS_HIDDEN_LOCATOR_BY_GROUP =
      TASK_NAME_IN_ALL_TASKS_TAB_BY_GROUP_LOCATOR_PATTERN
          + "/following-sibling::div/span[contains(@class,'subject__tasks-item-mark--hidden')]";
  private static final String REGULAR_TASK_NAME_IN_ALL_TABS_LOCATOR = "//div[contains(@class,'subject__tasks')]/parent::div[text()[contains(.,'%s')]]";
  private static final String MARK_OF_TASK_FROM_GROUP_DROPDOWN_LOCATOR_PATTERN =
      "//div[text()='%s']/../../div[@class='subject__tasks']/.//span[contains(@ng-if,'Mark')"
          + " and not(contains(@*,'hidden'))]";
  private static final String HIDDEN_MARK_IN_URGENT_TAB_BY_TASK_NAME_LOCATOR_PATTERN =
      "(//div[contains(@class,'ng-binding') and contains(text(),'%s')]/..//div[@class='rd-table__cell']/i)[1]";

  private Element tasksTab = Element.byCss("a.tabs__info-tab[href*='Tasks']");
  private Element tasksSection = Element.byCss("div[class='subject']");
  private Element regularTaskMark = Element.byXpath(
      "//div[contains(@class,'container')]//div[contains(@class,'subject')]"
          + "//div[contains(@class,'rd-table--desktop') and contains(@class,'ng-scope')]//div[@ng-if='!item.IsMarksHidden'] ");
  private Element onlineTaskMark = Element.byXpath(
      String.format(GENERAL_TASKS_LOCATOR_PATTERN, "online") + GENERAL_TASKS_MARK_LOCATOR_PATTERN);
  private Element regularTaskField = Element.byXpath(
      "//div[contains(@class,'container')]//div[contains(@class,'subject')]//div[contains(@class,'rd-table--desktop')"
          + " and contains(@class,'ng-scope')]//div[@ng-repeat='item in getCurrentItems()']");
  private Element epamTrainingTab = Element
      .byXpath("//tabs[@tabs='tasksTabs']/div[contains(text(),'ЕРАМ') or contains(text(),'EPAM')]");
  private Element epamTrainingTaskTitle = Element.byCss("div.rd-table__cell.ng-binding");
  private Element urgentTab = Element.byXpath("//tabs[@tabs='tabs']/div[1]");
  private Element allTasksTab = Element.byXpath("//tabs[@tabs='tabs']/div[2]");
  private Element departmentAffiliateTab = Element.byXpath("//tabs[@tabs='tasksTabs']/div[1]");
  private Element groupLabel = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[1]/div[1]");
  private Element sortItem = Element
      .byXpath(TRAINING_TABLE_PATTERN + "//div[@class='rd-table__sorting-arrow']");
  private Element taskLabel = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[1]/div[2]");
  private Element startDateLabel = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[1]/div[3]");
  private Element deadlineLabel = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[1]/div[4]");
  private Element markLabel = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[1]/div[5]");
  private Element statusLabel = Element.byXpath(TRAINING_TABLE_PATTERN + "//div[1]/div[6]");
  private Element warningIcon = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[1]//i");
  private Element taskIcons = Element.byXpath(TRAINING_TABLE_PATTERN + "//div[1]/div[@ng-if]");
  private Element groupNames = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[@ng-repeat]/div[1]");
  private Element taskNames = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[@ng-repeat]/div[2]");
  private Element startDates = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[@ng-repeat]/div[3]");
  private Element deadLineDates = Element
      .byXpath(TRAINING_TABLE_PATTERN + "/div[@ng-repeat]/div[4]");
  private Element taskMarks = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[@ng-repeat]/div[5]");
  private Element taskStatus = Element.byXpath(TRAINING_TABLE_PATTERN + "/div[@ng-repeat]/div[6]");
  private Element statusIcon = Element.byXpath(TRAINING_TABLE_PATTERN +
      "/div[@ng-repeat]/div[6]/span[contains(@class,'subject-info__status-icon')]");
  private Element taskCountInAllTasksTab = Element
      .byXpath("//span[contains(@class,'subject-info__tasks-count')]");
  private Element paginationBlock = Element
      .byXpath(TRAINING_TABLE_PATTERN + "//div[@uib-pagination]");
  private Element nextPageInPaginationButton = Element
      .byXpath(TRAINING_TABLE_PATTERN + "//div[contains(@class,'pagination')]/li[last()]/a");
  private Element popUpWindow = Element.byCss(".modal-content");


  @Override
  public boolean isScreenLoaded() {
    return tasksSection.isDisplayed();
  }

  public TasksTabOnLearningPageScreen waitScreenLoading() {
    tasksSection.waitForVisibility();
    return this;
  }

  public TasksTabOnLearningPageScreen waitTaskSectionForVisibility() {
    tasksSection.waitForVisibility(LONG_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public TasksTabOnLearningPageScreen clickTasksTab() {
    tasksTab.click();
    return this;
  }

  public boolean isTasksTabDisplayed() {
    return tasksTab.isDisplayed();
  }

  public boolean isAllMarkForRegularTasksDisplayed() {
    return regularTaskMark.isAllElementsDisplayed();
  }

  public boolean isAllMarkForOnlineTasksDisplayed() {
    return onlineTaskMark.isAllElementsDisplayed();
  }

  private List<Element> getRegularTasksList() {
    return regularTaskField.getElements();
  }

  public int getRegularTasksListCount() {
    return getRegularTasksList().size();
  }

  public TasksPopUpScreenOnLearningPageScreen clickRegularTaskNameByIndex(int index) {
    getRegularTasksList().get(index).click();
    return new TasksPopUpScreenOnLearningPageScreen();
  }

  public String getEpamTrainingTitle() {
    return epamTrainingTaskTitle.getText();
  }

  public boolean isTaskByNameInUrgentTabDisplayed(String groupName, String taskName) {
    return Element
        .byXpath(TRAINING_TABLE_PATTERN + TASK_NAME_IN_URGENT_TAB_LOCATOR_PATTERN, groupName,
            taskName)
        .isDisplayedNoWait();
  }

  public TasksTabOnLearningPageScreen waitSubmittedStatusForVisibility(String groupName,
      String taskName) {
    Element.byXpath(TRAINING_TABLE_PATTERN + TASK_NAME_IN_URGENT_TAB_LOCATOR_PATTERN +
            "/../div[6]/span[contains(@class, 'submitted-status')]", groupName, taskName).
        waitForVisibility(SHORT_TIME_OUT_IN_SECONDS);
    return this;
  }

  public TasksPopUpScreenOnLearningPageScreen clickTaskByNameInUrgentTab(String groupName,
      String taskName) {
    Element.byXpath(TRAINING_TABLE_PATTERN + TASK_NAME_IN_URGENT_TAB_LOCATOR_PATTERN, groupName,
            taskName)
        .click();
    return new TasksPopUpScreenOnLearningPageScreen();
  }

  public boolean isUrgentTabDisplayed() {
    return urgentTab.isDisplayed();
  }

  public boolean isAllTasksTabDisplayed() {
    return allTasksTab.isDisplayed();
  }

  public String getUrgentTabText() {
    return urgentTab.getText();
  }

  public String getAllTasksTabText() {
    return allTasksTab.getText();
  }

  public boolean isDepartmentAffiliateTabDisplayed() {
    return departmentAffiliateTab.isDisplayed();
  }

  public boolean isEpamTrainingTabDisplayed() {
    return epamTrainingTab.isDisplayed();
  }

  public String getDepartmentAffiliateTabText() {
    return departmentAffiliateTab.getText();
  }

  public TasksTabOnLearningPageScreen clickDepartmentAffiliateTab() {
    departmentAffiliateTab.click();
    return this;
  }

  public String getEpamTrainingTabText() {
    return epamTrainingTab.getText();
  }

  public TasksTabOnLearningPageScreen clickEpamTrainingTab() {
    epamTrainingTab.click();
    return this;
  }

  public String getGroupLabelText() {
    return groupLabel.getText();
  }

  public boolean isGroupLabelDisplayed() {
    return groupLabel.isDisplayed();
  }

  public boolean isAllSortedItemDisplayed() {
    return sortItem.isAllElementsDisplayed();
  }

  public String getTaskLabelText() {
    return taskLabel.getText();
  }

  public boolean isTaskLabelDisplayed() {
    return taskLabel.isDisplayed();
  }

  public String getDeadlineLabelText() {
    return deadlineLabel.getText();
  }

  public boolean isDeadlineLabelDisplayed() {
    return deadlineLabel.isDisplayed();
  }

  public String getStartDateText() {
    return startDateLabel.getText();
  }

  public boolean isStartDateLabelDisplayed() {
    return startDateLabel.isDisplayed();
  }

  public String getMarkLabelText() {
    return markLabel.getText();
  }

  public boolean isMarkLabelDisplayed() {
    return markLabel.isDisplayed();
  }

  public String getStatusLabelText() {
    return statusLabel.getText();
  }

  public boolean isStatusLabelDisplayed() {
    return statusLabel.isDisplayed();
  }

  public String getDeadlineColor() {
    return deadlineLabel.getCssValue(COLOR.toString());
  }

  public boolean isWarningIconDisplayed() {
    return warningIcon.isDisplayed();
  }

  public boolean isAllTaskIconsDisplayed() {
    return taskIcons.isAllElementsDisplayed();
  }

  public boolean isAllGroupNamesDisplayed() {
    return groupNames.isAllElementsDisplayed();
  }

  public boolean isAllTaskNamesDisplayed() {
    return taskNames.isAllElementsDisplayed();
  }

  public boolean isAllStartDateDisplayed() {
    return startDates.isAllElementsDisplayed();
  }

  public boolean isAllDeadlineDisplayed() {
    return deadLineDates.isAllElementsDisplayed();
  }

  public boolean isAllTaskMarkDisplayed() {
    return taskMarks.isAllElementsDisplayed();
  }

  public boolean isTaskStatusDisplayed() {
    return taskStatus.isDisplayed();
  }

  public boolean isStatusIconDisplayed() {
    return statusIcon.isDisplayed();
  }

  public List<String> getTaskMarksList() {
    return taskMarks.getElementsText();
  }

  public TasksTabOnLearningPageScreen clickAllTasksTab() {
    allTasksTab.click();
    return this;
  }

  public TasksTabOnLearningPageScreen clickGroupByName(String groupName) {
    Element.byXpath(TASKS_GROUP_BY_NAME_LOCATOR_PATTERN, groupName).click();
    return this;
  }

  public TasksPopUpScreenOnLearningPageScreen clickTaskByNameInAllTasksTab(String groupName,
      String taskName) {
    Element.byXpath(TASK_NAME_IN_ALL_TASKS_TAB_BY_GROUP_LOCATOR_PATTERN, groupName, taskName)
        .click();
    return new TasksPopUpScreenOnLearningPageScreen();
  }

  public boolean isTaskCountInAllTasksTabDisplayed() {
    return taskCountInAllTasksTab.isDisplayed();
  }

  public boolean isTaskMarksHiddenIconDisplayed(String groupName, String taskName) {
    return Element.byXpath(TASK_MARKS_HIDDEN_LOCATOR_BY_GROUP, groupName, taskName).isDisplayed();
  }

  public boolean isMarkHiddenIconByTaskNameDisplayed(String taskName) {
    return Element.byXpath(HIDDEN_MARK_IN_URGENT_TAB_BY_TASK_NAME_LOCATOR_PATTERN, taskName).isDisplayed();
  }

  public TasksPopUpScreenOnLearningPageScreen clickRegularTaskByNameInAllTasksTab(String taskName) {
    Element.byXpath(REGULAR_TASK_NAME_IN_ALL_TABS_LOCATOR, taskName).click();
    return new TasksPopUpScreenOnLearningPageScreen();
  }

  public boolean isAllMarksFromDropDownEqualsPattern(Pattern pattern, String groupName) {
    return Element.byXpath(MARK_OF_TASK_FROM_GROUP_DROPDOWN_LOCATOR_PATTERN, groupName)
        .getElements()
        .stream()
        .allMatch(element -> pattern.matcher(element.getText()).find());
  }

  public TasksTabOnLearningPageScreen clickNextInPaginationButton() {
    nextPageInPaginationButton.click();
    return this;
  }

  public boolean isNextInPaginationButtonDisabled() {
    return nextPageInPaginationButton.isAttributePresent(DISABLED_ATTRIBUTE);
  }

  public boolean isPaginationBlockDisplayedShortTimeOut() {
    return paginationBlock.isDisplayedShortTimeOut();
  }

  public TasksTabOnLearningPageScreen waitPopUpWindowDisappear() {
    popUpWindow.waitForInvisibility();
    return this;
  }
}
