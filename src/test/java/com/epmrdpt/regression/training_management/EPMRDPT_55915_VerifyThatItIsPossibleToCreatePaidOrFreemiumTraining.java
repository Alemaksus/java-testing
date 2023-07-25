package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PUBLIC_OFFER_LABEL;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactAdditionalInfoTabScreen;
import com.epmrdpt.screens.ReactDescriptionTabScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_55915_VerifyThatItIsPossibleToCreatePaidOrFreemiumTraining",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_55915_VerifyThatItIsPossibleToCreatePaidOrFreemiumTraining {

  private User user;
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;
  private ReactDescriptionTabScreen reactDescriptionTabScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_55915_VerifyThatItIsPossibleToCreatePaidOrFreemiumTraining(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .clickCreateNewButton();

  }

  @Test(priority = 1)
  public void checkAllAdditionalFieldsForPaidTrainings() {
    SoftAssert softAssert = new SoftAssert();
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen().waitScreenLoading()
        .clickPaidButtonFromPricingDdl();
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingPriceInputDisplayed(),
        "Training price input field is not displayed for paid training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingPriceRequiredMarkLabelDisplayed(),
        "Training price required mark label is not displayed for paid training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingCurrencyInputDisplayed(),
        "Training currency input field is not displayed for paid training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingCurrencyRequiredMarkLabelDisplayed(),
        "Training currency required mark label is not displayed for paid training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingVatInputDisplayed(),
        "Training value added tax input field is not displayed for paid training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingVatRequiredMarkLabelDisplayed(),
        "Training value added tax required mark label is not displayed for paid training");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isQuestionIconDisplayedByLabelName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PUBLIC_OFFER_LABEL)),
        "Training question icon is not displayed for paid training");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isTrainingPublicOfferRequiredMarkLabelDisplayed(),
        "Training public offer required mark label is not displayed for paid training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingAddPublicOfferButtonDisplayed(),
        "Training add public offer button is not displayed for paid training");
    softAssert.assertAll();
    reactGeneralInfoTabScreen.clickRefreshButton();
  }

  @Test(priority = 2)
  public void checkAllAdditionalFieldsForFreemiumTrainings() {
    SoftAssert softAssert = new SoftAssert();
    reactGeneralInfoTabScreen.waitScreenLoading().clickFreemiumButtonFromPricingDdl();
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingPriceInputDisplayed(),
        "Training price input field is not displayed for freemium training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingPriceRequiredMarkLabelDisplayed(),
        "Training price required mark label is not displayed for freemium training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingCurrencyInputDisplayed(),
        "Training currency input field is not displayed for freemium training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingCurrencyRequiredMarkLabelDisplayed(),
        "Training currency required mark label is not displayed for freemium training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingVatInputDisplayed(),
        "Training value added tax input field is not displayed for freemium training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingVatRequiredMarkLabelDisplayed(),
        "Training value added tax required mark label is not displayed for freemium training");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isQuestionIconDisplayedByLabelName(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PUBLIC_OFFER_LABEL)),
        "Training question icon is not displayed for paid training");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isTrainingPublicOfferRequiredMarkLabelDisplayed(),
        "Training public offer required mark label is not displayed for freemium training");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTrainingAddPublicOfferButtonDisplayed(),
        "Training add public offer button is not displayed for freemium training");
    reactDescriptionTabScreen = new ReactAdditionalInfoTabScreen().clickDescriptionTabFromTrainingScreen();
    softAssert.assertTrue(reactDescriptionTabScreen.isPaidConsultationLabelDisplayed(),
        "Paid consultation label is not displayed for freemium training");
    softAssert.assertTrue(reactDescriptionTabScreen.isPaidConsultationInfoFieldDisplayed(),
        "Paid consultation label is not displayed for freemium training");
    softAssert.assertAll();
  }
}
