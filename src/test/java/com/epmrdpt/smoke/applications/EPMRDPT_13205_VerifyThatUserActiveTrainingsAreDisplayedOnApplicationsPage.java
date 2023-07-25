package com.epmrdpt.smoke.applications;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13205_VerifyThatUserActiveTrainingsAreDisplayedOnApplicationsPage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_13205_VerifyThatUserActiveTrainingsAreDisplayedOnApplicationsPage {

  private ApplicationsScreen applicationsScreen;

  @Test(priority = 1)
  public void checkThatUserHasAccessToApplicationPage() {
    assertTrue(
        new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                UserFactory.withSimplePermissionAndWithTraining())
            .clickProfileMenu().waitDropDownDisplayed().clickApplicationsButton().isScreenLoaded(),
        "Applications page doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatActiveTabIsDisplayedByDefault() {
    applicationsScreen = new ApplicationsScreen();
    assertTrue(applicationsScreen.isActiveTabDisplayedByDefault(),
        "Active tab isn't displayed by default!");
  }

  @Test(priority = 3)
  public void checkThatActiveTrainingListDisplayed() {
    assertTrue(applicationsScreen.isTrainingListDisplayed(),
        "Training list on Active tab isn't displayed!");
  }
}
