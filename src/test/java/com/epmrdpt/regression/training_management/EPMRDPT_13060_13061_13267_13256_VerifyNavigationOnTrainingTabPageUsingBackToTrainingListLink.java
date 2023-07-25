package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13060_13061_13267_13256_VerifyNavigationOnTrainingTabPageUsingBackToTrainingListLink",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_13060_13061_13267_13256_VerifyNavigationOnTrainingTabPageUsingBackToTrainingListLink {

  private final String trainingName = "AutoTestWithTraining";
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenDetailTrainingPage() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactDetailTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName);
  }

  @Test(priority = 1)
  public void checkNavigationOnTrainingDetailsPage() {
    assertTrue(reactDetailTrainingScreen
            .clickPlanningTitle()
            .waitPreloadingPictureHidden()
            .isScreenLoaded(),
        "Training list isn't opened after click 'Back to training list' link from 'Detail' tab!");
  }

  @Test(priority = 2)
  public void checkBackToTrainingsLinkOnParticipantsTab() {
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    assertTrue(reactDetailTrainingScreen
            .clickPlanningTitle()
            .waitPreloadingPictureHidden()
            .isScreenLoaded(),
        "Training list isn't opened after click 'Back to training list' link from TrainingManagement tab!");
  }

  @Test(priority = 3)
  public void checkBackToTrainingsLinkOnGroupsTab() {
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickGroupsTabs()
        .waitDataLoading();
    assertTrue(reactDetailTrainingScreen
            .clickPlanningTitle()
            .waitPreloadingPictureHidden()
            .isScreenLoaded(),
        "Training list isn't opened after click 'Back to training list' link from Group tab!");
  }

  @Test(priority = 4)
  public void checkHomeButtonOnDetailsTab() {
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickHomeButton();
    assertTrue(new TrainingListScreen()
            .isScreenLoaded(),
        "Trainings list isn't opened after click 'Home button' link from Details training screen!");
  }
}
