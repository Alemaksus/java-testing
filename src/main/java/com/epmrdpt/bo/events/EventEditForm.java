package com.epmrdpt.bo.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class EventEditForm {

  @JsonProperty("Trainers")
  private List<EventTrainer> trainers;
  @JsonProperty("Locations")
  private List<String> locations;

  public EventEditForm() {}

  public EventEditForm(List<EventTrainer> trainers, List<String> locations) {
    this.trainers = trainers;
    this.locations = locations;
  }

  public List<EventTrainer> getTrainers() {
    return trainers;
  }

  public void setTrainers(List<EventTrainer> trainers) {
    this.trainers = trainers;
  }

  public List<String> getLocations() {
    return locations;
  }

  public void setLocations(List<String> locations) {
    this.locations = locations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EventEditForm)) {
      return false;
    }
    EventEditForm that = (EventEditForm) o;
    return Objects.equals(trainers, that.trainers) && Objects.equals(locations,
        that.locations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trainers, locations);
  }
}
