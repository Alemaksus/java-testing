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
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_102791_VerifyThatCertificateColumnContainsYesValueIfCandidateSelectedCertificateOptionInProfile",
    groups = {"full", "regression"})
public class EPMRDPT_102791_VerifyThatCertificateColumnContainsYesValueIfCandidateSelectedCertificateOptionInProfile {

  private final String trainingName = "C++ Gomel";
  private final String excelFileName = "StudentsInTraining - 2871.xlsx";
  private final String excelFilePath = getDownloadedFilePath() + FILE_DELIMITER + excelFileName;
  private final String studentName = "Fil filGordon";
  private int sheetIndex = 0;
  private int rowIndex = 0;
  private final String cellName = "Certificate";
  private final String cellFormula = "H2";
  private final String expectedCellValueWhenCheatingIsDetected = "Yes";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {

    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading()
        .clickEditButton()
        .waitConfigureColumnsTabHeaderVisibility()
        .clickConfiguresColumnsTabCheckAllButton()
        .clickConfigureColumnsTabApplyButton()
        .clickEnglishTestColumn()
        .clickEnglishTestColumn();
  }

  @Test(priority = 1)
  public void checkExcelDocumentIsDownloaded() {
    reactParticipantsTrainingScreen.clickStudentCheckBoxByStudentName(studentName);
    new ReactParticipantsService().downloadSelectedParticipantsFile();
    waitForFileExistence(excelFilePath);
    assertTrue(isFileExists(excelFilePath),
        "Document wasn't downloaded or has incorrect name!");
  }

  @Test(priority = 2)
  public void checkTheColumnCheatingInDownloadedDocument() {
    ExcelUtils reportExcelSheet = new ExcelUtils(excelFilePath, sheetIndex);
    softAssert = new SoftAssert();
    softAssert.assertTrue(reportExcelSheet.getAllRowValues(rowIndex).contains(cellName),
        "There is no  'Certificate' column in downloaded Excel document!");
    softAssert.assertEquals(reportExcelSheet.getCellValueByFormula(cellFormula),
        expectedCellValueWhenCheatingIsDetected,
        "Incorrect displaying of certificate probability in column 'Certificate'!");
    softAssert.assertAll();
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteDownloadedFile() {
    deleteFile(excelFilePath);
  }
}
