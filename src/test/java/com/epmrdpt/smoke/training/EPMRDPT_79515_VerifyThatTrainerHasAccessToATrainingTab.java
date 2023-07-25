package com.epmrdpt.smoke.training;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79515_VerifyThatTrainerHasAccessToATrainingTab",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_79515_VerifyThatTrainerHasAccessToATrainingTab {

  private User user;
  private ReactTrainingScreen reactTrainingScreen;

  @Factory(dataProvider = "Provider of users with Training tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79515_VerifyThatTrainerHasAccessToATrainingTab(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user);
  }

  @Test
  public void checkTrainingScreenOpened() {
    SoftAssert softAssert = new SoftAssert();
    assertFalse(new HeaderScreen().isBannerDisplayed(),
        "'Header' screen is still displaying!");
    assertTrue((reactTrainingScreen.isMyScheduleTabDisplayed() &&
            reactTrainingScreen.isMyGroupsTabDisplayed()),
        "Basic elements of 'Training' screen aren't displayed!");
    softAssert.assertAll();
  }
}
