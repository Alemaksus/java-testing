package com.epmrdpt.regression.events_management;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epmrdpt.bo.user.UserFactory.asStudentWithNotifications;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

@Test(description = "EPMRDPT_102795_VerifyMultipleVideosAreDisplayedForTheUserInCarousel",
    groups = {"full", "regression"})
public class EPMRDPT_102795_VerifyMultipleVideosAreDisplayedForTheUserInCarousel {

  private static final String EVENT_NAME = "AutoTestEventWithRecording";
  private EventPreviewScreen eventPreviewScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void openEventPage() {
    eventPreviewScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentWithNotifications())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickEventsNavigationLink()
        .clickPassedEventsButton()
        .waitEventCardsVisibility()
        .clickEventByName(EVENT_NAME);
  }
  @Test
  public void verifyEventRecordingIsDisplayed() {
    assertTrue(eventPreviewScreen.isEventRecordingDisplayed(), "Event recordings is not present!");
  }
  @Test
  public void verifySliderArrowNextButtonIsDisplayed() {
    assertTrue(eventPreviewScreen.isSliderNextArrowButtonDisplayed(), "'Slider Arrow Next' Button is not present!");
  }
  @Test
  public void verifyVideosAreRotatedInCarousel() {
    String initialVideoUrl = eventPreviewScreen.getCurrentVideoUrl();
    eventPreviewScreen.clickSliderArrowNextButton();
    String afterClickVideoUrl = eventPreviewScreen.getCurrentVideoUrl();
    assertNotEquals(initialVideoUrl, afterClickVideoUrl,
        "Video URL did not change after clicking the next button!");
  }
}
