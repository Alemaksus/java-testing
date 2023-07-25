package com.epmrdpt.bo.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventLocation {

  @JsonProperty("CityNames")
  private List<String> cityNames;
  @JsonProperty("CountryName")
  private String countryName;
}
