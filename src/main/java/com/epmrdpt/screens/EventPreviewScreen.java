package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.BUTTON_CANCEL_REGISTRATION_TO_THE_EVENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.BUTTON_REGISTRATION_TO_THE_EVENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENT_PREVIEW_SCREEN_JOIN_ONLINE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENT_RECORDING_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LOCALE_LANGUAGE_ACRONYM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_TO_EVENT_POP_UP_SCREEN_SUBMIT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SEE_AGENDA_BUTTON;
import static com.epmrdpt.utils.StringUtils.getLocaleDateFromString;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import javax.swing.text.html.HTML.Attribute;

public class EventPreviewScreen extends AbstractScreen {

  private static final String DATE_PATTERN = "d MMM yyyy";
  private static final String EVENT_SPEAKER_NAME_PATTERN = "(//*[contains(@class,'speaker-content_name')])[%s]";
  private static final String EVENT_SPEAKER_JOB_TITLE_PATTERN = "(//*[contains(@class,'speaker-content_position')])[%s]";
  private static final String EVENT_SPEAKER_DESCRIPTION_PATTERN = "(//*[contains(@class,'speaker-content_description-text')])[%s]";
  private static final String BUTTON_PATTERN = "//*[@class='uui-caption'][text()='%s']";
  private static final String LINK_BY_NAME_PATTERN = "//div[contains(text(),'%s')]";
  private static final String ATTRIBUTE_SRC = Attribute.SRC.toString();
  private static final String JOIN_US_DESCRIPTION = ("//*[contains(@class,'converter_check-mark')]/p[text()='%s']");
  private static final String EVENT_SPEAKER_NAME = "//*[contains(@class,'speaker-content_name')][text()='%s']";
  private static final String INNER_TEXT_ATTRIBUTE = "innerText";

