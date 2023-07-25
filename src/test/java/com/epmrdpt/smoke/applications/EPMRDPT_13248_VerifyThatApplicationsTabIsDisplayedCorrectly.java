package com.epmrdpt.smoke.applications;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13248_VerifyThatApplicationsTabIsDisplayedCorrectly",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_13248_VerifyThatApplicationsTabIsDisplayedCorrectly {

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatUserHasAccessToApplicationPage() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithTraining())
        .waitProfileMenuDisplayed()
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickApplicationsButton();
    assertTrue(new ApplicationsScreen().isScreenLoaded(), "Applications page doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkThatContentApplicationPageDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    ApplicationsScreen applicationsScreen = new ApplicationsScreen();
    softAssert.assertTrue(applicationsScreen.isApplicationsTitleDisplayed(),
        "Application title isn't displayed!");
    softAssert.assertTrue(applicationsScreen.isActiveTabDisplayed(), "Active tab isn't displayed!");
    softAssert
        .assertTrue(applicationsScreen.isInactiveTabDisplayed(), "Inactive tab isn't displayed!");
    softAssert
        .assertTrue(applicationsScreen.isCompletedTabDisplayed(), "Completed tab isn't displayed!");
    softAssert.assertAll();
  }
}
