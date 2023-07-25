package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97305_VerifyThatPopUpAppearsIfTheUserCountryDoesNotMatchTheTrainingRequirement",
    groups = {"full", "regression"})
public class EPMRDPT_97305_VerifyThatPopUpAppearsIfTheUserCountryDoesNotMatchTheTrainingRequirement {

  private final static String TRAINING_NAME = "TRAINING WITH EXACT COUNTRY";
  RegistrationWizardScreen registrationWizardScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        userForRegistrationWizard());
    registrationWizardScreen = new TrainingCardsSectionService()
        .openTrainingDescription(TRAINING_NAME)
        .clickRegisterButton();
  }

  @Test
  public void checkPopUpIsDisplayed() {
    assertTrue(registrationWizardScreen.isPopUpBlockDisplayed(), "Pop up block isn't displayed");
  }
}
