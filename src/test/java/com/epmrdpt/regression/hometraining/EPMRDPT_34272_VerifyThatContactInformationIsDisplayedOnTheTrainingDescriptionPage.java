package com.epmrdpt.regression.hometraining;

import static com.epmrdpt.bo.user.UserFactory.asRecruiter;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34272_VerifyThatEmailAndPhoneNumberFromOpenInformationIsDisplayedAsContactInformationOnTheTrainingDescriptionPage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34272_VerifyThatContactInformationIsDisplayedOnTheTrainingDescriptionPage {

  private final String TRAINING_NAME = "AutoTest_PlannedStatus";
  private String userEmail;
  private String userPhoneNumber;
  private TrainingDescriptionScreen trainingDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupContactInformation() {
    ProfileScreen profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asRecruiter())
        .clickProfileMenu()
        .clickProfileButton()
        .clickProfessionalInfoButton()
        .waitScreenLoading();
    userEmail = profileScreen.getUserEmailFromOpenInformation();
    userPhoneNumber = profileScreen.getUserPhoneNumberFromOpenInformation();
    new HeaderScreen().clickProfileMenu().signOut();
    trainingDescriptionScreen = new TrainingCardsSectionService()
        .openTrainingDescription(TRAINING_NAME)
        .waitTrainingTitleLabelVisibility();
  }

  @Test(priority = 1)
  public void checkThatContactInformationDisplayed() {
    assertTrue(trainingDescriptionScreen.mouseOverContactNameField()
            .isContactInformationTooltipDisplayed(),
        "Tooltip with contact information isn't displayed!");
  }

  @Test(priority = 2)
  public void checkEmailOnContactTooltip() {
    assertEquals(trainingDescriptionScreen.getEmailFieldText(), userEmail,
        "User email is incorrect!");
  }

  @Test(priority = 2)
  public void checkPhoneNumberOnContactTooltip() {
    assertEquals(trainingDescriptionScreen.getPhoneNumberFieldText(), userPhoneNumber,
        "User phone number is incorrect!");
  }
}
