package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.utils.FileUtils.FILE_DELIMITER;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_56444_VerifyThatPersonalResumeIsDownloadedViaCViconOnTheParticipantsPage",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_56444_VerifyThatPersonalResumeIsDownloadedViaCViconOnTheParticipantsPage {

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private final String TRAINING_NAME = "AutoTest_WithGroupAndPatricipantInProgress";
  private final String studentName = "QQ AA";
  private final String downloadedResume = "Resume_325903.zip";
  private final String RESUME_FILE_PATH =
      getDownloadedFilePath() + FILE_DELIMITER + downloadedResume;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToParticipantsPage() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkResumeIconNextToUserName() {
    assertTrue(reactParticipantsTrainingScreen.isStudentCVByStudentNameDisplayed(studentName),
        "The 'CV' icon is not displayed!");
  }

  @Test(priority = 2)
  public void verifyPersonalResumeIsDownloaded() {
    reactParticipantsTrainingScreen.clickStudentCVByStudentName(studentName);
    waitForFileExistence(RESUME_FILE_PATH);
    assertTrue(isFileExists(RESUME_FILE_PATH),
        "Personal resume wasn't downloaded or has incorrect name!");
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void removeResume() {
    deleteFile(RESUME_FILE_PATH);
  }
}
