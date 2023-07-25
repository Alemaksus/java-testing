package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90303_VerifyThatEventsWithTheStatusPublishedArePresentOnThePage",
    groups = {"full", "smoke"})
public class EPMRDPT_90303_VerifyThatEventsWithTheStatusPublishedArePresentOnThePage {

  private final String eventName = "Event AutoTest";
  private EventsScreen eventsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    eventsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton();
  }

  @Test()
  public void checkEventCardsArePresent() {
    assertTrue(
        eventsScreen.isEventDisplayedByName(eventName),
        String.format("Event '%s' is not displayed!", eventName)
    );
  }
}
