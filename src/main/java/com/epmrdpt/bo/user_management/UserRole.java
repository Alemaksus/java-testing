package com.epmrdpt.bo.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class UserRole implements Comparable<UserRole> {

  @JsonProperty("RoleId")
  private Integer roleId;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("CountryId")
  private String countryId;
  @JsonProperty("CountryName")
  private String countryName;
  @JsonProperty("CityId")
  private String cityId;
  @JsonProperty("CityName")
  private String cityName;
  @JsonProperty("TrainerSkillIds")
  private List<Integer> trainerSkillIds;

  @Override
  public int compareTo(UserRole o) {
    return this.roleId - o.getRoleId();
  }
}
