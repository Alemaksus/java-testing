package com.epmrdpt.bo.skill_picture;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillPicture {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("SkillImageType")
  private String skillImageType;
  @JsonProperty("LanguageType")
  private String languageType;
  @JsonProperty("Path")
  private String path;
}
