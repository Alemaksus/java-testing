package com.epmrdpt.smoke.training;

import static com.epmrdpt.framework.properties.UserPropertyConst.USER_LOGIN;
import static com.epmrdpt.framework.properties.UserPropertyConst.USER_PASSWORD;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_100743_VerifyTakeTestButtonIsActiveIfEnglishTestHasBeenReassigned",
    groups = {"full", "smoke"})

public class EPMRDPT_100743_VerifyTakeTestButtonIsActiveIfEnglishTestHasBeenReassigned {

  private ProfileScreen profileScreen;
  private User usedUser = new User();

  @BeforeClass
  public void switchToUserProfile() {
    usedUser.setUserName(UserProperty.getValueOf(USER_LOGIN));
    usedUser.setPassword(UserProperty.getValueOf(USER_PASSWORD));
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(usedUser)
        .clickProfileMenu()
        .clickProfileButton();
  }

  @Test
  public void checkTakeTestButtonIsPresent() {
    assertTrue(profileScreen.isTakeTestButtonDisplayed(),
        "The 'Take Test' Button is not presented in the Screen");
  }

  @Test
  public void checkTakeTestButtonIsActive() {
    profileScreen.clickTakeTestButton();
    assertTrue(profileScreen.isConfirmInformationPopUpDisplayed(),
        "The 'Take Test' Button is not active");
  }
}
