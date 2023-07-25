package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14296_VerifyTrainingManagerCanAddStudentsToTheGroupViaAddParticipantsButtonTrainingInProgress",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_14296_VerifyTrainingManagerCanAddStudentsToTheGroupViaAddParticipantsButtonTrainingInProgress {

  private static final String TRAINING_NAME = "AutoTest_DraftStatus";
  private static final String GROUP_NAME = "GroupForAddingStudents";
  private final String trainingInProgressStatus = "Training in Progress";
  private static final int MINIMUM_PARTICIPANT_COUNT = 2;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private List<String> participantNamesInPopUp;
  private List<String> participantNamesAddedToGroup;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactGroupDetailsScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickGroupsTabs()
        .waitDataLoading()
        .clickGroupByName(GROUP_NAME);
    if (reactGroupDetailsScreen.isParticipantsDisplayedShortTimeOut()) {
      reactTrainingManagementService.deleteAllParticipantsFromReactGroup();
    }
  }

  @Test(priority = 1)
  public void checkReactGroupDetailPageLoading() {
    assertTrue(reactGroupDetailsScreen
            .isScreenLoaded(),
        "React group details page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatParticipantsInDropDownMenuDisplayed() {
    reactGroupDetailsScreen
        .clickSelectParticipantDropDown()
        .waitPopperVisibility();
    assertTrue(reactGroupDetailsScreen.isStudentInPopperDropDownMenuDisplayed(),
        "Pop up with participants isn't opened");
  }

  @Test(priority = 3)
  public void checkPopUpWithListOfParticipantsIsOpened() {
    participantNamesInPopUp = reactGroupDetailsScreen
        .getStudentsNamesFromAddStudentDDL();
    assertTrue(participantNamesInPopUp.size() >=
            MINIMUM_PARTICIPANT_COUNT,
        format("Participant count in pop up is less than %d!", MINIMUM_PARTICIPANT_COUNT));
  }

  @Test(priority = 4)
  public void checkThatStudentsAddedToGroup() {
    Collections.sort(participantNamesInPopUp);
    reactGroupDetailsScreen
        .clickSelectAllStudentsButton()
        .clickAddParticipantButton()
        .waitDataLoading();
    participantNamesAddedToGroup = reactGroupDetailsScreen
        .getStudentsNamesStudentInfoSection();
    Collections.sort(participantNamesAddedToGroup);
    assertEquals(participantNamesInPopUp, participantNamesAddedToGroup,
        "Participants names in students info and in popup are different!");
  }

  @Test(priority = 5)
  public void checkThatStudentsTrainingInProgress() {
    assertTrue(participantNamesAddedToGroup
            .stream()
            .allMatch(participantName ->
                reactGroupDetailsScreen
                    .getApplicantStatusByName(participantName)
                    .equals(trainingInProgressStatus)),
        "Applicant status is incorrect!");
  }
}

