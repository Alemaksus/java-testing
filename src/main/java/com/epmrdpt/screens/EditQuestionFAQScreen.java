package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.element.Element;

public class EditQuestionFAQScreen extends AddQuestionFAQScreen {

  private Element editTitle = Element.byXpath("//h4[contains(@class,'modal-title')]");

  @Override
  public boolean isScreenLoaded() {
    return editTitle.isDisplayed();
  }
}
