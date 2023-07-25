package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.EventRegistrationUserFormPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90306_VerifyThatUserCanRegisterIfAllRequiredFieldsAreFilled",
    groups = {"full", "smoke"})
public class EPMRDPT_90306_VerifyThatUserCanRegisterIfAllRequiredFieldsAreFilled {

  private static final String EVENT_NAME = "NEW_TEST_EVENT_ONE";
  private static final String DESCRIPTION_TEXT = StringUtils.generateRandomPassword();
  private static final String COUNTRY = "AutoTestCountry";
  private static final String CITY = "AutoTestCity";

  private EventPreviewScreen eventPreviewScreen;
  private EventRegistrationUserFormPopUpScreen eventRegistrationUserFormPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    eventPreviewScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(withSimplePermission())
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(EVENT_NAME)
        .clickCancelRegistrationButtonIfNeedByReason(DESCRIPTION_TEXT);
  }

  @Test(priority = 1)
  public void checkEventDetailsPageIsOpen() {
    assertTrue(eventPreviewScreen.isScreenLoaded(), "Event details page isn't open!");
  }

  @Test(priority = 2)
  public void checkEventPreviewPageIsOpen() {
    eventRegistrationUserFormPopUpScreen = eventPreviewScreen.clickRegistrationButton();
    assertTrue(eventRegistrationUserFormPopUpScreen.isScreenLoaded(),
        "Registration pop-up is open!");
  }

  @Test(priority = 3)
  public void checkUserRegistered() {
    eventRegistrationUserFormPopUpScreen.clickCountryDropDown()
        .chooseCountryName(COUNTRY)
        .clickCityDropDown()
        .clickCityItemByName(CITY)
        .typeSurveyAnswerByName(DESCRIPTION_TEXT)
        .clickRegistrationButtonAndSuccessPopUp();
    assertTrue(eventPreviewScreen.isCancelRegistrationButtonDisplayed(),
        "User isn't registered for the Event!");
  }
}
