package com.epmrdpt.smoke.hometraining;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22270_VerifyThatUserCanSelectTrainingsOnHomeScreenBySkills",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22270_VerifyThatUserCanSelectTrainingsOnHomeScreenBySkills {

  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkIfDropDownNameIsSearchBySkills() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen.isFiltersDropDownDisplayed(),
        "Search drop down is not displayed!");
    trainingBlockScreen.openFiltersDropDownAndClickSkillsFiltersTab();
    softAssert.assertTrue(trainingBlockScreen.isOpenedFilterDropDownDisplayed(),
        "Filter drop down is not opened!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTrainingCardsWhenSomeNecessarySkillsSelected() {

    softAssert = new SoftAssert();
    softAssert.assertEquals(new ReactTrainingManagementService().selectSomeNecessarySkills()
            .getSelectedSkillsUnderDropDown(),
        trainingBlockScreen.waitForSelectedSkillsInDropDownVisibility()
            .getSelectedSkillsInDropDownList().stream().map(String::toUpperCase)
            .collect(Collectors.toList()), "Selected skills in dropdown and the displayed"
            + " skills on screen do not match when we select some necessary skills!");
    softAssert.assertTrue(
        trainingBlockScreen.isTrainingCardsDisplayed(),
        "Training cards of selected skills are not displayed when we select some necessary skills!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkSkillsInDropDownWhenAllSkillsCheckboxChecked() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen.getSelectedSkillsInDropDownList()
            .stream().map(String::toUpperCase).sorted().collect(Collectors.toList()),
        trainingBlockScreen.getSelectedSkillsUnderDropDown().stream().map(String::toUpperCase)
            .sorted().collect(Collectors.toList()), "Selected skills in dropdown and the"
            + " displayed skills on screen do not match when we select some necessary skills!");
    softAssert.assertTrue(
        trainingBlockScreen.isTrainingCardsDisplayed(),
        "Training cards of selected skills are not displayed when we select all skills!");
    softAssert.assertAll();
  }
}
