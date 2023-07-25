package com.epmrdpt.smoke.participants;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13185_VerifyIfTrainingManagerCanSearchParticipantsViaStatus",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13185_VerifyIfTrainingManagerCanSearchParticipantsViaStatus {

  private final String trainingName = "AUTOTEST WITH AC";
  private final int applicantStatusIndex = 1;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asTrainingManager());
    new HeaderScreen().clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab();
  }

  @Test(priority = 1)
  public void checkThatParticipantsPageOfExpectedTrainingOpened() {
    assertTrue(reactParticipantsTrainingScreen.waitSpinnerOfLoadingInvisibility().isScreenLoaded(),
        "Participant page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatManagerCanSearchByStatusParticipants() {
    new ReactParticipantsService()
        .clickAndSelectSortingStudentStatusByIndex(applicantStatusIndex)
        .waitSpinnerOfLoadingInvisibility();
    assertTrue(new ReactParticipantsService().checkStudentsStatuses(
            reactParticipantsTrainingScreen.getApplicantStatusText()),
        "Not all students have correct status");
  }
}
