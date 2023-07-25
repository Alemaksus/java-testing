package com.epmrdpt.services;

import static com.epmrdpt.framework.loging.Log.logInfoMessage;
import static com.epmrdpt.services.LanguageSwitchingService.LanguageEnum.*;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.SelectLanguageScreen;

public class LanguageSwitchingService {

  SelectLanguageScreen selectLanguageScreen = new SelectLanguageScreen();

  public LanguageSwitchingService setLanguageAccordingToLocaleSettings() {
    if (!selectLanguageScreen.isLanguageSetUpAccordingToLocaleSetting()) {
      new HeaderScreen().waitLanguageControlDropdownDisplayed().clickLanguageControlDropdown();
      if (selectLanguageScreen.isScreenLoaded()) {
        clickLanguageButton(getLocaleLanguage());
        selectLanguageScreen.isLanguageLocaleLocatorPresence(getLocaleLanguage());
      }
    }
    return this;
  }

  public LanguageSwitchingService setLanguageAccordingToLocaleSettingsNoWait() {
    if (!selectLanguageScreen.isLanguageSetUpAccordingToLocaleSetting()) {
      new HeaderScreen().waitLanguageControlDropdownDisplayed().clickLanguageControlDropdown();
      if (selectLanguageScreen.isScreenLoaded()) {
        logInfoMessage("Select language page load!");
        clickLanguageButton(getLocaleLanguage());
        selectLanguageScreen.switchLanguageFromPage(getLocaleLanguage());
      } else {
        logInfoMessage("Select language page did not load!");
      }
    }
    return this;
  }

  public LanguageSwitchingService setLanguageAccordingToChosenLanguage(LanguageEnum languageEnum) {
    new ReactHeaderScreen().waitLanguageControlDropdownDisplayed().clickLanguageControlDropdown();;
    if (selectLanguageScreen.isScreenLoaded()) {
      logInfoMessage("Select language page load!");
      clickLanguageButton(languageEnum);
      selectLanguageScreen.isLanguageLocaleLocatorPresence(languageEnum);
      selectLanguageScreen.waitForUrlContainsLanguageParameter(languageEnum);
    } else {
      logInfoMessage("Select language page did not load!");
    }
    return this;
  }

  public static LanguageEnum getLocaleLanguage() {
    switch (System.getProperty("locale")) {
      case "ru":
        return RUSSIAN;
      case "ukr":
        return UKRAINE;
      case "en":
        return ENGLISH;
      default:
        throw new IllegalArgumentException(
            "Unknown Locale name [" + System.getProperty("locale") + "].");
    }
  }

  private void clickLanguageButton(LanguageEnum language) {
    selectLanguageScreen.clickLanguageLink(language);
    logInfoMessage(
        String.format("Language has changed to %s", (language.getLanguageCode()).toUpperCase()));
  }

  public enum LanguageEnum {

    RUSSIAN("ru", "ru", "RU"),
    UKRAINE("ukr", "uk", "UA"),
    ENGLISH("en", "en", "US");

    private String localeName;
    private String languageCode;
    private String countryCode;

    LanguageEnum(String localeName, String languageCode, String countryCode) {
      this.localeName = localeName;
      this.languageCode = languageCode;
      this.countryCode = countryCode;
    }

    public String getLocaleName() {
      return localeName;
    }

    public String getLanguageCode() {
      return languageCode;
    }

    public String getCountryCode() {
      return countryCode;
    }

    public static LanguageEnum getLanguageEnumByLocaleName(String localeName) {
      switch (localeName) {
        case "en":
          return ENGLISH;
        case "ru":
          return RUSSIAN;
        case "ukr":
          return UKRAINE;
        default:
          throw new IllegalArgumentException("Unknown Locale name [" + localeName + "].");
      }
    }
  }
}
