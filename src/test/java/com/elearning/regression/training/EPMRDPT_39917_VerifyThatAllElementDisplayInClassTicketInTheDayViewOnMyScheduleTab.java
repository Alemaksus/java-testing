package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GO_TO_JOURNAL;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_39917_VerifyThatAllPossibleElementDisplayInClassTicketInTheDayViewScheduleOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39917_VerifyThatAllElementDisplayInClassTicketInTheDayViewOnMyScheduleTab {

  private final String classTicketName = "AutoTest_ClassDurationMore60Min";

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
  public void checkTimeInClassTicketDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketTimeByNameDisplayed(classTicketName),
        "Field 'Time of the class' in class ticket isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTypeInClassTicketDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketTypeByNameDisplayed(classTicketName),
        "Field 'Type of the class' in class ticket isn't displayed!");
  }

  @Test(priority = 2)
  public void checkLocationInClassTicketDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketLocationByNameDisplayed(classTicketName),
        "Field 'Location' in class ticket isn't displayed!");
  }

  @Test(priority = 2)
  public void checkGoToJournalButtonDisplayed() {
    assertEquals(reactTrainingScreen.getGoToJournalButtonText(),
        getValueOf(REACT_TRAINING_GO_TO_JOURNAL),
        "'Go to journal' button in class ticket has incorrect text!");
  }
}
