package com.epmrdpt.bo.participant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParticipantDate {

  @JsonProperty("StudentId")
  private int studentId;
  @JsonProperty("JoinDateTime")
  private String joinDateTime;

  public ParticipantDate() {
  }

  public ParticipantDate(int studentId, String joinDateTime) {
    this.studentId = studentId;
    this.joinDateTime = joinDateTime;
  }

  public int getStudentId() {
    return studentId;
  }

  public String getJoinDateTime() {
    return joinDateTime;
  }
}
