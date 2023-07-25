package com.epmrdpt.smoke.training_management;

import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77730_VerifyThatTheReviewersFieldIsPrefilledWithAnIndividualReviewer"
    + "ForASingleAssignmentOfAnOnlineTaskToAStudentIfStudentHasIndividualMentor",
    groups = {"full", "smoke"})
public class EPMRDPT_77730_VerifyThatTheReviewersFieldIsPrefilledWithAnIndividualReviewerForASingleAssignmentOfAnOnlineTaskToAStudentIfStudentHasIndividualMentor {

  private static final String TRAINING_NAME = "AutoTest_GroupWithEverydayClasses";
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private final User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_77730_VerifyThatTheReviewersFieldIsPrefilledWithAnIndividualReviewerForASingleAssignmentOfAnOnlineTaskToAStudentIfStudentHasIndividualMentor(
      User user) {
    this.user = user;
  }

  @BeforeClass
  public void loginToJournalTab() {
    reactTasksJournalScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingScreenLink()
        .clickMyGroupsTab()
        .typeGroupNameInput(TRAINING_NAME)
        .clickSearchIcon()
        .switchToGroupJournal(TRAINING_NAME)
        .switchToTaskJournalTab()
        .waitGroupJournalTableForVisibility();
  }

  @Test
  public void checkReviewersFieldIsNotEmpty() {
    reactTasksJournalScreen.clickNotAssignedStatusButton().isAddAssignmentPopUpDisplayed();
    assertFalse(reactTasksJournalScreen.checkReviewersFieldText(),
        "'Reviewer' is not assigned!");
  }
}
