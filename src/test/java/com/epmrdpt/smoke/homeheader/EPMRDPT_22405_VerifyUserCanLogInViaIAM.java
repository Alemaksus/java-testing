package com.epmrdpt.smoke.homeheader;


import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(
    description = "EPMRDPT_22405_VerifyUserCanLogInViaIAM",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22405_VerifyUserCanLogInViaIAM {

  @Test
  public void checkUserCanLogInViaIAM() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(UserFactory.asTrainer())
            .isProfilePhotoImageDisplayed(),
        "User can't log in via IAM!");
    softAssert.assertTrue(new HeaderScreen().isScreenLoaded(),
        "User isn't redirected to Home page!");
    softAssert.assertAll();
  }
}
