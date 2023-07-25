package com.epmrdpt.smoke.hometraining;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_CHANGE_RESOURCE_NOTIFICATION;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13118_VerifyThatUserCanPassEnglishTestAfterRegistrationForATraining",
    groups = {"full", "student", "smoke", "critical_path"})
public class EPMRDPT_13118_VerifyThatUserCanPassEnglishTestAfterRegistrationForATraining {

  private final String trainingName = "AUTOTEST WITH ENGLISH";
  private final String cityOfResidence = "AutoTestCity";
  private final String countryOfResidence = "AutoTestCountry";

  private TrainingBlockScreen trainingBlockScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private RegistrationForTrainingService registrationForTrainingService;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen();
    trainingDescriptionScreen = new TrainingDescriptionScreen();
    registrationForTrainingService = new RegistrationForTrainingService();
  }

  @Test(priority = 1)
  public void checkUserLogIn() {
    assertTrue(new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(userForRegistrationWizard())
        .isProfilePhotoImageDisplayed(), "User doesn't Log In!");
  }

  @Test(priority = 2)
  public void checkTrainingCardsOnHomePageLoading() {
    trainingBlockScreen.waitTrainingCardsVisibility();
    checkLocationFilterAndClearIt();
    assertTrue(
        trainingBlockScreen
            .clickViewMoreTrainingsLink()
            .isAllTitlesOfTrainingCardsDisplayed(),
        "Not All Trainings Card are displayed!");
  }

  @Test(priority = 3)
  public void checkTrainingDescriptionScreenLoading() {
    assertTrue(trainingBlockScreen
            .clickTrainingCardByName(trainingName)
            .isScreenLoaded(),
        "Training Description Screen isn't loaded!");
  }

  @Test(priority = 4)
  public void checkRegistrationWizardLoading() {
    if (trainingDescriptionScreen.isCancelButtonDisplayed()) {
      new RegistrationForTrainingService().isRegistrationOnTrainingCancel();
    }
    assertTrue(trainingDescriptionScreen.clickRegisterButton().isScreenLoaded(),
        "'Registration Wizard' isn't loaded!");
  }

  @Test(priority = 5)
  public void checkRegistrationCompletedSuccessfully() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(new RegistrationWizardScreen()
            .waitBasicInfoTabForVisibility()
            .chooseCountryOfResidence(countryOfResidence)
            .chooseCityOfResidence(cityOfResidence)
            .clickNextTabButton()
            .waitEducationFormInputVisibility()
            .clickNextTabButton()
            .clickRegisterButton()
            .isRegistrationSuccessfullyPopUpDisplayed(),
        "'Registration completed successfully' pop-up isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.clickPopUpOkButton()
            .waitTrainingInformationSectionForVisibility().isCancelButtonDisplayed(),
        "Registration isn't completed successfully!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkUserRedirectedToExaminatorSystemForPassingEnglishTest() {
    softAssert = new SoftAssert();
    trainingDescriptionScreen.clickEnglishTestLink();
    softAssert.assertEquals(trainingDescriptionScreen.getChangeResourceNotificationText(),
        getValueOf(TRAINING_DESCRIPTION_CHANGE_RESOURCE_NOTIFICATION),
        "Notification has incorrect text!");
    if (trainingDescriptionScreen.isRedirectToTestPopUpDisplayed()) {
      trainingDescriptionScreen.clickRedirectToTestOkButton();
    }
    trainingDescriptionScreen.switchToLastWindow();
    softAssert.assertTrue(
        trainingDescriptionScreen.isTestingSystemPageLogoDisplayed()
            && trainingDescriptionScreen.isEnglishTestTitleDisplayed(),
        "User isn't redirected to Examinator system for passing English Test!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkRegistrationOnTrainingCancel() {
    trainingDescriptionScreen.switchToFirstWindowIfMoreThanOne();
    assertTrue(registrationForTrainingService.isRegistrationOnTrainingCancel(),
        "User didn't unsubscribe from the Training!");
  }

  private void checkLocationFilterAndClearIt() {
    if (trainingBlockScreen.isLocationFilterClearButtonDisplayed()) {
      trainingBlockScreen.clickLocationFilterClearButton();
    }
  }
}
