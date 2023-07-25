package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_SKILL_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TYPE_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_CURRENT_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_DATES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_GROUP_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_SELECT_DATE_ACTION_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_SELECT_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_TRAINING_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK;
import static com.epmrdpt.utils.StringUtils.getTimeEvery30MinutesListByFromAndTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactFooterScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.screens.ReactTrainingTaskScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79520_VerifyTrainingTabContent",
    groups = {"full", "react", "regression"})
public class EPMRDPT_79520_VerifyTrainingTabContent {

  private ReactHeaderScreen reactHeaderScreen;
  private ReactTrainingScreen reactTrainingScreen;
  private SoftAssert softAssert;
  private ReactFooterScreen reactFooterScreen;
  private ReactTrainingTaskScreen reactTrainingTaskScreen;
  private final String startOfScheduleTime = "08:00";
  private final String endOfScheduleTime = "23:00";
  private Pattern datePattern = Pattern
      .compile("(0[1-9]|[12][0-9]|3[01])[\\.](0[1-9]|1[012])[\\.](19|20)\\d\\d");
  private final int quantityOfDaysInAWeek = 7;
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79520_VerifyTrainingTabContent(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingScreen() {
    reactTrainingTaskScreen = new ReactTrainingTaskScreen();
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkHeaderContent() {
    softAssert = new SoftAssert();
    reactHeaderScreen = new ReactHeaderScreen();
    assertTrue(reactHeaderScreen.isLanguageDropdownDisplayed(),
        "Language dropdown isn't displayed on the training page!");
    assertTrue(reactHeaderScreen.isCogwheelButtonDisplayed(),
        "Setting button isn't displayed on the training page!");
    assertTrue(reactHeaderScreen.isUserInfoToggleDisplayed(),
        "Profile Photo and drop down arrow are not displayed on the training page!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatCalendarPresentOnTrainingPage() {
    assertTrue(reactTrainingScreen.isCalendarDisplayed(),
        "Calendar isn't displayed on the training page!");
  }

  @Test(priority = 2)
  public void checkScheduleSectionContent() {
    softAssert = new SoftAssert();
    assertTrue(reactTrainingScreen.isCalendarIconInScheduleDisplayed(),
        "'Calendar Icon' isn't displayed on the training page!");
    assertEquals(reactTrainingScreen.getTodayFieldText(),
        getValueOf(REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT),
        "'Today' button has incorrect text!");
    assertTrue(reactTrainingScreen.isLeftArrowButtonInScheduleDisplayed(),
        "'Left arrow button' isn't displayed on the training page!");
    assertTrue(reactTrainingScreen.isRightArrowButtonInScheduleDisplayed(),
        "'Right arrow button' isn't displayed on the training page!");
    assertEquals(reactTrainingScreen.getMonthAndYearInCalendarText(),
        reactTrainingScreen.getMonthAndYearInScheduleText(),
        "'Month and year' field has incorrect text!");
    assertEquals(reactTrainingScreen.getScheduleDayViewModeText(),
        getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY), "'Day' field has incorrect text!");
    assertTrue(reactTrainingScreen.isViewArrowButtonDownDirectionByDefaultInScheduleDisplayed(),
        "'View arrow' button in down direction by default isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkGroupTabFiltersContent() {
    reactTrainingScreen.clickMyGroupsTab();
    softAssert = new SoftAssert();
    assertTrue(reactTrainingScreen.isMyGroupsSearchInputDisplayed(),
        "Search By Name Input isn't displayed on 'My Groups' screen!");
    assertTrue(reactTrainingScreen.isAdvancedSearchButtonDisplayed(),
        "Advanced search button isn't displayed on 'My Groups' screen!");
    reactTrainingScreen.clickAdvancedSearchButton();
    assertEquals(reactTrainingScreen.getAdvancedSearchElementsText(), advancedSearchElementsList,
        "'Advanced search elements' in group filter have incorrect text!");
    reactTrainingScreen
        .clickSelectDateAdvancedSearchInput()
        .clickOneOfDayFromCalendar();
    assertTrue(
        datePattern.matcher(reactTrainingScreen.getSelectDateAdvancedSearchInputValue()).find(),
        "Correct Date Format isn't displayed on 'My Groups' screen!");
    assertTrue(reactTrainingScreen.isOnlyMyGroupsCheckBoxDisplayed(),
        "'Only my Groups' checkbox isn't displayed on 'My Groups' screen!");
    assertTrue(reactTrainingScreen.isApplyButtonDisplayed(),
        "'Apply' button isn't displayed on 'My Groups' screen!");
    assertTrue(reactTrainingScreen.isResetButtonDisplayed(),
        "Reset' button isn't displayed on 'My Groups' screen!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkGroupListTableContent() {
    softAssert = new SoftAssert();
    assertEquals(reactTrainingScreen.getHeaderGroupsText(), headerGroupsList,
        "'Header Groups' in group list table has incorrect text!");
    assertTrue(reactTrainingScreen.isLocationIconDisplayed(),
        "'Location Icon' in group list table isn't displayed!");
    assertTrue(reactTrainingScreen.isJournalIconDisplayed(),
        "'Journal Icon' in group list table isn't displayed!");
    assertTrue(reactTrainingScreen.isPageNumbersDisplayed(),
        "'Pagination block' in group list table isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkScheduleTableContent() {
    softAssert = new SoftAssert();
    reactTrainingScreen.clickMyScheduleTab();
    assertTrue(reactTrainingScreen.isCalendarIconInScheduleDisplayed(),
        "'Calendar Icon' isn't displayed in 'Schedule' table!");
    assertEquals(reactTrainingScreen.getTodayFieldText(),
        getValueOf(REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT),
        "'Today' button has incorrect text in 'Schedule' table!");
    assertTrue(reactTrainingScreen.isLeftArrowButtonInScheduleDisplayed(),
        "'Left arrow button' isn't displayed in 'Schedule' table!");
    assertTrue(reactTrainingScreen.isRightArrowButtonInScheduleDisplayed(),
        "'Right arrow button' isn't displayed in 'Schedule' table!");
    assertEquals(reactTrainingScreen.getMonthAndYearInCalendarText(),
        reactTrainingScreen.getMonthAndYearInScheduleText(),
        "'Month and year' field has incorrect text in 'Schedule' table!");
    assertTrue(reactTrainingScreen.isCurrentDayInScheduleHighlightedInBlue(),
        "Current day of month in 'Schedule' table isn't highlighted in blue by default!");
    assertFalse(reactTrainingScreen.getCurrentDayOfWeekInScheduleText().isEmpty(),
        "Current day of week in 'Schedule' table isn't displayed!");
    reactTrainingScreen.clickDownViewArrowButtonInSchedule();
    assertEquals(reactTrainingScreen.getElementsOfPeriodFromDDLText(), elementsOfPeriodFromDDLList,
        "'Elements of period from DDL' in 'Schedule' table have incorrect text!");
    assertEquals(reactTrainingScreen.getTimeSlotInScheduleList(),
        getTimeEvery30MinutesListByFromAndTo(startOfScheduleTime, endOfScheduleTime),
        "Time slot in 'Schedule' table isn't displayed correctly!");
    assertTrue(reactTrainingScreen.isCurrentTimeLineInScheduleDisplayed(),
        "Current time line in 'Schedule' table isn't displayed!");
    assertTrue(reactTrainingScreen.isDownloadMyScheduleButtonDisplayed(),
        "'Download my schedule' button in in 'Schedule' table isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkTasksTabDayModeContent() {
    reactTrainingScreen.clickTasksButton();
    softAssert = new SoftAssert();
    assertTrue(reactTrainingScreen.isCalendarIconInScheduleDisplayed(),
        "'Calendar Icon' isn't displayed in 'Tasks' table and 'Day' mode!");
    assertEquals(reactTrainingScreen.getTodayFieldText(),
        getValueOf(REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT),
        "'Today' button has incorrect text in 'Tasks' table and 'Day' mode!");
    assertTrue(reactTrainingScreen.isLeftArrowButtonInScheduleDisplayed(),
        "'Left arrow button' isn't displayed in 'Tasks' table and 'Day' mode!");
    assertTrue(reactTrainingScreen.isRightArrowButtonInScheduleDisplayed(),
        "'Right arrow button' isn't displayed in 'Tasks' table and 'Day' mode!");
    assertEquals(reactTrainingScreen.getMonthAndYearInCalendarText(),
        reactTrainingScreen.getMonthAndYearInScheduleText(),
        "'Month and year' field has incorrect text in 'Tasks' table and 'Day' mode!");
    assertEquals(reactTrainingScreen.getScheduleDayViewModeText(),
        getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY),
        "'Day' field has incorrect text in 'Tasks' table!");
    reactTrainingScreen.clickDownViewArrowButtonInSchedule();
    assertEquals(reactTrainingScreen.getElementsOfPeriodFromDDLText(), elementsOfPeriodFromDDLList,
        "'Elements of period from DDL' in 'Tasks' table have incorrect text!");
    reactTrainingScreen.clickElementOfPeriodFromDDL(
        getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK));
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkTasksTabWeekModeContent() {
    softAssert = new SoftAssert();
    assertTrue(reactTrainingScreen.isCalendarIconInScheduleDisplayed(),
        "'Calendar Icon' isn't displayed in 'Tasks' table and 'Week' mode!");
    assertEquals(reactTrainingScreen.getTodayFieldText(),
        getValueOf(REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT),
        "'Today' button has incorrect text in 'Tasks' table and 'Week' mode!");
    assertTrue(reactTrainingScreen.isLeftArrowButtonInScheduleDisplayed(),
        "'Left arrow button' isn't displayed in 'Tasks' table and 'Week' mode!");
    assertTrue(reactTrainingScreen.isRightArrowButtonInScheduleDisplayed(),
        "'Right arrow button' isn't displayed in 'Tasks' table and 'Week' mode!");
    assertEquals(reactTrainingScreen.getMonthAndYearInCalendarText(),
        reactTrainingScreen.getMonthAndYearInScheduleText(),
        "'Month and year' field has incorrect text in 'Tasks' table and 'Week' mode!");
    assertEquals(reactTrainingScreen.getScheduleDayViewModeText(),
        getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK),
        "'Week' field has incorrect text in 'Tasks' table and 'Week' mode!");
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkTasksTabSubHeaderWeekModeContent() {
    softAssert = new SoftAssert();
    assertEquals(reactTrainingTaskScreen.getDaysWeekModeInTaskScheduleSize(),
        quantityOfDaysInAWeek,
        "Quantity of days in a week isn't correct in 'Tasks' table and 'Week' mode!");
    assertFalse(reactTrainingTaskScreen.getDaysOfWeekTextInTaskScheduleWeekMode().isEmpty(),
        "There are not all days of week displayed in 'Tasks' table and 'Week' mode!");
    assertFalse(reactTrainingTaskScreen.getDatesOfWeekTextInTaskScheduleWeekMode().isEmpty(),
        "There are not all dates of week displayed in 'Tasks' table and 'Week' mode!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkTasksTabMonthModeContent() {
    softAssert = new SoftAssert();
    new ReactTrainingService().openMonthViewSchedule();
    assertTrue(reactTrainingScreen.isCalendarIconInScheduleDisplayed(),
        "'Calendar Icon' isn't displayed in 'Tasks' table and 'Month' mode!");
    assertEquals(reactTrainingScreen.getTodayFieldText(),
        getValueOf(REACT_TRAINING_SCHEDULE_CALENDAR_TODAY_TEXT),
        "'Today' button has incorrect text in 'Tasks' table and 'Month' mode!");
    assertTrue(reactTrainingScreen.isLeftArrowButtonInScheduleDisplayed(),
        "'Left arrow button' isn't displayed in 'Tasks' table and 'Month' mode!");
    assertTrue(reactTrainingScreen.isRightArrowButtonInScheduleDisplayed(),
        "'Right arrow button' isn't displayed in 'Tasks' table and 'Month' mode!");
    assertEquals(reactTrainingScreen.getMonthAndYearInCalendarText(),
        reactTrainingScreen.getMonthAndYearInScheduleText(),
        "'Month and year' field has incorrect text in 'Tasks' table and 'Month' mode!");
    assertEquals(reactTrainingScreen.getScheduleDayViewModeText(),
        getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH),
        "'Month' field has incorrect text in 'Tasks' table and 'Month' mode!");
    softAssert.assertAll();
  }

  @Test(priority = 10)
  public void checkTasksTabSubHeaderMonthModeContent() {
    softAssert = new SoftAssert();
    assertEquals(reactTrainingTaskScreen.getDaysMonthModeInTaskScheduleSize(),
        quantityOfDaysInAWeek,
        "Quantity of days in a week isn't correct in 'Tasks' table and 'Month' mode!");
    assertFalse(reactTrainingTaskScreen.getDaysOfWeekTextInTaskScheduleMonthMode().isEmpty(),
        "There are not all days of week displayed in 'Tasks' table and 'Month' mode!");
    assertTrue(reactTrainingTaskScreen.isMonthGridDisplayed(),
        "Month grid isn't displayed in 'Tasks' table and 'Month' mode!");
    softAssert.assertAll();
  }

  @Test(priority = 11)
  public void checkFooterContent() {
    reactFooterScreen = new ReactFooterScreen();
    softAssert = new SoftAssert();
    assertEquals(reactFooterScreen.getCopyrightSignText(),
        LocaleProperties.getValueOf(LocalePropertyConst.FOOTER_COPYRIGHT_SIGN_TEXT),
        "Copyright sign in the footer isn't displayed correctly!");
    assertTrue(reactFooterScreen.isAllNavigationLinksDisplayed(),
        "Some navigation links in the footer aren't displayed!");
    assertTrue(reactFooterScreen.isTwitterLinkDisplayed(),
        "Twitter link in the footer isn't displayed!");
    assertTrue(reactFooterScreen.isFacebookLinkDisplayed(),
        "Facebook link in the footer isn't displayed!");
    assertTrue(reactFooterScreen.isLinkedInLinkDisplayed(),
        "LinkedIn link in the footer isn't displayed!");
    softAssert.assertAll();
  }

  private List<String> headerGroupsList = new ArrayList<>(Arrays.asList(
      getValueOf(REACT_TRAINING_MY_GROUPS_TAB_TRAINING_NAME),
      getValueOf(REACT_TRAINING_MY_GROUPS_TAB_GROUP_NAME),
      getValueOf(REACT_TRAINING_MY_GROUPS_TAB_DATES),
      getValueOf(REACT_TRAINING_MY_GROUPS_TAB_CURRENT_STATUS)));

  private List<String> advancedSearchElementsList = new ArrayList<>(Arrays.asList(
      getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE),
      getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE),
      getValueOf(REACT_TRAINING_MANAGEMENT_SKILL_DROPDOWN_DEFAULT_VALUE),
      getValueOf(REACT_TRAINING_MY_GROUPS_TAB_SELECT_STATUS),
      getValueOf(REACT_TRAINING_MY_GROUPS_TAB_SELECT_DATE_ACTION_INPUT),
      getValueOf(REACT_TRAINING_MANAGEMENT_TYPE_DROPDOWN_DEFAULT_VALUE)));

  private List<String> elementsOfPeriodFromDDLList = new ArrayList<>(Arrays.asList(
      getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_DAY),
      getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_WEEK),
      getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH)));
}
