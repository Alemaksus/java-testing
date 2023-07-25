package com.epmrdpt.regression.training;

import static com.epmrdpt.utils.IntegerUtils.getCurrentDayOfMonth;
import static com.epmrdpt.utils.StringUtils.getCurrentDayOfWeekValue;
import static com.epmrdpt.utils.StringUtils.getTimeEvery30MinutesListByFromAndTo;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_39916_VerifyThatAllElementsOfTheDayViewModeOfTheScheduleComponentIsPresentedOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39916_VerifyThatAllElementsOfTheDayViewModeOfTheScheduleIsPresentedOnMyScheduleTab {

  private final String startOfScheduleTime = "08:00";
  private final String endOfScheduleTime = "23:00";

  private ReactTrainingScreen reactTrainingScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(UserFactory.asTrainer())
        .waitScheduleForVisibility();
  }

  @Test
  public void checkCalendarGridInSchedule() {
    assertTrue(reactTrainingScreen.isCalendarGridInScheduleDisplayed(),
        "'Calendar grid' at 'Schedule' isn't displayed!");
  }

  @Test
  public void checkFirstColumnInSchedule() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingScreen.isTimeGridInFirstColumnAtScheduleDisplayed(),
        "First column with Time slots at 'Schedule' isn't displayed!");
    softAssert.assertEquals(reactTrainingScreen.getTimeSlotInScheduleList(),
        getTimeEvery30MinutesListByFromAndTo(startOfScheduleTime, endOfScheduleTime),
        "Time slot at 'Schedule' isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test
  public void checkFirstRowInSchedule() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactTrainingScreen.getDayOfMonthInScheduleValue(),
        getCurrentDayOfMonth(),
        "'Day of month' field in the first row at 'Schedule' isn't displayed correctly!");
    softAssert.assertEquals(reactTrainingScreen.getDayOfWeekInScheduleText(),
        getCurrentDayOfWeekValue(),
        "'Day of week' field in the first row at 'Schedule' isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test
  public void checkCurrentTimeLineInScheduleDisplayed() {
    assertTrue(reactTrainingScreen.isCurrentTimeLineInScheduleDisplayed(),
        "'Current time line' at 'Schedule' isn't displayed!");
  }
}
