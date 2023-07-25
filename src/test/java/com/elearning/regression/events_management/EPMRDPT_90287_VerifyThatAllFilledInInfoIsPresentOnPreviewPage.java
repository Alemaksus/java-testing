package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.events.EventSpeaker;
import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.services.EventPreviewService;
import com.epmrdpt.services.LoginService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90287_VerifyThatAllFilledInInfoIsPresentOnPreviewPage",
    groups = {"full", "regression"})
public class EPMRDPT_90287_VerifyThatAllFilledInInfoIsPresentOnPreviewPage {

  private static final String EVENT_NAME = "EventForPreviewVerification";
  private static final LocalDate EVENT_START_DATE = LocalDate.of(2024, 11, 30);
  private static final String EVENT_LOCATION = "AutoTestCountry";
  private static final String EVENT_FORMAT = "Online";
  private static final String EVENT_LANGUAGE = "Українська";
  private static final List<EventSpeaker> EVENT_SPEAKERS = Arrays.asList(
      new EventSpeaker("1", "1", "1"),
      new EventSpeaker("2", "2", "2"),
      new EventSpeaker("3", "3", "3"),
      new EventSpeaker("4", "4", "4"),
      new EventSpeaker("5", "5", "5"));
  private static final List<String> EVENT_WHATS_ON_TEXT = Arrays.asList("Some skills", "Fun");

  private EventPreviewScreen eventPreviewScreenScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToEventPreview() {
    eventPreviewScreenScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton()
        .clickPreviewButton();
    eventPreviewScreenScreen.switchToLastWindow();
  }

  @Test
  public void checkEventName() {
    assertEquals(eventPreviewScreenScreen.getEventNameText(), EVENT_NAME,
        "Event name is incorrect!");
  }

  @Test
  public void checkEventStartDate() {
    assertEquals(eventPreviewScreenScreen.getEventStartDate(), EVENT_START_DATE,
        "Event start date is incorrect!");
  }

  @Test
  public void checkEventLocation() {
    assertEquals(eventPreviewScreenScreen.getEventLocationText(), EVENT_LOCATION,
        "Event location is incorrect!");
  }

  @Test
  public void checkEventFormat() {
    assertEquals(eventPreviewScreenScreen.getEventFormatText(), EVENT_FORMAT,
        "Event format is incorrect!");
  }

  @Test
  public void checkEventLanguage() {
    assertEquals(eventPreviewScreenScreen.getEventLanguageText(), EVENT_LANGUAGE,
        "Event language is incorrect!");
  }

  @Test
  public void checkEventDetailsBlock() {
    assertTrue(eventPreviewScreenScreen.isEventAboutBlockPresent(),
        "Event about block is not displayed!");
  }

  @Test
  public void checkEventJoinUsBlock() {
    assertTrue(eventPreviewScreenScreen.isEventJoinUsBlockPresent(),
        "Event join us block is not displayed!");
  }

  @Test
  public void checkEventSpeakers() {
    assertEquals(new EventPreviewService().getEventSpeakers(), EVENT_SPEAKERS,
        "Event speakers are incorrect!");
  }

  @Test
  public void checkEventWhatsOnBlock() {
    assertEquals(eventPreviewScreenScreen.getWhatsOnText(), EVENT_WHATS_ON_TEXT,
        "Event what's on text is incorrect!");
  }
}
