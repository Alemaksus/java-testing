package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.stream.Collectors;

public class ReactSurveyPopUpScreen extends AbstractScreen {

  private Element surveyPopup = Element.byXpath("//div[contains(@class, 'popover-container')]");
  private Element questionFromModalWindow = Element.byXpath(
      "(//div[contains(@class, 'popover-container')]//div[text()])[2]");
  private Element answerFromModalWindow = Element.byXpath(
      "(//div[contains(@class, 'popover-container')]//div[text()])[3]");

  @Override
  public boolean isScreenLoaded() {
    return isSurveyPopUpDisplayed();
  }

  public boolean isSurveyPopUpDisplayed() {
    return surveyPopup.isDisplayed();
  }

  public String getAnswersFromSurveyPopUpText() {
    return answerFromModalWindow.getElementsText().stream().collect(Collectors.joining());
  }

  public ReactSurveyPopUpScreen waitSurveyPopUpDisplayed() {
    surveyPopup.waitForVisibility();
    return this;
  }

  public String getQuestionFromSurveyPopUpText() {
    return questionFromModalWindow.getText();
  }
}

