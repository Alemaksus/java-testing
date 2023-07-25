package com.epmrdpt.services;

import com.epmrdpt.bo.events.EventAgenda;
import com.epmrdpt.bo.events.EventOnlineFormat;
import com.epmrdpt.bo.events.EventSpeaker;
import com.epmrdpt.bo.events.EventWhatsOn;
import com.epmrdpt.screens.*;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReactEventsManagementService {

  private ReactEventDetailsScreen reactEventDetailsScreen = new ReactEventDetailsScreen();

  public EventOnlineFormat getEventWithRequiredFieldsAndLink() {
    return new EventOnlineFormat().withName(StringUtils.generateRandomPassword())
        .withRegistrationType("Open registration")
        .withEventLanguage("English")
        .withLinkToEvent("https://www.youtube.com/watch?v=n9uCgUzfeRQ")
        .withContactEmail(StringUtils.getGeneratedEmail())
        .withStartData(LocalDate.now())
        .withStartTime(LocalDateTime.now().plusHours(1))
        .withEndData(LocalDate.now().plusDays(3))
        .withEndTime(LocalDateTime.now());
  }

  public ReactEventDetailsScreen createEventWithMarksAndLink(EventOnlineFormat event) {
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HHmm");
    String formattedStartDate = event.getStartData().format(formatterDate);
    String formattedEndDate = event.getEndData().format(formatterDate);
    String formattedStartTime = event.getStartTime().format(formatterTime);
    String formattedEndTime = event.getEndTime().format(formatterTime);
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickEventsManagementLink()
        .waitScreenLoaded()
        .clickCreateEventButton()
        .waitCreateButtonPresent()
        .clickGeneralInfoButton()
        .typeEventName(event.getName())
        .clickFormatDropDown()
        .clickItemInDropDownByName("Online")
        .clickRegistrationTypeDropDown()
        .clickItemInDropDownByName(event.getRegistrationType())
        .clickLanguageDropDown()
        .clickItemInDropDownByName(event.getEventLanguage())
        .typeLinkOfEvent(event.getLinkToEvent())
        .typeContactEmail(event.getContactEmail())
        .typeStartDate(formattedStartDate)
        .clearStartTimeInputField()
        .typeStartTime(formattedStartTime)
        .typeEndDate(formattedEndDate)
        .clearEndTimeInputField()
        .typeEndTime(formattedEndTime);
    return reactEventDetailsScreen.clickCreateButton()
        .clickSaveChangesButton()
        .waitSaveChangesPopupDisplayed();
  }

  public EventPreviewScreen registerOnEvent(String name) {
    return new HeaderScreen().clickEventsNavigationLink()
        .waitEventCardsVisibility()
        .clickEventByName(name)
        .clickCancelRegistrationButtonIfNeedByReason(StringUtils.generateRandomPassword())
        .clickRegistrationButton()
        .clickCountryDropDown()
        .clickCountryItemByName("AutoTestCountry")
        .clickCityDropDown()
        .clickCityItemByName("AutoTestCity")
        .typeSurveyAnswerByName(StringUtils.generateRandomPassword())
        .clickRegistrationButtonAndSuccessPopUp()
        .waitCancelRegistrationButtonDisplayed();
  }

  public ReactEventDescriptionScreen fillDescriptionInEventCards(String text) {
    EventSpeaker eventSpeaker = new EventSpeaker(text, text, text);
    EventWhatsOn eventWhatsOn = new EventWhatsOn("Support3.svg",text);
    EventAgenda eventAgenda = new EventAgenda(text, text, LocalDate.now().atStartOfDay());
  new EventPreviewScreen().clickEditButton().switchToLastWindow();
    return new ReactEventDetailsScreen().clickDescriptionButton()
        .typeMetaTagDescriptionTest(text)
        .typeMetaTagTitleTest(text)
        .typeMetaTagKeywordsTest(text)
        .typeDetailsTextField(text)
        .typeJoinUsIfYouTextField(text)
        .typeSpeakerName(eventSpeaker.getName())
        .typeSpeakerRole(eventSpeaker.getJobTitle())
        .typeSpeakerDescription(eventSpeaker.getDescription())
        .typeAgendaReport(eventAgenda.getDescription())
        .typeWhatIsOnDescription(eventWhatsOn.getDescription())
        .clickSaveChangesButton();
      }

  public ReactEventHeaderScreen searchEventByNameAndRedirectOnIt(String eventName) {
    return new ReactEventsManagementScreen()
            .typeEventName(eventName)
            .clickApplyButton()
            .waitAllSpinnersAreNotDisplayed()
            .clickEventByName(eventName);
  }

  public ReactEventsManagementScreen searchEventByNameAndCountry(String eventName,String countryName) {
    return new ReactEventsManagementScreen()
            .typeEventName(eventName)
            .clickCountryDropDown()
            .typeCountryInDropDownSearch(countryName)
            .clickCountryByName(countryName)
            .clickApplyButton()
            .waitAllSpinnersAreNotDisplayed();
  }
}
