package com.epmrdpt.smoke.edittraining;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_30813_VerifyThatCreateTrainingPageOpensAfterClickingCreateTrainingButton",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_30813_VerifyThatCreateTrainingPageOpensAfterClickingCreateTrainingButton {

  private HeaderScreen headerScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_30813_VerifyThatCreateTrainingPageOpensAfterClickingCreateTrainingButton(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
  }

  @Test
  public void checkThatCreateTrainingPageOpened() {
    ReactTrainingManagementScreen reactTrainingManagementScreen = headerScreen
        .clickReactTrainingManagementLink()
        .waitPreloadingPictureHidden();
    assertTrue(reactTrainingManagementScreen.clickCreateNewButton().isScreenLoaded(),
        "Create training screen isn't displayed!");
  }
}
