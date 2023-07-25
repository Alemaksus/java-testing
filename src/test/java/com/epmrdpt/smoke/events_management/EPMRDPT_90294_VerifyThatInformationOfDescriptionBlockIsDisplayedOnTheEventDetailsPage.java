package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asSuperAdmin;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactEventsManagementService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_90294_VerifyThatInformationOfDescriptionBlockIsDisplayedOnTheEventDetailsPage",
    groups = {"full", "smoke"})
public class EPMRDPT_90294_VerifyThatInformationOfDescriptionBlockIsDisplayedOnTheEventDetailsPage {

  private final static String DESCRIPTION_TEXT = StringUtils.generateRandomPassword();
  private final static String EVENT_NAME = "EVENTS_FOR_TESTING";
  EventPreviewScreen eventPreviewScreen;

  @BeforeClass
  public void setUp() {
    eventPreviewScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asSuperAdmin())
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(EVENT_NAME);
  }

  @Test(priority = 1)
  public void checkInformationBlocksDisplayedOnThePage() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(eventPreviewScreen.isAboutTheEventLabelDisplayed(),
        "'About the event' label is not displayed.");
    softAssert.assertTrue(eventPreviewScreen.isJoinUsLabelDisplayed(),
        "'Join us if you:' label is not displayed.");
    softAssert.assertTrue(eventPreviewScreen.isMeetOurSpeakersLabelDisplayed(),
        "'Meet Our Speakers' label is not displayed.");
    softAssert.assertTrue(eventPreviewScreen.isWhatsOnLabelDisplayed(),
        "'What's on?' label is not displayed.");
    softAssert.assertTrue(eventPreviewScreen.isAgendaLabelDisplayed(),
        "'Agenda' label is not displayed.");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSavedChangesDisplayed() {
    new ReactEventsManagementService().fillDescriptionInEventCards(DESCRIPTION_TEXT);
    assertTrue(new ReactEventDetailsScreen().isSaveChangesPopupDisplayed(),
        "Changes were not saved");
  }

  @Test(priority = 3)
  public void checkChangesDisplayedOnTheEventDetailsPage() {
    SoftAssert softAssert = new SoftAssert();
    new ReactEventDetailsScreen().closeLastWindowAndSwitchToPreviousIfMoreThanOne();
    eventPreviewScreen.clickRefreshButtonUntilJoinUsDescriptionIsUpdated(DESCRIPTION_TEXT);
    softAssert.assertEquals(eventPreviewScreen.getAboutTheEventDescriptionText(), DESCRIPTION_TEXT,
        "The text of changes isn't correct");
    softAssert.assertEquals(eventPreviewScreen.getEventSpeakerDescriptionAttributeValue(1),
        DESCRIPTION_TEXT,
        "The text of changes isn't correct");
    softAssert.assertEquals(eventPreviewScreen.getWhatsOnTextWithoutMassive(), DESCRIPTION_TEXT,
        "The text of changes isn't correct");
    softAssert.assertEquals(eventPreviewScreen.getEventJoinUsDescriptionText(), DESCRIPTION_TEXT,
        "The text of changes isn't correct");
    softAssert.assertEquals(eventPreviewScreen.getEventAgendaDescriptionText(), DESCRIPTION_TEXT,
        "The text of changes isn't correct");
    softAssert.assertAll();
  }
}
