package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_POPUP_WARNING_TRAINING_IN_ENGLISH_AND_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_POPUP_WARNING_TRAINING_IN_ENGLISH_AND_UKRAINIAN;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34231_34232_34256_34257_VerifyTrainingCenterContentDisplayedInEnglishByDefaultOnRuAndUkrLocaleOnTrainingCenterPage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34231_34232_34256_34257_VerifyTrainingCenterContentDisplayedInEnglishByDefaultOnRuAndUkrLocaleOnTrainingCenterPage {

  private User user;
  private LanguageSwitchingService languageSwitchingService;
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private TrainingCenterManagementService trainingCenterManagementService;
  private final String COUNTRY = LocaleProperties.getValueOf(COUNTRY_NAME_BELARUS);
  private final String CITY = LocaleProperties.getValueOf(CITY_NAME_BELARUS_GOMEL);
  private final String ENGLISH_NAME = "Training Center Name ENGLISH";
  private final String ENGLISH_DESCRIPTION = "Training Center Description ENGLISH";
  private LanguageEnum SYSTEM_LOCALE;


  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34231_34232_34256_34257_VerifyTrainingCenterContentDisplayedInEnglishByDefaultOnRuAndUkrLocaleOnTrainingCenterPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenterManagementScreen() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
    languageSwitchingService = new LanguageSwitchingService();
    SYSTEM_LOCALE = LanguageSwitchingService.getLocaleLanguage();
  }

  @Test(dataProvider = "Training Centers name and description in Russian and Ukrainian")
  public void checkTrainingCenterScreenIsLoaded(LanguageEnum localeToCheck, String russianName,
      String russianDescription, String ukrainianName, String ukrainianDescription,
      String popupWarning) {
    languageSwitchingService.setLanguageAccordingToChosenLanguage(SYSTEM_LOCALE);
    trainingCenterManagementScreen = new HeaderScreen().clickCenterManagementLink();
    trainingCenterManagementScreen.waitTrainingCenterCitiesVisibility();
    trainingCenterManagementService = new TrainingCenterManagementService();
    CreateOrEditTrainingCenterScreen editTrainingCenterScreen = trainingCenterManagementService
        .openEditCenterScreen(COUNTRY, CITY);
    trainingCenterManagementService
        .fillTrainingCenterNameAndDescriptionOnTab(LanguageEnum.ENGLISH, ENGLISH_NAME,
            ENGLISH_DESCRIPTION);
    trainingCenterManagementService
        .fillTrainingCenterNameAndDescriptionOnTab(LanguageEnum.RUSSIAN, russianName,
            russianDescription);
    trainingCenterManagementService
        .fillTrainingCenterNameAndDescriptionOnTab(LanguageEnum.UKRAINE, ukrainianName,
            ukrainianDescription);
    SoftAssert softAssert = new SoftAssert();
    editTrainingCenterScreen
        .clickCreateOrSaveTrainingCenterTopButton();
    softAssert
        .assertEquals(editTrainingCenterScreen.getConfirmationPopupMessageText(), popupWarning,
            "The warning popup text isn't as expected!");
    editTrainingCenterScreen
        .waitForClickableAndClickCreateConfirmationButton();
    trainingCenterManagementScreen.isScreenLoaded();
    CenterScreen centerScreen = trainingCenterManagementService
        .openPreviewCenterScreen(COUNTRY, CITY);
    softAssert.assertTrue(centerScreen.isScreenLoaded(),
        "Training center screen isn't loaded!");
    languageSwitchingService.setLanguageAccordingToChosenLanguage(localeToCheck);
    softAssert.assertEquals(centerScreen.getTextTitle(), ENGLISH_NAME,
        "Training center name isn't displayed in English by default!");
    softAssert.assertEquals(centerScreen.getTextDescription(), ENGLISH_DESCRIPTION,
        "Training center description isn't displayed in English by default!");
    softAssert.assertAll();
  }

  @DataProvider(name = "Training Centers name and description in Russian and Ukrainian")
  public static Object[][] provideTrainingCentersNameAndDescription() {
    return new Object[][]{
        {LanguageEnum.RUSSIAN, "", "", "Тренинг Центр Гомель УКР",
            "Тренинг Центр Гомель Описание УКР",
            LocaleProperties.getValueOf(
                EDIT_CENTER_POPUP_WARNING_TRAINING_IN_ENGLISH_AND_UKRAINIAN)},
        {LanguageEnum.UKRAINE, "Тренинг Центр Гомель РУС", "Тренинг Центр Гомель Описание РУС", "",
            "",
            LocaleProperties.getValueOf(EDIT_CENTER_POPUP_WARNING_TRAINING_IN_ENGLISH_AND_RUSSIAN)}
    };
  }
}
