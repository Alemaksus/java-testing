package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_OFFLINE_TASK_ADD_HEAD_TRAINER_PLACEHOLDER;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactAddOfflineTaskPopUpScreen extends AbstractScreen {

  private static final String DAY_IN_CALENDAR_PATTERN = "//div[@class='uui-calendar-day'][text()='%s']";
  private static final String HEAD_TRAINER_PLACEHOLDER_PATTERN = "//*[@placeholder='%s']";
  private static final String ADD_OFFLINE_TASK_POP_UP_LOCATOR = "//div[contains(@class,'uui-modal-window')]";
  private static final String ADD_OFFLINE_TASK_FORM_LOCATOR =
      ADD_OFFLINE_TASK_POP_UP_LOCATOR + "//form";

  private Element headTrainerArrow = Element
      .byXpath(HEAD_TRAINER_PLACEHOLDER_PATTERN + "/../../descendant::div[2]",
          getValueOf(REACT_TRAINING_OFFLINE_TASK_ADD_HEAD_TRAINER_PLACEHOLDER));
  private Element headTrainerPlaceholder = Element
      .byXpath(HEAD_TRAINER_PLACEHOLDER_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_ADD_HEAD_TRAINER_PLACEHOLDER));
  private Element addTaskPopUpHeader = Element.byCss("div.modal-header");
  private Element addTaskPopUpForm = Element.byXpath(ADD_OFFLINE_TASK_FORM_LOCATOR);
  private Element onlineTaskToggle = Element
      .byXpath(ADD_OFFLINE_TASK_POP_UP_LOCATOR + "//div//div[@class='uui-switch-body']");
  private Element taskNameInput = Element
      .byXpath(ADD_OFFLINE_TASK_FORM_LOCATOR + "/div[1]/div[1]/div[1]//input");
  private Element dateInput = Element
      .byXpath(ADD_OFFLINE_TASK_FORM_LOCATOR + "/div[1]/div[2]/div[1]//input");
  private Element addOfflineTaskButton = Element
      .byXpath(ADD_OFFLINE_TASK_FORM_LOCATOR + "/div[2]/div[2]");
  private Element headTrainer = Element
      .byXpath("//*[@class='uui-popper']/descendant::div[5]");

  @Override
  public boolean isScreenLoaded() {
    return isAddTaskPopUpHeaderDisplayed() && isAddTaskPopUpFormDisplayed();
  }

  public boolean isAddTaskPopUpHeaderDisplayed() {
    return addTaskPopUpHeader.isDisplayed();
  }

  public boolean isHeadTrainerPlaceholderDisplayed() {
    return headTrainerPlaceholder.isDisplayedNoWait();
  }

  public boolean isAddTaskPopUpFormDisplayed() {
    return addTaskPopUpForm.isDisplayed();
  }

  public ReactAddOfflineTaskPopUpScreen waitAddTaskPopUpFormForVisibility() {
    addTaskPopUpForm.waitForVisibility();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen clickOnlineTaskToggle() {
    onlineTaskToggle.click();
    return new ReactAddOnlineTaskPopUpScreen();
  }

  public ReactAddOfflineTaskPopUpScreen typeOfflineTaskName(String taskName) {
    taskNameInput.type(taskName);
    return this;
  }

  public ReactAddOfflineTaskPopUpScreen clickDateInput() {
    dateInput.click();
    return this;
  }

  public ReactAddOfflineTaskPopUpScreen clickDayInCalendar(int day) {
    Element.byXpath(DAY_IN_CALENDAR_PATTERN, day).click();
    return this;
  }

  public ReactAddOfflineTaskPopUpScreen clickHeadTrainerArrow() {
    headTrainerArrow.click();
    return this;
  }

  public ReactAddOfflineTaskPopUpScreen clickHeadTrainerFromDropDown() {
    headTrainer.click();
    return this;
  }

  public ReactTasksJournalScreen clickAddOfflineTaskButton() {
    addOfflineTaskButton.clickJs();
    return new ReactTasksJournalScreen();
  }
}
