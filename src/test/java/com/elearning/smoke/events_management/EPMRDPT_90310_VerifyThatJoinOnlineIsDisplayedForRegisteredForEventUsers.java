package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static java.time.LocalDateTime.now;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.ReactDetailTrainingPopUpScreen;
import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.services.EventRegistrationService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90310_VerifyThatJoinOnlineIsDisplayedForRegisteredForEventUsers",
    groups = {"full", "smoke"})
public class EPMRDPT_90310_VerifyThatJoinOnlineIsDisplayedForRegisteredForEventUsers {

  private static final String PUBLISHED_EVENT_WITH_LINK_TO_EVENT_REGISTRATION = "Published WithLinkToEvent  AUTOTEST";
  private static final LocalDateTime EVENT_START_TIME = now().plusMinutes(45);
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
  private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
  private static final String COUNTRY_NAME = "AutoTestCountry";
  private static final String CITY_NAME = "AutoTestCity";
  private static final String SURVEY_ANSWER = "TestSurveyAnswer";
  private static final String CANCELLATION_REASON = "TestCancellationReason";

  private EventPreviewScreen eventPreviewScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRegisterToEventWithLessThanHourBeforeStart() {
    ReactLoginService reactLoginService = new ReactLoginService();
    reactLoginService
        .loginAndGoToReactEventsManagementScreen(asEventManager())
        .typeEventName(PUBLISHED_EVENT_WITH_LINK_TO_EVENT_REGISTRATION)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(PUBLISHED_EVENT_WITH_LINK_TO_EVENT_REGISTRATION)
        .clickDetailsButton()
        .clickGeneralInfoButton()
        .clearStartDateInputField()
        .typeStartDate(EVENT_START_TIME.format(DATE_FORMAT))
        .clearStartTimeInputField()
        .typeStartTime(EVENT_START_TIME.format(TIME_FORMAT));
    new ReactEventDetailsScreen().clickSaveChangesButton()
        .isSaveChangesPopupDisplayed();
    new ReactDetailTrainingPopUpScreen()
        .closeNotificationPopUp();
    reactLoginService.signOut();
    eventPreviewScreen = new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermission())
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(PUBLISHED_EVENT_WITH_LINK_TO_EVENT_REGISTRATION)
        .clickCancelRegistrationButtonIfNeedByReason(CANCELLATION_REASON)
        .clickRegistrationButton()
        .clickCountryDropDown()
        .chooseCountryName(COUNTRY_NAME)
        .clickCityDropDown()
        .clickCityItemByName(CITY_NAME)
        .typeSurveyAnswerByName(SURVEY_ANSWER)
        .clickRegistrationButtonAndSuccessPopUp();
  }

  @Test
  public void checkThatJoinOnlineButtonIsPresent() {
    assertTrue(eventPreviewScreen.isJoinOnlineButtonPresent(),
        "The 'Join online' button is not present on the page");
  }

  @AfterMethod
  public void cancelRegistration() {
    new EventRegistrationService().cancelRegistrationToTheEvent(CANCELLATION_REASON);
  }
}
