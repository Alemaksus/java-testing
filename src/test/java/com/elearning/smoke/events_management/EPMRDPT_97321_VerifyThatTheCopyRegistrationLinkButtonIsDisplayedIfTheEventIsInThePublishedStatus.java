package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97321_VerifyThatTheCopyRegistrationLinkButtonIsDisplayedIfTheEventIsInThePublishedStatus",
    groups = {"full", "smoke"})
public class EPMRDPT_97321_VerifyThatTheCopyRegistrationLinkButtonIsDisplayedIfTheEventIsInThePublishedStatus {

  private static final String PUBLISHED_EVENT_WITH_CLOSED_REGISTRATION = "Published ClosedRegistration AUTOTEST";

  private ReactEventDetailsScreen reactEventDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToPublishedEventDetailsWithClosedRegistration() {
    reactEventDetailsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink()
        .typeEventName(PUBLISHED_EVENT_WITH_CLOSED_REGISTRATION)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(PUBLISHED_EVENT_WITH_CLOSED_REGISTRATION)
        .clickDetailsButton();
  }

  @Test
  public void checkCopyRegistrationLinkButtonIsPresent() {
    assertTrue(reactEventDetailsScreen.isCopyRegistrationLinkButtonPresent(),
        "The 'Copy registration link' button is not present on the page");
  }
}
