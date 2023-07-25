package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77615_VerifyThatAllFilledInFieldsInTheCustomTextBlockAreDisplayedOnTrainingDetailsPage",
    groups = {"full", "regression"})
public class EPMRDPT_77615_VerifyThatAllFilledInFieldsInTheCustomTextBlockAreDisplayedOnTrainingDetailsPage {

  private final String trainingName = "AutoTestWithCustom";
  private final String customBlockTitle = "BLOCKFORAUTOTEST";
  private final String customBlockInformation = "Information for Auto Test";
  private TrainingDescriptionScreen trainingDescriptionScreen;

  @BeforeClass
  public void loginAndGoToTrainingDetails() {
    new LoginService()
        .loginAndSetLanguage(asTrainingManager());
    trainingDescriptionScreen = new TrainingBlockScreen()
        .waitScreenLoaded()
        .closeTimeZoneMenuIfNeeded()
        .cleanLocationFilterIfNeed()
        .waitLocationFilterClearButtonForDisappear()
        .waitScreenLoaded()
        .checkAndClickViewMoreTrainingsLink()
        .waitTrainingByNameDisplayed(trainingName)
        .clickTrainingCardByName(trainingName);
  }

  @Test(priority = 1)
  public void checkCustomBlockTitle() {
    trainingDescriptionScreen
        .waitTrainingCustomBlockTitleVisibility();
    assertTrue(
        trainingDescriptionScreen
            .getTrainingCustomBlockTitle()
            .contains(customBlockTitle),
        "Custom block title is not correct!"
    );
  }

  @Test(priority = 2)
  public void checkCustomBlockInformation() {
    trainingDescriptionScreen
        .waitTrainingCustomBlockInformationVisibility();
    assertTrue(
        trainingDescriptionScreen
            .getTrainingCustomBlockInformation()
            .contains(customBlockInformation),
        "Custom block information is not correct!"
    );
  }
}
