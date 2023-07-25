package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ADD_TASK_POPUP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_ADD_HEAD_TRAINER_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_TITLE_TASK_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_WARNING_MESSAGE;

public class ReactAddOnlineTaskPopUpScreen extends AbstractScreen {

  private static final String ADD_ONLINE_TASK_POP_UP_LOCATOR = "//div[contains(@class,'uui-modal-window')]";
  private static final String ADD_ONLINE_TASK_FORM_LOCATOR =
      ADD_ONLINE_TASK_POP_UP_LOCATOR + "/div[1]/div[1]/div[2]";
  private static final String HEAD_TRAINER_PLACEHOLDER_PATTERN = "//*[@placeholder='%s']";
  private static final String ADD_ONLINE_TASK_NAME_COUNT_PATTERN =
      "//*[@class='uui-label-top']/../following-sibling::div[contains(text(), '%s')]";
  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";

  private Element additionalTrainerWarningText = Element.byXpath(
      "//li[contains(text(), '%s')]", getValueOf(REACT_TRAINING_ONLINE_TASK_WARNING_MESSAGE));
  private Element headTrainerPlaceholder = Element
      .byXpath(HEAD_TRAINER_PLACEHOLDER_PATTERN,
          getValueOf(REACT_TRAINING_ONLINE_TASK_ADD_HEAD_TRAINER_PLACEHOLDER));
  private Element headTrainerArrow = Element
      .byXpath(HEAD_TRAINER_PLACEHOLDER_PATTERN + "//ancestor::div[2]//descendant::div[2]",
          getValueOf(REACT_TRAINING_ONLINE_TASK_ADD_HEAD_TRAINER_PLACEHOLDER));
  private Element addOnlineTaskPopUpHeader = Element.byCss("div.modal-header");
  private Element addOnlineTaskPopUpForm = Element.byXpath(ADD_ONLINE_TASK_FORM_LOCATOR);
  private Element taskNameInput = Element.byXpath(TEXT_LOCATOR_PATTERN
          + "//following-sibling::div//div[contains(@class, 'uui-input-box')]/input",
      getValueOf(REACT_TRAINING_ONLINE_TASK_TITLE_TASK_NAME));
  private Element addOnlineTaskButton = Element
      .byXpath(ADD_ONLINE_TASK_FORM_LOCATOR + "/div[7]/div[2]/div");
  private Element passMarkArrowTop = Element
      .byXpath("//div[text()='%s']//following-sibling::div//div[contains(@class, 'uui-icon')]",
          getValueOf(REACT_TRAINING_ADD_TASK_POPUP));
  private Element headTrainer = Element
      .byXpath("//*[@class='uui-popper']/descendant::div[5]");
  private Element spinnerOfLoading = Element.byXpath("//div[@class='modal-header']/following-sibling::*//"
      + "div[contains(@class,'uui-spinner-container')]");
  private Element onlineSwitchChecked = Element.byCss(".uui-checked.uui-switch-body");
  private Element warningTitle = Element.byXpath("//li[contains(text(), '*')]");
  private Element spinnerOfTrainersSynchronize = Element.byXpath("//div[contains(@class,'modal-window')]//*[@transform]");

  @Override
  public boolean isScreenLoaded() {
    return isAddOnlineTaskPopUpHeaderDisplayed() && isAddOnlineTaskPopUpFormDisplayed();
  }

  public boolean isWarningTitleDisplayed() {
    return warningTitle.isDisplayed();
  }

  public boolean isAddOnlineTaskPopUpHeaderDisplayed() {
    return addOnlineTaskPopUpHeader.isDisplayed();
  }

  public boolean isAddOnlineTaskPopUpFormDisplayed() {
    return addOnlineTaskPopUpForm.isDisplayed();
  }

  public boolean isHeadTrainerPlaceholderDisplayed() {
    return headTrainerPlaceholder.isDisplayedNoWait();
  }

  public ReactAddOnlineTaskPopUpScreen waitAdditionalTrainerWarningTextVisibility() {
    additionalTrainerWarningText.waitForVisibility();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen typeOnlineTaskName(String taskName) {
    taskNameInput.click();
    taskNameInput.type(taskName);
    Element.byXpath(ADD_ONLINE_TASK_NAME_COUNT_PATTERN, taskName.length()).waitForVisibility();
    return this;
  }

  public ReactTasksJournalScreen clickAddOnlineTaskButton() {
    addOnlineTaskButton.click();
    return new ReactTasksJournalScreen();
  }

  public ReactAddOnlineTaskPopUpScreen clickHeadTrainerArrow() {
    headTrainerArrow.click();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen clickHeadTrainerFromDropDown() {
    headTrainer.click();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen clickOnPassMarkArrowTop() {
    passMarkArrowTop.mouseOverAndClick();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen waitAddOnlineTaskButtonForClickable() {
    addOnlineTaskButton.waitForClickable();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen waitDataLoading() {
    spinnerOfLoading.waitForDisappear();
    waitTrainersSynchronize();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen waitOnlineSwitchCheckedForVisibility() {
    onlineSwitchChecked.waitForVisibility();
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen waitTrainersSynchronize() {
    spinnerOfTrainersSynchronize.waitUntilRequiredElementsAreInvisible(
        spinnerOfTrainersSynchronize.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public ReactAddOnlineTaskPopUpScreen waitForLoadingSpinnerInvisible() {
    spinnerOfLoading.waitForDisappear();
    return this;
  }
}
