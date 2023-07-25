package com.epmrdpt.regression.notification;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.SettingsService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22480_VerifyThatUserCanGetNotificationWithAutomaticReplyText",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_22480_VerifyThatUserCanGetNotificationWithAutomaticReplyText {

  private final String TRAINING_NAME = "AutoTest_AutomaticReplyText";
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  private NotificationPopUpScreen notificationPopUpScreen;
  private SettingsService settingsService;
  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    notificationPopUpScreen = new NotificationPopUpScreen();
    headerScreen = new HeaderScreen();
    settingsService = new SettingsService();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.forNotificationLanguageSwitch());
    settingsService.navigateToSettingsScreen();
    settingsService.changeNotificationLanguageToLocaleLanguage();
    headerScreen.clickTrainingListNavigationLink();
    new TrainingCardsSectionService().openTrainingDescription(TRAINING_NAME);
    new RegistrationForTrainingService().registerForTraining(cityOfResidence, countryOfResidence);
    headerScreen.waitUntilNewNotificationArrives()
        .clickNotificationButton()
        .waitBellOpening()
        .waitForVisibilityAndClickNotificationLink();
    notificationPopUpScreen
        .waitForNotificationPopupVisibility();
  }

  @Test
  public void checkUserGetAutomaticReply() {
    assertEquals(
        notificationPopUpScreen.getFullMessageWithOutTitleAndPatternMessage(),
        LocaleProperties.getValueOf(LocalePropertyConst.DETAIL_TRAINING_SCREEN_AUTOMATIC_REPLY),
        "Automatic reply isn't correct!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void cancelRegistration() {
    notificationPopUpScreen.closePopUpScreen();
    new RegistrationForTrainingService().cancelRegistrationForTraining();
  }
}
