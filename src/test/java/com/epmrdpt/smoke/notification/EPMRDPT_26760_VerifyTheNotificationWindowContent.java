package com.epmrdpt.smoke.notification;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_26760_VerifyTheNotificationWindowContent",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_26760_VerifyTheNotificationWindowContent {

  private HeaderScreen headerScreen;
  private NotificationPopUpScreen notificationPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new HeaderScreen();
    notificationPopUpScreen = new NotificationPopUpScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asStudentForNotificationPage());
    new NotificationService().markAllNotificationAsUnread();
    headerScreen.clickUniversityProgramLogo().waitTrainingListNavigationLinkVisibility();
  }

  @Test(priority = 1)
  public void checkThatPopUpWindowNotificationDisplayed() {
    headerScreen.clickNotificationButton().waitNotificationItemsVisibility();
    int randomNotification = (int) (Math.random() * new NotificationModuleScreen()
        .getNotificationsCount());
    new NotificationModuleScreen().clickNotificationByIndex(randomNotification);
    assertTrue(notificationPopUpScreen.isScreenLoaded(), "Pop up of notification didn't opened!");
  }

  @Test(priority = 2)
  public void checkThatUniversityProgramLogoDisplayed() {
    assertTrue(notificationPopUpScreen.isUniversityProgramLogoDisplayed(),
        "University program logo isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatUniversityProgramTitleDisplayed() {
    assertTrue(notificationPopUpScreen.isUniversityProgramTitleDisplayed(),
        "University program title isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatPopUpScreenHasMessage() {
    assertTrue(notificationPopUpScreen.getFullMessage().length() > 1,
        "Notification message is empty!");
  }

  @Test(priority = 3)
  public void checkThatSocialLinksDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(notificationPopUpScreen.isFacebookIconDisplayed(),
        "Facebook icon isn't displayed!");
    softAssert.assertTrue(notificationPopUpScreen.isTwitterIconDisplayed(),
        "Twitter icon isn't displayed!");
    softAssert.assertTrue(notificationPopUpScreen.isLinkedInIconDisplayed(),
        "Linked In icon isn't displayed!");
    softAssert.assertTrue(notificationPopUpScreen.isYoutubeIconDisplayed(),
        "Youtube icon isn't displayed!");
    softAssert.assertFalse(notificationPopUpScreen.isInstagramIconDisplayed(),
        "Instagram icon is displayed!");
    softAssert.assertFalse(notificationPopUpScreen.isVkIconDisplayed(),
        "Vk icon is displayed!");
    softAssert.assertAll();
  }
}
