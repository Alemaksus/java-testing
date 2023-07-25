package com.epmrdpt.bo.task_journal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OnlineTask {

  @JsonProperty("Name")
  private String name;
  @JsonProperty("Description")
  private String description;
  @JsonProperty("SpecificWeight")
  private double specificWeight;
  @JsonProperty("GroupIds")
  private List<Integer> groupIds;
  @JsonProperty("HeadTrainerId")
  private int headTrainerId;
  @JsonProperty("AdditionalTrainerIds")
  private List<Integer> additionalTrainerIds;
  @JsonProperty("StartDateTime")
  private String startDateTime;
  @JsonProperty("DeadlineDateTime")
  private String deadlineDateTime;
  @JsonProperty("MaxMark")
  private int maxMark;
  @JsonProperty("AllowFractionalMark")
  private boolean allowFractionalMark;
  @JsonProperty("PassMark")
  private int passMark;
  @JsonProperty("DefaultMark")
  private int defaultMark;
  @JsonProperty("NumberOfSubmissionAttempts")
  private int numberOfSubmissionAttempts;
  @JsonProperty("IncludeDismissedStudentsInCalculations")
  private boolean includeDismissedStudentsInCalculations;
  @JsonProperty("NotifyStudentAfterAssign")
  private boolean notifyStudentAfterAssign;
  @JsonProperty("NotifyStudentBeforeDeadline")
  private boolean notifyStudentBeforeDeadline;
  @JsonProperty("StudentNotificationTimeBeforeDeadline")
  private Duration studentNotificationTimeBeforeDeadline;
  @JsonProperty("AttachedLinks")
  private List<Link> attachedLinks;
  @JsonProperty("AttachedFileIds")
  private List<String> attachedFileIds;
  @JsonProperty("Variants")
  private List<UpdateVariant> variants;

  public OnlineTask withName(String name) {
    this.name = name;
    return this;
  }

  public OnlineTask withDescription(String description) {
    this.description = description;
    return this;
  }

  public OnlineTask withSpecificWeight(double specificWeight) {
    this.specificWeight = specificWeight;
    return this;
  }

  public OnlineTask withGroupIds(List<Integer> groupIds) {
    this.groupIds = groupIds;
    return this;
  }

  public OnlineTask withHeadTrainerId(int headTrainerId) {
    this.headTrainerId = headTrainerId;
    return this;
  }

  public OnlineTask withAdditionalTrainerIds(List<Integer> additionalTrainerIds) {
    this.additionalTrainerIds = additionalTrainerIds;
    return this;
  }

  public OnlineTask withStartDateTime(String startDateTime) {
    this.startDateTime = startDateTime;
    return this;
  }

  public OnlineTask withDeadlineDateTime(String deadlineDateTime) {
    this.deadlineDateTime = deadlineDateTime;
    return this;
  }

  public OnlineTask withMaxMark(int maxMark) {
    this.maxMark = maxMark;
    return this;
  }

  public OnlineTask withAllowFractionalMark(boolean allowFractionalMark) {
    this.allowFractionalMark = allowFractionalMark;
    return this;
  }

  public OnlineTask withPassMark(int passMark) {
    this.passMark = passMark;
    return this;
  }

  public OnlineTask withDefaultMark(int defaultMark) {
    this.defaultMark = defaultMark;
    return this;
  }

  public OnlineTask withNumberOfSubmissionAttempts(int numberOfSubmissionAttempts) {
    this.numberOfSubmissionAttempts = numberOfSubmissionAttempts;
    return this;
  }

  public OnlineTask withIncludeDismissedStudentsInCalculations(
      boolean includeDismissedStudentsInCalculations) {
    this.includeDismissedStudentsInCalculations = includeDismissedStudentsInCalculations;
    return this;
  }

  public OnlineTask withNotifyStudentAfterAssign(boolean notifyStudentAfterAssign) {
    this.notifyStudentAfterAssign = notifyStudentAfterAssign;
    return this;
  }

  public OnlineTask withNotifyStudentBeforeDeadline(boolean notifyStudentBeforeDeadline) {
    this.notifyStudentBeforeDeadline = notifyStudentBeforeDeadline;
    return this;
  }

  public OnlineTask withStudentNotificationTimeBeforeDeadline(
      Duration studentNotificationTimeBeforeDeadline) {
    this.studentNotificationTimeBeforeDeadline = studentNotificationTimeBeforeDeadline;
    return this;
  }

  public OnlineTask withAttachedLinks(List<Link> attachedLinks) {
    this.attachedLinks = attachedLinks;
    return this;
  }

  public OnlineTask withAttachedFileIds(List<String> attachedFileIds) {
    this.attachedFileIds = attachedFileIds;
    return this;
  }

  public OnlineTask withVariants(List<UpdateVariant> variants) {
    this.variants = variants;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getSpecificWeight() {
    return specificWeight;
  }

  public List<Integer> getGroupIds() {
    return groupIds;
  }

  public int getHeadTrainerId() {
    return headTrainerId;
  }

  public List<Integer> getAdditionalTrainerIds() {
    return additionalTrainerIds;
  }

  public String getStartDateTime() {
    return startDateTime;
  }

  public String getDeadlineDateTime() {
    return deadlineDateTime;
  }

  public int getMaxMark() {
    return maxMark;
  }

  public boolean isAllowFractionalMark() {
    return allowFractionalMark;
  }

  public int getPassMark() {
    return passMark;
  }

  public int getDefaultMark() {
    return defaultMark;
  }

  public int getNumberOfSubmissionAttempts() {
    return numberOfSubmissionAttempts;
  }

  public boolean isIncludeDismissedStudentsInCalculations() {
    return includeDismissedStudentsInCalculations;
  }

  public boolean isNotifyStudentAfterAssign() {
    return notifyStudentAfterAssign;
  }

  public boolean isNotifyStudentBeforeDeadline() {
    return notifyStudentBeforeDeadline;
  }

  public Duration getStudentNotificationTimeBeforeDeadline() {
    return studentNotificationTimeBeforeDeadline;
  }

  public List<Link> getAttachedLinks() {
    return attachedLinks;
  }

  public List<String> getAttachedFileIds() {
    return attachedFileIds;
  }

  public List<UpdateVariant> getVariants() {
    return variants;
  }
}
