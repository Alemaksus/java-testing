package com.epmrdpt.regression.settings;

import static com.epmrdpt.bo.user.UserFactory.forNotificationLanguageSwitch;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static java.lang.String.format;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.SettingsService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13170_VerifyThatMainSectionOfNotificationDependsOnChosenValueInNotificationLanguageDropdown",
    groups = {"full", "regression"})
public class EPMRDPT_13170_VerifyThatMainSectionOfNotificationDependsOnChosenValueInNotificationLanguageDropdown {

  private HeaderScreen headerScreen;
  private SettingsScreen settingsScreen;
  private SettingsService settingsService;
  private RegistrationForTrainingService registrationForTrainingService;
  private NotificationModuleScreen notificationModuleScreen;
  private SoftAssert softAssert;

  private final int notificationIndex = 0;
  private final String trainingName = "AutoTestWithCustom";
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new HeaderScreen();
    settingsScreen = new SettingsScreen();
    settingsService = new SettingsService();
    registrationForTrainingService = new RegistrationForTrainingService();
    notificationModuleScreen = new NotificationModuleScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            forNotificationLanguageSwitch())
        .waitProfileMenuDisplayed()
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickSettingsButton();
    headerScreen.clickNotificationButton()
        .clickMarkAllAsReadButton();
    headerScreen.openPageInNewTab(getEnv());
    new TrainingCardsSectionService()
        .openTrainingDescription(trainingName)
        .switchToFirstWindowIfMoreThanOne();
  }

  @DataProvider(name = "Provider of languages and notification-subject")
  public static Object[][] provideLanguagesAndNotificationSubject() {
    return new Object[][]{
        {LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN), "Ответ на вашу заявку"},
        {LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH),
            "Response to your application"},
        {LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN),
            "Відповідь на вашу заявку"}
    };
  }

  @Test(dataProvider = "Provider of languages and notification-subject")
  public void checkMainSectionNotificationAfterSettingNotificationLanguage(String language,
      String notificationSubject) {
    softAssert = new SoftAssert();
    settingsService.changeNotificationLanguage(language);
    softAssert.assertTrue(settingsScreen.waitNotificationLanguageDropDownButtonDisplayed()
            .isSelectedNotificationLanguagePresent(language),
        format("Notification language is not set to %s", language));
    headerScreen.switchToLastWindow();
    registrationForTrainingService.registerForTraining(cityOfResidence, countryOfResidence)
        .switchToFirstWindowIfMoreThanOne();
    softAssert.assertTrue(
        headerScreen.waitUntilNewNotificationArrives().clickNotificationButton().isScreenLoaded(),
        "Notification dropdown isn't loaded!");
    softAssert.assertEquals(
        notificationModuleScreen.getNotificationSubjectByIndex(notificationIndex),
        notificationSubject, format("Notification is not in %s language!", language));
    notificationModuleScreen.clickNotificationByIndex(notificationIndex)
        .waitTrainingBodyExpanded()
        .closePopUpScreen()
        .waitProfileMenuDisplayed();
    softAssert.assertAll();
  }
}
