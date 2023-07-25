package com.epmrdpt.smoke.training;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44776_VerifyTheUserCanOpenTaskJournalTabForGroupInTheGroupJournal",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_44776_VerifyTheUserCanOpenTaskJournalTabForGroupInTheGroupJournal {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44776_VerifyTheUserCanOpenTaskJournalTabForGroupInTheGroupJournal(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void signInAndWaitTrainingScreenToLoad() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
  }

  @Test
  public void checkThatUserOpenTaskJournalTabForGroupInTheGroupJournal() {
    ReactTrainingService reactTrainingService = new ReactTrainingService();
    String groupNameFromGroupJournal = reactTrainingService
        .navigateToMyGroupsTabAndSearchGroup(groupName).getGroupNameFromGroupJournalText();
    String groupNameFromTasksJournal = reactTrainingService.getGroupNameFromTasksJournalText();
    assertEquals(groupNameFromTasksJournal, groupNameFromGroupJournal,
        String.format("'Task Journal' tab for %s group isn't opened!", groupNameFromTasksJournal));
  }
}
