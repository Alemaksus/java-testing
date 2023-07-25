package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_LINK_COPIED_SUCCESSFULLY_POP_UP;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.EventRegistrationUserFormPopUpScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.ClipboardUtils;
import com.epmrdpt.utils.StringUtils;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97322_VerifyThatUserCanRegisterForTheEventWithClosedRegistrationType",
    groups = {"full", "smoke"})
public class EPMRDPT_97322_VerifyThatUserCanRegisterForTheEventWithClosedRegistrationType {

  private static final String EVENT_NAME = "AutoTestClosedRegistration97322";
  private static final String COUNTRY = "AutoTestCountry";
  private static final String CITY = "AutoTestCity";
  private static final String DESCRIPTION_TEXT = StringUtils.generateRandomPassword();
  private boolean isUserRegistered;
  private EventRegistrationUserFormPopUpScreen eventRegistrationUserFormPopUpScreen;
  private ReactEventDetailsScreen reactEventDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    reactEventDetailsScreen = new LoginService()
        .loginAndSetLanguage(
            asEventManager())
        .clickEventsManagementLink()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton();
  }

  @Test(priority = 1)
  public void checkRegistrationLinkCopiedFromClipboard() {
    reactEventDetailsScreen.waitCopyRegistrationLinkButtonClickable()
        .clickCopyRegistrationLinkButton();
    assertEquals(reactEventDetailsScreen.getCopyRegistrationLinkStatusMessageText(),
        getValueOf(DETAIL_TRAINING_SCREEN_LINK_COPIED_SUCCESSFULLY_POP_UP),
        "Registration link is not copied!");
  }

  @Test(priority = 2)
  public void checkUserLogIn() {
    reactEventDetailsScreen.closeBrowser();
    new HeaderScreen().openPage(getEnv());
    assertTrue(new LoginService()
        .loginAndSetLanguage(withSimplePermission())
        .isProfilePhotoImageDisplayed(), "User did not log in.");
  }

  @Test(priority = 3)
  public void checkRegistrationPopUpIsOpened() {
    new HeaderScreen().openPage(ClipboardUtils.pasteCopiedText());
    eventRegistrationUserFormPopUpScreen = new EventPreviewScreen()
        .waitRegistrationButtonDisplayed()
        .clickRegistrationButton();
    assertTrue(eventRegistrationUserFormPopUpScreen.isScreenLoaded(),
        "Registration pop-up did not open!");
  }

  @Test(priority = 4)
  public void checkUserRegistered() {
    isUserRegistered = eventRegistrationUserFormPopUpScreen.clickCountryDropDown()
        .chooseCountryName(COUNTRY)
        .clickCityDropDown()
        .clickCityItemByName(CITY)
        .typeSurveyAnswerByName(DESCRIPTION_TEXT)
        .clickRegistrationButtonAndSuccessPopUp()
        .isCancelRegistrationButtonDisplayed();
    assertTrue(isUserRegistered, "User was not registered for the Event!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void cancelRegistration(Method method) {
    if (method.getName()
        .equals("checkUserRegistered") & isUserRegistered) {
      new EventPreviewScreen().clickCancelRegistrationButtonIfNeedByReason(DESCRIPTION_TEXT);
    }
  }
}
