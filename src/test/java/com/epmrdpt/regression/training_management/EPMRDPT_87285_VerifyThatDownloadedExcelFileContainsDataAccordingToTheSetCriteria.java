package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_CITY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_SKILL_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.utils.FileUtils.FILE_DELIMITER;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.StringUtils.getTodayDate;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactSubscribersExportPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.utils.ExcelUtils;
import com.epmrdpt.utils.FileUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_87285_VerifyThatDownloadedExcelFileContainsDataAccordingToTheSetCriteria",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_87285_VerifyThatDownloadedExcelFileContainsDataAccordingToTheSetCriteria {

  private ReactSubscribersExportPopUpScreen reactSubscribersExportPopUpScreen;
  private SoftAssert softAssert;
  private String startDateText = "21.09.2022";
  private String datePattern = "dd.MM.yyyy";
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
  private LocalDate endDate = getTodayDate().plusDays(1);
  private LocalDate startDate = LocalDate.parse(startDateText, formatter);;
  private final String excelFileName = "SubscribersReport - Custom.xlsx";
  private final String excelFilePath = getDownloadedFilePath() + FILE_DELIMITER + excelFileName;
  private int sheetIndex = 0;
  private String filterCountry = "AutoTestCountry";
  private String filterCity = "AutoTestCity";
  private String filterSkill = "Automated Testing";
  private int countryColumnIndex = 2;
  private int cityColumnIndex = 3;
  private int skillColumnIndex = 5;
  private int dateColumnIndex = 6;

  @DataProvider(name = "provider of pop-up ddl labels, ddl placeholders and ddl values for choice")
  public Object[][] providerOfPopUpLabelsAndDdlPlaceholders() {
    return new Object[][]{
        {getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT),
            getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE), filterCountry},
        {getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_CITY_INPUT),
            getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE), filterCity},
        {getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT),
            getValueOf(REACT_TRAINING_MANAGEMENT_SKILL_DROPDOWN_DEFAULT_VALUE), filterSkill}
    };
  }

  @BeforeClass
  public void setUp() {
    reactSubscribersExportPopUpScreen = new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager())
        .waitAllSpinnersAreNotDisplayed()
        .clickSubscribersListButton()
        .waitScreenLoaded();
  }

  @Test(dataProvider = "provider of pop-up ddl labels, ddl placeholders and ddl values for choice", priority = 1)
  public void verifyDDLsOfSubscribersExportPopUp(String labelName, String placeholder,
      String value) {
    softAssert = new SoftAssert();
    softAssert
        .assertEquals(reactSubscribersExportPopUpScreen.getDdlPlaceholderTextByLabelName(labelName),
            placeholder, format("%s DDL placeholder has incorrect text!", labelName));
    reactSubscribersExportPopUpScreen.clickDdlByLabelName(labelName);
    softAssert.assertTrue(
        reactSubscribersExportPopUpScreen.isMultiSelectFieldDisplayedByLabelName(labelName),
        format("%s multiselect field isn't displayed!", labelName));
    reactSubscribersExportPopUpScreen.selectDdlValueByLabelName(value, labelName)
        .clickDdlByLabelName(labelName);
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyButtonsOfSubscribersExportPopUp() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactSubscribersExportPopUpScreen.isExportButtonDisplayed(),
        "'Export' button isn't displayed!");
    softAssert.assertTrue(reactSubscribersExportPopUpScreen.isCancelButtonDisplayed(),
        "'Cancel' button isn't displayed!");
    softAssert.assertTrue(reactSubscribersExportPopUpScreen.isCrossButtonDisplayed(),
        "'Cross' button isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyDateSectionOfSubscribersExportPopUp() {
    assertTrue(reactSubscribersExportPopUpScreen.isDateLabelDisplayed(),
        "Date field isn't displayed!");
  }

  @Test(priority = 3)
  public void verifyExcelFileIsDownloaded() {
    reactSubscribersExportPopUpScreen
        .typeStartDate(startDate.format(formatter))
        .typeEndDate(endDate.format(formatter))
        .clickExportButton();
    FileUtils.waitForFileExistence(excelFilePath);
    assertTrue(isFileExists(excelFilePath),
        "Subscribers list wasn't downloaded or has incorrect name!");
  }

  @Test(priority = 4, dependsOnMethods = {"verifyExcelFileIsDownloaded"})
  public void verifyDataInExcelFile() {
    ExcelUtils subscribersExcelList = new ExcelUtils(excelFilePath, sheetIndex);
    softAssert = new SoftAssert();
    softAssert.assertTrue(subscribersExcelList.getAllColumnValues(countryColumnIndex).stream()
            .allMatch(v -> v.equals(filterCountry)),
        "Country column cells don't contain chosen country!");
    softAssert.assertTrue(subscribersExcelList.getAllColumnValues(cityColumnIndex).stream()
        .allMatch(v -> v.equals(filterCity)), "City column cells don't contain chosen city!");
    softAssert.assertTrue(subscribersExcelList.getAllColumnValues(skillColumnIndex).stream()
        .allMatch(v -> v.equals(filterSkill)), "Skill column cells don't contain chosen skill!");
    softAssert.assertTrue(subscribersExcelList.getAllColumnValues(dateColumnIndex).stream()
        .allMatch(this::isDateWithinRange), "Dates in Date column aren't within range!");
    softAssert.assertAll();
  }

  private boolean isDateWithinRange(String dateValue) {
    LocalDate dateValueFromTable = LocalDate.parse(dateValue, formatter);
    return dateValueFromTable.isBefore(endDate.plusDays(1)) || dateValueFromTable
        .isAfter(startDate.minusDays(1));
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void deleteExistingReports() {
    deleteFile(excelFilePath);
  }
}
