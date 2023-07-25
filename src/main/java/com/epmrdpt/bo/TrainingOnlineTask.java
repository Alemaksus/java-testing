package com.epmrdpt.bo;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingOnlineTask extends TrainingOfflineTask {

  private String additionalTrainer;
  private LocalDate startDate;
  private LocalTime startTime;
  private LocalDate deadlineDate;
  private LocalTime deadlineTime;
  private int maxMark;
  private int passMark;
  private int defaultMarkForExpiredTask;
  private int attempts;
  private int deadlineNotice;
  private TaskReview taskReview = new TaskReview();

  @Override
  public TrainingOnlineTask withTaskName(String name) {
    this.taskName = name;
    return this;
  }

  @Override
  public TrainingOnlineTask withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public TrainingOnlineTask withStudent(String student) {
    this.student = student;
    return this;
  }

  public TrainingOnlineTask withTaskId(int taskId) {
    this.taskId = taskId;
    return this;
  }

  public TrainingOnlineTask withMarkOnReviewTask(int markOnReviewTask) {
    taskReview.setMark(markOnReviewTask);
    return this;
  }

  public TrainingOnlineTask withCommentForStudentOnReview(String commentForStudentOnReview) {
    taskReview.setCommentForStudent(commentForStudentOnReview);
    return this;
  }

  public TrainingOnlineTask withCommentForTrainerOnReview(String commentForTrainerOnReview) {
    taskReview.setCommentForTrainers(commentForTrainerOnReview);
    return this;
  }

  public TrainingOnlineTask withPassMarkOnReviewTask(int passMarkOnReviewTask) {
    this.passMark = passMarkOnReviewTask;
    return this;
  }

  @Override
  public String getTaskName() {
    return taskName;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String getGroup() {
    return group;
  }

  @Override
  public String getStudent() {
    return student;
  }

  public int getMarkOnReviewTask() {
    return taskReview.getMark();
  }

  public String getCommentForStudentOnReview() {
    return taskReview.getCommentForStudent();
  }

  public String getCommentForTrainerOnReview() {
    return taskReview.getCommentForTrainers();
  }

  public int getPassMark() {
    return passMark;
  }

  public int getTaskId() {
    return taskId;
  }

  @Override
  public String toString() {
    return "TrainingOnlineTask{" +
        "taskName='" + taskName + '\'' +
        ", group='" + group + '\'' +
        ", mainTrainer='" + mainTrainer + '\'' +
        ", student='" + student + '\'' +
        ", description='" + description + '\'' +
        ", additionalTrainer='" + additionalTrainer + '\'' +
        ", startDate=" + startDate +
        ", startTime=" + startTime +
        ", deadlineDate=" + deadlineDate +
        ", deadlineTime=" + deadlineTime +
        ", maxMark=" + maxMark +
        ", passMark=" + passMark +
        ", taskId='" + taskId + '\'' +
        ", defaultMarkForExpiredTask=" + defaultMarkForExpiredTask +
        ", attempts=" + attempts +
        ", deadlineNotice=" + deadlineNotice +
        ", taskReview=" + taskReview +
        '}';
  }
}
