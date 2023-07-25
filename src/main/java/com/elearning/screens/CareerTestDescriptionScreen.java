package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class CareerTestDescriptionScreen extends AbstractScreen {

  private final Element startTestButton = Element.byXpath("//a[text()='Start test']");

  @Override
  public boolean isScreenLoaded() {
    return startTestButton.isDisplayed();
  }

  public CareerTestQuestionnaireScreen clickStartTestButton() {
    startTestButton.click();
    return new CareerTestQuestionnaireScreen();
  }
}
