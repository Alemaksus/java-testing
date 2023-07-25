package com.epmrdpt.smoke.hometraining;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12968_StudentRegistrationForTraining",
    groups = {"full", "student", "smoke", "critical_path"})
public class EPMRDPT_12968_StudentRegistrationForTraining {

  private final String trainingName = "AutoTest_LearningStudentsWorkflow";
  private final String cityOfResidence = "AutoTestCity";
  private final String countryOfResidence = "AutoTestCountry";

  private TrainingDescriptionScreen trainingDescriptionScreen;
  private RegistrationForTrainingService registrationForTrainingService;
  private RegistrationWizardScreen registrationWizardScreen;
  private User user;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    registrationForTrainingService = new RegistrationForTrainingService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(userForRegistrationWizard())
        .waitProfilePhotoImageVisibility();
    new TrainingBlockScreen()
        .cleanLocationFilterIfNeed()
        .waitTrainingCardsVisibility()
        .checkAndClickViewMoreTrainingsLink()
        .waitTrainingCardListDisplayed();
    trainingDescriptionScreen = new TrainingCardsSectionService()
        .openTrainingDescription(trainingName)
        .waitTrainingTitleLabelVisibility();
  }

  @Test(priority = 1)
  public void checkRegistrationWizardLoading() {
    if (trainingDescriptionScreen.isPopUpSurveyDisplayed()) {
      trainingDescriptionScreen
          .clickPopUpSelectOptionInput()
          .chosePopUpAnswerOption()
          .clickPopUpSubmitButton();
    }
    RegistrationWizardScreen registrationWizardScreen = trainingDescriptionScreen.clickRegisterButton();
    assertTrue(registrationWizardScreen.isScreenLoaded(), "'Registration Wizard' isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatRegistrationCompletedSuccessfully() {
    user = UserFactory.userForWorkExperienceForm();
    SoftAssert softAssert = new SoftAssert();
    registrationWizardScreen = new RegistrationWizardScreen()
        .chooseCountryOfResidence(countryOfResidence)
        .chooseCityOfResidence(cityOfResidence)
        .clickNextTabButton()
        .waitEducationFormInputVisibility()
        .clickNextTabButton()
        .waitSkillsLabelVisibility();
    registrationForTrainingService.fillUserWorkExperienceForm(user);
    softAssert.assertTrue(registrationWizardScreen.clickRegisterButton()
            .isRegistrationSuccessfullyPopUpDisplayed(),
        "'Registration completed successfully' pop-up isn't displayed!");
    trainingDescriptionScreen.clickPopUpOkButton();
    softAssert.assertTrue(trainingDescriptionScreen.isCancelButtonDisplayed(),
        "Registration isn't completed successfully!");
    softAssert.assertTrue(registrationForTrainingService.isRegistrationOnTrainingCancel(),
        "Registration on training isn't cancelled");
    softAssert.assertAll();
  }
}
