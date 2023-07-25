package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactSelectLanguageScreen extends AbstractScreen {

  private static final String LANGUAGE_LINK_PATTERN
      = "//div[@class='uui-popper']//a[text()='%s']";

  private Element languageChoiceSection = Element.byCss("div.uui-modal-window");

  @Override
  public boolean isScreenLoaded() {
    return languageChoiceSection.isDisplayed();
  }

  public ReactSelectLanguageScreen clickLanguageLink(String language) {
    Element.byXpath(LANGUAGE_LINK_PATTERN, language).click();
    return this;
  }
}
