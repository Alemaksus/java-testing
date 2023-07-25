package com.epmrdpt.smoke.applications;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_26002_VerifyTheContentOfInactiveTabOfApplicationsPageIfUserHasNoTrainings",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_26002_VerifyTheContentOfInactiveTabOfApplicationsPageIfUserHasNoTrainings {

  private ApplicationsScreen applicationsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    applicationsScreen = new ApplicationsScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatRegisteredUserHasAccessToApplicationsPage() {
    assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                UserFactory.withSimplePermissionAndWithoutTrainingsNew())
            .clickProfileMenu().waitDropDownDisplayed().clickApplicationsButton().isScreenLoaded(),
        "Registered user hasn't access to applications page!");
  }

  @Test(priority = 4)
  public void checkThatInactiveSectionHasCorrectTitleIfUserHasNoTrainings() {
    String actualTitle = applicationsScreen.waitScreenLoading()
        .getActiveSectionStatusTitleText();
    String expectedTitle = LocaleProperties
        .getValueOf(LocalePropertyConst.APPLICATIONS_INACTIVE_TITLE);
    assertEquals(actualTitle, expectedTitle,
        format("Active title isn't correct! Actual %s, expected %s", actualTitle, expectedTitle));
  }

  @Test(priority = 3)
  public void checkThatInactiveSectionHasCorrectDescriptionIfUserHasNoTrainings() {
    String actualDescription = applicationsScreen.waitScreenLoading()
        .clickInactiveTab()
        .getActiveSectionStatusFallbackText();
    String expectedDescription = LocaleProperties
        .getValueOf(LocalePropertyConst.APPLICATIONS_INACTIVE_DESCRIPTION);
    assertEquals(actualDescription, expectedDescription,
        format("Active description isn't correct! Actual %s, expected %s", actualDescription,
            expectedDescription));
  }
}
