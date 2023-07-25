package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.utils.StringUtils;
import java.util.List;

public class NotificationScreen extends AbstractScreen {

  private static final String IS_NOTIFICATION_ITEM_CHECKBOX_SELECTED_LOCATOR_PATTERN =
      "//div[contains(@class,'item')][%d]/div[@class='checkbox']//input[@checked]";
  private static final String IS_NOTIFICATION_ITEM_UNREAD_LOCATOR_PATTERN =
      "//div[@class='notification-table--body']/div[contains(@class,'item')][%d]/self::div[contains(@class,'unread')]";
  private static final String IS_NOTIFICATION_ITEM_READ_LOCATOR_PATTERN =
      "//div[@class='notification-table--body']/div[contains(@class,'item')][%d]/self::div[not(contains(@class,'unread'))]";
  private static final String TOTAL_NOTIFICATIONS_REGEX = "\\d+$";
  private static final String UNREAD_NOTIFICATION_LOCATOR_PATTERN =
      "//div[@class='item ng-scope unread']/div[contains(text(),'%s')]";
  private static final String MENU_PATTERN = "//*[@class='nav-container']/a[contains(@href,'/%s')]";

  private Element searchField = Element.byCss("input.search");
  private Element findButton = Element.byCss("button.search-submit");
  private Element selectAllCheckBox = Element.byXpath("//div[@class='checkbox']//span");
  private Element selectAllCheckBoxWithCheckedAttribute = Element.byId("checkAll");
  private Element actionsWithCheckedDropDown = Element.byXpath("//div[@*='openChoose()']");
  private Element markAsUnreadButton = Element
      .byXpath("//div[contains(@ng-click,'markAsNotViewed')]");
  private Element markAsReadButton = Element.byXpath("//div[contains(@ng-click,'markAsViewed')]");
  private Element deleteButton = Element.byXpath("//div[contains(@ng-click, 'removeChecked')]");
  private Element uncheckButton = Element.byXpath("//div[contains(@ng-click, 'uncheckAll')]");
  private Element actionsWithCheckedDDL = Element
      .byXpath("//div[contains(@ng-class, 'dropDownActive')]");
  private Element readCheckBox = Element.byXpath("//label[@for='readMessage']/span");
  private Element messageHeader = Element.byXpath("//div[contains(@ng-class,'Subject')]");
  private Element senderHeader = Element.byXpath("//div[contains(@ng-class,'Sender')]");
  private Element dateHeader = Element.byXpath("//div[contains(@ng-class,'Created')]");
  private Element unreadFilterCheckbox = Element.byXpath("//label[@for='unreadMessage']");
  private Element readFilterCheckbox = Element.byXpath("//label[@for='readMessage']");
  private Element allFilterCheckbox = Element.byXpath("//label[@for='allMessage']");
  private Element notificationItemCheckbox = Element
      .byXpath("//div[@class='notification-table--body']//div[@class='checkbox']//span");
  private Element notificationItemSubject = Element
      .byXpath("//div[@class='notification-table--body']//div[contains(@class,'subject')]");
  private Element notificationItemDeleteButton = Element
      .byXpath("//div[@class='notification-table--body']//div[@class='remove']//span");
  private Element deletionConfirmationPopup = Element.byClassName("modal-content");
  private Element okButtonInDeletionConfirmationPopup = Element.byClassName("message-btn-ok");
  private Element unreadNotificationItem = Element.byXpath(
      "//div[@class='notification-table--body']/div[contains(@class,'item')]/self::div[contains(@class,'unread')]");
  private Element readNotificationItem = Element.byXpath(
      "//div[@class='notification-table--body']/div[contains(@class,'item')]/self::div[not(contains(@class,'unread'))]");
  private Element currentPagePaginationButton = Element.byCss("li.pagination-page.active>a");
  private Element nextPaginationButton = Element.byCss("li.pagination-next>a");
  private Element totalItemsText = Element
      .byXpath("//div[contains(@class,'notifications-select--wrapper')]");
  private Element checkedNotificationItem = Element
      .byXpath("//div[contains(@class,'item')]/div[@class='checkbox']//input[@checked]");
  private final Element closeTimezonePopupButton = Element.byXpath(
      "//*[contains(@class,'timezone-modal__close')]");
  private final Element eventsNavigationLink = Element.byXpath(
      String.format(MENU_PATTERN, "event"));

