package com.epmrdpt.smoke.center_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CENTER_MANAGEMENT_HEADER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34196_VerifyThatUserHasAccessToCenterManagementPage",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34196_VerifyUserHasAccessToCenterManagementPage {

  private User user;
  private TrainingCenterManagementScreen centerManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34196_VerifyUserHasAccessToCenterManagementPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    centerManagementScreen = new TrainingCenterManagementScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink();
  }

  @Test
  public void checkScreenIsLoaded() {
    assertTrue(centerManagementScreen.isScreenLoaded(),
        "Training center management screen isn't loaded!");
  }

  @Test
  public void checkTrainingCenterManagementHeaderIsDisplayed() {
    assertTrue(centerManagementScreen.isCenterManagementHeaderDisplayed(),
        "Training center management header isn't displayed!");
  }

  @Test
  public void checkTrainingCenterManagementHeaderContainsRightText() {
    assertEquals(centerManagementScreen.getTrainingCenterManagementHeaderText(),
        getValueOf(CENTER_MANAGEMENT_HEADER),
        "Training center management header does not contain right text!");
  }
}
