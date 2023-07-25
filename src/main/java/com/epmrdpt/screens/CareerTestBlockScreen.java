package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class CareerTestBlockScreen extends AbstractScreen {

  private static final String TEXT_CONTENT_ATTRIBUTE = "textContent";
  private final Element careerTestButton = Element.byXpath("//a[text()='Career Test']");
  private final Element careerTestBannerText = Element.byXpath("//p[contains(@class, 'banner-text')]");

  @Override
  public boolean isScreenLoaded() {
    return careerTestButton.isDisplayed();
  }

  public CareerTestDescriptionScreen clickCareerTestButton() {
    careerTestButton.click();
    return new CareerTestDescriptionScreen();
  }

  public boolean isCareerTestButtonDisplayed() {
    return careerTestButton.isDisplayed();
  }

  public String getTextFromCareerTestBanner() {
    return careerTestBannerText.getAttributeValue(TEXT_CONTENT_ATTRIBUTE);
  }
}
