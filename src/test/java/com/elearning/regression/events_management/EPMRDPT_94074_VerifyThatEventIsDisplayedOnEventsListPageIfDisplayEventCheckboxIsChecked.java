package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventsScreen;
import com.epmrdpt.screens.ReactEventGeneralInfoScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.services.ReactEventsManagementService;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94074_VerifyThatEventIsDisplayedOnEventsListPageIfDisplayEventCheckboxIsChecked",
    groups = {"full", "regression"})
public class EPMRDPT_94074_VerifyThatEventIsDisplayedOnEventsListPageIfDisplayEventCheckboxIsChecked {

  private static final String EVENT_NAME = "Testing_Event_94074";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new ReactLoginService()
        .loginAndGoToReactEventsManagementScreen(asEventManager());
    new ReactEventsManagementService()
        .searchEventByNameAndRedirectOnIt(EVENT_NAME);
    new ReactEventGeneralInfoScreen()
        .clickDisplayEventButton();
  }

  @Test
  public void checkEventIsDisplayedOnEventsListPage() {
    new ReactHeaderScreen()
        .clickEventLink();
    EventsScreen eventsScreen = new EventsScreen()
        .waitEventCardsVisibility();
    assertTrue(eventsScreen.isEventDisplayedByName(EVENT_NAME), "Event is not displayed.");
  }
}
