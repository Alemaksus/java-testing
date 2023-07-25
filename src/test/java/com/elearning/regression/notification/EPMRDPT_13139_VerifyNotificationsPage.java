package com.epmrdpt.regression.notification;

import static com.epmrdpt.bo.user.UserFactory.asStudentWithNotifications;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13139_VerifyNotificationsPage",
    groups = {"full", "regression", "student"})
public class EPMRDPT_13139_VerifyNotificationsPage {

  private final String counterWithoutSelectingNotifications = "(0)";
  private final String counterOnSelectingFiveNotifications = "(5)";
  private final String secondPageNumber = "2";
  private final String searchPhrase = "test";
  private final int indexThree = 3;
  private final int minimumNotifications = 10;
  private int attemptsToCheck = 15;
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  private NotificationScreen notificationScreen;
  private NotificationService notificationService;
  private SoftAssert softAssert;

  private final String trainingName = "With English test";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    notificationScreen = new NotificationScreen();
    notificationService = new NotificationService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentWithNotifications());
    int totalNotificationsCount = notificationService.getTotalNotificationsCount();
    new HeaderScreen().clickUniversityProgramLogo();
    int notificationsToBeGenerated = minimumNotifications >= totalNotificationsCount ?
        minimumNotifications - totalNotificationsCount + 1 : 0;
    notificationService
        .generateNotifications(trainingName, notificationsToBeGenerated, cityOfResidence,
            countryOfResidence)
        .ensureNotificationsReceived(minimumNotifications, attemptsToCheck);
  }

  @Test(priority = 1)
  public void checkNotificationScreenContent() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(notificationScreen.isMessageHeaderDisplayed(),
            "'Message' header isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isSenderHeaderDisplayed(),
            "'Sender' header isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isDateHeaderDisplayed(),
            "'Date' header isn't displayed!");
    notificationScreen.clickActionsWithCheckedDropDown();
    softAssert
        .assertTrue(notificationScreen.isMarkAsUnreadButtonDisplayed(),
            "'Mark as unread' button isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isMarkAsReadButtonDisplayed(),
            "'Mark as read' button isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isDeleteButtonDisplayed(),
            "'Delete' button isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isUncheckButtonDisplayed(),
            "'Uncheck' button isn't displayed!");
    notificationScreen.clickActionsWithCheckedDropDown();
    softAssert
        .assertTrue(notificationScreen.getActionsWithCheckedDropDownText()
                .contains(counterWithoutSelectingNotifications),
            "'Counter' value in 'Actions With Checked' drop-down button isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isUnreadFilterLabelDisplayed(),
            "'Unread' filter label isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isReadFilterLabelDisplayed(),
            "'Read' filter label isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isAllFilterLabelDisplayed(),
            "'All' filter label isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.isSearchFieldDisplayed(),
            "'Search' field isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkCounterInActionsWithCheckedDropDownButton() {
    softAssert = new SoftAssert();
    for (int notificationCounter = 0; notificationCounter < 5; notificationCounter++) {
      notificationScreen.clickNotificationCheckboxByIndex(notificationCounter);
      softAssert
          .assertTrue(
              notificationScreen.isNotificationCheckboxSelectedByIndex(notificationCounter + 1),
              format("Notification: %d isn't checked!", notificationCounter));
    }
    softAssert
        .assertTrue(notificationScreen.getActionsWithCheckedDropDownText()
                .contains(counterOnSelectingFiveNotifications),
            "'Counter' value in 'Actions With Checked' drop-down button is incorrect!");
    notificationScreen.clickNextPaginationButton();
    softAssert
        .assertEquals(notificationScreen.getCurrentPageNumber(), secondPageNumber,
            "Next page isn't displayed!");
    softAssert
        .assertTrue(notificationScreen.getActionsWithCheckedDropDownText()
                .contains(counterWithoutSelectingNotifications),
            "'Counter' value in 'Actions With Checked' drop-down button is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkMarkAsReadButtonInActionsWithCheckedDropDown() {
    softAssert = new SoftAssert();
    notificationScreen.clickRefreshButton();
    notificationScreen.waitScreenLoading();
    for (int notificationCounter = 0; notificationCounter < 5; notificationCounter++) {
      notificationScreen.clickNotificationCheckboxByIndex(notificationCounter);
    }
    notificationScreen
        .clickActionsWithCheckedDropDown()
        .clickMarkAsReadButton()
        .waitForActionsToBeApplied()
        .clickRefreshButton();
    for (int notificationCounter = 1; notificationCounter <= 5; notificationCounter++) {
      softAssert
          .assertTrue(notificationScreen.isNotificationReadByIndex(notificationCounter),
              format("Notification: %d isn't marked as read!", notificationCounter));
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkReadCheckboxFilter() {
    softAssert = new SoftAssert();
    notificationScreen.clickReadFilterCheckbox().waitForUnreadNotificationAbsence();
    int notificationCounter = notificationScreen.getNotificationItemsCount();
    while (notificationCounter > 0) {
      softAssert
          .assertTrue(notificationScreen.isNotificationReadByIndex(notificationCounter),
              format("Notification: %d isn't a read notification!", notificationCounter));
      notificationCounter--;
    }
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkUnreadCheckboxFilter() {
    softAssert = new SoftAssert();
    notificationScreen.clickUnreadFilterCheckbox().waitForReadNotificationAbsence();
    int notificationCounter = notificationScreen.getNotificationItemsCount();
    while (notificationCounter > 0) {
      softAssert
          .assertTrue(notificationScreen.isNotificationUnreadByIndex(notificationCounter),
              format("Notification: %d isn't an unread notification!", notificationCounter));
      notificationCounter--;
    }
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkAllCheckboxFilter() {
    softAssert = new SoftAssert();
    notificationScreen.clickAllFilterCheckbox().waitForReadNotificationPresence();
    softAssert
        .assertTrue(notificationScreen.isUnreadNotificationItemDisplayed() &&
                notificationScreen.isReadNotificationItemDisplayed(),
            "All notifications aren't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkMarkAsUnreadButtonInActionsWithCheckedDropDown() {
    softAssert = new SoftAssert();
    notificationScreen.scrollToTop();
    for (int notificationCounter = 0; notificationCounter < 5; notificationCounter++) {
      notificationScreen.clickNotificationCheckboxByIndex(notificationCounter);
    }
    notificationScreen
        .clickActionsWithCheckedDropDown()
        .clickMarkAsUnreadButton()
        .waitForActionsToBeApplied()
        .clickRefreshButton();
    for (int notificationCounter = 1; notificationCounter <= 5; notificationCounter++) {
      softAssert
          .assertTrue(notificationScreen.isNotificationUnreadByIndex(notificationCounter),
              format("Notification: %d isn't marked as unread!", notificationCounter));
    }
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkUncheckButtonInActionsWithCheckedDropDown() {
    softAssert = new SoftAssert();
    notificationScreen.scrollToTop();
    for (int notificationCounter = 0; notificationCounter < 5; notificationCounter++) {
      notificationScreen.clickNotificationCheckboxByIndex(notificationCounter);
    }
    notificationScreen
        .clickActionsWithCheckedDropDown()
        .clickUncheckButton()
        .waitForActionsToBeApplied();
    for (int notificationCounter = 1; notificationCounter <= 5; notificationCounter++) {
      softAssert
          .assertFalse(
              notificationScreen.isNotificationCheckboxSelectedByIndex(notificationCounter),
              format("Notification: %d checkbox isn't unchecked!", notificationCounter));
    }
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkDeleteButtonInActionsWithCheckedDropDown() {
    int notificationsCountBeforeDeletion = notificationScreen.getTotalNotificationsCount();
    for (int notificationCounter = 0; notificationCounter < 5; notificationCounter++) {
      notificationScreen.clickNotificationCheckboxByIndex(notificationCounter);
    }
    notificationScreen
        .clickActionsWithCheckedDropDown()
        .clickDeleteButton()
        .waitForDeletionConfirmationPopUpVisibility()
        .clickOkInDeletionConfirmationPopUp()
        .clickRefreshButton();
    int notificationsCountAfterDeletion = notificationScreen.getTotalNotificationsCount();
    assertTrue(notificationsCountBeforeDeletion > notificationsCountAfterDeletion,
        "Notifications weren't deleted!");
  }

  @Test(priority = 10)
  public void checkNotificationDeleteButton() {
    int notificationsCountBeforeDeletion = notificationScreen.getTotalNotificationsCount();
    notificationScreen
        .clickNotificationDeleteButtonByIndex(indexThree)
        .waitForDeletionConfirmationPopUpVisibility()
        .clickOkInDeletionConfirmationPopUp()
        .clickRefreshButton();
    int notificationsCountAfterDeletion = notificationScreen.getTotalNotificationsCount();
    assertTrue(notificationsCountBeforeDeletion > notificationsCountAfterDeletion,
        "Notification wasn't deleted!");
  }

  @Test(priority = 11)
  public void checkSearchResult() {
    int notificationsPresentCount = notificationScreen.getNotificationItemsCount();
    notificationScreen.typeSearchText(searchPhrase)
        .clickFindButton()
        .waitForSearchResultsDisplayed(notificationsPresentCount);
    assertTrue(
        notificationScreen.getNotificationItemsSubjectText().stream()
            .allMatch(subject -> subject.contains(searchPhrase)),
        format("Search results are not apt! Not all subjects contain '%s'!", searchPhrase));
  }
}
