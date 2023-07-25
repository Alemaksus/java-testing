package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_CONFIRMATION_DELETION_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_COMMENT_POPUP_GENERAL_TEXT;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactCommentPopUpScreen extends AbstractScreen {

  private static final String COMMENT_POPUP_LOCATOR = "//div[contains(@style,'transition')]";
  private static final String GENERAL_COMMENT_LOCATOR =
      COMMENT_POPUP_LOCATOR + "//div[contains(text(),'%s')]";
  private static final String GENERAL_COMMENT_TEXT =
      LocaleProperties.getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_COMMENT_POPUP_GENERAL_TEXT);
  private static final String WITH_TEXT_LOCATOR = "//*[text()='%s']";

  private Element commentPopUpScreen =
      Element.byXpath(String.format(GENERAL_COMMENT_LOCATOR, GENERAL_COMMENT_TEXT));
  private Element inputCommentField = Element.byXpath(COMMENT_POPUP_LOCATOR + "//textarea");
  private Element lastCommentElement =
      Element.byXpath(COMMENT_POPUP_LOCATOR + "//div[2]/div/div/span");
  private Element savePopUpButton =
      Element.byXpath(
          "//div[@class='uui-textarea-counter']/parent::div/parent::div/following-sibling::div/div[2]");
  private Element deleteBinButton =
      Element.byXpath(COMMENT_POPUP_LOCATOR + "//div[2]/div[2]//div[contains(@class,'clickable')]");
  private Element popUpCounter = Element.byCss(".uui-textarea-counter");
  private Element deleteSureButton =
      Element.byXpath(
          "//div[contains(@class,'uui-modal-window')]//div[2]/div[contains(@class,'uui-caption')]");
  private Element numberCommentsElement = Element.byXpath("//div[@data-id='comment-item']/div");
  private Element confirmationOfCommentDeletion = Element.byXpath("//div[contains(text(),'%s')]",
      LocaleProperties.getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_CONFIRMATION_DELETION_MESSAGE));

  public ReactCommentPopUpScreen typeComment(String text) {
    inputCommentField.type(text);
    return this;
  }

  public String getCommentFieldText() {
    return inputCommentField.getText();
  }

  public String getCommentPopUpScreenText() {
    return lastCommentElement.getText();
  }

  public int getNumberComments() {
    return numberCommentsElement.getElements().size();
  }

  public ReactGraduateReportScreen clickSavePopUpButton() {
    savePopUpButton.click();
    waitForCommentPopUpScreenInvisibilityShortTimeout();
    return new ReactGraduateReportScreen();
  }

  public ReactCommentPopUpScreen waitForCommentPopUpScreenInvisibilityShortTimeout() {
    commentPopUpScreen.waitForInvisibilityShortTimeout();
    return this;
  }

  public ReactCommentPopUpScreen clickDeleteBinButton() {
    deleteBinButton.click();
    return this;
  }

  public ReactCommentPopUpScreen clickDeleteSureButton() {
    deleteSureButton.click();
    return this;
  }

  public boolean isDeleteSureButtonDisplayed() {
    return deleteSureButton.isDisplayedShortTimeOut();
  }

  public boolean isDeleteBinButtonDisplayed() {
    return deleteBinButton.isDisplayedShortTimeOut();
  }

  public boolean isConfirmationOfCommentDeletionDisplayed() {
    return confirmationOfCommentDeletion.isDisplayedShortTimeOut();
  }

  @Override
  public boolean isScreenLoaded() {
    return isPopUpCounterDisplayed();
  }

  public boolean isPopUpCounterDisplayed() {
    return popUpCounter.isDisplayedShortTimeOut();
  }

  public boolean isCommentDisplayed(String comment) {
    return Element.byXpath(String.format(WITH_TEXT_LOCATOR, comment)).isDisplayed();
  }
}
