package com.epmrdpt.regression.events_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventsScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_90312_VerifyTheDisplayOfTheEventsCards",
    groups = {"full", "regression"})
public class EPMRDPT_90312_VerifyTheDisplayOfTheEventsCards {

  private final String eventName = "Event AutoTest";
  private EventsScreen eventsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    eventsScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton();
  }

  @Test(priority = 1)
  public void isEventByNameDisplayed() {
    assertTrue(eventsScreen.isEventDisplayedByName(eventName),
        String.format("An Event with name '%s' is not displayed!", eventName));
  }

  @Test(priority = 2)
  public void isElementsOfEventDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(eventsScreen.isLanguageOfEventDisplayedByName(eventName),
        "The language of event is not displayed!");
    softAssert.assertTrue(eventsScreen.isDateOfEventDisplayedByName(eventName),
        "The date of the event is not displayed!");
    softAssert.assertTrue(eventsScreen.isLocationOfEventDisplayedByName(eventName),
        "The location of the event is not displayed!");
    softAssert.assertTrue(eventsScreen.isHashtagOfEventDisplayedByName(eventName),
        "The hashtag of the event is not displayed!");
    softAssert.assertAll();
  }
}
