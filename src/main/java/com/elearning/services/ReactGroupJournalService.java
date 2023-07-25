package com.epmrdpt.services;

import com.epmrdpt.bo.TaskReview;
import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.screens.ReactAddClassPopUpScreen;
import com.epmrdpt.screens.ReactEditClassPopUpScreen;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.screens.ReactJournalMarkPopUpScreen;
import com.epmrdpt.utils.RandomUtils;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReactGroupJournalService {

  private ReactGroupJournalScreen reactGroupJournalScreen = new ReactGroupJournalScreen();
  private ReactAddClassPopUpScreen reactAddClassPopUpScreen = new ReactAddClassPopUpScreen();
  private ReactJournalMarkPopUpScreen reactJournalMarkPopUpScreen = new ReactJournalMarkPopUpScreen();

  public ReactGroupJournalScreen deleteClassInTableByDate(LocalDate date) {
    return reactGroupJournalScreen
        .clickInTableClassCardByDate(date)
        .clickDeleteIcon()
        .clickConfirmDeleteButton()
        .clickCloseNotificationButton();
  }

  public ReactGroupJournalScreen deleteClassAndSeriesClassesInTableByDate(LocalDate date) {
    return reactGroupJournalScreen
        .clickInTableClassCardByDate(date)
        .clickDeleteIcon()
        .clickTheEntireSeries()
        .clickConfirmDeleteButton()
        .waitWarningMessageDeleteSeriesForVisibility()
        .clickConfirmDeleteButton()
        .clickCloseNotificationButton();
  }

  public ReactGroupJournalService fillAllRequiredFieldsToAddClassInGroupJournal(
      TrainingClass trainingClass) {
    new ReactAddClassPopUpScreen()
        .waitClassFormForVisibility()
        .clickAdvancedSettingSwitch()
        .typeClassName(trainingClass.getName())
        .clickStartDateInCalendarInput()
        .clickDayOfMonth(String.valueOf(trainingClass.getStartDate().getDayOfMonth()))
        .clearAndTypeStartClassTimeInput(trainingClass.getStartTime())
        .clearAndTypeEndClassTimeInput(trainingClass.getEndTime())
        .clickHeadTrainerInput()
        .clickTrainerField(trainingClass.getMainTrainer())
        .clickAddClassButton()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactEditClassPopUpScreen openEditClassPopUpByDate(LocalDate date) {
    return reactGroupJournalScreen
        .clickInTableClassCardByDate(date)
        .clickEditIcon()
        .waitClassTopicFromEditNotEmpty()
        .waitMainTrainerFromEditNotEmpty();
  }

  public ReactGroupJournalScreen unmarkAbsenceOfStudentByName(String studentName) {
    if (reactGroupJournalScreen.isSymbolOfAbsenceInCellByNameDisplayedShortTimeOut(studentName)) {
      return reactGroupJournalScreen
          .clickCellMarkByName(studentName)
          .waitMarkPopUpForVisibility()
          .clickAbsentButton()
          .clickSaveButton()
          .waitMarkOfAbsenceInCellByNameForDisappear(studentName);
    }
    return reactGroupJournalScreen;
  }

  public ReactGroupJournalScreen addStudentMarkByNameAndDateIndex(int markValue,
      String student, int index) {
    reactGroupJournalScreen.clickStudentMarkFieldByNameAndDateIndex(student, index)
        .clickMarkButtonByValue(markValue)
        .clickSaveButton();
    return new ReactGroupJournalScreen();
  }

  public ReactGroupJournalScreen addStudentAbsenceByNameAndDateIndex(String student, int index) {
    reactGroupJournalScreen.clickStudentMarkFieldByNameAndDateIndex(student, index)
        .clickAbsentButton()
        .clickSaveButton();
    return new ReactGroupJournalScreen();
  }

  public ReactAddClassPopUpScreen fillRequiredFieldsToAddSeriesClassInGroupJournal(
      TrainingClass trainingClass) {
    reactAddClassPopUpScreen
        .waitClassFormForVisibility()
        .clickAdvancedSettingSwitch()
        .typeClassName(trainingClass.getName())
        .clickStartDateInCalendarInput();
    openScheduleByDayAndMonth(trainingClass.getStartDate())
        .clearAndTypeStartClassTimeInput(trainingClass.getStartTime())
        .clearAndTypeEndClassTimeInput(trainingClass.getEndTime())
        .clickHeadTrainerInput()
        .clickTrainerField(trainingClass.getMainTrainer())
        .clickRecurringClass()
        .clickDaysOfRepeated(trainingClass.getRepeatedDaysCounter(),
            LocalDate.now().getDayOfWeek().getValue() - 1)
        .clickDaysOfRepeatedIfCurrentIndexOfWeekDayMoreThanFour(
            trainingClass.getRepeatedDaysCounter(),
            LocalDate.now().getDayOfWeek().getValue() - 1);
    return reactAddClassPopUpScreen;
  }

  public ReactAddClassPopUpScreen fillRequiredFieldsToAddSeriesClassInGroupJournal(
          TrainingClass trainingClass, LocalTime startTime, LocalTime endTime) {
    reactAddClassPopUpScreen
            .waitClassFormForVisibility()
            .clickAdvancedSettingSwitch()
            .typeClassName(trainingClass.getName())
            .clickStartDateInCalendarInput();
    openScheduleByDayAndMonth(trainingClass.getStartDate())
            .clearAndTypeStartClassTimeInput(startTime)
            .clearAndTypeEndClassTimeInput(endTime)
            .clickHeadTrainerInput()
            .clickTrainerField(trainingClass.getMainTrainer())
            .clickRecurringClass()
            .clickDaysOfRepeated(trainingClass.getRepeatedDaysCounter(),
                    LocalDate.now().getDayOfWeek().getValue() - 1)
            .clickDaysOfRepeatedIfCurrentIndexOfWeekDayMoreThanFour(
                    trainingClass.getRepeatedDaysCounter(),
                    LocalDate.now().getDayOfWeek().getValue() - 1);
    return reactAddClassPopUpScreen;
  }

  public ReactGroupJournalService fillFieldsAddSeriesClassForEndAfter(TrainingClass trainingClass) {
    fillRequiredFieldsToAddSeriesClassInGroupJournal(trainingClass)
        .typeClassRepeatCount(trainingClass.getRepeatedDaysCounter())
        .clickAddClassButton()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactGroupJournalService fillFieldsAddSeriesClassForEndBy(TrainingClass trainingClass,
                                                                   LocalTime startTime, LocalTime endTime) {
    fillRequiredFieldsToAddSeriesClassInGroupJournal(trainingClass, startTime, endTime)
        .clickChoiceEndBy()
        .clickEndDateInCalendarInput();
    openScheduleByDayAndMonth(trainingClass.getEndDate())
        .clickAddClassButton()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactAddClassPopUpScreen openScheduleByDayAndMonth(LocalDate date) {
    reactAddClassPopUpScreen
        .clickMonthAndYearInCalendar()
        .waitMonthSelectionContainerForVisibility()
        .clickMonthInCalendar(new ReactTrainingService().getMonthNameByDate(date))
        .waitDatesOfMonthInCalendarForVisibility()
        .clickDayInCalendar(date.getDayOfMonth());
    return new ReactAddClassPopUpScreen();
  }

  public ReactGroupJournalScreen createCellMarkComments(TaskReview taskReview) {
    return reactGroupJournalScreen.clickCellMarkByName(taskReview.getStudentName())
        .waitMarkPopUpForVisibility()
        .typeCommentForStudent(taskReview.getCommentForStudent())
        .typeCommentForTrainer(taskReview.getCommentForTrainers())
        .clickSaveButton()
        .clickCloseNotificationButton();
  }

  public ReactGroupJournalScreen fillStudentRandomMarkIfRequired(String student,
      int totalNumberOfMarks) {
    if (reactGroupJournalScreen.getStudentAverageMark(student) == 0.0) {
      reactGroupJournalScreen.moveScrollBarToBeginning();
      for (int i = 1; i <= totalNumberOfMarks; i++) {
        int randomMark = RandomUtils.getRandomNumberInInterval(1, 10);
        reactGroupJournalScreen.clickStudentMarkFieldByNameAndDateIndex(student, i)
            .clickMarkButtonByValue(randomMark);
        reactJournalMarkPopUpScreen.clickSaveButton()
            .waitMarkForVisibility(randomMark, student, i);
      }
      reactGroupJournalScreen.clickRefreshButton();
    }
    return reactGroupJournalScreen;
  }

  public double countAverageTaskMarkByTaskName(String taskName) {
    return reactGroupJournalScreen.getTaskMarksByTaskName(taskName).stream()
        .filter(item -> !item.equals("-")).mapToInt(Integer::parseInt).average().getAsDouble();
  }
}