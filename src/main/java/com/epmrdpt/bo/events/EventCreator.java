package com.epmrdpt.bo.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCreator {

  @JsonProperty("Id")
  private int userId;
  @JsonProperty("DisplayName")
  private String displayName;
  @JsonProperty("HasAvatar")
  private boolean hasAvatar;
}
