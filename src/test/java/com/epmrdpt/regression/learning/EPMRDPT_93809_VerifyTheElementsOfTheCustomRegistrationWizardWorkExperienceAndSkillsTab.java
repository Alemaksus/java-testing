package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertEquals;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithoutTrainings;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_CITY_MINSK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_COMPANY_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_COMPANY_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_COUNTRY_BELARUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DATABASES_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DATE_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DESIGN_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_END_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_OPERATING_SYSTEMS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_PLATFORMS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_POSITION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_POSITION_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_PROGRAMMING_LANGUAGES_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_REGISTRATION_BUTTON_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_SKILL_LEVEL_DESCRIPTION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_START_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_TILL_NOW_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_WORK_EXPERIENCE_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_ADDITIONAL_INFORMATION_LABEL;

import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_93809_VerifyTheElementsOfTheCustomRegistrationWizardWorkExperienceAndSkillsTab",
    groups = {"full", "regression"})
public class EPMRDPT_93809_VerifyTheElementsOfTheCustomRegistrationWizardWorkExperienceAndSkillsTab {

  private static final String TRAINING_NAME = "AutoTest_LearningStudent_Tasks";
  private static final List<String> skillsLabelsList = Arrays.asList(
      getValueOf(REGISTRATION_WIZARD_SCREEN_PROGRAMMING_LANGUAGES_LABEL),
      getValueOf(REGISTRATION_WIZARD_SCREEN_OPERATING_SYSTEMS_LABEL),
      getValueOf(REGISTRATION_WIZARD_SCREEN_PLATFORMS_LABEL),
      getValueOf(REGISTRATION_WIZARD_SCREEN_DATABASES_LABEL),
      getValueOf(REGISTRATION_WIZARD_SCREEN_DESIGN_LABEL)
  );
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
        .clickNextTabButton()
        .clickNextTabButton();
  }

  @Test
  public void checkWorkExperienceTabName() {
    assertEquals(registrationWizardScreen.getWorkExperienceTabText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_WORK_EXPERIENCE_TAB),
        "Work experience tab text is incorrect!");
  }

  @Test
  public void checkCompanyName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getCompanyLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_COMPANY_NAME_LABEL),
        "Company name label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getCompanyInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_COMPANY_NAME_PLACEHOLDER),
        "Company name placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkPosition() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getPositionLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_POSITION_LABEL),
        "Position label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getPositionInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_POSITION_PLACEHOLDER),
        "Position placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkStartDate() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getStartDateLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_START_DATE_LABEL),
        "Start date label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getStartDateInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DATE_PLACEHOLDER),
        "Start date placeholder text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isStartDateCalendarButtonPresent(),
        "Start date calendar button is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkEndDate() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getEndDateLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_END_DATE_LABEL),
        "End date label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getEndDateInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DATE_PLACEHOLDER),
        "End date placeholder text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isEndDateCalendarButtonPresent(),
        "End date calendar button is not present!");
    softAssert.assertEquals(registrationWizardScreen.getTillNowLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_TILL_NOW_LABEL),
        "Till now label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isTillNowCheckboxPresent(),
        "Till now checkbox is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkAdditionalWorkInformation() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getAdditionalWorkInformationLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_ADDITIONAL_INFORMATION_LABEL),
        "Additional work information label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isAdditionalWorkInformationTextFieldPresent(),
        "Additional work information text field is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkSkills() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getSkillsLabelsText(),
        skillsLabelsList,
        "Skills labels text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getSkillsLevelDescriptionLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_SKILL_LEVEL_DESCRIPTION_LABEL),
        "Skills level description label text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkRegisterButton() {
    assertEquals(registrationWizardScreen.getRegistrationButtonText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_REGISTRATION_BUTTON_TEXT).toUpperCase(),
        "Register button text is incorrect!");
  }
}
