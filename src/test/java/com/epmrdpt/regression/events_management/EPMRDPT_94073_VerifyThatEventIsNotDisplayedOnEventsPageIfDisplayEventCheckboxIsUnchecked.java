package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;

import com.epmrdpt.screens.*;
import com.epmrdpt.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94073_VerifyThatEventIsNotDisplayedOnEventsPageIfDisplayEventCheckboxIsUnchecked",
        groups = {"full", "regression"})
public class EPMRDPT_94073_VerifyThatEventIsNotDisplayedOnEventsPageIfDisplayEventCheckboxIsUnchecked {
    private ReactEventGeneralInfoScreen reactEventGeneralInfoScreen;
    private EventsScreen eventsScreen;
    private final String eventName = "AutoTestEventPassed";

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
                .clickEventsManagementLink()
                .waitScreenLoaded()
                .typeEventName(eventName)
                .clickApplyButton()
                .waitAllSpinnersAreNotDisplayed()
                .clickEventByName(eventName);
        makeEventNotDisplayed();
        new ReactHeaderScreen().clickEventLink();
        eventsScreen = new EventsScreen().clickPassedEventsShowMoreButton();
    }

    @Test
    public void checkEventIsNotDisplayed() {
        Assert.assertFalse(eventsScreen.isEventDisplayedByName(eventName),
                String.format("Event \"%s\" is displayed", eventName));
    }

    private void makeEventNotDisplayed() {
        reactEventGeneralInfoScreen = new ReactEventGeneralInfoScreen();
        if (reactEventGeneralInfoScreen.isDisplayEventCheckboxChecked()) {
            reactEventGeneralInfoScreen.clickDisplayEventCheckBox()
                    .clickSaveChangesButton();
        }
    }
}
