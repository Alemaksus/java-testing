package com.epmrdpt.smoke.events_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventsScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94065_VerifyTheEventsAreDisplayedAccordingToTheSelectedSection",
    groups = {"full", "smoke"})
public class EPMRDPT_94065_VerifyTheEventsAreDisplayedAccordingToTheSelectedSection {

  private EventsScreen eventsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void goToEventsPage() {
    eventsScreen = new HeaderScreen()
        .clickEventsNavigationLink()
        .waitEventCardsVisibility();
  }

  @Test
  public void checkThatUpcomingEventsAreDisplayed() {
    assertTrue(eventsScreen.isUpcomingEventsCardsDisplayed(), "Upcoming events are not displayed");
  }

  @Test
  public void checkThatPassedEventsAreDisplayed() {
    assertTrue(eventsScreen.isPassedEventsCardsDisplayed(), "Passed events are not displayed");
  }
}
