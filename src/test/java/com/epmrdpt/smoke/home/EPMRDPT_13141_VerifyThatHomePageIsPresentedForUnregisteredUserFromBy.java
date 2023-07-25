package com.epmrdpt.smoke.home;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13141_VerifyThatHomePageIsPresentedForUnregisteredUserFromBy",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13141_VerifyThatHomePageIsPresentedForUnregisteredUserFromBy {

  @Test
  public void checkLogoIsDisplayed() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "The home page don't presented!");
  }
}
