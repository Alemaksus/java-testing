package com.epmrdpt.regression.training_management;

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
import com.epmrdpt.utils.ExcelUtils;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13066_VerifyTrainingManagerCanSaveListSelectedParticipantsToExcelFile",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_13066_VerifyTrainingManagerCanSaveListSelectedParticipantsToExcelFile {

  private static String DOWNLOADS_PATH = getDownloadedFilePath();
  private final String TRAINING_NAME = "AutoTest_CheckRatingColour";
  private final String FILE_NAME_PATTERN = "StudentsInTraining - %s.xlsx";
  private final int NUMBER_OF_STUDENTS = 3;
  private final int DEFAULT_SHEET_NUMBER = 0;
  private List<String> studentsNames;
  private String fileName;
  private ReactParticipantsService reactParticipantsService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    fileName = format(FILE_NAME_PATTERN,
        new ReactParticipantsTrainingScreen()
            .getPlanId());
    reactParticipantsService = new ReactParticipantsService();
    studentsNames = reactParticipantsService
        .selectSeveralFirstStudentsAndGetTheirNames(NUMBER_OF_STUDENTS);
    reactParticipantsService
        .downloadSelectedParticipantsFile();
    waitForFileExistence(DOWNLOADS_PATH, fileName);
  }

  @Test
  public void checkExcelFileIsDownloaded() {
    assertTrue(isFileExists(DOWNLOADS_PATH, fileName),
        format("File [%s] isn't downloaded!", fileName));
  }

  @Test(dependsOnMethods = {"checkExcelFileIsDownloaded"})
  public void checkDownloadedExcelFileHasCheckedStudentsNames() {
    String excelString = new ExcelUtils(DOWNLOADS_PATH, fileName, DEFAULT_SHEET_NUMBER)
        .readAndParseExcelToString().replace('\t', ' ');
    SoftAssert softAssert = new SoftAssert();
    studentsNames.forEach(studentName -> softAssert.assertTrue(excelString.contains(studentName),
        "Failed to find student [" + studentName + "] in " + fileName + " "));
    softAssert.assertAll();
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteDownloadedFile() {
    deleteExistingFile();
  }

  private void deleteExistingFile() {
    deleteFile(DOWNLOADS_PATH, fileName);
  }
}
