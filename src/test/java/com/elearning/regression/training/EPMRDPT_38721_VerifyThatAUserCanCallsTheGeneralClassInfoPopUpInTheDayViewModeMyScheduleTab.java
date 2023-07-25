package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactLessonInfoPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38721_VerifyThatAUserCanCallsTheGeneralClassInfoPopUpInTheDayViewModeMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38721_VerifyThatAUserCanCallsTheGeneralClassInfoPopUpInTheDayViewModeMyScheduleTab {

  private final String classTopic = "AutoTest_EditClass";

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactTrainingScreenInitialization() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
  }

  @Test(priority = 1)
  public void isLessonInfoDisplayed() {
    assertTrue(reactTrainingScreen
        .clickInScheduleClassCardByTopic(classTopic)
        .isLessonInfoDisplayed(), "General class info PopUp doesn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatExpectedLessonTopicTextIsDisplayed() {
    assertEquals(new ReactLessonInfoPopUpScreen().getLessonTopicText(),
        classTopic, "General info PopUp opened for wrong class!");
  }
}
