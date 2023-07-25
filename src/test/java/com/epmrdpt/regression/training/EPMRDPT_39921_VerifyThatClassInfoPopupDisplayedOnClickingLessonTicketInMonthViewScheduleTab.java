package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39921_VerifyThatClassInfoPopupDisplayedOnClickingLessonTicketInMonthViewScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39921_VerifyThatClassInfoPopupDisplayedOnClickingLessonTicketInMonthViewScheduleTab {

  private final String className = "AutoTest_ClassDurationMore60Min";

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupMonthScheduleTab() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
    new ReactTrainingService()
        .openMonthViewSchedule();
  }

  @Test
  public void checkThatClassInfoPopupOpensAfterClickingClassTicket() {
    assertTrue(reactTrainingScreen.clickClassInMonthViewScheduleByName(className)
            .waitLessonInfoForVisibility()
            .isDeleteIconButtonDisplayed(),
        "Class info pop-up window isn't displayed!");
  }
}
