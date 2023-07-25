package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithoutTrainings;
import static org.testng.Assert.assertEquals;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_EDUCATION_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_CITY_OF_STUDY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_EDUCATIONAL_ESTABLISHMENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_FACULTY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DEPARTMENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_EDUCATION_FORM_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DEGREE_INFORMATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_ADMISSION_YEAR_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_GRADUATION_YEAR_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_ASSIGNMENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DISTRIBUTION_COMPANY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DISTRIBUTION_COMPANY_EPAM_CHECKBOX_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_ENGLISH_LEVEL_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_ADDITIONAL_INFORMATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_PROFESSIONAL_ACTIVITIES_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_COUNTRY_BELARUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_CITY_MINSK;

import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_93808_VerifyTheElementsOfTheCustomRegistrationWizardEducationTab",
    groups = {"full", "regression"})
public class EPMRDPT_93808_VerifyTheElementsOfTheCustomRegistrationWizardEducationTab {

  private static final String TRAINING_NAME = "AutoTest_LearningStudent_Tasks";
  private RegistrationWizardScreen registrationWizardScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToEducationTabOfRegistrationWizard() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermissionAndWithoutTrainings());
    registrationWizardScreen = new TrainingCardsSectionService()
        .openTrainingDescription(TRAINING_NAME)
        .clickRegisterButton()
        .chooseCountryOfResidence(getValueOf(REGISTRATION_WIZARD_SCREEN_COUNTRY_BELARUS))
        .chooseCityOfResidence(getValueOf(REGISTRATION_WIZARD_SCREEN_CITY_MINSK))
        .clickNextTabButton();
  }

  @Test
  public void checkTabName() {
    assertEquals(registrationWizardScreen.getEducationTabText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_EDUCATION_TAB),
        "Education tab text is incorrect!");
  }

  @Test
  public void checkCityOfStudy() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getCityOfStudyLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_CITY_OF_STUDY_LABEL),
        "City of study label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isCityOfStudyDDLPresent(),
        "City of study DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkEducationalEstablishment() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getEducationalEstablishmentLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_EDUCATIONAL_ESTABLISHMENT_LABEL),
        "Educational establishment label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isEducationalEstablishmentDDLPresent(),
        "Educational establishment DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkFaculty() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getFacultyLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_FACULTY_LABEL),
        "Faculty label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isFacultyDDLPresent(),
        "Faculty DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkDepartment() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getDepartmentLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DEPARTMENT_LABEL),
        "Department label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isDepartmentDDLPresent(),
        "Department DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkEducationForm() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getEducationFormLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_EDUCATION_FORM_LABEL),
        "Education form label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isEducationFormDDLPresent(),
        "Education form DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkDegreeInformation() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getDegreeInformationLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DEGREE_INFORMATION_LABEL),
        "Degree information label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isDegreeInformationDDLPresent(),
        "Degree information DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkAdmissionYear() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getAdmissionYearLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_ADMISSION_YEAR_LABEL),
        "Admission year label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isAdmissionYearDDLPresent(),
        "Admission year DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkGraduationYear() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getGraduationYearLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_GRADUATION_YEAR_LABEL),
        "Graduation year label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isGraduationYearDDLPresent(),
        "Graduation year DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkAssignment() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getAssignmentLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_ASSIGNMENT_LABEL),
        "Assignment label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isAssignmentDDLPresent(),
        "Assignment DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkDistributionCompany() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getDistributionCompanyLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DISTRIBUTION_COMPANY_LABEL),
        "Distribution company label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isDistributionCompanyInputPresent(),
        "Distribution company input is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkDistributionCompanyEPAMCheckbox() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getDistributionCompanyEPAMCheckboxLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DISTRIBUTION_COMPANY_EPAM_CHECKBOX_LABEL),
        "Distribution company EPAM checkbox label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isDistributionCompanyEPAMCheckboxPresent(),
        "Distribution company EPAM checkbox is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkEnglishLevel() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getEnglishLevelLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_ENGLISH_LEVEL_LABEL),
        "English level label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isEnglishLevelDDLPresent(),
        "English level DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkAdditionalInformation() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getAdditionalInformationLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_ADDITIONAL_INFORMATION_LABEL),
        "Additional information label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isAdditionalInformationTextFieldPresent(),
        "Additional information text field is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkProfessionalActivities() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getProfessionalActivitiesLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_PROFESSIONAL_ACTIVITIES_LABEL),
        "Professional activities label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isProfessionalActivitiesTextFieldPresent(),
        "Professional activities text field is not present!");
    softAssert.assertAll();
  }
}
