package com.epmrdpt.smoke.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.screens.ReactTrainingTaskScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39914_VerifyThatUserCanOpenTasksTabOnTheTrainingTabPage",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_39914_VerifyThatUserCanOpenTasksTabOnTheTrainingTabPage {

  private ReactTrainingTaskScreen reactTrainingTaskScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTasksTab() {
    reactTrainingTaskScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility()
        .clickTasksButton();
  }

  @Test
  public void checkTasksOnTabDisplayed() {
    assertTrue(reactTrainingTaskScreen.isAllTasksOnTasksTabDisplayed(),
        "Tasks on the 'Tasks' tab aren't displayed!");
  }

  @Test
  public void checkTasksButtonHighlighted() {
    assertTrue(new ReactTrainingScreen().isTasksButtonHighlighted(),
        "Active 'Tasks' button has incorrect color!");
  }
}
