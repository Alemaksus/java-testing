package com.epmrdpt.smoke.training;

import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.NotificationScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_105581_VerifyThatUserInRegisteredStatusDoesNotReceiveEventRecordingIsAvailableNotificationWhenAddLinkToEventRecordingBlockIsNotCompleted",
    groups = {"full", "smoke"})
public class EPMRDPT_105581_VerifyThatUserInRegisteredStatusDoesNotReceiveEventRecordingIsAvailableNotificationWhenAddLinkToEventRecordingBlockIsNotCompleted {

  private static final String EVENT_NOTIFICATION = "Event recording is available";
  private NotificationScreen notificationScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    notificationScreen = new LoginService().login(UserFactory.withSimplePermission())
        .clickNotificationButton()
        .clickAllNotificationsButton()
        .typeSearchText(EVENT_NOTIFICATION)
        .clickFindButton();
  }

  @Test
  public void checkThatNotificationIsAbsent() {
    assertFalse(notificationScreen.isNotificationDisplayedByName(EVENT_NOTIFICATION), "Notifaction is displayed!");
  }
}
