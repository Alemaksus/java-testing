package com.epmrdpt.smoke.homeheader;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13232_VerifyThatRegisteredUserHasAccessToAllPagesPresentedInHeader",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13232_VerifyThatRegisteredUserHasAccessToAllPagesPresentedInHeader {

  private HeaderScreen headerScreen;
  private User user;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    user = UserFactory.withSimplePermission();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkUserLogIn() {
    assertTrue(new LoginService().loginAndSetLanguage(user).clickProfileMenu()
        .isProfilePhotoImageDisplayed(), "User doesn't logIn.");
  }

  @Test(priority = 3)
  public void checkAccessAboutPageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isAboutNavigationLinkDisplayed(),
        "'About' button isn't displayed!");
    softAssert.assertTrue(headerScreen.clickAboutNavigationLink().isScreenLoaded(),
        "'About' screen isn't loaded!");
    softAssert.assertAll("Registered user doesn't have access to 'About' page!");
  }

  @Test(priority = 4)
  public void checkAccessFAQPageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(headerScreen.isFaqNavigationLinkDisplayed(), "'FAQ' button isn't displayed!");
    softAssert.assertTrue(headerScreen.clickFAQNavigationLink().isScreenLoaded(),
        "'FAQ' screen isn't loaded!");
    softAssert.assertAll("Registered user doesn't have access to 'FAQ' page!");
  }

  @Test(priority = 5)
  public void checkAccessSkillsPageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isSkillsNavigationLinkDisplayed(),
        "'Skills' button isn't displayed!");
    softAssert.assertTrue(headerScreen.clickSkillsListNavigationLink().isScreenLoaded(),
        "'Skills' screen isn't loaded!");
    softAssert.assertAll("Registered user doesn't have access to 'Skills' page!");
  }

  @Test(priority = 6)
  public void checkAccessTrainingListPageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isTrainingListNavigationLinkDisplayed(),
        "'Training list' button isn't displayed!");
    softAssert.assertTrue(headerScreen.clickTrainingListNavigationLink().isScreenLoaded(),
        "'Training list' screen isn't loaded!");
    softAssert.assertAll("Registered user doesn't have access to 'Training list' page!");
  }

  @Test(priority = 7)
  public void checkAccessBlogForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isBlogNavigationLinkDisplayed(),
        "'Blog' button isn't displayed!");
    softAssert.assertTrue(headerScreen.clickBlogNavigationLink().isScreenLoaded(),
        "'Blog' screen isn't loaded!");
    softAssert.assertAll("Registered user doesn't have access to 'Blog' page!");
  }

  @Test(priority = 8)
  public void checkAccessToEventsPageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isEventsLinkDisplayed(), "'Events' link isn't displayed!");
    softAssert.assertTrue(headerScreen.clickEventsNavigationLink().isScreenLoaded(),
        "'Events' screen isn't loaded!");
    softAssert.assertAll("Registered user doesn't have access to 'Events' page!");
  }

  @Test(priority = 9)
  public void checkAccessToProfilePageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.clickProfileMenu().isProfileButtonDisplayed(),
        "'Profile' button isn't displayed!");
    softAssert.assertTrue(headerScreen.clickProfileButton().isScreenLoaded(),
        "'Profile' screen isn't loaded!");
    softAssert.assertAll("Registered user doesn't have access to 'Profile' page!");
  }
}
