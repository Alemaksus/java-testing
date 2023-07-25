package com.epmrdpt.regression.home;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_LANGUAGE_ACRONYM;
import static com.epmrdpt.services.LanguageSwitchingService.getLocaleLanguage;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77705_VerifyTheOptionsOfMainMenu",
    groups = {"full", "general", "regression"})
public class EPMRDPT_77705_VerifyTheOptionsOfMainMenu {

  private HeaderScreen headerScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LanguageSwitchingService().setLanguageAccordingToLocaleSettings();
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void verifyHeaderElements() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isEpamLogoDisplayed(),
        "Epam logo isn't displayed!");
    softAssert.assertTrue(headerScreen.isLanguageAcronymDisplayed(),
        "Language acronym isn't displayed!");
    softAssert.assertTrue(headerScreen.isSignInButtonDisplayed(),
        "Sign in isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatLanguageAcronymHasCorrectText() {
    assertEquals(headerScreen.getLanguageAcronymText(),
        getValueOf(HEADER_CONTAINER_LANGUAGE_ACRONYM),
        format("Language acronym has incorrect text when the locale is %s!",
            getLocaleLanguage().getLocaleName()));
  }
}
