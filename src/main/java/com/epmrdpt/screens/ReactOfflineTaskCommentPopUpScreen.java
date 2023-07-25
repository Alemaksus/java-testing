package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_OFFLINE_TASK_COMMENT_POP_UP_DELETE_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactOfflineTaskCommentPopUpScreen extends AbstractScreen {

  private static final String POP_UP_LOCATOR = "//div[@data-id='comment-item']";

  private Element popUpWindow = Element.byXpath(POP_UP_LOCATOR);
  private Element commentsFromPopUpWindow = Element.byXpath(POP_UP_LOCATOR + "//span");
  private Element deleteCommentButton = Element.byXpath(
      "(//div[@data-id='comment-item']//*[name()='svg'])[2]");
  private Element confirmationDeleteButton = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_TRAINING_OFFLINE_TASK_COMMENT_POP_UP_DELETE_BUTTON));

  public boolean isPopUpWindowDisplayed() {
    return popUpWindow.isDisplayedShortTimeOut();
  }

  public boolean isCommentDisplayed(String comment) {
    return getAllCommentsFromPopUp().contains(comment);
  }

  public ReactOfflineTaskCommentPopUpScreen waitCommentsVisible() {
    commentsFromPopUpWindow.waitForVisibility();
    return this;
  }

  public List<String> getAllCommentsFromPopUp() {
    return commentsFromPopUpWindow.getElementsText();
  }

  public ReactOfflineTaskCommentPopUpScreen moveTaskCommentPopUpToTheTopOfTheScreen() {
    deleteCommentButton.mouseDown();
    return this;
  }

  public ReactTasksJournalScreen deleteComment() {
    deleteCommentButton.click();
    confirmationDeleteButton.waitForVisibility();
    confirmationDeleteButton.click();
    return new ReactTasksJournalScreen();
  }

  @Override
  public boolean isScreenLoaded() {
    return isPopUpWindowDisplayed();
  }
}

