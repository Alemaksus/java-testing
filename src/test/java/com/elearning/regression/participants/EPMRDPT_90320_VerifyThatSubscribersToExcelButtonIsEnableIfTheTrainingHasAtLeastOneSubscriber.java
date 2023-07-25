package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_90320_VerifyThatSubscribersToExcelButtonIsEnableIfTheTrainingHasAtLeastOneSubscriber",
    groups = {"full", "regression"})
public class EPMRDPT_90320_VerifyThatSubscribersToExcelButtonIsEnableIfTheTrainingHasAtLeastOneSubscriber {

  private static final String TRAINING_NAME = "90320_Training";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private ReactTrainingManagementScreen reactTrainingManagementScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndSearchTraining() {
    reactTrainingManagementScreen = new ReactTrainingManagementScreen();
    new ReactLoginService().loginAndGoToReactTrainingManagement(asTrainingManager());
    new ReactTrainingManagementService()
        .searchTrainingByName(TRAINING_NAME);
  }

  @Test
  public void verifyThatSubscribersToExcelButtonEnabled() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        Integer.parseInt(reactTrainingManagementScreen.getTrainingSubscribersText()) >= 1,
        "Training does not have subscribers.");
    softAssert.assertTrue(reactTrainingManagementScreen.getTrainingParticipantsText().startsWith("0"),
        "Training has participants.");
    reactParticipantsTrainingScreen = reactTrainingManagementScreen
        .clickTrainingNameByName(TRAINING_NAME)
        .clickReactParticipantsTab()
        .clickExcelButton();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSubscribersToExcelButtonClickable(),
        "The subscribers to excel option is disabled");
    softAssert.assertAll();
  }
}