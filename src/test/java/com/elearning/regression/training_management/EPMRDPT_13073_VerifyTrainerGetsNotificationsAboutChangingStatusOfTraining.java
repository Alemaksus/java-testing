package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_STATUS_CHANGE_NOTIFICATION;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.services.SettingsService;
import java.util.NoSuchElementException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13073_VerifyTrainerGetsNotificationsAboutChangingStatusOfTraining",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_13073_VerifyTrainerGetsNotificationsAboutChangingStatusOfTraining {

  private static final String TRAINING_NAME = "AutoTest_StatusChanged";
  private static final String NOTIFICATIONS_MAIN_TEXT = getValueOf(
      TRAINING_DESCRIPTION_STATUS_CHANGE_NOTIFICATION);
  private static final int UNREAD_NOTIFICATIONS_COUNT = 0;
  private String userName;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private NotificationScreen notificationScreen;
  private LoginService loginService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainerAndTraining() {
    HeaderScreen headerScreen = new HeaderScreen();
    loginService = new LoginService();
    userName = loginService
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(asTrainer())
        .getUserNameText();
    headerScreen
        .clickNotificationButton()
        .clickMarkAllAsReadButton();
    headerScreen.clickSettingsButton();
    new SettingsService().changeNotificationLanguage(getCurrentLocaleLanguage());
    loginService
        .logout()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .waitScreenLoaded();
  }

  @Test(priority = 1)
  public void checkTrainingManagerCanSetDraftStatusForTraining() {
    assertTrue(
        reactDetailTrainingScreen
            .clickDraftStatusButton()
            .waitChangeStatusPopUpVisibility()
            .clickConfirmationOfChangingStatusButton()
            .waitNotificationPopUpVisibility()
            .isNotificationPopUpDisplayed(),
        "Manager can not set draft status to training");
  }

  @Test(priority = 2)
  public void checkTrainingManagerCanSetPlannedAfterDraftStatusForTraining() {
    assertTrue(
        reactDetailTrainingScreen
            .closeNotificationPopUp()
            .clickPlannedStatusButton()
            .waitChangeStatusPopUpVisibility()
            .clickConfirmationOfChangingStatusButton()
            .waitNotificationPopUpVisibility()
            .isNotificationPopUpDisplayed(),
        "Manager can not set planned status after draft status to training!");
  }

  @Test(priority = 3)
  public void checkTrainingManagerCanSetRegistrationOpenAfterPlannedStatusForTraining() {
    assertTrue(
        reactDetailTrainingScreen
            .closeNotificationPopUp()
            .clickRegistrationOpenStatusButton()
            .waitChangeStatusStaffingDeskPopUpVisibility()
            .clickConfirmationOfChangingStaffingDeskStatusButton()
            .waitNotificationPopUpVisibility()
            .isNotificationPopUpDisplayed(),
        "Manager can not set registration open status after planned status to training!");
  }

  @Test(priority = 4)
  public void checkTrainingManagerCanSetRegistrationClosedAfterRegistrationOpenStatusForTraining() {
    assertTrue(
        reactDetailTrainingScreen
            .closeNotificationPopUp()
            .clickRegistrationClosedStatusButton()
            .waitChangeStatusPopUpVisibility()
            .clickConfirmationOfChangingStatusButton()
            .waitNotificationPopUpVisibility()
            .isNotificationPopUpDisplayed(),
        "Manager can not set registration closed status after registration closed status to training!");
    new ReactHeaderScreen()
        .clickUserInfoToggle()
        .signOut();
  }

  @Test(priority = 5)
  public void checkTrainerReceivedNotificationsAboutTrainingStatusChanged() {
    notificationScreen = loginService
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(asTrainer())
        .clickNotificationButton()
        .clickAllNotificationsButton()
        .waitScreenLoading()
        .waitForUnreadNotificationPresence()
        .clickUnreadFilterCheckbox()
        .waitForReadNotificationAbsence();
    assertTrue(notificationScreen.getNotificationItemsCount() > UNREAD_NOTIFICATIONS_COUNT,
        "Trainer did not receive notification about training status");
  }

  @Test(priority = 6)
  public void checkNotificationText() {
    String messageText = notificationScreen
        .clickFirstNotification()
        .waitTrainingBodyExpanded()
        .getFullMessage();
    assertTrue(messageText.contains(format(NOTIFICATIONS_MAIN_TEXT, userName, TRAINING_NAME)),
        "Notification text did not contains information about training status changing");
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
