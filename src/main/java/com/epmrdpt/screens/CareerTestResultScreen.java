package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class CareerTestResultScreen extends AbstractScreen {

  private final Element personalProfileBlock = Element.byXpath(
      "//div[@class='psy-results-your-profile']");
  private final Element compatibleProfessionsListBlock = Element.byXpath(
      "//div[@class='psy-results-compatible-professions-list']");
  private final Element psychologicalPortraitBlock = Element.byXpath(
      "//div[@class='psy-results-psychological-portrait']");
  private final Element psychologicalProfileDescriptionBlock = Element.byXpath(
      "//div[@class='psy-results-psychological-profile-description']");
  private final Element psychologicalReactionsBlock = Element.byXpath(
      "//div[@class='psy-results-psychological-reactions']");
  private final Element copyResultLinkBlock = Element.byXpath(
      "//div[@class='psy-results-encoded-result']");
  private final Element restartTestButton = Element.byXpath("//a[text()='RESTART TEST']");

  @Override
  public boolean isScreenLoaded() {
    return isPersonalProfileBlockDisplayed();
  }

  public boolean isPersonalProfileBlockDisplayed() {
    return personalProfileBlock.isDisplayed();
  }

  public boolean isCompatibleProfessionsBlockDisplayed() {
    return compatibleProfessionsListBlock.isDisplayed();
  }

  public boolean isPsychologicalPortraitBlockDisplayed() {
    return psychologicalPortraitBlock.isDisplayed();
  }

  public boolean isPsychologicalProfileDescriptionBlockDisplayed() {
    return psychologicalProfileDescriptionBlock.isDisplayed();
  }

  public boolean isPsychologicalReactionsBlockDisplayed() {
    return psychologicalReactionsBlock.isDisplayed();
  }

  public boolean isCopyResultLinkBlockDisplayed() {
    return copyResultLinkBlock.isDisplayed();
  }

  public CareerTestQuestionnaireScreen clickRestartTestButton() {
    restartTestButton.click();
    return new CareerTestQuestionnaireScreen();
  }
}
