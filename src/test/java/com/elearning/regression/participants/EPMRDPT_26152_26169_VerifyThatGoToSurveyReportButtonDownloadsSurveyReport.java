package com.epmrdpt.regression.participants;

import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_26152_26169_VerifyThatGoToSurveyReportButtonDownloadsSurveyReport",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_26152_26169_VerifyThatGoToSurveyReportButtonDownloadsSurveyReport {

  private static String DOWNLOADS_PATH = getDownloadedFilePath();
  private static final String TRAINING_NAME = "AutoTest_REGISTRATION_OPEN_Survey";
  private final String questionCellFormula = "G2";
  private final String answerCellFormula = "G3";
  private final String expectedQuestionText = LocaleProperties.getValueOf(
      LocalePropertyConst.SURVEY_SCREEN_QUESTION);
  private final String expectedAnswerText = " 1";
  private final int sheetIndex = 0;
  private String reportSurveyFileName = "RegistrationSurveyReport.xlsx";
  private User user;

  @Factory(dataProvider = "Provider of users with Reports tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_26152_26169_VerifyThatGoToSurveyReportButtonDownloadsSurveyReport(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    deleteDownloadedReport();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  public void checkRegistrationSurveyReportDownloading() {
    SoftAssert softAssert = new SoftAssert();
    new ReactParticipantsService()
        .downloadSurveyResultsFile();
    waitForFileExistence(DOWNLOADS_PATH, reportSurveyFileName);
    ExcelUtils reportExcelSheet = new ExcelUtils(DOWNLOADS_PATH, reportSurveyFileName, sheetIndex);
    softAssert.assertEquals(reportExcelSheet.getCellValueByFormula(questionCellFormula),
        expectedQuestionText, "Questions do not match!");
    softAssert.assertEquals(reportExcelSheet.getCellValueByFormula(answerCellFormula),
        expectedAnswerText, "Answers do not match!");
    softAssert.assertAll();
    deleteDownloadedReport();
  }

  public void deleteDownloadedReport() {
    deleteFile(DOWNLOADS_PATH, reportSurveyFileName);
  }
}
