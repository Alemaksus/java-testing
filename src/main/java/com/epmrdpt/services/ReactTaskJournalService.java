package com.epmrdpt.services;

import com.epmrdpt.bo.Student;
import com.epmrdpt.bo.TrainingOfflineTask;
import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.ReactAddOfflineTaskPopUpScreen;
import com.epmrdpt.screens.ReactAddOnlineTaskPopUpScreen;
import com.epmrdpt.screens.ReactHomeTaskPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class ReactTaskJournalService {

  private static final String DATE_PATTERN = "dd.MM.yy";

  ReactTasksJournalScreen reactTasksJournalScreen = new ReactTasksJournalScreen();
  ReactAddOfflineTaskPopUpScreen reactAddOfflineTaskPopUpScreen = new ReactAddOfflineTaskPopUpScreen();
  ReactAddOnlineTaskPopUpScreen reactAddOnlineTaskPopUpScreen = new ReactAddOnlineTaskPopUpScreen();

  public ReactTasksJournalScreen addOnlineTaskIfNoTasksOnTable(TrainingOnlineTask trainingOnlineTask) {
    if (!reactTasksJournalScreen.isTaskHeaderColumnInTableDisplayed()) {
      return addOnlineTaskAndFillAllRequiredFieldsInTaskJournal(trainingOnlineTask);
    }
    return reactTasksJournalScreen;
  }

  public ReactTasksJournalScreen addOnlineTaskAndFillAllRequiredFieldsInTaskJournal(
      TrainingOnlineTask trainingOnlineTask) {
    reactTasksJournalScreen
        .clickAddTaskButton()
        .waitAddTaskPopUpFormForVisibility()
        .clickOnlineTaskToggle()
        .waitForLoadingSpinnerInvisible()
        .waitOnlineSwitchCheckedForVisibility()
        .waitAdditionalTrainerWarningTextVisibility();
    for (int i = 0; i < trainingOnlineTask.getPassMark(); i++) {
      reactAddOnlineTaskPopUpScreen.clickOnPassMarkArrowTop();
    }
    if (reactAddOnlineTaskPopUpScreen.isHeadTrainerPlaceholderDisplayed()) {
      reactAddOnlineTaskPopUpScreen.clickHeadTrainerArrow().clickHeadTrainerFromDropDown();
    }
    reactAddOnlineTaskPopUpScreen
        .typeOnlineTaskName(trainingOnlineTask.getTaskName())
        .waitAddOnlineTaskButtonForClickable()
        .clickAddOnlineTaskButton();
    return reactTasksJournalScreen.waitTableHeaderForVisibility();
  }

  public ReactTasksJournalScreen addOnlineTaskWithoutAssistantAndFillAllRequiredFieldsInTaskJournal
      (TrainingOnlineTask trainingOnlineTask) {
    reactTasksJournalScreen
        .clickAddTaskButton()
        .clickOnlineTaskToggle()
        .waitForLoadingSpinnerInvisible()
        .waitOnlineSwitchCheckedForVisibility()
        .waitAdditionalTrainerWarningTextVisibility();
    for (int i = 0; i < trainingOnlineTask.getPassMark(); i++) {
      reactAddOnlineTaskPopUpScreen.clickOnPassMarkArrowTop();
    }
    if (reactAddOnlineTaskPopUpScreen.isHeadTrainerPlaceholderDisplayed()) {
      reactAddOnlineTaskPopUpScreen.clickHeadTrainerArrow().clickHeadTrainerFromDropDown();
    }
    reactAddOnlineTaskPopUpScreen
        .typeOnlineTaskName(trainingOnlineTask.getTaskName())
        .waitAddOnlineTaskButtonForClickable()
        .clickAddOnlineTaskButton();
    return reactTasksJournalScreen.waitTableHeaderForVisibility();
  }

  public ReactTasksJournalScreen addOfflineTaskAndFillAllRequiredFieldsInTaskJournal(
      TrainingOfflineTask trainingOfflineTask) {
    reactTasksJournalScreen
        .waitTableHeaderForVisibility()
        .clickAddTaskButton()
        .typeOfflineTaskName(trainingOfflineTask.getTaskName())
        .clickDateInput()
        .clickDayInCalendar(trainingOfflineTask.getDate().getDayOfMonth());
    if (reactAddOfflineTaskPopUpScreen.isHeadTrainerPlaceholderDisplayed()) {
      reactAddOfflineTaskPopUpScreen.clickHeadTrainerArrow().clickHeadTrainerFromDropDown();
    }
    return reactAddOfflineTaskPopUpScreen
        .clickAddOfflineTaskButton()
        .waitTableHeaderForVisibility();
  }

  public ReactTasksJournalScreen addAssignmentByOnlineTaskName(String taskName) {
    return reactTasksJournalScreen
        .clickOnlineTaskInTaskJournalByName(taskName)
        .clickAssignToEveryoneButton()
        .waitAddAssignmentForEveryonePopUpFormForVisibility()
        .clickDeadlineNotificationCheckbox()
        .clickAssignButton()
        .clickCloseNotificationButton()
        .waitTableHeaderForVisibility();
  }

  public ReactTaskJournalService cancelTaskCheck(String studentName,
      TrainingOnlineTask trainingOnlineTask) {
    reactTasksJournalScreen
        .clickTaskStatusByStudentName(studentName, trainingOnlineTask);
    new ReactHomeTaskPopUpScreen()
        .clickCancelReviewButton()
        .clickConfirmationReviewButton()
        .waitForCheckedStatusHasDisappeared()
        .clickClosePopUpButton();
    return this;
  }

  public ReactTaskJournalService reassignTask(String studentName, TrainingOnlineTask task) {
    reactTasksJournalScreen
        .waitPassedStatusChangeByStudentName(studentName, task)
        .waitMarkChangeToDefaultByStudentName(studentName, task)
        .clickTaskStatusByStudentName(studentName, task)
        .clickReassignPopUpButton()
        .clickDeadlineNotificationCheckbox()
        .clickAssignButton()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactTaskJournalService unassignTask(String taskName) {
    reactTasksJournalScreen
        .clickOnlineTaskInTaskJournalByName(taskName)
        .clickAssignToEveryoneButton();
    reactTasksJournalScreen
        .waitAssignmentCancelEveryonePopUpVisibility();
    return this;
  }

  public ReactTaskJournalService unassignTaskForAllStudents(TrainingOnlineTask task) {
    reactTasksJournalScreen
        .clickOnlineTaskInTaskJournalByName(task.getTaskName())
        .clickAssignToEveryoneButton();
    reactTasksJournalScreen
        .waitAssignmentCancelEveryonePopUpVisibility()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactTaskJournalService unassignOnlineTask(TrainingOnlineTask task) {
    reactTasksJournalScreen
        .clickTaskStatus(task)
        .clickUnassignButtonOnTaskStatusEditPopUp()
        .clickSaveChangesButton()
        .waitTaskHasBeenUnassignedPopUpVisibility();
    return this;
  }

  public ReactTaskJournalService deleteOnlineTaskByName(String taskName) {
    reactTasksJournalScreen
        .clickOnlineTaskInTaskJournalByName(taskName)
        .clickDeleteTaskButton()
        .clickConfirmationDeleteTaskButton()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactTaskJournalService deleteOfflineTaskByName(String taskName) {
    reactTasksJournalScreen
        .clickOfflineTaskInTaskJournalByName(taskName)
        .clickDeleteTaskButton()
        .clickConfirmationDeleteTaskButton()
        .clickCloseNotificationButton();
    return this;
  }

  public ReactTaskJournalService deleteOnlineTask(TrainingOnlineTask task) {
    reactTasksJournalScreen
        .clickOnlineTaskInTaskJournalByName(task.getTaskName())
        .clickDeleteTaskButton()
        .clickConfirmationDeleteTaskButton()
        .clickCloseNotificationButton()
        .waitTaskForInvisibility(task.getTaskName());
    return this;
  }

  public ReactTaskJournalService setCustomPeriodToOneYear() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.YY");
    reactTasksJournalScreen.typePeriodFrom(formatter.format(new Date()));
    reactTasksJournalScreen.isScreenLoaded();
    Calendar periodTo = new GregorianCalendar();
    periodTo.add(Calendar.YEAR, 1);
    periodTo.add(Calendar.DAY_OF_MONTH, -1);
    reactTasksJournalScreen
        .typePeriodToAndSubmit(formatter.format(periodTo.getTime()));
    return this;
  }

  public ReactTaskJournalService setCustomPeriodToCalendar(LocalDate firstDate,
      LocalDate lastDate) {
    reactTasksJournalScreen.typePeriodFrom(
        firstDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    reactTasksJournalScreen.waitForLoadingSpinnerInvisibility()
        .isScreenLoaded();
    reactTasksJournalScreen.typePeriodToAndSubmit(
        lastDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    return this;
  }

  public double countAverageMarkDependsOnTaskWeightForStudent(Student student,
      Map<String, Integer> tasksWithWeight){
    List<Double> studentsMarkWithWeight = new ArrayList<>();
    List<Integer> weightList = new ArrayList<>();
    for(String task : tasksWithWeight.keySet()) {
      String mark = reactTasksJournalScreen
          .getStudentMarkByStudentNameAndTrainingTaskName(student.getName(), task);
      int weight = tasksWithWeight.get(task);
      if (!mark.equals("-")) {
        studentsMarkWithWeight.add(Double.parseDouble(mark) * weight);
        weightList.add(weight);
      }
    }
    return studentsMarkWithWeight
        .stream()
        .mapToDouble(a->a)
        .sum()/weightList
        .stream()
        .mapToInt(a->a)
        .sum();
  }

  public double countAverageMarkForStudent(Student student){
    List<Double> studentsMark = student.getTaskMarkList();
    return student.getTaskMarkList()
        .stream()
        .mapToDouble(a -> a)
        .sum() / studentsMark.size();
  }
}
