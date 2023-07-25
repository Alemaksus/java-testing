package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.screens.SettingsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.SettingsService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13076_VerifyThatSearchOptionsAreSavedIfSaveSearchOptionsCheckboxIsChecked",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_13076_VerifyThatSearchOptionsAreSavedIfSaveSearchOptionsCheckboxIsChecked {

  private SettingsScreen settingsScreen;
  private SettingsService settingsService;
  private ReactTrainingManagementScreen reactTrainingManagementScreen;

  private String trainingStatus = LocaleProperties
      .getValueOf(LocalePropertyConst.TRAINING_MANAGER_TRAINING_STATUS_FROM_FILTER_PLANNED);
  private boolean isSaveSearchOptionsCheckBoxChecked;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    settingsScreen = new SettingsScreen();
    reactTrainingManagementScreen = new ReactTrainingManagementScreen();
    settingsService = new SettingsService();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asTrainingManager());
    settingsService.navigateToSettingsScreen();
    isSaveSearchOptionsCheckBoxChecked = settingsScreen.isSaveSearchOptionsChecked();
  }

  @Test(priority = 1)
  public void checkSaveSearchOptionsCheckboxIsChecked() {
    assertTrue(
        settingsService
            .tickSaveSearchOptionsCheckBox()
            .clickApplySettingsButton()
            .waitScreenLoading()
            .isSaveSearchOptionsChecked(),
        "'Save search options' check box isn't checked!");
  }

  @Test(priority = 2)
  public void checkDataIsDisplayedAccordingToTheFilterValue() {
    settingsScreen.scrollToTop();
    new ReactHeaderScreen()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .uncheckCheckBoxes()
        .clickCancelFilterByDateButtonIfNeeded()
        .clickAdvancedSearchButtonIfNeeded()
        .clickCurrentStatusDropdownInSearchFilter()
        .selectTrainingStatusByName(trainingStatus)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed();
    assertTrue(reactTrainingManagementScreen.areAllTrainingsHaveCorrectStatus(trainingStatus),
        String.format("Not all training have '%s' status", trainingStatus));
  }

  @Test(priority = 3)
  public void checkSettingsFilterSavedAfterRefreshScreen() {
    reactTrainingManagementScreen
        .clickRefreshButton();
    reactTrainingManagementScreen
        .waitFilterBlockDisplayed()
        .waitAllSpinnersAreNotDisplayed()
        .clickAdvancedSearchButtonIfNeeded()
        .waitAllSpinnersAreNotDisplayed();
    assertEquals(reactTrainingManagementScreen.getTrainingStatusFromSearchFilterText(),
        trainingStatus,
        "Filter is changed after refresh page!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteTickFromSaveSearchOptions(Method method) {
    if (method.getName().equals("checkSettingsFilterSavedAfterRefreshScreen")
        && !isSaveSearchOptionsCheckBoxChecked) {
      settingsScreen.openPage(getEnv());
      settingsService.navigateToSettingsScreen();
      settingsService.unTickSaveSearchOptionsCheckBox()
          .clickApplySettingsButton()
          .waitScreenLoading()
          .waitSavSearchOptionBeUnchecked();
    }
  }
}
