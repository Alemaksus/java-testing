package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ATTENDEES_SURVEY_POPUP_QUESTION_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_REGISTRATION_SURVEY_SECTION;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactEventAttendeesScreen;
import com.epmrdpt.screens.ReactEventsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90284_VerifyHintAppearsWhenClickOnTheQuestionIconInSurveyColumn",
        groups = {"full", "regression", "manager"})
public class EPMRDPT_90284_VerifyHintAppearsWhenClickOnTheQuestionIconInSurveyColumn {

    private static final String EVENT_NAME = "EVENTS_FOR_TESTING";
    private static final String ATTENDEE_NAME = "QQ AA";
    private static final String SURVEY_POPUP_TITLE = getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_REGISTRATION_SURVEY_SECTION) + " - " + EVENT_NAME;
    private static final String SURVEY_POPUP_QUESTION = getValueOf(REACT_EVENTS_MANAGEMENT_ATTENDEES_SURVEY_POPUP_QUESTION_TEXT);

    private ReactEventAttendeesScreen reactEventAttendeesScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
                .clickEventsManagementLink();
        reactEventAttendeesScreen = new ReactEventsManagementScreen()
                .typeEventName(EVENT_NAME)
                .clickApplyButton()
                .waitAllSpinnersAreNotDisplayed()
                .clickEventByName(EVENT_NAME)
                .clickAttendeesButton()
                .clickSurveyIconOfAttendeeByName(ATTENDEE_NAME);
    }

    @Test
    public void checkSurveyPopUpLabel() {
        assertTrue(reactEventAttendeesScreen.isSurveyPopUpDisplayed(),
                "Survey is not displayed");
    }

    @Test
    public void checkSurveyPopupTitle() {
        assertEquals(reactEventAttendeesScreen.getSurveyPopUpTitleText(), SURVEY_POPUP_TITLE,
                "Survey PopUp title text is not correct");
    }

    @Test
    public void checkSurveyPopupQuestion() {
        assertEquals(reactEventAttendeesScreen.getSurveyPopUpQuestionText(), SURVEY_POPUP_QUESTION,
                "Survey PopUp question text is not correct");
    }

    @Test
    public void checkSurveyPopupAnswer() {
        assertTrue(reactEventAttendeesScreen.isSurveyPopUpAnswerDisplayed(),
                "Survey PopUp answer is not displayed");
    }
}
