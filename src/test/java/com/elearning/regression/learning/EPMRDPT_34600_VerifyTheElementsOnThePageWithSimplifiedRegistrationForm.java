package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static com.epmrdpt.bo.user.UserFactory.userWithDataForSimplifiedRegistrationWizard;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_BASIC_INFO_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_CONTACT_PHONE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DOCUMENTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_FIRST_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_LAST_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_REGISTRATION_BUTTON_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_REQUIRED_FIELD_HINT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_UPLOAD_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_UPLOAD_FILE_HINT;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34600_VerifyTheElementsOnThePageWithSimplifiedRegistrationForm",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34600_VerifyTheElementsOnThePageWithSimplifiedRegistrationForm {

  private final String trainingName = "AutoTest_WithSimplifiedRegistrationForm";
  private final String EMAIL_LABEL_TEXT = "E-mail *";
  private RegistrationWizardScreen registrationWizardScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(userForRegistrationWizard());
    registrationWizardScreen = new TrainingCardsSectionService()
        .openTrainingDescription(trainingName)
        .waitTrainingStatusTextPresent().clickRegisterButton()
        .waitIncorrectValueErrorMessageDisappear()
        .waitFirstNameAttributeValueIsNotEmpty();
  }

  @Test(priority = 1)
  public void checkUserDataInSimplifiedRegistrationWizardCorrect() {
    assertEquals(
        userWithDataForSimplifiedRegistrationWizard(),
        new RegistrationForTrainingService().getUserRequiredDataFromSimplifiedRegistrationWizard(),
        "User data in 'Registration Wizard' not equals with installed in the profile!");
  }

  @Test(priority = 1)
  public void checkBasicInfoTab() {
    assertEquals(registrationWizardScreen.getBasicInfoTabText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_BASIC_INFO_TAB),
        "Basic info' tab has incorrect text!");
  }

  @Test(priority = 1)
  public void checkLabelsFromSectionSimplifiedRegistrationWizard() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getFirstNameLabelText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_FIRST_NAME_LABEL),
        "'First name' label has incorrect text!");
    softAssert.assertEquals(registrationWizardScreen.getLastNameLabelText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_LAST_NAME_LABEL),
        "'Last name' label has incorrect text!");
    softAssert.assertEquals(registrationWizardScreen.getEmailLabelText(), EMAIL_LABEL_TEXT,
        "'E-mail' label has incorrect text!");
    softAssert.assertEquals(registrationWizardScreen.getPhoneLabelText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_CONTACT_PHONE_LABEL),
        "'Contact phone' label has incorrect text!");
    softAssert.assertEquals(registrationWizardScreen.getDocumentsLabelText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_DOCUMENTS_LABEL),
        "'Documents' label has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkHintTextsSimplifiedRegistrationWizard() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getUploadButtonText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_UPLOAD_BUTTON),
        "'Upload' button has incorrect text!");
    softAssert.assertEquals(registrationWizardScreen.getUploadFilesHintText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_UPLOAD_FILE_HINT),
        "'Upload file' hint has incorrect text!");
    softAssert.assertEquals(registrationWizardScreen.getRequiredFieldHintText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_REQUIRED_FIELD_HINT),
        "'All required fields' hint has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkButtonTextsSimplifiedRegistrationWizard() {
    assertEquals(registrationWizardScreen.getRegistrationButtonText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_REGISTRATION_BUTTON_TEXT)
            .toUpperCase(),
        "'Registration' button has incorrect text!");
  }
}

