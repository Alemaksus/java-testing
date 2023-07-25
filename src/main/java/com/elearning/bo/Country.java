package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {

  @JsonProperty("Id")
  private Integer id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Cities")
  private List<City> cities;

  @AllArgsConstructor
  @Getter
  public enum CountryEnum {
    ARMENIA(4, "Armenia"),
    RUSSIA(9, "Russia"),
    KAZAKHSTAN(11, "Kazakhstan"),
    INDIA(35, "India"),
    UZBEKISTAN(55, "Uzbekistan"),
    POLAND(56, "Poland"),
    HUNGARY(57, "Hungary"),
    LITHUANIA(58, "Lithuania"),
    GEORGIA(59, "Georgia"),
    SPAIN(62, "Spain"),
    COLOMBIA(68, "Colombia");

    int countryId;
    String countryName;
  }

  public static String getCountryNameById(int id) {
    return Arrays.stream(CountryEnum.values()).filter(e -> e.getCountryId() == id).findFirst().get()
        .getCountryName();
  }
}
