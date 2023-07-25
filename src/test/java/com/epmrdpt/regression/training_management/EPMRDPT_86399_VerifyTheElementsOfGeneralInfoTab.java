package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_DATA_PROCESSING_CONSENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_DISPLAYING_NAME_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_EDUCATIONAL_ESTABLISHMENT_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_END_DATE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_START_DATE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_SUCCESSFULLY_UPLOADED_FILE_POPUP_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getImportDocumentFolderPath;
import static com.epmrdpt.utils.FileUtils.getNewFile;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import java.nio.file.Paths;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_86399_VerifyTheElementsOfGeneralInfoTab",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_86399_VerifyTheElementsOfGeneralInfoTab {

  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;
  private SoftAssert softAssert;
  private final String DATE_PLACEHOLDER = "DD.MM.YYYY";
  private final String DATA_PROCESSING_CONSENT_FILE_PATH = String
      .format("%s%s%s", getImportDocumentFolderPath(), System.currentTimeMillis(), ".pdf");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    getNewFile(DATA_PROCESSING_CONSENT_FILE_PATH);
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(UserFactory.asTrainingManager())
        .clickCreateNewButton()
        .waitScreenLoading();
  }

  @Test
  public void checkElementsOfTheGeneralInfoTabIfTrainingTypeIsEducationalPractice() {
    String fileName = Paths.get(DATA_PROCESSING_CONSENT_FILE_PATH).getFileName().toString();
    String educationalEstablishmentDDL = getValueOf(
        REACT_GENERAL_INFO_TAB_SCREEN_EDUCATIONAL_ESTABLISHMENT_INPUT);
    String trainingNameInput = getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_NAME_LABEL);
    String displayingNameInput = getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_DISPLAYING_NAME_INPUT);
    String startDateInput = getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_START_DATE_INPUT);
    String endDateInput = getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_END_DATE_INPUT);
    reactGeneralInfoTabScreen
        .clickTrainingTypeDropDown()
        .selectTrainingTypeByName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE))
        .waitAllSpinnersAreNotDisplayed()
        .uploadFile(DATA_PROCESSING_CONSENT_FILE_PATH);
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactGeneralInfoTabScreen.getTextOfPopup(), getValueOf(
            REACT_GENERAL_INFO_TAB_SCREEN_SUCCESSFULLY_UPLOADED_FILE_POPUP_TEXT),
        "File has not been uploaded!");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isAllRequiredFieldsAreMarkedWithLabelDisplayed(),
        "'All required fields are marked with' text label is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingTypeDropDownDisplayed(),
        "Training type DDL is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isEducationalEstablishmentInputFieldDisplayed(),
        "Educational establishment DDl is not displayed!");
    softAssert.assertEquals(
        reactGeneralInfoTabScreen.getPlaceholderOfInputFieldByName(educationalEstablishmentDDL),
        getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT),
        String.format("Placeholders of the '%s' input are not the same!",
            educationalEstablishmentDDL));
    softAssert.assertTrue(reactGeneralInfoTabScreen.isDepartmentDropDownDisplayed(),
        "Department DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingNameInputDisplayed(),
        "Training name input field is not displayed!");
    softAssert.assertEquals(
        reactGeneralInfoTabScreen.getTrainingNameInputFieldPlaceholder(),
        getValueOf(REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER),
        String.format("Placeholders of the '%s' input are not the same!", trainingNameInput));
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingDisplayingNameInputDisplayed(),
        "Displaying name input field is not displayed!");
    softAssert.assertEquals(
        reactGeneralInfoTabScreen.getPlaceholderOfInputFieldByName(displayingNameInput),
        getValueOf(REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER),
        String.format("Placeholders of the '%s' input are not the same!", displayingNameInput));
    softAssert.assertTrue(reactGeneralInfoTabScreen.isStartDateInputDisplayed(),
        "Start date input field is not displayed!");
    softAssert.assertEquals(
        reactGeneralInfoTabScreen.getPlaceholderOfInputFieldByName(startDateInput),
        DATE_PLACEHOLDER,
        String.format("Placeholders of the '%s' input are not the same!", startDateInput));
    softAssert.assertTrue(reactGeneralInfoTabScreen.isEndDateInputDisplayed(),
        "End date input field is not displayed!");
    softAssert.assertEquals(
        reactGeneralInfoTabScreen.getPlaceholderOfInputFieldByName(endDateInput),
        DATE_PLACEHOLDER,
        String.format("Placeholders of the '%s' input are not the same!", endDateInput));
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingCountryDropDownDisplayed(),
        "Country DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isQuestionIconDisplayedByLabelName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT)),
        "Question icon of the Country DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isDataProcessingConsentLabelDisplayed(),
        "Data Processing Consent label is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isQuestionIconDisplayedByLabelName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_DATA_PROCESSING_CONSENT_LABEL)),
        "Question icon of the Data Processing Consent label is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isUpLoadButtonDisplayed(),
        "Upload button is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isUploadedFileDisplayedByName(fileName),
        "Uploaded file is not displayed!");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isDownloadButtonOfUploadedFileDisplayedByName(fileName),
        "Download button is not displayed!");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isCrossButtonOfUploadedFileDisplayedByName(fileName),
        "Cross button is not displayed!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void removeFile() {
    deleteFile(DATA_PROCESSING_CONSENT_FILE_PATH);
  }
}
