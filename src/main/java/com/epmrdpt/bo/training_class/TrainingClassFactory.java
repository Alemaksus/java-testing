package com.epmrdpt.bo.training_class;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingClassFactory {

  private static final String CLASS_NAME = "AutoTest_ClassForDelete";
  private static final String GROUP_NAME = "AutoTest_GroupWithDeletingClassTicket";
  private static final String AUTO_TRAINER_NAME = "AutoTrainer AutoTrainer";

  private TrainingClassFactory() {
  }

  public static TrainingClass forCheckMarkAndCommentsInTheGroupJournal() {
    return new TrainingClass()
        .withName(CLASS_NAME)
        .withGroup(GROUP_NAME)
        .withStartDate(LocalDate.now())
        .withStartTime(LocalTime.of(13, 40))
        .withEndTime(LocalTime.of(14, 0))
        .withMainTrainer(AUTO_TRAINER_NAME);
  }

  public static TrainingClass forCheckDeletingClassFromScheduleTab() {
    return new TrainingClass()
        .withName(CLASS_NAME)
        .withGroup(GROUP_NAME)
        .withStartTime(LocalTime.of(14, 10))
        .withEndTime(LocalTime.of(15, 0))
        .withMainTrainer(AUTO_TRAINER_NAME);
  }

  public static TrainingClass forCheckCreatingClassOnMyGroupsTab() {
    return new TrainingClass()
        .withName(CLASS_NAME)
        .withGroup(GROUP_NAME)
        .withStartDate(LocalDate.now())
        .withStartTime(LocalTime.of(22, 10))
        .withEndTime(LocalTime.of(22, 59))
        .withMainTrainer(AUTO_TRAINER_NAME);
  }

  public static TrainingClass forCheckCreatingClassOnTheGroupJournalPage() {
    return new TrainingClass()
        .withName(CLASS_NAME)
        .withGroup(GROUP_NAME)
        .withStartDate(LocalDate.now())
        .withStartTime(LocalTime.of(22, 45))
        .withEndTime(LocalTime.of(22, 55))
        .withMainTrainer(AUTO_TRAINER_NAME);
  }

  public static TrainingClass forCheckCreatingClassSeries() {
    return new TrainingClass()
        .withName("AutoTest_SeriesClassForGroupJournal")
        .withGroup("AutoTest_GroupForCreatingSeriesClasses")
        .withStartDate(LocalDate.now())
        .withStartTime(LocalTime.of(21, 10))
        .withEndTime(LocalTime.of(21, 15))
        .withMainTrainer(AUTO_TRAINER_NAME)
        .withRepeatedDaysCounter(3);
  }

  public static TrainingClass forCheckCreatingClassSeriesWithEndBy() {
    return new TrainingClass()
        .withName("AutoTest_SeriesClassUntilDateForGroupJournal")
        .withGroup("AutoTest_GroupForCreatingSeriesClasses")
        .withStartDate(LocalDate.now())
        .withEndDate(LocalDate.now().plusDays(1))
        .withStartTime(LocalTime.of(21, 16))
        .withEndTime(LocalTime.of(21, 20))
        .withMainTrainer(AUTO_TRAINER_NAME)
        .withRepeatedDaysCounter(3);
  }
}
