package com.epmrdpt.bo.events;

import java.util.Objects;

public class EventSpeaker {

  private String name;
  private String jobTitle;
  private String description;

  public EventSpeaker(String name, String jobTitle, String description) {
    this.name = name;
    this.jobTitle = jobTitle;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
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
    EventSpeaker that = (EventSpeaker) o;
    return Objects.equals(name, that.name) && Objects.equals(jobTitle,
        that.jobTitle) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, jobTitle, description);
  }

  @Override
  public String toString() {
    return "EventSpeaker{" +
        "name='" + name + '\'' +
        ", jobTitle='" + jobTitle + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
