package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_DOWNLOAD_SURVEY_RESULTS_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUBSCRIBERS_TABLE_FIRST_NAME_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUBSCRIBERS_TABLE_LAST_NAME_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SURVEY_FILE_APPLICANT_STATUS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SURVEY_FILE_DATE_OF_PASSING_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_MANAGEMENT_PROFILE;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.getFileDelimiter;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79807_VerifyColumnsOfTheSurveyResultsInDownloadedDocument",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_79807_VerifyColumnsOfTheSurveyResultsInDownloadedDocument {

  private User user;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private String trainingName = "DubFullRegistration+SD+StandardSurvey";
  private int newStudentStatusIndex = 1;
  private int sheetIndex = 0;
  private String downloadedFileName = "RegistrationSurveyReport.xlsx";
  private String surveyQuestion = "Where did you learn about our training?";
  private final String fullPathToDownloadedFile =
      getDownloadedFilePath() + getFileDelimiter() + downloadedFileName;
  private final Map<String, String> headerCellFormulasAndValues = Map
      .of("A2", "№",
          "B2", getValueOf(PARTICIPANTS_SUBSCRIBERS_TABLE_LAST_NAME_HEADER),
          "C2", getValueOf(PARTICIPANTS_SUBSCRIBERS_TABLE_FIRST_NAME_HEADER),
          "D2", getValueOf(PARTICIPANTS_SURVEY_FILE_APPLICANT_STATUS_COLUMN_HEADER),
          "E2", getValueOf(USER_MANAGEMENT_PROFILE),
          "F2", getValueOf(PARTICIPANTS_SURVEY_FILE_DATE_OF_PASSING_COLUMN_HEADER),
          "G2", surveyQuestion);

  @Factory(dataProvider = "Provider of users with Reports tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79807_VerifyColumnsOfTheSurveyResultsInDownloadedDocument(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToParticipantsPage() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(user);
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitScreenLoaded()
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility();
    new ReactParticipantsService().clickAndSelectSortingStudentStatusByIndex(newStudentStatusIndex);
  }

  @Test(priority = 1)
  public void checkExcelButtonInDdlIsDisplayed() {
    assertTrue(reactParticipantsTrainingScreen.clickExcelButton()
            .waitExcelChoicePopUpVisibility()
            .isDownloadSurveyResultsButtonDisplayedNoWait(),
        "'Download Survey results' button isn't displayed!");
  }

  @Test(priority = 2, dependsOnMethods = {"checkExcelButtonInDdlIsDisplayed"})
  public void checkDownloadSurveyConfirmationPopUp() {
    int numberOfSelectedParticipants = reactParticipantsTrainingScreen.waitScreenLoading()
        .getNumberOfParticipantsFromTable();
    assertEquals(reactParticipantsTrainingScreen.clickDownloadSurveyResultsButton()
            .waitForConfirmActionWindowVisibility().getConfirmActionWindowBodyText(),
        String.format(getValueOf(
            PARTICIPANTS_DOWNLOAD_SURVEY_RESULTS_POP_UP), numberOfSelectedParticipants,
            "Confirmation pop up test isn't equal to the expected one!"));
  }

  @Test(priority = 3, dependsOnMethods = {"checkExcelButtonInDdlIsDisplayed"})
  public void checkSurveyResultsFileIsDownloaded() {
    reactParticipantsTrainingScreen.clickConfirmActionButton();
    waitForFileExistence(fullPathToDownloadedFile);
    assertTrue(isFileExists(fullPathToDownloadedFile), "Survey results file wasn't downloaded!");
  }

  @Test(priority = 4, dependsOnMethods = {"checkExcelButtonInDdlIsDisplayed"})
  public void checkColumnHeadersInDownloadedFile() {
    ExcelUtils reportExcelSheet = new ExcelUtils(fullPathToDownloadedFile, sheetIndex);
    SoftAssert softAssert = new SoftAssert();
    for (Map.Entry<String, String> entry : headerCellFormulasAndValues.entrySet()) {
      softAssert.assertTrue(reportExcelSheet.getCellValueByFormula(entry.getKey())
              .equalsIgnoreCase(entry.getValue()),
          String.format("%s header value isn't equal to the expected one!", entry.getKey()));
    }
    softAssert.assertAll();
  }

  @AfterClass
  public void deleteDownloadedFile() {
    deleteFile(fullPathToDownloadedFile);
  }
}
