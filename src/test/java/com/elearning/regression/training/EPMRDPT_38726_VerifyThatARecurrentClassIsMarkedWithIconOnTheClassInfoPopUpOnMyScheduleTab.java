package com.epmrdpt.regression.training;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38726_VerifyThatARecurrentClassIsMarkedWithASpecialIconOnTheGeneralClassInfoPopUpOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38726_VerifyThatARecurrentClassIsMarkedWithIconOnTheClassInfoPopUpOnMyScheduleTab {

  private final String className = "AutoTest_EditClass";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(UserFactory.asTrainer())
        .waitScheduleForVisibility();
  }

  @Test
  public void checkRecurrentClassIconOnGeneralClassInfoInMyScheduleTabDisplayed() {
    assertTrue(new ReactTrainingScreen()
            .clickInScheduleClassCardByTopic(className)
            .isRecurringClassIconDisplayed(),
        "Recurrent class icon on 'General info' pop up at 'My schedule' tab isn't displayed!");
  }
}
