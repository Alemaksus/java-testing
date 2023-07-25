package com.epmrdpt.smoke.home;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13235_VerifyThatRegisteredUserCanSignOut",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13235_VerifyThatRegisteredUserCanSignOut {

  @Test
  public void checkRegisteredUserCanSignOut() {
    assertTrue(new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                UserFactory.withSimplePermission())
            .clickProfileMenu()
            .signOut()
            .isSignInButtonDisplayed(),
        "Registered User can't sign out!");
  }
}
