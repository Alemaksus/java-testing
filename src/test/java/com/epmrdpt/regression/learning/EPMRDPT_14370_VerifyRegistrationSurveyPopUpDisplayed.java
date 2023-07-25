package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asNewsCreator;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_SURVEY_TITLE;

import com.epmrdpt.screens.RegistrationSurveyScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14370_VerifyThatRegistrationSurveyPopUpDisplayedWhenUserApplyingForTheTrainingWhichRequiresRegistrationSurveyPassing",
    groups = {"full", "regression", "student"})
public class EPMRDPT_14370_VerifyRegistrationSurveyPopUpDisplayed {

  private TrainingCardsSectionService trainingCardsSectionService;
  private RegistrationSurveyScreen registrationSurveyScreen;
  private SoftAssert softAssert;

  private final String trainingName = "AutoTest_REGISTRATION_OPEN_Survey";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    trainingCardsSectionService = new TrainingCardsSectionService();
    registrationSurveyScreen = new RegistrationSurveyScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asNewsCreator());
    trainingCardsSectionService.openTrainingDescription(trainingName)
        .waitTrainingStatusTextPresent()
        .clickRegisterButton();
  }

  @Test
  public void checkRegistrationSurveyWindowAndTitle() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(registrationSurveyScreen.isScreenLoaded(),
        "Registration survey pop-up hasn't opened!");
    softAssert
        .assertEquals(registrationSurveyScreen.getSurveyPopUpTitle(),
            getValueOf(REGISTRATION_SURVEY_TITLE),
            "Registration survey pop-up title doesn't match!");
    softAssert.assertAll();
  }
}
