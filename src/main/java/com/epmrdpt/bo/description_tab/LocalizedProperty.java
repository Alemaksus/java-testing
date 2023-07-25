package com.epmrdpt.bo.description_tab;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocalizedProperty {

  @JsonProperty("Culture")
  private String culture;
  @JsonProperty("MetaTitle")
  private String metaTitle;
  @JsonProperty("MetaDescription")
  private String metaDescription;
  @JsonProperty("MetaKeywords")
  private String metaKeywords;
  @JsonProperty("ExpectedDuration")
  private String expectedDuration;
  @JsonProperty("ExpectedLocation")
  private String expectedLocation;
  @JsonProperty("ExpectedFrequency")
  private String expectedFrequency;
  @JsonProperty("ExpectedAudience")
  private String expectedAudience;
}
