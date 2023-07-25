package com.epmrdpt.smoke.edittraining;

import static com.epmrdpt.bo.user.UserFactory.withDepartmentTraining;
import static com.epmrdpt.framework.properties.ApiProperty.CLOSED_TRAINING_REGISTRATION_LINK;
import static com.epmrdpt.framework.properties.ApiPropertyService.getValueOf;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_LINK_COPIED_SUCCESSFULLY_POP_UP;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.AffiliateRegistrationFormScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34277_VerifyThatPossibleToShareTheRegistrationLinkForDepartmentAffiliate",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_34277_VerifyThatPossibleToShareTheRegistrationLinkForDepartmentAffiliate {

  private final String trainingName = "AutoTest_LearningStudentsWorkflowDepartmentAffiliate";
  private final String successfulStatusMessage = getValueOf(
      DETAIL_TRAINING_SCREEN_LINK_COPIED_SUCCESSFULLY_POP_UP);
  private AffiliateRegistrationFormScreen affiliateRegistrationFormScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34277_VerifyThatPossibleToShareTheRegistrationLinkForDepartmentAffiliate(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupRegistrationLinkForDepartmentAffiliate() {
    new LoginService()
        .loginAndSetLanguage(user)
        .clickReactTrainingManagementLink()
        .waitAllSpinnersAreNotDisplayed();
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName);
  }

  @Test(priority = 1)
  public void checkRegistrationLinkCopyMessage() {
    assertEquals(reactDetailTrainingScreen.clickCopyRegistrationLinkButton().getStatusMessageText(),
        successfulStatusMessage, "Registration link did not copied");
  }

  @Test(priority = 2)
  public void checkThatFilledRegistrationFormAppeared() {
    String registrationLinkValue = getEnv() + getValueOf(CLOSED_TRAINING_REGISTRATION_LINK);
    reactDetailTrainingScreen.closeBrowser();
    affiliateRegistrationFormScreen = new AffiliateRegistrationFormScreen();
    affiliateRegistrationFormScreen.openPage(getEnv());
    new LoginService()
        .loginAndSetLanguage(withDepartmentTraining())
        .openPageInNewTab(registrationLinkValue);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertFalse(affiliateRegistrationFormScreen.waitLastNameValueNotEmpty()
        .getLastNameValue().isEmpty(), "'Last name' field is empty!");
    softAssert.assertFalse(affiliateRegistrationFormScreen.waitFirstNameValueNotEmpty()
        .getFirstNameValue().isEmpty(), "'First name' field is empty!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkPersonalInformation() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertFalse(affiliateRegistrationFormScreen.getMiddleNameValue().isEmpty(),
        "'Middle name' field is empty!");
    softAssert.assertFalse(affiliateRegistrationFormScreen.getLastNameAsInPassportValue().isEmpty(),
        "'Last name (as in the passport)' field is empty!");
    softAssert.assertFalse(
        affiliateRegistrationFormScreen.getFirstNameAsInPassportValue().isEmpty(),
        "'First name (as in the passport)' field is empty!");
    softAssert.assertFalse(affiliateRegistrationFormScreen.getEmailValue().isEmpty(),
        "'E-mail' field is empty!");
    softAssert.assertFalse(affiliateRegistrationFormScreen.getPhoneNumberValue().isEmpty(),
        "'Phone' field is empty!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkUniversityInformation() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertFalse(affiliateRegistrationFormScreen.getUniversityValue().isEmpty(),
        "'Educational establishment' field is empty!");
    softAssert.assertFalse(affiliateRegistrationFormScreen.getFacultyValue().isEmpty(),
        "'Faculty' field is empty!");
    softAssert.assertFalse(affiliateRegistrationFormScreen.getDepartmentValue().isEmpty(),
        "'Department' field is empty!");
    softAssert.assertFalse(affiliateRegistrationFormScreen
            .waitScreenLoading()
            .clickStudentGroupArrowButton()
            .clickStudentGroupField()
            .getStudentGroupText().isEmpty(),
        "'Student group' field is empty!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkThatUserCanRegisterToGroup() {
    assertTrue(affiliateRegistrationFormScreen
            .clickDataProcessingCheckbox()
            .clickRegisterButton()
            .isConfirmationRegistrationPopupDisplayed(),
        "User can't registration to the study group!");
  }
}
