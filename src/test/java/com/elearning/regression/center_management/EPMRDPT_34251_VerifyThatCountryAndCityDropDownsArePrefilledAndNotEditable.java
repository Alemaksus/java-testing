package com.epmrdpt.regression.center_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34251_VerifyThatCountryAndCityDropDownsArePrefilledAndNotEditable",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34251_VerifyThatCountryAndCityDropDownsArePrefilledAndNotEditable {

  private User user;
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;
  private String countryOfTheTrainingCenter = "AutoTestCountry";
  private String cityOfTheTrainingCenter = "AutoTestCity";

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34251_VerifyThatCountryAndCityDropDownsArePrefilledAndNotEditable(
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
        .waitListOfActionForTrainingCenterMenuButtonForVisibility()
        .selectEditTheTrainingCenterButtonDropdownMenu()
        .waitEditAreaForVisibility()
        .waitForTrainingCenterNameVisibility();
  }

  @Test(priority = 1)
  public void checkThatCityFieldIsEditable() {
    assertTrue(
        createOrEditTrainingCenterScreen.getCenterCityClassValue().contains("disabled"),
        "The City field is possible to change!");
  }

  @Test(priority = 2)
  public void checkThatCityFieldIsPrefilled() {
    assertEquals(createOrEditTrainingCenterScreen.getCityNameOfTrainingCenter(),
        cityOfTheTrainingCenter, "The City field is not prefilled!");
  }

  @Test(priority = 3)
  public void checkThatCountryFieldIsEditable() {
    assertTrue(
        createOrEditTrainingCenterScreen.getCenterCountryClassValue().contains("chosen-disabled"),
        "The Country field is possible to change!");
  }

  @Test(priority = 4)
  public void checkThatCountryFieldIsPrefilled() {
    assertEquals(createOrEditTrainingCenterScreen.getCountryNameOfTrainingCenterText(),
        countryOfTheTrainingCenter, "The Country field is not prefilled!");
  }
}
