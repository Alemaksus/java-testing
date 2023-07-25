package com.epmrdpt.regression.training_management;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77643_VerifyThatSkillDescriptionBlockIsDisplayedWhenTheToggleDisplayOnTrainingEditPageIsON",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_77643_VerifyThatSkillDescriptionBlockIsDisplayedWhenTheToggleDisplayOnTrainingEditPageIsON {

  private final String trainingName = "AUTOTEST WITH AC";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    trainingDescriptionScreen = new TrainingDescriptionScreen();
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(UserFactory.asTrainingManager());
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test(priority = 1)
  public void checkTheToggleDisplayOfSkillOnGeneralInfoTabIsOn() {
    Assert.assertTrue(new ReactGeneralInfoTabScreen().isSkillDisplayToggleTurnedOn(),
        "Display toggle is not turned on!");
  }

  @Test(priority = 2)
  public void checkTrainingDetailsPageIsOpened() {
    reactDetailTrainingScreen.clickPreviewButton()
        .switchToLastWindow();
    Assert.assertTrue(trainingDescriptionScreen.isScreenLoaded(),
        "Training Description page is not opened!");
  }

  @Test(priority = 3)
  public void checkSkillDescriptionBlockIsDisplayed() {
    Assert.assertTrue(trainingDescriptionScreen.isSkillDescriptionBannerPresent(),
        "Skill description block is not displayed!");
  }
}
