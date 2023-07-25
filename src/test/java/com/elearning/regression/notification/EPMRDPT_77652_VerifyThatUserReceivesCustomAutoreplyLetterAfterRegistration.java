package com.epmrdpt.regression.notification;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77652_VerifyThatUserReceivesCustomAutoreplyLetterAfterRegistration",
    groups = {"full", "regression"})
public class EPMRDPT_77652_VerifyThatUserReceivesCustomAutoreplyLetterAfterRegistration {

  private final String customNotificationText = "Text for Auto Test";
  private final String trainingName = "AutoTest_NotificationWithCustom";
  private final String countryOfResidence = "AutoTestCountry";
  private final String cityOfResidence = "AutoTestCity";
  private final int notificationIndex = 0;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private HeaderScreen headerScreen;
  private RegistrationWizardScreen registrationWizardScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(userForRegistrationWizard())
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
    registrationWizardScreen =
        trainingDescriptionScreen.clickRegisterButton();
    assertTrue(registrationWizardScreen.isScreenLoaded(), "'Registration Wizard' isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatRegistrationCompletedSuccessfully() {
    registrationWizardScreen = registrationWizardScreen
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
    softAssert.assertTrue(
        trainingDescriptionScreen.isCancelButtonDisplayed(),
        "Registration has not completed successfully!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCustomTextInNotification() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        headerScreen
            .waitUntilNewNotificationArrives()
            .clickNotificationButton()
            .isScreenLoaded(),
        "Notification dropdown isn't loaded!");
    new NotificationModuleScreen()
        .clickNotificationByIndex(notificationIndex)
        .waitTrainingBodyExpanded();
    NotificationPopUpScreen notificationPopUpScreen = new NotificationPopUpScreen();
    softAssert.assertEquals(
        notificationPopUpScreen.getCustomNotificationPopUpContext(), customNotificationText,
        "Notification content does not contain custom text!");
    notificationPopUpScreen
        .closePopUpScreen()
        .waitProfileMenuDisplayed();
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatTrainingIsCanceled() {
    assertTrue(
        new RegistrationForTrainingService().isRegistrationOnTrainingCancel(),
        "Registration on training isn't cancelled"
    );
  }
}
