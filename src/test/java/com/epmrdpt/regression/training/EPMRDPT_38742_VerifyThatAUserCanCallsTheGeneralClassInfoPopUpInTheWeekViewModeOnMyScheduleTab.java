package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK;
import static com.epmrdpt.utils.StringUtils.getDayOfWeekValues;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactLessonInfoPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.utils.RandomUtils;
import java.util.Calendar;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_38742_VerifyThatAUserCanCallsTheGeneralClassInfoPopUpInTheWeekViewModeOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38742_VerifyThatAUserCanCallsTheGeneralClassInfoPopUpInTheWeekViewModeOnMyScheduleTab {

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(UserFactory.asTrainer())
        .waitScheduleForVisibility()
        .waitCalendarForVisibility()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkScheduleViewModeSelectionDisplayed() {
    assertTrue(reactTrainingScreen.clickViewArrowButtonInSchedule()
            .waitScheduleViewModeSelectionForVisibility().isScheduleViewModeSelectionDisplayed(),
        "'Schedule view mode selection' on the 'My schedule' isn't displayed!");
  }

  @Test(priority = 2)
  public void checkWeekViewModeOnSchedule() {
    assertEquals(reactTrainingScreen.clickScheduleWeekViewModeField()
            .getScheduleDayViewModeText(),
        LocaleProperties.getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK),
        "'Week' view mode isn't set!");
  }

  @Test(priority = 3)
  public void checkDaysOfWeekInWeekViewMode() {
    SoftAssert softAssert = new SoftAssert();
    String[] expectedDaysOfWeek = getDayOfWeekValues();
    for (int i = 1; i < Calendar.DAY_OF_WEEK_IN_MONTH; i++) {
      softAssert.assertTrue(reactTrainingScreen
              .isDayOfWeekInScheduleWeekViewModeDisplayed(expectedDaysOfWeek[i]
                  .replace("'", "ʼ").toUpperCase()),
          format("Day of week '%s' in 'Week' view mode isn't displayed correctly!",
              expectedDaysOfWeek[i]));
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatGeneralInfoPopUpInWeekViewModeDisplayed() {
    int numberOfClasses = reactTrainingScreen.waitUntilAllClassesInWeeklyScheduleDisplayed()
        .getClassInScheduleCount();
    int randomNumberOfClass = RandomUtils.getRandomNumber(numberOfClasses);
    assertTrue(
        reactTrainingScreen.clickClassInScheduleByIndex(randomNumberOfClass).isScreenLoaded(),
        "'General class info' pop up at 'Schedule' in week view mode isn't displayed!");
  }

  @Test(priority = 5)
  public void checkThatSelectedClassHasCorrectName() {
    String expectedClassName = reactTrainingScreen.getClassNameInSelectedEvent();
    assertEquals(new ReactLessonInfoPopUpScreen().waitLessonTopicForVisibility()
            .getLessonTopicText(), expectedClassName,
        "Selected class at 'Schedule' in week view mode has incorrect class name!");
  }
}
