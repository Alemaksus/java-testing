package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39929_VerifyThatUserCanOpenPopUpWithSubmittedTaskOnTheMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39929_VerifyThatUserCanOpenPopUpWithSubmittedTaskOnTheMyScheduleTab {

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactTrainingScreenInitialization() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
  }

  @Test
  public void checkThatPopUpWithSubmittedTaskDisplayed() {
    assertTrue(reactTrainingScreen
            .clickCheckButton()
            .isScreenLoaded(),
        "Pop-up window with home task doesn't displayed!");
  }
}
