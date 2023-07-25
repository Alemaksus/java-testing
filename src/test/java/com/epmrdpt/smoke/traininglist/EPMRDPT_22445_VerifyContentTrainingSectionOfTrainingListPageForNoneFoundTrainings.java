package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22445_VerifyContentTrainingSectionOfTrainingListPageForNoneFoundTrainings",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22445_VerifyContentTrainingSectionOfTrainingListPageForNoneFoundTrainings {

  private TrainingListScreen trainingListScreen;
  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    trainingBlockScreen = new TrainingBlockScreen();
    trainingListScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink();
  }

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(), "Training List screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkSearchForTrainingsDropDownDisplayed() {
    softAssert = new SoftAssert();
    for (int tryOpenCounter = 0; tryOpenCounter < 3; tryOpenCounter++) {
      trainingBlockScreen.clickSearchDropDown();
      if (trainingBlockScreen.isDropDownCountryDisplayed()) {
        break;
      }
    }
    softAssert.assertTrue(
        trainingBlockScreen.isDropDownCountryDisplayed(),
        "Drop Down 'Search for Trainings' isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkContentTrainingSectionForNoneFoundTrainingsBySkillAndLocation() {
    softAssert = new SoftAssert();
    trainingBlockScreen = new TrainingCardsSectionService()
        .searchForNoAvailableTrainingsByCountryAndSkill();
    softAssert.assertTrue(trainingBlockScreen.isSubscribeButtonDisplayed(),
        "Subscribe Button isn't displayed!");
    softAssert.assertEquals(
        trainingBlockScreen.getSubscribeButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_SUBSCRIBE_BUTTON),
        "Subscribe button has incorrect text!");
    softAssert.assertEquals(
        trainingBlockScreen.getSubscribeOnTrainingUpdatesText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_NO_AVAILABLE_TRAININGS_TEXT),
        "'No Available Trainings' text is incorrect!");
    softAssert.assertAll();
  }
}
