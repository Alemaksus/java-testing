package com.epmrdpt.smoke.center_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34197_VerifyThatTheCreateTrainingCenterPageIsOpenedAfterClickingCreateTrainingCenterButton",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34197_VerifyThatTheCreateTrainingCenterPageIsOpenedAfterClickingCreateTrainingCenterButton {

  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34197_VerifyThatTheCreateTrainingCenterPageIsOpenedAfterClickingCreateTrainingCenterButton(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitCreateTrainingCenterButtonVisibility()
        .clickCreateTrainingCenterButton()
        .waitCreateOrEditTrainingCenterHeaderVisibility();
  }

  @Test
  public void checkThatTitlePageDisplayed() {
    assertTrue(new CreateOrEditTrainingCenterScreen().isScreenLoaded(),
        "Create Training Center page isn't loaded!");
  }
}
