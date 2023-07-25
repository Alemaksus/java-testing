package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77640_VerifyTheFieldsThatAreNotPreFilledInWhenCopyingADepartmentAffiliateTraining",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_77640_VerifyTheFieldsThatAreNotPreFilledInWhenCopyingADepartmentAffiliateTraining {

  private static final String TRAINING_NAME = "AutoTestDepartmentAffiliateFormGroup";
  private static final String TRAINING_COPY_NAME = "Copy - AutoTestDepartmentAffiliateFormGroup";

  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void openDepartmentAffiliateTrainingAsTrainingManager() {
    reactDetailTrainingScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickTrainingNameByName(TRAINING_NAME)
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test(priority = 1)
  public void checkThatUserRedirectedToCopyPageOpenedInNewCreateTrainingPage() {
    reactDetailTrainingScreen.clickCreateCopyButton()
        .switchToLastWindow();
    reactGeneralInfoTabScreen = reactDetailTrainingScreen.waitAllSpinnersAreNotDisplayed()
        .clickGeneralInfoTabFromTrainingScreen();
    assertEquals(reactGeneralInfoTabScreen.getTrainingNameText(), TRAINING_COPY_NAME,
        "The user is not redirected to a copy page opened in a new Create training page!");
  }

  @Test(priority = 2)
  public void checkThatFieldsNotCopiedToTrainingAndNotPrefilledIn() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(isEmpty(reactGeneralInfoTabScreen.getTrainingStartDateValue()),
        "The 'Training start date' field is pre-filled!");
    softAssert.assertTrue(isEmpty(reactGeneralInfoTabScreen.getTrainingEndDateValue()),
        "The 'Training end date' field is pre-filled!");
    softAssert.assertTrue(isEmpty(reactGeneralInfoTabScreen.getPlannedStudentCountValue()),
        "The 'Planned student count' field is pre-filled!");
    softAssert.assertTrue(isEmpty(reactGeneralInfoTabScreen.getOwnerValue()),
        "The 'Owner' field is pre-filled!");
    softAssert.assertEquals(reactGeneralInfoTabScreen.getCountryValue(),
        getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT),
        "The 'Country' field is pre-filled!");
    softAssert.assertEquals(reactGeneralInfoTabScreen.getCityValue(),
        getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT),
        "The 'City' field is pre-filled!");
    softAssert.assertEquals(reactGeneralInfoTabScreen.getLabsValue(),
        getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT),
        "The 'Labs' field is pre-filled!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isUploadButtonBoxEnabled(),
        "The 'Data Processing Consent' is pre-filled!");
    softAssert.assertAll();
  }
}
