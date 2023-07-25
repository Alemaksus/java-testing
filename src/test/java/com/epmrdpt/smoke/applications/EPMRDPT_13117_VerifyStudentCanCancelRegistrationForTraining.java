package com.epmrdpt.smoke.applications;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_CANCELLATION_WARNING;

import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13117_VerifyStudentCanCancelRegistrationForTraining",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_13117_VerifyStudentCanCancelRegistrationForTraining {

  private ApplicationsScreen applicationsScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private SoftAssert softAssert;

  private final String trainingName = "AutoTest_NotificationLanguageVerification";
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    applicationsScreen = new ApplicationsScreen();
    trainingDescriptionScreen = new TrainingDescriptionScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent());
    new TrainingCardsSectionService().openTrainingDescription(trainingName);
    new RegistrationForTrainingService().registerForTraining(cityOfResidence, countryOfResidence);
    new HeaderScreen()
        .clickProfileMenu()
        .clickApplicationsButton()
        .waitScreenLoading();
  }

  @Test
  public void checkRegistrationCancellation() {
    softAssert = new SoftAssert();
    applicationsScreen.clickCancelButtonByTrainingName(trainingName);
    softAssert.assertTrue(trainingDescriptionScreen.isRegistrationCancellationPopUpDisplayed(),
        "Registration cancellation pop-up isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.getRegistrationCancellationPopUpText()
            .contains(getValueOf(TRAINING_DESCRIPTION_CANCELLATION_WARNING)),
        "Warning text in cancel-registration pop-up isn't correct!");
    trainingDescriptionScreen.clickSubmitButtonInCancelRegistrationPopUp().clickRefreshButton();
    softAssert.assertFalse(
        applicationsScreen.waitScreenLoading().isApplicationByNameDisplayed(trainingName),
        "Application cancellation wasn't successful!");
    softAssert.assertAll();
  }
}
