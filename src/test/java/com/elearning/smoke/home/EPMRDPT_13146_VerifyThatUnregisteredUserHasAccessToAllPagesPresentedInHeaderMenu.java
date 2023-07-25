package com.epmrdpt.smoke.home;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13146_VerifyThatUnregisteredUserHasAccessToAllPagesPresentedInHeaderMenu",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13146_VerifyThatUnregisteredUserHasAccessToAllPagesPresentedInHeaderMenu {

  private HeaderScreen headerScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkUnregisteredUserHasAccessToHomePage() {
    assertTrue(headerScreen.checkUsersRedirectionToHomePage(),
        "Unregistered user hasn't access to 'Home' page!");
  }

  @Test(priority = 2)
  public void checkUnregisteredUserHasAccessToAboutPage() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isAboutNavigationLinkDisplayed(),
        "'About' link isn't displayed!");
    softAssert.assertTrue(headerScreen.clickAboutNavigationLink().isAboutUsBannerDisplayed(),
        "'About' screen isn't loaded!");
    softAssert.assertAll("Unregistered user hasn't access to 'About' page!");
  }

  @Test(priority = 2)
  public void checkUnregisteredUserHasAccessToFAQPage() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isFaqNavigationLinkDisplayed(),
        "'Faq' link isn't displayed!");
    softAssert.assertTrue(headerScreen.clickFAQNavigationLink().isBannerFAQDisplayed(),
        "'Faq' screen isn't loaded!");
    softAssert.assertAll("Unregistered user hasn't access to 'FAQ' page!");
  }

  @Test(priority = 2)
  public void checkUnregisteredUserHasAccessToTrainingListPage() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isTrainingListNavigationLinkDisplayed(),
        "'Training List' link isn't displayed!");
    softAssert.assertTrue(headerScreen.clickTrainingListNavigationLink().isScreenLoaded(),
        "'Training list' screen isn't loaded!");
    softAssert.assertAll("Unregistered user hasn't access to 'TrainingList' page!");
  }

  @Test(priority = 2)
  public void checkUnregisteredUserHasAccessToBlogPage() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isBlogNavigationLinkDisplayed(),
        "'Blog' link isn't displayed!");
    softAssert.assertTrue(headerScreen.clickBlogNavigationLink().isScreenLoaded(),
        "'Blog' screen isn't loaded!");
    softAssert.assertAll("Unregistered user hasn't access to 'Blog' page!");
  }

  @Test(priority = 2)
  public void checkAccessSkillsPageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isSkillsNavigationLinkDisplayed(),
        "'Skills' button isn't displayed!");
    softAssert.assertTrue(headerScreen.clickSkillsListNavigationLink().isScreenLoaded(),
        "'Skills' screen isn't loaded!");
    softAssert.assertAll("Unregistered user doesn't have access to 'Skills' page!");
  }

  @Test(priority = 3)
  public void checkAccessToEventsPageForRegisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isEventsLinkDisplayed(), "'Events' link isn't displayed!");
    softAssert.assertTrue(headerScreen.clickEventsNavigationLink().isScreenLoaded(),
        "'Events' screen isn't loaded!");
    softAssert.assertAll("Unregistered user doesn't have access to 'Events' page!");
  }
}
