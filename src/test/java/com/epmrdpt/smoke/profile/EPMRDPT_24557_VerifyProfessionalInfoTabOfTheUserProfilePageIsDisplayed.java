package com.epmrdpt.smoke.profile;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_24557_VerifyProfessionalInfoTabOfTheUserProfilePageIsDisplayed",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_24557_VerifyProfessionalInfoTabOfTheUserProfilePageIsDisplayed {

  @Test
  public void checkUserLogIn() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asAdmin());
    assertTrue(
        new HeaderScreen().clickProfileMenu().clickProfileButton().clickProfessionalInfoButton()
            .isScreenLoaded(), "Professional info tab page isn't loaded!");
  }
}
