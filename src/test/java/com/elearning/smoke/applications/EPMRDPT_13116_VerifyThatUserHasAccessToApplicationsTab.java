package com.epmrdpt.smoke.applications;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13116_VerifyThatUserHasAccessToApplicationsTab",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_13116_VerifyThatUserHasAccessToApplicationsTab {

  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkThatItemsInMenuIsDisplayed() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithTraining())
        .clickProfileMenu()
        .waitProfileMenuDisplayed()
        .waitDropDownDisplayed();
    assertTrue(headerScreen.isUserDropDownDisplayed(),
        "Drop down menu isn't presented!");
  }

  @Test(priority = 2)
  public void checkThatUserHasAccessToApplicationsTab() {
    headerScreen.clickApplicationsButton();
    assertTrue(new ApplicationsScreen().isScreenLoaded(),
        "Applications page isn't opened!");
  }
}
