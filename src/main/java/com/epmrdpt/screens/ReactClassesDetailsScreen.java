package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_TO_ClASSES_LIST_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_TO_TRAINING_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TRAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_ADD_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_DELETE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_SELECT_STUDENT_GROUP_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_SELECT_STUDENT_GROUP_SELECT_ALL_OPTION;
import static java.lang.String.format;
import static javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactClassesDetailsScreen extends AbstractScreen {

  private static final String LINK_BY_NAME_PATTERN = "//a[contains(text(),'%s')]";
  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";
  private static final String ACTIVE_BUTTON_COLOR = "rgba(0, 158, 204, 1)";
  private final Element hideClassCheckboxLabel = Element.byXpath("//div[@class='uui-input-label']");
  private final Element hideClassCheckbox = Element.byXpath(
      "//div[@class='uui-input-label']/preceding-sibling::div[@class='uui-checkbox']");

  private Element trainersBlock = Element.byXpath(TEXT_LOCATOR_PATTERN + "/../../..",
      getValueOf(REACT_TRAINING_CLASS_TRAINER));
  private Element trainingTitle = Element.byXpath(
      "(//a[@href='/']/following-sibling::div//*[contains(@class, 'text')])[2]");
  private Element hideClassCheckboxIcon = Element
      .byXpath("//div[@class='uui-input-label']/../../following-sibling::div");
  private Element tooltip = Element.byXpath("//div[@class='uui-tooltip-body']");
  private Element classNameInput = Element.byXpath("//input[1]");
  private Element saveChangesButton = Element.byXpath(
      format("//*[contains(@class, 'uui-button-box')]/div[text()='%s']",
          getValueOf(REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON)));
  private Element toClassesListLink = Element.byXpath("(//a[contains(@href,'training')])[last()]");
  private Element confirmationToasterCrossButton = Element.byXpath(
      "//*[contains(@class, 'uui-snackbar-item-wrapper')]//div[contains(@class, 'uui-button-box')]");
  private Element confirmationToaster = Element.byXpath(
      "//div[@class='uui-snackbar-item-wrapper-self']");
  private Element participantsInputPlaceholder = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_SELECT_STUDENT_GROUP_PLACEHOLDER));
  private Element participantsSelectAllOption = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_SELECT_STUDENT_GROUP_SELECT_ALL_OPTION));
  private Element addButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_ADD_BUTTON));
  private Element participantsStudentGroupNamesList = Element.byXpath(
      "//*[contains(@class,'uui-table-row-container') and contains(@class,'uui-table-row')]/../preceding-sibling::*/../div[2]/div[1]/div[1]//div[normalize-space(text())]");
  private Element participantsStudentGroupsPreselected = Element.byXpath(
      "//div[contains(@class,'button')]//div[@class='uui-caption']");
  private Element deleteTextButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_CLASSES_PARTICIPANTS_DELETE_BUTTON));
  private Element settingsParticipantsSectionButton = Element.byXpath(
      "(//div[@data-id='training-table'])[2]/../../preceding-sibling::*/../div[3]/div[2]/div[4]");
  private Element selectAllStudentGroupsCheckbox = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]//*[@class='uui-checkbox']");
  private Element popUpDeleteButton = Element.byXpath(format("//div[contains(@class, 'uui-caption') and text()='%s']",
      getValueOf(PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON)));

  @Override
  public boolean isScreenLoaded() {
    return isTrainersBlockDisplayed();
  }

  public ReactClassesDetailsScreen clickSelectAllStudentGroupsCheckbox() {
    selectAllStudentGroupsCheckbox.waitForClickableAndClick();
    return this;
  }

  public ReactClassesDetailsScreen waitSettingsButtonToChangeColor() {
      settingsParticipantsSectionButton.waitUntilAttributeGetsValue(
          BACKGROUND_COLOR.toString(), ACTIVE_BUTTON_COLOR, DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
      return this;
  }

  public ReactClassesDetailsScreen clickDeleteButton() {
    deleteTextButton.clickJs();
    return this;
  }

  public ReactClassesDetailsScreen clickPopUpDeleteButton() {
    popUpDeleteButton.waitForClickableAndClick();
    return this;
  }

  public ReactClassesDetailsScreen clickSettingsButton() {
    settingsParticipantsSectionButton.clickJs();
    return this;
  }

  public ReactClassesDetailsScreen clickParticipantsInputPlaceholder() {
      participantsInputPlaceholder.waitForClickableAndClick();
      return this;
  }

  public boolean isParticipantsStudentGroupPreselected() {
    return participantsStudentGroupsPreselected.waitForPresence().isDisplayed();
  }

  public ReactClassesDetailsScreen clickParticipantsSelectAllOption() {
    participantsSelectAllOption.waitForClickableAndClick();
    return this;
  }

  public ReactClassesDetailsScreen clickAddButton() {
    addButton.waitForClickableAndClick();
    return this;
  }

  public List<String> getStudentGroupsNamesFromSelectStudentGroupDDL() {
    return participantsStudentGroupsPreselected.getElementsText();
  }

  public List<String> getStudentGroupsNamesFromList() {
    return participantsStudentGroupNamesList.getElementsText();
  }

  public boolean isTrainersBlockDisplayed() {
    return trainersBlock.isDisplayed();
  }

  public ReactClassesDetailsScreen waitTitleForVisibility() {
    trainingTitle.waitForVisibility();
    return this;
  }

  public boolean isLinkByNameDisplayed(String linkName) {
    return Element.byXpath(LINK_BY_NAME_PATTERN, linkName).isDisplayed();
  }

  public ReactClassesScreen clickClassesListLink() {
    Element.byXpath(LINK_BY_NAME_PATTERN, getValueOf(REACT_CLASSES_SCREEN_TO_ClASSES_LIST_LINK))
        .click();
    return new ReactClassesScreen();
  }

  public ReactGroupJournalScreen clickToTrainingListLink() {
    Element.byXpath(LINK_BY_NAME_PATTERN, getValueOf(REACT_GROUP_DETAILS_SCREEN_TO_TRAINING_LINK))
        .click();
    return new ReactGroupJournalScreen();
  }

  public boolean isHideClassCheckboxIconDisplayed() {
    return hideClassCheckboxIcon.isDisplayed();
  }

  public String getHideClassCheckboxIconTooltipText() {
    return hideClassCheckboxIcon.getTooltipText(tooltip);
  }

  public ReactClassesDetailsScreen typeNewClassName(String name) {
    classNameInput.waitForClickableAndClick();
    classNameInput.clearInputUsingBackspace();
    classNameInput.type(name);
    return this;
  }

  public ReactClassesDetailsScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public ReactClassesScreen clickToClassesListLink() {
    toClassesListLink.waitForClickableAndClick();
    return new ReactClassesScreen();
  }

  public ReactClassesDetailsScreen clickOnConfirmationToasterCross() {
    confirmationToasterCrossButton.mouseOverAndClick();
    return this;
  }

  public ReactClassesDetailsScreen waitConfirmationToasterForVisibility() {
    confirmationToaster.waitForVisibility();
    return this;
  }

  public String getHideClassCheckboxLabelText() {
    return hideClassCheckboxLabel.getText();
  }

  public boolean isHideClassCheckboxPresent() {
    return hideClassCheckbox.isPresent();
  }
}
