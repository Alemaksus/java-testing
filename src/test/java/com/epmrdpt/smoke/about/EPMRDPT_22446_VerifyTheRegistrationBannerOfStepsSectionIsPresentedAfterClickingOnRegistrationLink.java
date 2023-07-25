package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22446_VerifyTheRegistrationBannerOfStepsSectionIsPresentedAfterClickingOnRegistrationLink",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22446_VerifyTheRegistrationBannerOfStepsSectionIsPresentedAfterClickingOnRegistrationLink {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About us page didn't load!");
  }

  @Test(priority = 2)
  public void checkThatRegistrationSectionDisplayedAfterClickOnIt() {
    assertTrue(aboutScreen.clickRegistrationLink().isRegistrationSectionDisplayed(),
        "Registration Section wasn't display!");
  }
}
