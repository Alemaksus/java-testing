package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import javax.swing.text.html.CSS;

public class ReactTaskMarkPopUpScreen extends AbstractScreen {

  public static final String COLOR_CHOSEN_BUTTON_ACTIVE = "rgba(0, 138, 189, 1)";
  public static final String COLOR_SAVE_BUTTON_ACTIVE = "rgba(103, 163, 0, 1)";
  private static final String MODAL_BODY_PATTERN = "//div[@class='journal-evaluation-modal-body']";
  private static final String MARK_BUTTON_BY_VALUE_PATTERN =
      "//div[contains(@class,'uui-caption')][text()='%s']/..";

  private Element offlineTaskMarkPopUp = Element.byCss("div.uui-modal-window");
  private Element saveButton = Element.byXpath(MODAL_BODY_PATTERN +
      "/div/div[4]/div[2]");

  @Override
  public boolean isScreenLoaded() {
    return offlineTaskMarkPopUp.isDisplayed();
  }

  public ReactGroupJournalScreen clickSaveButton() {
    saveButton.click();
    return new ReactGroupJournalScreen();
  }

  public ReactTaskMarkPopUpScreen clickMarkButtonByValue(int mark) {
    Element.byXpath(MARK_BUTTON_BY_VALUE_PATTERN, mark).click();
    return this;
  }

  public String getBackgroundColorMarkButtonByValue(int value) {
    return Element.byXpath(MARK_BUTTON_BY_VALUE_PATTERN, value)
        .getCssValue(CSS.Attribute.BACKGROUND_COLOR.toString());
  }

  public ReactTaskMarkPopUpScreen waitUntilSaveButtonBecameActive() {
    saveButton.waitUntilAttributeGetsValue(
        CSS.Attribute.BACKGROUND_COLOR.toString(), COLOR_SAVE_BUTTON_ACTIVE);
    return this;
  }
}
