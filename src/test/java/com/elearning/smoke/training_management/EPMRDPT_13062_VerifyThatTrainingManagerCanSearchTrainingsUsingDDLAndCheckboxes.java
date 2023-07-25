package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_ENDLESS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_RESULT_ENDLESS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_STATUS_REGISTRATION_OPEN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_TYPE_TRAINING;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingForSearch;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13062_VerifyThatTrainingManagerCanSearchTrainingsUsingDDLAndCheckboxes",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13062_VerifyThatTrainingManagerCanSearchTrainingsUsingDDLAndCheckboxes {

  private ReactTrainingManagementScreen reactTrainingManagementScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13062_VerifyThatTrainingManagerCanSearchTrainingsUsingDDLAndCheckboxes(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    reactTrainingManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink().clickResetButton().clickApplyButton()
        .waitTrainingScreenIsLoaded();
  }

  @Test(priority = 1)
  public void checkSelectedTrainingsCorrespondSearchCriteria() {
    TrainingForSearch trainingForSearch = new TrainingForSearch("AutoTestCountry",
        "AutoTestCity", getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_ENDLESS),
        getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_TYPE_TRAINING),
        getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_STATUS_REGISTRATION_OPEN), "Online");
    new ReactTrainingManagementService().fillInputsForSearchTraining(trainingForSearch);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen
            .areAllTrainingsHaveCorrectStatus(trainingForSearch.getStatus()),
        "Not all trainings have selected status!");
    softAssert.assertTrue(reactTrainingManagementScreen
            .areAllTrainingsHaveCorrectTypeName(trainingForSearch.getType()),
        "Not all trainings have selected type!");
    softAssert.assertTrue(reactTrainingManagementScreen
            .areAllTrainingsHaveCorrectDates(
                getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_RESULT_ENDLESS)),
        "Not all trainings have selected end date!");
    softAssert.assertTrue(reactTrainingManagementScreen.areAllTrainingsHaveCorrectLocation(
            trainingForSearch.getCountryName(), trainingForSearch.getCityName()),
        "Not all trainings have selected location!");
    softAssert.assertTrue(reactTrainingManagementScreen
            .areAllTrainingsHaveCorrectFormat(trainingForSearch.getFormat()),
        "Not all trainings have selected format");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkAllFiltersAreResetAfterPushingResetButton() {
    reactTrainingManagementScreen
        .clickResetButton()
        .waitAllSpinnersAreNotDisplayed();
    assertTrue(reactTrainingManagementScreen.areAllSearchToolsReset(),
        "Not all filters were reset!");
  }
}
