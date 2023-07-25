package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_ARMENIA_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_BELARUS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_KAZAKHSTAN_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_UKRAINE_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_UZBEKISTAN_TAB;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.CountryContainerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14527_VerifyThatUserCanSelectCountryForSearchThroughSearchForTrainingsFilterToggle",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14527_VerifyThatUserCanSelectCountryForSearchThroughSearchForTrainingsFilterToggle {

  private List<String> countryList = new ArrayList<>(
      Arrays.asList(
          getValueOf(ABOUT_MAP_SECTION_ARMENIA_TAB),
          getValueOf(ABOUT_MAP_SECTION_BELARUS_TAB),
          getValueOf(ABOUT_MAP_SECTION_KAZAKHSTAN_TAB),
          getValueOf(ABOUT_MAP_SECTION_UKRAINE_TAB),
          getValueOf(ABOUT_MAP_SECTION_UZBEKISTAN_TAB)));
  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .isScreenLoaded(), "Training List screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkUserCanSelectCountryForSearchThroughSearchForTrainingsFilterToggle() {
    trainingBlockScreen.clickSearchDropDown().waitDropDownVisibility();
    assertTrue(trainingBlockScreen.getDropDownCountryCount() != 0,
        "Countries in drop down not found!");
  }

  @Test(priority = 3)
  public void checkUserCanSelectCityForSearchThroughSearchForTrainingsFilterToggle() {
    CountryContainerService countryContainerService = new CountryContainerService();
    SoftAssert softAssert = new SoftAssert();
    for (String selectedCountry : countryList) {
      trainingBlockScreen.clickCountryByNameFromDropDown(selectedCountry);
      List<String> cityNamesList = trainingBlockScreen.getDropDownCitiesText();
      softAssert.assertFalse(
          cityNamesList.isEmpty(),
          String.format("The list of city names of the %s is empty!", selectedCountry));
      for (String cityName : cityNamesList) {
        softAssert.assertTrue(
            countryContainerService
                .areCheckingCityBelongsChosenCountry(selectedCountry, cityName),
            String.format(
                "The '%s' doesn't belong to the selected country %s!", cityName,
                selectedCountry));
      }
    }
    softAssert.assertAll();
  }
}
