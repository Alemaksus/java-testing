package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;

import com.epmrdpt.screens.ReactEventAutoreplyScreen;
import com.epmrdpt.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_100731_VerifyTemplateSectionForAutoEnrollmentEventWithOnlineType",
    groups = {"full", "regression", "manager"})

public class EPMRDPT_100731_VerifyTemplateSectionForAutoEnrollmentEventWithOnlineType {

  private static final String EVENT_NAME = "Auto-enrollment custom notifications with link";
  private ReactEventAutoreplyScreen reactEventAutoreplyScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    reactEventAutoreplyScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink()
        .waitAllSpinnersAreNotDisplayed()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton()
        .clickAutoreplyButton();
  }

  @Test()
  public void verifyThanksForRegistrationTemplateDisplayed() {
    Assert.assertTrue(reactEventAutoreplyScreen.isThanksForRegistrationTemplateDisplayed(),
        "'Thanks for the registration' template is not displayed!");
  }

  @Test()
  public void verifyDayLeftBeforeEventStartTemplateDisplayed() {
    Assert.assertTrue(reactEventAutoreplyScreen.isDayLeftBeforeEventStartTemplateDisplayed(),
        "'24h left before the event start' template is not displayed!");
  }

  @Test()
  public void verifyEventComingSoonTemplateDisplayed() {
    Assert.assertTrue(reactEventAutoreplyScreen.isEventComingSoonTemplateDisplayed(),
        "'Event is coming soon' template is not displayed!");
  }

  @Test()
  public void verifyEventCancelledTemplateDisplayed() {
    Assert.assertTrue(reactEventAutoreplyScreen.isEventCancelledTemplateDisplayed(),
        "'Event is cancelled' template is not displayed!");
  }

  @Test()
  public void verifyEventRecordingAvailableTemplateDisplayed() {
    Assert.assertTrue(reactEventAutoreplyScreen.isEventRecordingAvailableTemplateDisplayed(),
        "'Event recording is available' template is not displayed!");
  }
}
