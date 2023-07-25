package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class CreatedBy {

  @JsonProperty("DisplayName")
  private String displayName;

  @JsonProperty("HasAvatar")
  private boolean hasAvatar;

  @JsonProperty("Id")
  private int userId;

  @JsonProperty("Name")
  private String name;

  public CreatedBy() {
  }

  public CreatedBy(String displayName, boolean hasAvatar, int id, String name) {
    this.displayName = displayName;
    this.hasAvatar = hasAvatar;
    this.userId = id;
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public boolean isHasAvatar() {
    return hasAvatar;
  }

  public int getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "CreatedBy{" +
        "displayName='" + displayName + '\'' +
        ", hasAvatar=" + hasAvatar +
        ", userId=" + userId +
        ", name='" + name + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatedBy createdBy = (CreatedBy) o;
    return hasAvatar == createdBy.hasAvatar && userId == createdBy.userId && displayName.equals(
        createdBy.displayName) && name.equals(createdBy.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, hasAvatar, userId, name);
  }
}
