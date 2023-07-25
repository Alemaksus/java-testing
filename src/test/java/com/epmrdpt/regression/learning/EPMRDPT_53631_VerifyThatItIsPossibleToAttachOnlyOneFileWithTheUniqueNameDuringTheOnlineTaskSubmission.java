package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.FileUtils;
import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_53631_VerifyThatItIsPossibleToAttachOnlyOneFileWithTheUniqueNameDuringTheOnlineTaskSubmission",
    groups = {"full", "student", "regression"})
public class EPMRDPT_53631_VerifyThatItIsPossibleToAttachOnlyOneFileWithTheUniqueNameDuringTheOnlineTaskSubmission {

  private static final String GROUP_NAME = "AutoTest Student Java Group";
  private static final String TASK_NAME = "AutoTest_CancelFileUploadTask";
  private static final String COMMENT = "AutoTestComment";
  private static final String MESSAGE_FOR_SAME_FILE_UPLOAD = LocaleProperties.getValueOf(
      LocalePropertyConst.ONLINE_POPUP_ERROR_MESSAGE_DUPLICATE_FILE_UPLOAD);
  private static final String TASK_FILE_PATH = String
      .format("%s%s%s", FileUtils.getImportDocumentPath("AutoTest_SameFileUploadTask_Answer")
          , System.currentTimeMillis(), ".pdf");
  private static final int PERMISSIBLE_COUNT_OF_FILES_WITH_A_NAME = 1;

  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen;
  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  private File taskFile;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asLearningStudent())
        .waitLinksToRedirectOnOtherSectionsDisplayed();
    new HeaderScreen().clickLearningButton();
    tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    taskFile = FileUtils.getNewFile(TASK_FILE_PATH);
  }

  @Test(priority = 1)
  public void checkTasksPageIsOpened() {
    assertTrue(tasksTabOnLearningPageScreen.clickTasksTab().waitScreenLoading().isScreenLoaded(),
        "Tasks tab of learning page isn't opened!");
  }

  @Test(priority = 2)
  public void checkTaskIsOpened() {
    new LearningService().navigateToTaskInAllTasksTab(GROUP_NAME, TASK_NAME);
    assertTrue(tasksPopUpScreenOnLearningPageScreen.isPopUpTitleDisplayed(),
        "Task pop up isn't opened!");
  }

  @Test(priority = 3)
  public void checkCommentIsWritten() {
    assertEquals(tasksPopUpScreenOnLearningPageScreen.typeComment(COMMENT)
        .getCommentFieldText(), COMMENT, "Comment isn't added!");
  }

  @Test(priority = 4)
  public void checkFileIsAttached() {
    assertTrue(tasksPopUpScreenOnLearningPageScreen.uploadTaskFile(TASK_FILE_PATH)
        .getUploadedTaskFileNames()
        .contains(Paths.get(TASK_FILE_PATH).getFileName().toString()), "File isn't attached!");
  }

  @Test(priority = 5)
  public void checkTwoFilesWithSameNameCannotBeUploaded() {
    SoftAssert softAssert = new SoftAssert();
    softAssert
        .assertEquals(
            Collections.frequency(tasksPopUpScreenOnLearningPageScreen
                .uploadTaskFile(TASK_FILE_PATH)
                .getUploadedTaskFileNames(), Paths.get(TASK_FILE_PATH).getFileName().toString()),
            PERMISSIBLE_COUNT_OF_FILES_WITH_A_NAME,
            "File with same name is uploaded!");
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getDuplicateFileUploadErrorText(),
        MESSAGE_FOR_SAME_FILE_UPLOAD, "Error message isn't displayed for same file upload!");
    FileUtils.deleteFile(TASK_FILE_PATH);
    softAssert.assertAll();
  }
}
