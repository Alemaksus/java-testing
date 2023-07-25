package com.epmrdpt.regression.training;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39923_VerifyThatCurrentTimeLineMarksTheCurrentTimeOnTheDayViewScheduleOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39923_VerifyThatCurrentTimeLineMarksTheCurrentTimeOnTheDayViewScheduleOnMyScheduleTab {

  private final int maxValidDifferenceInSecond = 90;
  private static final String COLOR_RED_TIMELINE = "rgba(230, 76, 0, 1)";
  private final LocalTime beginningOfDaySchedule = LocalTime.of(8, 0, 1);
  private final LocalTime endingOfDaySchedule = LocalTime.of(22, 59, 59);

  private User user;
  private ReactTrainingScreen reactTrainingScreen;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_39923_VerifyThatCurrentTimeLineMarksTheCurrentTimeOnTheDayViewScheduleOnMyScheduleTab(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupScheduleTabWithDayView() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitScheduleForVisibility();
  }

  @Test
  public void checkCurrentTimeLineInScheduleDisplayed() {
    assertTrue(reactTrainingScreen.isCurrentTimeLineInScheduleDisplayed(),
        "'Current time line' on 'Day view schedule' isn't displayed!");
  }

  @Test
  public void checkColorOfCurrentTimeLineInSchedule() {
    assertEquals(reactTrainingScreen.getCurrentTimeLineColor(), COLOR_RED_TIMELINE,
        "'Current time line' on 'Day view schedule' has incorrect color!");
  }

  @Test
  public void checkTimeMarkedByCurrentTimeLineInSchedule() {
    LocalTime currentTime = LocalTime.now();
    if (currentTime.isBefore(beginningOfDaySchedule) || currentTime.isAfter(endingOfDaySchedule)) {
      fail("System issue - current time out of valid range!");
    }
    LocalTime timeFromSchedule = new ReactTrainingService().getTimeFromSchedule();
    assertTrue(Math.abs(
            ChronoUnit.SECONDS.between(timeFromSchedule, currentTime)) < maxValidDifferenceInSecond,
        format(
            "'Current time line' on 'Day view schedule' marks wrong time: %s , but current time is: %s !",
            timeFromSchedule, currentTime));
  }
}
