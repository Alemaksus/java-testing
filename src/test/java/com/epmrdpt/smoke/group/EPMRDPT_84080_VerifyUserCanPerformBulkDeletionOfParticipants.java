package com.epmrdpt.smoke.group;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84080_VerifyUserCanPerformBulkDeletionOfParticipants",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_84080_VerifyUserCanPerformBulkDeletionOfParticipants {

  private static final String TRAINING_NAME = "AutoTest_StaffedStatus";
  private static final String GROUP_NAME = "GroupForAddDeleteParticipants";
  private static final String FIRST_STUDENT_NAME = "Auto test user first";
  private static final String SECOND_STUDENT_NAME = "Autotest user second";
  private static final String THIRD_STUDENT_NAME = "Autotest user third";
  private final int countOfParticipantsAfterDeleteSelected = 1;
  private final User user;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84080_VerifyUserCanPerformBulkDeletionOfParticipants(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingGroup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded();
    reactGroupDetailsScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickGroupsTabs()
        .waitDataLoading()
        .clickGroupByName(GROUP_NAME);
    if (reactGroupDetailsScreen.isParticipantsDisplayedShortTimeOut()) {
      reactTrainingManagementService.deleteAllParticipantsFromReactGroup();
    }
    reactGroupDetailsScreen.waitAllSpinnersAreNotDisplayed()
        .clickSelectParticipantDropDown()
        .waitPopperVisibility()
        .clickSelectAllStudentsButton()
        .clickAddParticipantButton()
        .waitDataLoading()
        .clickOnResultOfChangeMessageCross();
  }

  @Test(priority = 1)
  public void verifyCheckboxesIsChecked() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen
            .clickParticipantCheckboxByName(SECOND_STUDENT_NAME)
            .isCheckboxByStudentNameChecked(SECOND_STUDENT_NAME),
        String.format("Checkbox for '%s' is unchecked", SECOND_STUDENT_NAME));
    softAssert.assertTrue(reactGroupDetailsScreen
            .clickParticipantCheckboxByName(THIRD_STUDENT_NAME)
            .isCheckboxByStudentNameChecked(THIRD_STUDENT_NAME),
        String.format("Checkbox for '%s' is unchecked", THIRD_STUDENT_NAME));
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyDeleteButtonIsDisplayed() {
    assertTrue(reactGroupDetailsScreen.clickSettingsInParticipantSectionButton()
        .isDeleteButtonDisplayed(), "'Delete' button doesn't displayed!");
  }

  @Test(priority = 3)
  public void verifyThatPopUpAppearsAfterClickingOnTheDelete() {
    assertTrue(
        reactGroupDetailsScreen.clickDeleteAllParticipantsButton().isStatusChangePopUpDisplayed(),
        "Pop up window doesn't appears after clicking delete!");
  }

  @Test(priority = 4)
  public void verifyThatChangesSavedSuccessfullyPopUpDisplayed() {
    assertTrue(
        reactGroupDetailsScreen.clickDeleteButtonInPopUp().isResultOfChangeMessageDisplayed(),
        "Changes saved successfully pop up isn't displayed!");
  }

  @Test(priority = 5)
  public void verifyThatDeletedStudentsNotDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen
            .isStudentByNameDisplayed(FIRST_STUDENT_NAME),
        String.format("Student with name '%s' is not displayed", FIRST_STUDENT_NAME));
    softAssert.assertEquals(reactGroupDetailsScreen.getStudentsCountInStudentInfoSection(),
        countOfParticipantsAfterDeleteSelected, "Participants count is not correct!");
    softAssert.assertAll();
  }
}
