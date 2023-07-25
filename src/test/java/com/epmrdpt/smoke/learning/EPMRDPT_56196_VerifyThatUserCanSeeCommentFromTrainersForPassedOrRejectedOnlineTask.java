package com.epmrdpt.smoke.learning;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56196_VerifyThatUserCanSeeCommentFromTrainersForPassedOrRejectedOnlineTask",
    groups = {"student", "full", "smoke"})
public class EPMRDPT_56196_VerifyThatUserCanSeeCommentFromTrainersForPassedOrRejectedOnlineTask {

  private final String TASK_NAME = "AUTO_TEST_PASSED_TASK";
  private final String GROUP_NAME = "AutoTest_GroupForLearningPage";
  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asStudentForLearningPage()).clickLearningButton();
    tasksPopUpScreenOnLearningPageScreen = new LearningService()
        .navigateToTaskOfEpamTrainingInUrgentTab(GROUP_NAME, TASK_NAME).waitPopUpForVisibility();
  }

  @Test
  public void checkThatTrainersAnswerHasAllRequiredInformation() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTitleInWorkCommentSectionDisplayed(),
        "Title in work comment section isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerPhotoInWorkCommentsSectionDisplayed(),
        "Trainer photo in work comment section isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isTrainerNameInWorkCommentsSectionDisplayed(),
        "Trainer name in work comment section isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isDataInWorkCommentSectionDisplayed(),
        "Data in work comment section isn't displayed!");
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isTrainerCommentsMessageDisplayed(),
        "Trainer comment message isn't displayed!");
    String urlBeforeClick = tasksPopUpScreenOnLearningPageScreen.getCurrentWindowUrl();
    tasksPopUpScreenOnLearningPageScreen.clickOnTrainerCommentsMessageField();
    String urlAfterClick = tasksPopUpScreenOnLearningPageScreen.getCurrentWindowUrl();
    softAssert.assertEquals(urlBeforeClick, urlAfterClick,
        "Trainer comment message is clickable");
    softAssert.assertAll();
  }
}
