package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_DATE_OF_CREATION_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_METHOD_OF_COMMUNICATION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_TECHNICAL_TEST_RESULT_TITLE;
import static javax.swing.text.html.CSS.Attribute.BACKGROUND_IMAGE;
import static javax.swing.text.html.HTML.Attribute.HREF;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.screens.ProfileScreen;

public class ReactStudentCardPopUpScreen extends AbstractScreen {

  private static final String URL_CLASS_VALUE = "url";
  private static final String POPUP_SECTION_PATTERN =
      "//div[contains(text(),\"%s\")]";
  private static final String SECTION_VALUE_PATTERN =
      "//div[div[contains(text(),\"%s\")]]//div[2]";
  private static final String SMALL_POPUP_ICON_PATTERN =
      "(//div[contains(@class,'user-card-box')]//div[@color])";
  private static final String LINKEDIN_LINK_PATTERN =
      "//a[contains(@href,'linkedin') and contains(@href, \"%s\")]";

  private Element studentCardInfoPopOver = Element.byXpath(
      "//div[contains(@class,'react-tiny-popover-container')]");
  private Element studentPhoto = Element.byXpath("//span[contains(@style,'background-image')]");
  private Element contactPhoneIcon = Element.byXpath(
      SMALL_POPUP_ICON_PATTERN + "[3]");
  private Element contactPhoneCopyButton = Element.byXpath(
      SMALL_POPUP_ICON_PATTERN + "[4]");
  private Element contactPhoneNumber = Element.byXpath(
      "(//div[contains(@class,'user-card-box')]//div//span)[2]");
  private Element studentNameTitle = Element.byXpath(
      "(//div[contains(@class,'react-tiny-popover-container')]//a[contains(@href,'Main')])[2]");
  private Element emailIcon = Element.byXpath(
      SMALL_POPUP_ICON_PATTERN + "[1]");
  private Element emailCopyButton = Element.byXpath(
      SMALL_POPUP_ICON_PATTERN + "[2]");
  private Element emailAddress = Element.byXpath(
      "(//div[contains(@class, 'user-card-box')]//div//span)[1]");
  private Element socialNetworksLogo = Element.byXpath(
      "(//div[contains(@class,'react-tiny-popover-container')]//a[@target='_blank'])[3]");
  private Element dateOfCreationTitle = Element.byXpath(
      POPUP_SECTION_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_DATE_OF_CREATION_SECTION));
  private Element dateOfCreationValue = Element.byXpath(
      SECTION_VALUE_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_DATE_OF_CREATION_SECTION));
  private Element methodOfCommunicationTitle = Element.byXpath(
      POPUP_SECTION_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_METHOD_OF_COMMUNICATION_TITLE));
  private Element methodOfCommunicationValue = Element.byXpath(
      SECTION_VALUE_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_METHOD_OF_COMMUNICATION_TITLE));
  private Element englishTestResultTitle = Element.byXpath(
      POPUP_SECTION_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE));
  private Element englishTestResultValue = Element.byXpath(
      SECTION_VALUE_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE));
  private Element technicalTestResultTitle = Element.byXpath(
      POPUP_SECTION_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_TECHNICAL_TEST_RESULT_TITLE));
  private Element technicalTestResultValue = Element.byXpath(
      SECTION_VALUE_PATTERN,
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_TECHNICAL_TEST_RESULT_TITLE));

  @Override
  public boolean isScreenLoaded() {
    return studentCardInfoPopOver.isDisplayed();
  }

  public boolean isStudentPhotoBackgroundImageDisplayed() {
    return studentPhoto.getCssValue(BACKGROUND_IMAGE.toString()).contains(URL_CLASS_VALUE);
  }

  public boolean isContactPhoneIconDisplayed() {
    return contactPhoneIcon.isDisplayed();
  }

  public String getContactPhoneNumberText() {
    return contactPhoneNumber.getText();
  }

  public boolean isContactPhoneCopyButtonDisplayed() {
    return contactPhoneCopyButton.isDisplayed();
  }

  public boolean isEmailIconDisplayed() {
    return emailIcon.isDisplayed();
  }

  public String getEmailAddressText() {
    return emailAddress.getText();
  }

  public boolean isEmailCopyButtonDisplayed() {
    return emailCopyButton.isDisplayed();
  }

  public boolean isStudentNameTitleDisplayed() {
    return studentNameTitle.isDisplayed();
  }

  public String getStudentNameTitleText() {
    return studentNameTitle.getText();
  }

  public boolean isStudentNameTitleHasNoLink() {
    return studentNameTitle.getAttributeValue(HREF.toString()).isEmpty();
  }

  public String getDateOfCreationTitleText() {
    return dateOfCreationTitle.getText();
  }

  public String getDateOfCreationValueText() {
    return dateOfCreationValue.getText();
  }

  public String getPreferredMethodOfCommunicationTitleText() {
    return methodOfCommunicationTitle.getText();
  }

  public boolean isSocialNetworksLogosDisplayed() {
    return socialNetworksLogo.isDisplayed();
  }

  public ProfileScreen clickOnStudentPhoto() {
    studentPhoto.click();
    return new ProfileScreen();
  }

  public String getMethodOfCommunicationByPhoneText() {
    return methodOfCommunicationValue.getText();
  }

  public String getEnglishTestResultTitleText() {
    return englishTestResultTitle.getText();
  }

  public String getEnglishTestResultText() {
    return englishTestResultValue.getText();
  }

  public String getTechnicalTestResultTitleText() {
    return technicalTestResultTitle.getText();
  }

  public String getTechnicalTestResultText() {
    return technicalTestResultValue.getText();
  }

  public boolean isLinkedinLinkPresent(String linkedinProfile) {
    return Element.byXpath(LINKEDIN_LINK_PATTERN, linkedinProfile)
        .isPresent();
  }
}
