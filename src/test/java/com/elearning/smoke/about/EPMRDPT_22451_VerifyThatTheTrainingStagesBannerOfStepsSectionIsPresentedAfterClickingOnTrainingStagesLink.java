package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22451_VerifyThatTheTrainingStagesBannerOfStepsSectionIsPresentedAfterClickingOnTrainingStagesLink",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22451_VerifyThatTheTrainingStagesBannerOfStepsSectionIsPresentedAfterClickingOnTrainingStagesLink {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkThatAboutPageLoaded() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatTrainingStagesSectionDisplayedAfterClickOnIt() {
    aboutScreen.clickTrainingLink();
    assertTrue(aboutScreen.waitUntilTrainingStagesSectionLoaded().isTrainingStagesSectionDisplay(),
        "Training stages section isn't displayed after redirect!");
  }
}
