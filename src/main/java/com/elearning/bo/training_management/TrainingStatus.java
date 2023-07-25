package com.epmrdpt.bo.training_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class TrainingStatus {

  @JsonProperty("Status")
  private String status;

  @JsonProperty("State")
  private String state;

  @JsonProperty("IsAvailable")
  private boolean isAvailable;

  @AllArgsConstructor
  @Getter
  public enum TrainingStatuses {
    DRAFT("Draft"),
    PLANNED("Planned"),
    REGISTRATION_OPEN("RegistrationOpen"),
    REGISTRATION_CLOSED("RegistrationClosed"),
    FORM_GROUP("FormGroup"),
    STARTED("Started"),
    FINISHED("Finished"),
    CANCELED("Canceled");

    private String trainingStatus;
  }
}
