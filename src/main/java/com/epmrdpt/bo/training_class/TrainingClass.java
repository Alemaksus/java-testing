package com.epmrdpt.bo.training_class;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingClass {

  private String name;
  private String type;
  private String group;
  private String place;
  private String mainTrainer;
  private String additionalTrainer;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private LocalTime startTime;
  private LocalTime endTime;
  private int repeatedDaysCounter;

  public TrainingClass() {
  }

  public TrainingClass withName(String name) {
    this.name = name;
    return this;
  }

  public TrainingClass withGroup(String group) {
    this.group = group;
    return this;
  }

  public TrainingClass withMainTrainer(String mainTrainer) {
    this.mainTrainer = mainTrainer;
    return this;
  }

  public TrainingClass withStartDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public TrainingClass withEndDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  public TrainingClass withStartTime(LocalTime startTime) {
    this.startTime = startTime;
    return this;
  }

  public TrainingClass withEndTime(LocalTime endTime) {
    this.endTime = endTime;
    return this;
  }

  public TrainingClass withRepeatedDaysCounter(int repeatedDaysCounter) {
    this.repeatedDaysCounter = repeatedDaysCounter;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getGroup() {
    return group;
  }

  public String getPlace() {
    return place;
  }

  public String getMainTrainer() {
    return mainTrainer;
  }

  public String getAdditionalTrainer() {
    return additionalTrainer;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public int getRepeatedDaysCounter() {
    return repeatedDaysCounter;
  }

  @Override
  public String toString() {
    return "TrainingClass{" +
        "name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", group='" + group + '\'' +
        ", place='" + place + '\'' +
        ", mainTrainer='" + mainTrainer + '\'' +
        ", additionalTrainer='" + additionalTrainer + '\'' +
        ", description='" + description + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", repeatedDaysCounter=" + repeatedDaysCounter +
        '}';
  }
}
