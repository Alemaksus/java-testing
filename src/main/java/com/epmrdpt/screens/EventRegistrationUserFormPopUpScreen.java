package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.BUTTON_REGISTRATION_TO_THE_EVENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_TO_EVENT_POP_UP_SCREEN_CITY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_TO_EVENT_POP_UP_SCREEN_COUNTRY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_TO_EVENT_POP_UP_SCREEN_NOTIFICATION_TEXT;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class EventRegistrationUserFormPopUpScreen extends AbstractScreen {

  private static final String COUNTRY_NAME_PATTERN = "//li[text()='%s']";
  private static final String CITY_NAME_PATTERN = "//div[contains(text(),'%s')]";
  private static final String DROPDOWN_OPTION_PATTERN = "//*[contains(@class,'Text-module_container')][text()='%s']";
  private static final String BUTTON_PATTERN = "//*[@class='uui-caption'][text()='%s']";

  private final Element titleName = Element.byXpath(
      "//div[@class = 'modal-title popup-title']/div");
  private final Element firstNameLabel = Element.byXpath(
      "//div[@class = 'popup-register__event__name']//label");
  private final Element lastNameLabel = Element.byXpath(
      "//div[@class = 'popup-register__event__surname']//label");
  private final Element emailLabel = Element.byXpath(
      "//div[@class = 'popup-register__event__email']//label");
  private final Element contactPhoneLabel = Element.byCss("[for = 'phone']");
  private final Element countryLabel = Element.byCss("[for = 'locationCountries']");
  private final Element cityLabel = Element.byCss("[for = 'locationCities']");
  private final Element surveyLabel = Element.byCss("[for = 'SurveyAnswer']");
  private final Element notificationText = Element.byXpath(
      String.format("//p[contains (text(), '%s')]",
          getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_NOTIFICATION_TEXT)));
  private final Element registerButton = Element.byXpath(String.format(
      BUTTON_PATTERN, getValueOf(BUTTON_REGISTRATION_TO_THE_EVENT)));
  private final Element firstNameInputField = Element.byCss("[name='userName']");
  private final Element lastNameInputField = Element.byCss("[name='userSurname']");
  private final Element emailInputField = Element.byCss("[name='userEmail']");
  private final Element contactPhoneInputField = Element.byXpath("//input[@id='phone']");
  private final Element countryPlaceholder = Element.byXpath(
      "//select[@id='locationCountries']/following-sibling::div/a/span");
  private final Element crossButton = Element.byCss(".popup-title__close");
  private final Element questionIcon = Element.byCss(".main-question-icon");
  private final Element notificationAboutPhoneNumber = Element.byXpath(
      "//label[@for='phone']//div//span");
  private final Element inputFieldForSurveyAnswer = Element.byXpath(
      "//textarea[contains(@class,'TextArea-module')]");
  private final Element countryDropDown = Element.byXpath
      (String.format("//*[text()='%s']/following::div[contains(@class,'PickerToggler')]",
          getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_COUNTRY_LABEL)));
  private final Element cityDropDown = Element.byXpath
      (String.format("//*[text()='%s']/following::div[contains(@class,'PickerToggler')]/input",
          getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_CITY_LABEL)));
  private final Element surveyAnswerInputField = Element.byXpath(
      "//textarea[contains(@class,'TextArea-module')]");
  private final Element okButtonInPopUp = Element.byXpath("//*[@class='uui-caption'][text()='Ok']");
  private final Element registerPopUpButton = Element.byXpath(
      "//*[contains(@class,'registraion-modal_save-bt')]");

  @Override
  public boolean isScreenLoaded() {
    return registerButton.isDisplayed();
  }

  public boolean isCrossButtonDisplayed() {
    return crossButton.isDisplayed();
  }

  public String getTextTitleName() {
    return titleName.getText();
  }

  public boolean isTitleNameDisplayed() {
    return titleName.isDisplayed();
  }

  public boolean isFirstNameDisplayed() {
    return firstNameLabel.isDisplayed();
  }

  public String getTextFirstNameLabel() {
    return firstNameLabel.getText();
  }

  public boolean isInputFieldDisplayed() {
    return firstNameInputField.isDisplayed();
  }

  public String getTextFirstNameFromInputField() {
    return firstNameInputField.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public String getTextLastNameLabel() {
    return lastNameLabel.getText();
  }

  public boolean isLastNameDisplayed() {
    return lastNameLabel.isDisplayed();
  }

  public boolean isInputFieldLastNameDisplayed() {
    return lastNameInputField.isDisplayed();
  }

  public String getTextLastNameFromInputField() {
    return lastNameInputField.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public String getTextEmailLabel() {
    return emailLabel.getText();
  }

  public boolean isEmailLabelDisplayed() {
    return emailLabel.isDisplayed();
  }

  public boolean isInputFieldEmailDisplayed() {
    return emailInputField.isDisplayed();
  }

  public String getTextEmailFromInputField() {
    return emailInputField.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public String getTextContactPhoneLabel() {
    return contactPhoneLabel.getText();
  }

  public boolean isContactPhoneLabelDisplayed() {
    return contactPhoneLabel.isDisplayed();
  }

  public boolean isInputFieldContactPhoneDisplayed() {
    return contactPhoneInputField.isDisplayed();
  }

  public String getTextContactPhoneFromInputField() {
    return contactPhoneInputField.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public boolean isQuestionIconDisplayed() {
    return questionIcon.isDisplayed();
  }

  public String getTextNotificationAboutPhoneNumber() {
    questionIcon.mouseOver();
    return notificationAboutPhoneNumber.getText();
  }

  public String getTextCountryLabel() {
    return countryLabel.getText();
  }

  public boolean isCountryLabelDisplayed() {
    return countryLabel.isDisplayed();
  }

  public String getCountryPlaceholderText() {
    return countryPlaceholder.getText();
  }

  public String getTextCityLabel() {
    return cityLabel.getText();
  }

  public boolean isCityLabelDisplayed() {
    return cityLabel.isDisplayed();
  }

  public String getCityPlaceholderAttributeValue() {
    return cityDropDown.getAttributeValue("placeholder");
  }

  public String getTextSurveyQuestion() {
    return surveyLabel.getText();
  }

  public boolean isSurveyDisplayed() {
    return surveyLabel.isDisplayed();
  }

  public boolean isInputFieldForSurveyAnswerDisplayed() {
    return inputFieldForSurveyAnswer.isDisplayed();
  }

  public String getNotificationText() {
    return notificationText.getText();
  }

  public boolean isNotificationTextIsDisplayed() {
    return notificationText.isDisplayed();
  }

  public boolean isRegisterButtonDisplayed() {
    return registerButton.isDisplayed();
  }

  public String getRegisterButtonText() {
    return registerButton.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public EventRegistrationUserFormPopUpScreen clickCountryDropDown() {
    countryDropDown.click();
    return this;
  }

  public EventRegistrationUserFormPopUpScreen clickCountryItemByName(String name) {
    Element.byXpath(String.format(COUNTRY_NAME_PATTERN, name)).click();
    return this;
  }

  public EventRegistrationUserFormPopUpScreen clickCityDropDown() {
    cityDropDown.waitForClickableAndClick();
    return this;
  }

  public EventRegistrationUserFormPopUpScreen clickCityItemByName(String name) {
    Element.byXpath(String.format(CITY_NAME_PATTERN, name)).click();
    return this;
  }

  public EventRegistrationUserFormPopUpScreen typeSurveyAnswerByName(String name) {
    surveyAnswerInputField.type(name);
    return this;
  }

  public EventPreviewScreen clickRegistrationButtonAndSuccessPopUp() {
    registerPopUpButton.click();
    okButtonInPopUp.waitForClickableAndClick();
    return new EventPreviewScreen();
  }

  public EventRegistrationUserFormPopUpScreen chooseCountryName(String countryName) {
    Element.byXpath(String.format(DROPDOWN_OPTION_PATTERN, countryName)).click();
    return this;
  }

  public EventRegistrationUserFormPopUpScreen chooseCityName(String cityName) {
    Element.byXpath(String.format(DROPDOWN_OPTION_PATTERN, cityName)).click();
    return this;
  }

  public EventRegistrationUserFormPopUpScreen typeSurveyAnswer(String surveyAnswer) {
    inputFieldForSurveyAnswer.type(surveyAnswer);
    return this;
  }
}
