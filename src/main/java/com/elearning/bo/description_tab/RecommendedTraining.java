package com.epmrdpt.bo.description_tab;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RecommendedTraining {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("IsActive")
  private boolean isActive;
}
