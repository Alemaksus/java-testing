package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.stream.Collectors;
import javax.swing.text.html.CSS.Attribute;

public class TrainingProgramScreen extends AbstractScreen {

  public static final String TRAINING_LOGO_URL = "/Content/themes/Redesign/ico/program-logo.svg";

  private Element trainingLogo = Element.byXpath("//div[@class='training-logo__image']/div");
  private Element trainingProgramTitle = Element
      .byXpath("//div[@class='training-logo__header']/h6");
  private Element trainingTitle = Element.byCss("div.modal-body__heading");
  private Element descriptionSection = Element.byXpath("//article/p");
  private Element crossButton = Element.byCss("button.cross");
  private Element modalWindow = Element.byId("trainingProgramModal");

  @Override
  public boolean isScreenLoaded() {
    return modalWindow.isDisplayed();
  }

  public boolean isCrossDisplayed() {
    return crossButton.isDisplayed();
  }

  public String getFullDescriptionText() {
    return descriptionSection.getElementsText().stream().collect(Collectors.joining());
  }

  public String getTrainingTitleText() {
    return trainingTitle.getText();
  }

  public boolean isTrainingLogoDisplayed() {
    return trainingLogo.getCssValue(Attribute.BACKGROUND.toString())
        .contains(TRAINING_LOGO_URL);
  }

  public boolean isTrainingProgramTitleDisplayed() {
    return trainingProgramTitle.isDisplayed();
  }

  public String getTrainingProgramTitleText() {
    return trainingProgramTitle.getText();
  }

  public TrainingProgramScreen waitTrainingProgramPopUpDisplayed() {
    modalWindow.waitForVisibility();
    return this;
  }
}
