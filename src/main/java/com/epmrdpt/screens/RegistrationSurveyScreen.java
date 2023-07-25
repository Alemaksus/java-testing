package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class RegistrationSurveyScreen extends AbstractScreen {

  private Element surveyPopup = Element.byClassName("popup-survey");
  private Element surveyPopupTitle = Element.byClassName("popup-survey__title");
  private Element submitButton = Element.byCss("button.popup-subscribe__submit");

  @Override
  public boolean isScreenLoaded() {
    return surveyPopup.isDisplayed();
  }

  public String getSurveyPopUpTitle() {
    return surveyPopupTitle.getText();
  }

  public void clickSubmitButton() {
    submitButton.click();
  }
}
