package com.epmrdpt.smoke.hometraining;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22269_VerifyThatUserCanSelectLocationsOnTheTrainingSectionOnHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22269_VerifyThatUserCanSelectLocationsOnTheTrainingSectionOnHomePage {

  private static final String TEST_COUNTRY = "AutoTestCountry";

  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;
  private TrainingCardsSectionService trainingCardsSectionService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    trainingCardsSectionService = new TrainingCardsSectionService();
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingBlockOnStartScreenLoading() {
    assertTrue(trainingBlockScreen.isScreenLoaded(),
        "Training block on start screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkFilterDropDownIsDisplayedAndOpened() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen.isFiltersDropDownDisplayed(),
        "Search DropDown is not displayed!");
    trainingBlockScreen.waitTrainingCardsVisibility()
        .clickSearchDropDown()
        .waitDropDownCountryVisibility();
    softAssert.assertTrue(trainingBlockScreen.isOpenedFilterDropDownDisplayed(),
        "Filter drop down is not opened!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCountrySelectionFromDropDown() {
    trainingBlockScreen.clickCountryByNameFromDropDown(TEST_COUNTRY);
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen.getDropDownHighlightedCountry(), TEST_COUNTRY,
        TEST_COUNTRY + " is not highlighted!");
    softAssert.assertTrue(trainingBlockScreen.areCitiesAndCheckBoxesPresentForCountry(),
        "Cities and checkboxes are not displayed for country " + TEST_COUNTRY);
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTrainingsFilterForACity() {
    trainingBlockScreen
        .clickCityByIndexFromDropDown(0)
        .checkAndClickViewMoreTrainingsLink();
    String selectedCity = trainingBlockScreen.getDropDownCityByIndexText(0);
    assertTrue(trainingBlockScreen
            .isTrainingCardsFilteredByCountryAndCity(TEST_COUNTRY, selectedCity),
        "Filter by city didn't work correct!");
  }

  @Test(priority = 5)
  public void checkThatAfterChooseAllCitiesOfCountryAndVerifyAppropriateCardsAreDisplayed() {
    trainingCardsSectionService.selectAllCitiesCheckboxIfNeeded();
    softAssert = new SoftAssert();
    trainingBlockScreen
        .getDropDownCitiesText()
        .forEach(cityName -> softAssert.assertTrue(trainingBlockScreen
                .isTrainingCardsFilteredByCountryAndCity(TEST_COUNTRY, cityName),
            "Filter with city " + cityName + " didn't work correct!"));
    softAssert.assertAll();
  }
}
