package com.epmrdpt.regression.training;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77726_VerifyThatTheReviewerColumnIsDisplayedInTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_77726_VerifyThatTheReviewerColumnIsDisplayedInTaskJournal {

  private User user;
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_77726_VerifyThatTheReviewerColumnIsDisplayedInTaskJournal(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTaskJournalTab() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitScheduleForVisibility()
        .clickMyGroupsTab();
    reactTasksJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(groupName)
        .clickTaskJournalTab()
        .waitGroupJournalTableForVisibility()
        .waitTableHeaderForVisibility();
  }

  @Test
  public void checkIfReviewerColumnIsDisplayed() {
    assertTrue(reactTasksJournalScreen.isReviewerColumnDisplayed(),
        "Reviewer column isn't displayed!");
  }
}
