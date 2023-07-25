package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12982_VerifyThatStudentIsAbleToSeeMarksForTasksAndOnlineTasksOnTheRelevantPage",
    groups = {"student", "smoke", "full"})
public class EPMRDPT_12982_VerifyThatStudentIsAbleToSeeMarksForTasksAndOnlineTasksOnTheRelevantPage {

  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;
  private Pattern markPattern = Pattern.compile("(0|([1-9])|([1-9][0-9]))/(([1-9])|([1-9][0-9]))");
  private final String groupName = "AutoTest_GroupForLearningPage";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asStudentForLearningPage())
        .clickLearningButton()
        .clickTasksTab()
        .waitTaskSectionForVisibility()
        .clickEpamTrainingTab();
  }

  @Test(priority = 1)
  public void checkThatTabWithAllGroupDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isGroupLabelDisplayed(),
        "Not all group name are displayed!");
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isAllTasksTabDisplayed(),
        "Not all tasks name are displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkCurrentMarkHasCorrectPattern() {
    tasksTabOnLearningPageScreen.clickAllTasksTab()
        .clickGroupByName(groupName);
    assertTrue(
        tasksTabOnLearningPageScreen.isAllMarksFromDropDownEqualsPattern(markPattern, groupName),
        "Marks have incorrect pattern!");
  }
}
