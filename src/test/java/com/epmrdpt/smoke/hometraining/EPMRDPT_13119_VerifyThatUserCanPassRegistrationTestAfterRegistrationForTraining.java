package com.epmrdpt.smoke.hometraining;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13119_VerifyThatUserCanPassRegistrationTestAfterRegistrationForTraining",
    groups = {"full", "student", "smoke", "critical_path"})
public class EPMRDPT_13119_VerifyThatUserCanPassRegistrationTestAfterRegistrationForTraining {

  private final String trainingName = "AUTOTEST WITH AC";
  private String cityOfResidence = "AutoTestCity";
  private final String countryOfResidence = "AutoTestCountry";

  private TrainingDescriptionScreen trainingDescriptionScreen;
  private RegistrationForTrainingService registrationForTrainingService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingDescriptionScreen = new TrainingDescriptionScreen();
    registrationForTrainingService = new RegistrationForTrainingService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(userForRegistrationWizard())
        .waitProfilePhotoImageVisibility();
    new TrainingCardsSectionService()
        .openTrainingDescription(trainingName);
    if (trainingDescriptionScreen.isCancelButtonDisplayed()) {
      registrationForTrainingService
          .cancelRegistrationForTraining()
          .waitRegisterButtonVisibility();
    }
    trainingDescriptionScreen
        .clickRegisterButton()
        .waitBasicInfoTabForVisibility();
  }

  @Test(priority = 1)
  public void checkRegistrationCompletedSuccessfullyPopUpDisplayed() {
    assertTrue(
        new RegistrationWizardScreen()
            .waitBasicInfoTabForVisibility()
            .chooseCountryOfResidence(countryOfResidence)
            .chooseCityOfResidence(cityOfResidence)
            .clickNextTabButton()
            .waitEducationFormInputVisibility()
            .clickNextTabButton()
            .waitSkillsLabelVisibility()
            .clickRegisterButton()
            .isRegistrationSuccessfullyPopUpDisplayed(),
        "'Registration completed successfully' pop-up isn't displayed!");
  }

  @Test(priority = 2)
  public void checkUserCanPassRegistrationTestAfterRegistrationForTraining() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        trainingDescriptionScreen
            .clickPopUpOkButton()
            .waitRegistrationSuccessfullyPopUpDisappear()
            .clickAssignmentContainerLink()
            .isRedirectToTestPopUpDisplayed(),
        "Redirect information pop-up isn't displayed!");
    trainingDescriptionScreen
        .clickRedirectToTestOkButton()
        .switchToLastWindow();
    softAssert.assertTrue(trainingDescriptionScreen
            .isTestingSystemPageLogoDisplayed()
            && trainingDescriptionScreen
            .isExaminatorAssignmentDetailsDisplayed(),
        "User isn't redirected to Registration Test!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkRegistrationOnTrainingCancel() {
    trainingDescriptionScreen.switchToFirstWindowIfMoreThanOne();
    assertTrue(registrationForTrainingService.isRegistrationOnTrainingCancel(),
        "User didn't unsubscribe from the Training!");
  }
}
