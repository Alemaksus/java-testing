package com.epmrdpt.smoke.hometraining;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13178_VerifyTrainingDescriptionPageIsOpenedAfterClickingOnTrainingCard",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_13178_VerifyTrainingDescriptionPageIsOpenedAfterClickingOnTrainingCard {

  private String trainingCardTitle;
  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingCardsLoading() {
    assertTrue(
        trainingBlockScreen.isAllTitlesOfTrainingCardsDisplayed(),
        "Training cards on Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainingDescriptionScreenLoading() {
    int cardIndex = new Random().nextInt(trainingBlockScreen.getTrainingTittlesCount());
    trainingCardTitle = trainingBlockScreen.getTrainingCardFullTitleByIndexText(cardIndex);
    assertTrue(trainingBlockScreen.openTrainingCardByIndex(cardIndex)
        .isScreenLoaded(), "Training Description page isn't loaded!");
  }

  @Test(priority = 3)
  public void checkCorrectTrainingDescriptionPageOpensAfterClickingOnLink() {
    String trainingDescriptionTitle =
        new TrainingDescriptionScreen()
            .getTrainingDescriptionTitleText();
    assertThat(
        "The title of the Training Description page doesn't match the name of the opened link",
        trainingDescriptionTitle,
        equalToIgnoringCase(trainingCardTitle));
  }
}
