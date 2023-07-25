package com.epmrdpt.regression.events_management;

import com.epmrdpt.bo.events.EventForCopy;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactEventDescriptionScreen;
import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.screens.ReactEventGeneralInfoScreen;
import com.epmrdpt.screens.ReactEventHeaderScreen;
import com.epmrdpt.services.ReactDetailEventService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_94068_VerifyEventIsCopiedWithPreFilledFields",
    groups = {"full", "regression"})
public class EPMRDPT_94068_VerifyEventIsCopiedWithPreFilledFields {

  private static final String EVENT_NAME = "EventForCopy";

  private final User user;
  private final SoftAssert softAssert = new SoftAssert();
  private EventForCopy eventForCopy;
  private ReactEventGeneralInfoScreen eventGeneralInfoScreen;
  private ReactEventDescriptionScreen eventDescriptionScreen;

  @Factory(dataProvider = "Provider of users with events management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_94068_VerifyEventIsCopiedWithPreFilledFields(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndClickCopyEventButton() {
    eventGeneralInfoScreen = new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            user)
        .clickEventsManagementLink()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton()
        .clickGeneralInfoButton();
    eventForCopy = getEventForCopy();
    ReactEventHeaderScreen eventHeaderScreen = new ReactEventDetailsScreen().clickCreateCopyButton();
    eventHeaderScreen.switchToLastWindow();
    eventGeneralInfoScreen = eventHeaderScreen
        .clickDetailsButton()
        .clickGeneralInfoButton();
  }

  @Test(priority = 1)
  public void checkGeneralInfoTab() {
    softAssert.assertEquals(eventGeneralInfoScreen.getEventNameValue(),
        "Copy - " + eventForCopy.getName(), "Event name is incorrect!");
    softAssert.assertEquals(eventGeneralInfoScreen.getEventFormatPlaceholder(),
        eventForCopy.getFormat(), "Event format is incorrect!");
    softAssert.assertEquals(eventGeneralInfoScreen.getEventCountryChosenItemsText(),
        eventForCopy.getCountries(), "Event country is incorrect!");
    softAssert.assertEquals(eventGeneralInfoScreen.getEventCityChosenItemsText(),
        eventForCopy.getCities(), "Event city is incorrect!");
    softAssert.assertEquals(eventGeneralInfoScreen.getEventLanguagePlaceholder(),
        eventForCopy.getEventLanguage(), "Event language is incorrect!");
    softAssert.assertEquals(eventGeneralInfoScreen.getEventTagsChosenItemsText(),
        eventForCopy.getTags(), "Event tag is incorrect!");
    softAssert.assertEquals(eventGeneralInfoScreen.getEventSkillChosenItemsText(),
        eventForCopy.getSkills(), "Event skill is incorrect!");
    softAssert.assertEquals(eventGeneralInfoScreen.getEventContactEmailValue(),
        eventForCopy.getContactEmail(), "Event contact e-mail is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkDescriptionTab() {
    eventDescriptionScreen = new ReactEventDetailsScreen()
        .clickDescriptionButton()
        .waitWhatsOnValueNotEmpty();
    softAssert.assertEquals(eventDescriptionScreen.getMetaTagTitleValue(),
        eventForCopy.getMetaTagTitle(), "Event meta tag title is incorrect!");
    softAssert.assertEquals(eventDescriptionScreen.getMetaTagDescriptionValue(),
        eventForCopy.getMetaTagDescription(), "Event meta tag description is incorrect!");
    softAssert.assertEquals(eventDescriptionScreen.getMetaTagKeywordsValue(),
        eventForCopy.getMetaTagKeywords(), "Event meta tag keywords is incorrect!");
    softAssert.assertEquals(eventDescriptionScreen.getDetailsText(),
        eventForCopy.getDetailsBlockText(), "Event details text is incorrect!");
    softAssert.assertEquals(eventDescriptionScreen.getJoinUsIfYouText(),
        eventForCopy.getJoinUsIfYouBlockText(), "Event 'Join us if you' text is incorrect!");
    ReactDetailEventService reactDetailEventService = new ReactDetailEventService();
    softAssert.assertEquals(reactDetailEventService.getEventSpeakersList(),
        eventForCopy.getEventSpeakersList(), "Event speakers are incorrect!");
    softAssert.assertEquals(reactDetailEventService.getEventWhatsOnList(),
        eventForCopy.getEventWhatsOnList(), "Event 'What's on' list is incorrect!");
    softAssert.assertAll();
  }

  private EventForCopy getEventForCopy() {
    EventForCopy eventForCopy = new EventForCopy()
        .withName(eventGeneralInfoScreen.getEventNameValue())
        .withFormat(eventGeneralInfoScreen.getEventFormatPlaceholder())
        .withCountries(eventGeneralInfoScreen.getEventCountryChosenItemsText())
        .withCities(eventGeneralInfoScreen.getEventCityChosenItemsText())
        .withEventLanguage(eventGeneralInfoScreen.getEventLanguagePlaceholder())
        .withTags(eventGeneralInfoScreen.getEventTagsChosenItemsText())
        .withSkills(eventGeneralInfoScreen.getEventSkillChosenItemsText())
        .withContactEmail(eventGeneralInfoScreen.getEventContactEmailValue());
    eventDescriptionScreen = new ReactEventDetailsScreen()
        .clickDescriptionButton()
        .waitWhatsOnValueNotEmpty();
    ReactDetailEventService reactDetailEventService = new ReactDetailEventService();
    return eventForCopy
        .withMetaTagTitle(eventDescriptionScreen.getMetaTagTitleValue())
        .withMetaTagDescription(eventDescriptionScreen.getMetaTagDescriptionValue())
        .withMetaTagKeywords(eventDescriptionScreen.getMetaTagKeywordsValue())
        .withDetailsBlockText(eventDescriptionScreen.getDetailsText())
        .withJoinUsIfYouBlockText(eventDescriptionScreen.getJoinUsIfYouText())
        .withEventSpeakersList(reactDetailEventService.getEventSpeakersList())
        .withEventWhatsOnList(reactDetailEventService.getEventWhatsOnList());
  }
}
