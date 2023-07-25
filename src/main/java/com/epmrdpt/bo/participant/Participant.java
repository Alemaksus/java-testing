package com.epmrdpt.bo.participant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Participant {

  @JsonProperty("Email")
  private String email;
  @JsonProperty("Phone")
  private String phone;
  @JsonProperty("Id")
  private int id;
  @JsonProperty("DisplayName")
  private String displayName;
  @JsonProperty("HasAvatar")
  private Boolean hasAvatar;
}
