package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.element.Element;

public class SurveyQuestionaryPopUpScreen extends SurveyPopUpScreen {

  private Element surveyPopUp = Element.byXpath("//div[@aria-describedby='surveyAnswersDialog']");
  private Element answer = Element.byXpath("//li[@class='answer-text']/span");

  @Override
  public boolean isScreenLoaded() {
    return isSurveyPopUpDisplayed();
  }

  public boolean isSurveyPopUpDisplayed() {
    return surveyPopUp.isDisplayed();
  }

  public String getAnswerText() {
    return answer.getText();
  }
}
