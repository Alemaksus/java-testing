package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ADD_ASSIGNMENT_FOR_EVERYONE_POPUP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ADD_ASSIGNMENT_POPUP;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactAddAssignmentPopUpScreen extends AbstractScreen {

  private static final String ADD_ASSIGNMENT_POPUP_PATTERN = "//div[contains(text(), '%s')]/..";

  private Element addAssignmentForEveryonePopUpForm = Element.byXpath(ADD_ASSIGNMENT_POPUP_PATTERN,
      getValueOf(REACT_TRAINING_ADD_ASSIGNMENT_FOR_EVERYONE_POPUP));
  private Element addAssignmentPopUpForm = Element.byXpath(
      "//div[contains(text(), '%s') or contains(text(), '%s')]/..",
      getValueOf(REACT_TRAINING_ADD_ASSIGNMENT_POPUP),
      getValueOf(REACT_TRAINING_ADD_ASSIGNMENT_FOR_EVERYONE_POPUP));
  private Element assignButton = Element.byXpath("//div[@class='modal-footer']/div/div/div[2]");
  private Element deadlineNotificationCheckbox = Element.byXpath(
      "//div[contains(@class,'uui-checkbox')]/..");

  @Override
  public boolean isScreenLoaded() {
    return addAssignmentPopUpForm.isDisplayed();
  }

  public ReactAddAssignmentPopUpScreen waitAddAssignmentForEveryonePopUpFormForVisibility() {
    addAssignmentForEveryonePopUpForm.waitForVisibility();
    return this;
  }

  public ReactTasksJournalScreen clickAssignButton() {
    assignButton.click();
    return new ReactTasksJournalScreen();
  }

  public ReactAddAssignmentPopUpScreen clickDeadlineNotificationCheckbox() {
    deadlineNotificationCheckbox.click();
    return this;
  }
}
