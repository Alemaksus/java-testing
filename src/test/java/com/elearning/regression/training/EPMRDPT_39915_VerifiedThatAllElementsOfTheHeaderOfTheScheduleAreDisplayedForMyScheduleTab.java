package com.epmrdpt.regression.training;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39915_VerifiedThatAllElementsOfTheHeaderOfTheScheduleComponentAreDisplayedForMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39915_VerifiedThatAllElementsOfTheHeaderOfTheScheduleAreDisplayedForMyScheduleTab {

  private ReactTrainingScreen reactTrainingScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_39915_VerifiedThatAllElementsOfTheHeaderOfTheScheduleAreDisplayedForMyScheduleTab(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility();
  }

  @Test
  public void checkCalendarIconInScheduleDisplayed() {
    assertTrue(reactTrainingScreen.isCalendarIconInScheduleDisplayed(),
        "'Calendar icon' of the header at 'Schedule' isn't displayed!");
  }

  @Test
  public void checkTodayFieldInSchedule() {
    assertEquals(reactTrainingScreen.getTodayFieldText(),
        LocaleProperties.getValueOf(
            LocalePropertyConst.REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT),
        "'Today' field of the header at 'Schedule' has incorrect text!");
  }

  @Test
  public void checkLeftArrowButtonInScheduleDisplayed() {
    assertTrue(reactTrainingScreen.isLeftArrowButtonInScheduleDisplayed(),
        "'Left arrow button' of the header at 'Schedule' isn't displayed!");
  }

  @Test
  public void checkRightArrowButtonInScheduleDisplayed() {
    assertTrue(reactTrainingScreen.isRightArrowButtonInScheduleDisplayed(),
        "'Right arrow button' of the header at 'Schedule' isn't displayed!");
  }

  @Test
  public void checkMonthAndYearFieldInSchedule() {
    assertFalse(reactTrainingScreen.getMonthAndYearInScheduleText().isEmpty(),
        "'Month and year' field of the header at 'Schedule' is empty!");
  }

  @Test
  public void checkDayViewModeInScheduleOpenByDefault() {
    assertEquals(reactTrainingScreen.getScheduleDayViewModeText(),
        LocaleProperties.getValueOf(LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY),
        "'Day' view mode of the header at 'Schedule' isn't opened by default!");
  }

  @Test
  public void checkViewArrowButtonDownDirectionByDefaultInScheduleDisplayed() {
    assertTrue(reactTrainingScreen.isViewArrowButtonDownDirectionByDefaultInScheduleDisplayed(),
        "'View arrow' button of the header at 'Schedule' in down direction by default isn't displayed!");
  }
}
