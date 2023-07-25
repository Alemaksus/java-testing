package com.epmrdpt.smoke.hometraining;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_FILTERS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_FILTERS_LOCATIONS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_FILTERS_SKILLS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_FILTERS_TYPES_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_ONLINE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_PAID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_PLANNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_SOON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_TRAININGS_TITLE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.TrainingBlockScreen;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13174_VerifyTheTrainingSectionOfHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13174_VerifyTheTrainingSectionOfHomePage {

  private SoftAssert softAssert;
  private TrainingBlockScreen trainingBlockScreen;
  private List<String> referenceListOfTabNames = new ArrayList<>();

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen();
    referenceListOfTabNames.add(LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_SOON));
    referenceListOfTabNames.add(LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_PLANNED));
    referenceListOfTabNames.add(LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_ONLINE));
    referenceListOfTabNames.add(LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_PAID));
  }

  @Test(priority = 1)
  public void checkTrainingBlockOnStartScreenLoading() {
    assertTrue(trainingBlockScreen.isScreenLoaded(),
        "Training block on start screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainingTittleDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen.isTrainingsTitleDisplayed(),
        "Title not found!");
    softAssert.assertTrue(trainingBlockScreen.getTrainingsTitleText().equalsIgnoreCase(
            LocaleProperties.getValueOf(TRAINING_LIST_TRAININGS_TITLE)),
        "Training title has incorrect text!");
  }

  @Test(priority = 2)
  public void checkQuickFilterTabsHasCorrectText() {
    assertEquals(referenceListOfTabNames, trainingBlockScreen.getQuickFilterTabsText(),
        "Tabs from quick filter haven't got correct text!");
  }

  @Test(priority = 2)
  public void checkFiltersDropdownDisplayed() {
    assertTrue(trainingBlockScreen.isFiltersDropDownDisplayed(),
        "'Filters' dropdown isn't displayed");
  }

  @Test(priority = 2)
  public void checkTextFromFiltersDropDownCorrect() {
    assertTrue(trainingBlockScreen.getTextFromFiltersDropDown().contentEquals(
            LocaleProperties.getValueOf(TRAINING_FILTERS)),
        "'Filters' text isn't displayed in the dropdown!");
  }

  @Test(priority = 3)
  public void checkFiltersLocationsTabHasCorrectText() {
    trainingBlockScreen.openFiltersDropDownAndClickLocationsFiltersTab();
    assertEquals(trainingBlockScreen.getLocationsFiltersTabText(),
        LocaleProperties.getValueOf(TRAINING_FILTERS_LOCATIONS_TAB),
        "'Locations' tab has incorrect text!");
  }

  @Test(priority = 4)
  public void checkChooseAllCitiesCheckBoxDisplayed() {
    softAssert = new SoftAssert();
    int countryCount = trainingBlockScreen.waitDropDownCountryVisibility()
        .getDropDownCountryCount();
    for (int countryIndex = 0; countryIndex < countryCount; countryIndex++) {
      String countryName = trainingBlockScreen.getDropDownCountryByIndexText(countryIndex).trim();
      softAssert.assertTrue(trainingBlockScreen
              .clickCountryByNameFromDropDown(countryName).isChooseAllCitiesCheckBoxDisplayed(),
          "Not all countries has 'Choose all cities' checkbox!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkFiltersSkillsTabHasCorrectText() {
    assertEquals(trainingBlockScreen
            .clickSkillsFiltersTab()
            .getSkillsFilterTabText(),
        LocaleProperties.getValueOf(TRAINING_FILTERS_SKILLS_TAB),
        "'Skills' tab has incorrect text!");
  }

  @Test(priority = 6)
  public void checkSkillsCheckboxesDisplayed() {
    assertTrue(trainingBlockScreen.isAllSkillsCheckBoxesDisplayed(),
        "Skill checkboxes aren't displayed");
  }

  @Test(priority = 7)
  public void checkFiltersTypesTabHasCorrectText() {
    trainingBlockScreen.clickTypesFiltersTab();
    assertEquals(trainingBlockScreen.getTypesFiltersTabText(),
        LocaleProperties.getValueOf(TRAINING_FILTERS_TYPES_TAB),
        "'Types' tab has incorrect text!");
  }

  @Test(priority = 8)
  public void checkTypesCheckboxesDisplayed() {
    assertTrue(trainingBlockScreen.isAllTypesCheckboxesDisplayed(),
        "Types checkboxes aren't displayed");
  }

  @Test(priority = 2)
  public void checkTrainingCardDisplayed() {
    assertTrue(trainingBlockScreen.isAllTrainingsCardsDisplayed(),
        "Training card list not found!");
  }

  @Test(priority = 2)
  public void checkViewMoreTrainingsButtonDisplayed() {
    assertTrue(trainingBlockScreen.isViewMoreTrainingsLinkDisplayed(),
        "View more button not found!");
  }
}
