package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FORMATION;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(
    description = "EPMRDPT_13054_VerifyThatTrainingManagerCanAddStudentsToTheGroupViaAddButton",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13054_VerifyThatTrainingManagerCanAddStudentsToTheGroupViaAddButton {

  private final String trainingName = "AutoTest_RegistrationOpenStatus";
  private final String groupName = "Android 1";
  private final String studentName = "QQ AA";
  private final String acceptedForTrainingStatus = "Accepted for Training";
  private final String trainingInProgressStatus = "Training in Progress";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactDetailTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName);
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
  }

  @Test(priority = 1)
  public void checkStudentWithAcceptedForTrainingStatus() {
    reactDetailTrainingScreen
        .clickReactParticipantsTab()
        .waitScreenLoading();
    assertEquals(new ReactParticipantsService().getRequiredParticipantsStatusByName(studentName),
        acceptedForTrainingStatus,
        format("Applicant status of student: %s is not 'Accepted For Training'!", studentName));
  }

  @Test(priority = 2)
  public void checkGroupHasFormationStatus() {
    assertEquals(reactDetailTrainingScreen
            .clickGroupsTabs()
            .waitDataLoading()
            .getGroupStatusByName(groupName),
        getValueOf(TRAINING_MANAGER_STATUS_FORMATION), "Group status is not 'Formation'!");
  }

  @Test(priority = 3)
  public void checkTrainingManagerCanAddStudentsToGroupViaAddButton() {
    new ReactGroupScreen().clickGroupByName(groupName);
    if (reactGroupDetailsScreen.isStudentInParticipantSectionDisplayed(studentName)) {
      reactTrainingManagementService.deleteStudentFromReactGroup(studentName);
    }
    reactGroupDetailsScreen.waitAllSpinnersAreNotDisplayed();
    assertTrue(reactTrainingManagementService.addStudentInReactGroup(studentName)
            .isStudentInParticipantSectionDisplayed(studentName),
        format("Student %s has not been added in group!", studentName));
  }

  @Test(priority = 4)
  public void checkStudentsHasCorrectStatusAfterAddingInGroup() {
    String studentStatus = reactGroupDetailsScreen
        .getStudentStatusFromParticipantSection(studentName);
    reactTrainingManagementService
        .deleteStudentFromReactGroup(studentName);
    assertEquals(studentStatus,
            trainingInProgressStatus,
        format(
            "Applicant status of student:%s hasn't changed to 'Training in Progress' after adding to group!",
            studentName));
  }
}
