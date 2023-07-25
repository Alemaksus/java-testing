package com.epmrdpt.smoke.hometraining;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34270_VerifyThatSubscribeForTrainingIsDisplayedOnHomePage",
    groups = {"full", "smoke", "general", "deprecated"})
public class EPMRDPT_34270_VerifyThatSubscribeForTrainingIsDisplayedOnHomePage {

  private final String skillName = "Automated";

  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingBlockOnStartScreenLoading() {
    assertTrue(trainingBlockScreen.isScreenLoaded(),
        "Training block on start screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void openTrainingDropDownAndCheckText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen.isFiltersDropDownDisplayed(),
        "Search DropDown is not displayed!");
    trainingBlockScreen.openFiltersDropDownAndClickSkillsFiltersTab();
    new TrainingCardsSectionService()
        .clickSkillFromDDLByText(skillName)
        .waitTrainingIconsAreDisplayedBySkillName(skillName);
    softAssert
        .assertTrue(trainingBlockScreen.isTrainingIconsAreDisplayedBySkillName(skillName)
            , "Selected trainings aren't displayed!");
    softAssert
        .assertTrue(trainingBlockScreen
                .hoverOverSubscribeButton()
                .isSubscribeButtonDisplayed()
            , "Subscribe button isn't displayed!");
    softAssert.assertAll();
  }
}
