package com.epmrdpt.bo.group_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("StartDate")
  private String startDate;
  @JsonProperty("EndDate")
  private String endDate;
  @JsonProperty("CurrentGraduationCount")
  private int currentGraduationCount;
  @JsonProperty("MaxGraduationCount")
  private int maxGraduationCount;
  @JsonProperty("Status")
  private String status;
  @JsonProperty("HasParticipants")
  private boolean hasParticipants;
  @JsonProperty("HasTrainers")
  private boolean hasTrainers;
  @JsonProperty("IsEndless")
  private boolean isEndless;
}
