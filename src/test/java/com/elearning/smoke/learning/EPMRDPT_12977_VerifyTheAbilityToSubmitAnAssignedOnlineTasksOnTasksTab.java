package com.epmrdpt.smoke.learning;

import static com.epmrdpt.bo.user.UserFactory.asStudentWithOnlineTasks;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_SUBMITTED;
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

@Test(description = "EPMRDPT_12977_VerifyTheAbilityToSubmitAnAssignedOnlineTasksOnTasksTab",
    groups = {"full", "smoke", "student"})
public class EPMRDPT_12977_VerifyTheAbilityToSubmitAnAssignedOnlineTasksOnTasksTab {

  private final String taskStatus = getValueOf(ONLINE_POPUP_STATUS_SUBMITTED);
  private final String taskName = "AutoTest_PassedOnlineTask";
  private final String groupName = "AutoTest_Group_10";
  private final String commentText = "Hello world" + java.time.LocalTime.now();
  private final String taskFilePath = getImportDocumentPath("AutoTest_OnlineTaskFile.pdf");
  private final String taskFileName = ("AutoTest_OnlineTaskFile.pdf");

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
    softAssert
        .assertTrue(
            tasksPopUpScreenOnLearningPageScreen.isOnlineTaskIconDisplayed(),
            "Task icon isn't displayed!");
    tasksPopUpScreenOnLearningPageScreen.typeComment(commentText);
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.getCommentFieldText().equalsIgnoreCase(commentText));
    softAssert
        .assertTrue(tasksPopUpScreenOnLearningPageScreen
                .uploadTaskFile(taskFilePath)
                .isTaskFileByNameDisplayed(taskFileName),
            format("File named %s isn't uploaded!", taskFileName));
    tasksPopUpScreenOnLearningPageScreen.clickSubmitButton();
    softAssert.assertEquals(
        new LearningService().navigateToTaskOfEpamTrainingInUrgentTab(groupName, taskName)
            .getUserCommentsMessageText(), commentText,
        "Last comment doesn't displayed!");
    softAssert.assertTrue(
        tasksPopUpScreenOnLearningPageScreen.getStatusLabelText().equalsIgnoreCase(taskStatus));
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteCreatedFiles() {
    deleteFile(taskFilePath);
  }
}
