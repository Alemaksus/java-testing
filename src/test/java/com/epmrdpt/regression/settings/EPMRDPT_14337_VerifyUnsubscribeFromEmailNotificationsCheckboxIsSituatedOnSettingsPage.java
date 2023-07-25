package com.epmrdpt.regression.settings;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14337_VerifyUnsubscribeFromEmailNotificationsCheckboxIsSituatedOnSettingsPage",
    groups = {"full", "regression"})
public class EPMRDPT_14337_VerifyUnsubscribeFromEmailNotificationsCheckboxIsSituatedOnSettingsPage {

  @Test(priority = 1)
  public void checkSettingsScreenLoading() {
    assertTrue(new LoginService()
        .loginAndSetLanguage(UserFactory.withDepartmentTraining())
        .clickProfileMenu()
        .clickSettingsButton()
        .isScreenLoaded(), "Settings page hasn't loaded!");
  }

  @Test(priority = 2)
  public void checkEmailNotificationCheckbox() {
    SettingsScreen settingsScreen = new SettingsScreen();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(settingsScreen.isEmailNotificationCheckboxDisplayed(),
        "'Email Notification' checkbox isn't displayed!");
    softAssert.assertTrue(settingsScreen.isEmailNotificationCheckboxLabelDisplayed(),
        "'Email Notification' checkbox label isn't displayed!");
    softAssert.assertEquals(settingsScreen.getEmailNotificationCheckboxLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.SETTINGS_EMAIL_NOTIFICATION_CHECKBOX_LABEL),
        "'Email Notification' checkbox label text isn't displayed correctly!");
    softAssert.assertAll();
  }
}
