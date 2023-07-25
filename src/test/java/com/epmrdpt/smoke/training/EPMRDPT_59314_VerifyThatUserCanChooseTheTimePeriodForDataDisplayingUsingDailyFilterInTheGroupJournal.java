package com.epmrdpt.smoke.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.utils.EColorUtils;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_59314_VerifyThatUserCanChooseTheTimePeriodForDataDisplayingUsingDailyFilterInTheGroupJournal",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_59314_VerifyThatUserCanChooseTheTimePeriodForDataDisplayingUsingDailyFilterInTheGroupJournal {

  private ReactGroupJournalScreen reactGroupJournalScreen;
  private final String groupName = "test_task_journal";
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
    reactGroupJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab()
        .typeGroupNameInput(groupName)
        .clickSearchIcon()
        .clickGroupNameButton()
        .waitForLoadingSpinnerInvisibility();
  }

  @Test(priority = 1)
  public void VerifyThatTheWeekPeriodFilterApplied() {
    reactGroupJournalScreen
        .waitTableHeaderForVisibility()
        .clickWeekPeriodButton()
        .waitTableHeaderForVisibility();
    assertTrue(reactGroupJournalScreen.isScreenLoaded(),
        "The week period filter is not applied");
    assertEquals(reactGroupJournalScreen.getWeekPeriodButtonColour(),
        activePeriodButtonColour,
        "The week period button colour is not blue");
    assertEquals(ChronoUnit.DAYS.between(
            reactGroupJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactGroupJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInWeekPeriod,
        "The selected period does not equals to week period");
  }

  @Test(priority = 2)
  public void VerifyThatTheTwoWeekPeriodFilterApplied() {
    reactGroupJournalScreen
        .waitTableHeaderForVisibility()
        .clickTwoWeekPeriodButton()
        .waitTableHeaderForVisibility();
    assertTrue(reactGroupJournalScreen.isScreenLoaded(),
        "The two week period filter is not applied");
    assertEquals(ChronoUnit.DAYS.between(
            reactGroupJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactGroupJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInTwoWeekPeriod,
        "The selected period does not equals to two week period");
  }

  @Test(priority = 3)
  public void VerifyThatTheMonthPeriodFilterApplied() {
    reactGroupJournalScreen
        .waitTableHeaderForVisibility()
        .clickMonthPeriodButton()
        .waitTableHeaderForVisibility();
    assertTrue(reactGroupJournalScreen.isScreenLoaded(),
        "The month period filter is not applied");
    assertEquals(ChronoUnit.DAYS.between(
            reactGroupJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactGroupJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInMonthPeriod,
        "The selected period does not equals to month period");
  }

  @Test(priority = 4)
  public void VerifyThatTheCustomFilterApplied() {
    new ReactTaskJournalService().setCustomPeriodToCalendar(customPeriodStartDate,
        customPeriodEndDate);
    assertEquals(ChronoUnit.DAYS.between(
            reactGroupJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactGroupJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInCustomPeriod,
        "The selected period does not equals to custom period");
  }

  @Test(priority = 5)
  public void VerifyThatTheAllPeriodFilterApplied() {
    reactGroupJournalScreen
        .waitTableHeaderForVisibility()
        .clickAllPeriodButton()
        .waitTableHeaderForVisibility();
    assertTrue(reactGroupJournalScreen.isScreenLoaded(),
        "The all period filter is not applied");
    assertEquals(ChronoUnit.DAYS.between(
            reactGroupJournalScreen.getFirstDayOfSelectedPeriodFromCalendarField(),
            reactGroupJournalScreen.getLastDayOfSelectedPeriodFromCalendarField()), daysInAllPeriod,
        "The selected period does not equals to all period");
  }
}
