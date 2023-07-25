package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithoutTrainings;
import static com.epmrdpt.framework.properties.ApiProperty.CLOSED_TRAINING_REGISTRATION_LINK;
import static com.epmrdpt.framework.properties.ApiPropertyService.getValueOf;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.AFFILIATE_REGISTRATION_FORM_SCREEN_DATA_PROCESSING_CONSENT_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.AFFILIATE_REGISTRATION_FORM_SCREEN_FIRST_NAME_AS_IN_PASSPORT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.AFFILIATE_REGISTRATION_FORM_SCREEN_LAST_NAME_AS_IN_PASSPORT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.AFFILIATE_REGISTRATION_FORM_SCREEN_MIDDLE_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.AFFILIATE_REGISTRATION_FORM_SCREEN_MIDDLE_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.AFFILIATE_REGISTRATION_FORM_SCREEN_REGISTRATION_HINT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.AFFILIATE_REGISTRATION_FORM_SCREEN_STUDENT_GROUP_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_TO_EVENT_POP_UP_SCREEN_CONTACT_PHONE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_TO_EVENT_POP_UP_SCREEN_EMAIL_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DEPARTMENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_EDUCATIONAL_ESTABLISHMENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_FACULTY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_FIRST_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_FIRST_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_LAST_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_LAST_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_REGISTRATION_BUTTON_TEXT;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.AffiliateRegistrationFormScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_78297_VerifyTheElementsOfTheDepartmentAffiliateRegistrationWizard",
    groups = {"full", "regression"})
public class EPMRDPT_78297_VerifyTheElementsOfTheDepartmentAffiliateRegistrationWizard {

  private static final String PHONE_PLACEHOLDER = "+xxx...";
  private AffiliateRegistrationFormScreen affiliateRegistrationFormScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void getTrainingRegistrationLinkAndGoToRegistrationPage() {
    String registrationLinkValue = getEnv() + getValueOf(CLOSED_TRAINING_REGISTRATION_LINK);
    new LoginService()
        .loginAndSetLanguage(withSimplePermissionAndWithoutTrainings())
        .openPageInNewTab(registrationLinkValue);
    affiliateRegistrationFormScreen = new AffiliateRegistrationFormScreen();
  }

  @Test
  public void checkRegistrationHint() {
    assertEquals(affiliateRegistrationFormScreen.getRegistrationHintText(),
        getValueOf(AFFILIATE_REGISTRATION_FORM_SCREEN_REGISTRATION_HINT),
        "Registration hint text is incorrect!");
  }

  @Test
  public void checkFirstName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getFirstNameLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_FIRST_NAME_LABEL),
        "First name label text is incorrect!");
    softAssert.assertEquals(affiliateRegistrationFormScreen.getFirstNamePlaceholder(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_FIRST_NAME_PLACEHOLDER),
        "First name placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkLastName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getLastNameLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_LAST_NAME_LABEL),
        "Last name label text is incorrect!");
    softAssert.assertEquals(affiliateRegistrationFormScreen.getLastNamePlaceholder(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_LAST_NAME_PLACEHOLDER),
        "Last name placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkMiddleName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getMiddleNameLabelText(),
        getValueOf(AFFILIATE_REGISTRATION_FORM_SCREEN_MIDDLE_NAME_LABEL),
        "Middle name label text is incorrect!");
    softAssert.assertEquals(affiliateRegistrationFormScreen.getMiddleNamePlaceholder(),
        getValueOf(AFFILIATE_REGISTRATION_FORM_SCREEN_MIDDLE_NAME_PLACEHOLDER),
        "Middle name placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkFirstNameAsInPassport() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getFirstNameAsInPassportLabelText(),
        getValueOf(AFFILIATE_REGISTRATION_FORM_SCREEN_FIRST_NAME_AS_IN_PASSPORT_LABEL),
        "First name as in passport label text is incorrect!");
    softAssert.assertEquals(affiliateRegistrationFormScreen.getFirstNameAsInPassportPlaceholder(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_FIRST_NAME_PLACEHOLDER),
        "First name as in passport placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkLastNameAsInPassport() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getLastNameAsInPassportLabelText(),
        getValueOf(AFFILIATE_REGISTRATION_FORM_SCREEN_LAST_NAME_AS_IN_PASSPORT_LABEL),
        "Last name as in passport label text is incorrect!");
    softAssert.assertEquals(affiliateRegistrationFormScreen.getLastNameAsInPassportPlaceholder(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_LAST_NAME_PLACEHOLDER),
        "Last name as in passport placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkEmail() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getEmailLabelText(),
        getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_EMAIL_LABEL),
        "Email label text is incorrect!");
    softAssert.assertTrue(affiliateRegistrationFormScreen.isEmailInputPresent(),
        "E-mail input is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkContactPhone() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getPhoneLabelText(),
        getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_CONTACT_PHONE_LABEL),
        "Contact phone label text is incorrect!");
    softAssert.assertEquals(affiliateRegistrationFormScreen.getPhonePlaceholder(),
        PHONE_PLACEHOLDER,
        "Contact phone placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkEducationalEstablishment() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getUniversityLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_EDUCATIONAL_ESTABLISHMENT_LABEL),
        "Educational establishment label text is incorrect!");
    softAssert.assertTrue(affiliateRegistrationFormScreen.isUniversityInputPresent(),
        "Educational establishment input is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkFaculty() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getFacultyLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_FACULTY_LABEL),
        "Faculty label text is incorrect!");
    softAssert.assertTrue(affiliateRegistrationFormScreen.isFacultyInputPresent(),
        "Faculty input is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkDepartment() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getDepartmentLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DEPARTMENT_LABEL),
        "Department label text is incorrect!");
    softAssert.assertTrue(affiliateRegistrationFormScreen.isDepartmentInputPresent(),
        "Department input is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkStudentGroup() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(affiliateRegistrationFormScreen.getStudentGroupLabelText(),
        getValueOf(AFFILIATE_REGISTRATION_FORM_SCREEN_STUDENT_GROUP_LABEL),
        "Student group label text is incorrect!");
    softAssert.assertTrue(affiliateRegistrationFormScreen.isStudentGroupDDLPresent(),
        "Student group DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkDataProcessingConsent() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(affiliateRegistrationFormScreen.isDataProcessingConsentCheckboxPresent(),
        "Data processing consent checkbox is not present!");
    softAssert.assertEquals(affiliateRegistrationFormScreen.getDataProcessingConsentText(),
        getValueOf(AFFILIATE_REGISTRATION_FORM_SCREEN_DATA_PROCESSING_CONSENT_TEXT),
        "Data processing consent text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkRegisterButton() {
    assertEquals(affiliateRegistrationFormScreen.getRegisterButtonText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_REGISTRATION_BUTTON_TEXT).toUpperCase(),
        "Register button text is incorrect!");
  }
}
