package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77723_VerifyAllOfTheTrainingLanguagesAreShownOnTheTrainingDescriptionPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_77723_VerifyAllOfTheTrainingLanguagesAreShownOnTheTrainingDescriptionPage {

  private final String trainingName = "AUTO_Training_For_Finding";

  private TrainingDescriptionScreen trainingDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingDescription() {
    trainingDescriptionScreen = new TrainingCardsSectionService()
        .openTrainingDescription(trainingName);
  }

  @Test(priority = 1)
  public void checkHeaderContainerDisplayed() {
    assertTrue(new HeaderScreen().isHeaderContainerDisplayed(),
        "Header container on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkLanguageInfoSection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(trainingDescriptionScreen.isLanguageInfoSectionDisplayed(),
        "Language Info Section on Training description page isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.isEnglishLanguageInInfoSectionDisplayed(),
        "English language on Language Info Section isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.isRussianLanguageInInfoSectionDisplayed(),
        "Russian language on Language Info Section isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.isUkrainianLanguageInInfoSectionDisplayed(),
        "Ukrainian language on Language Info Section isn't displayed!");
    softAssert.assertAll();
  }
}
