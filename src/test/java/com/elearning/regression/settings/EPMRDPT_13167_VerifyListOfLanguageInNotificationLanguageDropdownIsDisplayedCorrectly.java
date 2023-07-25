package com.epmrdpt.regression.settings;

import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.SettingsService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13167_VerifyListOfLanguageInNotificationLanguageDropdownIsDisplayedCorrectly",
    groups = {"full", "regression"})
public class EPMRDPT_13167_VerifyListOfLanguageInNotificationLanguageDropdownIsDisplayedCorrectly {

  @Test(priority = 1)
  public void checkSettingsScreenLoading() {
    assertTrue(new LoginService()
        .loginAndSetLanguage(UserFactory.withDepartmentTraining())
        .clickProfileMenu()
        .clickSettingsButton()
        .isScreenLoaded(), "Settings page hasn't loaded!");
  }

  @Test(priority = 2)
  public void checkLanguagesInNotificationLanguageDropdown() {
    SettingsScreen settingsScreen = new SettingsScreen();
    SoftAssert softAssert = new SoftAssert();
    new SettingsService()
        .untickEmailNotificationCheckbox()
        .clickNotificationLanguageDropDownButton();
    softAssert.assertTrue(settingsScreen.isLanguageInNotificationLanguageDropdownDisplayed(
            LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH)),
        "English language isn't displayed in 'Notification language' dropdown!");
    softAssert.assertTrue(settingsScreen.isLanguageInNotificationLanguageDropdownDisplayed(
            LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN)),
        "Russian language isn't displayed in 'Notification language' dropdown!");
    softAssert.assertTrue(settingsScreen.isLanguageInNotificationLanguageDropdownDisplayed(
            LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN)),
        "Ukrainian language isn't displayed in 'Notification language' dropdown!");
    softAssert.assertAll();
  }
}
