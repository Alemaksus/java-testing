package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactOnlineTaskInfoPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44740_VerifyThatUserCanOpenCheckPopUpWindowFromTaskInfoPopoverInTheTaskCalendar",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44740_VerifyThatUserCanOpenCheckPopUpWindowFromTaskInfoPopoverInTheTaskCalendar {

  private ReactOnlineTaskInfoPopUpScreen reactOnlineTaskInfoPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTaskInTasksTab() {
    ReactTrainingScreen reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab()
        .waitTasksSectionForVisibility();
    int taskIndex = new ReactTrainingService()
        .getFirstTaskIndexContainsDeadlineDateFromSchedule();
    String taskName = reactTrainingScreen.getTaskNameInTaskTicketByIndex(taskIndex);
    String taskDeadLine = reactTrainingScreen.getDateOfDeadlineTaskByIndex(taskIndex);
    reactOnlineTaskInfoPopUpScreen = reactTrainingScreen
        .clickMyScheduleTab()
        .waitScheduleForVisibility()
        .clickTasksButton()
        .clickTasksOnTasksTabByNameAndDeadLine(taskName, taskDeadLine)
        .waitOnlineTaskNameOnPopUpVisibility()
        .waitEditTaskButtonForVisibility();
  }

  @Test
  public void checkThatHomeTaskPageOpensFromTaskInfoPopUp() {
    assertTrue(reactOnlineTaskInfoPopUpScreen
            .moveTaskInfoPopUpToTheTopOfTheScreen()
            .clickCheckButton()
            .isScreenLoaded(),
        "Pop-up window with home task doesn't displayed!");
  }
}
