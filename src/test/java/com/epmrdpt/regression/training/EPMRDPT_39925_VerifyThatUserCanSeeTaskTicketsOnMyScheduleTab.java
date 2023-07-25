package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_39925_VerifyThatUserCanSeeTaskTicketsOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39925_VerifyThatUserCanSeeTaskTicketsOnMyScheduleTab {

  private ReactTrainingScreen reactTrainingScreen;
  private int ticketAtTasksSectionCount;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingScreen() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
    ticketAtTasksSectionCount =
        reactTrainingScreen.getTaskTicketsAtTasksSectionList().size();
  }

  @Test(priority = 1)
  public void checkTasksSectionDisplayed() {
    assertTrue(reactTrainingScreen.isTasksSectionDisplayed(),
        "'Tasks' section on 'My schedule' tab isn't displayed!");
  }

  @Test(priority = 2)
  public void checkDeadlineFieldInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(
          reactTrainingScreen.isDeadlineTitleInTaskTicketDisplayedByIndex(ticketIndex + 1),
          format("'Deadline' title in %s ticket isn't displayed!", ticketIndex + 1));
      softAssert.assertTrue(
          reactTrainingScreen.isDeadlineDateInTaskTicketDisplayedByIndex(ticketIndex + 1),
          format("'Deadline' date in %s ticket isn't displayed!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkGroupNameInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(
          reactTrainingScreen.isGroupNameInTaskTicketDisplayedByIndex(ticketIndex + 1),
          format("'Group name' field in %s ticket isn't displayed!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTaskNameInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(
          reactTrainingScreen.isTaskNameInTaskTicketDisplayedByIndex(ticketIndex + 1),
          format("'Task name' field in %s ticket isn't displayed!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSubmittedFieldInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(
          reactTrainingScreen.isSubmittedTaskStatusDisplayedByIndex(ticketIndex + 1),
          format("'Submitted' status field in %s ticket isn't displayed!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkCheckedFieldInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(
          reactTrainingScreen.isCheckedTaskStatusDisplayedByIndex(ticketIndex + 1),
          format("'Checked' status field in %s ticket isn't displayed!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkCheckButtonInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(reactTrainingScreen.isCheckButtonDisplayedByIndex(ticketIndex + 1),
          format("'Check' button in %s ticket isn't displayed!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }
}
