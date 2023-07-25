package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_SET_DATE_IN_DEADLINE_INPUT;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactParticipantsTrainingAssignACPopUpScreen extends AbstractScreen {

  private static final String INPUT_BY_PLACEHOLDER_PATTERN = "//input[@placeholder='%s']";
  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";

  private final Element assignRegistrationTestPopUp = Element.byXpath(
      "//div[contains(@class,'uui-modal-window')]");
  private final Element assignButton = Element.byXpath("//div[contains(@class,'assign-btn')]");
  private final Element dateInput = Element.byXpath(String.format(INPUT_BY_PLACEHOLDER_PATTERN,
      getValueOf(REACT_ASSIGNMENT_CONTAINER_POP_UP_SCREEN_SET_DATE_IN_DEADLINE_INPUT)));
  private final Element confirmActionPopUpTitle = Element.byXpath(TEXT_LOCATOR_PATTERN,
      "Confirm action");
  private final Element confirmActionPopUpYesButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Yes");

  @Override
  public boolean isScreenLoaded() {
    return assignButton.isDisplayed();
  }

  public ReactParticipantsTrainingScreen clickAssignButton() {
    assignButton.click();
    return new ReactParticipantsTrainingScreen();
  }

  public ReactDetailTrainingCalendarScreen clickCalendarInput() {
    dateInput.click();
    return new ReactDetailTrainingCalendarScreen();
  }

  public String getDateValue() {
    return dateInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public boolean isAssignRegistrationTestPopUpClosed() {
    return assignRegistrationTestPopUp.isAbsent();
  }

  public boolean isConfirmActionPopUpOpened() {
    return confirmActionPopUpTitle.isDisplayed();
  }

  public ReactParticipantsTrainingScreen clickYesButton() {
    confirmActionPopUpYesButton.click();
    return new ReactParticipantsTrainingScreen();
  }
}
