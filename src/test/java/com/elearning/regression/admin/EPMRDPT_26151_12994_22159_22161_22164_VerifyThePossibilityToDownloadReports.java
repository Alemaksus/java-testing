package com.epmrdpt.regression.admin;

import static com.epmrdpt.bo.user.UserFactory.asSuperAdmin;
import static com.epmrdpt.framework.loging.Log.logInfoMessage;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REPORTS_GROUPS_INFO_REPORT_END_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REPORTS_GROUPS_INFO_REPORT_START_DATE;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static java.lang.String.format;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReportsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactReportsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_26151_12994_22159_22161_22164_VerifyThePossibilityToDownloadReports",
    groups = {"full", "regression"})
public class EPMRDPT_26151_12994_22159_22161_22164_VerifyThePossibilityToDownloadReports {

  private static String DOWNLOADS_PATH = getDownloadedFilePath();
  private final String trainingName = "AutoTest_REGISTRATION_OPEN_Survey";
  private final String fileName = "RegistrationSurveyReport.xlsx";
  private final int sheetIndex = 0;
  private final String questionCellFormula = "G2";
  private final String answerCellFormula = "G3";
  private final String expectedQuestionText = LocaleProperties.getValueOf(
      LocalePropertyConst.SURVEY_SCREEN_QUESTION);
  private final String expectedAnswerText = "1";
  private final List<String> reportsFileNames = ReactReportsService.getReportsFileNames();
  private final int countryIndex = 0;
  private final int cityIndex = 0;
  private final int skillIndex = 0;
  private final String startDate = getValueOf(REPORTS_GROUPS_INFO_REPORT_START_DATE);
  private final String endDate = getValueOf(REPORTS_GROUPS_INFO_REPORT_END_DATE);
  private final int totalTestsCount = 7;

  private int numberOfTestsPerformed = 0;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    deleteFile(DOWNLOADS_PATH, fileName);
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asSuperAdmin())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkSurveyReportDownloadingAndReportContent() {
    SoftAssert softAssert = new SoftAssert();
    new ReactParticipantsService()
        .downloadSurveyResultsFile();
    waitForFileExistence(DOWNLOADS_PATH, fileName);
    softAssert.assertTrue(isFileExists(DOWNLOADS_PATH, fileName),
        format("File named %s isn't downloaded!", fileName));
    ExcelUtils reportExcelSheet = new ExcelUtils(DOWNLOADS_PATH, fileName, sheetIndex);
    softAssert.assertEquals(reportExcelSheet.getCellValueByFormula(questionCellFormula),
        expectedQuestionText, "Questions do not match!");
    softAssert
        .assertTrue(
            reportExcelSheet.getCellValueByFormula(answerCellFormula).contains(expectedAnswerText),
            "Answers do not match!");
    softAssert.assertAll();
  }

  @Test(priority = 2, dataProvider = "Provider of users with Reports tab", dataProviderClass = DataProviderSource.class)
  public void checkReportsDownloadingFromReportsTab(User user) {
    logInfoMessage(format("Checking Reports Downloading from %s user-profile",
        user.getFirstName().toUpperCase()));
    SoftAssert softAssert = new SoftAssert();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReportsLink()
        .waitScreenLoading();
    new ReactReportsService()
        .downloadParticipantsInfoReports()
        .downloadTrainingParticipantsInfoReportsInExcel()
        .downloadGroupsInfoReport(countryIndex, cityIndex, skillIndex, startDate, endDate)
        .downloadRegistrationSurveyReports()
        .downloadUtmTagsReports();
    for (String reportName : reportsFileNames) {
      softAssert.assertTrue(isFileExists(DOWNLOADS_PATH, reportName),
          format("File named %s isn't downloaded!", reportName));
    }
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteExistingReports() {
    for (String reportName : reportsFileNames) {
      deleteFile(DOWNLOADS_PATH, reportName);
    }
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void reopenBrowser() {
    if (++numberOfTestsPerformed < totalTestsCount) {
      new ReportsScreen().closeBrowser();
      new HeaderScreen().openPage(getEnv());
    }
  }
}
