package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_NOTIFICATION_HINT_SAVE_CHANGE_TO_COMPLETE_AC_LINKING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_ASSIGNMENT_DEADLINE_CHECKBOX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_CONTAINER_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_OK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_SET_DATE_IN_DEADLINE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_WARNING_FIELD;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.stream.Collectors;

public class ReactAssignmentContainerPopUpScreen extends AbstractScreen {

  protected static final String SELECT_ID_FROM_DDL_PATTERN = "//div[contains(text(),'%s')]";

  private final Element customDeadlineCheckboxChecked = Element.byXpath(
      "//div[text()='%s']/../div[contains(@class,'uui-checkbox') and contains(@class,'uui-checked')]",
      getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_DEADLINE_CHECKBOX));
  private final Element assignmentContainerPlaceholder = Element.byXpath(
      "(//div[contains(@class,'uui-input-box')]//input[contains(@class,'uui-placeholder')])[2]");
  private Element containerInput = Element.byXpath("//input[@placeholder='%s']",
      getValueOf(REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_CONTAINER_INPUT));
  private Element confirmationButton = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_OK_BUTTON));
  private Element setDateInDeadLineInput = Element.byXpath("//input[@placeholder='%s']",
      getValueOf(REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_SET_DATE_IN_DEADLINE_INPUT));
  private Element monthAndYearFromCalender = Element.byXpath(
      "//div[contains(@class,'datepickerheader')]");
  private Element setTimeInDeadLineInput = Element.byXpath("//input[@placeholder='HH:mm']");
  private Element notificationMessage = Element.byXpath("//div[contains(text(),'%s')]",
      getValueOf(DETAIL_TRAINING_NOTIFICATION_HINT_SAVE_CHANGE_TO_COMPLETE_AC_LINKING));
  private Element customDeadlineCheckbox = Element.byXpath(
      "//div[text()='%s']/../div[contains(@class,'uui-checkbox')]",
      getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_DEADLINE_CHECKBOX));
  private Element periodInDaysInput = Element.byXpath("//input[@type='number']");
  private Element warningRequiredField = Element.byXpath("//div[text()=\"%s\"]",
          getValueOf(REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_WARNING_FIELD));

  @Override
  public boolean isScreenLoaded() {
    return containerInput.isDisplayed();
  }

  public ReactAssignmentContainerPopUpScreen clickContainerInput() {
    containerInput.click();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen typeIdValue(String id) {
    containerInput.type(id);
    return this;
  }

  public ReactAssignmentContainerPopUpScreen clickSelectIdAndNameFromDDL(String id, String name) {
    Element.byXpath(String.format(SELECT_ID_FROM_DDL_PATTERN, id)).getElements().stream()
        .filter(e -> e.getText().contains(name)).collect(
        Collectors.toList()).get(0).waitForVisibility().click();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen clickSetDeadLineInput() {
    setDateInDeadLineInput.clickJs();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen typeDateInSetDeadLineInput(String date) {
    setDateInDeadLineInput.type(date);
    clickSetDeadLineInput();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen typeTimeInSetDeadLineInput(String time) {
    setTimeInDeadLineInput.clearInputUsingBackspace();
    setTimeInDeadLineInput.type(time);
    return this;
  }

  public boolean isNotificationMessageDisplayed() {
    return notificationMessage.isDisplayed();
  }


  public ReactAssignmentContainerPopUpScreen waitCalendarVisibility() {
    monthAndYearFromCalender.waitForVisibility();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen clickConfirmationButton() {
    confirmationButton.click();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen clickCustomDeadlineCheckbox() {
    customDeadlineCheckbox.click();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen typePeriodInDaysInput(String day) {
    periodInDaysInput.clearInputUsingBackspace();
    periodInDaysInput.type(day);
    return this;
  }

  public String getAssignmentContainerName() {
    return assignmentContainerPlaceholder.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isWarningFieldDisplayed() {
    return warningRequiredField.isDisplayed();
  }

  public String getDeadlineDate() {
    return setDateInDeadLineInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getDeadlineTime() {
    return setTimeInDeadLineInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getPeriodInDays() {
    return periodInDaysInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public boolean isCustomDeadlineCheckboxChecked() {
    return customDeadlineCheckboxChecked.isDisplayed();
  }
}

