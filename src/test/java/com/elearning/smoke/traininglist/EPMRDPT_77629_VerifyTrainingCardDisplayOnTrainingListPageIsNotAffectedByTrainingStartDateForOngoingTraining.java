package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_REGISTER_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_SUBSCRIBE_BUTTON;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77629_VerifyTrainingCardDisplayOnTrainingListPageIsNotAffectedByTrainingStartDateForOngoingTraining",
    groups = {"full", "smoke"})
public class EPMRDPT_77629_VerifyTrainingCardDisplayOnTrainingListPageIsNotAffectedByTrainingStartDateForOngoingTraining {

  private TrainingBlockScreen trainingBlockScreen;
  private static String subscribeButton = getValueOf(TRAINING_LIST_SUBSCRIBE_BUTTON);
  private static String registerButton = getValueOf(TRAINING_DESCRIPTION_REGISTER_BUTTON);
  private static String registrationIsOpenTrainingName = "AutoTest_REGISTRATION_OPEN_Survey";
  private static String plannedTrainingName = "AutoTest_StudentsForAnotherTraining";
  private static Map<String, String> trainings;

  static {
    trainings = new HashMap<>();
    trainings.put("AutoTest_TrainingFor_Almaty_City", registerButton);
    trainings.put("AutoTest_NotificationLanguageVerification", registerButton);
    trainings.put("AutoTest_Daily_Filter", subscribeButton);
    trainings.put("AutomatedTesting Training For Checkbox search", subscribeButton);
    trainings.put(registrationIsOpenTrainingName, registerButton);
    trainings.put(plannedTrainingName, subscribeButton);
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingManagement() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @BeforeMethod(inheritGroups = false, alwaysRun = true)
  public void changeTrainingsDatesAndGoToTrainingList() {
    ReactTrainingManagementService reactTrainingManagementService =
        new ReactTrainingManagementService();
    reactTrainingManagementService
        .changeTrainingStartDateToPresentAndClickPlaningTitle(registrationIsOpenTrainingName);
    reactTrainingManagementService
        .changeTrainingStartDateToPresentAndClickPlaningTitle(plannedTrainingName);
    new ReactHeaderScreen()
        .clickTrainingListLink();
    trainingBlockScreen = new TrainingBlockScreen()
        .waitTrainingCardsVisibility()
        .cleanLocationFilterIfNeed()
        .waitTrainingCardsVisibility();
    if (trainingBlockScreen.isViewMoreTrainingsLinkDisplayedNoWait()) {
      trainingBlockScreen.clickViewMoreTrainingsLink()
          .waitViewMoreTrainingsLinkAbsent();
    }
  }

  @Test
  public void checkTrainingsOnTrainingList() {
    SoftAssert softAssert = new SoftAssert();
    for (String training : trainings.keySet()) {
      softAssert.assertTrue(trainingBlockScreen.isTrainingCardByNameDisplayed(training),
          String.format("Training '%s' is not displayed in Training List!", training));
      softAssert.assertEquals(trainingBlockScreen.getTrainingCardButtonText(training),
          trainings.get(training), String.format(
              "Training '%s' is not displayed with a '%s' button on the training card",
              training, trainings.get(training)));
    }
    softAssert.assertAll();
  }
}
