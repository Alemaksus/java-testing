package com.epmrdpt.smoke.hometraining;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_REGISTRATION_NOTIFICATION_CONTENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_REGISTRATION_NOTIFICATION_TITLE;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.SettingsService;
import com.epmrdpt.services.TrainingCardsSectionService;
import java.util.NoSuchElementException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(
    description = "EPMRDPT_22479_VerifyThatUserReceivesNotificationAboutRegistrationForTheTraining",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_22479_VerifyThatUserReceivesNotificationAboutRegistrationForTheTraining {

  private final int notificationIndex = 0;
  private final String trainingName = "AutoTest_NotificationLanguageVerification";
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";
  private String notificationForTrainingPortal = String.format(
      getValueOf(TRAINING_DESCRIPTION_REGISTRATION_NOTIFICATION_CONTENT), "EPAM Training Portal");
  private String notificationForUniversityProgram = String.format(
      getValueOf(TRAINING_DESCRIPTION_REGISTRATION_NOTIFICATION_CONTENT),
      "EPAM University Program");

  private HeaderScreen headerScreen;
  private SettingsService settingsService;
  private RegistrationForTrainingService registrationForTrainingService;
  private NotificationModuleScreen notificationModuleScreen;
  private NotificationPopUpScreen notificationPopUpScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    settingsService = new SettingsService();
    registrationForTrainingService = new RegistrationForTrainingService();
    notificationModuleScreen = new NotificationModuleScreen();
    notificationPopUpScreen = new NotificationPopUpScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
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

  @Test
  public void checkMainSectionNotificationAfterSettingNotificationLanguage() {
    softAssert = new SoftAssert();
    settingsService.changeNotificationLanguage(getCurrentLocaleLanguage());
    headerScreen.switchToLastWindow();
    registrationForTrainingService
        .registerForTraining(cityOfResidence, countryOfResidence)
        .switchToFirstWindowIfMoreThanOne();
    softAssert.assertTrue(
        headerScreen
            .waitUntilNewNotificationArrives()
            .clickNotificationButton()
            .isScreenLoaded(),
        "Notification dropdown isn't loaded!");
    softAssert.assertEquals(
        notificationModuleScreen.getNotificationSubjectByIndex(notificationIndex),
        getValueOf(TRAINING_DESCRIPTION_REGISTRATION_NOTIFICATION_TITLE),
        "Notification title is not equals expected text!");
    notificationModuleScreen
        .clickNotificationByIndex(notificationIndex)
        .waitTrainingBodyExpanded();
    String notificationContent = new NotificationService().getNotificationPopUpContent();
    softAssert.assertTrue(notificationContent.equals(notificationForTrainingPortal) ||
            notificationContent.equals(notificationForUniversityProgram),
        "Notification content is not equal to expected text!");
    softAssert.assertTrue(notificationPopUpScreen.isSocialNetworkLinksDisplayed(),
        "Social network icons aren't displayed!");
    notificationPopUpScreen
        .closePopUpScreen()
        .waitProfileMenuDisplayed();
    softAssert.assertAll();
  }

  private String getCurrentLocaleLanguage() {
    switch (System.getProperty("locale")) {
      case "ru":
        return getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN);
      case "en":
        return getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH);
      case "ukr":
        return getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN);
      default:
        throw new NoSuchElementException("There is no such locale");
    }
  }
}
