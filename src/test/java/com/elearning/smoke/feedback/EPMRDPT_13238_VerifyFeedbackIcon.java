package com.epmrdpt.smoke.feedback;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FeedbackScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13238_VerifyFeedbackIcon",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_13238_VerifyFeedbackIcon {

  private FeedbackScreen feedbackScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    feedbackScreen = new FeedbackScreen();
  }

  @Test(priority = 1)
  public void checkFeedbackIconDisplayed() {
    assertTrue(feedbackScreen.isFeedbackButtonDisplayed(), "Feedback icon isn't displayed!");
  }

  @Test(priority = 2)
  public void checkFeedbackButtonIsHighlightedWhenHoverOver() {
    assertFalse(feedbackScreen.getFeedbackButtonColor()
            .equals(feedbackScreen.getHoveredFeedbackButtonColor()),
        "Feedback button doesn't change color!");
  }

  @Test(priority = 3)
  public void checkFeedbackFormAppearsAfterClickingFeedbackButtonDisplayed() {
    assertTrue(
        feedbackScreen.clickFeedbackButton().isScreenLoaded(),
        "Feedback form doesn't appear after clicking feedback button!");
  }
}
