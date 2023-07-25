package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trainer {

  @JsonProperty("IsPhotoExist")
  private Boolean photo;

  @JsonProperty("Id")
  private int id;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("Email")
  private String email;

  @JsonProperty("Phone")
  private String phone;

  @JsonProperty("DisplayName")
  private String displayName;

  @JsonProperty("HasAvatar")
  private Boolean hasAvatar;

  public Trainer(Boolean photo, int id, String name) {
    this.photo = photo;
    this.id = id;
    this.name = name;
  }

  public Trainer(String email, String phone, int id, String displayName, Boolean hasAvatar) {
    this.email = email;
    this.phone = phone;
    this.id = id;
    this.displayName = displayName;
    this.hasAvatar = hasAvatar;
  }
}
