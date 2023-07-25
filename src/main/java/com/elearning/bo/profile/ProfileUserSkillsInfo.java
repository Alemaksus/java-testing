package com.epmrdpt.bo.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class ProfileUserSkillsInfo {

  @JsonProperty("SkillId")
  private int skillId;

  @JsonProperty("SkillName")
  private String skillName;

  @JsonProperty("SkillLevel")
  private int skillLevel;

  public int getSkillId() {
    return skillId;
  }

  public void setSkillId(int skillId) {
    this.skillId = skillId;
  }

  public String getSkillName() {
    return skillName;
  }

  public void setSkillName(String skillName) {
    this.skillName = skillName;
  }

  public int getSkillLevel(int skillLevel) {
    return skillLevel;
  }

  public void setSkillLevel(int skillLevel) {
    this.skillLevel = skillLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileUserSkillsInfo profileUserSkillsInfo = (ProfileUserSkillsInfo) o;
    return skillId == profileUserSkillsInfo.skillId
        && skillLevel == profileUserSkillsInfo.skillLevel
        && Objects.equals(skillName, profileUserSkillsInfo.skillName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skillId, skillName, skillLevel);
  }
}
