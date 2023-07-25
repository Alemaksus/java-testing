package com.epmrdpt.smoke.training;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77616_VerifyThatAllFilledInFieldsInTheTextBlocksSectionAreDisplayedOnTheTrainingDetailsPage",
    groups = {"full", "smoke"})
public class EPMRDPT_77616_VerifyThatAllFilledInFieldsInTheTextBlocksSectionAreDisplayedOnTheTrainingDetailsPage {

  private TrainingDescriptionScreen trainingDescriptionScreen;
  private static final String TRAINING = "Auto_Training_With_All_Text_Blocks_Filled_In";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
    trainingDescriptionScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING)
        .waitScreenLoaded()
        .clickPreviewButton();
    trainingDescriptionScreen.switchToLastWindow();
  }

  @Test(priority = 1)
  public void checkTrainingDetailsSectionIsDisplayed() {
    assertFalse(trainingDescriptionScreen.getTrainingDetailsFieldText().isEmpty(),
        "Details section on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkSkillDescriptionBannerIsDisplayed() {
    assertTrue(trainingDescriptionScreen.isSkillDescriptionBannerPresent(),
        "Skill description banner on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkNotMeetRequirementsSectionIsDisplayed() {
    assertFalse(trainingDescriptionScreen.getNotMeetRequirementsFieldText().isEmpty(),
        "Not meet requirements section on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkRequiredSkillsSectionIsDisplayed() {
    assertFalse(trainingDescriptionScreen.getRequiredSkillsFieldText().isEmpty(),
        "Required skills section on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkForWhoTrainingSectionIsDisplayed() {
    assertFalse(trainingDescriptionScreen.getForWhoTrainingFieldText().isEmpty(),
        "Who is this training for section on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkHowToJoinSectionIsDisplayed() {
    assertFalse(trainingDescriptionScreen.getHowToJoinFieldText().isEmpty(),
        "How to join section on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkInformationAboutPaidConsultationsIsDisplayed() {
    assertFalse(trainingDescriptionScreen.getPaidConsultationsContentText().isEmpty(),
        "Information about paid consultations section on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkVideoContentIsDisplayed() {
    assertTrue(trainingDescriptionScreen.isVideoContentDisplayed(),
        "Video content on Training description page isn't displayed!");
  }

  @Test(priority = 2)
  public void checkMoreTrainingDetailsSectionIsDisplayed() {
    assertFalse(trainingDescriptionScreen
            .clickViewButton()
            .waitTrainingProgramPopUpDisplayed()
            .getFullDescriptionText()
            .isEmpty(),
        "More training details section on Training description page isn't displayed!");
  }
}
