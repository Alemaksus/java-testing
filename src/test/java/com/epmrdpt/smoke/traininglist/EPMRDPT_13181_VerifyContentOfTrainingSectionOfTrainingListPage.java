package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_ARMENIA_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_FILTERS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_CITIES_CHECKBOX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LOCATIONS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_ONLINE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_PAID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_PLANNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_SOON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_SKILLS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_TYPE_TAB;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingListScreen;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13181_VerifyContentOfTrainingSectionOfTrainingListPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13181_VerifyContentOfTrainingSectionOfTrainingListPage {

  private final int MAX_COUNT_TRAININGS_CARDS_DISPLAYED = 30;
  private String viewMoreTrainingsLinkRegex = LocaleProperties.getValueOf(
      LocalePropertyConst.TRAINING_LIST_VIEW_MORE_TRAININGS_LINK) + " \\(\\d+\\)";
  private List<String> referenceListOfTabNames = new ArrayList<>(Arrays.asList(
      LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_SOON),
      LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_PLANNED),
      LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_ONLINE),
      LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_PAID)));
  private SoftAssert softAssert;
  private TrainingListScreen trainingListScreen;
  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingListScreen =
        new HeaderScreen()
            .waitLinksToRedirectOnOtherSectionsDisplayed()
            .clickTrainingListNavigationLink();
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(), "Training List screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkTrainingsTitleDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        trainingBlockScreen.isTrainingsTitleDisplayed(), "'Trainings' title isn't displayed!");
    softAssert.assertEquals(
        trainingBlockScreen.getTrainingsTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_TRAININGS_TITLE),
        "Trainings title is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkQuickFilterTabsHasCorrectText() {
    assertEquals(referenceListOfTabNames,
        trainingBlockScreen.waitForQuickFilterTab().getQuickFilterTabsText(),
        "Tabs from quick filter haven't got correct text!");
  }

  @Test(priority = 2)
  public void checkFiltersDropdownDisplayed() {
    assertTrue(
        trainingBlockScreen.isFiltersDropDownDisplayed(),
        "'Search for trainings' filter-toggle isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTextFromFiltersDropdown() {
    assertTrue(trainingBlockScreen.getTextFromFiltersDropDown().contentEquals(
            LocaleProperties.getValueOf(TRAINING_FILTERS)),
        "'Filters' text isn't displayed in dropdown!");
  }

  @Test(priority = 3)
  public void checkLocationsSection() {
    trainingBlockScreen.openFiltersDropDownAndClickLocationsFiltersTab();
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen
            .clickCountryByNameFromDropDown(LocaleProperties.getValueOf(ABOUT_MAP_SECTION_ARMENIA_TAB))
            .getChooseAllCitiesCheckBoxText(),
        LocaleProperties.getValueOf(TRAINING_LIST_CITIES_CHECKBOX),
        "'Choose all cities' checkbox! isn't displayed");
    softAssert.assertEquals(trainingBlockScreen
            .getLocationsFiltersTabText(),
        LocaleProperties.getValueOf(TRAINING_LIST_LOCATIONS_TAB),
        "filters dropdown tab 'Locations' has incorrect text");
    softAssert.assertTrue(trainingBlockScreen.isAllCityCheckBoxesForChosenCountryDisplayed(),
        "City checkboxes isn't displayed for chosen country");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkSkillsSection() {
    softAssert = new SoftAssert();
    trainingBlockScreen.clickSkillsFiltersTab();
    softAssert.assertEquals(trainingBlockScreen
            .getSkillsFilterTabText(),
        LocaleProperties.getValueOf(TRAINING_LIST_SKILLS_TAB),
        "Filters dropdown tab 'Skills' has incorrect text!");
    softAssert.assertTrue(trainingBlockScreen.isAllSkillsCheckBoxesDisplayed(),
        "Not all skill checkboxes are displayed");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkTypesSection() {
    softAssert = new SoftAssert();
    trainingBlockScreen.clickTypesFiltersTab();
    softAssert.assertEquals(trainingBlockScreen
            .getTypesFiltersTabText(),
        LocaleProperties.getValueOf(TRAINING_LIST_TYPE_TAB),
        "Filters dropdown tab 'Types' has incorrect text!");
    softAssert.assertTrue(trainingBlockScreen.isAllTypesCheckboxesDisplayed(),
        "Not all type checkboxes displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkViewMoreAndTrainingCardsList() {
    softAssert = new SoftAssert();
    int cardsCount = trainingBlockScreen.getTrainingsCardsCount();
    softAssert.assertTrue(cardsCount != 0 && cardsCount <= MAX_COUNT_TRAININGS_CARDS_DISPLAYED,
        "Training cards doesn't displayed correctly!");
    if (cardsCount == MAX_COUNT_TRAININGS_CARDS_DISPLAYED) {
      String viewMoreTrainingsLinkText = trainingBlockScreen.getViewMoreTrainingsLinkText();
      softAssert.assertTrue(Pattern.compile(viewMoreTrainingsLinkRegex)
              .matcher(viewMoreTrainingsLinkText).find(),
          "'View More Trainings' link is displayed incorrect!");
    }
    softAssert.assertAll();
  }
}
