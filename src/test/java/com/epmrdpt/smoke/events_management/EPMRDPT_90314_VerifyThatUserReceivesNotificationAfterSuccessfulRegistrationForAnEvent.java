package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asStudentWithNotifications;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_THANKS_FOR_REGISTRATION_TEMPLATE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.EventRegistrationUserFormPopUpScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationScreen;
import com.epmrdpt.services.EventRegistrationService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_90314_VerifyThatUserReceivesNotificationAfterSuccessfulRegistrationForAnEvent",
    groups = {"full", "smoke"})
public class EPMRDPT_90314_VerifyThatUserReceivesNotificationAfterSuccessfulRegistrationForAnEvent {

  private EventRegistrationUserFormPopUpScreen eventRegistrationUserFormPopUpScreen;
  private EventRegistrationService eventRegistrationService;
  private NotificationService notificationService;
  private NotificationScreen notificationScreen;
  private HeaderScreen headerScreen;
  private final String eventName = "EventAutoTestForCheckNotification90314";
  private final String countryName = "AutoTestCountry";
  private final String cityName = "AutoTestCity";
  private final String reasonCancelRegistration = "Test reason";
  private final String surveyAnswer = "Test answer";
  private boolean isUserRegistered = false;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentWithNotifications());
    notificationService = new NotificationService();
    eventRegistrationService = new EventRegistrationService();
    notificationScreen = new NotificationScreen();
    headerScreen = new HeaderScreen();
    while (notificationService.getFirstNotificationText()
        .equals(getValueOf(
            REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_THANKS_FOR_REGISTRATION_TEMPLATE))) {
      notificationService.deleteFirstNotification();
    }
    eventRegistrationUserFormPopUpScreen = notificationScreen
        .clickEventsNavigationLink()
        .waitEventCardsVisibility()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(eventName)
        .clickCancelRegistrationButtonIfNeedByReason(reasonCancelRegistration)
        .clickRegistrationButton();
  }

  @Test(priority = 1)
  public void checkRegistrationPopUpScreenAppears() {
    assertTrue(eventRegistrationUserFormPopUpScreen.isScreenLoaded(),
        "Registration pop up screen is not appears");
  }

  @Test(priority = 2)
  public void checkThatNotificationIsPresentInTheNotificationListAfterSuccessfulRegistration() {
    eventRegistrationUserFormPopUpScreen
        .clickCountryDropDown()
        .chooseCountryName(countryName)
        .clickCityDropDown()
        .chooseCityName(cityName)
        .typeSurveyAnswer(surveyAnswer)
        .clickRegistrationButtonAndSuccessPopUp();
    SoftAssert softAssert = new SoftAssert();
    isUserRegistered = new EventPreviewScreen().isCancelRegistrationButtonDisplayed();
    softAssert.assertTrue(isUserRegistered,
        "User is not registered to the event");
    softAssert.assertEquals(notificationService.getFirstNotificationText(),
        getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_THANKS_FOR_REGISTRATION_TEMPLATE),
        "Notification is not present in the list " +
            "(check that the event has not started, at least 1 hour before start)");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteNotification() {
    if (isUserRegistered) {
      notificationService
          .deleteFirstNotification();
      notificationScreen
          .clickEventsNavigationLink()
          .clickUpcomingEventsShowMoreButton()
          .clickEventByName(eventName);
      eventRegistrationService
          .cancelRegistrationToTheEvent(reasonCancelRegistration);
    }
  }
}
