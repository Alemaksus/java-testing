package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13197_VerifyPriorityMessageAboutPassingEnglishTestIsNotDisplayed",
    groups = {"full", "regression", "student", "deprecated"})
public class EPMRDPT_13197_VerifyPriorityMessageAboutPassingEnglishTestIsNotDisplayed {

  private final String trainingName = "AutoTest_EnglishTestTraining";
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    new LoginService().loginAndSetLanguage(UserFactory.withSimplePermission());
    trainingDescriptionScreen = new TrainingDescriptionScreen();
  }

  @Test(priority = 1)
  public void checkHomePageIsLoaded() {
    assertTrue(new TrainingBlockScreen().isScreenLoaded(), "Home page isn't opened!");
  }

  @Test(priority = 2)
  public void checkTrainingDescriptionPageIsOpened() {
    new TrainingCardsSectionService().openTrainingDescription(trainingName);
    assertTrue(trainingDescriptionScreen.isScreenLoaded(),
        "Training description page isn't opened!");
  }

  @Test(priority = 3)
  public void checkUserIsRegisteredForTraining() {
    new RegistrationForTrainingService().registerForTraining(cityOfResidence, countryOfResidence);
    assertTrue(trainingDescriptionScreen.isCancelButtonDisplayed(),
        "User registration isn't completed!");
  }

  @Test(priority = 4)
  public void checkPriorityMessageIsNotDisplayed() {
    assertFalse(trainingDescriptionScreen.isPriorityNotificationDisplayed(),
        "Priority notification is displayed for english test!");
  }
}
