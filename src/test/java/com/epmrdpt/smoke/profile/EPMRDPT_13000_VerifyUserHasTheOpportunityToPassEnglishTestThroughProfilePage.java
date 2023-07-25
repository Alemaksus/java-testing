package com.epmrdpt.smoke.profile;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13000_VerifyThatUserHasTheOpportunityToPassEnglishTestFromTheProfilePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13000_VerifyUserHasTheOpportunityToPassEnglishTestThroughProfilePage {

  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupProfilePage() {
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithoutTrainingsNew())
        .clickProfileMenu()
        .clickProfileButton();
  }

  @Test(priority = 1)
  public void checkTakeTestButtonDisplayed() {
    assertTrue(profileScreen.isEnglishTestButtonDisplayed(),
        "'Take test' on 'Profile' page isn't displayed!");
  }

  @Test(priority = 2)
  public void checkUserHasTheOpportunityToPassEnglishTestThroughProfilePage() {
    assertTrue(profileScreen.clickEnglishTestButtonIfExists()
            .isRedirectToEnglishTestPopUpDisplayed(),
        "User hasn't opportunity to pass English test through 'Profile' page!");
  }
}
