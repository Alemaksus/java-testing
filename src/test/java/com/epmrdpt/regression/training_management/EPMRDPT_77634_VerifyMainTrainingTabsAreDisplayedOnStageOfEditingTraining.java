package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactAdditionalInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77634_VerifyMainTrainingTabsAreDisplayedOnStageOfEditingTraining",
    groups = {"full", "regression"})
public class EPMRDPT_77634_VerifyMainTrainingTabsAreDisplayedOnStageOfEditingTraining {

  private final String trainingName = "AutoTest_TrainerWorkflow";
  private ReactAdditionalInfoTabScreen reactAdditionalInfoTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGotToTraining() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitScreenLoaded();
    reactAdditionalInfoTabScreen = new ReactAdditionalInfoTabScreen();
  }

  @Test
  public void checkTrainingTabs() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactAdditionalInfoTabScreen.isGeneralInfoTabDisplayedNoWait(),
        "'General Info' tab is not displayed!");
    softAssert.assertTrue(
        reactAdditionalInfoTabScreen.isDescriptionTabDisplayedNoWait(),
        "'Description' tab is not displayed!");
    softAssert.assertTrue(
        reactAdditionalInfoTabScreen.isAutoReplyTabDisplayedNoWait(),
        "'Autoreply' tab is not displayed!");
    softAssert.assertTrue(
        reactAdditionalInfoTabScreen.isRegistrationFormTabDisplayedNoWait(),
        "'Registration form' tab is not displayed!");
    softAssert.assertTrue(
        reactAdditionalInfoTabScreen.isSurveyAndTestingTabDisplayedNoWait(),
        "'Survey and Testing' tab is not displayed!");
    softAssert.assertTrue(
        reactAdditionalInfoTabScreen.isIntegrationTabDisplayedNoWait(),
        "'Integration' tab is not displayed!");
    softAssert.assertAll();
  }
}
