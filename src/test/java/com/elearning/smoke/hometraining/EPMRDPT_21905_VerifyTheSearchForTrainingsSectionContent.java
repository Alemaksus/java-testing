package com.epmrdpt.smoke.hometraining;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_21905_VerifyTheSearchForTrainingsSectionContent",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_21905_VerifyTheSearchForTrainingsSectionContent {

  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingBlockOnStartScreenLoading() {
    assertTrue(trainingBlockScreen.isScreenLoaded(),
        "Training block on start screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkSearchDropdownFieldDisplayed() {
    assertTrue(trainingBlockScreen.isFiltersDropDownDisplayed(),
        "Search dropdown field not found!");
  }

  @Test(priority = 2)
  public void checkArrowButtonDisplayed() {
    assertTrue(trainingBlockScreen.isArrowButtonDisplayed(), "Arrow button not found!");
  }
}
