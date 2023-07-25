package com.epmrdpt.regression.profile;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_24555_VerifyOpenInformationTabIsDisplayedInProfessionalInfoTabInProfile",
    groups = {"full", "regression"})
public class EPMRDPT_24555_VerifyOpenInformationTabIsDisplayedInProfessionalInfoTabInProfile {

  @Test(priority = 1)
  public void checkProfilePageLoading() {
    assertTrue(new LoginService()
            .loginAndSetLanguage(UserFactory.asAdmin())
            .clickProfileMenu()
            .clickProfileButton()
            .isScreenLoaded(),
        "Profile page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkOpenInformationTabInProfessionalInfoTabDisplaying() {
    assertTrue(new ProfileScreen().waitTrainingInfoDisplayed().clickProfessionalInfoButton()
            .isOpenInformationTabDisplayed(),
        "'Open Information' tab isn't displayed under 'Professional Info' tab!");
  }
}
