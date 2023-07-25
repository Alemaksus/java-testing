package com.epmrdpt.smoke.events_management;

import com.epmrdpt.screens.EventsScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97319_VerifyThatEventWithClosedRegistrationTypeDoesNotDisplaysOnEventsPage",
        groups = {"full", "general", "smoke"})
public class EPMRDPT_97319_VerifyThatEventWithClosedRegistrationTypeDoesNotDisplaysOnEventsPage {
    private final String EVENT_NAME = "Autotest 97319";
    private EventsScreen eventsScreen;

    @BeforeClass
    public void setUp() {
        eventsScreen = new HeaderScreen()
                .clickEventsNavigationLink();
    }

    @Test
    public void checkIsClosedEventPresentOnEventsPage() {
        Assert.assertFalse(eventsScreen.isEventDisplayedByName(EVENT_NAME));
    }
}
