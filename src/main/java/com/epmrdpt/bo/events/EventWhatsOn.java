package com.epmrdpt.bo.events;

import java.util.Objects;

public class EventWhatsOn {

  private String iconName;
  private String description;

  public EventWhatsOn(String iconName, String description) {
    this.iconName = iconName;
    this.description = description;
  }

  public String getIconName() {
    return iconName;
  }

  public void setIconName(String iconName) {
    this.iconName = iconName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventWhatsOn that = (EventWhatsOn) o;
    return Objects.equals(iconName, that.iconName) && Objects.equals(description,
        that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iconName, description);
  }

  @Override
  public String toString() {
    return "EventWhatsOn{" +
        "iconName='" + iconName + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
