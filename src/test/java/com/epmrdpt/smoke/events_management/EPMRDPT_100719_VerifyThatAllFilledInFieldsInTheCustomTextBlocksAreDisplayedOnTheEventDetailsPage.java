package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_100719_VerifyThatAllFilledInFieldsInTheCustomTextBlocksAreDisplayedOnTheEventDetailsPage",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_100719_VerifyThatAllFilledInFieldsInTheCustomTextBlocksAreDisplayedOnTheEventDetailsPage {

  private static final String EVENT_NAME = "EVENTS_FOR_TESTING100719";
  private static final String SPEAKER_NAME = "Test Test";
  private static final String SPEAKER_ROLE = "AQA Team";
  private static final String SPEAKER_DESCRIPTION = "Automation QA Java";
  private static final int EVENT_SPEAKER_NUMBER = 1;
  private EventPreviewScreen eventPreviewScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new ReactLoginService()
        .loginAndGoToReactEventsManagementScreen(asEventManager())
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton()
        .clickDescriptionButton()
        .typeSpeakerName(SPEAKER_NAME)
        .typeSpeakerRole(SPEAKER_ROLE)
        .typeSpeakerDescription(SPEAKER_DESCRIPTION)
        .clickSaveChangesButton();
    eventPreviewScreen = new ReactHeaderScreen()
        .clickEventLink()
        .waitEventCardsVisibility()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(EVENT_NAME);
  }

  @Test
  public void checkEventSpeakerData() {
    SoftAssert softAssert = new SoftAssert();
    eventPreviewScreen.clickRefreshButtonUntilSpeakerNameIsUpdated(SPEAKER_NAME);
    softAssert.assertEquals(
        eventPreviewScreen.getEventSpeakerNameAttributeValue(EVENT_SPEAKER_NUMBER),
        SPEAKER_NAME,
        "Event speaker name isn't correct!");
    softAssert.assertEquals(
        eventPreviewScreen.getEventSpeakerJobTitleAttributeValue(EVENT_SPEAKER_NUMBER),
        SPEAKER_ROLE,
        "Event speaker role isn't correct!");
    softAssert.assertEquals(
        eventPreviewScreen.getEventSpeakerDescriptionAttributeValue(EVENT_SPEAKER_NUMBER),
        SPEAKER_DESCRIPTION,
        "Event speaker description isn't correct!");
    softAssert.assertAll();
  }
}
