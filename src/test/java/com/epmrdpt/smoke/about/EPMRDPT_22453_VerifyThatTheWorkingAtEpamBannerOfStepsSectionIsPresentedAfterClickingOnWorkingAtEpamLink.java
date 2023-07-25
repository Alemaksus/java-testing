package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22453_VerifyThatTheWorkingAtEpamBannerOfStepsSectionIsPresentedAfterClickingOnWorkingAtEpamLink",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22453_VerifyThatTheWorkingAtEpamBannerOfStepsSectionIsPresentedAfterClickingOnWorkingAtEpamLink {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkThatAboutPageLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkWorkingAtEpamSectionDisplayingAfterRedirecting() {
    assertTrue(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .isWorkingAtEpamSectionDisplayed(),
        "Working at epam section isn't displayed!");
  }
}
