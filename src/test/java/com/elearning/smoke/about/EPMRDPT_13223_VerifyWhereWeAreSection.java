package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13223_VerifyWhereWeAreSection",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13223_VerifyWhereWeAreSection {

  private AboutScreen aboutScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About us page didn't load!");
  }

  @Test(priority = 2)
  public void checkOurTrainingCentersTitleHasCorrectText() {
    assertEquals(aboutScreen.getTrainingCentersSectionTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_TITLE),
        "Map title doesn't have correct text!");
  }

  @Test(priority = 2)
  public void checkTrainingCentersCountryLinksHaveCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_ARMENIA_TAB)),
        "Armenia link doesn't have correct text in 'Our Training Centers' section!");
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_BELARUS_TAB)),
        "Belarus link doesn't have correct text in 'Our Training Centers' section!");
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_GEORGIA_TAB)),
        "The link to Georgia does not have the correct text in the 'Our Training Centers' section!");
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_KAZAKHSTAN_TAB)),
        "Kazakhstan link doesn't have correct text in 'Our Training Centers' section!");
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_LITHUANIA_TAB)),
        "Lithuania link doesn't have correct text in 'Our Training Centers' section!");
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_UKRAINE_TAB)),
        "Ukraine link doesn't have correct text in 'Our Training Centers' section!");
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_UZBEKISTAN_TAB)),
        "Uzbekistan link doesn't have correct text in 'Our Training Centers' section!");
    softAssert.assertTrue(aboutScreen.isTrainingCentersCountryLinkHasCorrectText(
            LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_POLAND_TAB)),
        "Poland link doesn't have correct text in 'Our Training Centers' section!");
    softAssert.assertAll();
  }
}
