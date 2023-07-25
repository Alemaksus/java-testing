package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_56447_VerifyThatAllAddedResumeIsDownloadedAsZipFileFromParticipantsPageViaDownloadDocumentButton",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_56447_VerifyThatAllAddedResumeIsDownloadedAsZipFileFromParticipantsPage {

  private static String DOWNLOADS_PATH = getDownloadedFilePath();
  private final String TRAINING_NAME = "AKM C++ 111";
  private final String ALL_RESUME_FILE_NAME_PATTERN = "StudentsDocuments - %s.zip";
  private String allResumeFileName;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToParticipantsPage() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test
  public void checkThatAllAddedResumeIsDownloaded() {
    allResumeFileName = format(ALL_RESUME_FILE_NAME_PATTERN,
        new ReactParticipantsTrainingScreen()
            .getPlanId());
    new ReactParticipantsService()
        .downloadParticipantsDocumentArchive();
    waitForFileExistence(DOWNLOADS_PATH, allResumeFileName);
    assertTrue(isFileExists(DOWNLOADS_PATH, allResumeFileName),
        "File is not downloaded!");
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteDownloadedFile() {
    deleteExistingFile();
  }

  private void deleteExistingFile() {
    deleteFile(DOWNLOADS_PATH, allResumeFileName);
  }
}
