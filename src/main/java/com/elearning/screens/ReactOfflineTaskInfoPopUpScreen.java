package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReactOfflineTaskInfoPopUpScreen extends AbstractScreen {

  private static final String DEADLINE_DATE_PATTERN = "dd.MM.yyyy";

  private static final String OFFLINE_TASK_INFO_POP_UP_LOCATOR = "//div[@style]/div[2]/div[not(@style)]";
  private static final String MAIN_FORM_OF_OFFLINE_TASK_LOCATOR =
      OFFLINE_TASK_INFO_POP_UP_LOCATOR + "/div[2]";
  private static final String BUTTON_SECTION_LOCATOR =
      OFFLINE_TASK_INFO_POP_UP_LOCATOR + "/div[1]/div[2]";
  private static final String OFFLINE_TASK_INFO_POP_UP_TITLE_PATTERN = "//div[text()='%s']";
  private static final String OFFLINE_TASK_INFO_POP_UP_VALUE_PATTERN = "//div[text()='%s']/following-sibling::div";

  private Element taskNameField = Element
      .byXpath(OFFLINE_TASK_INFO_POP_UP_LOCATOR + "/div[1]/div[1]");
  private Element editTaskButton = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[1]");
  private Element deleteTaskButton = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[2]");
  private Element dateTitle = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_TITLE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_DATE));
  private Element dateValue = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_VALUE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_DATE));
  private Element averageMarkTitle = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_TITLE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_AVERAGE_MARK));
  private Element averageMarkValue = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_VALUE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_AVERAGE_MARK));
  private Element specificWeightTitle = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_TITLE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_SPECIFIC_WEIGHT));
  private Element specificWeightValue = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_VALUE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_SPECIFIC_WEIGHT));
  private Element taskDescriptionTitle = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_TITLE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_TASK_DESCRIPTION));
  private Element taskDescriptionValue = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_VALUE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_TASK_DESCRIPTION));
  private Element reviewerTitle = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_TITLE_PATTERN,
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_REVIEWER));
  private Element reviewerIcon = Element
      .byXpath(MAIN_FORM_OF_OFFLINE_TASK_LOCATOR + OFFLINE_TASK_INFO_POP_UP_VALUE_PATTERN + "//img",
          getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_REVIEWER));
  private Element confirmationDeleteTaskButton = Element
      .byXpath("//div[contains(@class,'uui-modal-window')]/div[3]/div[2]");

  @Override
  public boolean isScreenLoaded() {
    return taskNameField.isDisplayed();
  }

  public String getOfflineTaskName() {
    return taskNameField.getText();
  }

  public boolean isEditButtonDisplayed() {
    return editTaskButton.isDisplayed();
  }

  public boolean isDeleteButtonDisplayed() {
    return deleteTaskButton.isDisplayed();
  }

  public ReactOfflineTaskInfoPopUpScreen clickDeleteTaskButton() {
    deleteTaskButton.click();
    return this;
  }

  public boolean isDateTitleDisplayed() {
    return dateTitle.isDisplayed();
  }

  public LocalDate getDateValue() {
    return LocalDate.parse(dateValue.getText(),
        DateTimeFormatter.ofPattern(DEADLINE_DATE_PATTERN));
  }

  public boolean isAverageMarkTitleDisplayed() {
    return averageMarkTitle.isDisplayed();
  }

  public double getAverageMarkValue() {
    return Double.parseDouble(averageMarkValue.getText());
  }

  public boolean isSpecificWeightTitleDisplayed() {
    return specificWeightTitle.isDisplayed();
  }

  public int getSpecificWeightValue() {
    return Integer.parseInt(specificWeightValue.getText());
  }

  public boolean isTaskDescriptionTitleDisplayed() {
    return taskDescriptionTitle.isDisplayed();
  }

  public String getTaskDescriptionValue() {
    return taskDescriptionValue.getText();
  }

  public boolean isReviewerTitleDisplayed() {
    return reviewerTitle.isDisplayed();
  }

  public boolean isReviewerIconDisplayed() {
    return reviewerIcon.isDisplayed();
  }

  public boolean isConfirmationDeleteTaskButtonDisplayed() {
    return confirmationDeleteTaskButton.isDisplayed();
  }

  public ReactTasksJournalScreen clickConfirmationDeleteTaskButton() {
    confirmationDeleteTaskButton.click();
    return new ReactTasksJournalScreen();
  }
}
