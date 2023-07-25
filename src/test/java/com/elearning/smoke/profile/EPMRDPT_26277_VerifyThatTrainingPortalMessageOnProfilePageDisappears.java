package com.epmrdpt.smoke.profile;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_26277_VerifyThatTrainingPortalMessageOnProfilePageDisappears",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_26277_VerifyThatTrainingPortalMessageOnProfilePageDisappears {

  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    profileScreen = new ProfileScreen();
  }

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
    assertTrue(profileScreen.isConfirmInformationPopUpDisplayed(),
        "'Confirm Information' pop-up isn't displayed!");
  }

  @Test(priority = 3)
  public void checkConfirmInformationPopUpDisappears() {
    assertFalse(profileScreen.clickConfirmInformationPopUpOkButton().waitPopUpDisappear()
            .isConfirmInformationPopUpDisplayedNotWait(),
        "'Confirm Information' pop-up don't appear!");
  }
}
