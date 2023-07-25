package com.epmrdpt.regression.settings;

import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.SettingsService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13161_VerifyThatItIsPossibleToChangePortalLanguageViaSettings",
    groups = {"full", "regression"})
public class EPMRDPT_13161_VerifyThatItIsPossibleToChangePortalLanguageViaSettings {

  private SoftAssert softAssert;
  private SettingsScreen settingsScreen;
  private SettingsService settingsService;
  private LanguageSwitchingService languageSwitchingService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    settingsScreen = new SettingsScreen();
    settingsService = new SettingsService();
    languageSwitchingService = new LanguageSwitchingService();
  }

  @DataProvider(name = "Provider of languages and settings-title-text")
  public static Object[][] provideLanguagesAndSettingsTitleText() {
    return new Object[][]{
        {LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN), "НАСТРОЙКИ"},
        {LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH), "SETTINGS"},
        {LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN), "НАЛАШТУВАННЯ"}
    };
  }

  @Test(priority = 1)
  public void checkSettingsScreenLoading() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(
        UserFactory.forNotificationLanguageSwitch());
    assertTrue(settingsService.navigateToSettingsScreen().isScreenLoaded(),
        "Settings page hasn't loaded!");
  }

  @Test(priority = 2, dataProvider = "Provider of languages and settings-title-text")
  public void checkChangeLanguageSettings(String language, String settingsTitleText) {
    languageSwitchingService.setLanguageAccordingToLocaleSettings();
    softAssert = new SoftAssert();
    settingsService.selectLanguageUnderGeneralSettings(language);
    softAssert.assertTrue(settingsScreen.isSelectedLanguagePresent(language),
        format("Set language under General Settings to %s hasn't worked!", language));
    settingsScreen.clickApplySettingsButton().waitUntilLanguageSwitchedFromPage(language);
    softAssert
        .assertEquals(settingsScreen.getSettingsTitleSpanText(), settingsTitleText,
            format("Change language to %s hasn't worked!", language));
    softAssert.assertAll();
  }
}
