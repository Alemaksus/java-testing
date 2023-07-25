package com.epmrdpt.regression.news_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14331_VerifyThatAllNewsItemsAreShownAccordingToLanguageThatUserChooses",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14331_VerifyThatAllNewsItemsAreShownAccordingToLanguageThatUserChooses {

  private static final String ARTICLE_TITLE_IN_ENGLISH = "AutoTest Language Check";
  private static final String ARTICLE_TITLE_IN_RUSSIAN = "Авто-тест язык Проверьте";
  private static final String ARTICLE_TITLE_IN_UKRAINIAN = "Автотест Мова Перевірка";

  private User user;
  private NewsManagementScreen newsManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab for language switch", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14331_VerifyThatAllNewsItemsAreShownAccordingToLanguageThatUserChooses(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickNewsManagementLink()
        .waitScreenLoading();
    newsManagementScreen = new NewsManagementScreen();
  }

  @DataProvider(name = "Provider of titles of language")
  public static Object[][] provideLanguages() {
    return new Object[][]{
        {ARTICLE_TITLE_IN_ENGLISH, LanguageEnum.ENGLISH},
        {ARTICLE_TITLE_IN_RUSSIAN, LanguageEnum.RUSSIAN},
        {ARTICLE_TITLE_IN_UKRAINIAN, LanguageEnum.UKRAINE}
    };
  }

  @Test(priority = 1)
  public void checkNewsManagementScreenIsOpened() {
    assertTrue(newsManagementScreen.isScreenLoaded(), "News management page isn't opened!");
  }

  @Test(priority = 2, dataProvider = "Provider of titles of language")
  public void checkTitleOfSubjectColumnBasedOnChosenLanguage(String title, LanguageEnum language) {
    new LanguageSwitchingService().setLanguageAccordingToChosenLanguage(language);
    newsManagementScreen.clickRefreshButton();
    newsManagementScreen.waitSearchResultsLoading()
        .typeSearchBySubject(title)
        .clickFindButton()
        .waitSearchResultsLoading();
    assertTrue(newsManagementScreen.isNewsItemWithSubjectPresent(title),
        String.format("Title in subject column isn't displayed correctly for locale %s",
            language.getLocaleName()));
  }
}
