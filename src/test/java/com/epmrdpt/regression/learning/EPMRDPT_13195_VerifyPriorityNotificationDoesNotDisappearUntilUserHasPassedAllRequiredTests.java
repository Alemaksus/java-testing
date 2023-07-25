package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_AC_TRAINING_PRIORITY_NOTIFICATION;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13195_VerifyPriorityNotificationDoesNotDisappearUntilUserHasPassedAllRequiredTests",
    groups = {"full", "regression", "student"})
public class EPMRDPT_13195_VerifyPriorityNotificationDoesNotDisappearUntilUserHasPassedAllRequiredTests {

  private TrainingDescriptionScreen trainingDescriptionScreen;
  private HeaderScreen headerScreen;
  private SoftAssert softAssert;

  private final String trainingName = "AUTOTEST WITH AC";
  private String trainingDescriptionPageUrl;
  private String cityOfResidence = "AutoTestCity";
  private String countryOfResidence = "AutoTestCountry";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    trainingDescriptionScreen = new TrainingDescriptionScreen();
    headerScreen = new HeaderScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickTrainingListNavigationLink();
    new TrainingCardsSectionService()
        .openTrainingDescription(trainingName);
    trainingDescriptionPageUrl = trainingDescriptionScreen
        .getCurrentWindowUrl();
    new RegistrationForTrainingService()
        .registerForTraining(cityOfResidence, countryOfResidence);
  }

  @Test(priority = 1)
  public void checkPriorityNotificationDisplayingAfterPageRefresh() {
    softAssert = new SoftAssert();
    trainingDescriptionScreen
        .clickRefreshButton();
    trainingDescriptionScreen
        .waitTrainingStatusTextPresent();
    softAssert.assertTrue(trainingDescriptionScreen
            .isPriorityNotificationDisplayed(),
        "Priority notification isn't displayed after page refresh!");
    softAssert.assertEquals(
        trainingDescriptionScreen
            .getPriorityNotificationText(),
        getValueOf(TRAINING_DESCRIPTION_AC_TRAINING_PRIORITY_NOTIFICATION),
        "Priority notification doesn't mention Registration Test!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkPriorityNotificationDisplayingAfterPageNavigation() {
    headerScreen
        .clickAboutNavigationLink()
        .openPageInNewTab(trainingDescriptionPageUrl);
    trainingDescriptionScreen
        .waitTrainingStatusTextPresent();
    assertTrue(trainingDescriptionScreen.isPriorityNotificationDisplayed(),
        "Priority notification isn't displayed after page navigation!");
  }
}
