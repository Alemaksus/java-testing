package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13217_VerifyUserCanSelectSkillsInSearchFiltersOnTheTrainingLstPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13217_VerifyUserCanSelectSkillsInSearchFiltersOnTheTrainingLstPage {

  private TrainingListScreen trainingListScreen;
  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void trainingBlockScreenInitialization() {
    trainingListScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink();
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(), "Training List screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkIfFilterDropDownDisplayedAndOpened() {
    softAssert = new SoftAssert();
    trainingBlockScreen.openFiltersDropDownAndClickSkillsFiltersTab();
    softAssert
        .assertTrue(trainingBlockScreen.isFiltersDropDownDisplayed(),
            "Filter drop down is not displayed!");
    softAssert
        .assertTrue(trainingBlockScreen.isOpenedFilterDropDownDisplayed(),
            "Filter drop down is not opened!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTrainingCardsWhenSomeNecessarySkillsSelected() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(new ReactTrainingManagementService().selectSomeNecessarySkills()
            .getSelectedSkillsUnderDropDown(),
        trainingBlockScreen.waitForSelectedSkillsInDropDownVisibility()
            .getSelectedSkillsInDropDownList().stream().map(String::toUpperCase)
            .collect(Collectors.toList()), "Selected skills in dropdown and the displayed"
            + " skills on screen do not match when we select some necessary skills!");
    softAssert.assertTrue(
        trainingBlockScreen.isTrainingCardsDisplayed(), "Training cards of selected"
            + " skills are not displayed when we select some necessary skills!");
    softAssert.assertAll();
  }
}
