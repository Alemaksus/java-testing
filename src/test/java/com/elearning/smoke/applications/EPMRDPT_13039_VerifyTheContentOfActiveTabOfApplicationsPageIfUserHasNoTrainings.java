package com.epmrdpt.smoke.applications;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13039_VerifyTheContentOfActiveTabOfApplicationsPageIfUserHasNoTrainings",
    groups = {"full", "smoke"})
public class EPMRDPT_13039_VerifyTheContentOfActiveTabOfApplicationsPageIfUserHasNoTrainings {

  private ApplicationsScreen applicationsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    applicationsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithoutTrainings())
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickApplicationsButton();
  }

  @Test(priority = 1)
  public void checkThatApplicationsPageIsOpened() {
    assertTrue(
        applicationsScreen.isScreenLoaded(),
        "Application screen isn't loaded!"
    );
  }

  @Test(priority = 2)
  public void checkThatActiveSectionHasCorrectTitleIfUserHasNoTrainings() {
    assertEquals(
        applicationsScreen.waitScreenLoading().getActiveSectionStatusTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.APPLICATIONS_ACTIVE_TITLE),
        "Wrong description of title!"
    );
  }

  @Test(priority = 3)
  public void checkThatActiveSectionHasCorrectDescriptionIfUserHasNoTrainings() {
    assertEquals(
        applicationsScreen.getActiveSectionStatusFallbackText(),
        LocaleProperties.getValueOf(LocalePropertyConst.APPLICATIONS_ACTIVE_DESCRIPTION),
        "Active description isn't correct!");
  }
}
