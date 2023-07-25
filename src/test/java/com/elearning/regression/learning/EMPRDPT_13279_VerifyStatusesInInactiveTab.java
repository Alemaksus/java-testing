package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_DISMISSED_FROM_LAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_DISMISSED_FROM_TRAINING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_REGISTRATION_IS_CANCELLED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_REJECTED;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EMPRDPT_13279_VerifyStatusesInInactiveTab",
    groups = {"full", "regression", "student"})
public class EMPRDPT_13279_VerifyStatusesInInactiveTab {

  private final String TRAINING_WITH_STATUS_REJECTED = "AutoTest_WithSimplifiedRegistrationForm";
  private final String TRAINING_WITH_STATUS_REGISTRATION_IS_CANCELLED = "AutoTest_AutomaticReplyText";
  private final String TRAINING_WITH_STATUS_DISMISSED_FROM_TRAINING = "MOBILE TESTING";
  private final String TRAINING_WITH_STATUS_DISMISSED_FROM_LAB = "Automated Testing";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickApplicationsButton()
        .clickInactiveTab();
  }

  @DataProvider(name = "Provider of trainings with statuses")
  private Object[][] providerOfTrainingWithStatuses() {
    return new Object[][]{
        {TRAINING_WITH_STATUS_REJECTED, getValueOf(APPLICATIONS_STATUS_REJECTED)},
        {TRAINING_WITH_STATUS_REGISTRATION_IS_CANCELLED,
            getValueOf(APPLICATIONS_STATUS_REGISTRATION_IS_CANCELLED)},
        {TRAINING_WITH_STATUS_DISMISSED_FROM_TRAINING,
            getValueOf(APPLICATIONS_STATUS_DISMISSED_FROM_TRAINING)},
        {TRAINING_WITH_STATUS_DISMISSED_FROM_LAB,
            getValueOf(APPLICATIONS_STATUS_DISMISSED_FROM_LAB)}
    };
  }

  @Test(dataProvider = "Provider of trainings with statuses")
  public void checkStatusOfTrainingsInInactiveTab(String trainingName, String statusOfTraining) {
    assertEquals(
        new ApplicationsScreen()
            .getStatusOfTrainingByTrainingNameText(trainingName),
        statusOfTraining,
        format("Training '%s' do not have status '%s'", trainingName, statusOfTraining));
  }
}
