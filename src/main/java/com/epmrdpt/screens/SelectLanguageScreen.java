package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import org.openqa.selenium.StaleElementReferenceException;

public class SelectLanguageScreen extends AbstractScreen {

  private final static String ANGULAR_LOCALE_LOCATOR_PATTERN ="html[@lang='%s']";
  private final static String LANGUAGE_LINK_LOCATOR_PATTERN =
      "//*[contains(@class,'menu-control')and contains(@class,'language-control')"
          + "and contains(@class,'dropdown')]//a[contains(@ng-click,'%s-%s')]";
  private String currentLanguage = " ";
  private Element languageChoiceModule = Element.byCss("div.language-control__menu");

  public SelectLanguageScreen clickLanguageLink(LanguageEnum languageCodeFromPage) {
    Element.byXpath(String.format(LANGUAGE_LINK_LOCATOR_PATTERN,
            languageCodeFromPage.getLanguageCode(), languageCodeFromPage.getCountryCode()))
        .clickJs();
    return this;
  }

  @Override
  public boolean isScreenLoaded() {
    return languageChoiceModule.isDisplayed();
  }

  public boolean isLanguageLocaleLocatorPresence(LanguageEnum languageCodeFromPage) {
    return Element.byXpath(
            String.format(ANGULAR_LOCALE_LOCATOR_PATTERN, languageCodeFromPage.getLanguageCode()))
        .isPresent(MIDDLE_TIME_OUT_IN_SECONDS);
  }

  public void switchLanguageFromPage(LanguageEnum languageCodeFromPage) {
    if (!getCurrentLanguage().equals(languageCodeFromPage.getLanguageCode())) {
      Element.byXpath(
              String.format(ANGULAR_LOCALE_LOCATOR_PATTERN, currentLanguage))
          .waitForDisappear();
    }
    Element.byXpath(
            String.format(ANGULAR_LOCALE_LOCATOR_PATTERN, languageCodeFromPage.getLanguageCode()))
        .waitForPresence();
  }

  public boolean isLanguageSetUpAccordingToLocaleSetting() {
    return Element.byXpath(String.format(ANGULAR_LOCALE_LOCATOR_PATTERN,
            LanguageSwitchingService.getLocaleLanguage().getLanguageCode()))
        .isPresent();
  }

  public String getCurrentLanguage() {
    int attempts = 0;
    while (attempts < 3) {
      try {
        if (Element.byXpath("//script[contains(@src,'locale_')]").isPresent()) {
          currentLanguage = Element.byXpath("//script[contains(@src,'locale_')]")
              .getAttributeValue("src");
          currentLanguage = currentLanguage
              .substring(currentLanguage.lastIndexOf("locale_") + 7,
                  currentLanguage.lastIndexOf("-"));
          break;
        }
      } catch (StaleElementReferenceException ignored) {
      }
      attempts++;
    }
    return currentLanguage;
  }

  public void waitForUrlContainsLanguageParameter(LanguageEnum parameter) {
    super.waitForUrlContainsText("lang=" + parameter.getLocaleName().charAt(0));
  }
}
