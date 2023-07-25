package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77714_77715_VerifyThatSelectLanguageButtonIsAvailableByAUserFromAnyTabDuringTrainingCreation",
    groups = {"full", "smoke"})
public class EPMRDPT_77714_77715_VerifyThatSelectLanguageButtonIsAvailableByAUserFromAnyTabDuringTrainingCreation {

  private final String trainingName = "AutoTest_SecondStage";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink();
    reactDetailTrainingScreen = reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(
        trainingName);
  }

  @Test(priority = 1)
  public void checkThatTrainingIsOpened() {
    assertEquals(reactDetailTrainingScreen.waitAllSpinnersAreNotDisplayed().getTrainingTitleText(),
        trainingName, trainingName + " isn't opened!");
  }

  @Test(priority = 2)
  public void checkSelectLanguageButtonIsDisplayedFromAnyTab() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactDetailTrainingScreen.isTrainingStatusByStatusNameActive(
            getValueOf(REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS)),
        "Draft status is not active by default");
    softAssert.assertTrue(reactDetailTrainingScreen.isSelectLanguageButtonDisplayed(),
        "Select language button is not displayed in Draft status");
    reactTrainingManagementService.setTrainingStatusByText(
        getValueOf(REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS));
    softAssert.assertTrue(reactDetailTrainingScreen.isSelectLanguageButtonDisplayed(),
        "Select language button is not displayed in Planned status");
    reactDetailTrainingScreen.clickRegistrationOpenStatusButton()
        .clickConfirmationOfChangingStaffingDeskStatusButton()
        .waitNotificationPopUpVisibility()
        .closeNotificationPopUp();
    softAssert.assertTrue(reactDetailTrainingScreen.isSelectLanguageButtonDisplayed(),
        "Select language button is not displayed in Registration open status");
    reactTrainingManagementService.setTrainingStatusByText(
        getValueOf(REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS));
    softAssert.assertTrue(reactDetailTrainingScreen.isSelectLanguageButtonDisplayed(),
        "Select language button is not displayed in Registration closed status");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTheElementsOfTheSelectLanguageField() {
    reactTrainingManagementService.setTrainingStatusByText(
        getValueOf(REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS));
    reactDetailTrainingScreen.clickSelectLanguageButton()
        .clickRussianLanguageButton()
        .clickUkrainianLanguageButton();
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactDetailTrainingScreen.isEnglishLanguageButtonDisplayedNoWait(),
        "English element is not displayed");
    softAssert.assertTrue(reactDetailTrainingScreen.isRussianLanguageButtonDisplayedNoWait(),
        "Russian element is not displayed");
    softAssert.assertTrue(
        reactDetailTrainingScreen.isCrossOnRussianLocalizationButtonDisplayedNoWait(),
        "Cross icon of russian localization is not displayed");
    softAssert.assertTrue(reactDetailTrainingScreen.isUkrainianLanguageButtonDisplayedNoWait(),
        "Ukrainian element is not displayed");
    softAssert.assertTrue(
        reactDetailTrainingScreen.isCrossOnUkrainianLocalizationButtonDisplayedNoWait(),
        "Cross icon of ukrainian localization is not displayed");
    softAssert.assertAll();
  }
}
