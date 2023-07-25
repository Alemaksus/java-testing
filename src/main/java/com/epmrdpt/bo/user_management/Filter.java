package com.epmrdpt.bo.user_management;

import com.epmrdpt.bo.Country;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

  @JsonProperty("Countries")
  private List<Country> countries;

  @JsonProperty("Roles")
  private List<Role> roles;

  @JsonProperty("AccountTypes")
  private List<String> accountTypes;

  public String getCountryNameById(int id) {
    return countries.stream().filter(country -> country.getId() == id).findFirst().get().getName();
  }

  public List<String> getRoleNamesList() {
    return roles.stream().map(role -> role.getName()).collect(Collectors.toList());
  }
}
