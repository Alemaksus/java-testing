package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22440_VerifyThatUserCanAddLocationThroughSearchForTrainingsFilterToggle",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22440_VerifyThatUserCanAddLocationThroughSearchForTrainingsFilterToggle {

  private TrainingListScreen trainingListScreen;
  private final String locationGlobal = "Global";
  private final String multiLocation = "Multi-location";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    trainingListScreen =
        new HeaderScreen()
            .waitLinksToRedirectOnOtherSectionsDisplayed()
            .clickTrainingListNavigationLink();
  }

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(), "Training List screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkUserCanAddLocationThroughSearchForTrainingsFilterToggle() {
    TrainingBlockScreen trainingBlockScreen = new TrainingBlockScreen();
    String expectedLocation = new TrainingCardsSectionService().getLocationWithActiveTrainings();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen.isTrainingCardsDisplayed(),
        "Active Trainings not found!");
    for (String actualLocation : trainingBlockScreen.getTrainingCardsLocationsText()) {
      softAssert.assertTrue(actualLocation.contains(expectedLocation)
              || actualLocation.equals(locationGlobal)
              || actualLocation.equals(multiLocation),
          "Not all Training card's expected location match with the actual location!");
    }
    softAssert.assertAll();
  }
}
