package com.epmrdpt.bo.skill_information;

import com.epmrdpt.bo.Fact;
import com.epmrdpt.bo.LanguageEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonInclude(Include.NON_NULL)
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class SkillInformation {

  @JsonProperty("Language")
  private LanguageEnum language;
  @JsonProperty("Title")
  private String title;
  @JsonProperty("Description")
  private String description;
  @JsonProperty("MetaTagTitle")
  private String metaTagTitle;
  @JsonProperty("MetaTagDescription")
  private String metaTagDescription;
  @JsonProperty("MetaTagKeywords")
  private String metaTagKeywords;
  @JsonProperty("VideoLink")
  private String videoLink;
  @JsonProperty("CoreInfo")
  private String coreInfo;
  @JsonProperty("AdditionalInfo")
  private String additionalInfo;
  @JsonProperty("KnowledgeInfo")
  private String knowledgeInfo;
  @JsonProperty("MaterialsInfo")
  private String materialsInfo;
  @JsonProperty("BannerText")
  private String bannerText;
  @JsonProperty("Facts")
  private List<Fact> facts = new ArrayList<>();
  @JsonProperty("DescriptionPicture")
  private String descriptionPicture;

  public SkillInformation addFactToList(Fact fact) {
    this.facts.add(fact);
    return this;
  }
}
