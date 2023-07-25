package com.epmrdpt.smoke.learning;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_26566_VerifyTheContentOfLisTabFromCalendarTabsSectionOfHomeTabOfLearningPage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_26566_VerifyTheContentOfLisTabFromCalendarTabsSectionOfHomeTabOfLearningPage {

  private HomeTabOnLearningPageScreen homeScreenFromLearningScreen;
  private SoftAssert softAssert;
  private String patternDate = "dd.MM.yyyy HH:mm";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    homeScreenFromLearningScreen = new HomeTabOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatStudentHasAccessLearningScreen() {
    assertTrue(new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asStudentForLearningPage())
        .clickLearningButton().isScreenLoaded(), "Learning screen isn't loaded!");
  }

  @Test(priority = 3)
  public void checkThatHeaderColumnLessonDateDisplayed() {
    assertTrue(
        homeScreenFromLearningScreen.clickListRadioButton().isHeaderColumnLessonDateDisplayed(),
        "Lesson date header isn't displayed!");
  }

  @Test(priority = 4)
  public void checkThatHeaderColumnLessonNameDisplayed() {
    assertTrue(homeScreenFromLearningScreen.isHeaderColumnLessonNameDisplayed(),
        "Lesson name header isn't displayed!");
  }

  @Test(priority = 4)
  public void checkThatHeaderColumnGroupNameDisplayed() {
    assertTrue((homeScreenFromLearningScreen.isHeaderColumnGroupNameDisplayed()),
        "Group name header isn't displayed!");
  }

  @Test(priority = 4)
  public void checkThatLessonsHaveCorrectDateFormat() {
    softAssert = new SoftAssert();
    for (int lessonIndex = 0; lessonIndex < homeScreenFromLearningScreen.getQuantityLessons();
        lessonIndex++) {
      softAssert.assertTrue(StringUtils
              .isDateMatchExpectedPattern(
                  homeScreenFromLearningScreen.getLessonDateByIndex(lessonIndex),
                  patternDate),
          format("Lesson %s has incorrect date format, expected pattern %s, actual %s",
              homeScreenFromLearningScreen.getLessonNameByIndex(lessonIndex), patternDate,
              homeScreenFromLearningScreen.getLessonDateByIndex(lessonIndex)));
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatLessonsHaveLessonName() {
    softAssert = new SoftAssert();
    for (int lessonIndex = 0; lessonIndex < homeScreenFromLearningScreen.getQuantityLessons();
        lessonIndex++) {
      softAssert
          .assertTrue(!homeScreenFromLearningScreen.getLessonNameByIndex(lessonIndex).isEmpty(),
              format("Lesson name %d is empty!", lessonIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatLessonsHaveGroupName() {
    for (int lessonIndex = 0; lessonIndex < homeScreenFromLearningScreen.getQuantityLessons();
        lessonIndex++) {
      softAssert
          .assertTrue(!homeScreenFromLearningScreen.getGroupNameFromLessonListByIndex(lessonIndex)
                  .isEmpty(),
              format("Lesson %d hasn't group name!", lessonIndex + 1));
    }
    softAssert.assertAll();
  }
}
