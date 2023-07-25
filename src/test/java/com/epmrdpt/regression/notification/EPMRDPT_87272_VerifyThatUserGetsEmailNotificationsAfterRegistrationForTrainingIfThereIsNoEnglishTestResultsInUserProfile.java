package com.epmrdpt.regression.notification;

import static com.epmrdpt.bo.user.UserFactory.asStudentForNotificationPage;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import com.epmrdpt.services.RegistrationForTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_87272_VerifyThatUserGetsEmailNotificationsAfterRegistrationForTrainingIfThereIsNoEnglishTestResultsInUserProfile",
    groups = {"full", "regression"})
public class EPMRDPT_87272_VerifyThatUserGetsEmailNotificationsAfterRegistrationForTrainingIfThereIsNoEnglishTestResultsInUserProfile {

  private final String trainingName = "AutoTest_EnglishTestTraining";
  private final String countryOfResidence = "AutoTestCountry";
  private final String cityOfResidence = "AutoTestCity";
  private final String englishTestText = "English test";
  private final String takeTestText = "Take test";
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asStudentForNotificationPage())
        .waitProfilePhotoImageVisibility();
    trainingDescriptionScreen = new TrainingBlockScreen()
        .cleanLocationFilterIfNeed()
        .waitTrainingCardsVisibility()
        .checkAndClickViewMoreTrainingsLink()
        .waitTrainingCardListDisplayed()
        .clickTrainingCardByName(trainingName)
        .waitTrainingTitleLabelVisibility();
    headerScreen = new HeaderScreen();
    headerScreen
        .clickNotificationButton()
        .clickMarkAllAsReadButton();
  }

  @Test(priority = 1)
  public void checkRegistrationWizardLoaded() {
    assertTrue(
        trainingDescriptionScreen.cancelRegistrationIfNeed().clickRegisterButton().isScreenLoaded(),
        "'Registration Wizard' isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatRegistrationCompletedSuccessfully() {
    RegistrationWizardScreen registrationWizardScreen = new RegistrationWizardScreen()
        .chooseCountryOfResidence(countryOfResidence)
        .chooseCityOfResidence(cityOfResidence)
        .clickNextTabButton()
        .waitEducationFormInputVisibility()
        .clickNextTabButton()
        .waitSkillsLabelVisibility();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        registrationWizardScreen.clickRegisterButton()
            .isRegistrationSuccessfullyPopUpDisplayed(),
        "'Registration completed successfully' pop-up isn't displayed!");
    trainingDescriptionScreen.clickPopUpOkButton();
    softAssert.assertTrue(trainingDescriptionScreen.isCancelButtonDisplayed(),
        "Registration has not completed successfully!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTextInNotification() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        headerScreen
            .waitUntilNewNotificationArrives()
            .clickNotificationButton()
            .isScreenLoaded(),
        "Notification dropdown isn't loaded!");
    new NotificationModuleScreen()
        .clickNotificationByName(englishTestText)
        .waitTrainingBodyExpanded();
    String notificationContent = new NotificationService().getNotificationPopUpContent();
    softAssert.assertTrue(notificationContent.contains(englishTestText),
        "Notification content does not contain the 'English Test' text!");
    softAssert.assertTrue(notificationContent.contains(takeTestText),
        "Notification content does not contain the 'Take Test' text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatTrainingIsCanceled() {
    new NotificationPopUpScreen()
        .closePopUpScreen()
        .waitProfileMenuDisplayed();
    assertTrue(new RegistrationForTrainingService().isRegistrationOnTrainingCancel(),
        "Registration on training isn't cancelled");
  }
}
