package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asAdminLocatedRoleAutoTestCity;
import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.bo.user.UserFactory.asResourceManagerLocatedRoleAutoTestCity;
import static com.epmrdpt.bo.user.UserFactory.asTrainingManagerLocatedRoleAutoTestCity;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.events.EventOnlineFormat;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.screens.NotificationScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import com.epmrdpt.services.ReactEventsManagementService;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90315_VerifyThatUserReceivesNotification1hBeforeTheEventStart",
    groups = {"full", "regression"})
public class EPMRDPT_90315_VerifyThatUserReceivesNotification1hBeforeTheEventStart {

  private EventOnlineFormat event;
  private ReactEventsManagementService reactEventsManagementService;
  private NotificationScreen notificationScreen;
  private boolean isUserRegistered = false;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void createEvent() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(asEventManager());
    reactEventsManagementService = new ReactEventsManagementService();
    event = reactEventsManagementService.getEventWithRequiredFieldsAndLink();
    reactEventsManagementService.createEventWithMarksAndLink(event)
        .clickPublishedStatusButton()
        .clickOkButtonInPopUp();
    new ReactLoginService().signOut();
  }

  @DataProvider(name = "Provider of users with message notification")
  public static Object[][] provideUsers() {
    return new Object[][]{
        {asResourceManagerLocatedRoleAutoTestCity(), "Event is coming soon"},
        {asTrainingManagerLocatedRoleAutoTestCity(), "Мероприятие скоро стартует"},
        {asAdminLocatedRoleAutoTestCity(), "Подія скоро стартує"}
    };
  }

  @Test(dataProvider = "Provider of users with message notification")
  public void checkNotification1hBeforeTheEventStart(User user, String notificationText) {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
    reactEventsManagementService.registerOnEvent(event.getName());
    notificationScreen = new NotificationService().openNotificationScreen();
    new NotificationService().waitForNotificationDisplayedByName(notificationText);
    assertTrue(notificationScreen.isNotificationDisplayedByName(notificationText),
        String.format("The '%s' notification is not present in the notifications list!",
            notificationText));
    isUserRegistered = true;
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void setNotificationAsReadAndLogout() {
    if (isUserRegistered) {
      notificationScreen.markAllNotificationAsRead();
    }
    new LoginService().logout();
  }
}
