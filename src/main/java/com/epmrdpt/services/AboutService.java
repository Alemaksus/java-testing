package com.epmrdpt.services;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.TrainingBlockScreen;

public class AboutService {

  private AboutScreen aboutScreen = new AboutScreen();

  public CenterScreen openTrainingCenter(String country, String city) {
    aboutScreen
        .clickTrainingCenterCountryByName(country)
        .waitForTrainingCenterCitiesSectionVisibility()
        .clickTrainingCenterCityByName(city);
    return new CenterScreen();
  }

  public TrainingBlockScreen openSeeAllTrainingsLink() {
    return new CenterScreen().clickSeeAllTrainingLink()
        .waitLocationFilterClearButtonVisibility()
        .waitTrainingCardLocationVisibility();
  }
}
