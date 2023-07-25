package com.epmrdpt.regression.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34315_VerifyThatTheUserCanSelectOneOrManySearchCheckboxesByType",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34315_VerifyThatTheUserCanSelectOneOrManySearchCheckboxesByType {

  private ReactTrainingManagementService reactTrainingManagementService;
  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    trainingBlockScreen = new TrainingBlockScreen()
        .clickDropDownArrowButton()
        .clickTypesFiltersTab();
  }

  @Test(priority = 1)
  public void checkFilterDropDownIsDisplayed() {
    assertTrue(trainingBlockScreen.isOpenedFilterDropDownDisplayed(),
        "Filter dropdown isn't opened!");
  }

  @Test(priority = 2)
  public void checkAllCheckboxesAreDisplayed() {
    assertTrue(trainingBlockScreen.isAllTypesCheckboxesDisplayed(),
        "All checkboxes in dropdown aren't displayed!");
  }

  @Test(priority = 3)
  public void checkUserCanSelectOneSearchCheckboxByType() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementService.selectEnglishCheckboxByType()
        .isSelectedSkillsInDropDownDisplayed(), "Checkbox isn't checked!");
    softAssert.assertEquals(trainingBlockScreen.getSelectedSkillsInDropDownList()
            .stream().map(String::toUpperCase).collect(Collectors.toList()),
        trainingBlockScreen.clickDropDownArrowButton().getFilterTypesText(),
        "Selected type isn't match displayed types!");
    softAssert.assertTrue(trainingBlockScreen.isTrainingCardsDisplayed(),
        "Relevant training isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkUserCanSelectManySearchCheckboxesByType() {
    softAssert = new SoftAssert();
    trainingBlockScreen.clickDropDownArrowButton();
    softAssert.assertTrue(reactTrainingManagementService.selectMoreSearchCheckboxesByType()
        .isSelectedSkillsInDropDownDisplayed(), "Checkboxes aren't checked!");
    softAssert.assertEquals(trainingBlockScreen.getSelectedSkillsInDropDownList()
            .stream().map(String::toUpperCase).sorted().collect(Collectors.toList()),
        trainingBlockScreen.clickDropDownArrowButton().getFilterTypesText()
            .stream().map(String::toUpperCase).sorted().collect(Collectors.toList()),
        "Selected types aren't match displayed types!");
    softAssert.assertTrue(trainingBlockScreen.isTrainingCardsDisplayed(),
        "Relevant training isn't displayed!");
    softAssert.assertAll();
  }
}
