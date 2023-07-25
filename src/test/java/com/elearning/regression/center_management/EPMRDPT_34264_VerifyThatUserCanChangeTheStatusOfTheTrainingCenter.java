package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_STATUS_DRAFT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED;
import static org.testng.Assert.assertNotEquals;
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

@Test(description = "EPMRDPT_34264_VerifyThatUserCanChangeTheStatusOfTheTrainingCenter",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34264_VerifyThatUserCanChangeTheStatusOfTheTrainingCenter {

  private User user;
  private String statusTrainingCenter;
  private String countryOfTheTrainingCenter = "AutoTestCountry";
  private String cityOfTheTrainingCenter = "AutoTestCity";
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34264_VerifyThatUserCanChangeTheStatusOfTheTrainingCenter(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenter() {
    trainingCenterManagementScreen = new LoginService()
        .loginAndSetLanguage(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(countryOfTheTrainingCenter, cityOfTheTrainingCenter);
    createOrEditTrainingCenterScreen = trainingCenterManagementScreen
        .clickTrainingCenterMenuButton(cityOfTheTrainingCenter)
        .selectEditTheTrainingCenterButtonDropdownMenu()
        .waitEditAreaForVisibility()
        .waitForTrainingCenterNameVisibility();
  }

  @Test(priority = 1)
  public void checkForPopUpAppearance() {
    selectNewStatusForTheTrainingCenter();
    createOrEditTrainingCenterScreen.clickAtTheTopSaveButton();
    replyToMessage();
    assertTrue(createOrEditTrainingCenterScreen.isSaveChangesOrCreatePopUpDisplayed(),
        "The 'Changes saved' pop-up did not appear!");
  }

  @Test(priority = 2)
  public void checkStatusOfTrainingCenter() {
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(countryOfTheTrainingCenter, cityOfTheTrainingCenter);
    assertNotEquals(trainingCenterManagementScreen.getStatusOfCenterText(cityOfTheTrainingCenter),
        statusTrainingCenter, "The status of the training center has not changed!");
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

  private void replyToMessage() {
    if (createOrEditTrainingCenterScreen.isTrainingPortalMessagePopUpDisplayed()) {
      createOrEditTrainingCenterScreen.confirmMessageTrainingPortalPopUp();
    }
  }
}
