package com.epmrdpt.regression.home;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77722_VerifyAllOfTheTrainingLanguageAbbreviationsAreShownOnTheCard",
    groups = {"full", "general", "regression"})
public class EPMRDPT_77722_VerifyAllOfTheTrainingLanguageAbbreviationsAreShownOnTheCard {

  private TrainingBlockScreen trainingBlockScreen;
  private final String trainingName = "training Hel Ver ";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void fillArrayListWithObjects() {
    trainingBlockScreen = new TrainingBlockScreen()
        .waitTrainingCardsVisibility();
  }

  @Test(priority = 1)
  public void checkThatTrainingCardDisplayed() {
    trainingBlockScreen.checkAndClickViewMoreTrainingsLink();
    assertTrue(trainingBlockScreen.isTrainingCardByNameDisplayed(trainingName),
        format("Training card by name %s isn't displayed", trainingName));
  }

  @Test(priority = 2)
  public void checkThatAllLanguagesOnCardDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen.isEnglishLanguageOnCardDisplayed(trainingName),
        "English language isn't displayed on card!");
    softAssert.assertTrue(trainingBlockScreen.isRussianLanguageOnCardDisplayed(trainingName),
        "Russian language isn't displayed on card!");
    softAssert.assertTrue(trainingBlockScreen.isUkrainianLanguageOnCardDisplayed(trainingName),
        "Ukrainian language isn't displayed on card!");
    softAssert.assertAll();
  }
}
