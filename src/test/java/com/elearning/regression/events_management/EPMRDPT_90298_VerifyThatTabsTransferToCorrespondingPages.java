package com.epmrdpt.regression.events_management;

import com.epmrdpt.screens.*;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_ATTENDEES_BUTTON;

@Test(description = "EPMRDPT_90298_VerifyThatTabsTransferToCorrespondingPages",
        groups = {"full", "regression"})
public class EPMRDPT_90298_VerifyThatTabsTransferToCorrespondingPages {

    private ReactEventDetailsScreen reactEventDetailsScreen;
    private ReactEventHeaderScreen reactEventHeaderScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        reactEventHeaderScreen = new LoginService()
                .loginAndSetLanguage(asEventManager())
                .clickEventsManagementLink()
                .clickFirstEventInList();
    }

    @Test(priority = 1)
    public void checkDetailsTabTransferToCorrespondingPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(reactEventHeaderScreen.getDetailsButtonText(),
                getValueOf(REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB),
                "'Details' tab is not present");
        reactEventDetailsScreen = reactEventHeaderScreen.clickDetailsButton();
        softAssert.assertTrue(reactEventDetailsScreen.isScreenLoaded(),
                "The user is not transferred to the 'Details' page");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void checkGeneralInfoTabTransferToCorrespondingPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(reactEventDetailsScreen.isGeneralInfoButtonDisplayed(),
                "'General Info' tab is not present");
        ReactEventGeneralInfoScreen reactEventGeneralInfoScreen = reactEventDetailsScreen.clickGeneralInfoButton();
        softAssert.assertTrue(reactEventGeneralInfoScreen.isScreenLoaded(),
                "The user is not transferred to the 'General Info' page");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void checkDescriptionTabTransferToCorrespondingPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(reactEventDetailsScreen.isDescriptionButtonDisplayed(),
                "'Description' tab is not present");
        ReactEventDescriptionScreen reactEventDescriptionScreen = reactEventDetailsScreen.clickDescriptionButton();
        softAssert.assertTrue(reactEventDescriptionScreen.isScreenLoaded(),
                "The user is not transferred to the 'Description' page");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void checkAttendeesTabTransferToCorrespondingPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(reactEventHeaderScreen.getAttendeesButtonText(),
                getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_ATTENDEES_BUTTON),
                "'Attendees' tab is not present");
        ReactEventAttendeesScreen reactEventAttendeesScreen = reactEventHeaderScreen.clickAttendeesButton();
        softAssert.assertTrue(reactEventAttendeesScreen.isScreenLoaded(),
                "The user is not transferred to the 'Attendees' page");
        softAssert.assertAll();
    }
}
