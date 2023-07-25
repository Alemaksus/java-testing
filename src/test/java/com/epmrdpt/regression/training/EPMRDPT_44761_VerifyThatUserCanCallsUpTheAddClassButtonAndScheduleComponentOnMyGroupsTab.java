package com.epmrdpt.regression.training;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44761_VerifyThatUserCanCallsUpTheAddClassButtonAndScheduleComponentOnMyGroupsTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44761_VerifyThatUserCanCallsUpTheAddClassButtonAndScheduleComponentOnMyGroupsTab {

  private final String groupName = "AutoTest_GroupWithDeletingClassTicket";

  private User user;
  ReactTrainingScreen reactTrainingScreen;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44761_VerifyThatUserCanCallsUpTheAddClassButtonAndScheduleComponentOnMyGroupsTab(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingInGroupsTab() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitScheduleForVisibility();
    new ReactTrainingService().selectTrainingByGroupName(groupName);
  }

  @Test(priority = 1)
  public void checkThatAddClassButtonDisplayed() {
    assertTrue(reactTrainingScreen.clickTrainingButton()
            .isAddClassButtonDisplayed(),
        "'Add class' button doesn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatScheduleComponentDisplayed() {
    assertTrue(reactTrainingScreen.clickAddClassButton()
            .isScreenLoaded(),
        "Schedule component doesn't displayed!");
  }
}
