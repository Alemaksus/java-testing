package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22449_VerifyThatTheInterviewStagesBannerOfStepsSectionIsPresentedAfterClickingOnInterviewStagesLink",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22449_VerifyThatTheInterviewStagesBannerOfStepsSectionIsPresentedAfterClickingOnInterviewStagesLink {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "Interview stages section isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatInterviewStagesSectionDisplayedAfterRedirect() {
    assertTrue(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .isInterviewSectionDisplayed(),
        "Interview stages section isn't displayed!");
  }
}
