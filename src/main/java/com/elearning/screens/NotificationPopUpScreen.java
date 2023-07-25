package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.CSS.Attribute;

public class NotificationPopUpScreen extends AbstractScreen {

  private static final String UNIVERSITY_PROGRAM_LOGO_URL = "Content/themes/Redesign/ico/";
  private static final String NOTIFICATION_MESSAGE_PATTERN = "//*[@class='autoreplyContainer']/p";
  private static final String NOTIFICATION_BODY_INFORMATION_TEXT_PATTERN = "//tbody/tr[5]//*[contains(text(),\"%s\")]";

  private Element universityProgramLogo = Element.byXpath("//td[contains(@class, 'logo')]/img");
  private Element universityProgramTitle = Element.byXpath("//td[contains(@class, 'title')]");
  private Element notificationMessageElement = Element.byXpath("//tbody/tr[not(@style)]//span");
  private Element notificationBodyInformation = Element.byXpath("//tbody/tr[5]");
  private Element notificationBodyReason = Element.byXpath("//tbody/tr[7]");
  private Element notificationBodyRequest = Element.byXpath("//tbody/tr[9]");
  private Element socialNetworkLink = Element.byXpath("//tbody/tr[12]//a");
  private Element facebookIconElement = Element.byXpath("//td//a[contains(@href,'facebook')]");
  private Element twitterIconElement = Element.byXpath("//td//a[contains(@href,'twitter')]");
  private Element linkedInIcon = Element.byXpath("//td//a[contains(@href,'linkedin.com')]");
  private Element vkIconElement = Element.byXpath("//td//a[contains(@href,'vk.com')]");
  private Element instagramIconElement = Element.byXpath("//td//a[contains(@href,'instagram')]");
  private Element youtubeIconElement = Element.byXpath("//td//a[contains(@href,'youtube')]");
  private Element notificationPopupCrossElement = Element.byXpath(
      "//div[@class='popup-title__close']");
  private Element notificationPopup = Element.byXpath(
      "//div[contains(@class,'popup--inbox-message')]");
  private Element linkToEnglishTest = Element.byXpath("//span[contains(text(),'English test')]//a");
  private Element linkToRegistrationTest = Element.byXpath(
      "//span[contains(@style, 'font-family')]/a");
  private Element examinatorPageEnglishTestTitle = Element.byXpath(
      "//div[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'english')]");
  private Element testingSystemPageLogo = Element.byXpath(
      "//div[contains(@class,'Header__header__title')]");
  private Element customNotificationContainer = Element.byClassName("autoreplyContainer");

  @Override
  public boolean isScreenLoaded() {
    return waitTrainingBodyExpanded().isUniversityProgramTitleDisplayed();
  }

  public NotificationPopUpScreen waitTrainingBodyExpanded() {
    universityProgramTitle.waitUntilAttributeChangeCssValue(Attribute.DISPLAY.toString(), "block");
    return this;
  }

  public boolean isUniversityProgramLogoDisplayed() {
    return universityProgramLogo.getAttributeValue("src").contains(UNIVERSITY_PROGRAM_LOGO_URL);
  }

  public boolean isUniversityProgramTitleDisplayed() {
    return universityProgramTitle.isDisplayed();
  }

  public boolean isTestingSystemPageLogoDisplayed() {
    return testingSystemPageLogo.isDisplayed();
  }

  public boolean isEnglishTestTitleDisplayed() {
    return examinatorPageEnglishTestTitle.isDisplayed();
  }

  private List<Element> getAllMessageElements() {
    return notificationMessageElement.getElements();
  }

  public String getFullMessage() {
    return getAllMessageElements().stream().map(element -> element.getText())
        .collect(Collectors.joining("\n"));
  }

  public String getTitleMessage() {
    return notificationMessageElement.getText();
  }

  public boolean isFacebookIconDisplayed() {
    return facebookIconElement.isDisplayed();
  }

  public boolean isTwitterIconDisplayed() {
    return twitterIconElement.isDisplayed();
  }

  public boolean isLinkedInIconDisplayed() {
    return linkedInIcon.isDisplayed();
  }

  public boolean isVkIconDisplayed() {
    return vkIconElement.isDisplayed();
  }

  public boolean isInstagramIconDisplayed() {
    return instagramIconElement.isDisplayed();
  }

  public boolean isYoutubeIconDisplayed() {
    return youtubeIconElement.isDisplayed();
  }

  public HeaderScreen closePopUpScreen() {
    notificationPopupCrossElement.clickJs();
    return new HeaderScreen();
  }

  public String getFullMessageWithOutTitleAndPatternMessage() {
    return Element.byXpath(NOTIFICATION_MESSAGE_PATTERN).getElements().stream()
        .map(Element::getText).collect(Collectors.joining());
  }

  public NotificationPopUpScreen waitForNotificationPopupVisibility() {
    notificationPopup.waitForVisibility();
    return this;
  }

  public String getNotificationBodyInformationText() {
    return notificationBodyInformation.getText();
  }

  public String getNotificationBodyReasonText() {
    return notificationBodyReason.getText();
  }

  public String getNotificationBodyRequestText() {
    return notificationBodyRequest.getText();
  }

  public boolean isSocialNetworkLinksDisplayed() {
    return socialNetworkLink.isAllElementsDisplayed();
  }

  public NotificationPopUpScreen clickLinkToEnglishTest() {
    linkToEnglishTest.mouseOver();
    linkToEnglishTest.clickJs();
    return this;
  }

  public ExaminatorScreen clickLinkToRegistrationTest() {
    linkToRegistrationTest.mouseOver();
    linkToRegistrationTest.clickJs();
    return new ExaminatorScreen();
  }

  public String getCustomNotificationPopUpContext() {
    return customNotificationContainer.getText();
  }

  public boolean isNotificationBodyInformationTextDisplayed(String message) {
    return Element.byXpath(NOTIFICATION_BODY_INFORMATION_TEXT_PATTERN, message).isDisplayed();
  }

  public String getNotificationBodyInformationText(String message) {
    return Element.byXpath(NOTIFICATION_BODY_INFORMATION_TEXT_PATTERN, message)
        .getAttributeValue("outerText");
  }
}
