package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.CountryContainerService;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13218_VerifyUserCanSelectLocationsInSearchFiltersOnTrainingListPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13218_VerifyUserCanSelectLocationsInSearchFiltersOnTrainingListPage {

  private static final String GLOBAL_LOCATION = "Global";
  private final String multiLocation = "Multi-location";
  private String countryToVerifyTrainingsFilter;
  private List<String> citiesToVerifyTrainingsFilter;

  private TrainingListScreen trainingListScreen;
  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    trainingListScreen = new HeaderScreen().clickTrainingListNavigationLink();
    trainingBlockScreen = new TrainingBlockScreen();
    String[] countryAndItsCities = LocaleProperties
        .getValueOf(LocalePropertyConst.KAZAKHSTAN_CONTAINER).split(" ");
    countryToVerifyTrainingsFilter = countryAndItsCities[0];
    citiesToVerifyTrainingsFilter = Arrays
        .asList(Arrays.copyOfRange(countryAndItsCities, 1, countryAndItsCities.length));
  }

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(),
        "Training List screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkFilterDropDownIsDisplayedAndOpened() {
    softAssert = new SoftAssert();
    trainingBlockScreen.waitTrainingCardsVisibility()
        .openFiltersDropDownAndClickLocationsFiltersTab();
    softAssert.assertTrue(trainingBlockScreen.isFiltersDropDownDisplayed(),
        "Filter DropDown is not displayed!");
    softAssert
        .assertTrue(trainingBlockScreen.isOpenedFilterDropDownDisplayed(),
            "Filter drop down is not opened!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCountrySelectionFromDropDown() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(
        trainingBlockScreen.waitTrainingCardsVisibility()
            .clickCountryByNameFromDropDown(countryToVerifyTrainingsFilter)
            .getDropDownHighlightedCountry(),
        countryToVerifyTrainingsFilter,
        countryToVerifyTrainingsFilter + "is not highlighted!");
    softAssert.assertTrue(trainingBlockScreen.areCitiesAndCheckBoxesPresentForCountry(),
        "Cities and checkboxes are not displayed for country " + countryToVerifyTrainingsFilter
            + "!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTrainingsFilterForACountry() {
    softAssert = new SoftAssert();
    citiesToVerifyTrainingsFilter = trainingBlockScreen.waitTrainingCardsVisibilityShortTimeOut()
        .clickCountryByNameFromDropDown(countryToVerifyTrainingsFilter).getDropDownCitiesText();
    for (int i = 0; i < citiesToVerifyTrainingsFilter.size(); i++) {
      softAssert.assertTrue(
          new CountryContainerService()
              .areCheckingCityBelongsChosenCountry(countryToVerifyTrainingsFilter,
                  citiesToVerifyTrainingsFilter.get(i)),
          String.format(
              "The '%s' isn't belonged to the selected country %s!",
              citiesToVerifyTrainingsFilter.get(i), countryToVerifyTrainingsFilter));
      softAssert.assertTrue(
          trainingBlockScreen.clickCityByIndexFromDropDown(i).clickDropDownArrowButton()
              .isFilterItemDisplayedShortTimeout(citiesToVerifyTrainingsFilter.get(i)),
          "Filter with city name " + citiesToVerifyTrainingsFilter.get(i) + " is applied!");
      verifyTrainingsFilter(citiesToVerifyTrainingsFilter.get(i));
      clearCityFilter(i);
      softAssert.assertTrue(
          trainingBlockScreen
              .isFilterItemDisappearShortTimeout(citiesToVerifyTrainingsFilter.get(i)),
          "Filter with city name " + citiesToVerifyTrainingsFilter.get(i) + " is not removed!");
      trainingBlockScreen.clickDropDownArrowButton();
    }
    softAssert.assertAll();
  }

  private void clearCityFilter(int index) {
    trainingBlockScreen.clickDropDownArrowButton().waitForDropDownCities()
        .clickCityByIndexFromDropDown(index)
        .clickDropDownArrowButton();
  }

  @Test(priority = 5)
  public void checkThaAfterChooseAllCitiesOfCountryAndVerifyAppropriateCardsAreDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen
            .selectAllCitiesInDropDownByCountryNameAndCloseDropDown(countryToVerifyTrainingsFilter)
            .waitForFilterItemDisplayedShortTimeout(countryToVerifyTrainingsFilter)
            .isFilterItemDisplayedShortTimeout(countryToVerifyTrainingsFilter),
        "Filter with country name " + countryToVerifyTrainingsFilter + " is applied!");
    trainingBlockScreen
        .clickDropDownArrowButton()
        .waitAllDropDownCountriesVisibility();
    verifyTrainingsFilter(countryToVerifyTrainingsFilter);
    softAssert.assertTrue(trainingBlockScreen
            .selectAllCitiesInDropDownByCountryNameAndCloseDropDown(countryToVerifyTrainingsFilter)
            .waitForFilterItemDisappearShortTimeout(countryToVerifyTrainingsFilter)
            .isFilterItemDisappearShortTimeout(countryToVerifyTrainingsFilter),
        "Filter with country name " + countryToVerifyTrainingsFilter + " is not removed!");
    softAssert.assertAll();
  }

  private void verifyTrainingsFilter(String locationName) {
    boolean flag = trainingBlockScreen.isAnyTrainingCardDisplayed();
    if (flag) {
      List<String> filteredByLocationTrainingsLocation = trainingBlockScreen
          .waitTrainingCardsVisibility()
          .waitTrainingCardLocationVisibility()
          .getTrainingCardsLocationsText();
      for (String locationText : filteredByLocationTrainingsLocation) {
        softAssert.assertTrue(locationText.contains(locationName)
            || locationText.equals(GLOBAL_LOCATION)
            || locationText.equals(multiLocation),
            String.format("Training filter as %s is displayed incorrectly with location as %s!",
            locationName, locationText));
      }
    } else {
      softAssert.assertFalse(flag);
    }
  }
}
