package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34714_VerifyThatTheTrainingCardIsClickableOverallAndLeadsToTrainingDescriptionPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34714_VerifyThatTheTrainingCardIsClickableOverallAndLeadsToTrainingDescriptionPage {

  private TrainingCardsSectionService trainingCardsSectionService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void trainingCardsSectionServiceInitialization() {
    trainingCardsSectionService = new TrainingCardsSectionService();
  }

  @Test(priority = 1)
  public void checkTrainingBlockOnStartScreenLoading() {
    assertTrue(new TrainingBlockScreen().isScreenLoaded(),
        "Training block on start screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatTrainingCardFromHomePageLeadsToTrainingDescriptionPage() {
    assertTrue(trainingCardsSectionService.clickAnyTrainingCardOnTrainingBlock()
        .isScreenLoaded(), "Training description screen from Home page doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkThatTrainingCardFromTrainingListLeadsToTrainingDescriptionPage() {
    trainingCardsSectionService.openTrainingListScreen().waitSkillCardsForVisibility();
    assertTrue(trainingCardsSectionService.clickAnyTrainingCardOnTrainingBlock()
        .isScreenLoaded(), "Training description screen from Training list doesn't loaded!");
  }
}
