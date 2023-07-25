package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantStatus {

  @JsonProperty("Id")
  private int statusId;

  @JsonProperty("StudentCount")
  private int studentCount;

  @JsonProperty("IsMain")
  private boolean main;

  @JsonProperty("OriginalName")
  private String originalName;

  @JsonProperty("LocalizedName")
  private String localizedName;

  @JsonProperty("WorkflowStatusId")
  private int workflowStatusId;

  @JsonProperty("HiringLinkStatusId")
  private int hiringLinkStatusId;

  @JsonProperty("HiringLinkStatusName")
  private String hiringLinkStatusName;
}
