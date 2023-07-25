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

@Test(description = "EPMRDPT_46348_VerifyThatARecurrentClassIsMarkedWithASpecialIconOnTheGeneralClassInfoPopUpOnMyGroupsTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_46348_VerifyThatARecurrentClassIsMarkedWithIconOnTheClassInfoPopUpOnMyGroupsTab {

  private static final String CLASS_NAME = "AutoTest_ClassDurationMore60Min";
  private static final String GROUP_NAME = "AutoTest_GroupWithEverydayClasses";
  private static final String TRAINING_NAME = "AutoTest_React";

  private final User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_46348_VerifyThatARecurrentClassIsMarkedWithIconOnTheClassInfoPopUpOnMyGroupsTab(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupGroupOnMyGroupsTab() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitScheduleForVisibility();
    new ReactTrainingService().searchGroupByNameAndClickTrainingByName(GROUP_NAME, TRAINING_NAME);
  }

  @Test
  public void checkRecurringClassIconOnGeneralClassInfoPopUpOnMyGroupTabDisplayed() {
    assertTrue(new ReactTrainingScreen()
            .clickInScheduleClassCardByTopic(CLASS_NAME)
            .isRecurringClassIconDisplayed(),
        "Recurring class icon on 'General info' pop up at 'My Groups' tab isn't displayed!");
  }
}
