package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactLessonInfoPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38743_VerifyCallsTheGeneralClassInfoPopUpInMonthViewModeMyScheduleTab",
    groups = {"full", "react", "regression", "deprecated"})
public class EPMRDPT_38743_VerifyCallsTheGeneralClassInfoPopUpInMonthViewModeMyScheduleTab {

  private final String className = "AutoTest_EditClass";
  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupMonthScheduleTab() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
    new ReactTrainingService()
        .openMonthViewSchedule();
  }

  @Test(priority = 1)
  public void checkThatGeneralClassInfoPopUpInMonthViewModeDisplayed() {
    assertTrue(reactTrainingScreen
            .clickClassInMonthViewScheduleByName(className)
            .waitLessonInfoForVisibility()
            .isDeleteIconButtonDisplayed(),
        "General class info popup in Month view mode My schedule tab isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatClassNameHasCorrectText() {
    assertEquals(new ReactLessonInfoPopUpScreen().getLessonTopicText(), className,
        "General class info popup in Month view mode My schedule tab has incorrect name!");
  }
}
