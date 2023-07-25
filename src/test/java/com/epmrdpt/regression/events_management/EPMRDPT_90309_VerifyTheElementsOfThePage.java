package com.epmrdpt.regression.events_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_NAVIGATION_ABOUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_NAVIGATION_AGENDA;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_NAVIGATION_JOIN_US_IF_YOU;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_NAVIGATION_MEET_OUR_SPEAKER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_NAVIGATION_WHAT_IS_ON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_90309_VerifyTheElementsOfThePage",
    groups = {"full", "regression"})
public class EPMRDPT_90309_VerifyTheElementsOfThePage {

  private final static String EVENT_NAME = "EVENTS_FOR_TESTING";
  private List<String> listNavigationOptionLabel = Arrays.asList(
      getValueOf(EVENTS_NAVIGATION_ABOUT),
      getValueOf(EVENTS_NAVIGATION_WHAT_IS_ON),
      getValueOf(EVENTS_NAVIGATION_JOIN_US_IF_YOU),
      getValueOf(EVENTS_NAVIGATION_MEET_OUR_SPEAKER));
  private List<String> actualNavigationLabelsList;
  private EventPreviewScreen eventPreviewScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    eventPreviewScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(EVENT_NAME);
  }

  @Test
  public void checkEventsNameIsInvalid() {
    assertEquals(eventPreviewScreen.getEventNameText(), EVENT_NAME);
  }

  @Test
  public void checkEventsDateIsDisplayed() {
    assertTrue(eventPreviewScreen.isEventStartDateDisplayed(), "'Event Date' isn't displayed");
  }

  @Test
  public void checkLocationIsDisplayed() {
    assertTrue(eventPreviewScreen.isEventLocationDisplayed(), "'Event location' isn't displayed");
  }

  @Test
  public void checkEventFormatIsDisplayed() {
    assertTrue(eventPreviewScreen.isEventFormatDisplayed(), "'Event format' isn't displayed");
  }

  @Test
  public void checkEventLanguageIsDisplayed() {
    assertTrue(eventPreviewScreen.isEventLanguageDisplayed(), "'Event language' isn't displayed");
  }

  @Test
  public void checkEventRegisterButtonIsDisplayed() {
    assertTrue(eventPreviewScreen.isEventRegisterButtonDisplayed(),
        "'Register' button isn't displayed");
  }

  @Test
  public void checkSeeAgendaButtonIsDisplayed() {
    assertTrue(eventPreviewScreen.isSeeAgendaButtonDisplayed(),
        "'See the agenda' button isn't displayed");
  }

  @Test
  public void checkEventCountdownTimerIsDisplayed() {
    assertTrue(eventPreviewScreen.isEventCountdownTimerDisplayed(),
        "Event countdown timer isn't displayed");
  }

  @Test
  public void checkNavigationOptionLabel() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(eventPreviewScreen.getEventAgendaLabelText(),
        getValueOf(EVENTS_NAVIGATION_AGENDA), "Navigation element 'Agenda' is not displayed!");
    actualNavigationLabelsList = eventPreviewScreen.getNavigationOptionLabel();
    for (String element : listNavigationOptionLabel) {
      softAssert.assertTrue(actualNavigationLabelsList
              .contains(element),
          String.format("Navigation element '%s' is not displayed!", element));
    }
    softAssert.assertAll();
  }
}
