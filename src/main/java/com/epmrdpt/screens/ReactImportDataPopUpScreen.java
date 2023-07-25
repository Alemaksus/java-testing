package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactImportDataPopUpScreen extends AbstractScreen {

  private Element popUpWindow = Element.byXpath("//div[contains(@class, 'modal-window')]");
  private Element popUpHeader = Element.byCss(".modal-header");
  private Element inputField = Element.byXpath("//input[@type='file' and @id='fileCreateLesson']");

  @Override
  public boolean isScreenLoaded() {
    return popUpWindow.isDisplayed();
  }

  public ReactImportDataPopUpScreen waitScreenLoading() {
    popUpHeader.waitForVisibility();
    return this;
  }

  public ReactTasksJournalScreen importExcelReport(String filePath) {
    inputField.sendFilePath(filePath);
    return new ReactTasksJournalScreen();
  }
}

