package com.epmrdpt.regression.events_management;

import com.epmrdpt.screens.ReactEventAttendeesScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactEventsManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;

@Test(description = "EPMRDPT_94083_VerifyReasonIsDisplayedOnHoveringTheIconNearRegistrationCancelledStatus",
        groups = {"full", "regression", "manager"})
public class EPMRDPT_94083_VerifyReasonIsDisplayedOnHoveringTheIconNearRegistrationCancelledStatus {
    private final String EVENT_NAME = "Autotest 94083";
    private final String REASON_TO_CANCEL_REGISTRATION = "burgor";


    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
                .clickEventsManagementLink();

        new ReactEventsManagementService()
                .searchEventByNameAndRedirectOnIt(EVENT_NAME)
                .clickAttendeesButton();
    }

    @Test
    public void isReasonToCancelDisplayedAndCorrect() {
        String actualReasonToCancelRegistration = new ReactEventAttendeesScreen().getStatusText();
        Assert.assertEquals(
                REASON_TO_CANCEL_REGISTRATION,
                actualReasonToCancelRegistration,
                "Displayed reason doesn't match actual reason to cancel registration"
        );
    }
}
