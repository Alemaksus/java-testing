package com.epmrdpt.smoke.home;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77737_VerifyThatRegisteredUserHasAccessToAllPagesPresentedInHeader",
    groups = {"full", "smoke"})
public class EPMRDPT_77737_VerifyThatRegisteredUserHasAccessToAllPagesPresentedInHeader {

  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void signIn() {
    headerScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermission());
  }

  @Test
  public void checkUserIsRedirectedToAboutPage() {
    assertTrue(headerScreen.clickAboutNavigationLink().isScreenLoaded(),
        "'About' Page fails to load");
  }

  @Test
  public void checkUserIsRedirectedToFaqScreen() {
    assertTrue(headerScreen.clickFAQNavigationLink().isBannerFAQDisplayed(),
        "'Faq' Page fails to load");
  }

  @Test
  public void checkUserIsRedirectedToTrainingListScreen() {
    assertTrue(headerScreen.clickTrainingListNavigationLink().isScreenLoaded(),
        "'Training list' Page fails to load");
  }

  @Test
  public void checkUserIsRedirectedToBlogScreen() {
    assertTrue(headerScreen.clickBlogNavigationLink().isScreenLoaded(),
        "'Blog' Page fails to load");
  }

  @Test
  public void checkUserIsRedirectedToSkillsScreen() {
    assertTrue(headerScreen.clickSkillsListNavigationLink().isScreenLoaded(),
        "'Skills' Page fails to load");
  }

  @Test
  public void checkUserIsRedirectedToEventsScreen() {
    assertTrue(headerScreen.clickEventsNavigationLink().isScreenLoaded(),
        "'Events' Page fails to load");
  }

  @Test
  public void checkUserIsRedirectedToProfileScreen() {
    assertTrue(headerScreen.clickProfileNavigationLink().isScreenLoaded(),
        "'Profile' Page fails to load");
  }
}
