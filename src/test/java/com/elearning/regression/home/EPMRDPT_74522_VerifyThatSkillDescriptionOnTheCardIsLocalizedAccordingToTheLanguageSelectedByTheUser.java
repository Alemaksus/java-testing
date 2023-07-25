package com.epmrdpt.regression.home;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SkillDescriptionScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_74522_VerifyThatSkillDescriptionOnTheCardIsLocalizedAccordingToTheLanguageSelectedByTheUser",
    groups = {"full", "regression"})
public class EPMRDPT_74522_VerifyThatSkillDescriptionOnTheCardIsLocalizedAccordingToTheLanguageSelectedByTheUser {

  private static final String englishDescriptionText =
      ".NET is a powerful Microsoft platform for developing applications of any level of complexity. "
          + "Its main advantages are that it is flexible, easy to learn, and widely used.";
  private static final String russianDescriptionText =
      ".NET - мощная платформа от компании Microsoft для разработки приложений любого уровня сложности. "
          + "Гибкость, простота освоения, широкий спектр применения - основные преимущества .NET.";
  private static final String ukrainianDescriptionText =
      ".NET - потужна платформа від компанії Microsoft для розробки застосунків будь-якого рівня складності. "
          + "Гнучкість, простота освоєння, широкий спектр застосування - основні переваги .NET.";
  private final String cardName = ".NET";
  private HeaderScreen headerScreen;
  private SkillDescriptionScreen skillDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkAccessSkillsPage() {
    assertTrue(headerScreen.isSkillsNavigationLinkDisplayed(),
        "'Skills' button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatSkillsPageLoaded() {
    skillDescriptionScreen = headerScreen.clickSkillsListNavigationLink();
    assertTrue(skillDescriptionScreen.isScreenLoaded(),
        "'Skills' screen isn't loaded!");
  }

  @Test(priority = 3)
  public void checkThatSkillsPageTitleDisplayed() {
    assertTrue(
        skillDescriptionScreen.isOurSkillsTittleDisplayed(),
        "OUR SKILLS title isn't displayed!");
  }

  @Test(priority = 4)
  public void checkThatSkillsPageTitleHasCorrectText() {
    assertEquals(skillDescriptionScreen.getOurSkillsTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_OUR_SKILLS_TITLE),
        "OUR SKILLS title has incorrect text!");
  }

  @DataProvider(name = "Data provider for language")
  public static Object[][] provideLanguage() {
    return new Object[][]{
        {LanguageEnum.ENGLISH, "English", englishDescriptionText},
        {LanguageEnum.RUSSIAN, "Russia", russianDescriptionText},
        {LanguageEnum.UKRAINE, "Ukraine", ukrainianDescriptionText}
    };
  }

  @Test(priority = 5, dataProvider = "Data provider for language")
  public void checkSkillsCardDescription(LanguageEnum language, String languageName,
      String descriptionText) {
    new LanguageSwitchingService().setLanguageAccordingToChosenLanguage(language);
    skillDescriptionScreen.waitScreenLoaded()
        .clickRefreshButton();
    assertEquals(skillDescriptionScreen.getSkillCardDescriptionByName(cardName), descriptionText,
        format("Card description has incorrect text when the locale is %s!", languageName));
  }
}
