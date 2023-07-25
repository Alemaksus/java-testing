package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14304_VerifyThatTrainingsParticipantsPageContainsRatingColumn",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_14304_VerifyThatTrainingsParticipantsPageContainsRatingColumn {

  private final String TRAINING = "AUTOTEST WITH AC";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING)
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility()
        .waitScreenLoading();
  }

  @Test
  public void checkIsTableContainsRatingColumn() {
    assertTrue(new ReactParticipantsTrainingScreen().isRatingColumnDisplayed(),
        "ParticipantScreen doesn't contain \"Rating\" column!");
  }
}
