package com.epmrdpt.regression.learning;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_AC_REDIRECTION_DESCRIPTION;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13198_VerifyThatUserWithExpiredEnglishTestResultsIsRedirectedToExaminatorWhenClicksTakeTestLink",
    groups = {"full", "regression", "student"})
public class EPMRDPT_13198_VerifyThatUserWithExpiredEnglishTestResultsIsRedirectedToExaminator {

  private final String trainingName = "AutoTest_EnglishTestTraining";
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    new LoginService().loginAndSetLanguage(UserFactory.asLearningStudent());
    trainingDescriptionScreen = new TrainingCardsSectionService().openTrainingDescription(
        trainingName);
  }

  @Test(priority = 1)
  public void checkUserHasRegisteredForTraining() {
    assertTrue(new RegistrationForTrainingService()
            .registerForTraining(cityOfResidence, countryOfResidence)
            .isCancelButtonDisplayed(),
        "User registration isn't completed!");
  }

  @Test(priority = 2)
  public void checkEnglishTestPopUp() {
    assertEquals(trainingDescriptionScreen
            .clickEnglishTestLink()
            .getRedirectToEnglishTestWarningText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_AC_REDIRECTION_DESCRIPTION),
        "'Registration completed successfully' has incorrect text!");
  }

  @Test(priority = 3)
  public void checkUserIsRedirectedToExaminatorPage() {
    trainingDescriptionScreen.clickRedirectToTestOkButton()
        .switchToLastWindow();
    assertTrue(trainingDescriptionScreen.isEnglishTestTitleDisplayed(),
        "User isn't redirected to Examinator system for passing English Test!");
  }
}
