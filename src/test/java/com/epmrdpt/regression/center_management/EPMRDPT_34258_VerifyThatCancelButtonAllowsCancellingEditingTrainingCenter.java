package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_STATUS_DRAFT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34258_VerifyThatCancelButtonAllowsCancellingEditingTrainingCenter",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34258_VerifyThatCancelButtonAllowsCancellingEditingTrainingCenter {

  private User user;
  private String statusTrainingCenter;
  private String trainingCenterCountry = "AutoTestCountry";
  private String trainingCenterCity = "AutoTestCity";
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34258_VerifyThatCancelButtonAllowsCancellingEditingTrainingCenter(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupEditTrainingCenter() {
    trainingCenterManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(trainingCenterCountry, trainingCenterCity);
    createOrEditTrainingCenterScreen = trainingCenterManagementScreen
        .clickTrainingCenterMenuButton(trainingCenterCity)
        .selectEditTheTrainingCenterButtonDropdownMenu()
        .waitEditAreaForVisibility()
        .waitForTrainingCenterNameVisibility();
    selectNewStatusForTheTrainingCenter();
    createOrEditTrainingCenterScreen.clickCancelTopButton()
        .waitForTrainingPortalMessagePopUpVsibility()
        .confirmMessageTrainingPortalPopUp();
  }

  @Test(priority = 1)
  public void checkThatUserRedirectsOnCenterManagementScreen() {
    assertTrue(trainingCenterManagementScreen.isCenterManagementHeaderDisplayed(),
        "Center Management Header isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatChangesAreNotSaved() {
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(trainingCenterCountry, trainingCenterCity);
    assertEquals(trainingCenterManagementScreen.getStatusOfCenterText(trainingCenterCity),
        statusTrainingCenter, "The changes have been saved!");
  }

  private void selectNewStatusForTheTrainingCenter() {
    statusTrainingCenter = createOrEditTrainingCenterScreen.getChooseStatusText();
    createOrEditTrainingCenterScreen.clickToChooseStatus().waitStatusDropdownListForVisibility();
    if (statusTrainingCenter
        .equals(LocaleProperties.getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED))) {
      createOrEditTrainingCenterScreen.selectStatusFromDropDownList(
          LocaleProperties.getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_DRAFT));
    } else {
      createOrEditTrainingCenterScreen.selectStatusFromDropDownList(
          LocaleProperties.getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED));
    }
  }
}
