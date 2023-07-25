package com.epmrdpt.regression.events_management;

import com.epmrdpt.screens.ReactEventGeneralInfoScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;

@Test(description = "EPMRDPT_90295_97329_VerifyElementsOfTheGeneralInfoTab",
        groups = {"full", "regression"})
public class EPMRDPT_90295_97329_VerifyElementsOfTheGeneralInfoTab {

    private final static String REGISTRATION_TYPE_LABEL = "Registration type";
    private final static String PRE_FILLED_OPTION_REGISTRATION_OPTION = "Open registration";
    private final static String ENROLLMENT_TYPE = "Enrollment type";
    private final static String ENROLLMENT_TYPE_PLACEHOLDER_TEXT = "Auto-enrollment";
    private final static String CUSTOM_IMAGE_LABEL = "Custom image for sharing";
    private final static String CUSTOM_IMAGE_PLACEHOLDER_TEXT = "Add image";
    private final static String LINK_PLACEHOLDER_TEXT = "http://example.com";
    private final String notificationText = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_NOTIFICATION_TEXT);
    private final String formatLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_FORMAT_LABEL);
    private final String nameLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_NAME_LABEL);
    private final String namePlaceholderText = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_NAME_PLACEHOLDER_TEXT);
    private final String countryLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_COUNTRY_LABEL);
    private final String selectFromTheListPlaceholder = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_SELECT_FROM_THE_LIST_PLACEHOLDER);
    private final String cityLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_CITY_LABEL);
    private final String addressLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_ADDRESS_LABEL);
    private final String enterTextPlaceholder = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_ENTER_TEXT_PLACEHOLDER);
    private final String languageLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_LANGUAGE_LABEL);
    private final String tagsLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_TAGS_LABEL);
    private final String skillLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_SKILL_LABEL);
    private final String startLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_START_LABEL);
    private final String endLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_END_LABEL);
    private final String emailLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_EMAIL_LABEL);
    private final String enterEmailPlaceholder = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_ENTER_EMAIL_PLACEHOLDER);
    private final String eventCoverLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_EVENT_COVER_LABEL);
    private final String eventCoverPlaceholderText = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_EVENT_COVER_PLACEHOLDER);
    private final String eventCoverMiniatureLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_EVENT_COVER_MINIATURE_LABEL);
    private final String eventCoverMiniaturePlaceholderText = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_EVENT_COVER_MINIATURE_PLACEHOLDER);
    private final String linkToEventLabel = getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_LINK_TO_EVENT_LABEL);
    private ReactEventGeneralInfoScreen reactEventGeneralInfoScreen;

    @DataProvider(name = "Provider of events formats")
    public static Object[][] provideFormats() {
        return new Object[][]{
                {"Online"},
                {"Offline"},
        };
    }

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        reactEventGeneralInfoScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
                .clickEventsManagementLink()
                .clickCreateEventButton()
                .clickGeneralInfoButton();
    }

    @Test(dataProvider = "Provider of events formats")
    public void checkElementsOfTheGeneralInfoTab(String formatOption) {
        reactEventGeneralInfoScreen.clickFormatDDL()
                .clickItemInDropDownByName(formatOption);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(notificationText),
                "Notification text is incorrect or not displayed ");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(nameLabel),
                "Name label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromInputDependsLabel(nameLabel),
                namePlaceholderText,
                "Name placeholder text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(formatLabel),
                "Format label is incorrect or not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(countryLabel),
                "Country label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromDivWithClassPlaceholderDependsLabel(countryLabel),
                selectFromTheListPlaceholder,
                "Country placeholder text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(cityLabel),
                "City label is incorrect or not displayed ");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromSpanDependsLabel(cityLabel),
                selectFromTheListPlaceholder,
                "Placeholder city text is incorrect");
        if (Objects.equals(formatOption, "Offline")) {
            softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(addressLabel),
                    "Address label is incorrect or not displayed");
            softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromInputDependsLabel(addressLabel),
                    enterTextPlaceholder,
                    "Address placeholder text is incorrect");
        }
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(REGISTRATION_TYPE_LABEL),
                "Registration type label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromInputDependsLabel(REGISTRATION_TYPE_LABEL),
                PRE_FILLED_OPTION_REGISTRATION_OPTION,
                "Pre-filled registration option has incorrect text");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(ENROLLMENT_TYPE),
                "Enrollment type label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromInputDependsLabel(ENROLLMENT_TYPE),
                ENROLLMENT_TYPE_PLACEHOLDER_TEXT,
                "Enrollment type placeholder text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(languageLabel),
                "Language label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromInputDependsLabel(languageLabel),
                selectFromTheListPlaceholder,
                "Language placeholder text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(tagsLabel),
                "Tags label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromSpanDependsLabel(tagsLabel),
                enterTextPlaceholder,
                "Tags placeholder text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(skillLabel),
                "Skill label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromDivWithClassPlaceholderDependsLabel(skillLabel),
                selectFromTheListPlaceholder,
                "Skill placeholder text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(startLabel),
                "Start label is incorrect or not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isDatePickerButtonDependsLabelDisplayed(startLabel),
                "Start time picker is not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(endLabel),
                "End label is incorrect or not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isDatePickerButtonDependsLabelDisplayed(endLabel),
                "End time picker is not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelWithAsteriskDisplayed(emailLabel),
                "Contact email label is incorrect or not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromInputDependsLabel(emailLabel),
                enterEmailPlaceholder,
                "Email placeholder text is incorrect");
        if (Objects.equals(formatOption, "Online")) {
            softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(linkToEventLabel),
                    "Link to event label is incorrect or not displayed");
            softAssert.assertTrue(reactEventGeneralInfoScreen.isToolTipIconDependsLabelDisplayed(linkToEventLabel),
                    "Link to event tooltip is not displayed");
            softAssert.assertEquals(reactEventGeneralInfoScreen.getPlaceholderFromInputDependsLabel(linkToEventLabel),
                    LINK_PLACEHOLDER_TEXT,
                    "Link to event placeholder text is incorrect");
        }
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(eventCoverLabel),
                "Event cover label is incorrect or not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isToolTipIconDependsLabelDisplayed(eventCoverLabel),
                "Event cover tooltip is not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getTextButtonDependsLabel(eventCoverLabel),
                eventCoverPlaceholderText,
                "Event cover button text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(eventCoverMiniatureLabel),
                "Event cover miniature label is incorrect or not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isToolTipIconDependsLabelDisplayed(eventCoverMiniatureLabel),
                "Event cover miniature tooltip is not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getTextButtonDependsLabel(eventCoverMiniatureLabel),
                eventCoverMiniaturePlaceholderText,
                "Event cover miniature button text is incorrect");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isLabelDisplayed(CUSTOM_IMAGE_LABEL),
                "Custom image label is incorrect or not displayed");
        softAssert.assertTrue(reactEventGeneralInfoScreen.isToolTipIconDependsLabelDisplayed(CUSTOM_IMAGE_LABEL),
                "Custom image tooltip is not displayed");
        softAssert.assertEquals(reactEventGeneralInfoScreen.getTextButtonDependsLabel(CUSTOM_IMAGE_LABEL),
                CUSTOM_IMAGE_PLACEHOLDER_TEXT,
                "Custom image button text is incorrect");
        softAssert.assertAll();
    }
}
