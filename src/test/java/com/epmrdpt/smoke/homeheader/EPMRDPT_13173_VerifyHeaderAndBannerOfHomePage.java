package com.epmrdpt.smoke.homeheader;

import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_ABOUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_BLOG;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_EVENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_FAQ;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_LANGUAGE_ACRONYM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_TRAINING_LIST;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_SIGN_IN_BUTTON;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13173_VerifyHeaderAndBannerOfHomePage",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_13173_VerifyHeaderAndBannerOfHomePage {

  private SoftAssert softAssert;
  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test
  public void checkEpamLogoDisplayed() {
    assertTrue(headerScreen.isEpamLogoDisplayed(), "Epam logo isn't displayed!");
  }

  @Test
  public void checkUniversityProgramLogoDisplayed() {
    assertTrue(headerScreen.isUniversityProgramLogoDisplayed(),
        "University program logo isn't displayed!");
  }

  @Test
  public void checkAboutNavigationLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isAboutNavigationLinkDisplayed(),
        "About link isn't displayed!");
    softAssert.assertTrue(headerScreen.getAboutNavigationLinkText().equalsIgnoreCase(
        LocaleProperties.getValueOf(HEADER_CONTAINER_ABOUT)), "About link has incorrect name!");
    softAssert.assertAll();
  }

  @Test
  public void checkBannerDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isBannerDisplayed(), "Banner isn't displayed!");
    softAssert.assertEquals(headerScreen.getBannerText(),
        "Jumpstart & Develop\n" + "Your Career with Us",
        "There isn't text in the banner!");
    softAssert.assertAll();
  }

  @Test
  public void checkTrainingListNavigationLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isTrainingListNavigationLinkDisplayed(),
        "Training list link isn't displayed!");
    softAssert.assertTrue(headerScreen.getTrainingListNavigationLinkText().equalsIgnoreCase(
            LocaleProperties.getValueOf(HEADER_CONTAINER_TRAINING_LIST)),
        "Training list link has incorrect name!");
    softAssert.assertAll();
  }

  @Test
  public void checkSkillsNavigationLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isSkillsNavigationLinkDisplayed(),
        "Skills link isn't displayed!");
    softAssert.assertTrue(headerScreen.getSkillsNavigationLinkText().equalsIgnoreCase(
            LocaleProperties.getValueOf(HEADER_CONTAINER_SKILLS)),
        "Training list link has incorrect name!");
    softAssert.assertAll();
  }

  @Test
  public void checkBlogNavigationLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isBlogNavigationLinkDisplayed(),
        "Blog link isn't displayed!");
    softAssert.assertTrue(headerScreen.getBlogNavigationLinkText().equalsIgnoreCase(
        LocaleProperties.getValueOf(HEADER_CONTAINER_BLOG)), "Blog link has incorrect name!");
    softAssert.assertAll();
  }

  @Test
  public void checkFaqNavigationLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isFaqNavigationLinkDisplayed(),
        "Faq link isn't displayed!");
    softAssert.assertTrue(headerScreen.getFaqNavigationLinkText().equalsIgnoreCase(
        LocaleProperties.getValueOf(HEADER_CONTAINER_FAQ)), "Faq link has incorrect name!");
    softAssert.assertAll();
  }

  @Test
  public void checkLanguageAcronymDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isLanguageAcronymDisplayed(),
        "Language isn't displayed!");
    softAssert.assertTrue(headerScreen.getLanguageAcronymText().equalsIgnoreCase(
            LocaleProperties.getValueOf(HEADER_CONTAINER_LANGUAGE_ACRONYM)),
        "Language acronym has incorrect name!");
    softAssert.assertAll();
  }

  @Test
  public void checkGlobeIconDisplayed() {
    assertTrue(headerScreen.isLanguageControlDropdownDisplayed(), "Globe icon isn't displayed!");
  }

  @Test
  public void checkSignInButtonDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isSignInButtonDisplayed(),
        "Sign In Button isn't displayed!");
    softAssert.assertTrue(headerScreen.getSignInButtonText().equalsIgnoreCase(
        LocaleProperties.getValueOf(HEADER_SIGN_IN_BUTTON)), "Sign In button has incorrect name!");
    softAssert.assertAll();
  }

  @Test
  public void checkEventsButtonDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isEventsLinkDisplayed(),
        "Events Button isn't displayed!");
    softAssert.assertTrue(headerScreen.getEventsNavigationLinkText().equalsIgnoreCase(
        LocaleProperties.getValueOf(HEADER_CONTAINER_EVENTS)), "Events button has incorrect name!");
    softAssert.assertAll();
  }
}
