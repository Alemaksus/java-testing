package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.EnvironmentPropertyConst.STAGE;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUBSCRIBERS_TABLE_FIRST_NAME_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUBSCRIBERS_TABLE_LAST_NAME_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_CITY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_OFFLINE_TASK_INFO_DATE;
import static com.epmrdpt.utils.FileUtils.FILE_DELIMITER;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.EnvironmentProperty;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_89901_VerifyThatSubscribersReportExcelFileIsDownloadedAfterClickingOnAnyNumberInTheСSBRColumn",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_89901_VerifyThatSubscribersReportExcelFileIsDownloadedAfterClickingOnAnyNumberInTheСSBRColumn {

  private String trainingName = "AutomatedTesting Training For Checkbox search";
  private int sheetIndex = 0;
  private final Map<String, String> headerCellFormulasAndValues = Map
      .of("A1", getValueOf(PARTICIPANTS_SUBSCRIBERS_TABLE_FIRST_NAME_HEADER),
          "B1", getValueOf(PARTICIPANTS_SUBSCRIBERS_TABLE_LAST_NAME_HEADER),
          "C1", getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT),
          "D1", getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_CITY_INPUT),
          "E1", "Email",
          "F1", getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT),
          "G1", getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_DATE),
          "H1", "PlanID");
  private final String downloadedFilePath =
      getDownloadedFilePath() + FILE_DELIMITER + getFileName();

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndDownloadSubscribersReport() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(asTrainingManager());
    new ReactTrainingManagementService()
        .searchTrainingByName(trainingName)
        .clickSBRColumnByTrainingName(trainingName);
  }

  @Test(priority = 1)
  public void verifySubscribersReportFileIsDownloaded() {
    waitForFileExistence(downloadedFilePath);
    assertTrue(isFileExists(downloadedFilePath),
        "Subscribers report wasn't downloaded or has incorrect name!");
  }

  @Test(priority = 2)
  public void verifySubscribersReportFile() {
    ExcelUtils reportExcelSheet = new ExcelUtils(downloadedFilePath, sheetIndex);
    SoftAssert softAssert = new SoftAssert();
    for (Map.Entry<String, String> entry : headerCellFormulasAndValues.entrySet()) {
      softAssert
          .assertEquals(reportExcelSheet.getCellValueByFormula(entry.getKey()), entry.getValue(),
              String.format("%s header value isn't equal to the expected one!", entry.getKey()));
    }
    softAssert
        .assertTrue(reportExcelSheet.getNumberOfFilledRows() > 1, "List of subscribers is empty!");
    softAssert.assertAll();
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteExistingReports() {
    deleteFile(downloadedFilePath);
  }

  private String getFileName() {
    String downloadedFileName;
    switch (EnvironmentProperty.getEnv()) {
      case STAGE:
        downloadedFileName = "SubscribersReport - 3391.xlsx";
        break;
      default:
        downloadedFileName = "SubscribersReport - 3505.xlsx";
    }
    return downloadedFileName;
  }
}
