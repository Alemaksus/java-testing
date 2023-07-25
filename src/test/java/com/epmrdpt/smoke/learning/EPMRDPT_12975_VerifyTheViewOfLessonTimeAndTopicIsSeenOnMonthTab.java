package com.epmrdpt.smoke.learning;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12975_VerifyTheViewOfLessonTimeAndTopicIsSeenOnMonthTab",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_12975_VerifyTheViewOfLessonTimeAndTopicIsSeenOnMonthTab {

  private HomeTabOnLearningPageScreen homeTabOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    homeTabOnLearningPageScreen = new HomeTabOnLearningPageScreen();
    new LoginService().login(UserFactory.asStudentForLearningPage());
    new HeaderScreen().clickLearningButton().clickMonthTabInCalendar();
  }

  @Test
  public void checkLessonInfoInCalendar() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isEventTitleInCalendarDisplayed(),
        "Not all event titles are displayed!");
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isEventTimeInCalendarDisplayed(),
        "Not all event times are displayed!");
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.getEventTimeFromCalendarList().stream()
            .allMatch(e -> e.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")),
        "Event time has incorrect format!");
    softAssert.assertAll();
  }
}
