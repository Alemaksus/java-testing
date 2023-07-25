package com.epmrdpt.regression.events_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_LOCATIONS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_EVENT_LANGUAGES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_FILTER_TAGS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_SWITCHER_PASSED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_SWITCHER_UPCOMING;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.EventsScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94063_VerifyTheElementsOfTheFiltersOfEvents",
    groups = {"full", "regression"})
public class EPMRDPT_94063_VerifyTheElementsOfTheFiltersOfEvents {

  private EventsScreen eventsScreen;
  private List<String> listOfDropDownFilterElements = Arrays.asList(getValueOf(
          EVENTS_FILTER_LOCATIONS),
      getValueOf(EVENTS_FILTER_SKILLS),
      getValueOf(EVENTS_FILTER_EVENT_LANGUAGES),
      getValueOf(EVENTS_FILTER_TAGS));
  private List<String> listOfSwitcherFilterElements = Arrays.asList(getValueOf(EVENTS_SWITCHER_UPCOMING),
      getValueOf(EVENTS_SWITCHER_PASSED));

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void fillArrayListsWithObjects() {
    eventsScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickEventsNavigationLink();
  }

  @Test
  public void checkAttributesOfDropDownFiltersIsCorrect() {
    assertEquals(eventsScreen.getDropDownFilterElementsText(), listOfDropDownFilterElements,
        "Attributes of drop down filters isn't correct on the Events page!");
  }

  @Test
  public void checkAttributesOfSwitcherFiltersIsCorrect() {
    assertEquals(eventsScreen.getSwitcherFilterElementsText(), listOfSwitcherFilterElements,
        "Attributes of switcher filters isn't correct on the Events page!");
  }
}
