package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_79796_VerifyThatChangesOfColumnConfigurationAreSavedForCurrentTraining",
    groups = {"full", "regression"})
public class EPMRDPT_79796_VerifyThatChangesOfColumnConfigurationAreSavedForCurrentTraining {

  private static final String TRAINING_NAME_WITH_CHANGES_IN_COLUMNS = "Training_For_Testing";
  private static final String TRAINING_NAME_WITHOUT_CHANGES_IN_COLUMNS = "Another_Test_Training";
  private static final String ENGLISH_LEVEL_COLUMN = "English level";
  private static final String EMAIL_COLUMN = "E-mail";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToParticipantsPage() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    new ReactLoginService().loginAndGoToReactTrainingManagement(asTrainingManager());
    reactParticipantsTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME_WITH_CHANGES_IN_COLUMNS)
        .clickReactParticipantsTab();
  }

  @Test(priority = 1)
  public void verifyColumnsAreDisplayedAccordingToChangesMade() {
    reactParticipantsTrainingScreen
        .clickConfigureColumnsButton()
        .clickConfigureColumnsCheckBoxByName(EMAIL_COLUMN)
        .clickConfigureColumnsCheckBoxByName(ENGLISH_LEVEL_COLUMN)
        .clickConfigureColumnsTabApplyButton()
        .clickDetailsTab();
    new ReactDetailTrainingScreen()
        .waitScreenLoaded()
        .clickReactParticipantsTab()
        .waitScreenLoading();
    assertTrue(reactParticipantsTrainingScreen.isParticipantHeaderColumnDisplayed(ENGLISH_LEVEL_COLUMN)
            && reactParticipantsTrainingScreen.isParticipantHeaderColumnDisplayed(EMAIL_COLUMN),
        "Changed columns are not displayed.");
  }

  @Test(priority = 2)
  public void verifyChangedColumnConfigurationsAreNotDisplayedInOtherTrainings() {
    new ReactHeaderScreen()
        .clickTrainingManagementLink();
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME_WITHOUT_CHANGES_IN_COLUMNS)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    assertFalse(reactParticipantsTrainingScreen.isParticipantHeaderColumnDisplayed(ENGLISH_LEVEL_COLUMN)
            && reactParticipantsTrainingScreen.isParticipantHeaderColumnDisplayed(EMAIL_COLUMN),
        "Changed columns are displayed.");
  }
}
