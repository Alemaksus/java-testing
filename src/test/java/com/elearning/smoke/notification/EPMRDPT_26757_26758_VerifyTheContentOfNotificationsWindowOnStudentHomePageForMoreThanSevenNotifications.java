package com.epmrdpt.smoke.notification;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_NOTIFICATION_CONTROLLER;
import static com.epmrdpt.bo.user.UserFactory.asStudentForNotificationPage;
import static com.epmrdpt.tokens_storage.TokensStorage.getAdminAuthorizationToken;
import static com.epmrdpt.framework.listeners.RetrieveAuthorizationTokenListener.setUpRestAssuredForUI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.http.ContentType.JSON;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.services.LoginService;
import io.restassured.internal.RequestSpecificationImpl;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_26757_26758_VerifyTheContentOfNotificationsWindowOnStudentHomePageForMoreThanSevenNotifications",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_26757_26758_VerifyTheContentOfNotificationsWindowOnStudentHomePageForMoreThanSevenNotifications {

  private static final int planId = 2838;
  private static final int userId = 326286;
  private static final int numberOfNotifications = 8;
  private boolean areAllTaskReadiedAndReadyToDeletion = false;
  private NotificationModuleScreen notificationModuleScreen;
  private HeaderScreen headerScreen;

  @BeforeTest()
  public void postTrainingManagementNotificationController() {
    setUpRestAssuredForUI();
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getAdminAuthorizationToken());
    for (int i = 0; i < numberOfNotifications; i++) {
      given()
          .queryParam("planId", planId)
          .body(List.of(userId))
          .contentType(JSON)
          .when()
          .post(TRAINING_MANAGEMENT_NOTIFICATION_CONTROLLER);
    }
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asStudentForNotificationPage());
    notificationModuleScreen = headerScreen
        .clickNotificationButton()
        .waitNotificationItemsVisibility();
  }

  @Test(priority = 1)
  public void checkAllNotificationsLink() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        notificationModuleScreen.isScreenLoaded(),
        "'Notifications' window doesn't contain 'All notification' link!");
    softAssert.assertTrue(
        notificationModuleScreen.isAllNotificationsLinkClickable(),
        "'All Notifications' link isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkMarkAllAsReadButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        notificationModuleScreen.isMarkAllAsReadButtonDisplayed(),
        "'Notifications' window doesn't contain 'Mark all as read' button!");
    softAssert.assertTrue(
        notificationModuleScreen.isMarkAllAsReadButtonClickable(),
        "'Mark All As Read' button isn't clickable");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkScrollbar() {
    assertTrue(
        notificationModuleScreen.isDropDownHasScrollbar(),
        "'Notifications' window doesn't contain scrollbar!");
  }

  @Test(priority = 4)
  public void checkNotificationSubject() {
    assertTrue(
        notificationModuleScreen.getNotificationsCount() >= numberOfNotifications,
        "Not all notifications was added to student!");
  }

  @Test(priority = 5)
  public void checkThatAllNotificationsIsNotDisplayedAfterClickingMarkAllAsRead() {
    areAllTaskReadiedAndReadyToDeletion = true;
    notificationModuleScreen.clickMarkAllAsReadButton();
    assertFalse(
        notificationModuleScreen.isNotificationItemDisplayed(),
        "Notifications wasn't not read!");
  }

  @AfterMethod
  public void deleteCreatedNotifications() {
    if (areAllTaskReadiedAndReadyToDeletion) {
      headerScreen
          .clickNotificationButton()
          .clickAllNotificationsButton()
          .waitScreenLoading()
          .clickOnMessageCheckbox()
          .clickActionsWithCheckedDropDown()
          .clickDeleteButton()
          .clickOkInDeletionConfirmationPopUp();
    }
  }
}
