package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_56032_VerifyThatSimplifiedRegistrationFormIsDisplayedToApplicantsIfCorrespondingRegistrationFormIsSelected",
    groups = {"regression", "full"})
public class EPMRDPT_56032_VerifyThatSimplifiedRegistrationFormIsDisplayedToApplicantsIfCorrespondingRegistrationFormIsSelected {

  private static final String TRAINING_NAME = "AutoTest_WithSimplifiedRegistrationForm";
  private RegistrationWizardScreen registrationWizardScreen;

  @BeforeClass
  public void loginToRegistrationPage() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithTraining());
    registrationWizardScreen = new TrainingCardsSectionService()
        .clearAllFilters()
        .clickViewMoreTrainingsLink()
        .waitTrainingCardLocationVisibility()
        .clickTrainingCardByName(TRAINING_NAME)
        .waitRegisterButtonVisibility()
        .clickRegisterButton();
  }

  @Test
  public void checkSimplifiedRegistrationFormIsPresented() {
    assertTrue(registrationWizardScreen.isRegistrationButtonPresent(),
        "The 'Simplified Registration Screen' fails to load!");
  }
}
