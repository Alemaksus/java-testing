package com.epmrdpt.smoke.home;

import static java.lang.String.format;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77707_VerifyLanguageAcronymIsDisplayedAccordingToTheSelectedLanguageInMainMenu",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_77707_VerifyLanguageAcronymIsDisplayedAccordingToTheSelectedLanguageInMainMenu {

  private HeaderScreen headerScreen;
  private TrainingBlockScreen trainingBlockScreen;
  private LanguageSwitchingService languageSwitchingService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    languageSwitchingService = new LanguageSwitchingService();
    trainingBlockScreen = new TrainingBlockScreen();
    headerScreen = new LoginService().
        loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermission());
  }

  @DataProvider(name = "Data provider for language")
  public static Object[][] provideLanguage() {
    return new Object[][]{
        {LanguageEnum.ENGLISH, "English", "EN"},
        {LanguageEnum.RUSSIAN, "Russia", "RU"},
        {LanguageEnum.UKRAINE, "Ukraine", "UA"}
    };
  }

  @Test(dataProvider = "Data provider for language")
  public void checkSkillsCardDescription(LanguageEnum language, String languageName,
      String languageAcronym) {
    SoftAssert softAssert = new SoftAssert();
    languageSwitchingService.setLanguageAccordingToChosenLanguage(language);
    trainingBlockScreen.waitScreenLoaded()
        .clickRefreshButton();
    softAssert.assertTrue(headerScreen.isLanguageAcronymDisplayed(),
        "Language acronym isn't displayed");
    softAssert.assertEquals(headerScreen.getLanguageAcronymText(), languageAcronym,
        format("Language acronym has incorrect text when the locale is %s!", languageName));
    softAssert.assertAll();
  }
}
