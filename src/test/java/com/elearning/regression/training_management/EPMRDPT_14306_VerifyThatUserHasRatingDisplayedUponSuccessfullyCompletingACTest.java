package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14306_VerifyThatUserHasRatingDisplayedUponSuccessfullyCompletingACTest",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_14306_VerifyThatUserHasRatingDisplayedUponSuccessfullyCompletingACTest {

  private final String trainingName = "AutoTest_VCH AC";
  private final String studentName = "QQ AA";
  private final String regexForExpectedRating = "\\d+";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    reactParticipantsTrainingScreen = new ReactParticipantsService()
        .findParticipantBySearchPhrase(studentName);
  }

  @Test
  public void checkThatStudentRatingIsShownAsNumber() {
    assertTrue(
        reactParticipantsTrainingScreen
            .getStudentRatingText(studentName)
            .matches(regexForExpectedRating),
        "Student's rating is not an integer of the required form!"
    );
  }
}
