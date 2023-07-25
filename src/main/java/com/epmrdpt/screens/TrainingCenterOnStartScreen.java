package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class TrainingCenterOnStartScreen extends AbstractScreen {

  private static final String TRAINING_CENTER_COUNTRY_TAB_PATTERN = "//div[contains(@class,'our-centers')]//span[contains(text(),'%s')]";
  private static final String TRAINING_CENTER_CITY_LINK_PATTERN = "//a[@class='our-centers__item-link']/span[contains(text(),'%s')]";

  private Element trainingCentersSection = Element.byXpath(
      "//div[contains(@class,'our-centers')]/section");

  @Override
  public boolean isScreenLoaded() {
    return isTrainingCentersSectionDisplayed();
  }

  public boolean isTrainingCentersSectionDisplayed() {
    return trainingCentersSection.isDisplayed();
  }

  public TrainingCenterOnStartScreen clickTrainingCenterCountryByName(String country) {
    Element.byXpath(TRAINING_CENTER_COUNTRY_TAB_PATTERN, country)
        .waitForVisibility(LONG_TIME_OUT_IN_SECONDS).click();
    return this;
  }

  public CenterScreen clickTrainingCenterCityByName(String city) {
    Element.byXpath(TRAINING_CENTER_CITY_LINK_PATTERN, city).click();
    return new CenterScreen();
  }
}
