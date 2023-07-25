package com.epmrdpt.smoke.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13067_VerifyIfTrainingManagerCanSelectAnyStatusForApplicant",
    groups = {"full", "manager", "smoke", "critical_path"})
public class EPMRDPT_13067_VerifyIfTrainingManagerCanSelectAnyStatusForApplicant {

  private static final String TRAINING_NAME = "AutoTest_DraftStatus";
  private static final String rejectionReason = "Candidate exhibits poor character and Integrity";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private ReactParticipantsService reactParticipantsService;
  private List<String> statusList;
  private List <Integer> listOfRejectionIndexes;
  private int indexRejected;
  private int indexDismissedFromTraining;
  private int indexDismissedFromLab;
  private final String statusParticipantsRejected = "Rejected";
  private final String statusParticipantsDismissedFromTraining = "Dismissed from Training";
  private final String statusParticipantsDismissedFromLab = "Dismissed from Lab";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    reactParticipantsService = new ReactParticipantsService();
    statusList = reactParticipantsService.getApplicantStatusListFromTableMenu();
    indexDismissedFromTraining = statusList.indexOf(statusParticipantsDismissedFromTraining) + 1;
    indexDismissedFromLab = statusList.indexOf(statusParticipantsDismissedFromLab) + 1;
    indexRejected = statusList.indexOf(statusParticipantsRejected) + 1;
    listOfRejectionIndexes = List.of(indexRejected, indexDismissedFromTraining, indexDismissedFromLab);
  }

  @Test(priority = 1)
  public void checkTrainingManagerCanSelectAnyStatusForApplicantInParticipantsTable() {
    SoftAssert softAssert = new SoftAssert();
    for (int statusIndex = 0; statusIndex < statusList.size(); statusIndex++) {
      reactParticipantsService.setApplicantStatusToFirstParticipantInParticipantTableByIndex(
          statusIndex + 1,  listOfRejectionIndexes, rejectionReason);
      softAssert.assertEquals(reactParticipantsTrainingScreen
              .waitFirstParticipantStatusToBe(statusList.get(statusIndex))
              .getFirstParticipantApplicantStatus(),
          statusList.get(statusIndex),
          String.format(
              "Applicant Status of Participant wasn't changed via 'Participants Table' in %s iteration",
              statusIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTrainingManagerCanSelectAnyStatusForApplicantViaCogwheel() {
    SoftAssert softAssert = new SoftAssert();
    for (int statusIndex = 0; statusIndex < statusList.size(); statusIndex++) {
      reactParticipantsService.setApplicantStatusToFirstParticipantViaCogwheelByIndex(
          statusIndex + 1, listOfRejectionIndexes,  rejectionReason);
      softAssert.assertEquals(reactParticipantsTrainingScreen
              .waitFirstParticipantStatusToBe(statusList.get(statusIndex))
              .getFirstParticipantApplicantStatus(),
          statusList.get(statusIndex),
          String.format(
              "Applicant Status of Participant wasn't changed via 'Cogwheel' in %s iteration",
              statusIndex + 1));
    }
    softAssert.assertAll();
  }
}