  @Override
  public boolean isScreenLoaded() {
    return isSearchFieldDisplayed();
  }

  public NotificationScreen waitScreenLoading() {
    searchField.waitForVisibility();
    return this;
  }

  public boolean isMessageHeaderDisplayed() {
    return messageHeader.isDisplayed();
  }

  public boolean isSenderHeaderDisplayed() {
    return senderHeader.isDisplayed();
  }

  public boolean isDateHeaderDisplayed() {
    return dateHeader.isDisplayed();
  }

  public boolean isMarkAsUnreadButtonDisplayed() {
    return markAsUnreadButton.isDisplayed();
  }

  public boolean isMarkAsReadButtonDisplayed() {
    return markAsReadButton.isDisplayed();
  }

  public boolean isDeleteButtonDisplayed() {
    return deleteButton.isDisplayed();
  }

  public boolean isUncheckButtonDisplayed() {
    return uncheckButton.isDisplayed();
  }

  public boolean isUnreadFilterLabelDisplayed() {
    return unreadFilterCheckbox.isDisplayed();
  }

  public boolean isReadFilterLabelDisplayed() {
    return readFilterCheckbox.isDisplayed();
  }

  public boolean isAllFilterLabelDisplayed() {
    return allFilterCheckbox.isDisplayed();
  }

  public boolean isSearchFieldDisplayed() {
    return searchField.isDisplayed();
  }

  public String getActionsWithCheckedDropDownText() {
    return actionsWithCheckedDropDown.getText();
  }

  public String getCurrentPageNumber() {
    return currentPagePaginationButton.getText();
  }

  public int getNotificationItemsCount() {
    return notificationItemCheckbox.getElements().size();
  }

  public String getTotalItemsText() {
    return totalItemsText.getText();
  }

  public int getTotalNotificationsCount() {
    return Integer
        .parseInt(StringUtils
            .getStringValueByRegex(TOTAL_NOTIFICATIONS_REGEX, getTotalItemsText()));
  }

  public NotificationScreen clickOnMessageCheckbox() {
    selectAllCheckBox.click();
    return this;
  }

  public NotificationScreen clickActionsWithCheckedDropDown() {
    actionsWithCheckedDropDown.mouseOverAndClick();
    return this;
  }

  public NotificationScreen clickMarkAsUnreadButton() {
    markAsUnreadButton.click();
    return this;
  }

  public NotificationScreen clickMarkAsReadButton() {
    markAsReadButton.click();
    return this;
  }

  public NotificationScreen clickDeleteButton() {
    deleteButton.click();
    return this;
  }

  public NotificationScreen clickUncheckButton() {
    uncheckButton.click();
    return this;
  }

  public NotificationScreen clickOnReadCheckBox() {
    readCheckBox.click();
    return this;
  }

  public NotificationScreen waitActionsWithCheckedDDLLoading() {
    actionsWithCheckedDDL.waitForVisibility();
    return this;
  }

  public NotificationScreen waitMessageToBeChecked() {
    selectAllCheckBoxWithCheckedAttribute.waitUntilAttributeGetsValue("checked", "true");
    return this;
  }

  public NotificationScreen clickNotificationDeleteButtonByIndex(int notificationIndex) {
    notificationItemDeleteButton.getElements().get(notificationIndex).clickJs();
    return this;
  }

  public NotificationScreen clickNotificationCheckboxByIndex(int notificationIndex) {
    notificationItemCheckbox.getElements().get(notificationIndex).click();
    return this;
  }

  public NotificationPopUpScreen clickNotificationItemByName(String name) {
    List<String> notificationItems = getNotificationItemsSubjectText();
    notificationItemSubject.getElements().get(notificationItems.indexOf(name)).click();
    return new NotificationPopUpScreen();
  }

  public boolean isNotificationCheckboxSelectedByIndex(int notificationIndex) {
    return Element
        .byXpath(IS_NOTIFICATION_ITEM_CHECKBOX_SELECTED_LOCATOR_PATTERN, notificationIndex)
        .isPresent();
  }

