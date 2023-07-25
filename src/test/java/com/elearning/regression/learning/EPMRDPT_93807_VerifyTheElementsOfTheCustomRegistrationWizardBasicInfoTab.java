package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertEquals;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithoutTrainings;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DOCUMENTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_PORTFOLIO_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_UPLOAD_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_UPLOAD_FILE_HINT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_CITY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_COMMUNICATION_METHOD_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_COUNTRY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_CURRENT_LOCATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_PORTFOLIO_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_BASIC_INFO_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_BIRTH_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_DATE_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_CONTACT_PHONE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_FIRST_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_FIRST_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_LAST_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_LAST_NAME_PLACEHOLDER;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_93807_VerifyTheElementsOfTheCustomRegistrationWizardBasicInfoTab",
    groups = {"full", "regression"})
public class EPMRDPT_93807_VerifyTheElementsOfTheCustomRegistrationWizardBasicInfoTab {

  private static final String TRAINING_NAME = "AutoTest_LearningStudent_Tasks";
  private static final String EMAIL_LABEL = "E-mail *";
  private static final String EMAIL_PLACEHOLDER = "mail@example.com";
  private static final String CONTACT_PHONE_PLACEHOLDER = "+xxx...";
  private static final Map<String, String> socialNetworkNamePlaceholderMap;

  private RegistrationWizardScreen registrationWizardScreen;

  static {
    socialNetworkNamePlaceholderMap = new HashMap<>();
    socialNetworkNamePlaceholderMap.put("GitHub", "GitHub");
    socialNetworkNamePlaceholderMap.put("Skype", "Skype name");
    socialNetworkNamePlaceholderMap.put("LinkedIn", "LinkedIn");
    socialNetworkNamePlaceholderMap.put("Telegram", "Telegram");
    socialNetworkNamePlaceholderMap.put("Odnoklassniki", "OK");
    socialNetworkNamePlaceholderMap.put("Vk", "VK id");
    socialNetworkNamePlaceholderMap.put("Facebook", "Facebook");
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToGeneralInfoTabOfRegistrationWizard() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermissionAndWithoutTrainings());
    registrationWizardScreen = new TrainingCardsSectionService()
        .openTrainingDescription(TRAINING_NAME)
        .clickRegisterButton();
  }

  @Test
  public void checkTabName() {
    assertEquals(registrationWizardScreen.getBasicInfoTabText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_BASIC_INFO_TAB),
        "Basic info tab text is incorrect!");
  }

  @Test
  public void checkFirstName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getFirstNameLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_FIRST_NAME_LABEL),
        "First name label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getFirstNameInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_FIRST_NAME_PLACEHOLDER),
        "First name placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkLastName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getLastNameLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_LAST_NAME_LABEL),
        "Last name label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getLastNameInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_LAST_NAME_PLACEHOLDER),
        "Last name placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkEmail() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getEmailLabelText(),
        EMAIL_LABEL,
        "E-mail label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getEmailInputPlaceholderText(),
        EMAIL_PLACEHOLDER,
        "E-mail placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkContactPhone() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getPhoneLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_CONTACT_PHONE_LABEL),
        "Contact phone label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getContactPhoneInputPlaceholderText(),
        CONTACT_PHONE_PLACEHOLDER,
        "Contact phone placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkBirthDate() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getBirthDateLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_BIRTH_DATE_LABEL),
        "Birth date label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isBirthDateCalendarButtonPresent(),
        "Birth date calendar button is not present!");
    softAssert.assertEquals(registrationWizardScreen.getBirthDateInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DATE_PLACEHOLDER),
        "Birth date placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkSocialNetwork() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(registrationWizardScreen.isSocialNetworkLabelPresent(),
        "Social network label is not present or has incorrect text!");
    for (String socialNetwork : socialNetworkNamePlaceholderMap.keySet()) {
      registrationWizardScreen.chooseSocialNetwork(socialNetwork);
      softAssert.assertEquals(registrationWizardScreen.getSocialNetworkInputPlaceholderText(),
          socialNetworkNamePlaceholderMap.get(socialNetwork),
          String.format("Social network placeholder for %s is incorrect", socialNetwork));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkCurrentLocationLabel() {
    assertEquals(registrationWizardScreen.getCurrentLocationLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_CURRENT_LOCATION_LABEL));
  }

  @Test
  public void checkCountry() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getCountryLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_COUNTRY_LABEL),
        "Country label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isCountryDDLPresent(),
        "Country DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkCity() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getCityLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_CITY_LABEL),
        "City label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isCityDDLPresent(),
        "City DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkCommunicationMethod() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getCommunicationMethodLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_COMMUNICATION_METHOD_LABEL),
        "Communication method label text is incorrect!");
    softAssert.assertTrue(registrationWizardScreen.isCommunicationMethodDDLPresent(),
        "Communication method DDL is not present!");
    softAssert.assertAll();
  }

  @Test
  public void checkPortfolio() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getPortfolioLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_PORTFOLIO_LABEL),
        "Portfolio label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getPortfolioInputPlaceholderText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_PORTFOLIO_PLACEHOLDER),
        "Portfolio input placeholder text is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkDocumentsBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(registrationWizardScreen.getDocumentsLabelText(),
        getValueOf(REGISTRATION_WIZARD_SCREEN_DOCUMENTS_LABEL),
        "Documents label text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getUploadFilesHintText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_UPLOAD_FILE_HINT),
        "Upload files hint text is incorrect!");
    softAssert.assertEquals(registrationWizardScreen.getUploadButtonText(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_UPLOAD_BUTTON),
        "Upload button text is incorrect!");
    softAssert.assertAll();
  }
}
