package com.epmrdpt.smoke.homeheader;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.Test;

@Test(
    description = "EPMRDPT_22402_VerifyAfterClickingOnSignInButtonIAMSignInFormIsDisplayed",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22402_VerifyAfterClickingOnSignInButtonIAMSignInFormIsDisplayed {

  @Test
  public void checkIAMSignInFormLoading() {
    assertTrue(new HeaderScreen().clickSignInButton().isScreenLoaded(),
        "'IAM sign in form isn't loaded!");
  }
}
