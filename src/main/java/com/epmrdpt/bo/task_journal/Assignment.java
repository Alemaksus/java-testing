package com.epmrdpt.bo.task_journal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Assignment {

  @JsonProperty("TaskId")
  private int taskId;
  @JsonProperty("GroupId")
  private int groupId;
  @JsonProperty("AssignedAt")
  private String assignedAt;
  @JsonProperty("Deadline")
  private String deadline;
  @JsonProperty("ReviewersIds")
  private List<Integer> reviewersIds;
  @JsonProperty("Notice")
  private Notice notice;
  @JsonProperty("NumberOfVariant")
  private int numberOfVariant;
  @JsonProperty("TrainingPortalLink")
  private String trainingPortalLink;

  public Assignment withTaskId(int taskId) {
    this.taskId = taskId;
    return this;
  }

  public Assignment withDeadline(String deadline) {
    this.deadline = deadline;
    return this;
  }

  public Assignment withGroupId(int groupId) {
    this.groupId = groupId;
    return this;
  }

  public Assignment withAssignedAt(String assignedAt) {
    this.assignedAt = assignedAt;
    return this;
  }

  public Assignment withReviewersIds(List<Integer> reviewersIds) {
    this.reviewersIds = reviewersIds;
    return this;
  }

  public Assignment withNotice(Notice notice) {
    this.notice = notice;
    return this;
  }

  public Assignment withNumberOfVariant(int numberOfVariant) {
    this.numberOfVariant = numberOfVariant;
    return this;
  }

  public Assignment withTrainingPortalLink(String trainingPortalLink) {
    this.trainingPortalLink = trainingPortalLink;
    return this;
  }

  public int getTaskId() {
    return taskId;
  }

  public int getGroupId() {
    return groupId;
  }

  public String getAssignedAt() {
    return assignedAt;
  }

  public String getDeadline() {
    return deadline;
  }

  public List<Integer> getReviewersIds() {
    return reviewersIds;
  }

  public Notice getNotice() {
    return notice;
  }

  public int getNumberOfVariant() {
    return numberOfVariant;
  }

  public String getTrainingPortalLink() {
    return trainingPortalLink;
  }
}
