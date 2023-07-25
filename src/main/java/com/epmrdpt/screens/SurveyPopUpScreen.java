package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.stream.Collectors;

public class SurveyPopUpScreen extends AbstractScreen {

  private static final String DELIMITER = "\\. ";

  private Element surveyPopup = Element.byClassName("modal-content");
  private Element questionFromModalWindow = Element.byXpath(
      "//p[contains(@class,'question-text')]");
  private Element answerFromModalWindow = Element.byXpath("//*[@*='modal-content']//li[@ng-class]");

  @Override
  public boolean isScreenLoaded() {
    return isSurveyPopUpDisplayed();
  }

  public boolean isSurveyPopUpDisplayed() {
    return surveyPopup.isDisplayed();
  }

  public String getQuestionsFromSurveyPopUpText() {
    return questionFromModalWindow.getElementsText()
        .stream()
        .map(questionWithoutNumber -> questionWithoutNumber.split(DELIMITER)[1])
        .collect(Collectors.joining());
  }

  public String getAnswersFromSurveyPopUpText() {
    return answerFromModalWindow.getElementsText().stream().collect(Collectors.joining());
  }

  public SurveyPopUpScreen waitSurveyPopUpDisplayed() {
    surveyPopup.waitForVisibility();
    return this;
  }

}
