package com.epmrdpt.smoke.learning;

import static com.epmrdpt.bo.user.UserFactory.asStudentForLearningPage;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HOME_NAVIGATION_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_HOME_SCREEN_ATTENDANCE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_HOME_SCREEN_AVERAGE_MARK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_HOME_SCREEN_AVERAGE_MARK_FOR_CLASSES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_HOME_SCREEN_TASKS_COMPLETED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_DEPARTMENT_AFFILIATE_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_EPAM_TRAINING_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TRAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASKS_BUTTON;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FeedbackScreen;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12973_VerifyContentOfTheLearningHomePage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_12973_VerifyContentOfTheLearningHomePage {

  private String taskCompletedTextRegex =
      getValueOf(LEARNING_HOME_SCREEN_TASKS_COMPLETED) + "\\s(0|([1-9])|([1-9][0-9])|100)%";
  private SoftAssert softAssert;
  private HeaderScreen headerScreen;
  private HomeTabOnLearningPageScreen homeTabOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    homeTabOnLearningPageScreen = new HomeTabOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkUserHasAccessLearningPage() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentForLearningPage());
    new HeaderScreen().clickLearningButton();
    assertTrue(new HomeTabOnLearningPageScreen().isScreenLoaded(),
        " User hasn't access to learning page!");
  }

  @Test(priority = 2)
  public void checkHeaderContent() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isTrainingListNavigationLinkDisplayed(),
        "University program logo isn't displayed!");
    softAssert.assertTrue(headerScreen.isNotificationButtonDisplayed(),
        "Ring bell icon isn't displayed!");
    softAssert.assertTrue(headerScreen.isProfilePhotoImageDisplayed(),
        "Profile photo isn't displayed!");
    softAssert.assertTrue(headerScreen.isArrowButtonDisplayed(),
        "Arrow Button isn't displayed!");
    softAssert.assertTrue(headerScreen.isLanguageControlDropdownDisplayed(),
        "Globe icon isn't displayed!");
    softAssert.assertTrue(headerScreen.isAboutNavigationLinkDisplayed(),
        "About isn't displayed!");
    softAssert.assertTrue(headerScreen.isTrainingListNavigationLinkDisplayed(),
        "Training list link isn't displayed!");
    softAssert.assertTrue(headerScreen.isBlogNavigationLinkDisplayed(),
        "Blog link isn't displayed!");
    softAssert.assertTrue(headerScreen.isFaqNavigationLinkDisplayed(),
        "Faq link isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkLearningLinkContainer() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isLearningContainerLinkDisplayed
        (getValueOf(HOME_NAVIGATION_LINK)), "'Home' link isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isLearningContainerLinkDisplayed
        (getValueOf(REACT_TRAINING_TASKS_BUTTON)), "'Tasks' link isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isLearningContainerLinkDisplayed
        (getValueOf(REACT_TRAINING_CLASS_TRAINER)), "'Trainers' link isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTabLinkContainer() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isEpamTrainingTabDisplayed(),
        "'EPAM Training' tab isn't displayed!");
    softAssert.assertEquals(homeTabOnLearningPageScreen.getEpamTrainingTabText(),
        getValueOf(LEARNING_TASK_TAB_EPAM_TRAINING_TAB),
        "'EPAM Training' tab has incorrect text!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isDepartmentAffiliateTabDisplayed(),
        "'Department Affiliate' tab isn't displayed!");
    softAssert.assertEquals(homeTabOnLearningPageScreen.getDepartmentAffiliateTabText(),
        getValueOf(LEARNING_TASK_TAB_DEPARTMENT_AFFILIATE_TAB),
        "'Department Affiliate' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkAverageMarkForClassesArea() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isAverageMarkForClassesAreaDisplayed(),
        "Average mark for classes area isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.getAverageMarkForClassesText()
            .equalsIgnoreCase(getValueOf(LEARNING_HOME_SCREEN_AVERAGE_MARK_FOR_CLASSES)),
        "'Average mark for classes' title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkAttendanceMarkArea() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isAttendanceMarkAreaDisplayed(),
        "Attendance mark area isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.getAttendanceMarkText()
            .contains(getValueOf(LEARNING_HOME_SCREEN_ATTENDANCE)),
        "'Attendance mark' title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkAverageMarkForTasksArea() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isAverageMarkForTasksAreaDisplayed(),
        "Average mark for tasks area isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.getAverageMarkForTasksText()
            .contains(getValueOf(LEARNING_HOME_SCREEN_AVERAGE_MARK)),
        "'Average mark for tasks' title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTaskCompletedArea() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isTaskCompletedAreaDisplayed(),
        "Task completed area isn't displayed!");
    softAssert.assertTrue(Pattern.compile(taskCompletedTextRegex)
            .matcher(homeTabOnLearningPageScreen.getTaskCompletedText()).find(),
        "'Task completed' title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkGroupInformation() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isGroupNameTitleIsDisplayed(),
        "Group name title isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isLocationTitleIsDisplayed(),
        "Location title isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isStartDateTitleIsDisplayed(),
        "Start date title isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isEndDateTitleIsDisplayed(),
        "End date title isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCalendarTabs() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isCalendarButtonDisplayed(),
        "'Calendar tab' isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isListButtonDisplayed(),
        "'List tab' isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkHomeTabHasCalendarTabsSection() {
    assertTrue(homeTabOnLearningPageScreen.isCalendarTabsSectionDisplayed(),
        "Home tab hasn't calendar tabs section!");
  }

  @Test(priority = 3)
  public void checkHomeTabHasFeedback() {
    assertTrue(new FeedbackScreen().isFeedbackButtonDisplayed(), "Home tab hasn't feedback!");
  }

  @Test(priority = 3)
  public void checkHomeTabHasFooterSection() {
    assertTrue(new FooterScreen().isFooterContainerDisplayed(), "Home tab hasn't footer!");
  }
}