  private final Element eventName = Element.byXpath(
      "//*[contains(@class,'event-right_name')]");
  private final Element eventStartDate = Element.byXpath(
      "//*[contains(@class,'tags_tags-item__')]");
  private final Element eventLocation = Element.byXpath(
      "//*[contains(@class,'header_single-event-right_location')]//span");
  private final Element eventFormat = Element.byXpath(
      "//*[contains(@class,'tags_tags-item__')][2]");
  private final Element eventLanguage = Element.byXpath(
      "//*[contains(@class,'tags_tags-item__')][3]");
  private final Element eventAboutBlock = Element.byXpath(
      "//*[contains(@class,'event-description_event_description-left__')]");
  private final Element eventJoinUsBlock = Element.byXpath(
      "//*[contains(@class,'join-us_join-us__')]");
  private final Element eventWhatsOnDescriptionBlock = Element.byXpath(
      "//*[contains(@class,'whats-on-content_text')]");
  private final Element cancelRegisterButton = Element.byXpath(
      "//*[contains(@class,'cancel-registration-button')]");
  private final Element registerButton = Element.byXpath(String.format(
      BUTTON_PATTERN, getValueOf(BUTTON_REGISTRATION_TO_THE_EVENT)));
  private final Element seeAgendaButton = Element.byXpath(String.format(
      BUTTON_PATTERN, getValueOf(SEE_AGENDA_BUTTON)));
  private final Element cancelButton = Element.byXpath(String.format(
      BUTTON_PATTERN, getValueOf(BUTTON_CANCEL_REGISTRATION_TO_THE_EVENT)));
  private final Element joinOnlineButton = Element.byXpath(String.format(
      BUTTON_PATTERN, getValueOf(EVENT_PREVIEW_SCREEN_JOIN_ONLINE_BUTTON)));
  private final Element videoPlayer = Element.byXpath(
      String.format(LINK_BY_NAME_PATTERN, getValueOf(EVENT_RECORDING_TITLE)));
  private final Element cancelReasonInputFieldInPopUp = Element.byXpath(
      "//textarea[contains(@class,'TextArea-module')]");
  private final Element submitButtonInPopUp = Element.byXpath
      (String.format(BUTTON_PATTERN,
          getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_SUBMIT_BUTTON)));
  private final Element navigationBlockContainer = Element.byXpath(
      "//div[contains(@class,'navigation-item')]");
  private final Element navigationOptionLabelContainer = Element.byXpath(
      "//*[@class='content-block']//*[contains(@class,'-header') or contains(@class,'event_description-left_header')]");
  private final Element meetOurSpeakersLabel = Element.byXpath(
      "//*[contains(@class,'speakers-header')]");
  private final Element eventTime = Element.byXpath(
      "//div[@class='event-details-header--date']");
  private final Element editButton = Element.byXpath("//*[contains(@href,'/Management#!/event/')]");
  private final Element eventRecording = Element.byXpath(
      "//div[contains(@class,'event-recording')]");
  private final Element sliderArrowNextButton = Element.byXpath(
      "//button[contains(@class,'slider-arrow--next')]");
  private final Element iframeForCurrentEventVideoUrl = Element.byXpath(
      "//div[@class='owl-item']/iframe");
  private final Element aboutTheEventLabel = Element.byXpath(
      "//*[contains(@class,'event_description-left_header')]");
  private final Element joinUsLabel = Element.byXpath(
      "//*[contains(@class,'join-us_join-us-header')]");
  private final Element whatsOnLabel = Element.byXpath("//*[contains(@class,'whats-on-header')]");
  private final Element agendaLabel = Element.byXpath("//*[contains(@class,'agenda-title')]");
  private final Element aboutTheEventDescriptionField = Element.byXpath(
      "//*[contains(@class,'event_description-left_text')]");
  private final Element joinUsDescriptionField = Element.byXpath(
      "//*[contains(@class,'converter_check-mark')]/p");
  private final Element agendaDescriptionField = Element.byXpath
      ("//*[contains(@class,'agenda-content-right-card_title')]");
  private final Element countdownTimer = Element.byXpath
      ("//*[contains(@class,'time-before-event_before-event-timer')]");

  @Override
  public boolean isScreenLoaded() {
    return eventName.isDisplayed();
  }

  public String getEventNameText() {
    return eventName.getText();
  }

  public LocalDate getEventStartDate() {
    return getLocaleDateFromString(eventStartDate.getText(),DATE_PATTERN);
  }

  private DateTimeFormatter getDateFormatter() {
    return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(
        new Locale(getValueOf(LOCALE_LANGUAGE_ACRONYM).toLowerCase(),
            getValueOf(LOCALE_LANGUAGE_ACRONYM).toUpperCase()));
  }

  public String getEventStartDateText() {
    return eventStartDate.getText();
  }


  public String getEventLocationText() {
    return eventLocation.getText();
  }

  public String getEventFormatText() {
    return eventFormat.getText();
  }

  public String getEventLanguageText() {
    return eventLanguage.getText();
  }

  public boolean isEventAboutBlockPresent() {
    return eventAboutBlock.isPresent();
  }

  public boolean isEventJoinUsBlockPresent() {
    return eventJoinUsBlock.isPresent();
  }

  public String getEventSpeakerNameAttributeValue(int eventSpeakerNumber) {
    return Element.byXpath(String.format(EVENT_SPEAKER_NAME_PATTERN, eventSpeakerNumber))
        .getAttributeValue(INNER_TEXT_ATTRIBUTE);
  }

  public String getEventSpeakerJobTitleAttributeValue(int eventSpeakerNumber) {
    return Element.byXpath(String.format(EVENT_SPEAKER_JOB_TITLE_PATTERN, eventSpeakerNumber))
        .getAttributeValue(INNER_TEXT_ATTRIBUTE);
  }

  public String getEventSpeakerDescriptionAttributeValue(int eventSpeakerNumber) {
    return Element.byXpath(String.format(EVENT_SPEAKER_DESCRIPTION_PATTERN, eventSpeakerNumber))
        .getAttributeValue(INNER_TEXT_ATTRIBUTE);
  }

  public boolean isEventSpeakerPresent(int eventSpeakerNumber) {
    return Element.byXpath(String.format(EVENT_SPEAKER_NAME_PATTERN, eventSpeakerNumber))
        .isPresent();
  }

  public List<String> getWhatsOnText() {
    return eventWhatsOnDescriptionBlock.getElementsText();
  }

  public String getWhatsOnTextWithoutMassive() {
    return eventWhatsOnDescriptionBlock.getText();
  }

  public boolean isVideoPlayerDisplayed() {
    return videoPlayer.isDisplayed();
  }

  public boolean isCancelRegistrationButtonDisplayed() {
    return cancelRegisterButton.isDisplayed();
  }

  public EventPreviewScreen clickCancelRegistrationButtonIfNeedByReason(String name) {
    if (cancelRegisterButton.isDisplayedShortTimeOut()) {
      cancelRegisterButton.click();
      cancelReasonInputFieldInPopUp.waitForVisibility();
      cancelReasonInputFieldInPopUp.type(name);
      submitButtonInPopUp.click();
      registerButton.waitForVisibility();
    }
    return this;
  }

  public EventPreviewScreen waitCancelRegistrationButtonDisplayed() {
    cancelRegisterButton.waitForVisibility();
    return this;
  }

  public boolean isEventTimeDisplayed() {
    return eventTime.isDisplayed();
  }

  public boolean isEventLocationDisplayed() {
    return eventLocation.isDisplayed();
  }

  public boolean isEventFormatDisplayed() {
    return eventFormat.isDisplayed();
  }

  public boolean isEventLanguageDisplayed() {
    return eventLanguage.isDisplayed();
  }

  public boolean isEventCountdownTimerDisplayed() {
    return countdownTimer.isDisplayed();
  }

  public boolean isEventRegisterButtonDisplayed() {
    return registerButton.isDisplayed();
  }

  public boolean isSeeAgendaButtonDisplayed() {
    return   seeAgendaButton.isDisplayed();
  }

  public List<String> getNavigationBlock() {
    return navigationBlockContainer.getElementsText();
  }

  public List<String> getNavigationOptionLabel() {
    return navigationOptionLabelContainer.getElementsText();
  }

  public boolean isMeetOurSpeakersLabelDisplayed() {
    return meetOurSpeakersLabel.isDisplayed();
  }

  public boolean isEventStartDateDisplayed() {
    return eventStartDate.isDisplayed();
  }

  public boolean isJoinOnlineButtonPresent() {
    return joinOnlineButton.isPresent();
  }

  public EventRegistrationUserFormPopUpScreen clickRegistrationButton() {
    registerButton.click();
    return new EventRegistrationUserFormPopUpScreen();
  }

  public EventCancelRegistrationDescribeReasonPopUpScreen clickCancelRegistrationButton() {
    cancelButton.clickJs();
    return new EventCancelRegistrationDescribeReasonPopUpScreen();
  }

  public EventPreviewScreen waitRegistrationButtonDisplayed() {
    registerButton.waitForVisibility();
    return this;
  }

  public EventPreviewScreen clickEditButton() {
    editButton.click();
    return this;
  }

  public String getAboutTheEventDescriptionText() {
    return aboutTheEventDescriptionField.getText();
  }

  public String getEventJoinUsDescriptionText() {
    return joinUsDescriptionField.getText();
  }

  public boolean isEventRecordingDisplayed() {
    return eventRecording.isDisplayed();
  }

  public boolean isSliderNextArrowButtonDisplayed() {
    return sliderArrowNextButton.isDisplayed();
  }

  public String getCurrentVideoUrl() {
    return iframeForCurrentEventVideoUrl.getAttributeValue(ATTRIBUTE_SRC);
  }

  public void clickSliderArrowNextButton() {
    sliderArrowNextButton.click();
  }

  public boolean isJoinUsLabelDisplayed() {
    return joinUsLabel.isDisplayedShortTimeOut();
  }

  public boolean isAboutTheEventLabelDisplayed() {
    return aboutTheEventLabel.isDisplayedShortTimeOut();
  }

  public boolean isWhatsOnLabelDisplayed() {
    return whatsOnLabel.isDisplayedShortTimeOut();
  }

  public boolean isAgendaLabelDisplayed() {
    return agendaLabel.isDisplayedShortTimeOut();
  }

  public String getEventAgendaLabelText() {
    return agendaLabel.getText();
  }

  public String getEventAgendaDescriptionText() {
    return agendaDescriptionField.getText();
  }

  public EventPreviewScreen clickRefreshButtonUntilJoinUsDescriptionIsUpdated(String text) {
    Element joinUsDescription = Element.byXpath(String.format(JOIN_US_DESCRIPTION, text));
    int count = 0;
    clickRefreshButton();
    while (!joinUsDescription.isDisplayed(SHORT_TIME_OUT_IN_SECONDS) && count <= 6) {
      clickRefreshButton();
      count++;
    }
    return this;
  }

  public EventPreviewScreen clickRefreshButtonUntilSpeakerNameIsUpdated(String text) {
    Element speakerName = Element.byXpath(String.format(EVENT_SPEAKER_NAME, text));
    int count = 0;
    while (!speakerName.isDisplayed(SHORT_TIME_OUT_IN_SECONDS) && count <= 6) {
      clickRefreshButton();
      count++;
    }
    return this;
  }
}
