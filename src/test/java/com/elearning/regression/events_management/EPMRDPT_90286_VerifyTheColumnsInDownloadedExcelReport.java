package com.epmrdpt.regression.events_management;

import static com.epmrdpt.framework.properties.UserPropertyConst.ATTENDEES_IN_OFFLINE_EVENT_EXCEL_FILE;
import static com.epmrdpt.framework.properties.UserPropertyConst.ATTENDEES_IN_ONLINE_EVENT_EXCEL_FILE;
import static com.epmrdpt.utils.FileUtils.FILE_DELIMITER;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.screens.ReactEventAttendeesScreen;
import com.epmrdpt.services.EventPreviewService;
import com.epmrdpt.utils.ExcelUtils;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_90286_VerifyTheColumnsInDownloadedExcelReport",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_90286_VerifyTheColumnsInDownloadedExcelReport {

  private static final String ATTENDEES_IN_ONLINE_EVENT_EXCEL_FILE_NAME = UserProperty.getValueOf(
      ATTENDEES_IN_ONLINE_EVENT_EXCEL_FILE);
  private static final String ATTENDEES_IN_OFFLINE_EVENT_EXCEL_FILE_NAME = UserProperty.getValueOf(
      ATTENDEES_IN_OFFLINE_EVENT_EXCEL_FILE);
  private static final String ATTENDEES_IN_ONLINE_EVENT_FILE_PATH =
      getDownloadedFilePath() + FILE_DELIMITER + ATTENDEES_IN_ONLINE_EVENT_EXCEL_FILE_NAME;
  private static final String ATTENDEES_IN_OFFLINE_EVENT_FILE_PATH =
      getDownloadedFilePath() + FILE_DELIMITER + ATTENDEES_IN_OFFLINE_EVENT_EXCEL_FILE_NAME;
  private static final String ONLINE_EVENT_NAME = "EVENTS_FOR_TESTING";
  private static final String OFFLINE_EVENT_NAME = "OFFLINE_EVENTS_FOR_TESTING";
  private static final String COLUMN_HEADER_PHOTOGRAPHING_ACCEPTED = "Photographing accepted";
  private final int sheetIndex = 0;
  private final int rowIndex = 0;
  private static List<String> listOfHeadersOnlineEventInDownloadedExcelFile = List.of(
      "Registration date",
      "First name",
      "Last name",
      "Birth date",
      "Email",
      "English level",
      "English test",
      "Mobile phone",
      "Country",
      "Region",
      "City",
      "Status",
      "Survey",
      "University",
      "Faculty",
      "Department",
      "Degree information",
      "Graduation year",
      "Skills",
      "Skill level",
      "Previous events",
      "View in Staffing Desk",
      "Attachments"
  );
  private String eventName;
  private List<String> columnHeadersList;
  private String excelFilePath;

  @Factory(dataProvider = "Provider of events")
  public EPMRDPT_90286_VerifyTheColumnsInDownloadedExcelReport(String eventName,
      List<String> columnHeadersList, String excelFilePath) {
    this.eventName = eventName;
    this.columnHeadersList = columnHeadersList;
    this.excelFilePath = excelFilePath;
  }

  @DataProvider(name = "Provider of events")
  public static Object[][] provideEvents() {
    return new Object[][]{
        {ONLINE_EVENT_NAME, listOfHeadersOnlineEventInDownloadedExcelFile,
            ATTENDEES_IN_ONLINE_EVENT_FILE_PATH},
        {OFFLINE_EVENT_NAME, getListOfHeadersOfflineEventInDownloadedExcelFile(),
            ATTENDEES_IN_OFFLINE_EVENT_FILE_PATH}
    };
  }

  @Test(priority = 1)
  public void checkExcelFileWasDownloaded() {
    new EventPreviewService().passageToAttendeesOfEventsCardAsEventManager(eventName);
    new ReactEventAttendeesScreen()
        .waitAttendeesTableDisplayed()
        .clickExcelButton();
    waitForFileExistence(excelFilePath);
    assertTrue(isFileExists(excelFilePath),
        format("File named %s isn't downloaded!", excelFilePath));
  }

  @Test(priority = 2)
  public void checkTheColumnOfDownloadedDocument() {
    ExcelUtils downloadedExcelSheet = new ExcelUtils(excelFilePath, sheetIndex);
    SoftAssert softAssert = new SoftAssert();
    columnHeadersList.forEach(column -> softAssert.assertTrue(
        (downloadedExcelSheet.getAllRowValues(rowIndex).contains(column)),
        format("Column  %s isn't displayed!", column)));
    softAssert.assertAll();
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteDownloadedFile() {
    deleteFile(excelFilePath);
  }

  private static List<String> getListOfHeadersOfflineEventInDownloadedExcelFile() {
    List<String> listOfHeadersOfflineEventInDownloadedExcelFile =
        new ArrayList<>(List.copyOf(listOfHeadersOnlineEventInDownloadedExcelFile));
    listOfHeadersOfflineEventInDownloadedExcelFile.add(COLUMN_HEADER_PHOTOGRAPHING_ACCEPTED);
    return listOfHeadersOfflineEventInDownloadedExcelFile;
  }
}
