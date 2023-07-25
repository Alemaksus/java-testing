package com.epmrdpt.smoke.notification;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13236_VerifyThatNotificationPageIsPresentedAfterClickingAllNotificationsLink",
    groups = {"full", "smoke"})
public class EPMRDPT_13236_VerifyThatNotificationPageIsPresentedAfterClickingAllNotificationsLink {

  NotificationModuleScreen notificationModuleScreen;

  @BeforeClass
  public void screenInitialization() {
    notificationModuleScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait
            (UserFactory.asStudentForNotificationPage())
        .clickNotificationButton()
        .waitBellOpening();
  }

  @Test(priority = 1)
  public void checkThatDropDownWithNotificationsIsDisplayed() {
    assertTrue(
        notificationModuleScreen.isDropDownWithNotificationsDisplayed(),
        "Drop down with notifications isn't displayed!");
  }

  @Test(priority = 2)
  public void checkNotificationPageIsPresentedAfterClickingAllNotificationsLink() {
    assertTrue(
        notificationModuleScreen.clickAllNotificationsButton().isScreenLoaded(),
        "Notification page isn't loaded!");
  }
}
