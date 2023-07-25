package com.epmrdpt.smoke.learning;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_DEPARTMENT_AFFILIATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_TYPE_OF_TRAINING;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.LessonDetailPopUpScreenOnLearningPageScreen;
import com.epmrdpt.screens.ListTabScreenOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import java.util.Random;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPRMDPT_13257_VerifyUserCanSeeLessonsDetails",
    groups = {"full", "student", "smoke"})
public class EPRMDPT_13257_VerifyUserCanSeeLessonsDetails {

  private Pattern timePattern = Pattern
      .compile("([0-2][0-9]):([0-6][0-9])\\s-\\s([0-2][0-9]):([0-6][0-9])");
  private Pattern datePattern = Pattern
      .compile("(0[1-9]|[12][0-9]|3[01])[\\.](0[1-9]|1[012])[\\.](19|20)\\d\\d");
  private SoftAssert softAssert;
  private HeaderScreen headerScreen;
  private ListTabScreenOnLearningPageScreen listTabFromHomeTabFromLearningPageScreen;
  private LessonDetailPopUpScreenOnLearningPageScreen lessonDetailPopUpScreenOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    listTabFromHomeTabFromLearningPageScreen = new ListTabScreenOnLearningPageScreen();
    lessonDetailPopUpScreenOnLearningPageScreen = new LessonDetailPopUpScreenOnLearningPageScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asStudentForLearningPage());
  }

  @Test(priority = 2)
  public void checkLessonDetailPopUpAppearsAfterClickTopicName() {
    headerScreen.clickLearningButton().waitEpamTrainingTitleVisibility();
    int index = new Random()
        .nextInt(listTabFromHomeTabFromLearningPageScreen.getLessonCalendarListSize());
    assertTrue(
        listTabFromHomeTabFromLearningPageScreen.clickLessonCalendarByIndex(index).isScreenLoaded(),
        "Lesson detail pop -up isn't displayed after clicking Topic name!");
  }

  @Test(priority = 3)
  public void checkPopUpContainsLessonDetailTitle() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonDetailTitleDisplayed(),
            "Lesson detail pop-up isn't contained 'Lesson detail' title!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.getLessonDetailTitleText().equalsIgnoreCase(
            getValueOf(LocalePropertyConst.LESSON_DETAIL_POP_UP_LESSON_DETAILS_TITLE)),
        "'Lesson detail' title hasn't correct text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkPopUpContainsTrainingName() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isTrainingOrClassNameDisplayed(),
        "Lesson detail pop-up isn't contained training name!");
    if (lessonDetailPopUpScreenOnLearningPageScreen.isDepartmentTitleInDALessonDisplayed()) {
      softAssert.assertEquals(lessonDetailPopUpScreenOnLearningPageScreen.getTrainingTypeText(),
          getValueOf(APPLICATIONS_DEPARTMENT_AFFILIATE),
          "Training title isn't correct for 'Department affiliate' type!");
    } else {
      softAssert.assertEquals(lessonDetailPopUpScreenOnLearningPageScreen.getTrainingTypeText(),
          getValueOf(LESSON_DETAIL_POP_UP_TYPE_OF_TRAINING),
          "Training title isn't correct for 'EPAM Training' type!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkPopUpContainsTopicName() {
    assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTopicNameDisplayed(),
        "Lesson detail pop-up isn't contained topic name!");
  }

  @Test(priority = 3)
  public void checkPopUpContainsTrainingType() {
    assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainingTypeDisplayed(),
        "Lesson detail pop-up isn't contained training type!");
  }

  @Test(priority = 3)
  public void checkPopUpContainsDateAndTime() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isDateAndTimeDisplayed(),
            "Lesson detail pop-up isn't contained 'Lesson detail' title!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.getDateAndTimeText().contains(
            getValueOf(LocalePropertyConst.LESSON_DETAIL_POP_UP_DATE_AND_TIME)),
        "Date and time hasn't correct text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkPopUpContainsTrainersName() {
    assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainersNameDisplayed(),
        "Lesson detail pop-up isn't contained trainers name!");
  }

  @Test(priority = 3)
  public void checkPopUpContainsTrainersProfileImage() {
    assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainersProfileImageDisplayed(),
        "Lesson detail pop-up isn't contained trainers profile image!");
  }

  @Test(priority = 4)
  public void checkPopUpContainsUserRole() {
    assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isUserRoleDisplayed(),
        "Lesson detail pop-up isn't contained user role!");
  }

  @Test(priority = 3)
  public void checkPopUpContainsGroupSubTitle() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isGroupSubtitleDisplayed(),
            "Lesson detail pop-up isn't contained 'Group' text!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.getGroupSubtitleText().equalsIgnoreCase(
            getValueOf(LocalePropertyConst.LESSON_DETAIL_POP_UP_GROUP)),
        "Group isn't correct!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkPopUpContainsLessonGroup() {
    if (lessonDetailPopUpScreenOnLearningPageScreen.isDepartmentTitleInDALessonDisplayed()) {
      assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonGroupForDAClassDisplayed(),
          "Lesson detail pop-up isn't contained lesson group!");
    } else {
      assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonGroupDisplayed(),
          "Lesson detail pop-up isn't contained lesson group!");
    }
  }

  @Test(priority = 3)
  public void checkPopUpContainsCorrectTimeFormat() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isActualTimeDisplayed(),
            "Lesson detail pop-up isn't contained lesson time!");
    softAssert.assertTrue(
        timePattern.matcher(lessonDetailPopUpScreenOnLearningPageScreen.getActualTimeText()).find(),
        "Time is displayed incorrectly!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkPopUpContainsCorrectLessonDateFormat() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonDateDisplayed(),
            "Lesson detail pop-up isn't contained lesson date!");
    softAssert.assertTrue(
        datePattern.matcher(lessonDetailPopUpScreenOnLearningPageScreen.getLessonDateText()).find(),
        "Date is displayed incorrectly!");
    softAssert.assertAll();
  }
}
