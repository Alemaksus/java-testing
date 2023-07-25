package com.epmrdpt.smoke.hometraining;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithoutTrainings;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34643_VerifyThatAfterClickingOnRegisterButtonOnTheTrainingCardRegistrationFormForTheTrainingProgramIsDisplayedForLoggedUser",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34643_VerifyThatAfterClickingOnRegisterButtonOnTheTrainingCardRegistrationFormForTheTrainingProgramIsDisplayedForLoggedUser {

  private final String trainingName = "AUTOTEST WITH ENGLISH";

  private TrainingCardsSectionService trainingCardsSectionService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void homeScreenInitialization() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermissionAndWithoutTrainings());
    trainingCardsSectionService = new TrainingCardsSectionService();
  }

  @Test(priority = 1)
  public void checkThatRegisterButtonOnTrainingCardFromHomePageLeadsToRegistrationForm() {
    assertTrue(trainingCardsSectionService.clickRegisterButtonByTrainingName(trainingName)
        .isScreenLoaded(), "Training Registration form from Home page doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainingListPageLoading() {
    trainingCardsSectionService.openTrainingListScreen();
    new RegistrationForTrainingService().clickTrainingRegistrationPopUpOkButton();
    assertTrue(new TrainingListScreen()
        .isScreenLoaded(), "Training list page doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkThatRegisterButtonOnTrainingCardFromTrainingListLeadsToRegistrationForm() {
    assertTrue(trainingCardsSectionService.clickRegisterButtonByTrainingName(trainingName)
        .isScreenLoaded(), "Training Registration form from Training list doesn't loaded!");
  }
}
