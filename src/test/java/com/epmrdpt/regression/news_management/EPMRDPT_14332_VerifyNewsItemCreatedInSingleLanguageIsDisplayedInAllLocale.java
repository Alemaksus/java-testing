package com.epmrdpt.regression.news_management;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.EditNewsItemScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14332_VerifyNewsItemCreatedInSingleLanguageIsDisplayedInAllLocale",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14332_VerifyNewsItemCreatedInSingleLanguageIsDisplayedInAllLocale {

  private NewsManagementScreen newsManagementScreen;
  private EditNewsItemScreen editNewsItemScreen;
  private User user;

  private final static String SUBJECT_NAME = "AutoTest_News created in single language";
  private final static int EXPECTED_NUMBER_OF_LANGUAGES_IN_WHICH_NEWS_CREATED = 1;
  private final static int NEWS_ITEM_SUBJECT_INDEX = 0;
  private int numberOfLanguagesInWhichNewsCreated = 0;

  @Factory(dataProvider = "Provider of users with News Management tab")
  public EPMRDPT_14332_VerifyNewsItemCreatedInSingleLanguageIsDisplayedInAllLocale(User user) {
    this.user = user;
  }

  @DataProvider(name = "Provider of users with News Management tab")
  public static Object[][] provideUsers() {
    return new Object[][]{
        {UserFactory.asNewsCreator()},
        {UserFactory.asSuperAdmin()}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    newsManagementScreen = new NewsManagementScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
    editNewsItemScreen = new EditNewsItemScreen();
  }

  @Test(priority = 1)
  public void checkNewsManagementScreenLoaded() {
    assertTrue(new HeaderScreen().clickNewsManagementLink().isScreenLoaded(),
        "News management page isn't opened!");
  }

  @DataProvider(name = "Data provider for language")
  public static Object[][] provideLanguage() {
    return new Object[][]{{LanguageEnum.ENGLISH, "English"},
        {LanguageEnum.RUSSIAN, "Russia"},
        {LanguageEnum.UKRAINE, "Ukraine"}
    };
  }

  @Test(priority = 2, dataProvider = "Data provider for language")
  public void checkIfNewsItemIsDisplayedInAllThreeLanguages(LanguageEnum language,
      String languageName) {
    LanguageSwitchingService languageSwitchingService = new LanguageSwitchingService();
    languageSwitchingService.setLanguageAccordingToChosenLanguage(language);
    newsManagementScreen.clickRefreshButton();
    newsManagementScreen
        .waitSearchResultsLoading()
        .enterSearchBySubjectInput(SUBJECT_NAME)
        .clickFindButton()
        .waitSearchResultsLoading();
    assertTrue(
        newsManagementScreen.getSearchResultsItemBySubjectColumn().equals(SUBJECT_NAME),
        format("Subject name did not appear when locale is %s!", languageName));
  }

  @Test(priority = 3)
  public void checkEditScreenLoading() {
    newsManagementScreen.clickNewsItemSubjectByIndex(NEWS_ITEM_SUBJECT_INDEX);
    assertTrue(editNewsItemScreen.isScreenLoaded(), "Edit news item page isn't loaded!");
  }

  @DataProvider(name = "Language tab title input provider")
  private Object[][] LanguageTabTitleInputProvider() {
    return new Object[][]{
        {editNewsItemScreen.getTitleOfEnglishTabInputTextValue()},
        {editNewsItemScreen.getTitleOfRussianTabInputTextValue()},
        {editNewsItemScreen.getTitleOfUkrainianTabInputTextValue()}
    };
  }

  @Test(priority = 4, dataProvider = "Language tab title input provider")
  public void checkIfNewsItemIsDisplayedInLanguageCreated(String inputInLanguageTab) {
    if (inputInLanguageTab.length() > 0) {
      assertEquals(inputInLanguageTab, SUBJECT_NAME,
          "News item displayed in search results is not equal to language in which news item is created!");
      numberOfLanguagesInWhichNewsCreated++;
    }
  }

  @Test(priority = 5)
  public void checkIfNewsItemIsCreatedInSingleLanguage() {
    assertEquals(numberOfLanguagesInWhichNewsCreated,
        EXPECTED_NUMBER_OF_LANGUAGES_IN_WHICH_NEWS_CREATED,
        "News item is created in more than one language!");
  }
}
