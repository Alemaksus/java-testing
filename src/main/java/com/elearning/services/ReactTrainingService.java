package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.bo.LessonDetails;
import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactAddClassPopUpScreen;
import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.screens.ReactEditClassPopUpScreen;
import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.screens.ReactHomeTaskPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class ReactTrainingService {

  private static final int SCHEDULE_TIME_DURATION_IN_SECONDS = 55800;

  private ReactTrainingScreen reactTrainingScreen = new ReactTrainingScreen();

  public ReactTrainingService navigateToMyGroupsTabAndSearchGroup(String groupName) {
    reactTrainingScreen
        .clickMyGroupsTab()
        .typeGroupNameInput(groupName)
        .clickSearchIcon()
        .waitPageNumbersForDisappear()
        .clickTrainingButton()
        .waitScheduleForVisibility();
    Log.logInfoMessage("Navigated to My groups tab and search group %s", groupName);
    return this;
  }

  public ReactGroupJournalScreen searchGroupByName(String groupName) {
    return searchTrainingByName(groupName)
        .clickTrainingJournalLink()
        .waitTableHeaderForVisibility();
  }

  private ReactTrainingScreen searchTrainingByName(String groupName) {
    reactTrainingScreen
        .clickMyGroupsTab()
        .typeGroupNameInput(groupName);
    reactTrainingScreen.selectAllGroups();
    if (!reactTrainingScreen.getGroupNameButtonText().equals(groupName)) {
      reactTrainingScreen.clickSearchIcon();
    }
    reactTrainingScreen
        .waitPageNumbersForDisappear();
    return reactTrainingScreen;
  }

  public String getClassTopicFromLessonInfoPopUpText() {
    return reactTrainingScreen
        .clickClassCard()
        .waitLessonInfoForVisibility()
        .getLessonTopicText();
  }

  public ReactTrainingScreen searchGroupByNameAndClickTrainingByName(String groupName,
      String trainingName) {
    new ReactTrainingScreen()
        .clickMyGroupsTab()
        .clickAdvancedSearchButton()
        .clickOnlyMyGroupsCheckBox()
        .clickApplyButton()
        .waitPageNumbersForVisibility()
        .typeGroupNameInput(groupName)
        .clickSearchIcon()
        .waitPageNumbersForDisappear()
        .clickTrainingByNameInMyGroupsTab(trainingName)
        .waitScheduleForVisibility();
    return new ReactTrainingScreen();
  }

  public ReactTrainingScreen uncheckOnlyMyGroupCheckBox() {
    new ReactTrainingScreen()
        .clickMyGroupsTab()
        .clickAdvancedSearchButton()
        .clickOnlyMyGroupsCheckBox()
        .clickApplyButton();
    return new ReactTrainingScreen();
  }

  public ReactTrainingService fillAllRequiredFieldsWithCurrentDateToAddClassInSchedule(
      TrainingClass trainingClass) {
    new ReactAddClassPopUpScreen()
        .waitClassFormForVisibility()
        .clickAdvancedSettingSwitch()
        .typeClassName(trainingClass.getName())
        .clickStartDateInCalendarInput()
        .clickCurrentDayButton()
        .clearAndTypeStartClassTimeInput(trainingClass.getStartTime())
        .clearAndTypeEndClassTimeInput(trainingClass.getEndTime())
        .clickHeadTrainerInput()
        .clickTrainerField(trainingClass.getMainTrainer())
        .clickAddClassButton()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactTrainingScreen deleteClassInSchedule(String className) {
    return new ReactTrainingScreen()
        .clickInScheduleClassCardByTopic(className)
        .clickDeleteIconButton()
        .clickConfirmDeleteButton()
        .clickCloseNotificationButton();
  }

  public ReactTrainingScreen selectTrainingByGroupName(String groupName) {
    return uncheckOnlyMyGroupCheckBox()
        .waitPageNumbersForVisibility()
        .typeGroupNameInput(groupName)
        .clickSearchIcon()
        .waitPageNumbersForDisappear();
  }

  public ReactTrainingScreen selectTrainingByUsingEnterKeyboard(String groupName) {
    reactTrainingScreen
        .clickEnterInMyGroupsSearchInput();
    if (!reactTrainingScreen.getGroupNameButtonText().equals(groupName)) {
      reactTrainingScreen.clickEnterInMyGroupsSearchInput();
    }
    reactTrainingScreen
        .waitForNumberOfSelectedTrainingsToBeEqualOne();
    return reactTrainingScreen;
  }

  public ReactTrainingScreen openGroupScheduleByName(String groupName, String classTopic) {
    selectTrainingByGroupName(groupName)
        .clickTrainingButton();
    if (!reactTrainingScreen.isClassTicketInScheduleByNameDisplayed(classTopic)) {
      reactTrainingScreen
          .clickTrainingButton()
          .waitScheduleForVisibility();
    }
    return reactTrainingScreen;
  }

  public ReactGroupJournalScreen openTrainingJournalByGroupName(String groupName) {
    return selectTrainingByGroupName(groupName)
        .clickTrainingJournalLink()
        .waitTableHeaderForVisibility();
  }

  public ReactGroupJournalScreen openGroupJournalByName(String groupName) {
    return reactTrainingScreen
        .clickAdvancedSearchButton()
        .clickOnlyMyGroupsCheckBox()
        .clickApplyButton()
        .typeGroupNameInput(groupName)
        .clickSearchIcon()
        .waitPageNumbersForDisappear()
        .clickGroupByName(groupName)
        .waitForLoadingSpinnerInvisibility();
  }

  public ReactGroupJournalScreen openGroupJournalByNameAndChooseAllPeriod(String groupName) {
    return openGroupJournalByName(groupName)
        .waitTableHeaderForVisibility()
        .clickAllPeriodButton()
        .waitForLoadingSpinnerInvisibility();
  }

  public ReactTaskJournalService openScheduleTabByGroupName(String groupName) {
    openGroupJournalByName(groupName)
        .clickScheduleTab()
        .waitScheduleTableForVisibility();
    return new ReactTaskJournalService();
  }

  public ReactGraduateReportScreen openGraduateReportByGroupName(String groupName) {
    openGroupJournalByName(groupName)
        .clickGraduateReportTab()
        .waitGraduateReportForVisibility();
    return new ReactGraduateReportScreen();
  }

  public String getGroupNameFromGroupJournalText() {
    return reactTrainingScreen
        .clickGroupJournalButton()
        .waitTableHeaderForVisibility()
        .getTextFromBredCrumbs();
  }

  public String getGroupNameFromTasksJournalText() {
    return new ReactGroupJournalScreen()
        .clickTaskJournalTab()
        .waitTableHeaderForVisibility()
        .getTextFromBredCrumbs();
  }

  public ReactEditClassPopUpScreen openEditClassPopUpByName(String className) {
    return reactTrainingScreen
        .clickInScheduleClassCardByTopic(className)
        .clickEditIcon()
        .waitClassTopicFromEditNotEmpty()
        .waitMainTrainerFromEditNotEmpty();
  }

  public int getFirstTaskIndexContainsDeadlineDateFromSchedule() {
    int ticketAtTasksSectionCount = reactTrainingScreen.getTaskTicketsAtTasksSectionCount();
    int taskIndex = 1;
    for (int ticketIndex = 0; ticketIndex < ticketAtTasksSectionCount; ticketIndex++) {
      if (reactTrainingScreen.getDeadlineTitleInTaskTicketByIndex(ticketIndex + 1).equals(
          LocaleProperties.getValueOf(LocalePropertyConst.REACT_TRAINING_TASK_DEADLINE))) {
        break;
      }
      taskIndex++;
    }
    return taskIndex;
  }

  public ReactTrainingScreen openScheduleByDate(LocalDate date) {
    reactTrainingScreen
        .clickMonthAndYearInCalendar()
        .clickMonthAndYearInCalendar()
        .waitYearSelectionContainerForVisibility()
        .clickYearInCalendar(date.getYear())
        .waitMonthSelectionContainerForVisibility()
        .clickMonthInCalendar(getMonthNameByDate(date))
        .waitDatesOfMonthInCalendarForVisibility()
        .clickDayInCalendar(date.getDayOfMonth());
    return new ReactTrainingScreen();
  }

  public String getMonthNameByDate(LocalDate date) {
    Locale locale = Locale
        .forLanguageTag(new LanguageSwitchingService().getLocaleLanguage().getLanguageCode());
    Month month = date.getMonth();
    String monthName = month.getDisplayName(TextStyle.FULL, locale).substring(0, 3);
    return monthName.endsWith("ая") ? monthName.replaceAll("ая", "ай") : monthName;
  }

  public LocalTime getTimeFromSchedule() {
    return LocalTime.of(8, 0, 0).plus((int) (SCHEDULE_TIME_DURATION_IN_SECONDS *
        reactTrainingScreen.getDayFromScheduleExpirationPercent() / 100), ChronoUnit.SECONDS);
  }

  public ReactTaskJournalService openTaskJournalByGroupName(String groupName) {
    openGroupJournalByName(groupName)
        .clickTaskJournalTab()
        .waitGroupJournalTableForVisibility()
        .waitTableHeaderForVisibility();
    return new ReactTaskJournalService();
  }

  public ReactTrainingService fillMarkAndCommentsToCheckTaskTicketInScheduleTab(
      TrainingOnlineTask trainingOnlineTask) {
    new ReactHomeTaskPopUpScreen()
        .clickEstimationButtonByMark(trainingOnlineTask.getMarkOnReviewTask())
        .typeCommentForStudent(trainingOnlineTask.getCommentForStudentOnReview())
        .typeCommentForTrainer(trainingOnlineTask.getCommentForTrainerOnReview())
        .clickSubmitButton()
        .waitSpinnerOfLoadingDisappear();
    return this;
  }

  public ReactTrainingScreen openMonthViewSchedule() {
     reactTrainingScreen
        .clickViewArrowButtonInSchedule()
        .waitScheduleViewModeSelectionForVisibility()
        .clickScheduleMonthViewModeField()
         .waitScheduleViewModeSelectionForInvisibility();
     if(!reactTrainingScreen.getScheduleDayViewModeText()
         .equals(getValueOf(REACT_TRAINING_SCHEDULE_VIEW_MODE_MONTH))) {
       openMonthViewSchedule();
     }
    return reactTrainingScreen;
  }

  public ReactHomeTaskPopUpScreen fillMarkAndPassMarkToCheckTaskTicketInScheduleTab(
      TrainingOnlineTask trainingOnlineTask) {
    new ReactHomeTaskPopUpScreen()
        .clickEstimationButtonByMark(trainingOnlineTask.getMarkOnReviewTask())
        .clickSubmitButton()
        .waitForInvisibleEstimationButtonByMark(trainingOnlineTask.getMarkOnReviewTask());
    return new ReactHomeTaskPopUpScreen();
  }

  public LessonDetails getLessonDetailsFromLessonInfoPopUp(String classTopic) {
    return reactTrainingScreen
        .clickInScheduleClassCardByTopic(classTopic)
        .waitLessonInfoForVisibility()
        .getLessonDetails();
  }
}