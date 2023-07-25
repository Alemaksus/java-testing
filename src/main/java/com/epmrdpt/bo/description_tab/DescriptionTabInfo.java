package com.epmrdpt.bo.description_tab;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DescriptionTabInfo {

  @JsonProperty("PlanId")
  private int planId;
  @JsonProperty("LogotypeId")
  private int logotypeId;
  @JsonProperty("RecommendedTrainings")
  private RecommendedTraining[] recommendedTrainings;
  @JsonProperty("LocalizedProperties")
  private LocalizedProperty[] localizedProperties;
  @JsonProperty("DescriptionBlocks")
  private DescriptionBlock[] descriptionBlocks;
}
