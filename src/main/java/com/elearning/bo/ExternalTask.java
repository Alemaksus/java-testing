package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ExternalTask {
  @JsonProperty("Id")
  private int id;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("Description")
  private String description;

  @JsonProperty("StartedAt")
  private LocalDateTime startedAt;

  @JsonProperty("Deadline")
  private LocalDateTime deadline;

  public ExternalTask() {
  }

  public ExternalTask(int id, String name, String description, LocalDateTime startedAt, LocalDateTime deadline) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.startedAt = startedAt;
    this.deadline = deadline;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public LocalDateTime getDeadline() {
    return deadline;
  }

  @Override
  public String toString() {
    return "ExternalTask{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", startedAt=" + startedAt +
        ", deadline=" + deadline +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExternalTask)) {
      return false;
    }
    ExternalTask externalTask = (ExternalTask) o;
    return getId() == externalTask.getId()
        && Objects.equals(getName(), externalTask.getName())
        && Objects.equals(getDescription(), externalTask.getDescription())
        && Objects.equals(getStartedAt(), externalTask.getStartedAt())
        && Objects.equals(getDeadline(), externalTask.getDeadline());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDescription(), getStartedAt(), getDeadline());
  }
}
