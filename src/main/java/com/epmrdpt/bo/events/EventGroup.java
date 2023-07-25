package com.epmrdpt.bo.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class EventGroup {

  @JsonProperty("StartTrainingDate")
  private String startTrainingDate;
  @JsonProperty("FinishTrainingDate")
  private String finishTrainingDate;
  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;

  public EventGroup() {
  }

  public EventGroup(String startTrainingDate, String finishTrainingDate, int id,
      String name) {
    this.startTrainingDate = startTrainingDate;
    this.finishTrainingDate = finishTrainingDate;
    this.id = id;
    this.name = name;
  }

  public String getStartTrainingDate() {
    return startTrainingDate;
  }

  public void setStartTrainingDate(String startTrainingDate) {
    this.startTrainingDate = startTrainingDate;
  }

  public String getFinishTrainingDate() {
    return finishTrainingDate;
  }

  public void setFinishTrainingDate(String finishTrainingDate) {
    this.finishTrainingDate = finishTrainingDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventGroup that = (EventGroup) o;
    return id == that.id && Objects.equals(startTrainingDate, that.startTrainingDate)
        && Objects.equals(finishTrainingDate, that.finishTrainingDate)
        && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTrainingDate, finishTrainingDate, id, name);
  }

  @Override
  public String toString() {
    return "EventGroup{" +
        "startTrainingDate=" + startTrainingDate +
        ", finishTrainingDate=" + finishTrainingDate +
        ", id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
