package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_LOCATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_ONGOING_DURATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_PLANNED_LANGUAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_PROGRAM_HEADER;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactDescriptionTabScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.screens.TrainingProgramScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14301_VerifyThatTrainingManagerCanSetUpTrainingProgram",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_14301_VerifyThatTrainingManagerCanSetUpTrainingProgram {

  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactDescriptionTabScreen reactDescriptionTabScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private TrainingProgramScreen trainingProgramScreen;
  private SoftAssert softAssert;
  private ReactTrainingManagementService reactTrainingManagementService;

  private static final String TRAINING = "AutoTest_REGISTRATION_OPEN_Survey";
  private static final String TRAINING_PROGRAM_TEXT = RandomStringUtils.randomAlphabetic(10);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactDescriptionTabScreen = new ReactDescriptionTabScreen();
    trainingDescriptionScreen = new TrainingDescriptionScreen();
    trainingProgramScreen = new TrainingProgramScreen();
    reactTrainingManagementService = new ReactTrainingManagementService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactDetailTrainingScreen = reactTrainingManagementService.
        searchTrainingByNameAndRedirectOnIt(TRAINING)
        .waitScreenLoaded();
  }

  @Test(priority = 1)
  public void checkAllChangedAreSavedTrainingProgramPresent() {
    reactTrainingManagementService
        .switchLangForCurrentLocale();
    reactDetailTrainingScreen
        .clickDescriptionTabFromTrainingScreen()
        .waitAllSpinnersAreNotDisplayed();
    reactTrainingManagementService
        .selectTrainingProgram(TRAINING_PROGRAM_TEXT)
        .clickSaveChangesButton()
        .waitAllSpinnersAreNotDisplayed();
    assertEquals(reactDescriptionTabScreen
            .getTrainingProgramText(),
        TRAINING_PROGRAM_TEXT,
        "Changes made aren't saved!");
  }

  @Test(priority = 2)
  public void checkTrainingDescriptionPageContent() {
    reactDetailTrainingScreen
        .clickPreviewButton()
        .switchToLastWindow();
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingDescriptionScreen
            .isOngoingDurationDisplayed(),
        "'Ongoing' duration of training isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen
            .isPlannedLanguageDisplayed(),
        "Planned language is'not displayed!");
    softAssert.assertTrue(trainingDescriptionScreen
            .isLocationOfTrainingDisplayed(),
        "Location of training isn't displayed!");
    softAssert.assertEquals(trainingDescriptionScreen
            .getOngoingDurationText(),
        getValueOf(TRAINING_DESCRIPTION_ONGOING_DURATION),
        "Ongoing text isn't correct!");
    softAssert.assertEquals(trainingDescriptionScreen
            .getLocationOfTrainingText(),
        getValueOf(TRAINING_DESCRIPTION_LOCATION),
        "Location isn't correct!");
    softAssert.assertEquals(trainingDescriptionScreen
            .getPlannedLanguageText(),
        getValueOf(TRAINING_DESCRIPTION_PLANNED_LANGUAGE),
        "Planned language isn't correct!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkDetailedDescriptionPopUpContent() {
    softAssert = new SoftAssert();
    trainingDescriptionScreen.clickRefreshButton();
    trainingProgramScreen = trainingDescriptionScreen
        .clickViewButton();
    trainingProgramScreen
        .waitTrainingProgramPopUpDisplayed();
    softAssert.assertTrue(trainingProgramScreen
            .isScreenLoaded(),
        "Training program pop up isn't displayed!");
    softAssert.assertTrue(trainingProgramScreen
            .isCrossDisplayed(),
        "'Cross' button isn't displayed!");
    softAssert.assertTrue(trainingProgramScreen
            .isTrainingLogoDisplayed(),
        "Training logo isn't displayed!");
    softAssert.assertTrue(trainingProgramScreen
            .isTrainingProgramTitleDisplayed(),
        "Training program header isn't displayed!");
    softAssert.assertEquals(trainingProgramScreen
            .getFullDescriptionText(),
        TRAINING_PROGRAM_TEXT,
        "Training program saved incorrect!");
    softAssert.assertEquals(trainingProgramScreen
            .getTrainingTitleText(),
        TRAINING.toUpperCase(),
        "Redirect on incorrect training!");
    softAssert.assertEquals(trainingProgramScreen
            .getTrainingProgramTitleText(),
        getValueOf(TRAINING_PROGRAM_HEADER), "Header has incorrect text!");
    softAssert.assertAll();
  }
}
