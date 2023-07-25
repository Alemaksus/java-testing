package com.epmrdpt.smoke.edittraining;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ENROLLMENT_NAME_ASSESSMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_CARD_PRICE_FREE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_PROGRAM_LEVEL_ADVANCED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TYPE_NAME_ASSESSMENT;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingForCopy;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.time.LocalTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13259_VerifyThatTrainingManagerCanCreateATraining",
    groups = {"full", "manager", "critical_path"})
public class EPMRDPT_13259_VerifyThatTrainingManagerCanCreateATraining {

  private final String trainingTypeName = getValueOf(TYPE_NAME_ASSESSMENT);
  private final String trainingCountryName = getValueOf(COUNTRY_NAME_BELARUS);
  private final String trainingCityName = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private final String trainingEnrollmentTypeName = getValueOf(ENROLLMENT_NAME_ASSESSMENT);
  private final String trainingProgramLanguageName = getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH);
  private final String trainingPricingName = getValueOf(TRAINING_CARD_PRICE_FREE);
  private final String trainingProgramLevelName = getValueOf(TRAINING_PROGRAM_LEVEL_ADVANCED);
  private final String trainingFormatName = "Blended";
  private final String trainingSkillName = ".NET";
  private final String PLANNED_STUDENTS_COUNT = "5";
  private String trainingName;
  private ReactTrainingManagementService reactTrainingManagementService;
  private TrainingForCopy training;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    training = new TrainingForCopy();
    trainingName = "AutoTest_#" + LocalTime.now();
    training
        .withName(trainingName)
        .withType(trainingTypeName)
        .withFormat(trainingFormatName)
        .withCountry(trainingCountryName)
        .withCity(trainingCityName)
        .withSkill(trainingSkillName)
        .withEnrollmentType(trainingEnrollmentTypeName)
        .withProgramLanguage(trainingProgramLanguageName)
        .withProgramLevel(trainingProgramLevelName)
        .withPricing(trainingPricingName)
        .withStudentsCount(PLANNED_STUDENTS_COUNT);
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitPreloadingPictureHidden()
        .clickCreateNewButton()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkThatTrainingIsCreated() {
    reactTrainingManagementService
        .fillRequiredFieldsForNewTrainingAndCreateIt(training);
    assertTrue(new ReactDetailTrainingScreen()
            .isScreenLoaded(),
        "Training is not created");
  }

  @Test(priority = 2)
  public void checkTrainingIsPresentedInTrainingList() {
    new ReactHeaderScreen()
        .clickTrainingManagementLink();
    reactTrainingManagementService
        .searchTrainingByName(training.getName());
    assertTrue(new ReactTrainingManagementScreen()
            .getFirstTrainingTableNameText()
            .contains(training.getName()),
        "Training: \"" + training.getName() + "\"  isn't displayed in Training List!");
  }
}
