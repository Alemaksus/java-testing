package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_SUBSCRIBE_BUTTON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingListScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_56443_VerifyThatPlannedTrainingCanBeCreated",
    groups = {"full", "react", "regression"})
public class EPMRDPT_56443_VerifyThatPlannedTrainingCanBeCreated {

  private static final String TRAINING_NAME = "AutoTestTraining";
  private TrainingListScreen trainingListScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void openTrainingList() {
    trainingListScreen = new HeaderScreen().clickTrainingListNavigationLink()
        .waitTrainingCardsForVisibility()
        .clickViewMoreTrainings()
        .waitTrainingCardsForVisibility();
  }

  @Test
  public void checkPlannedTrainingIsPresent() {
    assertTrue(trainingListScreen.isTrainingPresentByName(TRAINING_NAME),
        "The training was not present!");
  }

  @Test
  public void checkSubscribeButtonIsPresent() {
    assertEquals(trainingListScreen.getActionOfTrainingByTrainingName(TRAINING_NAME),
        LocaleProperties.getValueOf(TRAINING_LIST_SUBSCRIBE_BUTTON),
        "A subscribe button was not shown!");
  }
}
