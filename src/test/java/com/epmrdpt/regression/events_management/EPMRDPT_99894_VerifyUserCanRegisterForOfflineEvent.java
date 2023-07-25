package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.EventRegistrationUserFormPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_99894_VerifyUserCanRegisterForOfflineEvent",
    groups = {"full", "regression"})
public class EPMRDPT_99894_VerifyUserCanRegisterForOfflineEvent {

  private static final String CITY = "AutoTestCity";
  private static final String COUNTRY = "AutoTestCountry";
  private static final String DESCRIPTION_TEXT = StringUtils.generateRandomPassword();
  private static final String EVENT_NAME = "VM offline";
  private boolean isUserRegistered = false;
  private EventPreviewScreen eventPreviewScreen;
  private EventRegistrationUserFormPopUpScreen eventRegistrationUserFormPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void signInUser() {
    eventPreviewScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermission())
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(EVENT_NAME);
  }

  @Test(priority = 1)
  public void checkRegistrationFormIsPresented() {
    eventRegistrationUserFormPopUpScreen = eventPreviewScreen.clickRegistrationButton();
    assertTrue(eventRegistrationUserFormPopUpScreen.isScreenLoaded(),
        "Registration form is not open");
  }

  @Test(priority = 2)
  public void checkUserRegisteredAndPopUpPresented() {
    isUserRegistered = eventRegistrationUserFormPopUpScreen.clickCountryDropDown()
        .chooseCountryName(COUNTRY)
        .clickCityDropDown()
        .clickCityItemByName(CITY)
        .typeSurveyAnswerByName(DESCRIPTION_TEXT)
        .clickRegistrationButtonAndSuccessPopUp()
        .isCancelRegistrationButtonDisplayed();
    assertTrue(isUserRegistered, "User is not registered for the Event");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void cancelRegistration(Method method) {
    if (isUserRegistered) {
      new EventPreviewScreen().clickCancelRegistrationButtonIfNeedByReason(DESCRIPTION_TEXT);
    }
  }
}
