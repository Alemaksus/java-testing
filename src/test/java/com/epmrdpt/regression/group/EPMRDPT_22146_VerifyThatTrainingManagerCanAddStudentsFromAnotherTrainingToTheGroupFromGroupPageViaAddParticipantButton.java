package com.epmrdpt.regression.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22146_VerifyThatTrainingManagerCanAddStudentsFromAnotherTrainingToTheGroupFromGroupPageViaAddParticipantButton",
    groups = {"full", "regression", "manager"})
public
class EPMRDPT_22146_VerifyThatTrainingManagerCanAddStudentsFromAnotherTrainingToTheGroupFromGroupPageViaAddParticipantButton {

  private String trainingWithStudents = "AutoTest_StudentsForAnotherTraining";
  private String trainingNameForAddingStudents = "AutoTest_AddManyStudentsFromAnotherTraining";
  private String groupName = "AddStudentFromAnotherTraining";
  private String statusTrainingInProgress = "Training in Progress";
  private List<String> participantNamesAddedToGroup;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    reactTrainingManagementService = new ReactTrainingManagementService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkGroupDetailScreenLoading() {
    assertTrue(
        reactTrainingManagementService
            .searchTrainingByNameAndRedirectOnIt(trainingNameForAddingStudents)
            .clickGroupsTabs()
            .clickGroupByName(groupName)
            .waitDataLoading()
            .isScreenLoaded(),
        "Group detail screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatChangeMessageIsDisplayed() {
    if (reactGroupDetailsScreen.isParticipantsDisplayedShortTimeOut()) {
      reactTrainingManagementService.deleteAllParticipantsFromReactGroup();
    }
    participantNamesAddedToGroup = reactGroupDetailsScreen
        .clickCancelTrainingIconInParticipantsSection()
        .waitDataLoading()
        .clickTrainingNameInSelectTrainingDDL(trainingWithStudents)
        .waitAllSpinnersAreNotDisplayed().clickSelectParticipantDropDown()
        .getStudentsNamesFromAddStudentDDL();
    assertTrue(
        reactGroupDetailsScreen
            .clickSelectAllStudentsButton()
            .clickAddParticipantButton().isResultOfChangeMessageDisplayed(),
        "Result of change message isn't displayed");
  }

  @Test(priority = 3)
  public void checkStudentsAddedToGroupAndTheirStatusIsTrainingInProgress() {
    softAssert = new SoftAssert();
    for (String participantName : participantNamesAddedToGroup) {
      softAssert.assertTrue(
          reactGroupDetailsScreen.isStudentInParticipantSectionDisplayed(participantName),
          String.format("Student %s isn't added!", participantName));
      softAssert.assertEquals(
          reactGroupDetailsScreen.getStudentStatusFromParticipantSection(participantName),
          statusTrainingInProgress,
          String.format("Student %s has incorrect status!", participantName));
    }
    softAssert.assertAll();
  }
}
