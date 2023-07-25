package com.epmrdpt.screens;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElement;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElementEnum;
import javax.swing.text.html.CSS.Attribute;

public class FeedbackScreen extends AbstractScreen {

  private static final String EMAIL_ADDRESS_CSS_SELECTOR = "div.popup-feedback__email>span";
  private static final String COUNTRY_DROPDOWN_FIELD_PATTERN = "//div[@class='popup-feedback__country']//li[text()='%s']";
  private static final String COUNTRY_DDL_ITEM_PATTERN = "//div[@class='popup-feedback__country']//li[text()='%s']";
  private static final String CITY_DROPDOWN_FIELD_PATTERN =
      "//div[@class='popup-feedback__city']//div[@class='option-list']/div[contains(text(),'%s')]";
  private static final String CATEGORY_DROPDOWN_FIELD_PATTERN = "//div[@class='popup-feedback__summary']//span[text()='%s']";
  private static final String MESSAGE_FIELD_PATTERN = "//div[@class='popup-feedback__message']/textarea[@placeholder='%s']";
  private static final String IM_NOT_A_ROBOT_PATTERN = "//div[@class='rc-anchor-center-container']/label[text()=\"%s\"]";
  private static final String SEND_BUTTON_PATTERN = "//input[@type='submit' and @value='%s']";

  private Element feedbackModalWindow = Element.byClassName("popup-wrapper");
  private Element feedbackButton = Element.byCss("#root > div.ask-question > div");
  private Element emailAddressField = Element.byId("feedback-email");
  private Element countryDropdown = Element
      .byXpath("//div[@class='popup-feedback__country']/div[contains(@class,'chosen-container')]");
  private Element cityDropdown = Element.byXpath("//div[@class='popup-feedback__city']");
  private Element categoryDropdown = Element
      .byXpath("//div[@class='popup-feedback__summary']/div[2]");
  private Element categoryDDLItem = Element.byXpath("//div[@class='popup-feedback__summary']//li");
  private Element messageField = Element
      .byXpath("//div[@class='popup-feedback__message']/textarea");
  private Element sendButton = Element.byXpath("//input[@type='submit']");
  private Element recaptchaCheckbox = Element.byId("rc-anchor-container");
  private Element recaptchaFrame = Element.byXpath("//div[@id='recaptcha-container']/div//iframe");
  private Element emailAlertMessage = Element
      .byXpath("//div[@class='popup-feedback__email']//div[@ng-message='required']");
  private Element cityInputField = Element.byXpath("//input[@ng-change='searchCity()']");
  private Element iConfirmCheckbox = Element.byId("feedback-consent");

  @Override
  public boolean isScreenLoaded() {
    return feedbackModalWindow.isDisplayed();
  }

  public boolean isFeedbackButtonDisplayed() {
    return feedbackButton.isDisplayed();
  }

  public FeedbackScreen clickCountryDropdown() {
    countryDropdown.click();
    return this;
  }

  public FeedbackScreen clickCityDropdown() {
    cityDropdown.waitForClickable().click();
    return this;
  }

  public FeedbackScreen clickCategoryDropdown() {
    categoryDropdown.click();
    return this;
  }

  public FeedbackScreen clickCategoryDDLItemByIndex(int index) {
    categoryDDLItem.getElements().get(index).click();
    return this;
  }

  public int getCategoryDDLItemsCount() {
    return categoryDDLItem.getElements().size();
  }

  public FeedbackScreen typeMessage(String message) {
    messageField.type(message);
    return this;
  }

  public FeedbackScreen clickSendButton() {
    sendButton.click();
    return this;
  }

  public FeedbackScreen switchToRecaptchaFrame() {
    recaptchaFrame.switchToFrame();
    return this;
  }

  public String getEmailAlertMessage() {
    return emailAlertMessage.getText();
  }

  public String getEmailSpanBackgroundOfBefore() {
    return new PseudoElement().getPseudoElementCssPropertyValue(EMAIL_ADDRESS_CSS_SELECTOR,
        Attribute.BACKGROUND.toString(), PseudoElementEnum.BEFORE);
  }

  public String getEmailSpanBackgroundOfAfter() {
    return new PseudoElement().getPseudoElementCssPropertyValue(EMAIL_ADDRESS_CSS_SELECTOR,
        Attribute.BACKGROUND.toString(), PseudoElementEnum.AFTER);
  }

  public FeedbackScreen clickFeedbackButton() {
    feedbackButton.clickJs();
    return this;
  }

  public boolean isEmailAddressFieldDisplayed() {
    return emailAddressField.isDisplayed();
  }

  public boolean isCountryFieldDisplayed() {
    return Element
        .byXpath(String.format(COUNTRY_DROPDOWN_FIELD_PATTERN, LocaleProperties.getValueOf(
            LocalePropertyConst.FEEDBACK_COUNTRY_OTHER_COUNTRY))).isDisplayed();
  }

  public boolean isCountryDDLItemByTextDisplayed(String optionName) {
    return Element.byXpath(String.format(COUNTRY_DDL_ITEM_PATTERN, optionName)).isDisplayed();
  }

  public FeedbackScreen selectCountry(String nameCountry) {
    Element.byXpath(String.format(COUNTRY_DDL_ITEM_PATTERN, nameCountry)).click();
    return this;
  }

  public boolean isOtherCityFieldDisplayed() {
    return Element.byXpath(String.format(CITY_DROPDOWN_FIELD_PATTERN, LocaleProperties.getValueOf(
        LocalePropertyConst.FEEDBACK_CITY_OTHER_CITY))).isDisplayed();
  }

  public boolean isCityDDLItemByTextDisplayed(String optionName) {
    return Element.byXpath(String.format(CITY_DROPDOWN_FIELD_PATTERN, optionName)).isDisplayed();
  }

  public boolean isCategoryFieldDisplayed() {
    return Element
        .byXpath(String.format(CATEGORY_DROPDOWN_FIELD_PATTERN, LocaleProperties.getValueOf(
            LocalePropertyConst.FEEDBACK_CATEGORY_PLACEHOLDER))).isDisplayed();
  }

  public boolean isMessageFieldDisplayed() {
    return Element.byXpath(String.format(MESSAGE_FIELD_PATTERN, LocaleProperties.getValueOf(
        LocalePropertyConst.FEEDBACK_MESSAGE))).isDisplayed();
  }

  public boolean isImNotRobotCheckboxDisplayed() {
    return recaptchaCheckbox.isDisplayed();
  }

  public boolean isImNotRobotTextDisplayed(String optionName) {
    return Element.byXpath(IM_NOT_A_ROBOT_PATTERN, optionName).isDisplayed();
  }

  public boolean isSendButtonDisplayed() {
    return Element.byXpath(String.format(SEND_BUTTON_PATTERN, LocaleProperties.getValueOf(
        LocalePropertyConst.FEEDBACK_SEND))).isDisplayed();
  }

  public String getFeedbackButtonColor() {
    return feedbackButton.getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public String getHoveredFeedbackButtonColor() {
    return feedbackButton.getHoveredCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public FeedbackScreen typeCityName(String cityName) {
    cityInputField.type(cityName);
    return this;
  }

  public FeedbackScreen waitCaptchaVisibility() {
    recaptchaFrame.waitForVisibility();
    return this;
  }

  public boolean isIConfirmCheckboxDisplayed() {
    return iConfirmCheckbox.isDisplayed();
  }

  public FeedbackScreen clickIConfirmCheckbox() {
    iConfirmCheckbox.click();
    return  this;
  }
}
