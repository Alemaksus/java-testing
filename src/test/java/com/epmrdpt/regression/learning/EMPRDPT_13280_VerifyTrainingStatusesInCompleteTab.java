package com.epmrdpt.regression.learning;

import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_HIRED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_LAB_FINISHED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_TRAINING_FINISHED;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EMPRDPT_13280_VerifyTrainingStatusesInCompleteTab",
    groups = {"full", "regression", "student"})
public class EMPRDPT_13280_VerifyTrainingStatusesInCompleteTab {

  private final String TRAINING_WITH_STATUS_TRAINING_FINISHED = "AutoTest_WithFreemiumPricing";
  private final String TRAINING_WITH_STATUS_HIRED = "AutoTest_WithFreePricing";
  private final String TRAINING_WITH_STATUS_LAB_FINISHED = "AutoTest_TrainingFor_Karaganda_City";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asLearningStudent())
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickApplicationsButton()
        .clickCompletedTab();
  }

  @DataProvider(name = "Provider of trainings with statuses")
  private Object[][] providerOfTrainingWithStatuses() {
    return new Object[][]{
        {TRAINING_WITH_STATUS_TRAINING_FINISHED,
            LocaleProperties.getValueOf(APPLICATIONS_STATUS_TRAINING_FINISHED)},
        {TRAINING_WITH_STATUS_HIRED,
            LocaleProperties.getValueOf(APPLICATIONS_STATUS_HIRED)},
        {TRAINING_WITH_STATUS_LAB_FINISHED,
            LocaleProperties.getValueOf(APPLICATIONS_STATUS_LAB_FINISHED)}
    };
  }

  @Test(dataProvider = "Provider of trainings with statuses")
  public void checkStatusOfTrainingsInInactiveTab(String trainingName, String statusOfTraining) {
    assertEquals(new ApplicationsScreen()
            .getStatusOfTrainingByTrainingNameText(trainingName),
        statusOfTraining,
        format("Training '%s' do not have status '%s'!", trainingName, statusOfTraining));
  }
}
