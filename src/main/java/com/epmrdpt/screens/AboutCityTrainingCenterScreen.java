package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class AboutCityTrainingCenterScreen extends AbstractScreen {

  private final Element cityTrainingCenterTitle = Element.byCss("div.center-title.ng-binding");

  @Override
  public boolean isScreenLoaded() {
    return cityTrainingCenterTitle.isDisplayed();
  }

  public boolean isCityTrainingCenterTitleDisplayed(String cityTrainingTitle) {
    return cityTrainingTitle.equals(cityTrainingCenterTitle.getText());
  }
}
