package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39918_VerifyClassNameInTheMonthViewScheduleOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39918_VerifyClassNameInTheMonthViewScheduleOnMyScheduleTab {

  private final String className = "AutoTest_ClassDurationMore45Min";

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupMonthScheduleTab() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
    new ReactTrainingService()
        .openMonthViewSchedule();
  }

  @Test
  public void checkThatTheNameOfClassIsDisplayed() {
    assertTrue(reactTrainingScreen.isClassNameInMonthScheduleTabDisplayed(className),
        format("Class ticket with '%s' name on 'Month Schedule' tab isn't displayed!", className));
  }

  @Test
  public void checkThatClassNameLengthLessThanWidthOfSchedule() {
    assertTrue(reactTrainingScreen.getMonthScheduleCellWidth() >=
            reactTrainingScreen.getClassNameWidth(className),
        format("'%s' class name on 'Month Schedule' tab has incorrect length", className));
  }
}
