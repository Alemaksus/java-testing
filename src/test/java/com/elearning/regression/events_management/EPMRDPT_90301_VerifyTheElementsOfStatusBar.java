package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90301_VerifyTheElementsOfStatusBar",
    groups = {"full", "regression"})
public class EPMRDPT_90301_VerifyTheElementsOfStatusBar {

  private static final String EVENT_NAME = "EventForPreviewVerification";

  private ReactEventDetailsScreen eventDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToEventDetailsScreen() {
    eventDetailsScreen = new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asEventManager())
        .clickEventsManagementLink()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton();
  }

  @Test
  public void checkDraftStatus() {
    assertFalse(eventDetailsScreen.isDraftStatusButtonClickable(),
        "The draft status button should not be clickable!");
  }

  @Test
  public void checkPublishedStatus() {
    assertTrue(eventDetailsScreen.isPublishedStatusButtonClickable(),
        "The published status button should be clickable!");
  }

  @Test
  public void checkPassedStatus() {
    assertFalse(eventDetailsScreen.isPassedStatusButtonClickable(),
        "The passed status button should not be clickable!");
  }
}
