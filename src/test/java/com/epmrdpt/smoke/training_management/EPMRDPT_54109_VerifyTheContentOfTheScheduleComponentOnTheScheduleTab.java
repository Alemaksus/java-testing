package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactScheduleTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.EColorUtils;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_54109_VerifyTheContentOfTheScheduleComponentOnTheScheduleTab",
    groups = {"smoke", "full"})

public class EPMRDPT_54109_VerifyTheContentOfTheScheduleComponentOnTheScheduleTab {

  private final static String GROUP_NAME = "AutoTest_GroupWithEverydayClasses";
  private final String activePeriodButtonColour = EColorUtils.BLUE_COLOUR.getColorRgbaFormat();
  private LocalDate currentDate = LocalDate.now();
  private ReactScheduleTabScreen reactScheduleTabScreen;
  private SoftAssert softAssert;
  private User user;

  @Factory(dataProvider = "Provider of users with React Training permissions",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_54109_VerifyTheContentOfTheScheduleComponentOnTheScheduleTab(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void switchToTrainingSchedule() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingScreenLink()
        .clickMyGroupsTab();
    reactScheduleTabScreen = new ReactTrainingService()
        .uncheckOnlyMyGroupCheckBox()
        .waitPageNumbersForVisibility()
        .typeGroupNameInput(GROUP_NAME)
        .clickSearchIcon()
        .waitPageNumbersForDisappear()
        .switchToGroupJournal(GROUP_NAME)
        .switchToScheduleTab()
        .waitScheduleTableForVisibility();
  }

  @Test(priority = 1)
  public void checkCalendarIconVisibility() {
    assertTrue(reactScheduleTabScreen.isCalendarIconDisplayed(),
        "'Calendar' Icon is not displayed!");
  }

  @Test(priority = 2)
  public void checkButtonText() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactScheduleTabScreen.getDayButtonText(),
        getValueOf(REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT),
        "The text on the 'Day' Button is incorrect!");
    softAssert.assertEquals(reactScheduleTabScreen.getViewModeText(),
        getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY),
        "The text on the 'ViewMode' Button is incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkDateValues() {
    softAssert = new SoftAssert();
    String expectedDate =
        getValueOf(currentDate.getMonth().toString()).toUpperCase() + " " + currentDate.getYear();
    String expectedDayOfWeek = getValueOf(currentDate.getDayOfWeek().toString()).toUpperCase();
    softAssert.assertEquals(reactScheduleTabScreen.getMonthButtonText(), expectedDate,
        "The 'Month and Year' is not displayed correctly!");
    softAssert.assertEquals(reactScheduleTabScreen.getDayOfWeekButtonText(), expectedDayOfWeek,
        "The 'Day' of Week is not displayed correctly!");
    softAssert.assertEquals(reactScheduleTabScreen.getDayValueButtonText(),
        String.valueOf(currentDate.getDayOfMonth()),
        "The 'Date' is not displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkArrowButtonsVisibility() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactScheduleTabScreen.isLeftArrowButtonDisplayed(),
        "'Left' Arrow button is not displayed!");
    softAssert.assertTrue(reactScheduleTabScreen.isRightArrowButtonDisplayed(),
        "'Right' Arrow button is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkButtonColor() {
    softAssert.assertEquals(reactScheduleTabScreen.getDayButtonColour(),
        activePeriodButtonColour,
        "The 'Week Period' button colour is not blue!");
  }

  @Test(priority = 6)
  public void checkListOfViewMode() {
    assertEquals(reactScheduleTabScreen.clickViewModeDDL().getListElementsOfViewMode(),
        getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY) + ", " +
            getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK) + ", " +
            getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH),
        "The 'View Mode DDL' List is incorrect!");
  }

  @Test(priority = 7)
  public void checkElementsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactScheduleTabScreen.isScheduleComponentFieldDisplayed(),
        "The 'Schedule Component' Field is not displayed!");
    softAssert.assertTrue(reactScheduleTabScreen.isCurrentTimeIndicatorDisplayed(),
        "The 'Current Time Indicator' is not displayed!");
    softAssert.assertTrue(reactScheduleTabScreen.isActiveClassTicketDisplayed(),
        "The 'Active Class Ticket' Field is not displayed!");
  }
}
