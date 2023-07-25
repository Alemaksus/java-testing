package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_IMPOSSIBLE_DELETE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactOnlineTaskInfoPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_50956_VerifyThatUserCannotDeleteOnlineTaskIfItsStatusIsNotUnassignedInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50956_VerifyUserCannotDeleteOnlineTaskIfStatusIsNotUnassignedInTaskJournal {

  private static final String TASK_NAME_PATTERN = "AutoTest_React_OnlineTask";
  private final String groupName = "AutoTest_GroupWithEverydayClasses";

  private ReactOnlineTaskInfoPopUpScreen reactOnlineTaskInfoPopUpScreen;

  @DataProvider(name = "Provider of online tasks")
  public static Object[][] provideOnlineTasks() {
    return new Object[][]{
        {TASK_NAME_PATTERN + " Assigned Status"},
        {TASK_NAME_PATTERN + " Submitted Status"},
        {TASK_NAME_PATTERN + " Passed WithMark"},
        {TASK_NAME_PATTERN + " Expired Status"},
        {TASK_NAME_PATTERN + " Rejected Status"},
        {TASK_NAME_PATTERN + " Reassigned Status"}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTaskJournal() {
    reactOnlineTaskInfoPopUpScreen = new ReactOnlineTaskInfoPopUpScreen();
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactHeaderScreen()
        .waitTrainingTabForVisibility();
    new ReactTrainingService()
        .openTaskJournalByGroupName(groupName);
  }

  @Test(priority = 1, dataProvider = "Provider of online tasks")
  public void checkDeleteIconDisabled(String onlineTaskName) {
    new ReactTasksJournalScreen()
        .waitTableHeaderForVisibility()
        .clickAllPeriodButton()
        .clickOnlineTaskInTaskJournalByName(onlineTaskName)
        .mouseOverDeleteTaskButton();
    assertFalse(reactOnlineTaskInfoPopUpScreen.isDeleteIconEnabled(),
        "'Delete icon' on 'Online task info' pop up isn't disabled!");
  }

  @Test(priority = 2)
  public void checkDeleteIconTooltip() {
    assertEquals(reactOnlineTaskInfoPopUpScreen.getTooltipButtonText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_IMPOSSIBLE_DELETE),
        "'Delete icon' tooltip on the 'Online task info pop up' has incorrect text!");
  }
}
