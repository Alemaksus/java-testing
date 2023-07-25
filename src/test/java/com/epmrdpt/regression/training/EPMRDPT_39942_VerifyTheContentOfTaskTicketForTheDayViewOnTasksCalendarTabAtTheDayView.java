package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactTrainingTaskScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_39942_VerifyTheContentOfTaskTicketForTheDayViewOnTasksCalendarTabAtTheDayView",
    groups = {"full", "react", "regression"})
public class EPMRDPT_39942_VerifyTheContentOfTaskTicketForTheDayViewOnTasksCalendarTabAtTheDayView {

  private int taskFieldsOnTaskTabCount;
  private final String dateFormat = "dd.MM.yyyy";

  private ReactTrainingTaskScreen reactTrainingTaskScreen;
  SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTasksTab() {
    reactTrainingTaskScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility()
        .clickTasksButton();
    taskFieldsOnTaskTabCount = reactTrainingTaskScreen
        .getTaskFieldsOnTaskTabCount();
  }

  @Test
  public void checkTaskNameFieldInTaskTicket() {
    softAssert = new SoftAssert();
    for (int taskIndex = 0; taskIndex < taskFieldsOnTaskTabCount; taskIndex++) {
      softAssert.assertFalse(
          reactTrainingTaskScreen.getTaskNameInTaskTicketByIndex(taskIndex + 1).isEmpty(),
          format("'Task name' field in task ticket %s is empty!", taskIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkDeadlineIconInTaskTicketDisplayed() {
    softAssert = new SoftAssert();
    for (int taskIndex = 0; taskIndex < taskFieldsOnTaskTabCount; taskIndex++) {
      softAssert.assertTrue(
          reactTrainingTaskScreen.isClockIconInTaskTicketDisplayedByIndex(taskIndex + 1),
          format("'Clock' icon in task ticket %s isn't displayed!", taskIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkDeadlineTitleInTaskTicket() {
    softAssert = new SoftAssert();
    for (int taskIndex = 0; taskIndex < taskFieldsOnTaskTabCount; taskIndex++) {
      softAssert
          .assertEquals(reactTrainingTaskScreen.getDeadlineTitleInTaskTicketByIndex(taskIndex + 1),
              LocaleProperties.getValueOf(LocalePropertyConst.REACT_TRAINING_TASK_DEADLINE),
              format("'Deadline' title in task ticket %s is incorrect!", taskIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkDeadlineDateInTaskTicket() {
    softAssert = new SoftAssert();
    for (int taskIndex = 0; taskIndex < taskFieldsOnTaskTabCount; taskIndex++) {
      softAssert.assertTrue(StringUtils.isDateMatchExpectedPattern(
              reactTrainingTaskScreen.getDeadlineDateInTaskTicketByIndex(taskIndex + 1), dateFormat),
          format("'Deadline' date in task ticket %s has incorrect format!", taskIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkSubmittedFieldInTaskTicket() {
    softAssert = new SoftAssert();
    for (int taskIndex = 0; taskIndex < taskFieldsOnTaskTabCount; taskIndex++) {
      String submittedTaskStatus = reactTrainingTaskScreen
          .getSubmittedTaskStatusInTaskTicketByIndex(taskIndex + 1);
      softAssert.assertTrue(submittedTaskStatus.matches(
              LocaleProperties.getValueOf(LocalePropertyConst.REACT_TRAINING_TASK_SUBMITTED)),
          format("'Submitted' task status in task ticket %s has incorrect format!", taskIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkCheckedFieldInTaskTicket() {
    softAssert = new SoftAssert();
    for (int taskIndex = 0; taskIndex < taskFieldsOnTaskTabCount; taskIndex++) {
      String checkedTaskStatus = reactTrainingTaskScreen
          .getCheckedTaskStatusInTaskTicketByIndex(taskIndex + 1);
      softAssert.assertTrue(checkedTaskStatus.matches(
              LocaleProperties.getValueOf(LocalePropertyConst.REACT_TRAINING_TASK_CHECKED)),
          format("'Checked' task status in task ticket %s has incorrect format!", taskIndex + 1));
    }
    softAssert.assertAll();
  }
}
