package com.epmrdpt.smoke.homelocalization;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.BlogBlockOnStartPage;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13142_VerifyItIsPossibleToChangeHomePageLocale",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_13142_VerifyItIsPossibleToChangeHomePageLocale {

  private HeaderScreen headerScreen;
  private LanguageSwitchingService languageSwitchingService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    languageSwitchingService = new LanguageSwitchingService();
  }

  @DataProvider(name = "Provider of checking language")
  private Object[][] providerOfCheckingLanguage() {
    return new Object[][]{
        {LanguageEnum.ENGLISH, LanguageEnum.RUSSIAN, "ВОЙТИ"},
        {LanguageEnum.RUSSIAN, LanguageEnum.ENGLISH, "SIGN IN"},
        {LanguageEnum.ENGLISH, LanguageEnum.UKRAINE, "УВІЙТИ"}
    };
  }

  @Test(dataProvider = "Provider of checking language")
  public void checkPossibilityToChangeHomePageLocale(LanguageEnum initialLanguage,
      LanguageEnum checkingLanguage, String referenceSignInButtonText) {
    languageSwitchingService.setLanguageAccordingToChosenLanguage(initialLanguage);
    new BlogBlockOnStartPage().waitArticleImageVisibility();
    languageSwitchingService.setLanguageAccordingToChosenLanguage(checkingLanguage);
    assertEquals(headerScreen.waitLanguageControlDropdownDisplayed().getSignInButtonText(),
        referenceSignInButtonText,
        "It isn't possible to change home page locale!");
  }
}
