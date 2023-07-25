package com.epmrdpt.smoke.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13065_VerifyThatTrainingManagerCanSaveListOfTheParticipantsToExcelFile",
    groups = {"full", "smoke", "manager"})
public class EPMRDPT_13065_VerifyThatTrainingManagerCanSaveListOfTheParticipantsToExcelFile {

  private static String DOWNLOADS_PATH = getDownloadedFilePath();
  private final String TRAINING_NAME = "AutoTest_StudentForAnotherTraining";
  private final String FIRST_PARTICIPANT_STRING = "Student\tAutoTest";
  private final String SECOND_PARTICIPANT_STRING = "QQ\tAA";
  private final String FILE_NAME_PATTERN = "StudentsInTraining - %s.xlsx";
  private final int DEFAULT_SHEET_NUMBER = 0;
  private String fileName;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private ExcelUtils excelFile;
  private String parsedFileFromExcelToString;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    fileName = format(FILE_NAME_PATTERN, reactParticipantsTrainingScreen.getPlanId());
  }

  @Test(priority = 1)
  public void checkParticipantsTrainingScreenLoaded() {
    assertTrue(reactParticipantsTrainingScreen
            .isScreenLoaded(),
        "Participants Training Screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkExcelButtonDisplayed() {
    assertTrue(reactParticipantsTrainingScreen
            .isExcelButtonDisplayed(),
        "Download button isn't displayed!");
  }

  @Test(priority = 3)
  public void checkFileDownloaded() {
    new ReactParticipantsService()
        .downloadSelectedParticipantsFile();
    waitForFileExistence(DOWNLOADS_PATH, fileName);
    excelFile = new ExcelUtils(DOWNLOADS_PATH, fileName, DEFAULT_SHEET_NUMBER);
    parsedFileFromExcelToString = excelFile
        .readAndParseExcelToString();
    assertTrue(isFileExists(DOWNLOADS_PATH, fileName),
        "File is not downloaded!");
  }

  @Test(priority = 4, dependsOnMethods = "checkFileDownloaded")
  public void checkFileContainsParticipants() {
    assertTrue(parsedFileFromExcelToString
            .contains(FIRST_PARTICIPANT_STRING) &&
            parsedFileFromExcelToString
                .contains(SECOND_PARTICIPANT_STRING),
        "Downloaded file did not contains required participants!");
  }

  @Test(priority = 4, dependsOnMethods = "checkFileDownloaded")
  public void checkFileParticipantsCount() {
    assertEquals(excelFile
            .getRowsCount() - 1,
        reactParticipantsTrainingScreen
            .getParticipantNamesList()
            .size(),
        "Downloaded file did not contains all participants!");
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteDownloadedFile() {
    deleteExistingFile();
  }

  private void deleteExistingFile() {
    deleteFile(DOWNLOADS_PATH, fileName);
  }
}