  public boolean isNotificationUnreadByIndex(int notificationIndex) {
    return Element
        .byXpath(IS_NOTIFICATION_ITEM_UNREAD_LOCATOR_PATTERN, notificationIndex)
        .isPresent();
  }

  public boolean isNotificationReadByIndex(int notificationIndex) {
    return Element
        .byXpath(IS_NOTIFICATION_ITEM_READ_LOCATOR_PATTERN, notificationIndex)
        .isPresent();
  }

  public boolean isUnreadNotificationItemDisplayed() {
    return unreadNotificationItem.isDisplayed();
  }

  public boolean isReadNotificationItemDisplayed() {
    return readNotificationItem.isDisplayed();
  }

  public NotificationScreen clickNextPaginationButton() {
    nextPaginationButton.click();
    return this;
  }

  public NotificationScreen waitForSearchResultsDisplayed(int notificationsPresent) {
    notificationItemSubject.waitNumberOfElementsToBeLessThan(notificationsPresent);
    return this;
  }

  public NotificationScreen clickUnreadFilterCheckbox() {
    unreadFilterCheckbox.scrollAndClick();
    return this;
  }

  public NotificationScreen clickReadFilterCheckbox() {
    readFilterCheckbox.scrollAndClick();
    return this;
  }

  public NotificationScreen clickAllFilterCheckbox() {
    allFilterCheckbox.scrollAndClick();
    return this;
  }

  public NotificationScreen typeSearchText(String inputPhrase) {
    searchField.type(inputPhrase);
    return this;
  }

  public NotificationScreen clickFindButton() {
    findButton.click();
    return this;
  }

  public NotificationPopUpScreen clickFirstNotification() {
    notificationItemSubject.click();
    return new NotificationPopUpScreen();
  }

  public List<String> getNotificationItemsSubjectText() {
    return notificationItemSubject.getElementsText();
  }

  public NotificationScreen waitForActionsToBeApplied() {
    checkedNotificationItem.waitForInvisibility();
    return this;
  }

  public NotificationScreen waitForUnreadNotificationAbsence() {
    unreadNotificationItem.waitForAbsence();
    return this;
  }

  public NotificationScreen waitForUnreadNotificationPresence() {
    unreadNotificationItem.waitForPresence();
    return this;
  }

  public NotificationScreen waitForReadNotificationAbsence() {
    readNotificationItem.waitForAbsence();
    return this;
  }

  public NotificationScreen waitForReadNotificationPresence() {
    readNotificationItem.waitForPresence();
    return this;
  }

  public NotificationScreen waitForDeletionConfirmationPopUpVisibility() {
    deletionConfirmationPopup.waitForVisibility();
    return this;
  }

  public NotificationScreen clickOkInDeletionConfirmationPopUp() {
    okButtonInDeletionConfirmationPopup.click();
    return this;
  }

  public NotificationScreen waitForUnreadNotificationByNameDisplayed(String name) {
    Element.byXpath(UNREAD_NOTIFICATION_LOCATOR_PATTERN, name).waitForVisibility();
    return this;
  }

  public boolean isNotificationDisplayedByName(String name) {
    return Element.byXpath(UNREAD_NOTIFICATION_LOCATOR_PATTERN, name).isDisplayed();
  }

  public NotificationScreen markAllNotificationAsRead() {
    if (isUnreadNotificationItemDisplayed()) {
      clickOnMessageCheckbox();
      waitMessageToBeChecked();
      clickActionsWithCheckedDropDown();
      waitActionsWithCheckedDDLLoading();
      clickMarkAsReadButton();
      waitForUnreadNotificationAbsence();
    }
    return this;
  }

  public void waitRefreshScreen() {
    notificationItemSubject.waitForVisibilityAfterScreenRefresh();
  }

  public NotificationScreen closeTimezonePopupWindowIfAppears() {
    if (closeTimezonePopupButton.isDisplayedShortTimeOut()) {
      closeTimezonePopupButton.click();
    }
    return this;
  }

  public EventsScreen clickEventsNavigationLink() {
    eventsNavigationLink.click();
    return new EventsScreen();
  }
}