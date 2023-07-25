package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonInclude(Include.NON_NULL)
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class Fact {

  @JsonProperty("Title")
  private String title;
  @JsonProperty("Description")
  private String description;
}
