package com.epmrdpt.bo.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class EventTrainer {

  @JsonProperty("DisplayName")
  private String displayName;
  @JsonProperty("HasAvatar")
  private boolean hasAvatar;
  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;

  public EventTrainer() {}

  public EventTrainer(String displayName, boolean hasAvatar, int id, String name) {
    this.displayName = displayName;
    this.hasAvatar = hasAvatar;
    this.id = id;
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public boolean isHasAvatar() {
    return hasAvatar;
  }

  public void setHasAvatar(boolean hasAvatar) {
    this.hasAvatar = hasAvatar;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EventTrainer)) {
      return false;
    }
    EventTrainer that = (EventTrainer) o;
    return hasAvatar == that.hasAvatar && id == that.id && Objects.equals(displayName,
        that.displayName) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, hasAvatar, id, name);
  }
}
