package com.epmrdpt.services;

import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.screens.ConfirmDeleteNotificationPopUpScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.screens.NotificationScreen;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.TimeoutException;

public class NotificationService {

  private static final int NUMBER_OF_ATTEMPTS = 17; // max time of wait is 5 min 20 sec
  private HeaderScreen headerScreen = new HeaderScreen();
  private NotificationScreen notificationScreen = new NotificationScreen();
  private NotificationPopUpScreen notificationPopUpScreen = new NotificationPopUpScreen();

  public NotificationService markAllNotificationAsUnread() {
    headerScreen.clickNotificationButton()
        .waitBellOpening()
        .clickAllNotificationsButton();
    notificationScreen
        .clickOnMessageCheckbox()
        .waitMessageToBeChecked()
        .clickActionsWithCheckedDropDown()
        .waitActionsWithCheckedDDLLoading()
        .clickMarkAsUnreadButton()
        .clickOnReadCheckBox();
    return this;
  }

  public String getNotificationPopUpContent() {
    List<String> notificationContent = new ArrayList<>();
    notificationContent.add(notificationPopUpScreen.getNotificationBodyInformationText());
    notificationContent.add(notificationPopUpScreen.getNotificationBodyReasonText());
    notificationContent.add(notificationPopUpScreen.getNotificationBodyRequestText());
    return notificationContent.toString();
  }

  public NotificationService generateNotifications(String trainingName, int notificationCount,
      String cityOfResidence, String countryOfResidence) {
    RegistrationForTrainingService registrationService = new RegistrationForTrainingService();
    new TrainingCardsSectionService().openTrainingDescription(trainingName);
    for (int notificationCounter = 0; notificationCounter < notificationCount;
        notificationCounter++) {
      registrationService.generateNotificationsMessage(cityOfResidence, countryOfResidence);
    }
    return this;
  }

  public NotificationScreen openNotificationScreen() {
    new HeaderScreen()
        .clickNotificationButton()
        .clickAllNotificationsButton()
        .waitScreenLoading();
    return notificationScreen;
  }

  public int getTotalNotificationsCount() {
    return openNotificationScreen().getTotalNotificationsCount();
  }

  public NotificationScreen ensureNotificationsReceived(int targetTotalNotificationsCount,
      int attempts) {
    openNotificationScreen();
    for (int i = 0; i < attempts; i++) {
      if (notificationScreen.getTotalNotificationsCount() >= targetTotalNotificationsCount) {
        return notificationScreen;
      }
      notificationScreen.clickRefreshButton();
    }
    throw new IllegalStateException(
        "Didn't receive all notifications, that has been sent during " + attempts + " attempts!");
  }

  public NotificationPopUpScreen openNotificationByName(String notificationName) {
    return new HeaderScreen()
        .clickNotificationButton()
        .clickAllNotificationsButton()
        .waitScreenLoading()
        .clickNotificationItemByName(notificationName)
        .waitForNotificationPopupVisibility();
  }

  public void waitForNotificationDisplayedByName(String name) {
    for (int i = 0; i < NUMBER_OF_ATTEMPTS; i++) {
      try {
        notificationScreen.clickRefreshButton();
        notificationScreen.waitForUnreadNotificationByNameDisplayed(name);
        return;
      } catch (TimeoutException e) {
        Log.logInfoMessage(String.format("waiting notification by name '%s'", name));
      }
    }
  }

  public String getFirstNotificationText() {
    return new HeaderScreen()
        .waitForNotificationButtonVisibility()
        .clickNotificationButtonNew()
        .closeTimezonePopupWindowIfAppears()
        .getNotificationItemsSubjectText()
        .get(0);
  }

  public void deleteFirstNotification() {
    new NotificationScreen().clickNotificationDeleteButtonByIndex(0);
    new ConfirmDeleteNotificationPopUpScreen().clickOkButton()
        .waitRefreshScreen();
  }
}
