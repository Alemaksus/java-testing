package com.epmrdpt.bo.skill_description;

import com.epmrdpt.bo.Image;
import com.epmrdpt.bo.LanguageEnum;
import com.epmrdpt.bo.skill_information.SkillInformation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class SkillDescription {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("StringId")
  private String stringId;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Status")
  private boolean status;
  @JsonProperty("Languages")
  private List<SkillInformation> skillInformations = new ArrayList<>();
  @JsonProperty("SkillCoverPicture")
  private String skillCoverPicture;
  @JsonProperty("ImageSharingId")
  private int imageSharingId;
  @JsonProperty("ImgForSharingPath")
  private String imgForSharingPath;
  @JsonProperty("LocalizedInfo")
  private SkillInformation localizedInfo;
  @JsonProperty("Banner")
  private Image banner;

  public SkillDescription addSkillInformationToList(SkillInformation skillInformation) {
    this.skillInformations.add(skillInformation);
    return this;
  }

  public SkillInformation getSkillInformationByLanguage(LanguageEnum languageEnum) {
    return skillInformations.stream()
        .filter(skillInformation -> skillInformation.getLanguage().equals(languageEnum))
        .findFirst().get();
  }
}
