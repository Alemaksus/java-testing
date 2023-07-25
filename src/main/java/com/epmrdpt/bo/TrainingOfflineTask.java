package com.epmrdpt.bo;

import java.time.LocalDate;

public class TrainingOfflineTask {

  protected String taskName;
  protected String group;
  protected String mainTrainer;
  protected String student;
  protected String description;
  protected int taskId;
  protected double fractionalMark;
  private LocalDate date;
  private int maxMark;

  public TrainingOfflineTask withTaskName(String name) {
    this.taskName = name;
    return this;
  }

  public TrainingOfflineTask withGroup(String group) {
    this.group = group;
    return this;
  }

  public TrainingOfflineTask withDate(LocalDate date) {
    this.date = date;
    return this;
  }

  public TrainingOfflineTask withStudent(String student) {
    this.student = student;
    return this;
  }

  public TrainingOfflineTask withTaskId(int taskId) {
    this.taskId = taskId;
    return this;
  }

  public TrainingOfflineTask withMark(double fractionalMark) {
    this.fractionalMark = fractionalMark;
    return this;
  }

  public String getTaskName() {
    return taskName;
  }

  public String getGroup() {
    return group;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getStudent() {
    return student;
  }

  public int getTaskId() {
    return taskId;
  }

  public double getFractionalMark() {
    return fractionalMark;
  }

  @Override
  public String toString() {
    return "TrainingOfflineTask{" +
        "taskName='" + taskName + '\'' +
        ", group='" + group + '\'' +
        ", mainTrainer='" + mainTrainer + '\'' +
        ", student='" + student + '\'' +
        ", description='" + description + '\'' +
        ", taskId='" + taskId + '\'' +
        ", fractionalMark=" + fractionalMark +
        ", date=" + date +
        ", maxMark=" + maxMark +
        '}';
  }
}
