package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingTaskScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44737_VerifyThatUserCanOpenOnlineTaskInfoPopoverOnTasksCalendarAtDayView",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44737_VerifyThatUserCanOpenOnlineTaskInfoPopoverOnTasksCalendarAtDayView {

  private ReactTrainingTaskScreen reactTrainingTaskScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTasksTab() {
    reactTrainingTaskScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility()
        .clickTasksButton();
  }

  @Test
  public void checkThatTaskInfoPopUpDisplayed() {
    assertTrue(reactTrainingTaskScreen.clickTasksOnTasksTab()
        .isScreenLoaded(), "Task info pop-up is not displayed!");
  }
}
