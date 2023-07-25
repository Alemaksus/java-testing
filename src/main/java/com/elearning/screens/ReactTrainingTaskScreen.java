package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactTrainingTaskScreen extends AbstractScreen {

  private static final String TASK_TICKET_BY_NAME_AND_DEADLINE_PATTERN =
      "//div[text()='%s']/following-sibling::div/div[1]/div/span[2][text()='%s']";
  private static final String TASK_TICKET_BY_INDEX_PATTERN =
      "//div[@class='rbc-row-content']/div[@class='rbc-row'][%s]//div[@class='task-event']/div/div[1]";
  private static final String TASK_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_BY_INDEX_PATTERN + "/div/div[1]";
  private static final String CLOCK_ICON_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_BY_INDEX_PATTERN + "//*[@fill-rule='evenodd']";
  private static final String DEADLINE_TITLE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_BY_INDEX_PATTERN + "/div[2]/div//div/div/span[1]";
  private static final String DEADLINE_DATE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_BY_INDEX_PATTERN + "/div[2]/div/div/div/span[2]";
  private static final String SUBMITTED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_BY_INDEX_PATTERN + "/div[2]/div/div[2]/span";
  private static final String CHECKED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN =
      TASK_TICKET_BY_INDEX_PATTERN + "/div[2]//div/div[3]/span";
  private static final String TIME_CONTENT_PATTERN = "//span[@class='day-of-week-label']";

  private Element taskTab = Element.byCss("div.task-schedule-wrapper");
  private Element taskFieldOnTasksTab = Element.byXpath("//div[@class='task-event']");
  private Element daysOfWeekMode = Element.byXpath(TIME_CONTENT_PATTERN);
  private Element datesOfWeek = Element.byXpath(TIME_CONTENT_PATTERN + "/..//span[1]");
  private Element daysOfWeekMonthMode = Element.byXpath("//div[@class='rbc-header']//span");
  private Element monthGrid = Element.byXpath("//div[@class='rbc-month-view']");

  @Override
  public boolean isScreenLoaded() {
    return taskTab.isDisplayed();
  }

  public boolean isAllTasksOnTasksTabDisplayed() {
    return taskFieldOnTasksTab.isAllElementsDisplayed();
  }

  public ReactOnlineTaskInfoPopUpScreen clickTasksOnTasksTab() {
    taskFieldOnTasksTab.click();
    return new ReactOnlineTaskInfoPopUpScreen();
  }

  public ReactOnlineTaskInfoPopUpScreen clickTasksOnTasksTabByNameAndDeadLine(String taskName,
      String deadLine) {
    Element.byXpath(TASK_TICKET_BY_NAME_AND_DEADLINE_PATTERN, taskName, deadLine).click();
    return new ReactOnlineTaskInfoPopUpScreen();
  }

  public List<Element> getTaskFieldsOnTaskTabList() {
    return taskFieldOnTasksTab.getElements();
  }

  public int getTaskFieldsOnTaskTabCount() {
    return getTaskFieldsOnTaskTabList().size();
  }

  public String getTaskNameInTaskTicketByIndex(int index) {
    return Element.byXpath(TASK_NAME_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isClockIconInTaskTicketDisplayedByIndex(int index) {
    return Element.byXpath(CLOCK_ICON_IN_TASK_TICKET_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public String getDeadlineTitleInTaskTicketByIndex(int index) {
    return Element.byXpath(DEADLINE_TITLE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public String getDeadlineDateInTaskTicketByIndex(int index) {
    return Element.byXpath(DEADLINE_DATE_FIELD_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public String getSubmittedTaskStatusInTaskTicketByIndex(int index) {
    return Element.byXpath(SUBMITTED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public String getCheckedTaskStatusInTaskTicketByIndex(int index) {
    return Element.byXpath(CHECKED_TASK_STATUS_IN_TASK_TICKET_BY_INDEX_PATTERN, index).getText();
  }

  public List<String> getDaysOfWeekTextInTaskScheduleWeekMode() {
    return daysOfWeekMode.getElementsText();
  }

  public List<String> getDatesOfWeekTextInTaskScheduleWeekMode() {
    return datesOfWeek.getElementsText();
  }

  public List<String> getDaysOfWeekTextInTaskScheduleMonthMode() {
    return daysOfWeekMonthMode.getElementsText();
  }

  public boolean isMonthGridDisplayed() {
    return monthGrid.isDisplayed();
  }

  public int getDaysWeekModeInTaskScheduleSize() {
    return daysOfWeekMode.getElements().size();
  }

  public int getDaysMonthModeInTaskScheduleSize() {
    return daysOfWeekMonthMode.getElements().size();
  }
}
