package com.epmrdpt.smoke.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.utils.EColorUtils;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_59315_VerifyThatUserCanChooseTheTimePeriodForDataDisplayingUsingDailyFilterInTheTaskJournal",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_59315_VerifyThatUserCanChooseTheTimePeriodForDataDisplayingUsingDailyFilterInTheTaskJournal {

  private ReactTasksJournalScreen reactTasksJournalScreen;
  private final TrainingOnlineTask trainingOnlineTask = new TrainingOnlineTask()
      .withGroup("test_task_journal");
  private final String activePeriodButtonColour = EColorUtils.BLUE_COLOUR.getColorRgbaFormat();
  private final int daysInWeekPeriod = 6;
  private final int daysInTwoWeekPeriod = 13;
  private final int daysInMonthPeriod = ((LocalDate.now().lengthOfMonth()) - 1);
  private final int daysInCustomPeriod = 20;
  private final int daysInAllPeriod = 351;
  private final LocalDate customPeriodStartDate = LocalDate.now().minusDays(10);
  private final LocalDate customPeriodEndDate = LocalDate.now().plusDays(10);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTasksJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab()
        .typeGroupNameInput(trainingOnlineTask.getGroup())
        .clickSearchIcon()
        .clickGroupNameButton()
        .clickTaskJournalTab()
        .waitForLoadingSpinnerInvisibility();
  }

  @Test(priority = 1)
  public void VerifyThatTheWeekPeriodFilterApplied() {
    reactTasksJournalScreen
        .waitTableHeaderForVisibility()
        .clickWeekPeriodButton()
        .waitTableHeaderForVisibility();
    Assert.assertTrue(reactTasksJournalScreen.isScreenLoaded(),
        "The week period filter is not applied");
    Assert.assertEquals(reactTasksJournalScreen.getWeekPeriodButtonColour(),
        activePeriodButtonColour,
        "The week period button colour is not blue");
    Assert.assertEquals(ChronoUnit.DAYS.between(
            reactTasksJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactTasksJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInWeekPeriod,
        "The selected period does not equals to week period");
  }

  @Test(priority = 2)
  public void VerifyThatTheTwoWeekPeriodFilterApplied() {
    reactTasksJournalScreen
        .waitTableHeaderForVisibility()
        .clickTwoWeekPeriodButton()
        .waitTableHeaderForVisibility();
    Assert.assertTrue(reactTasksJournalScreen.isScreenLoaded(),
        "The two week period filter is not applied");
    Assert.assertEquals(ChronoUnit.DAYS.between(
            reactTasksJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactTasksJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInTwoWeekPeriod,
        "The selected period does not equals to two week period");
  }

  @Test(priority = 3)
  public void VerifyThatTheMonthPeriodFilterApplied() {
    reactTasksJournalScreen
        .waitTableHeaderForVisibility()
        .clickMonthPeriodButton()
        .waitTableHeaderForVisibility();
    Assert.assertTrue(reactTasksJournalScreen.isScreenLoaded(),
        "The month period filter is not applied");
    Assert.assertEquals(ChronoUnit.DAYS.between(
            reactTasksJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactTasksJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInMonthPeriod,
        "The selected period does not equals to month period");
  }

  @Test(priority = 4)
  public void VerifyThatTheCustomFilterApplied() {
    new ReactTaskJournalService().setCustomPeriodToCalendar(customPeriodStartDate,
        customPeriodEndDate);
    Assert.assertEquals(ChronoUnit.DAYS.between(
            reactTasksJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactTasksJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInCustomPeriod,
        "The selected period does not equals to custom period");
  }

  @Test(priority = 5)
  public void VerifyThatTheAllPeriodFilterApplied() {
    reactTasksJournalScreen
        .waitTableHeaderForVisibility()
        .clickAllPeriodButton()
        .waitTableHeaderForVisibility();
    Assert.assertTrue(reactTasksJournalScreen.isScreenLoaded(),
        "The all period filter is not applied");
    Assert.assertEquals(ChronoUnit.DAYS.between(
            reactTasksJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactTasksJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInAllPeriod,
        "The selected period does not equals to all period");
  }
}
