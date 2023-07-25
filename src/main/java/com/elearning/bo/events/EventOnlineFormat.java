package com.epmrdpt.bo.events;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class EventOnlineFormat {

  private String name;
  private String registrationType;
  private String eventLanguage;
  private String linkToEvent;
  private String contactEmail;
  private LocalDate startData;
  private LocalDateTime startTime;
  private LocalDate endData;
  private LocalDateTime endTime;

  public EventOnlineFormat withName(String name) {
    this.name = name;
    return this;
  }

  public EventOnlineFormat withRegistrationType(String registrationType) {
    this.registrationType = registrationType;
    return this;
  }

  public EventOnlineFormat withEventLanguage(String eventLanguage) {
    this.eventLanguage = eventLanguage;
    return this;
  }

  public EventOnlineFormat withLinkToEvent(String linkToEvent) {
    this.linkToEvent = linkToEvent;
    return this;
  }

  public EventOnlineFormat withContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
    return this;
  }

  public EventOnlineFormat withStartData(LocalDate startData) {
    this.startData = startData;
    return this;
  }

  public EventOnlineFormat withStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  public EventOnlineFormat withEndData(LocalDate endData) {
    this.endData = endData;
    return this;
  }

  public EventOnlineFormat withEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getRegistrationType() {
    return registrationType;
  }

  public String getEventLanguage() {
    return eventLanguage;
  }

  public String getLinkToEvent() {
    return linkToEvent;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public LocalDate getStartData() {
    return startData;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public LocalDate getEndData() {
    return endData;
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
    EventOnlineFormat that = (EventOnlineFormat) o;
    return Objects.equals(name, that.name) && Objects.equals(registrationType,
        that.registrationType) && Objects.equals(eventLanguage, that.eventLanguage)
        && Objects.equals(linkToEvent, that.linkToEvent) && Objects.equals(
        contactEmail, that.contactEmail) && Objects.equals(startData, that.startData)
        && Objects.equals(startTime, that.startTime) && Objects.equals(endData,
        that.endData) && Objects.equals(endTime, that.endTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, registrationType, eventLanguage, linkToEvent, contactEmail, startData,
        startTime, endData, endTime);
  }

  @Override
  public String toString() {
    return "EventOnlineFormat{" +
        "name='" + name + '\'' +
        ", registrationType='" + registrationType + '\'' +
        ", eventLanguage='" + eventLanguage + '\'' +
        ", linkToEvent='" + linkToEvent + '\'' +
        ", contactEmail='" + contactEmail + '\'' +
        ", startData=" + startData +
        ", startTime=" + startTime +
        ", endData=" + endData +
        ", endTime=" + endTime +
        '}';
  }
}
