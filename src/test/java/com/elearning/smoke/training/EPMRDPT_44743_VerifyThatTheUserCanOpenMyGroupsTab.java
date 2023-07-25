package com.epmrdpt.smoke.training;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44743_VerifyThatTheUserCanOpenMyGroupsTab",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_44743_VerifyThatTheUserCanOpenMyGroupsTab {

  private User user;

  @Factory(dataProvider = "Provider of users with React Training permissions",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44743_VerifyThatTheUserCanOpenMyGroupsTab(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactTrainingScreenInitialization() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
  }

  @Test
  public void checkThatUserCanOpenMyGroupsTab() {
    assertTrue(new ReactTrainingScreen().clickMyGroupsTab()
        .isGroupsSearchBarDisplayed(), "My Groups tab isn't opened!");
  }
}
