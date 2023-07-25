package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NEW_PROGRAM_CHECKBOX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_MEMBERS_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_MEMBERS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_START_DATE_CHECKBOX;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactNotifyMembersPopUpScreen extends AbstractScreen {

  private final String POPUP_LOCATOR_PATTERN = "//div[contains(@class, 'uui-modal-window')]";
  private final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private final String BUTTON_LOCATOR_PATTERN = "//div[contains(@class,'button')]";
  private final String CHECKBOX_LOCATOR_PATTERN = "//div[contains(@class, 'checkbox')]/following::div[text()='%s']";

  private Element crossButton = Element.byXpath(POPUP_LOCATOR_PATTERN + "//*[name()='use']");
  private Element cancelButton = Element.byXpath(
      POPUP_LOCATOR_PATTERN + BUTTON_LOCATOR_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON));
  private Element notifyButton = Element.byXpath(
      POPUP_LOCATOR_PATTERN + BUTTON_LOCATOR_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_BUTTON));
  private Element commentField = Element.byXpath(POPUP_LOCATOR_PATTERN + "//textarea");
  private Element startDateCheckbox = Element.byXpath(
      POPUP_LOCATOR_PATTERN + CHECKBOX_LOCATOR_PATTERN,
      getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_START_DATE_CHECKBOX));
  private Element newProgramCheckbox = Element.byXpath(
      POPUP_LOCATOR_PATTERN + CHECKBOX_LOCATOR_PATTERN,
      getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NEW_PROGRAM_CHECKBOX));
  private Element notifyMembersTitle = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_MEMBERS_TITLE));
  private Element notifyMembersText = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_MEMBERS_TEXT));

  public ReactNotifyMembersPopUpScreen waitScreenLoading() {
    crossButton.waitForVisibility();
    return this;
  }

  public Boolean isCrossButtonDisplayed() {
    return crossButton.isDisplayed();
  }

  public Boolean isCommentFieldDisplayed() {
    return commentField.isDisplayed();
  }

  public Boolean isStartDateCheckBoxDisplayed() {
    return startDateCheckbox.isDisplayed();
  }

  public Boolean isNewProgramCheckBoxDisplayed() {
    return newProgramCheckbox.isDisplayed();
  }

  public Boolean isCancelButtonDisplayed() {
    return cancelButton.isDisplayed();
  }

  public String getCancelButtonText() {
    return cancelButton.getText();
  }

  public Boolean isNotifyButtonDisplayed() {
    return notifyButton.isDisplayed();
  }

  public String getNotifyButtonText() {
    return notifyButton.getText();
  }

  public Boolean isNotifyMembersTitleDisplayed() {
    return notifyMembersTitle.isDisplayed();
  }

  public Boolean isNotifyMembersTextDisplayed() {
    return notifyMembersText.isDisplayed();
  }

  public String getPopUpTitleText() {
    return notifyMembersTitle.getText();
  }

  public String getCommentFieldText() {
    return commentField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getStartDateText() {
    return startDateCheckbox.getText();
  }

  public String getNewProgramText() {
    return newProgramCheckbox.getText();
  }

  public ReactNotifyMembersPopUpScreen typeComment(String comment) {
    commentField.type(comment);
    return this;
  }

  public ReactNotifyMembersPopUpScreen clickStartDateCheckbox() {
    startDateCheckbox.click();
    return this;
  }

  public ReactNotifyMembersPopUpScreen clickNewProgramCheckbox() {
    newProgramCheckbox.click();
    return this;
  }

  public ReactDetailTrainingScreen clickNotifyButton() {
    notifyButton.click();
    return new ReactDetailTrainingScreen();
  }

  @Override
  public boolean isScreenLoaded() {
    return notifyMembersTitle.isDisplayed();
  }
}
