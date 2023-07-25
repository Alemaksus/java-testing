package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.screens.LessonDetailPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LoginService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13122_VerifyViewOfLessonTimeAndTopicIsSeenOnWeekTab",
    groups = {"full", "student", "regression"})
public class EPMRDPT_13122_VerifyViewOfLessonTimeAndTopicIsSeenOnWeekTab {

  private static final LocalDate ET_LESSON_DATE = LocalDate.of(2021, 5, 3);
  private static final LocalDate DA_LESSON_DATE = LocalDate.of(2021, 5, 14);
  private static final String TOPIC_FOR_EPAM_TRAINING = "AutoTest_AssignedOnlineTask";
  private static final String TOPIC_FOR_DEPARTMENT_AFFILIATE = "AutoTestDATopic";
  private static final String DATE_PATTERN_LONG_FORMAT = "d MMMM yyyy";

  private HomeTabOnLearningPageScreen homeTabOnLearningPageScreen;
  private LessonDetailPopUpScreenOnLearningPageScreen lessonDetailPopUpScreenOnLearningPageScreen;
  private Locale locale;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    locale = new Locale(LanguageSwitchingService.getLocaleLanguage().getLanguageCode());
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asLearningStudent())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickLearningButton();
    homeTabOnLearningPageScreen = new HomeTabOnLearningPageScreen();
    lessonDetailPopUpScreenOnLearningPageScreen = new LessonDetailPopUpScreenOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkHomeTabOfLearningPageIsOpened() {
    assertTrue(homeTabOnLearningPageScreen.isScreenLoaded(),
        "Home tab of learning page isn't opened!");
  }

  @Test(priority = 2)
  public void checkLessonForEpamTrainingIsDisplayedForScheduledWeek() {
    SoftAssert softAssert = new SoftAssert();
    String displayedDate = homeTabOnLearningPageScreen.clickMonthTabInCalendar()
        .getDisplayedDateInCalendar();
    homeTabOnLearningPageScreen.goToScheduledLessonMonth(LocalDate
                .parse(displayedDate,
                    DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT).withLocale(locale)),
            ET_LESSON_DATE).clickDayOfMonthDisplayedInCalendar(ET_LESSON_DATE.getDayOfMonth())
        .clickWeekTabInCalendar();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isTopicNameDisplayed(TOPIC_FOR_EPAM_TRAINING),
        "Epam training topic isn't displayed!");
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isTimeDisplayedForTopicByTopicName(TOPIC_FOR_EPAM_TRAINING),
        "Epam training topic time isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkLessonForDepartmentAffiliateIsDisplayedForScheduledWeek() {
    String displayedDate = homeTabOnLearningPageScreen.clickMonthTabInCalendar()
        .getDisplayedDateInCalendar();
    homeTabOnLearningPageScreen.goToScheduledLessonMonth(LocalDate
                .parse(displayedDate,
                    DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT).withLocale(locale)),
            DA_LESSON_DATE).clickDayOfMonthDisplayedInCalendar(DA_LESSON_DATE.getDayOfMonth())
        .clickWeekTabInCalendar();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isTopicNameDisplayed(TOPIC_FOR_DEPARTMENT_AFFILIATE),
        "Department training topic isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen
            .isTimeDisplayedForTopicByTopicName(TOPIC_FOR_DEPARTMENT_AFFILIATE),
        "Department training topic time isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkLessonDetailsForDepartmentAffiliate() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(homeTabOnLearningPageScreen.clickTopicByName(TOPIC_FOR_DEPARTMENT_AFFILIATE)
            .isPopUpTitleDisplayed(), "Lesson details pop up isn't opened!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTopicNameDisplayed(),
        "Lesson name isn't displayed in lesson details pop up!");
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainingOrClassNameDisplayed(),
            "Class name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainersNameDisplayed(),
        "Trainer name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isDateAndTimeDisplayed(),
        "Date and time isn't displayed in lesson details pop up!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isDepartmentTitleInDALessonDisplayed(),
        "Department isn't displayed in lesson details pop up!");
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isFacultyTitleInDALessonDisplayed(),
            "Faculty isn't displayed in lesson details pop up!");
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isGroupTitleInDALessonDisplayed(),
            "Group isn't displayed in lesson details pop up!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isLocationTitleInDALessonDisplayed(),
        "Location isn't displayed in lesson details pop up!");
    softAssert.assertFalse(lessonDetailPopUpScreenOnLearningPageScreen.clickCrossButtonOfPopUp()
        .isPopUpTitleDisplayed(), "Lesson details pop up isn't closed!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkLessonDetailsPopUpForEpamTraining() {
    softAssert = new SoftAssert();
    String displayedDate = homeTabOnLearningPageScreen.clickMonthTabInCalendar()
        .getDisplayedDateInCalendar();
    homeTabOnLearningPageScreen.goToScheduledLessonMonth(LocalDate
                .parse(displayedDate,
                    DateTimeFormatter.ofPattern(DATE_PATTERN_LONG_FORMAT).withLocale(locale)),
            ET_LESSON_DATE).clickDayOfMonthDisplayedInCalendar(ET_LESSON_DATE.getDayOfMonth())
        .clickWeekTabInCalendar();
    softAssert.assertTrue(homeTabOnLearningPageScreen.clickTopicByName(TOPIC_FOR_EPAM_TRAINING)
        .isPopUpTitleDisplayed(), "Lesson details pop up isn't opened!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTopicNameDisplayed(),
        "Lesson name isn't displayed in lesson details pop up!");
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainingOrClassNameDisplayed(),
            "Training name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainersNameDisplayed(),
        "Trainer name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isDateAndTimeDisplayed(),
        "Date and time isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonGroupDisplayed(),
        "Group isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonLocationDisplayed(),
        "Location isn't displayed in lesson details pop up!");
    softAssert.assertFalse(lessonDetailPopUpScreenOnLearningPageScreen.clickCrossButtonOfPopUp()
        .isPopUpTitleDisplayed(), "Lesson details pop up isn't closed!");
    softAssert.assertAll();
  }
}
