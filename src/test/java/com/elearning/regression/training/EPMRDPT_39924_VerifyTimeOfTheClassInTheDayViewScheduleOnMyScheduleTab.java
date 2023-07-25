package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39924_VerifyTimeOfTheClassInTheDayViewScheduleOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39924_VerifyTimeOfTheClassInTheDayViewScheduleOnMyScheduleTab {

  private final String className = "AutoTest_ClassDurationMore60Min";
  private final Pattern timePattern = Pattern.compile(
      "^([01]?[0-9]|2[0-3]):[0-5][0-9] - ([01]?[0-9]|2[0-3]):[0-5][0-9]$");

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupScheduleTab() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
  }

  @Test(priority = 1)
  public void checkClassTicketOnScheduleTabDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketInScheduleByNameDisplayed(className),
        format("Class ticket with '%s' name on 'Schedule' tab isn't displayed!", className));
  }

  @Test(priority = 2)
  public void checkClockIconInClassTicketDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketClockIconByNameDisplayed(className),
        "Clock Icon in class ticket isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTimeFormatInClassTicket() {
    assertTrue(
        timePattern.matcher(reactTrainingScreen.getClassTicketTimeByNameText(className)).find(),
        "Time in class ticket has incorrect format!");
  }
}
