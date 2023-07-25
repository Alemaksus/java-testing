package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_OFFLINE_TASK_INFO_SPECIFIC_WEIGHT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_ASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_ATTEMPTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_ATTEMPTS_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_AVERAGE_MARK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_CANCEL_ASSIGN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_CREATOR;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_ONLINE_TASK_INFO_REVIEWERS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_CHECKED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_DEADLINE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_SUBMITTED;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactOnlineTaskInfoPopUpScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_50951_VerifyTheElementsOfOnlineTaskInfoPopoverInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50951_VerifyTheElementsOfOnlineTaskInfoPopoverInTheTaskJournal {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String taskName = "AutoTest_React_OnlineTask Passed WithMark";
  private final LocalDate assignedDate = LocalDate.of(2023, 1, 1);
  private static final ZonedDateTime expectedDeadlineDateTime =
    ZonedDateTime.of(LocalDateTime.of(2023, 12, 31, 8, 0),
        ZoneId.of("Europe/Minsk"));

  private final int specificWeight = 1;

  private ReactOnlineTaskInfoPopUpScreen reactOnlineTaskInfoPopUpScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupOnlineTaskInTaskJournal() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility()
        .clickMyGroupsTab();
    reactOnlineTaskInfoPopUpScreen = new ReactTrainingService()
        .openGroupJournalByName(groupName)
        .clickTaskJournalTab()
        .waitGroupJournalTableForVisibility()
        .clickAllPeriodButton()
        .clickOnlineTaskInTaskJournalByName(taskName);
  }

  @Test
  public void checkTaskNameOfOnlineTask() {
    assertEquals(reactOnlineTaskInfoPopUpScreen.getOnlineTaskName(), taskName,
        "'Task name' on the 'Online task info pop up' is incorrect!");
  }

  @Test
  public void checkGroupNameOfOnlineTask() {
    assertEquals(reactOnlineTaskInfoPopUpScreen.getGroupName(), groupName,
        "'Group name' on the 'Online task info pop up' is incorrect!");
  }

  @Test
  public void checkButtonSectionInOnlineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen
            .mouseOverAssignToEveryoneButton()
            .getTooltipButtonText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_CANCEL_ASSIGN),
        "'Assign to everyone' tooltip on the 'Online task info pop up' has incorrect text!");
    softAssert.assertTrue(reactOnlineTaskInfoPopUpScreen.isEditTaskButtonDisplayed(),
        "'Edit' button on the 'Online task info pop up' isn't displayed!");
    softAssert.assertTrue(reactOnlineTaskInfoPopUpScreen.isDeleteTaskButtonDisplayed(),
        "'Delete' button on the 'Online task info pop up' isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkDeadlineSectionInOnlineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactOnlineTaskInfoPopUpScreen.isDeadlineClockIconDisplayed(),
        "'Deadline' icon on the 'Online task info pop up' isn't displayed!");
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getDeadlineTitleText(),
        getValueOf(REACT_TRAINING_TASK_DEADLINE),
        "'Deadline' title on the 'Online task info pop up' has incorrect text!");
    ZonedDateTime actualDeadlineTime = ZonedDateTime.of(
        reactOnlineTaskInfoPopUpScreen.getDeadlineDateTime(), ZoneId.systemDefault());
    softAssert.assertTrue(expectedDeadlineDateTime.isEqual(actualDeadlineTime),
        "'Deadline date' on the 'Online task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkSubmittedFieldInOnlineTaskInfoPopup() {
    assertTrue(reactOnlineTaskInfoPopUpScreen.getSubmittedTaskStatusText()
            .matches(getValueOf(REACT_TRAINING_TASK_SUBMITTED)),
        "'Submitted' task status on the 'Online task info pop up' has incorrect format!");
  }

  @Test
  public void checkCheckedFieldInOnlineTaskInfoPopup() {
    assertTrue(reactOnlineTaskInfoPopUpScreen.getCheckedTaskStatusText()
            .matches(getValueOf(REACT_TRAINING_TASK_CHECKED)),
        "'Checked' task status on the 'Online task info pop up' has incorrect format!");
  }

  @Test
  public void checkAssignedFieldInOnlineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getAssignedTitleText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_ASSIGNED),
        "'Assigned' title on the 'Online task info pop up' has incorrect text!");
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getAssignedDateValue(), assignedDate,
        "'Assigned date' on the 'Online task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkAverageMarkFieldInOnlineTaskInfoPopup() {
    softAssert = new SoftAssert();
    double averageMarkOfOnlineTask = new ReactGroupJournalService()
        .countAverageTaskMarkByTaskName(taskName);
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getAverageMarkTitleText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_AVERAGE_MARK),
        "'Average mark' title on the 'Online task info pop up' has incorrect text!");
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getAverageMarkValue(),
        averageMarkOfOnlineTask,
        "'Average mark' value on the 'Online task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkAttemptsFieldInOnlineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getAttemptsTitleText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_ATTEMPTS),
        "'Attempts' title on the 'Online task info pop up' has incorrect text!");
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getAttemptsText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_ATTEMPTS_VALUE),
        "'Attempts' value on the 'Online task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkSpecificWeightFieldInOfflineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getSpecificWeightTitleText(),
        getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_SPECIFIC_WEIGHT),
        "'Specific weight' title on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getSpecificWeightValue(), specificWeight,
        "'Specific weight' value on the 'Offline task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkCreatorFieldInOnlineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getCreatorTitleText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_CREATOR),
        "'Creator' title on the 'Online task info pop up' has incorrect text!");
    softAssert.assertTrue(reactOnlineTaskInfoPopUpScreen.isCreatorIconDisplayed(),
        "'Creator' icon on the 'Online task info pop up' isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkReviewersFieldInOnlineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactOnlineTaskInfoPopUpScreen.getReviewersTitleText(),
        getValueOf(REACT_TRAINING_ONLINE_TASK_INFO_REVIEWERS),
        "'Reviewers' title on the 'Online task info pop up' has incorrect text!");
    softAssert.assertTrue(reactOnlineTaskInfoPopUpScreen.isReviewersIconDisplayed(),
        "'Reviewers' icon on the 'Online task info pop up' isn't displayed!");
    softAssert.assertAll();
  }
}
