package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.utils.IntegerUtils.getCurrentDayOfMonth;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38713_VerifyThatTheCurrentDateIsCircledInBlueByDefaultAtCalendarOnTrainingPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38713_VerifyThatTheCurrentDateIsCircledInBlueByDefaultAtCalendarOnTrainingPage {

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
  }

  @Test(priority = 1)
  public void checkSelectedDayAtCalendar() {
    assertEquals(reactTrainingScreen.getSelectedDayInCalendarValue(), getCurrentDayOfMonth(),
        "Selected day of month at 'Calendar' isn't appropriate to the current day!");
  }

  @Test(priority = 2)
  public void checkCurrentDayHighlightedInBlueByDefaultAtCalendar() {
    assertTrue(reactTrainingScreen.isSelectedDayHighlightedInBlue(),
        "Current day of month at 'Calendar' isn't highlighted in blue by default!");
  }
}
