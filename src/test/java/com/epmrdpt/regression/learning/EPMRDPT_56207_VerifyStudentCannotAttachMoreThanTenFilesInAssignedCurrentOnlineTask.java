package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_ERROR_MESSAGE_MAXIMUM_FILE_UPLOADED;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getImportDocumentFolderPath;
import static com.epmrdpt.utils.FileUtils.getNewFile;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56207_VerifyStudentCannotAttachMoreThanTenFilesInAssignedCurrentOnlineTask",
    groups = {"full", "regression", "student"})
public class EPMRDPT_56207_VerifyStudentCannotAttachMoreThanTenFilesInAssignedCurrentOnlineTask {

  private final String taskName = "AutoTest_ReassignedOnlineTask";
  private final String groupName = "AutoTest_LearningStudent_DOTNET_Tasks";
  private final String taskFilePath = getImportDocumentFolderPath();
  private final String taskFileNamePattern = ("AutoTest_TaskFile_%d.pdf");
  private final String studentComment = "Assigned Online Task Solution for AutoTest";
  private final String errorText = getValueOf(
      ONLINE_POPUP_ERROR_MESSAGE_MAXIMUM_FILE_UPLOADED);
  private final int filesToBeCreatedCount = 10;
  private final int fileCountAfterRemovingAll = 0;

  private List<String> taskFilesNames = new ArrayList<>();
  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    tasksPopUpScreenOnLearningPageScreen = new TasksPopUpScreenOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService().navigateToTaskInAllTasksTab(groupName, taskName);
    createFilesForUploading();
  }

  @Test(priority = 1)
  public void checkCommentInput() {
    tasksPopUpScreenOnLearningPageScreen.typeComment(studentComment);
    assertEquals(tasksPopUpScreenOnLearningPageScreen.getCommentFieldText(), studentComment,
        "Written comment isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTaskFilesUploading() {
    softAssert = new SoftAssert();
    for (String taskFileName : taskFilesNames) {
      tasksPopUpScreenOnLearningPageScreen
          .uploadTaskFile(taskFilePath + taskFileName);
      softAssert
          .assertTrue(tasksPopUpScreenOnLearningPageScreen.isTaskFileByNameDisplayed(taskFileName),
              format("File named %s isn't uploaded!", taskFileName));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkMoreThanTenFilesCannotBeAttached() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(
        tasksPopUpScreenOnLearningPageScreen.getMaximumPermissibleFileUploadReachedErrorText(),
        errorText, format("\"%s\" error text isn't displayed!", errorText));
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isAddFileDisabled(),
        "Add file button isn't disabled!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkUploadedTaskFilesRemoving() {
    softAssert = new SoftAssert();
    for (String taskFileName : taskFilesNames) {
      tasksPopUpScreenOnLearningPageScreen.clickRemoveFileButtonByName(taskFileName);
      softAssert
          .assertTrue(
              tasksPopUpScreenOnLearningPageScreen.isTaskFileByNameNotDisplayed(taskFileName),
              format("File named %s isn't removed!", taskFileName));
    }
    softAssert.assertEquals(tasksPopUpScreenOnLearningPageScreen.getUploadedTaskFilesCount(),
        fileCountAfterRemovingAll, "All task files weren't removed!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteCreatedFiles(Method method) {
    if (method.getName().equals("checkTaskFilesUploading")) {
      for (String taskFileName : taskFilesNames) {
        deleteFile(taskFilePath, taskFileName);
      }
    }
  }

  private void createFilesForUploading() {
    int fileCounter = 1;
    while (fileCounter <= filesToBeCreatedCount) {
      taskFilesNames.add(format(taskFileNamePattern, fileCounter++));
    }
    for (String taskFileName : taskFilesNames) {
      getNewFile(taskFilePath, taskFileName);
    }
  }
}
