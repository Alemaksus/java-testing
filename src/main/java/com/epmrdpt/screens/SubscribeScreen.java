package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class SubscribeScreen extends AbstractScreen {

  private static final String ATTRIBUTE_VALUE = "value";
  private static final String ATTRIBUTE_PLACEHOLDER = "placeholder";

  private Element subscribeContainer = Element.byClassName("modal-body");
  private Element closeSubscribeScreenButton = Element.byClassName("popup-title__close");
  private Element screenName = Element.byClassName("popup-title__name");
  private Element nameInput = Element.byName("userName");
  private Element surnameInput = Element.byName("userSurname");
  private Element emailInput = Element.byName("userEmail");
  private Element locationInput = Element.byId("locationCountries_chosen");
  private Element skillInput = Element.byId("skills_chosen");
  private Element infoText = Element.byCss("div.popup-subscribe__info-text.ng-binding");
  private Element submitButton = Element.byClassName("popup-subscribe__submit");
  private Element confirmationPopupInfoMessage = Element.byXpath(
      "//*[@ng-bind-html='resources.messageContent']");
  private Element confirmationPopup = Element.byXpath(
      "//*[contains(@class,'popup successful-popup')]");

  public SubscribeScreen waitUntilSubscribeScreenLoads() {
    subscribeContainer.waitForVisibility();
    return this;
  }

  @Override
  public boolean isScreenLoaded() {
    return subscribeContainer.isDisplayed();
  }

  public TrainingBlockScreen clickCloseSubscribeScreen() {
    closeSubscribeScreenButton.waitForClickable();
    closeSubscribeScreenButton.clickJs();
    return new TrainingBlockScreen();
  }

  public boolean isLocationInputDisplayed() {
    return locationInput.isDisplayed();
  }

  public boolean isSkillInputDisplayed() {
    return skillInput.isDisplayed();
  }

  public String getScreenNameText() {
    return screenName.getText();
  }

  public String getInfoText() {
    return infoText.getText();
  }

  public String getSubmitButtonAttributeValue() {
    return submitButton.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getEmailInputAttributePlaceholder() {
    return emailInput.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
  }

  public String getNameInputAttributePlaceholder() {
    return nameInput.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
  }

  public String getSurnameInputAttributePlaceholder() {
    return surnameInput.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
  }

  public String getEmailInputAttributeValue() {
    return emailInput.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getNameInputAttributeValue() {
    return nameInput.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getSurnameInputAttributeValue() {
    return surnameInput.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public SubscribeScreen waitEmailInputVisbility() {
    emailInput.waitForVisibility();
    return this;
  }

  public boolean isEmailFieldForRegisteredUserDisabled() {
    return emailInput.isDisabled();
  }

  public SubscribeScreen clickSubmitButton(){
    submitButton.clickJs();
    return this;
  }

  public String getSubscribeInfoMessageText(){
    return confirmationPopupInfoMessage.getText();
  }

  public boolean isConfirmationPopupDisplayed(){
    return confirmationPopup.isDisplayed();
  }
}
