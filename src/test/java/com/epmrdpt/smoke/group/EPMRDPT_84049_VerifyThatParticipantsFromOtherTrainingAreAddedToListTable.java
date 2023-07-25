package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84049_VerifyThatParticipantsFromOtherTrainingAreAddedToListTable",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_84049_VerifyThatParticipantsFromOtherTrainingAreAddedToListTable {

  private static final String TRAINING_NAME_FOR_ADD_STUDENTS = "AutoTest_AddManyStudentsFromAnotherTraining";
  private static final String TRAINING_NAME_WITH_STUDENTS = "AutoTest_StudentsForAnotherTraining";
  private static final String GROUP_NAME = "AddStudentFromAnotherTraining";
  private static final String FIRST_STUDENT_NAME = "Auto test user first";
  private static final String SECOND_STUDENT_NAME = "Autotest user second";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingGroup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded();
    reactGroupDetailsScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME_FOR_ADD_STUDENTS)
        .clickGroupsTabs()
        .waitDataLoading()
        .clickGroupByName(GROUP_NAME);
    if (reactGroupDetailsScreen.isParticipantsDisplayedShortTimeOut()) {
      reactTrainingManagementService.deleteAllParticipantsFromReactGroup();
    }
  }

  @Test(priority = 1)
  public void verifyThatTrainingNameIsDisplayed() {
    reactGroupDetailsScreen
        .clickCancelTrainingIconInParticipantsSection()
        .clickTrainingNameInSelectTrainingDDL(TRAINING_NAME_WITH_STUDENTS)
        .waitAllSpinnersAreNotDisplayed();
    assertTrue(
        reactGroupDetailsScreen
            .isTrainingNameDisplayed(TRAINING_NAME_WITH_STUDENTS),
        "Training name isn't displayed");
  }

  @Test(priority = 2)
  public void verifyThatCheckboxesForSelectedStudentsAreChecked() {
    softAssert = new SoftAssert();
    reactGroupDetailsScreen
        .clickSelectParticipantDropDown()
        .clickStudentNameInSelectParticipantDropDownByName(FIRST_STUDENT_NAME);
    softAssert.assertTrue(
        reactGroupDetailsScreen.isCheckboxByStudentNameInDropDownChecked(FIRST_STUDENT_NAME),
        String.format("Checkbox for '%s' is unchecked", FIRST_STUDENT_NAME));
    reactGroupDetailsScreen
        .clickStudentNameInSelectParticipantDropDownByName(SECOND_STUDENT_NAME);
    softAssert.assertTrue(
        reactGroupDetailsScreen.isCheckboxByStudentNameInDropDownChecked(SECOND_STUDENT_NAME),
        String.format("Checkbox for '%s' is unchecked", SECOND_STUDENT_NAME));
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifyThatStudentsAddedToGroupIsDisplayed() {
    softAssert = new SoftAssert();
    reactGroupDetailsScreen
        .clickAddParticipantButton()
        .waitDataLoading();
    softAssert.assertTrue(reactGroupDetailsScreen
            .isStudentByNameDisplayed(FIRST_STUDENT_NAME),
        String.format("Student with name '%s' is not displayed", FIRST_STUDENT_NAME));
    softAssert.assertTrue(reactGroupDetailsScreen
            .isStudentByNameDisplayed(SECOND_STUDENT_NAME),
        String.format("Student with name '%s' is not displayed", SECOND_STUDENT_NAME));
    softAssert.assertAll();
  }
}
