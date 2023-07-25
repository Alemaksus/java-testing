package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_COPY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_PREVIEW_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_AUTOREPLY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_CREATE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_ATTENDEES_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_DRAFT_STATUS_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_PASSED_STATUS_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_PUBLISHED_STATUS_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_PUBLISH_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_GENERAL_INFO_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_SUCCESSFUL_STATUS_CHANGED_POP_UP_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CONFIRMATION_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.Objects;

public class ReactEventDetailsScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String STATUS_BUTTON_LOCATOR_PATTERN =
      TEXT_LOCATOR_PATTERN + "/preceding-sibling::div";

  private final Element previewButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_PREVIEW_BUTTON)));
  private final Element draftStatusButton = Element.byXpath(
      String.format(STATUS_BUTTON_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_DRAFT_STATUS_TEXT)));
  private final Element publishedStatusButton = Element.byXpath(
      String.format(STATUS_BUTTON_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_PUBLISHED_STATUS_TEXT)));
  private final Element passedStatusButton = Element.byXpath(
      String.format(STATUS_BUTTON_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_PASSED_STATUS_TEXT)));
  private Element saveChangesButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON)));
  private Element generalInfoButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_BUTTON)));
  private Element descriptionButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_BUTTON)));
  private Element autoreplyButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_AUTOREPLY_BUTTON)));
  private Element successfulPopup = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_SUCCESSFUL_STATUS_CHANGED_POP_UP_TEXT)));
  private final Element createCopyButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_COPY_BUTTON)));
  private Element createButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_CREATE_BUTTON));
  private Element okButtonInPopUp = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CONFIRMATION_BUTTON));
  private Element attendeesButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_ATTENDEES_BUTTON));
  private Element publishButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_PUBLISH_BUTTON));
  private final Element copyRegistrationLinkButton = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN + "/parent::div", "Copy registration link"));
  private final Element copyRegistrationLinkStatusMessage = Element.byXpath(
      "//div[contains(@class,'wrapper')]//div[text()]");

  @Override
  public boolean isScreenLoaded() {
    return previewButton.isDisplayed();
  }

  public ReactEventDetailsScreen waitSaveChangesButtonClickable() {
    saveChangesButton.waitForClickable(LONG_TIME_OUT_IN_SECONDS);
    return this;
  }

  public ReactEventDetailsScreen clickSaveChangesButton() {
    saveChangesButton.waitForClickableAndClick();
    return this;
  }

  public EventPreviewScreen clickPreviewButton() {
    previewButton.waitForClickableAndClick();
    return new EventPreviewScreen();
  }

  public ReactEventGeneralInfoScreen clickGeneralInfoButton() {
    generalInfoButton.waitForClickableAndClick();
    return new ReactEventGeneralInfoScreen();
  }

  public boolean isGeneralInfoButtonDisplayed() {
    return generalInfoButton.isDisplayed();
  }

  public ReactEventDescriptionScreen clickDescriptionButton() {
    descriptionButton.waitForClickableAndClick();
    return new ReactEventDescriptionScreen();
  }

  public boolean isDescriptionButtonDisplayed() {
    return descriptionButton.isDisplayed();
  }

  public ReactEventAutoreplyScreen clickAutoreplyButton() {
    autoreplyButton.waitForClickableAndClick();
    return new ReactEventAutoreplyScreen();
  }

  public ReactEventHeaderScreen clickCreateCopyButton() {
    createCopyButton.waitForClickableAndClick();
    return new ReactEventHeaderScreen();
  }

  public boolean isSaveChangesPopupDisplayed() {
    return successfulPopup.isDisplayed();
  }

  public ReactEventDetailsScreen waitSaveChangesPopupDisplayed() {
    successfulPopup.waitForVisibility();
    return this;
  }

  public boolean isDraftStatusButtonClickable() {
    return isStatusButtonClickable(draftStatusButton);
  }

  public boolean isPublishedStatusButtonClickable() {
    return isStatusButtonClickable(publishedStatusButton);
  }

  public boolean isPassedStatusButtonClickable() {
    return isStatusButtonClickable(passedStatusButton);
  }

  public boolean isPublishButtonDisplayed() {
    return publishButton.isDisplayed();
  }

  public ReactEventDetailsScreen clickPublishedStatusButton() {
    publishedStatusButton.click();
    return this;
  }

  public ReactEventDetailsScreen clickOkButtonInPopUp() {
    okButtonInPopUp.waitForClickableAndClick();
    return this;
  }

  public ReactEventDetailsScreen waitCreateButtonPresent() {
    createButton.waitForPresence();
    return this;
  }

  public ReactEventDetailsScreen clickCreateButton() {
    createButton.click();
    return this;
  }

  private boolean isStatusButtonClickable(Element statusButton) {
    return Objects.equals(
        statusButton.getCssValue("cursor"),
        "pointer");
  }

  public ReactEventAttendeesScreen clickButtonAttendees() {
    attendeesButton.click();
    return new ReactEventAttendeesScreen();
  }

  public ReactEventDetailsScreen waitCopyRegistrationLinkButtonClickable() {
    copyRegistrationLinkButton.waitForElementClassEnabled();
    return this;
  }

  public ReactEventDetailsScreen clickCopyRegistrationLinkButton() {
    copyRegistrationLinkButton.click();
    return this;
  }

  public boolean isCopyRegistrationLinkButtonPresent() {
    return copyRegistrationLinkButton.isPresent();
  }

  public String getCopyRegistrationLinkStatusMessageText() {
    return copyRegistrationLinkStatusMessage.getText();
  }
}
