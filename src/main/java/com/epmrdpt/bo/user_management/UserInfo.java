package com.epmrdpt.bo.user_management;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserInfo {

  @JsonAlias({"Id", "UserId"})
  private int id;
  @JsonProperty("FirstName")
  private String firstName;
  @JsonProperty("LastName")
  private String lastName;
  @JsonProperty("NativeName")
  private String nativeName;
  @JsonProperty("Email")
  private String email;
  @JsonAlias({"PhoneNumber", "PhoneMobile"})
  private String phoneNumber;
  @JsonProperty("CountryName")
  private String countryName;
  @JsonProperty("CityName")
  private String cityName;
  @JsonProperty("PhotoId")
  private String photoId;
  @JsonProperty("PhoneHome")
  private String phoneHome;
  @JsonProperty("PhoneWork")
  private String phoneWork;
}
