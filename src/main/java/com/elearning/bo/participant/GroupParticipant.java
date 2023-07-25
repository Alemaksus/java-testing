package com.epmrdpt.bo.participant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupParticipant {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("StudentName")
  private String studentName;
  @JsonProperty("ManualAssignmentDateTime")
  private String manualAssignmentDateTime;
  @JsonProperty("ApplicantStatusId")
  private int applicantStatusId;
  @JsonProperty("WorkflowStatusId")
  private int workflowStatusId;
  @JsonProperty("StaffingDeskId")
  private String staffingDeskId;
  @JsonProperty("CandidateLink")
  private String candidateLink;
  @JsonProperty("StaffingDeskRejectionReasonsId")
  private int staffingDeskRejectionReasonsId;
  @JsonProperty("CancelRegistrationReason")
  private int cancelRegistrationReason;
}
