package com.epmrdpt.smoke.center_management;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactUserDetailsScreen;
import com.epmrdpt.screens.ReactUserManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97280_VerifyThatEditUserIsOpenAfterClickingUserName",
    groups = {"full", "smoke"})
public class EPMRDPT_97280_VerifyThatEditUserIsOpenAfterClickingUserName {

  private ReactUserManagementScreen reactUserManagementScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUpUserManagementScreen() {
    reactUserManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait
            (UserFactory.asUserManager())
        .clickReactUserManagementLink()
        .waitScreenLoading();
  }

  @Test
  public void checkUserIsRedirectedToUserDetailsScreen() {
    reactUserManagementScreen.clickFirstUserButton()
        .switchToLastWindow();
    Assert.assertTrue(new ReactUserDetailsScreen().isScreenLoaded(),
        "Manager isn't redirected to User List!");
  }
}
