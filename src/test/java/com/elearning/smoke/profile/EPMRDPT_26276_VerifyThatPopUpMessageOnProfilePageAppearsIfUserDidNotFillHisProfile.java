package com.epmrdpt.smoke.profile;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_26276_VerifyThatPopUpMessageOnProfilePageAppearsIfUserDidNotFillHisProfile",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_26276_VerifyThatPopUpMessageOnProfilePageAppearsIfUserDidNotFillHisProfile {

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkUserLogIn() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndEmptyProfile())
        .waitProfileMenuDisplayed()
        .clickProfileMenu()
        .clickProfileButton();
    assertTrue(new ProfileScreen().waitPopUpAppear().isConfirmInformationPopUpDisplayed(),
        "'Confirm Information' pop-up isn't displayed!");
  }
}
