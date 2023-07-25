package com.epmrdpt.smoke.center_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34201_VerifyEditTrainingCenterPageIsDisplayedAfterClickingOnTheCityNameOnTheCenterManagementPage",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34201_VerifyEditTrainingCenterPageIsDisplayed {

  private final String countryOfCenter = "AutoTestCountry";
  private final String cityOfCenter = "AutoTestCity";

  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34201_VerifyEditTrainingCenterPageIsDisplayed(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
  }

  @Test
  public void checkEditTrainingCenterPageIsDisplayed() {
    CreateOrEditTrainingCenterScreen editTrainingCenterScreen = new TrainingCenterManagementService()
        .openEditCenterScreen(countryOfCenter, cityOfCenter);
    assertTrue(editTrainingCenterScreen.isScreenLoaded(),
        "Edit Training center page isn't displayed");
  }
}
