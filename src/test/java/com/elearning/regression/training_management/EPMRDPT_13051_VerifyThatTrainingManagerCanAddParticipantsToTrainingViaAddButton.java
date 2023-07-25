package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13051_VerifyThatTrainingManagerCanAddParticipantsToTrainingViaAddButton",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_13051_VerifyThatTrainingManagerCanAddParticipantsToTrainingViaAddButton {

  private static final String PART_PARTICIPANT_NAME = "Las";
  private static final String TRAINING_NAME = "AutoTest_ForAdding1";
  private static final String PARTICIPANTS_APPLICANT_STATUS_NEW = "New";
  private static String participantName;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkDetailsScreenLoading() {
    assertTrue(new ReactTrainingManagementService()
            .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
            .waitAllSpinnersAreNotDisplayed()
            .isScreenLoaded(),
        "'Details' screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkParticipantsScreenLoading() {
    new ReactDetailTrainingScreen()
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility();
    assertTrue(reactParticipantsTrainingScreen.isScreenLoaded(),
        "'Participants' screen doesn't loaded!");
  }

  @Test(priority = 3)
  public void isSelectStudentDropDownDisplayed() {
    assertTrue(reactParticipantsTrainingScreen
        .typeTextInAddParticipantInput(PART_PARTICIPANT_NAME)
        .waitSpinnerOfLoadingInvisibility()
        .isStudentDDLDisplayed(), "Select student drop down list isn't displayed!");
  }

  @Test(priority = 4)
  public void checkTrainingManagerCanAddParticipantsToTrainingViaAddButton() {
    participantName = reactParticipantsTrainingScreen
        .clickFirstStudentInAddParticipantDDLByPartialName(PART_PARTICIPANT_NAME)
        .getAddParticipantInputValue();
    reactParticipantsTrainingScreen
        .clickAddParticipantButton()
        .waitSuccessfullyAddedParticipantPopUpVisibility()
        .clickCrossButtonPopUp()
        .typeSearchByNameInput(participantName)
        .clickApplyButton()
        .waitSpinnerOfLoadingInvisibility();
    assertTrue(reactParticipantsTrainingScreen.isParticipantFindByNameDisplayed(participantName),
        String.format(
            "Student %s isn't displayed in participant table after adding via Add participant button!",
            participantName));
  }

  @Test(priority = 5)
  public void isAddedParticipantHasStatusNew() {
    assertEquals(reactParticipantsTrainingScreen
            .getApplicantStatusByParticipantName(participantName), PARTICIPANTS_APPLICANT_STATUS_NEW,
        String.format("Student %s hasn't applicant status new!", participantName));
  }
}
