package com.epmrdpt.smoke.training;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactAddClassPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(
    description = "EPMRDPT_54108_VerifyThatUserCanCallsUpTheAddClassPopUpOnScheduleTab",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_54108_VerifyThatUserCanCallsUpTheAddClassPopUpOnScheduleTab {

  private final String groupName = "AutoTest_GroupWithDeletingClassTicket";
  private User user;

  @Factory(
      dataProvider = "Provider of users with React Training permissions",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_54108_VerifyThatUserCanCallsUpTheAddClassPopUpOnScheduleTab(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void signInAndWaitTrainingScreenToLoad() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitScheduleForVisibility();
  }

  @Test
  public void verifyThatPopUpScreenDisplayed() {
    ReactAddClassPopUpScreen reactAddClassPopUpScreen = new ReactTrainingService()
        .searchGroupByName(groupName)
        .clickScheduleTab()
        .clickAddClassButton();
    assertTrue(reactAddClassPopUpScreen.isScreenLoaded(), "Class pop-up isn't displayed!");
  }
}
