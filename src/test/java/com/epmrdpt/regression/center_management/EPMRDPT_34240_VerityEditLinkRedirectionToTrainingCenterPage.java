package com.epmrdpt.regression.center_management;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34240_VerityEditLinkRedirectionToTrainingCenterPage ",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34240_VerityEditLinkRedirectionToTrainingCenterPage {

  private final String countryOfCenter = "AutoTestCountry";
  private final String cityOfCenter = "AutoTestCity";
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34240_VerityEditLinkRedirectionToTrainingCenterPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupTrainingCenter() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(user)
        .clickCenterManagementLink();
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(countryOfCenter, cityOfCenter)
        .clickTrainingCenterMenuButton(cityOfCenter)
        .waitListOfActionForTrainingCenterMenuButtonForVisibility()
        .selectEditTheTrainingCenterButtonDropdownMenu()
        .isScreenLoaded();
  }

  @Test
  public void checkThatCenterHasCorrectCityName() {
    assertEquals(new CreateOrEditTrainingCenterScreen().waitForTrainingCenterNameVisibility()
            .getCityNameOfTrainingCenter(), cityOfCenter,
        "The opened training center has incorrect city name!");
  }
}