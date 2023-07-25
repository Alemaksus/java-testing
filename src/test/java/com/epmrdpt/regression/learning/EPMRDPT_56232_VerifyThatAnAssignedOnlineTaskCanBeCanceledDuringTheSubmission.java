package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.FileUtils;
import java.nio.file.Paths;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_56232_VerifyThatAnAssignedOnlineTaskCanBeCanceledDuringTheSubmission",
    groups = {"full", "student", "regression"})
public class EPMRDPT_56232_VerifyThatAnAssignedOnlineTaskCanBeCanceledDuringTheSubmission {

  private static final String GROUP_NAME = "AutoTest Student Java Group";
  private static final String TASK_NAME = "AutoTest_CancelFileUploadTask";
  private static final String COMMENT = "AutoTestComment";
  private static final String TASK_FILE_PATH = String
      .format("%s%s", FileUtils.getImportDocumentPath("AutoTest_CancelFileUploadTask_Answer")
          + System.currentTimeMillis(), ".pdf");

  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;
  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asLearningStudent())
        .waitLinksToRedirectOnOtherSectionsDisplayed();
    new HeaderScreen().clickLearningButton();
    tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    FileUtils.getNewFile(TASK_FILE_PATH);
  }

  @Test(priority = 1)
  public void checkTasksPageIsOpened() {
    assertTrue(tasksTabOnLearningPageScreen.clickTasksTab().isScreenLoaded(),
        "Tasks tab of learning page isn't opened!");
  }

  @Test(priority = 2)
  public void checkTaskIsOpened() {
    new LearningService().navigateToTaskInAllTasksTab(GROUP_NAME, TASK_NAME);
    assertTrue(tasksPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Task details pop up isn't opened!");
  }

  @Test(priority = 3)
  public void checkCommentIsWritten() {
    assertEquals(tasksPopUpScreenOnLearningPageScreen.typeComment(COMMENT)
        .getCommentFieldText(), COMMENT, "Comment isn't written in comment box!");
  }

  @Test(priority = 4)
  public void checkFileIsAttached() {
    assertTrue(tasksPopUpScreenOnLearningPageScreen.uploadTaskFile(TASK_FILE_PATH)
        .getUploadedTaskFileNames()
        .contains(Paths.get(TASK_FILE_PATH).getFileName().toString()), "File isn't uploaded!");
  }

  @Test(priority = 5)
  public void checkSubmissionIsNotOccurred() {
    tasksPopUpScreenOnLearningPageScreen.clickCrossMark();
    assertEquals(tasksTabOnLearningPageScreen.clickTaskByNameInAllTasksTab(GROUP_NAME, TASK_NAME)
            .getStatusLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.ONLINE_POPUP_STATUS_ASSIGNED),
        "Task status isn't 'assigned'!");
  }

  @Test(priority = 6)
  public void checkLoggedInAsTrainer() {
    tasksPopUpScreenOnLearningPageScreen.clickCrossMark();
    new HeaderScreen().clickProfileMenu()
        .signOut();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(
            UserFactory.asTrainer())
        .waitLinksToRedirectOnOtherSectionsDisplayed();
    assertTrue(new HeaderScreen().clickProfileMenu()
        .isProfilePhotoImageDisplayed(), "User isn't logged in!");
  }

  @Test(priority = 7)
  public void checkTaskJournalPageIsOpened() {
    new HeaderScreen().clickReactTrainingScreenLink()
        .waitScheduleForVisibility()
        .clickMyGroupsTab();
    assertTrue(new ReactTrainingService()
        .openGroupJournalByName(GROUP_NAME)
        .clickTaskJournalTab()
        .isScreenLoaded(), "Task Journal page isn't loaded!");
  }

  @Test(priority = 8)
  public void checkTaskHasAssignedStatus() {
    new ReactTasksJournalScreen()
        .waitTableHeaderForVisibility();
    new ReactTaskJournalService().setCustomPeriodToOneYear();
    assertEquals(new ReactTasksJournalScreen()
            .clickAllPeriodButton()
            .clickOnlineTaskInTaskJournalByName(TASK_NAME)
            .mouseOverAssignToEveryoneButton()
            .getTooltipButtonText(),
        LocaleProperties
            .getValueOf(LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_CANCEL_ASSIGN),
        "Task doesn't have assigned status!");
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteFile() {
    FileUtils.deleteFile(TASK_FILE_PATH);
  }
}
