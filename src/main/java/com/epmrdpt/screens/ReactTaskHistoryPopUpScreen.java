package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactTaskHistoryPopUpScreen extends AbstractScreen {

  private String studentFullNameLabel = "//span[text()='%s']/../../div[text()='%s']";
  private Element historyLabel = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_TASK_HISTORY_POPUP_SCREEN_HISTORY_LABEL));
  private Element historyCard = Element.byXpath("//div[@class='history-card']");

  @Override
  public boolean isScreenLoaded() {
    return historyLabel.isDisplayed();
  }

  public ReactTaskHistoryPopUpScreen waitScreenLoaded() {
    historyLabel.waitForVisibility();
    return this;
  }

  public boolean isHistoryPopUpOpenedForSelectedStudent(String studentFullName) {
    return Element.byXpath(studentFullNameLabel,
            getValueOf(REACT_TASK_HISTORY_POPUP_SCREEN_STUDENT_FULL_NAME_LABEL), studentFullName)
        .isDisplayed();
  }

  public List<String> getAllHistoryTextFromTaskJournal() {
    return historyCard.getElementsText();
  }
}
