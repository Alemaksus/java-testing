package com.epmrdpt.smoke.hometraining;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_21906_VerifyThatViewMoreTrainingsLinkInTrainingsSectionIsHighlightedWhenHoverOver",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_21906_VerifyThatViewMoreTrainingsLinkInTrainingsSectionIsHighlightedWhenHoverOver {

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
  public void checkChangeColorMoreTrainingLink() {
    String oldColor = trainingBlockScreen.getColorMoreTrainingsLink();
    trainingBlockScreen.hoverOnMoreTrainingLink();
    String newColor = trainingBlockScreen.getColorMoreTrainingsLink();
    assertNotEquals(oldColor, newColor, "Link color wasn't changed after mouse hovering!");
  }
}
