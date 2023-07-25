package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.text.html.HTML.Attribute;

public class ReactOnlineTaskInfoPopUpScreen extends AbstractScreen {

  private static final String ASSIGNED_DATE_PATTERN = "dd.MM.yyyy";
  private static final String DEADLINE_DATE_PATTERN = "dd.MM.yyyy, HH:mm";

  private static final String ONLINE_TASK_INFO_POP_UP_LOCATOR = "//div[@style]/div[not(@class)]/div/div[not(@style)]";
  private static final String BUTTON_SECTION_LOCATOR =
      ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[1]/div[2]";

  private Element taskNameField = Element.byXpath(
      ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[1]//div[normalize-space(text())]");
  private Element groupNameField = Element.byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[2]/div");
  private Element assignToEveryoneButton = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[2]");
  private Element tooltipButtonText = Element.byCss("div.uui-tooltip-body");
  private Element editTaskButton = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[3]");
  private Element deleteTaskButton = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[4]/div");
  private Element deadlineClockIcon = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[3]//*[@fill-rule='evenodd']");
  private Element deadlineTitle = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[3]/div[1]//div/span[1]");
  private Element deadlineDateValue = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[3]/div[1]//div/span[2]");
  private Element submittedTaskStatus = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[3]/div[2]/span");
  private Element checkedTaskStatus = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[3]/div[3]/span");
  private Element assignedTitle = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]//div[1]/span[1]");
  private Element assignedDateValue = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]//div[1]/span[2]");
  private Element averageMarkTitle = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]/div[1]/div/div[1]/div[2]/span[1]");
  private Element averageMarkValue = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]//div[2]/span[2]");
  private Element attemptsTitle = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]/div[1]/div/div[1]/div[3]/span[1]");
  private Element attemptsValue = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]//div[3]/span[2]");
  private Element creatorTitle = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]/div[1]/div/div[2]/span");
  private Element creatorIcon = Element.byCss("div.task-event-reviewers-photo");
  private Element reviewersTitle = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]/div[1]/div/div[3]/span");
  private Element reviewersIcon = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]/div[1]/div/div[3]/div");
  private Element checkButton = Element
      .byXpath(
          "//div[@id='app']/following-sibling::div//div[@class='']/div/div[4]//div[2]/div[2]/div");
  private Element confirmationDeleteTaskButton = Element
      .byXpath("//div[contains(@class,'uui-modal-window')]/div[3]/div[2]");
  private Element specificWeightTitle = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]/div[1]/div/div[1]/div[4]/span[1]");
  private Element specificWeightValue = Element
      .byXpath(ONLINE_TASK_INFO_POP_UP_LOCATOR + "/div[4]//div[4]/span[2]");

  @Override
  public boolean isScreenLoaded() {
    return taskNameField.isDisplayed();
  }

  public ReactHomeTaskPopUpScreen clickCheckButton() {
    checkButton.click();
    return new ReactHomeTaskPopUpScreen();
  }

  public String getOnlineTaskName() {
    return taskNameField.getText();
  }

  public String getGroupName() {
    return groupNameField.getText();
  }

  public ReactOnlineTaskInfoPopUpScreen mouseOverAssignToEveryoneButton() {
    assignToEveryoneButton.mouseOver();
    return this;
  }

  public String getTooltipButtonText() {
    return tooltipButtonText.getText();
  }

  public boolean isEditTaskButtonDisplayed() {
    return editTaskButton.isDisplayed();
  }

  public ReactOnlineTaskInfoPopUpScreen waitEditTaskButtonForVisibility() {
    editTaskButton.waitForVisibility();
    return this;
  }

  public boolean isDeleteTaskButtonDisplayed() {
    return deleteTaskButton.isDisplayed();
  }

  public ReactOnlineTaskInfoPopUpScreen mouseOverDeleteTaskButton() {
    deleteTaskButton.mouseOver();
    return this;
  }

  public boolean isDeleteIconEnabled() {
    return deleteTaskButton.getAttributeValue(Attribute.CLASS.toString())
        .contains(Element.ENABLED_ATTRIBUTE);
  }

  public ReactOnlineTaskInfoPopUpScreen clickDeleteTaskButton() {
    deleteTaskButton.waitForClickableAndClick();
    return this;
  }

  public ReactAddAssignmentPopUpScreen clickAssignToEveryoneButton() {
    assignToEveryoneButton.click();
    return new ReactAddAssignmentPopUpScreen();
  }

  public boolean isDeadlineClockIconDisplayed() {
    return deadlineClockIcon.isDisplayed();
  }

  public String getDeadlineTitleText() {
    return deadlineTitle.getText();
  }

  public LocalDateTime getDeadlineDateTime() {
    return LocalDateTime.parse(deadlineDateValue.getText(),
        DateTimeFormatter.ofPattern(DEADLINE_DATE_PATTERN));
  }

  public String getSubmittedTaskStatusText() {
    return submittedTaskStatus.getText();
  }

  public String getCheckedTaskStatusText() {
    return checkedTaskStatus.getText();
  }

  public String getAssignedTitleText() {
    return assignedTitle.getText();
  }

  public LocalDate getAssignedDateValue() {
    return LocalDate.parse(assignedDateValue.getText(),
        DateTimeFormatter.ofPattern(ASSIGNED_DATE_PATTERN));
  }

  public String getAverageMarkTitleText() {
    return averageMarkTitle.getText();
  }

  public double getAverageMarkValue() {
    return Double.parseDouble(averageMarkValue.getText());
  }

  public String getAttemptsTitleText() {
    return attemptsTitle.getText();
  }

  public String getAttemptsText() {
    return attemptsValue.getText();
  }

  public String getCreatorTitleText() {
    return creatorTitle.getText();
  }

  public boolean isCreatorIconDisplayed() {
    return creatorIcon.isDisplayed();
  }

  public String getReviewersTitleText() {
    return reviewersTitle.getText();
  }

  public boolean isReviewersIconDisplayed() {
    return reviewersIcon.isDisplayed();
  }

  public ReactTasksJournalScreen clickConfirmationDeleteTaskButton() {
    confirmationDeleteTaskButton.waitForClickableAndClick();
    return new ReactTasksJournalScreen();
  }

  public boolean isConfirmationDeleteTaskButtonDisplayed() {
    return confirmationDeleteTaskButton.isDisplayed();
  }

  public ReactOnlineTaskInfoPopUpScreen waitOnlineTaskNameOnPopUpVisibility() {
    taskNameField.waitForVisibility();
    return this;
  }

  public ReactOnlineTaskInfoPopUpScreen moveTaskInfoPopUpToTheTopOfTheScreen() {
    checkButton.mouseDown();
    return this;
  }

  public String getSpecificWeightTitleText() {
    return specificWeightTitle.getText();
  }

  public int getSpecificWeightValue() {
    return Integer.parseInt(specificWeightValue.getText());
  }
}
