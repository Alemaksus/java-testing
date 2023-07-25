package com.epmrdpt.bo.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class EventType {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;

  public EventType() {
  }

  public EventType(int id, String name) {
    this.id = id;
    this.name = name;
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventType eventType = (EventType) o;
    return id == eventType.id && Objects.equals(name, eventType.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "EventType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
