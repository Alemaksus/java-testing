package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_BODY_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_GO_TO_GROUPS_TAB_LINK;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactCancelTrainingPopUpScreen extends AbstractScreen {

  private final String POPUP_LOCATOR_PATTERN = "//div[contains(@class, 'uui-modal-window')]";
  private final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";

  private Element crossButton = Element.byXpath(POPUP_LOCATOR_PATTERN + "//*[name()='use']");
  private Element statusChangeTitle = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE));
  private Element okButton = Element
      .byXpath(
          String.format("(%s//div[contains(@class,'uui-caption')])[2]", POPUP_LOCATOR_PATTERN));
  private Element bodyText = Element
      .byXpath(String.format("(%s//div[@display]//div[text()])[1]", POPUP_LOCATOR_PATTERN));
  private Element goToGroupsOrClassesTabLink = Element.byXpath(
      "//div[contains(@class, 'uui-modal-window')]//div[contains(@class,'uui-no-left-icon')]");

  @Override
  public boolean isScreenLoaded() {
    return crossButton.isDisplayed();
  }

  public boolean isStatusChangeTitleDisplayed() {
    return statusChangeTitle.isDisplayed();
  }

  public String getPopUpTitleText() {
    return statusChangeTitle.getText();
  }

  public boolean isCrossButtonDisplayed() {
    return crossButton.isDisplayed();
  }

  public boolean isCrossButtonClickable() {
    return crossButton.isClickable();
  }

  public boolean isBodyTextDisplayed() {
    return bodyText.isDisplayed();
  }

  public String getBodyText() {
    return bodyText.getText();
  }

  public boolean isOkButtonDisplayed() {
    return okButton.isDisplayed();
  }

  public boolean isOkButtonClickable() {
    return okButton.isClickable();
  }

  public boolean isGoToGroupsOrClassesTabLinkDisplayed() {
    return goToGroupsOrClassesTabLink.isDisplayed();
  }

  public boolean isGoToGroupsOrClassesTabLinkClickable() {
    return goToGroupsOrClassesTabLink.isClickable();
  }

  public String getGoToGroupsOrClassesTabLinkText() {
    return goToGroupsOrClassesTabLink.getText();
  }

  public ReactCancelTrainingPopUpScreen waitScreenLoading() {
    statusChangeTitle.waitForVisibility();
    return this;
  }
}
