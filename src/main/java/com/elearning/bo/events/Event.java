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
public class Event {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Location")
  private List<EventLocation> location;
  @JsonProperty("StartDate")
  private String startDate;
  @JsonProperty("LanguageName")
  private String languageName;
  @JsonProperty("RegistrationCount")
  private int registrationCount;
  @JsonProperty("Status")
  private int status;
  @JsonProperty("Creator")
  private EventCreator creator;
}
