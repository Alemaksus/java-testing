package com.epmrdpt.smoke.home;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22409_VerifyThatAfterClickingSignInInMenuIAMSignInFormIsDisplayed",
    groups = {"full", "smoke"})
public class EPMRDPT_22409_VerifyThatAfterClickingSignInInMenuIAMSignInFormIsDisplayed {

  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    headerScreen.setWindowSize(991, 600);
  }

  @Test(priority = 1)
  public void checkMenuDropdownDisplay() {
    assertTrue(headerScreen.clickMenuButton().isMenuDropdownDisplayed(),
        "Menu drop-down isn't displayed!");
  }

  @Test(priority = 2)
  public void checkSignInOptionInMenuRedirectionToIAMScreen() {
    assertTrue(headerScreen.clickSignInButtonUnderMenu().isScreenLoaded(),
        "Login screen isn't loaded!");
  }
}
