package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ATTENDEES_CONTACT_PHONE_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ATTENDEES_EMAIL_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ATTENDEES_FULL_NAME_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ATTENDEES_LOCATION_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ATTENDEES_STATUS_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ATTENDEES_SURVEY_TEXT;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactEventAttendeesScreen;
import com.epmrdpt.screens.ReactEventsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90283_VerifyTheColumnsInAttendeesTable",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_90283_VerifyTheColumnsInAttendeesTable {

  private String expectedFullNameText = getValueOf(REACT_EVENTS_MANAGEMENT_ATTENDEES_FULL_NAME_TEXT);
  private String expectedEmailText = getValueOf(REACT_EVENTS_MANAGEMENT_ATTENDEES_EMAIL_TEXT);
  private String expectedContactPhoneText = getValueOf(REACT_EVENTS_MANAGEMENT_ATTENDEES_CONTACT_PHONE_TEXT);
  private String expectedLocationText = getValueOf(REACT_EVENTS_MANAGEMENT_ATTENDEES_LOCATION_TEXT);
  private String expectedStatusText = getValueOf(REACT_EVENTS_MANAGEMENT_ATTENDEES_STATUS_TEXT);
  private String expectedSurveyText = getValueOf(REACT_EVENTS_MANAGEMENT_ATTENDEES_SURVEY_TEXT);
  private ReactEventAttendeesScreen reactEventAttendeesScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink();
    reactEventAttendeesScreen = new ReactEventsManagementScreen()
        .clickFirstEventInList()
        .clickAttendeesButton();
  }

  @Test
  public void checkFullNameTitle() {
    assertEquals(
        reactEventAttendeesScreen.getFullNameColumnText(), expectedFullNameText,
        "Wrong title of full name column"
    );
  }

  @Test
  public void checkEmailTitle() {
    assertEquals(
        reactEventAttendeesScreen.getEmailColumnText(), expectedEmailText,
        "Wrong title of email column"
    );
  }

  @Test
  public void checkContactPhoneTitle() {
    assertEquals(
        reactEventAttendeesScreen.getContactPhoneColumnText(), expectedContactPhoneText,
        "Wrong title of contact phone column"
    );
  }

  @Test
  public void checkLocationTitle() {
    assertEquals(
        reactEventAttendeesScreen.getLocationColumnText(), expectedLocationText,
        "Wrong title of location column"
    );
  }

  @Test
  public void checkStatusTitle() {
    assertEquals(
        reactEventAttendeesScreen.getStatusColumnText(), expectedStatusText,
        "Wrong title of status column"
    );
  }

  @Test
  public void checkSurveyTitle() {
    assertEquals(
        reactEventAttendeesScreen.getSurveyColumnText(), expectedSurveyText,
        "Wrong title of survey column"
    );
  }
}
