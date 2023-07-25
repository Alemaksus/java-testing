package com.epmrdpt.smoke.learning;

import static com.epmrdpt.bo.user.UserFactory.asStudentWithOnlineTasks;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_REJECTED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TASKS_TAB_ATTEMPTS_TEXT;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getImportDocumentPath;
import static com.epmrdpt.utils.FileUtils.getNewFile;
import static java.lang.String.format;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12978_VerifyTheAbilityToSubmitRejectedOnlineTasksIfSeveralAttempts",
    groups = {"full", "smoke", "student"})
public class EPMRDPT_12978_VerifyTheAbilityToSubmitRejectedOnlineTasksIfSeveralAttempts {

  private final String taskStatus = getValueOf(ONLINE_POPUP_STATUS_REJECTED);
  private final String taskName = "AutoTest_RejectedOnlineTask";
  private final String groupName = "AutoTest_Group_10";
  private final String commentText = "Rejected comment" + java.time.LocalTime.now();
  private final String taskFileName = "AutoTest_RejectedTaskFile.pdf";
  private final String taskFilePath = getImportDocumentPath(taskFileName);
  private final String attemptsText = getValueOf(TASKS_TAB_ATTEMPTS_TEXT);

  private TasksPopUpScreenOnLearningPageScreen tasksPopUpScreenOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentWithOnlineTasks())
        .clickLearningButton();
    tasksPopUpScreenOnLearningPageScreen = new LearningService()
        .navigateToTaskOfEpamTrainingInUrgentTab(groupName, taskName);
    getNewFile(taskFilePath);
  }

  @Test
  public void checkAbilityToSubmitOnlineTask() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen
            .getStatusLabelText().equalsIgnoreCase(taskStatus),
        "Task status not as expected!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.isOnlineTaskIconDisplayed(),
        "Task icon isn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.getAttemptsText().equalsIgnoreCase(attemptsText),
        "In the task Attempts aren't multiple!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.typeComment(commentText).getCommentFieldText()
            .equalsIgnoreCase(commentText),
        "Comment isn't as expected!");
    softAssert
        .assertTrue(tasksPopUpScreenOnLearningPageScreen
                .uploadTaskFile(taskFilePath)
                .isTaskFileByNameDisplayed(taskFileName),
            format("File named %s isn't uploaded!", taskFileName));
    softAssert.assertTrue(tasksPopUpScreenOnLearningPageScreen.isSubmitButtonEnabled(),
        "'Submit' button isn't clickable");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteCreatedFiles() {
    deleteFile(taskFilePath);
  }
}
