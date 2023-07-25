package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_COUNTRY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_EVENT_LANGUAGES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_LOCATIONS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_TAGS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_SWITCHER_PASSED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_SWITCHER_UPCOMING;

import com.epmrdpt.screens.EventsScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_94064_VerifyTheFoundEventsCorrespondTheSelectedFilters",
    groups = {"full", "smoke"})
public class EPMRDPT_94064_VerifyTheFoundEventsCorrespondTheSelectedFilters {

  private static final String COUNTRY_FOR_UPCOMING_EVENT = "AutoTestCountry";
  private static final String COUNTRY_FOR_PASSED_EVENT = getValueOf(EVENTS_COUNTRY);
  private static final String SKILL_FOR_UPCOMING_EVENT = ".NET";
  private static final String EVENT_LANGUAGE_FOR_UPCOMING_EVENT = "English";
  private static final String EVENT_LANGUAGE_FOR_PASSED_EVENT = "Українська";
  private static final String TAG_FOR_UPCOMING_EVENT = "#AutoTest";
  private static final String UPCOMING_EVENT_NAME = "Event AutoTest";
  private static final String PASSED_EVENT_NAME = "VK event with description";
  private EventsScreen eventsScreen;

  @BeforeMethod(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    eventsScreen = new HeaderScreen()
        .clickEventsNavigationLink()
        .waitEventCardsVisibility();
  }

  @Test(priority = 1)
  public void checkEventInUpcomingTab() {
    SoftAssert softAssert = new SoftAssert();
    eventsScreen
        .chooseDropdownListOption(getValueOf(EVENTS_FILTER_LOCATIONS), COUNTRY_FOR_UPCOMING_EVENT)
        .chooseDropdownListOption(getValueOf(EVENTS_FILTER_SKILLS), SKILL_FOR_UPCOMING_EVENT)
        .chooseDropdownListOption(getValueOf(EVENTS_FILTER_EVENT_LANGUAGES),
            EVENT_LANGUAGE_FOR_UPCOMING_EVENT)
        .chooseDropdownListOption(getValueOf(EVENTS_FILTER_TAGS), TAG_FOR_UPCOMING_EVENT);
    softAssert.assertTrue(eventsScreen.isEventDisplayedByName(UPCOMING_EVENT_NAME),
        String.format("Event '%s' is not displayed!", UPCOMING_EVENT_NAME));
    softAssert.assertEquals(getValueOf(EVENTS_SWITCHER_UPCOMING),
        eventsScreen.getEventStatusText(UPCOMING_EVENT_NAME, getValueOf(EVENTS_SWITCHER_UPCOMING)),
        String.format("Event '%s' is not upcoming!", UPCOMING_EVENT_NAME));
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkEventInPassedTab() {
    SoftAssert softAssert = new SoftAssert();
    eventsScreen
        .chooseDropdownListOption(getValueOf(EVENTS_FILTER_LOCATIONS), COUNTRY_FOR_PASSED_EVENT)
        .chooseDropdownListOption(getValueOf(EVENTS_FILTER_EVENT_LANGUAGES),
            EVENT_LANGUAGE_FOR_PASSED_EVENT)
        .clickDropdownList(getValueOf(EVENTS_FILTER_EVENT_LANGUAGES));
    softAssert.assertTrue(eventsScreen.isEventDisplayedByName(PASSED_EVENT_NAME),
        String.format("Event '%s' is not displayed!", PASSED_EVENT_NAME));
    softAssert.assertEquals(getValueOf(EVENTS_SWITCHER_PASSED),
        eventsScreen.getEventStatusText(PASSED_EVENT_NAME, getValueOf(EVENTS_SWITCHER_PASSED)),
        String.format("Event '%s' is not passed!", PASSED_EVENT_NAME));
    softAssert.assertAll();
  }
}
