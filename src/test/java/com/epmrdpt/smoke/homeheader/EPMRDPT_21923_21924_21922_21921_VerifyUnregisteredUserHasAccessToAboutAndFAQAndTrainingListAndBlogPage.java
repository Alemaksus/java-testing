package com.epmrdpt.smoke.homeheader;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_21923_21924_21922_21921_VerifyUnregisteredUserHasAccessToAboutAndFAQAndTrainingListAndBlogPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_21923_21924_21922_21921_VerifyUnregisteredUserHasAccessToAboutAndFAQAndTrainingListAndBlogPage {

  private HeaderScreen header;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    header = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkHomePageLoading() {
    assertTrue(header.isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkAccessBlogForUnregisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(header.isBlogNavigationLinkDisplayed(), "'Blog' link isn't displayed!");
    softAssert.assertTrue(
        header.clickBlogNavigationLink().isScreenLoaded(), "'Blog' screen isn't loaded!");
    softAssert.assertAll("Unregistered user doesn't have access to Blog!");
  }

  @Test(priority = 2)
  public void checkAccessFAQForUnregisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(header.isFaqNavigationLinkDisplayed(), "'Faq' link isn't displayed!");
    softAssert.assertTrue(
        header.clickFAQNavigationLink().isBannerFAQDisplayed(), "'Faq' screen isn't loaded!");
    softAssert.assertAll("Unregistered user doesn't have access to FAQ!");
  }

  @Test(priority = 2)
  public void checkAccessAboutForUnregisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(header.isAboutNavigationLinkDisplayed(), "'About' link isn't displayed!");
    softAssert.assertTrue(
        header.clickAboutNavigationLink().isAboutUsBannerDisplayed(),
        "'About' screen isn't loaded!");
    softAssert.assertAll("Unregistered user doesn't have access About!");
  }

  @Test(priority = 2)
  public void checkAccessTrainingListUnregisteredUser() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        header.isTrainingListNavigationLinkDisplayed(), "'Training List' link isn't displayed!");
    softAssert.assertTrue(
        header.clickTrainingListNavigationLink().isScreenLoaded(),
        "'Training list' screen isn't loaded!");
    softAssert.assertAll("Unregistered user doesn't have access TrainingList!");
  }
}
