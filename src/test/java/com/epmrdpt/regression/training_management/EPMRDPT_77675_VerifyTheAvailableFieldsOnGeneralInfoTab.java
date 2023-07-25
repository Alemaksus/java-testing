package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_DEPARTMENT_AFFILIATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_DATA_PROCESSING_CONSENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_STUDENTS_COUNT_INPUT;

import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77675_VerifyTheAvailableFieldsOnGeneralInfoTab",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_77675_VerifyTheAvailableFieldsOnGeneralInfoTab {

  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingCreationGeneralInfoTab() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager())
        .clickCreateNewButton();
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen()
        .clickTrainingTypeDropDown()
        .selectTrainingTypeByName(
            getValueOf(APPLICATIONS_DEPARTMENT_AFFILIATE))
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test
  public void checkFieldsOnTheGeneralInfoTab() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isAllRequiredFieldsAreMarkedWithLabelDisplayed(),
        "'All required fields are marked with' text label is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingTypeDropDownDisplayed(),
        "Training type DDL is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isEducationalEstablishmentInputFieldDisplayed(),
        "Educational establishment input field is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isDepartmentDropDownDisplayed(),
        "Department DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingNameInputDisplayed(),
        "Training name input field is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingDisplayingNameInputDisplayed(),
        "Displaying name input field is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isStartDateInputDisplayed(),
        "Start date input field is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isEndDateInputDisplayed(),
        "End date input field is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isPlannedStudentsCountFieldDisplayed(),
        "Planned students count field is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isQuestionIconDisplayedByLabelName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_STUDENTS_COUNT_INPUT)),
        "Question icon of the Students count field is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingStudentGroupDropDownDisplayed(),
        "Student group DDL is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingCountryDropDownDisplayed(),
        "Country DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isQuestionIconDisplayedByLabelName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT)),
        "Question icon of the Country DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingCityDropDownDisplayed(),
        "City DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingLabsDropDownPresent(),
        "Labs DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingOwnerDropDownDisplayed(),
        "Owner DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingSupervisorsDropDownDisplayed(),
        "Supervisors DDl is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isDataProcessingConsentLabelDisplayed(),
        "Data Processing Consent label is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isQuestionIconDisplayedByLabelName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_DATA_PROCESSING_CONSENT_LABEL)),
        "Question icon of the Data Processing Consent label is not displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isUpLoadButtonDisplayed(),
        "Upload button is not displayed!");
    softAssert.assertAll();
  }
}
