package com.epmrdpt.regression.events_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94062_VerifyTheLinkToEventRecordingIsAvailableFromTheUserSide",
    groups = {"full", "general", "regression"})
public class EPMRDPT_94062_VerifyTheLinkToEventRecordingIsAvailableFromTheUserSide {

  private final static String EVENT_NAME = "VK event with description";
  private EventPreviewScreen eventPreviewScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToEventPreviewScreen() {
    eventPreviewScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(UserFactory.withSimplePermission())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickEventsNavigationLink()
        .waitEventCardsVisibility()
        .clickPassedEventsShowMoreButton()
        .clickEventByName(EVENT_NAME);
  }

  @Test
  public void checkVideoPlayerPlayButtonIsDisplayed() {
    assertTrue(eventPreviewScreen.isVideoPlayerDisplayed(),
        "The link to event recording is not available from the user side because of video player is not displayed!");
  }
}
