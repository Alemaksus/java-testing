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

@Test(description = "EPMRDPT_26000_VerifyTheContentOfApplicationsPage",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_26000_VerifyTheContentOfApplicationsPage {

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
                UserFactory.withSimplePermissionAndWithoutTrainings())
            .clickProfileMenu().waitDropDownDisplayed().clickApplicationsButton().isScreenLoaded(),
        "Registered user hasn't access to applications page!");
  }

  @Test(priority = 3)
  public void checkThatActiveSectionHasCorrectText() {
    String actualText = applicationsScreen.waitScreenLoading()
        .getActiveSectionTitleText();
    String expectedText = LocaleProperties.getValueOf(LocalePropertyConst.APPLICATIONS_ACTIVE_LINK);
    assertEquals(actualText, expectedText,
        format("Active section has title %s, but expected title %s!", actualText, expectedText));
  }

  @Test(priority = 3)
  public void checkThatInactiveSectionHasCorrectText() {
    String actualText = applicationsScreen.waitScreenLoading()
        .getInactiveSectionTitleText();
    String expectedText = LocaleProperties
        .getValueOf(LocalePropertyConst.APPLICATIONS_INACTIVE_LINK);
    assertEquals(actualText, expectedText,
        format("Active section has title %s, but expected  title %s!", actualText, expectedText));
  }

  @Test(priority = 3)
  public void checkThatCompletedSectionHasCorrectText() {
    String actualText = applicationsScreen.waitScreenLoading()
        .getCompletedSectionTitleText();
    String expectedText = LocaleProperties
        .getValueOf(LocalePropertyConst.APPLICATIONS_COMPLETED_LINK);
    assertEquals(actualText, expectedText,
        format("Active section has title %s, but expected  title %s!", actualText, expectedText));
  }
}
