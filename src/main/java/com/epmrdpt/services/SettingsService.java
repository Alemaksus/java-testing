package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;

import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;

public class SettingsService {

  private HeaderScreen headerScreen = new HeaderScreen();
  private SettingsScreen settingsScreen = new SettingsScreen();

  public SettingsScreen navigateToSettingsScreen() {
    headerScreen.waitProfileMenuDisplayed()
        .clickSettingsButton()
        .waitScreenLoading();
    Log.logInfoMessage("Navigated to Settings screen");
    return settingsScreen;
  }

  public SettingsScreen selectLanguageUnderGeneralSettings(String language) {
    settingsScreen.waitScreenLoading()
        .clickLanguageDropDownButton()
        .waitLanguageDropDownDisplayed()
        .clickDropDownListItem(language);
    Log.logInfoMessage("Language under General Settings is set to %s", language);
    return settingsScreen;
  }

  public String getLanguageCode(String language) {
    String languageCode;
    switch (language) {
      case "Russian":
      case "Русский":
      case "Російська":
        languageCode = LanguageEnum.RUSSIAN.getLanguageCode();
        break;
      case "Ukrainian":
      case "Украинский":
      case "Українська":
        languageCode = LanguageEnum.UKRAINE.getLanguageCode();
        break;
      case "English":
      case "Английский":
      case "Англійська":
        languageCode = LanguageEnum.ENGLISH.getLanguageCode();
        break;
      default:
        languageCode = null;
    }
    return languageCode;
  }

  public SettingsScreen untickEmailNotificationCheckbox() {
    if (settingsScreen.isEmailNotificationCheckboxChecked()) {
      settingsScreen.clickEmailNotificationCheckbox();
      Log.logInfoMessage("Unchecked 'Email Notification' checkbox");
    }
    return settingsScreen;
  }

  public SettingsScreen changeNotificationLanguage(String language) {
    untickEmailNotificationCheckbox().scrollToBottom();
    settingsScreen.clickNotificationLanguageDropDownButton()
        .clickDropDownListItem(language)
        .clickApplySettingsButton();
    headerScreen.waitForPageToReload();
    Log.logInfoMessage("Notification language is changed to %s", language);
    return settingsScreen;
  }

  public SettingsScreen tickSaveSearchOptionsCheckBox() {
    if (!settingsScreen.isSaveSearchOptionsChecked()) {
      settingsScreen.clickSaveSearchOptions();
    }
    return settingsScreen;
  }

  public SettingsScreen unTickSaveSearchOptionsCheckBox() {
    if (settingsScreen.isSaveSearchOptionsChecked()) {
      settingsScreen.clickSaveSearchOptions();
    }
    return settingsScreen;
  }

  public SettingsScreen changeNotificationLanguageToLocaleLanguage() {
    String locale = System.getProperty("locale");
    String language;
    switch (locale) {
      case "en":
        language = getValueOf(LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH);
        break;
      case "ru":
        language = getValueOf(LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN);
        break;
      case "ukr":
        language = getValueOf(LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN);
        break;
      default:
        language = null;
    }
    return changeNotificationLanguage(language);
  }

  public SettingsScreen changeTimezone(String timezone) {
    settingsScreen.waitScreenLoading()
        .clickTimezoneDropDownButton()
        .waitTimezoneDropDownMenuPresent()
        .clickDropDownListItem(timezone);
    Log.logInfoMessage("Timezone under General Settings is set to %s", timezone);
    return settingsScreen;
  }
}
