package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_71919_VerifyThatNoteIsDisplayedIfNoSDPoolIsLinkedToTraining",
    groups = {"full", "react", "regression"})
public class EPMRDPT_71919_VerifyThatNoteIsDisplayedIfNoSDPoolIsLinkedToTraining {

  private List<String> trainingNames = List.of("AutomatedTesting Training For Checkbox search",
      "AutoTest_NotificationLanguageVerification",
      "AutoTest_SecondStage",
      ".Net Summer 2022",
      "AutoTestWithGroupInLearning",
      "AutoTest_StartedStatus",
      "AutoTest_FinishedStatus");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenTrainingManagementPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager())
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test
  public void verifyNoteOnIntegrationTab() {
    SoftAssert softAssert = new SoftAssert();
    ReactTrainingManagementService reactTrainingManagementService = new ReactTrainingManagementService();
    for (String training : trainingNames) {
      softAssert.assertTrue(reactTrainingManagementService
              .searchTrainingByNameAndRedirectOnIt(training)
              .waitDataLoading()
              .clickIntegrationTabFromTrainingScreen()
              .isLinkedToSDNoteNotDisplayed(),
          String.format("Note for training '%s' isn't displayed!", training));
      new ReactDetailTrainingScreen().clickPlanningTitle();
    }
    softAssert.assertAll();
  }
}
