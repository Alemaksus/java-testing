package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13056_VerifyThatTrainingManagerCanDeleteStudentFromTheGroup",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13056_VerifyThatTrainingManagerCanDeleteStudentFromTheGroup {

  private final static String TRAINING_NAME = "AutoTest_RegistrationOpenStatus";
  private final static String GROUP_NAME = "GroupForDeletingStudentFromGroup";
  private final String studentName = "Aleks LastName304352";
  private final String acceptedForTrainingStatus = "Accepted for Training";
  private final String trainingInProgressStatus = "Training in Progress";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setParticipantsTab() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility();
  }

  @Test(priority = 1)
  public void checkStudentStatusInParticipantsTabBeforeDeleting() {
    assertEquals(
        new ReactParticipantsService().getRequiredParticipantsStatusByName(studentName),
            trainingInProgressStatus,
        "Students status is not 'Training in progress' before deleting");
  }

  @Test(priority = 2)
  public void checkDetailScreenLoading() {
    assertTrue(reactDetailTrainingScreen
        .clickGroupsTabs()
        .waitDataLoading()
        .clickGroupByName(GROUP_NAME)
        .isScreenLoaded(), "Group detail screen isn't loading");
  }

  @Test(priority = 3)
  public void checkThatStudentCanBeDeletedFromGroup() {
    reactTrainingManagementService
        .deleteStudentFromReactGroup(studentName);
    assertFalse(
        reactGroupDetailsScreen.isStudentInParticipantSectionDisplayed(studentName),
        format("Student %s has not been added in group!", studentName));
  }

  @Test(priority = 4)
  public void checkStudentStatusInParticipantsTabAfterDeleting() {
    reactDetailTrainingScreen
        .clickReactParticipantsTab()
        .waitScreenLoading();
    assertEquals(
        new ReactParticipantsService().getRequiredParticipantsStatusByName(studentName),
            acceptedForTrainingStatus,
        "Students status is not 'Accepted for training'");
  }

  @Test(priority = 5)
  public void checkThatStudentCanBeAddedToGroup() {
    reactDetailTrainingScreen
        .clickGroupsTabs()
        .waitDataLoading()
        .clickGroupByName(GROUP_NAME);
    reactTrainingManagementService
        .addStudentInReactGroup(studentName);
    assertTrue(
        reactGroupDetailsScreen
            .isStudentInParticipantSectionDisplayed(studentName),
        format("Student %s has not been added in group!", studentName));
  }
}
