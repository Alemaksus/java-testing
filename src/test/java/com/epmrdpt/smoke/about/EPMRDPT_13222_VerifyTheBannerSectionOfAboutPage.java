package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13222_VerifyTheBannerSectionOfAboutPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13222_VerifyTheBannerSectionOfAboutPage {

  private AboutScreen about;
  private static final String IMAGE_PATH = "/Content/themes/Redesign/banners/about.jpg";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    about = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(about.isScreenLoaded(), "Page 'About us' didn't load!");
  }

  @Test(priority = 2)
  public void checkBannerBackgroundImageDisplayed() {
    assertTrue(about.getAboutUsBannerBackground().contains(IMAGE_PATH),
        "Banner's background doesn't contain a link to the image!");
  }

  @Test(priority = 2)
  public void checkTitleAboutUsBannerDisplayed() {
    assertEquals(about.getAboutUsBannerTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_ABOUT_US_BANNER_TITLE),
        "There isn't title text in the banner!");
  }

  @Test(priority = 2)
  public void checkQuestionAboutUsBannerDisplayed() {
    assertTrue(about.getAboutUsBannerQuestionText().startsWith(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_ABOUT_US_BANNER_QUESTION)),
        "There isn't question text in the banner!");
  }

  @Test(priority = 2)
  public void checkDescriptionAboutUsBannerDisplayed() {
    assertTrue(about.getAboutUsBannerDescriptionText().endsWith(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_ABOUT_US_BANNER_DESCRIPTION)),
        "There isn't description text in the banner!");
  }
}
