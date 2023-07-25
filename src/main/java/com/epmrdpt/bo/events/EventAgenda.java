package com.epmrdpt.bo.events;

import java.time.LocalDateTime;
import java.util.Objects;

public class EventAgenda {

  private String report;
  private String description;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public EventAgenda(String report, String description, LocalDateTime startTime) {
    this.report = report;
    this.description = description;
    this.startTime = startTime;
  }

  public EventAgenda withEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
    return this;
  }

  public String getReport() {
    return report;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventAgenda that = (EventAgenda) o;
    return Objects.equals(report, that.report) && Objects.equals(description,
        that.description) && Objects.equals(startTime, that.startTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(report, description, startTime);
  }

  @Override
  public String toString() {
    return "EventAgenda{" +
        "report='" + report + '\'' +
        ", description='" + description + '\'' +
        ", startTime=" + startTime +
        '}';
  }
}
