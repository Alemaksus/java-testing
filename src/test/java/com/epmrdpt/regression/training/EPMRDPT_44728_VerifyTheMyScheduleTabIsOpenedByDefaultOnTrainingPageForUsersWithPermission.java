package com.epmrdpt.regression.training;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44728_VerifyTheMyScheduleTabIsOpenedByDefaultOnTrainingPageForUsersWithPermission",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44728_VerifyTheMyScheduleTabIsOpenedByDefaultOnTrainingPageForUsersWithPermission {

  private User user;
  private ReactTrainingScreen reactTrainingScreen;

  @Factory(dataProvider = "Provider of users with React Training permissions",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44728_VerifyTheMyScheduleTabIsOpenedByDefaultOnTrainingPageForUsersWithPermission(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactTrainingScreenIsLoading() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
  }

  @Test
  public void checkThatMyScheduleTabIsOpenedByDefaultOnTrainingPage() {
    assertTrue(reactTrainingScreen.isScheduleForDayDisplayed(),
        "My Schedule tab isn't opened by default on training page!");
  }
}
