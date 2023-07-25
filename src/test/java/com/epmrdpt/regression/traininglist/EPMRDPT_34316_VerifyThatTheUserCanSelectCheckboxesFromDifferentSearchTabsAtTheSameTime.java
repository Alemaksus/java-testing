package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_STATUS_TRAINING_FREE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_TYPE_TRAINING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_PLANNED_LANGUAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LEVEL_PREREQUISITE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingForSearch;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34316_VerifyThatTheUserCanSelectCheckboxesFromDifferentSearchTabsByLocationsBySkillsByTypeAtTheSameTime",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34316_VerifyThatTheUserCanSelectCheckboxesFromDifferentSearchTabsAtTheSameTime {

  private final int firstCheckboxIndex = 0;
  private List<String> selectedCheckboxes = new ArrayList<>();
  private String iconName = "Testing_Icon";
  private String expectedCardLanguage = "RU";

  private ReactTrainingManagementService reactTrainingManagementService;
  private TrainingBlockScreen trainingBlockScreen;
  TrainingForSearch trainingForSearch = new TrainingForSearch("AutoTestCountry", "AutoTestCity",
      getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_TYPE_TRAINING), "Online",
      getValueOf(TRAINING_DESCRIPTION_PLANNED_LANGUAGE), "Automated Testing",
      getValueOf(HEADER_STATUS_TRAINING_FREE),
      getValueOf(TRAINING_LIST_LEARNING_LEVEL_PREREQUISITE));

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingListPage() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    trainingBlockScreen = new TrainingBlockScreen()
        .clickDropDownArrowButton();
  }

  @Test(priority = 1)
  public void checkFilterDropDownDisplayed() {
    assertTrue(trainingBlockScreen.isOpenedFilterDropDownDisplayed(),
        "Filter dropdown isn't opened!");
  }

  @Test(priority = 2)
  public void checkThatUserCanSelectCheckboxOnByLocationsTab() {
    trainingBlockScreen
        .clickLocationFiltersTab()
        .clickCountryByNameFromDropDown(trainingForSearch.getCountryName())
        .clickCityByNameFromDropDown(trainingForSearch.getCityName());
    selectedCheckboxes.add(
        trainingBlockScreen.getSelectedCityInDropDownList().get(firstCheckboxIndex));
    assertTrue(trainingBlockScreen.isCityCheckBoxSelected(trainingForSearch.getCityName()),
        "Checkbox on 'By locations' tab isn't checked!");
  }

  @Test(priority = 3)
  public void checkThatUserCanSelectCheckboxOnBySkillsTab() {
    trainingBlockScreen.clickSkillsFiltersTab()
        .clickSkillCheckboxByName(trainingForSearch.getSkill());
    selectedCheckboxes.add(trainingBlockScreen
        .getSelectedSkillsInDropDownList().get(firstCheckboxIndex));
    assertTrue(trainingBlockScreen.isSelectedSkillsInDropDownDisplayed(),
        "Checkbox on 'By skills' tab isn't checked!");
  }

  @Test(priority = 4)
  public void checkThatUserCanSelectCheckboxOnByTypeTab() {
    trainingBlockScreen.clickTypesFiltersTab();
    reactTrainingManagementService.selectLevelLanguagePriceTypeCheckboxes(trainingForSearch);
    selectedCheckboxes.addAll(trainingBlockScreen.getSelectedTypeInDropDownList());
    assertTrue(trainingBlockScreen.isSelectedTypeInDropDownDisplayed(),
        "Checkbox on 'By type' tab isn't checked!");
  }

  @Test(priority = 5)
  public void checkRelevantTrainingDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    trainingBlockScreen.clickDropDownArrowButton();
    softAssert.assertTrue(selectedCheckboxes.stream().map(String::toUpperCase)
            .collect(Collectors.toList()).containsAll(trainingBlockScreen.getAllSelectedAttributes()),
        "Selected type isn't match displayed types!");
    softAssert.assertTrue(trainingBlockScreen
            .isAppropriateTrainingDisplayed(trainingForSearch.getSkill(), iconName,
                expectedCardLanguage),
        "Relevant training isn't displayed!");
  }
}
