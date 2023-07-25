package com.epmrdpt.regression.events_management;

import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.screens.EventRegistrationUserFormPopUpScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;
import static com.epmrdpt.framework.properties.UserPropertyConst.USER_LOGIN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(description = "EPMRDPT_90305_VerifyThatElementsOfRegistrationPopUpAreExist",
        groups = {"full", "regression"})
public class EPMRDPT_90305_VerifyThatElementsOfRegistrationPopUpAreExist {

    private final String FIRST_NAME = "QQ";
    private final String LAST_NAME = "AA";
    private final String EMAIL = UserProperty.getValueOf(USER_LOGIN);
    private final String CONTACT_PHONE = "+375111000";
    private final String EVENT_NAME = "Event AutoTest";
    private EventRegistrationUserFormPopUpScreen eventRegistrationUserFormPopUpScreen;
    private SoftAssert softAssert;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp(){
        eventRegistrationUserFormPopUpScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(withSimplePermission())
                .clickEventsNavigationLink()
                .waitEventCardsVisibility()
                .clickEventByName(EVENT_NAME)
                .clickRegistrationButton();
        eventRegistrationUserFormPopUpScreen.isScreenLoaded();
    }

    @Test
    public void checkTitle() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isTitleNameDisplayed(),
                "Title name is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextTitleName(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_TITLE),
                "Title name is not correct");
        softAssert.assertAll();
    }

    @Test
    public void checkCrossButton(){
        assertTrue(eventRegistrationUserFormPopUpScreen.isCrossButtonDisplayed(),
                "Cross-button is not displayed");
    }

    @Test
    public void checkFirstNameLabel() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isFirstNameDisplayed(),
                "First name label is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextFirstNameLabel(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_FIRST_NAME_LABEL),
                "First name label is not correct");
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isInputFieldDisplayed(),
                "Input field first name is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextFirstNameFromInputField(),
                FIRST_NAME, "First name is not correct" );
        softAssert.assertAll();
    }

    @Test
    public void checkLastNameLabel() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isLastNameDisplayed(),
                "Last name label is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextLastNameLabel(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_LAST_NAME_LABEL),
                "Last name label is not correct");
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isInputFieldLastNameDisplayed(),
                "Input field last name is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextLastNameFromInputField(),
                LAST_NAME, "Last name is not correct" );
        softAssert.assertAll();
    }

    @Test
    public void checkEmailLabel() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isEmailLabelDisplayed(),
                "Email label is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextEmailLabel(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_EMAIL_LABEL),
                "Email label is not correct");
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isInputFieldEmailDisplayed(),
                "Input field email is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextEmailFromInputField(),
                EMAIL, "Email is not correct" );
        softAssert.assertAll();
    }

    @Test
    public void checkContactPhoneLabel() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isContactPhoneLabelDisplayed(),
                "Contact phone label is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextContactPhoneLabel(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_CONTACT_PHONE_LABEL),
                "Contact phone label is not correct");
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isInputFieldContactPhoneDisplayed(),
                "Input field contact phone is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextContactPhoneFromInputField(),
                CONTACT_PHONE, "Contact phone is not correct" );
        softAssert.assertAll();
    }

    @Test
    public void checkQuestionIcon(){
        assertTrue(eventRegistrationUserFormPopUpScreen.isQuestionIconDisplayed(),
                "Question icon is not displayed");
    }

    @Test
    public void checkNotificationAboutPhoneNumber(){
        assertEquals(eventRegistrationUserFormPopUpScreen.getTextNotificationAboutPhoneNumber(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_MESSAGE_ABOUT_PHONE_CONTACT_TEXT),
                "Notification about phone number is not correct");
    }

    @Test
    public void checkCountryLabel() {
       softAssert = new SoftAssert();
       softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isCountryLabelDisplayed(),
                "Country label is not displayed");
       softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextCountryLabel(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_COUNTRY_LABEL),
                "Country label is not correct");
       softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getCountryPlaceholderText(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_PLACEHOLDER_TEXT),
                "Country placeholder text is not correct");
        softAssert.assertAll();
    }

    @Test
    public void checkCityLabel() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isCityLabelDisplayed(),
                "City label is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextCityLabel(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_CITY_LABEL),
                "City label is not correct");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getCityPlaceholderAttributeValue(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_PLACEHOLDER_TEXT),
                "City placeholder text is not correct");
        softAssert.assertAll();
    }

    @Test
    public void checkSurveyLabel(){
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isSurveyDisplayed(),
                "Survey is not displayed");
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isInputFieldForSurveyAnswerDisplayed(),
                "Input field for survey answer is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getTextSurveyQuestion(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_SURVEY_TEXT),
                "Survey text is not correct");
        softAssert.assertAll();
    }

    @Test
    public void checkNotificationText(){
        softAssert = new SoftAssert();
        softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isNotificationTextIsDisplayed(),
                "Notification text is not displayed");
        softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getNotificationText(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_NOTIFICATION_TEXT),
                "Notification text is not correct");
        softAssert.assertAll();
    }

    @Test
    public void checkRegisterButton(){
       softAssert = new SoftAssert();
       softAssert.assertTrue(eventRegistrationUserFormPopUpScreen.isRegisterButtonDisplayed(),
                "Register button is not displayed");
       softAssert.assertEquals(eventRegistrationUserFormPopUpScreen.getRegisterButtonText(),
                getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_REGISTER_BUTTON),
                "Register button text is not correct");
        softAssert.assertAll();
    }
}
