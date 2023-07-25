package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14310_VerifyThatTrainingsParticipantsPageContainsColumnEnglishTestResults",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_14310_VerifyThatTrainingsParticipantsPageContainsColumnEnglishTestResults {

  private final String TRAINING = "AutoTest_CheckRatingColour";
  private final String STUDENT_NAME = "Kristof LastName314478";
  private final String EXPECTED_ENGLISH_TEST_RESULT_A2 = "A2";
  private final String EXPECTED_ENGLISH_TEST_RESULT_A0 = "A0";
  private final String EXPECTED_ENGLISH_TEST_RESULT_A1 = "A1";

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING)
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility()
        .waitScreenLoading();
  }

  @Test
  public void checkParticipantsPageContainsEnglishTestResultsCell() {
    reactParticipantsTrainingScreen.typeSearchPhraseToSearchInput(STUDENT_NAME)
        .waitSpinnerOfLoadingInvisibility()
        .clickApplyButton();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isEnglishResultsColumnDisplayed());
    String studentTestResult = reactParticipantsTrainingScreen.getStudentEnglishTestResultText(
        STUDENT_NAME);
    softAssert.assertTrue(studentTestResult.contains(EXPECTED_ENGLISH_TEST_RESULT_A2) ||
        studentTestResult.contains(EXPECTED_ENGLISH_TEST_RESULT_A0) ||
        studentTestResult.contains(EXPECTED_ENGLISH_TEST_RESULT_A1));
    softAssert.assertAll();
  }
}
