package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GroupJournal {

  @JsonProperty("TrainingId")
  private int trainingId;

  @JsonProperty("TrainingName")
  private String trainingName;

  @JsonProperty("GroupName")
  private String groupName;

  @JsonProperty("Trainers")
  private List<Trainer> trainers = new ArrayList<>();

  public GroupJournal() {
  }

  public GroupJournal(int trainingId, String trainingName, String groupName,
      List<Trainer> trainers) {
    this.trainingId = trainingId;
    this.trainingName = trainingName;
    this.groupName = groupName;
    this.trainers = trainers;
  }

  public int getTrainingId() {
    return trainingId;
  }

  public void setTrainingId(int trainingId) {
    this.trainingId = trainingId;
  }

  public String getTrainingName() {
    return trainingName;
  }

  public void setTrainingName(String trainingName) {
    this.trainingName = trainingName;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public List<Trainer> getTrainers() {
    return trainers;
  }

  public void setTrainers(List<Trainer> trainers) {
    this.trainers = trainers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupJournal that = (GroupJournal) o;
    return trainingId == that.trainingId && Objects.equals(trainingName, that.trainingName)
        && Objects.equals(groupName, that.groupName) && Objects.equals(trainers,
        that.trainers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trainingId, trainingName, groupName, trainers);
  }
}
