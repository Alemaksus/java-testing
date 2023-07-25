package com.epmrdpt.bo.autoreply_tab;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoReplyTab {

  @JsonProperty("FirstLine")
  private String firstLine;
  @JsonProperty("SecondLine")
  private String secondLine;
  @JsonProperty("ThirdLine")
  private String thirdLine;
  @JsonProperty("Culture")
  private String culture;
}
