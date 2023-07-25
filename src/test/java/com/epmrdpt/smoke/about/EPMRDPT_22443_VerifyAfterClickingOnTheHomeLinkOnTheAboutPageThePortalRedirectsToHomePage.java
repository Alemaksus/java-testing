package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22443_VerifyAfterClickingOnTheHomeLinkOnTheAboutPageThePortalRedirectsToHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22443_VerifyAfterClickingOnTheHomeLinkOnTheAboutPageThePortalRedirectsToHomePage {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page didn't load!");
  }

  @Test(priority = 2)
  public void checkBackButtonRedirectFromAboutPageOnPreviousPage() {
    assertTrue(aboutScreen.clickHomeButton().isScreenLoaded(),
        "Back button doesn't redirect from About page on home page!");
  }
}
