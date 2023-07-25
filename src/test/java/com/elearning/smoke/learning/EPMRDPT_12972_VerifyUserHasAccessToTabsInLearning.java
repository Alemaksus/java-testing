package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.screens.TrainersTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_12972_VerifyUserHasAccessToTabsInLearning",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_12972_VerifyUserHasAccessToTabsInLearning {

  @Test(priority = 1)
  public void checkUserHasAccessLearningPage() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asStudentForLearningPage());
    new HeaderScreen().clickLearningButton();
    assertTrue(new HomeTabOnLearningPageScreen().isScreenLoaded(),
        " User hasn't access to learning page!");
  }

  @Test(priority = 2)
  public void checkTasksTabDisplayed() {
    assertTrue(new TasksTabOnLearningPageScreen().clickTasksTab().isScreenLoaded(),
        "Task page isn't displayed!");
  }

  @Test(priority = 3)
  public void checkTrainersTabDisplayed() {
    assertTrue(new TrainersTabOnLearningPageScreen().clickTrainersTab().isScreenLoaded(),
        "Trainers page isn't displayed!");
  }

  @Test(priority = 4)
  public void checkHomeTabDisplayed() {
    assertTrue(new HomeTabOnLearningPageScreen().clickHomeTab().isScreenLoaded(),
        "Home page isn't displayed!");
  }
}
