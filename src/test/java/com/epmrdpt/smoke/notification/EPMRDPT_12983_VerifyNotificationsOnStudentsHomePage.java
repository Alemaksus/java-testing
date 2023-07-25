package com.epmrdpt.smoke.notification;

import static com.epmrdpt.utils.RandomUtils.getRandomNumber;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12983_VerifyNotificationsOnStudentsHomePage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_12983_VerifyNotificationsOnStudentsHomePage {

  private final int INDEX_FOUR = 4;
  private final String ERROR_MESSAGE = "Notification by %s index didn't displayed";
  private final String trainingName = "AutoTest_3RegistrationOpen";
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  private HeaderScreen headerScreen;
  private NotificationModuleScreen notificationModuleScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    headerScreen = new HeaderScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(
            UserFactory.asStudentForNotificationPage())
        .waitForNotificationButtonVisibility();
    notificationModuleScreen = headerScreen.clickNotificationButton().waitBellOpening();
    if (notificationModuleScreen.isNotificationDisplayed(INDEX_FOUR)) {
      headerScreen.clickNotificationButton();
    } else {
      headerScreen.clickTrainingListNavigationLink();
      new NotificationService().generateNotifications(trainingName, INDEX_FOUR, cityOfResidence,
          countryOfResidence);
    }
  }

  @Test(priority = 1)
  public void checkThatNotificationButtonIsDisplayed() {
    assertTrue(headerScreen.isNotificationButtonDisplayed(),
        "Notification button isn't displayed in header!");
  }

  @Test(priority = 2)
  public void checkThatNotificationDropdownIsDisplayed() {
    headerScreen.clickRefreshButton();
    headerScreen.clickNotificationButton().waitBellOpening();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(notificationModuleScreen.isScreenLoaded(),
        "Notifications List didn't open!");
    softAssert.assertFalse(notificationModuleScreen.getAllNotificationsHeaderText().isEmpty(),
        "Notifications List didn't contain header!");
    for (int notificationIndex = 0; notificationIndex < INDEX_FOUR; notificationIndex++) {
      softAssert.assertTrue(notificationModuleScreen
              .isNotificationsByIndexDisplayed(notificationIndex),
          String.format(ERROR_MESSAGE, notificationIndex));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatNotificationPopUpWindowDisplayed() {
    int randomNotification = getRandomNumber(INDEX_FOUR - 1);
    assertTrue(notificationModuleScreen.clickNotificationByIndex(randomNotification)
        .isScreenLoaded(), "Pop up of notification didn't opened!");
  }
}
