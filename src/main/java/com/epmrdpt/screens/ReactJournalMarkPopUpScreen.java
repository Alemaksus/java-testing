package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import javax.swing.text.html.CSS;

public class ReactJournalMarkPopUpScreen extends AbstractScreen {

  public static final String COLOR_CHOSEN_BUTTON_ACTIVE = "rgba(0, 138, 189, 1)";
  public static final String COLOR_CHOSEN_BUTTON_INACTIVE = "rgba(255, 255, 255, 1)";
  public static final String COLOR_SAVE_BUTTON_ACTIVE = "rgba(103, 163, 0, 1)";
  private static final String MODAL_BODY_PATTERN = "//div[@class='journal-evaluation-modal-body']";
  private static final String MARK_BUTTON_BY_VALUE_PATTERN =
      "//div[text()='%s']/ancestor::div[contains(@class,'uui-button-box')]";

  private Element journalMarkPopUp = Element.byCss("div.uui-modal-window");
  private Element markPartPopUp = Element.byXpath("//div[contains(@class, 'uui-modal-window')]/descendant::div[div[contains(@class, 'uui-caption')]]");
  private Element absentButton = Element.byXpath(MODAL_BODY_PATTERN +
      "/div/div[1]/div[2]//div[contains(@class,'uui-button-box')]");
  private Element saveButton = Element.byXpath(MODAL_BODY_PATTERN +
      "/div/div[4]/div[2]");
  private Element commentForStudentTextarea = Element.byCss(
      "div.journal-evaluation-modal-body > div:nth-child(1) > div:nth-child(2) div.uui-input-box textarea");
  private Element commentForTrainersTextarea = Element.byCss(
      "div.journal-evaluation-modal-body > div:nth-child(1) > div:nth-child(3) div.uui-input-box textarea");
  private Element fieldSaveButton = Element.byXpath(
      "//div[@class='journal-evaluation-modal-body']/div/div[4]/div[2]");
  private static final String PAGE_FOR_MARK_STUDENT_FOR_TASK = "//div[contains(@class,'journal-evaluation-modal-body')]";
  private Element scopeInput = Element.byXpath(PAGE_FOR_MARK_STUDENT_FOR_TASK + "//input");
  private Element cleanMarkButton = Element.byXpath(PAGE_FOR_MARK_STUDENT_FOR_TASK
      + "//div[contains(@class,'uui-input-box -clickable')]/div");

  @Override
  public boolean isScreenLoaded() {
    return journalMarkPopUp.isDisplayed();
  }

  public ReactJournalMarkPopUpScreen waitMarkPopUpForVisibility() {
    markPartPopUp.waitForVisibility();
    return this;
  }

  public ReactJournalMarkPopUpScreen clickMarkButtonByValue(int value) {
    Element.byXpath(MARK_BUTTON_BY_VALUE_PATTERN, value).mouseOverAndClick();
    return this;
  }

  public ReactJournalMarkPopUpScreen clickAbsentButton() {
    absentButton.click();
    return this;
  }

  public String getBackgroundColorAbsentButton() {
    return absentButton.getCssValue(CSS.Attribute.BACKGROUND_COLOR.toString());
  }

  public ReactGroupJournalScreen clickSaveButton() {
    saveButton.waitUntilAttributeGetsValue(
        CSS.Attribute.BACKGROUND_COLOR.toString(), COLOR_SAVE_BUTTON_ACTIVE);
    saveButton.click();
    return new ReactGroupJournalScreen();
  }

  public ReactJournalMarkPopUpScreen typeCommentForStudent(String comment) {
    commentForStudentTextarea.type(comment);
    return this;
  }

  public ReactJournalMarkPopUpScreen typeCommentForTrainer(String comment) {
    commentForTrainersTextarea.type(comment);
    return this;
  }

  public String getBackgroundColorMarkButtonByValue(int value) {
    return Element.byXpath(MARK_BUTTON_BY_VALUE_PATTERN, value)
        .getCssValue(CSS.Attribute.BACKGROUND_COLOR.toString());
  }

  public String getCommentForStudentTextareaText() {
    return commentForStudentTextarea.getText();
  }

  public String getCommentForTrainerTextareaText() {
    return commentForTrainersTextarea.getText();
  }

  public String getSaveButtonColor() {
    return fieldSaveButton.getCssValue(CSS.Attribute.BACKGROUND_COLOR.toString());
  }

  public double getCurrentMark() {
    return Double.parseDouble(scopeInput.getAttributeValue("value"));
  }

  public ReactJournalMarkPopUpScreen typeMark(double value) {
    scopeInput.type(String.valueOf(value));
    return this;
  }

  public boolean isDisplayedCleanMarkButton() {
    boolean displayedCleanMarkButton = cleanMarkButton.isDisplayed();
    return displayedCleanMarkButton;
  }

  public ReactJournalMarkPopUpScreen cleanOldMark() {
    cleanMarkButton.click();
    return this;
  }
}

