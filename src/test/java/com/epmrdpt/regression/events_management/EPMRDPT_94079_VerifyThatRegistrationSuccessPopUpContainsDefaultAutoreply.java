package com.epmrdpt.regression.events_management;

import static org.testng.Assert.assertEquals;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_FOR_TRAINING_SUCCESS_POP_UP_TEXT;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94079_VerifyThatRegistrationSuccessPopUpContainsDefaultAutoreply",
    groups = {"full", "regression"})
public class EPMRDPT_94079_VerifyThatRegistrationSuccessPopUpContainsDefaultAutoreply {

  private final static String TRAINING_NAME = "AutoTestWithCustom";
  private final static String COUNTRY = "AutoTestCountry";
  private final static String CITY = "AutoTestCity";
  private TrainingDescriptionScreen trainingDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingDescriptionScreen() {
    new LoginService()
        .loginAndSetLanguage(UserFactory.asLearningStudent());
    new TrainingCardsSectionService()
        .openTrainingDescription(TRAINING_NAME);
    trainingDescriptionScreen = new RegistrationForTrainingService()
        .generateRegistrationSuccessPopUp(CITY, COUNTRY);
  }

  @Test
  public void checkTheTextOfRegistrationSuccessPopUp() {
    assertEquals(trainingDescriptionScreen.getRegistrationSuccessfulPopUpText(),
        getValueOf(REGISTRATION_FOR_TRAINING_SUCCESS_POP_UP_TEXT),
        "Wrong text of 'Registration Success' pop-up!");
  }
}
