package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13120_VerifyNavigationOnSchedule",
    groups = {"full", "student", "regression"})
public class EPMRDPT_13120_VerifyNavigationOnSchedule {

  private static final String HIGHLIGHTED_COLOR_RGBA = "rgba(118, 206, 217, 1)";
  private static final int WEEK_START_INDEX = 0;
  private static final int WEEK_END_INDEX = 6;
  private static final String START_TIME = "08:00";
  private static final String END_TIME = "23:00";
  private static final String DATE_PATTERN_LONG_FORMAT = "d MMMM yyyy";

  private HomeTabOnLearningPageScreen homeTabOnLearningPageScreen;
  private LocalDate todayDate;
  private String todayDateAccordingToLocaleInString;
  private Locale locale;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    locale = new Locale(LanguageSwitchingService.getLocaleLanguage().getLanguageCode());
    todayDate = LocalDate.now();
    todayDateAccordingToLocaleInString = DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT)
        .withLocale(locale).format(LocalDate.now());
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asLearningStudent())
        .waitLinksToRedirectOnOtherSectionsDisplayed();
    homeTabOnLearningPageScreen = new HomeTabOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkHomeTabOfLearningPageIsOpened() {
    assertTrue(new HeaderScreen().clickLearningButton().isScreenLoaded(),
        "Learning page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkCurrentMonthIsDisplayed() {
    String displayedMonth = homeTabOnLearningPageScreen.clickMonthTabInCalendar()
        .getDisplayedDateInCalendar().split(" ")[1];
    assertEquals(displayedMonth, todayDateAccordingToLocaleInString.split(" ")[1],
        "Current month isn't displayed in month tab!");
  }

  @Test(priority = 3)
  public void checkMonthsAreDisplayedInChronologicalOrder() {
    softAssert = new SoftAssert();
    String nextMonth = homeTabOnLearningPageScreen.clickNextButtonInCalendar()
        .getDisplayedDateInCalendar().split(" ")[1];
    softAssert.assertEquals(nextMonth,
        todayDate.plusMonths(1).getMonth().getDisplayName(TextStyle.FULL, locale),
        "Next month isn't displayed!");
    String currentMonth = homeTabOnLearningPageScreen.clickPreviousButtonInCalendar()
        .getDisplayedDateInCalendar().split(" ")[1];
    softAssert
        .assertEquals(currentMonth, todayDate.getMonth().getDisplayName(TextStyle.FULL, locale),
            "Previous month isn't displayed");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTodayDateIsMarkedInCurrentMonth() {
    softAssert = new SoftAssert();
    String displayedDateInCalendar = homeTabOnLearningPageScreen.clickTodayInCalendar()
        .getDisplayedDateInCalendar();
    softAssert.assertEquals(displayedDateInCalendar.split(" ")[1],
        todayDate.getMonth().getDisplayName(TextStyle.FULL, locale),
        "Current month isn't displayed!");
    softAssert.assertEquals(homeTabOnLearningPageScreen
            .getColorOfTextOfDayOfMonth(displayedDateInCalendar.split(" ")[0]), HIGHLIGHTED_COLOR_RGBA,
        "Current date text isn't highlighted in calendar!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkCurrentWeekTabIsOpened() {
    softAssert = new SoftAssert();
    LocalDate weekStartDate = todayDate.minusDays(todayDate.getDayOfWeek().getValue() - 1);
    String weekStartDateFullFormat = StringUtils
        .getDateStringWithFormattedMonth(weekStartDate, TextStyle.FULL);
    String weekEndDateFullFormat = StringUtils
        .getDateStringWithFormattedMonth(weekStartDate.plusDays(WEEK_END_INDEX), TextStyle.FULL);
    List<String> weekDates = homeTabOnLearningPageScreen.clickWeekTabInCalendar()
        .getWeekDatesTextInCalendar();
    softAssert.assertTrue(weekStartDateFullFormat
            .contains(weekDates.get(WEEK_START_INDEX).replaceAll(".", "")),
        "Week start date isn't displayed correctly!");
    softAssert.assertTrue(weekEndDateFullFormat
            .contains(weekDates.get(WEEK_END_INDEX).replaceAll(".", "")),
        "Week end date isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkWeeksAreDisplayedChronologically() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(
        homeTabOnLearningPageScreen.clickNextButtonInCalendar().getDisplayedDateInCalendar(),
        todayDate.plusDays(7).format(DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT)
            .withLocale(locale)), "Next week isn't displayed!");
    softAssert.assertEquals(
        homeTabOnLearningPageScreen.clickPreviousButtonInCalendar().getDisplayedDateInCalendar(),
        todayDateAccordingToLocaleInString, "Previous week isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkCurrentDateIsMarkedInCurrentWeek() {
    softAssert = new SoftAssert();
    LocalDate weekStartDate = todayDate.minusDays(todayDate.getDayOfWeek().getValue() - 1);
    List<String> weekDates = homeTabOnLearningPageScreen.clickTodayInCalendar()
        .getWeekDatesTextInCalendar();
    for (int weekIndex = WEEK_START_INDEX; weekIndex <= WEEK_END_INDEX; weekIndex++) {
      softAssert.assertTrue(StringUtils
              .getDateStringWithFormattedMonth(weekStartDate.plusDays(weekIndex), TextStyle.FULL)
              .contains(weekDates.get(weekIndex).replaceAll(".", "")),
          String.format("Week date of %s isn't displayed correctly!",
              weekStartDate.plusDays(weekIndex)));
    }
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkDayTabIsOpened() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(
        homeTabOnLearningPageScreen.clickDayTabInCalendar().getDisplayedDateInCalendar(),
        DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT).withLocale(locale).format(todayDate));
    List<String> hoursInDayTab = homeTabOnLearningPageScreen.getHoursTextInDayTab();
    softAssert
        .assertEquals(hoursInDayTab.get(0), START_TIME, "Start time isn't displayed correctly!");
    softAssert.assertEquals(hoursInDayTab.get(hoursInDayTab.size() - 1), END_TIME,
        "End time isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkDaysAreDisplayedInChronologicalOrder() {
    softAssert = new SoftAssert();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT)
        .withLocale(locale);
    softAssert.assertEquals(
        homeTabOnLearningPageScreen.clickNextButtonInCalendar().getDisplayedDateInCalendar(),
        dateTimeFormatter.format(todayDate.plusDays(1)), "Next day isn't displayed correctly!");
    softAssert.assertEquals(
        homeTabOnLearningPageScreen.clickPreviousButtonInCalendar().getDisplayedDateInCalendar(),
        dateTimeFormatter.format(todayDate), "Previous day isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 10)
  public void checkCurrentDateIsDisplayed() {
    assertEquals(homeTabOnLearningPageScreen.clickTodayInCalendar().getDisplayedDateInCalendar(),
        DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT).withLocale(locale).format(todayDate),
        "Current date isn't displayed correctly!");
  }
}
