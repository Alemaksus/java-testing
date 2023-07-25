package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56215_VerifyThatStudentCanNotSeeMarksForOfflineAndOnlineTasksWhenTheTrainerSelectCorrespondedOptionOnTheTaskJournal",
    groups = {"full", "student", "regression"})
public class EPMRDPT_56215_VerifyThatStudentCanNotSeeMarksForOfflineAndOnlineTasksWhenTheTrainerSelectCorrespondedOptionOnTheTaskJournal {

  private static final String ONLINE_TASK = "Online_Java_1";
  private static final String OFFLINE_TASK = "Offline_Java_1";
  private static final String HIDDEN_AVERAGE_MARK = "--";
  private HomeTabOnLearningPageScreen homeTabOnLearningPageScreen;
  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    homeTabOnLearningPageScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asStudentWithTasks())
        .waitLinksToRedirectOnOtherSectionsDisplayed().clickLearningButton();
  }

  @Test(priority = 1)
  public void checkAverageMarkForTasksBlockIsHidden() {
    assertEquals(homeTabOnLearningPageScreen.getAverageMarkForTasksAreaText(), HIDDEN_AVERAGE_MARK);
  }

  @Test(priority = 2)
  public void checkMarksAreNotVisible() {
    tasksTabOnLearningPageScreen = homeTabOnLearningPageScreen.clickTasksTab()
        .waitTaskSectionForVisibility();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isMarkHiddenIconByTaskNameDisplayed(ONLINE_TASK),
        String.format("The mark of task %s is not hidden!", ONLINE_TASK));
    softAssert.assertTrue(tasksTabOnLearningPageScreen.isMarkHiddenIconByTaskNameDisplayed(OFFLINE_TASK),
        String.format("The mark of task %s is not hidden!", OFFLINE_TASK));
    softAssert.assertAll();
  }
}
