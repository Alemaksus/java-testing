package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPRMDPT_13057_VerifyThatTrainingManagerCanAddTrainerToTheGroup",
    groups = {"full", "manager", "smoke"})
public class EPRMDPT_13057_VerifyThatTrainingManagerCanAddTrainerToTheGroup {

  private final static String TRAINING_NAME = "TrainingForWorkingWithGroupAQ";
  private final static String TRAINER_NAME = "TrainerForAdding TrainerForAdding";
  private final static String GROUP_NAME = "Automated Testing 2";
  private ReactGroupScreen reactGroupScreen;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupScreen = new ReactGroupScreen();
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkGroupDetailsScreenLoaded() {
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME);
    new ReactDetailTrainingScreen()
        .clickGroupsTabs();
    assertTrue(
        reactGroupScreen
            .clickGroupByName(GROUP_NAME)
            .isScreenLoaded(),
        "Group detail screen isn't loaded");
  }

  @Test(priority = 2)
  public void checkTrainingManagerCanAddTrainersToTrainingViaAddButton() {
    new ReactTrainingManagementService()
        .deleteTrainerFromTrainersSectionIfNeed(TRAINER_NAME);
    assertTrue(
        reactGroupDetailsScreen
            .waitGroupNameTextToBePresent()
            .typeAddTrainerInput(TRAINER_NAME)
            .clickResultFromSearchByName(TRAINER_NAME)
            .clickAddTrainerButton()
            .waitAllSpinnersAreNotDisplayed()
            .isTrainerByNameDisplayed(TRAINER_NAME),
        "Trainer wasn't added to training!");
  }
}
