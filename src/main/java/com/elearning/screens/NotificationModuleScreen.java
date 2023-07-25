package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;

public class NotificationModuleScreen extends AbstractScreen {

  private static final String NOTIFICATION_ITEM_PATTERN = "//li[contains(@class, 'notification-list')][%d]";

  private Element bellButton = Element
      .byXpath("//div[contains(@class,'dropdown notifications')]");
  private Element markAllAsReadButton = Element.byId("unreadAll");
  private Element preloadingPicture = Element.byCss("ul li[style]");
  private Element notificationItem = Element.byXpath("//a[contains(@class,'js-message')]");
  private Element notificationItemDescription = Element.byXpath(".//a[@title]");
  private Element notificationItemDate = Element.byXpath(".//span[@class='preview-date']");
  private Element notificationItemSubject = Element.byXpath(".//span[@class='preview-subject']");
  private Element allNotificationsButton = Element
      .byXpath("//a[@href='/Inbox#!/Notifications']//parent::li");
  private Element notificationsDropDown = Element
      .byXpath("//a[@href='/Inbox#!/Notifications']//ancestor::ul");
  private Element allNotificationsHeader = Element.byXpath("//a[@href='/Inbox#!/Notifications']");
  private Element notificationLink = Element.byXpath("//li[@role='menuitem'][1]");

  @Override
  public boolean isScreenLoaded() {
    return allNotificationsButton.isDisplayed();
  }

  public NotificationModuleScreen waitBellOpening() {
    bellButton.waitNeedValueAttributeContaining("class", "open");
    return this;
  }

  public NotificationModuleScreen waitForVisibilityAndClickNotificationLink() {
    notificationLink.waitForVisibility().click();
    return this;
  }

  public NotificationScreen clickAllNotificationsButton() {
    allNotificationsButton.click();
    return new NotificationScreen();
  }

  public NotificationModuleScreen clickMarkAllAsReadButton() {
    if (markAllAsReadButton.isDisplayed()) {
      markAllAsReadButton.click();
    }
    return this;
  }

  public NotificationModuleScreen waitPreloadingPictureHidden() {
    preloadingPicture.waitUntilAttributeGetsValue(Attribute.DISPLAY.toString(), "none");
    return this;
  }

  public boolean isMarkAllAsReadButtonDisplayed() {
    return markAllAsReadButton.isDisplayed();
  }

  private List<Element> getAllNotificationItems() {
    return notificationItem.getElements();
  }

  public List<String> getAllNotificationText() {
    return notificationItem.getElementsText();
  }

  public boolean isNotificationDisplayed(int index) {
    return Element.byXpath(NOTIFICATION_ITEM_PATTERN, index).isDisplayed();
  }

  public int getNotificationsCount() {
    return getAllNotificationItems().size();
  }

  public boolean isNotificationHasNotificationSubjectByIndex(int notificationIndex) {
    return getAllNotificationItems()
        .get(notificationIndex)
        .findChild(notificationItemDescription)
        .getText()
        .length() > 0;
  }

  public String getNotificationDateByIndex(int notificationIndex) {
    return getAllNotificationItems()
        .get(notificationIndex)
        .findChild(notificationItemDate)
        .getText();
  }

  public String getNotificationSubjectByIndex(int notificationIndex) {
    return getAllNotificationItems()
        .get(notificationIndex)
        .getText();
  }

  public boolean isDropDownHasScrollbar() {
    return notificationsDropDown.isHasScrollbar();
  }

  public boolean isDropDownWithNotificationsDisplayed() {
    return notificationsDropDown.isDisplayed();
  }

  public NotificationModuleScreen waitNotificationDropDownVisibility() {
    notificationsDropDown.waitForVisibility();
    return this;
  }

  public NotificationPopUpScreen clickNotificationByIndex(int notificationIndex) {
    getAllNotificationItems()
        .get(notificationIndex)
        .click();
    return new NotificationPopUpScreen();
  }

  public NotificationPopUpScreen clickNotificationByName(String notificationName) {
    List<String> notificationItems = getAllNotificationText();
    notificationItem.getElements().get(notificationItems.indexOf(notificationName)).click();
    return new NotificationPopUpScreen();
  }

  public String getAllNotificationsHeaderText() {
    return allNotificationsHeader.getText();
  }

  public boolean isNotificationsByIndexDisplayed(int index) {
    return getAllNotificationItems().get(index).isDisplayed();
  }

  public NotificationModuleScreen waitNotificationItemsVisibility() {
    notificationItem.waitForVisibility();
    return this;
  }

  public boolean isNotificationItemDisplayed() {
    return notificationItem.isDisplayedShortTimeOut();
  }

  public boolean isAllNotificationsLinkClickable() {
    return allNotificationsHeader.isClickable();
  }

  public boolean isMarkAllAsReadButtonClickable() {
    return markAllAsReadButton.isClickable();
  }
}
