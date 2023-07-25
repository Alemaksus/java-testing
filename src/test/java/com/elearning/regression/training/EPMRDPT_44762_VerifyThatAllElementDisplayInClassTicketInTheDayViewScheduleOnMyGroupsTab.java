package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GO_TO_JOURNAL;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44762_VerifyThatAllElementDisplayInClassTicketInTheDayViewScheduleOnMyGroupsTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44762_VerifyThatAllElementDisplayInClassTicketInTheDayViewScheduleOnMyGroupsTab {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String classTicketName = "AutoTest_ClassDurationMore60Min";

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupClassTicketInMyGroupsTab() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainingManager())
        .waitScheduleForVisibility();
    new ReactTrainingService()
        .openGroupScheduleByName(groupName, classTicketName)
        .waitClassTicketInScheduleByNameForVisibility(classTicketName);
  }

  @Test(priority = 1)
  public void checkClassTicketOnMyGroupsTabDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketInScheduleByNameDisplayed(classTicketName),
        format("Class ticket with '%s' name on 'My groups' tab isn't displayed!", classTicketName));
  }

  @Test(priority = 2)
  public void checkTimeInClassTicketOnMyGroupsTabDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketTimeByNameDisplayed(classTicketName),
        "Field 'Time of the class' in class ticket on 'My groups' tab isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTypeInClassTicketOnMyGroupsTabDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketTypeByNameDisplayed(classTicketName),
        "Field 'Type of the class' in class ticket on 'My groups' tab isn't displayed!");
  }

  @Test(priority = 2)
  public void checkLocationInClassTicketOnMyGroupsTabDisplayed() {
    assertTrue(reactTrainingScreen.isClassTicketLocationByNameDisplayed(classTicketName),
        "Field 'Location' in class ticket on 'My groups' tab isn't displayed!");
  }

  @Test(priority = 2)
  public void checkGoToJournalButtonOnMyGroupsHasCorrectText() {
    assertEquals(reactTrainingScreen.getGoToJournalButtonTextByClassName(classTicketName),
        getValueOf(REACT_TRAINING_GO_TO_JOURNAL),
        "'Go to journal' button in class ticket on 'My groups' tab has incorrect text!");
  }
}
