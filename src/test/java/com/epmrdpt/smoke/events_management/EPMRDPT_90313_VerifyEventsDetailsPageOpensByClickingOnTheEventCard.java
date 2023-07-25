package com.epmrdpt.smoke.events_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90313_VerifyEventsDetailsPageOpensByClickingOnTheEventCard",
    groups = {"full", "smoke"})
public class EPMRDPT_90313_VerifyEventsDetailsPageOpensByClickingOnTheEventCard {

  private static final String EVENT_NAME = "EVENTS_FOR_TESTING";
  private EventPreviewScreen eventPreviewScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    eventPreviewScreen = new HeaderScreen()
        .clickEventsNavigationLink()
        .waitEventCardsVisibility()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(EVENT_NAME);
  }

  @Test
  public void checkEventDetailsPageIsOpen() {
    assertTrue(eventPreviewScreen.isScreenLoaded(), "Event details page isn't open!");
  }
}
