package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_DEPARTMENT_AFFILIATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_DATE_AND_TIME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_TYPE_OF_CLASS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_TYPE_OF_TRAINING;

import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.screens.LessonDetailPopUpScreenOnLearningPageScreen;
import com.epmrdpt.screens.ListTabScreenOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13123_VerifyThatLessonsTimeTopicsAreSeenOnListTab",
    groups = {"full", "student", "regression"})
public class EPMRDPT_13123_VerifyThatLessonsTimeTopicsAreSeenOnListTab {

  private static final String TOPIC_FOR_EPAM_TRAINING = "AutoTest_AssignedOnlineTask";
  private static final String TOPIC_FOR_DEPARTMENT_AFFILIATE = "AutoTestDATopic";
  private static String departmentName = "Web-technologies and computer simulation";
  private static String facultyName = "Faculty of Mechanics and Mathematics";
  private static String locationName = "Location";
  private static String dAGroupName = "AutoTestGroupd";
  private static String eTGroupName = "AutoTest_LearningStudent_DOTNET_Tasks";

  private ListTabScreenOnLearningPageScreen listTabScreenOnLearningPageScreen;
  private LessonDetailPopUpScreenOnLearningPageScreen lessonDetailPopUpScreenOnLearningPageScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(asLearningStudent())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickLearningButton();
    lessonDetailPopUpScreenOnLearningPageScreen = new LessonDetailPopUpScreenOnLearningPageScreen();
    listTabScreenOnLearningPageScreen = new HomeTabOnLearningPageScreen()
        .clickListTab();
  }

  @Test(priority = 1)
  public void checkClassListTabDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(listTabScreenOnLearningPageScreen.isClassDateHeaderDisplayed(),
        "Class date heading isn't displayed!");
    softAssert.assertTrue(listTabScreenOnLearningPageScreen.isLessonNameHeaderDisplayed(),
        "Class name heading isn't displayed!");
    softAssert.assertTrue(listTabScreenOnLearningPageScreen.isTopicHeaderDisplayed(),
        "Topic heading isn't displayed!");
    softAssert.assertTrue(listTabScreenOnLearningPageScreen.isTypeOfClassHeaderDisplayed(),
        "Type of class heading isn't displayed!");
    new HomeTabOnLearningPageScreen().clickEpamLearningTab();
    softAssert.assertTrue(listTabScreenOnLearningPageScreen.isGroupNameHeaderDisplayed(),
        "Group name heading isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkHeaderOfClassDetailsForDepartmentAffiliate() {
    softAssert = new SoftAssert();
    listTabScreenOnLearningPageScreen.clickLessonTopicByName(TOPIC_FOR_DEPARTMENT_AFFILIATE);
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Lesson details pop up isn't opened!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTopicNameDisplayed(),
        "Lesson name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getTrainingTypeText()
            .contains(getValueOf(APPLICATIONS_DEPARTMENT_AFFILIATE)),
        "Department affiliate text is not correct!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isTrainingOrClassNameDisplayed(),
        "Class name isn't displayed in lesson details pop up!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTrainerDataOfClassDetailsForDepartmentAffiliate() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainersNameDisplayed(),
        "Trainer name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isTrainersProfileImageDisplayed(),
        "Trainer profile image isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isUserRoleDisplayed(),
        "User's role isn't displayed in lesson details pop up!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkDateAndTimeOfClassDetailsForDepartmentAffiliate() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isDateAndTimeDisplayed(),
        "Date and time isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getDateAndTimeText()
            .contains(getValueOf(LESSON_DETAIL_POP_UP_DATE_AND_TIME)),
        "Date and time text is not correct!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkDepartmentAndFacultyDataOfClassDetailsForDepartmentAffiliate() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isDepartmentTitleInDALessonDisplayed(),
        "Department isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getDepartmentInputText()
        .contains(departmentName), "Department name is not correct!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isFacultyTitleInDALessonDisplayed(),
        "Faculty isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getFacultyInputText()
        .contains(facultyName), "Faculty name is not correct!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkAdditionalClassDetailsForDepartmentAffiliate() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTypeOfClassDisplayed(),
        "Type of class isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getTypeOfClassInputText()
            .contains(getValueOf(LESSON_DETAIL_POP_UP_TYPE_OF_CLASS)),
        "Type of class name is not correct!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isGroupTitleInDALessonDisplayed(),
        "Group isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getGroupInputText()
        .contains(dAGroupName), "Group name is not correct!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isLocationTitleInDALessonDisplayed(),
        "Location isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getLocationInputText()
        .contains(locationName), "Location name is not correct!");
    softAssert.assertFalse(lessonDetailPopUpScreenOnLearningPageScreen.clickCrossButtonOfPopUp()
        .isPopUpTitleDisplayed(), "Lesson details pop up isn't closed!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkHeaderOfClassDetailsForEpamTraining() {
    softAssert = new SoftAssert();
    listTabScreenOnLearningPageScreen.clickLessonTopicByName(TOPIC_FOR_EPAM_TRAINING);
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Lesson details pop up isn't opened!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTopicNameDisplayed(),
        "Lesson name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getTrainingTypeText()
            .contains(getValueOf(LESSON_DETAIL_POP_UP_TYPE_OF_TRAINING)),
        "Epam training text is not correct!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isTrainingOrClassNameDisplayed(),
        "Training name isn't displayed in lesson details pop up!");
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkTrainerDataOfClassDetailsForEpamTraining() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isTrainersNameDisplayed(),
        "Trainer name isn't displayed in lesson details pop up!");
    softAssert.assertTrue(
        lessonDetailPopUpScreenOnLearningPageScreen.isTrainersProfileImageDisplayed(),
        "Trainer profile image isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isUserRoleDisplayed(),
        "User's role isn't displayed in lesson details pop up!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkDateAndTimeOfClassDetailsForEpamTraining() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isDateAndTimeDisplayed(),
        "Date and time isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getDateAndTimeText()
            .contains(getValueOf(LESSON_DETAIL_POP_UP_DATE_AND_TIME)),
        "Date and time text is not correct!");
    softAssert.assertAll();
  }

  @Test(priority = 10)
  public void checkAdditionalClassDetailsForForEpamTraining() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonGroupDisplayed(),
        "Group isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getGroupInputText()
        .contains(eTGroupName), "Group name is not correct!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getTypeOfClassInputText()
            .contains(getValueOf(LESSON_DETAIL_POP_UP_TYPE_OF_CLASS)),
        "Type of class name is not correct!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.isLessonLocationDisplayed(),
        "Location isn't displayed in lesson details pop up!");
    softAssert.assertTrue(lessonDetailPopUpScreenOnLearningPageScreen.getLocationInputText()
        .contains(locationName), "Location name is not correct!");
    softAssert.assertFalse(lessonDetailPopUpScreenOnLearningPageScreen.clickCrossButtonOfPopUp()
        .isPopUpTitleDisplayed(), "Lesson details pop up isn't closed!");
    softAssert.assertAll();
  }
}
