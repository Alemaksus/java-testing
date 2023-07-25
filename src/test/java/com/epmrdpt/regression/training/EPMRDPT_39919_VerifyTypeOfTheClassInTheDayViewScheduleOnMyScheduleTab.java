package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39919_VerifyTypeOfTheClassInTheDayViewScheduleOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39919_VerifyTypeOfTheClassInTheDayViewScheduleOnMyScheduleTab {

  private final String classTicketName = "AutoTest_ClassDurationMore45Min";

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupScheduleTab() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
  }

  @Test(priority = 1)
  public void checkClassTicketOnScheduleTabDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketInScheduleByNameDisplayed(classTicketName),
        format("Class ticket with '%s' name on 'Schedule' tab isn't displayed!", classTicketName));
  }

  @Test(priority = 2)
  public void checkTypeIconInClassTicketDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketTypeIconByNameDisplayed(classTicketName),
        "Type Icon in class ticket isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTypeTextInClassTicketDisplayed() {
    assertFalse(reactTrainingScreen.getClassTicketTypeByNameText(classTicketName).isEmpty(),
        "Type text in class ticket isn't displayed!");
  }
}
