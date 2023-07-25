package com.epmrdpt.smoke.homeheader;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_21899_VerifyTheHeaderSectionContent",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_21899_VerifyTheHeaderSectionContent {

  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test
  public void checkEpamLogoDisplayed() {
    assertTrue(headerScreen.isEpamLogoDisplayed(), "Epam Logo isn't displayed!");
  }

  @Test
  public void checkUniversityProgramLogoDisplayed() {
    assertTrue(
        headerScreen.isUniversityProgramLogoDisplayed(),
        "University program Logo isn't displayed!");
  }

  @Test
  public void checkTrainingListNavigationLinkDisplayed() {
    assertTrue(
        headerScreen.isTrainingListNavigationLinkDisplayed(),
        "Training list link isn't displayed!");
  }

  @Test
  public void checkAboutNavigationLinkDisplayed() {
    assertTrue(headerScreen.isAboutNavigationLinkDisplayed(), "About link isn't displayed!");
  }

  @Test
  public void checkBlogNavigationLinkDisplayed() {
    assertTrue(headerScreen.isBlogNavigationLinkDisplayed(), "News link isn't displayed!");
  }

  @Test
  public void checkFaqNavigationLinkDisplayed() {
    assertTrue(headerScreen.isFaqNavigationLinkDisplayed(), "Faq link isn't displayed!");
  }

  @Test
  public void checkGlobeIconDisplayed() {
    assertTrue(headerScreen.isLanguageControlDropdownDisplayed(), "Globe icon isn't displayed!");
  }

  @Test
  public void checkSignInButtonDisplayed() {
    assertTrue(headerScreen.isSignInButtonDisplayed(), "Sign In Button isn't displayed!");
  }
}
