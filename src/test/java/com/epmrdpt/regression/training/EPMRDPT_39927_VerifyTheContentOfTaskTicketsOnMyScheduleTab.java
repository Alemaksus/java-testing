package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_CHECKED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_CHECK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_DEADLINE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_SUBMITTED;
import static com.epmrdpt.utils.StringUtils.isDateMatchExpectedPattern;
import static java.lang.String.format;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_39927_VerifyTheContentOfTaskTicketsOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39927_VerifyTheContentOfTaskTicketsOnMyScheduleTab {

  private String dateFormat = "dd.MM.yyyy, HH:mm";
  private ReactTrainingScreen reactTrainingScreen;
  private int ticketAtTasksSectionCount;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
    ticketAtTasksSectionCount = reactTrainingScreen
        .getTaskTicketsAtTasksSectionCount();
  }

  @Test
  public void checkDeadlineIconInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(
          reactTrainingScreen.isClockIconInTaskTicketDisplayedByIndex(ticketIndex + 1),
          format("'Clock' icon in ticket %s isn't displayed!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkDeadlineTitleInTaskTicket() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertEquals(
          reactTrainingScreen.getDeadlineTitleInTaskTicketByIndex(ticketIndex + 1),
          getValueOf(REACT_TRAINING_TASK_DEADLINE),
          format("'Deadline' title in ticket %s is incorrect!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkDeadlineDateInTaskTicket() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertTrue(isDateMatchExpectedPattern(
              reactTrainingScreen.getDeadlineDateInTaskTicketByIndex(ticketIndex + 1), dateFormat),
          format("'Deadline' date in ticket %s has incorrect format!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkGroupNameFieldInTaskTicket() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertFalse(
          reactTrainingScreen.getGroupNameInTaskTicketByIndex(ticketIndex + 1).isEmpty(),
          format("'Group name' field in ticket %s is empty!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkTaskNameFieldInTaskTicket() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertFalse(
          reactTrainingScreen.getTaskNameInTaskTicketByIndex(ticketIndex + 1).isEmpty(),
          format("'Task name' field in ticket %s is empty!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkSubmittedFieldInTaskTicket() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      String submittedTaskStatus = reactTrainingScreen
          .getSubmittedTaskStatusInTaskTicketByIndex(ticketIndex + 1);
      softAssert.assertTrue(submittedTaskStatus.matches(
              getValueOf(REACT_TRAINING_TASK_SUBMITTED)),
          format("'Submitted' task status in ticket %s has incorrect format!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkCheckedFieldInTaskTicket() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      String checkedTaskStatus = reactTrainingScreen
          .getCheckedTaskStatusInTaskTicketByIndex(ticketIndex + 1);
      softAssert.assertTrue(checkedTaskStatus.matches(
              getValueOf(REACT_TRAINING_TASK_CHECKED)),
          format("'Checked' task status in ticket %s has incorrect format!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkCheckButtonTextInTaskTicket() {
    softAssert = new SoftAssert();
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      softAssert.assertEquals(
          reactTrainingScreen.getCheckButtonTextInTaskTicketByIndex(ticketIndex + 1),
          getValueOf(REACT_TRAINING_TASK_CHECK_BUTTON),
          format("'Check' button in ticket %s has incorrect text!", ticketIndex + 1));
    }
    softAssert.assertAll();
  }
}
