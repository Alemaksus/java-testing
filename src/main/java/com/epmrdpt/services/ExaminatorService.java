package com.epmrdpt.services;

import com.epmrdpt.screens.ExaminatorScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.screens.ProfileScreen;

public class ExaminatorService {

  private static final String REGISTRATION_NOTIFICATION_NAME = "Assigning of registration test";
  private static final String ENGLISH_NOTIFICATION_NAME = "You have been assigned English test";

  private ExaminatorScreen examinatorScreen = new ExaminatorScreen();

  public void failRegistrationTestInNotification(String notificationName) {
    failTestInNotification(notificationName);
  }

  public void failEnglishTestInNotification(String notificationName) {
    failTestInNotification(notificationName);
  }

  public void failEnglishTestInProfile() {
    new ProfileScreen()
            .clickTakeTestButton()
            .clickIfDisplayedRedirectToEnglishTestOkButton();
    examinatorScreen.switchToLastWindow();
    examinatorScreen.waitScreenLoaded();
    failEnglishTest();
    examinatorScreen.closeLastWindowAndSwitchToPreviousIfMoreThanOne();
  }

  private void failTestInNotification(String notificationName) {
    examinatorScreen = new HeaderScreen()
        .waitForNotificationButtonVisibility()
        .clickNotificationButton()
        .waitNotificationItemsVisibility()
        .clickNotificationByName(notificationName)
        .waitForNotificationPopupVisibility()
        .clickLinkToRegistrationTest();
    examinatorScreen.switchToLastWindow();
    examinatorScreen.waitScreenLoaded();
    switch (notificationName) {
      case REGISTRATION_NOTIFICATION_NAME:
        failRegistrationTest();
        break;
      case ENGLISH_NOTIFICATION_NAME:
        failEnglishTest();
        break;
      default:
        throw new UnsupportedOperationException(String.format(
            "Test with this notification: '%s' is unsupported", notificationName));
    }
    examinatorScreen.closeLastWindowAndSwitchToPreviousIfMoreThanOne();
    new NotificationPopUpScreen().closePopUpScreen();
  }

  private ExaminatorScreen failRegistrationTest() {
    int questionsAmount = 9;
    examinatorScreen.clickStartButton();
    for (int i = 0; i < questionsAmount && !examinatorScreen.isFinishButtonDisplayed(); i++) {
      answerQuestion();
    }
    return finishTest().clickResultButton();
  }

  private ExaminatorScreen failEnglishTest() {
    int testsAmount = 3;
    int questionsAmount = 7;
    examinatorScreen.clickStartButton()
        .waitBeforeStartEnglishTestVisibility()
        .clickPopUpStartButton();
    for (int i = 0; i < testsAmount; i++) {
      for (int j = 0; j < questionsAmount && !examinatorScreen.isFinishButtonDisplayed(); j++) {
        if (i < 2) {
          answerQuestion();
        } else {
          try {
            answerQuestion();
          }
          catch (Exception e) {
            return examinatorScreen;
          }
        }
      }
      finishTest();
    }
    return examinatorScreen;
  }

  private void answerQuestion() {
    examinatorScreen
        .waitCurrentQuestionVisibility()
        .clickFirstRadioButton()
        .clickNextQuestionButton();
  }

  private ExaminatorScreen finishTest() {
    return examinatorScreen
        .clickFinishButton()
        .clickSubmitButton();
  }
}
