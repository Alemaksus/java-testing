package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_AUTOMATIC_REPLY;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94080_VerifyThatRegistrationSuccessPopUpContainsCustomAutoreplyText",
    groups = {"full", "regression"})
public class EPMRDPT_94080_VerifyThatRegistrationSuccessPopUpContainsCustomAutoreplyText {

  private final static String TRAINING_NAME = "AutoTest_AutomaticReplyText";
  private final static String COUNTRY = "AutoTestCountry";
  private final static String CITY = "AutoTestCity";
  private TrainingDescriptionScreen trainingDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToEducationTabOfRegistrationWizard() {
    new LoginService()
        .loginAndSetLanguage(asLearningStudent());
    new TrainingCardsSectionService()
        .openTrainingDescription(TRAINING_NAME);
    trainingDescriptionScreen = new RegistrationForTrainingService()
        .generateRegistrationSuccessPopUp(CITY, COUNTRY);
  }

  @Test
  public void checkTheTextOfRegistrationSuccessPopUp() {
    assertEquals(trainingDescriptionScreen.getRegistrationSuccessfulPopUpText(),
        getValueOf(DETAIL_TRAINING_SCREEN_AUTOMATIC_REPLY),
        "Wrong text of 'Registration Success' pop-up!");
  }
}

