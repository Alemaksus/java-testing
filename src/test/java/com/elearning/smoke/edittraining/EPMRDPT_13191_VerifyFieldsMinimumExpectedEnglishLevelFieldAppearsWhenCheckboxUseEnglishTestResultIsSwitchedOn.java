package com.epmrdpt.smoke.edittraining;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13191_VerifyFieldsMinimumExpectedEnglishLevelFieldAppearsWhenCheckboxUseEnglishTestResultIsSwitchedOn",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13191_VerifyFieldsMinimumExpectedEnglishLevelFieldAppearsWhenCheckboxUseEnglishTestResultIsSwitchedOn {

  private String trainingName = "AutoTest Planned Status";
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13191_VerifyFieldsMinimumExpectedEnglishLevelFieldAppearsWhenCheckboxUseEnglishTestResultIsSwitchedOn(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
  }

  @Test
  public void checkThatMinimalEnglishLevelFieldDisplayed() {
    ReactSurveyAndTestingTabScreen reactSurveyAndTestingTabScreen =
        new ReactTrainingManagementService()
            .searchTrainingByNameAndRedirectOnIt(trainingName)
            .waitGroupsTabDisplayed()
            .clickSurveyAndTestingTabFromTrainingScreen();
    new ReactTrainingManagementService().checkUseEnglishResultsCheckBoxAndSwitchOffIfChecked();
    assertTrue(reactSurveyAndTestingTabScreen.clickUseEnglishResultsCheckbox()
            .isMinimumEnglishLevelInputDisplayed(),
        "'Minimum expected English level' field isn't appeared!");
  }
}
