package com.epmrdpt.regression.participants;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CHEATING_TOOLTIP_TEXT;
import static com.epmrdpt.utils.FileUtils.FILE_DELIMITER;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79788_VerifyCheatingIconIsDisplayedInRatingColumnOfAUserWhoHasBeenDetectedInCheatingAndCanBeDownloadedIntoExcelFile",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_79788_VerifyCheatingIconIsDisplayedInRatingColumnOfAUserWhoHasBeenDetectedInCheatingAndCanBeDownloadedIntoExcelFile {

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private final String excelFileName = "StudentsInTraining - 3597.xlsx";
  private final String excelFilePath = getDownloadedFilePath() + FILE_DELIMITER + excelFileName;
  private final String trainingName = "AUTOTEST WITH AC CHEATING DETECTED";
  private final String studentName = "NotificationLanguageSwitch AutoTest";
  private int sheetIndex = 0;
  private int rowIndex = 0;
  private final String cellName = "Suspect in cheating";
  private final String cellFormula = "Y2";
  private final String expectedCellValueWhenCheatingIsDetected = "Yes";
  private User user;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79788_VerifyCheatingIconIsDisplayedInRatingColumnOfAUserWhoHasBeenDetectedInCheatingAndCanBeDownloadedIntoExcelFile(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToParticipantsPage() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(user);
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkIconNextToRatingResults() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isStudentCheatingIconDisplayed(studentName),
        "The cheating icon is not displayed");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getCheatingIconTooltipText(studentName),
        getValueOf(PARTICIPANTS_CHEATING_TOOLTIP_TEXT),
        "The cheating icon has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkExcelDocumentIsDownloaded() {
    reactParticipantsTrainingScreen.clickStudentCheckBoxByStudentName(studentName);
    new ReactParticipantsService().downloadSelectedParticipantsFile();
    waitForFileExistence(excelFilePath);
    assertTrue(isFileExists(excelFilePath),
        "Document wasn't downloaded or has incorrect name!");
  }

  @Test(priority = 3)
  public void checkTheColumnCheatingInDownloadedDocument() {
    ExcelUtils reportExcelSheet = new ExcelUtils(excelFilePath, sheetIndex);
    softAssert = new SoftAssert();
    softAssert.assertTrue(reportExcelSheet.getAllRowValues(rowIndex).contains(cellName),
        "There is no  'Suspect in cheating' column in downloaded Excel document!");
    softAssert.assertEquals(reportExcelSheet.getCellValueByFormula(cellFormula),
        expectedCellValueWhenCheatingIsDetected,
        "Incorrect displaying of cheating probability in column 'Suspect in cheating'!");
    softAssert.assertAll();
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteDownloadedFile() {
    deleteFile(excelFilePath);
  }
}
