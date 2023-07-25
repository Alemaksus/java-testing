package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_SUBMITTED;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactHomeTaskPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.nio.file.FileSystems;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(
    description = "EPMRDPT_54104_VerifyThatAUserCanDownloadTheFileAttachedByAStudentInTheCheckPopUp",
    groups = {"full", "react", "regression"})
public class EPMRDPT_54104_VerifyThatAUserCanDownloadTheFileAttachedByAStudentInTheCheckPopUp {

  private ReactTasksJournalScreen reactTasksJournalScreen;
  private ReactHomeTaskPopUpScreen reactHomeTaskPopUpScreen;

  private final String groupName = "test_task_journal";
  private final String attachedFileName = "OnlineTaskForDownload.docx";
  private final String downloadedFilePath =
      getDownloadedFilePath() + FileSystems.getDefault().getSeparator() + attachedFileName;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingJournalPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
    reactTasksJournalScreen = new ReactTrainingService()
        .openTrainingJournalByGroupName(groupName)
        .clickTaskJournalTab()
        .waitGroupJournalTableForVisibility();
    reactTasksJournalScreen
        .clickAllPeriodButton()
        .waitTableHeaderForVisibility();
  }

  @Test(priority = 1)
  public void checkIfCheckPopUpIsOpened() {
    reactHomeTaskPopUpScreen = reactTasksJournalScreen
        .clickTaskByStatus(getValueOf(ONLINE_POPUP_STATUS_SUBMITTED));
    assertTrue(reactHomeTaskPopUpScreen.isScreenLoaded(), "Home task pop-up isn't loaded!");
  }

  @Test(priority = 2)
  public void checkIfStudentsAttachedFileIsPresent() {
    assertTrue(reactHomeTaskPopUpScreen.isAttachedFileDisplayed(attachedFileName),
        "Student's attached file isn't displayed!");
  }

  @Test(priority = 3)
  public void checkIfAttachedFileIsDownloaded() {
    reactHomeTaskPopUpScreen.clickDownloadButton();
    waitForFileExistence(downloadedFilePath);
    assertTrue(isFileExists(downloadedFilePath), "File wasn't downloaded!");
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteExistingReports() {
    deleteFile(downloadedFilePath);
  }
}
