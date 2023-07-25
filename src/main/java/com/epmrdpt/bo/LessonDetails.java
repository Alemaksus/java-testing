package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class LessonDetails {
  @JsonProperty("Id")
  public int id;
  @JsonProperty("SeriesTitle")
  public String seriesTitle;
  @JsonProperty("AdditionalFiles")
  public ArrayList<AdditionalFile> additionalFiles;
  @JsonProperty("AdditionalLinks")
  public ArrayList<AdditionalLink> additionalLinks;
  @JsonProperty("Description")
  public String description;
  @JsonProperty("Topic")
  public String topic;
  @JsonProperty("Location")
  public String location;
  @JsonProperty("Type")
  public String type;
  @JsonProperty("StartDateTime")
  public Date startDateTime;
  @JsonProperty("FinishDateTime")
  public Date finishDateTime;

  public LessonDetails() {
  }

  public LessonDetails withId(int id) {
    this.id = id;
    return this;
  }

  public LessonDetails withSeriesTitle(String seriesTitle) {
    this.seriesTitle = seriesTitle;
    return this;
  }

  public LessonDetails withAdditionalFiles(
      ArrayList<AdditionalFile> additionalFiles) {
    this.additionalFiles = additionalFiles;
    return this;
  }

  public LessonDetails withAdditionalLinks(
      ArrayList<AdditionalLink> additionalLinks) {
    this.additionalLinks = additionalLinks;
    return this;
  }

  public LessonDetails withDescription(String description) {
    this.description = description;
    return this;
  }

  public LessonDetails withTopic(String topic) {
    this.topic = topic;
    return this;
  }

  public LessonDetails withLocation(String location) {
    this.location = location;
    return this;
  }

  public LessonDetails withType(String type) {
    this.type = type;
    return this;
  }

  public LessonDetails withStartDateTime(Date startDateTime) {
    this.startDateTime = startDateTime;
    return this;
  }

  public LessonDetails withFinishDateTime(Date finishDateTime) {
    this.finishDateTime = finishDateTime;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getSeriesTitle() {
    return seriesTitle;
  }

  public ArrayList<AdditionalFile> getAdditionalFiles() {
    return additionalFiles;
  }

  public ArrayList<AdditionalLink> getAdditionalLinks() {
    return additionalLinks;
  }

  public String getDescription() {
    return description;
  }

  public String getTopic() {
    return topic;
  }

  public String getLocation() {
    return location;
  }

  public String getType() {
    return type;
  }

  public Date getStartDateTime() {
    return startDateTime;
  }

  public Date getFinishDateTime() {
    return finishDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LessonDetails)) {
      return false;
    }
    LessonDetails that = (LessonDetails) o;
    return getId() == that.getId() && Objects.equals(getSeriesTitle(),
        that.getSeriesTitle()) && Objects.equals(getAdditionalFiles(),
        that.getAdditionalFiles()) && Objects.equals(getAdditionalLinks(),
        that.getAdditionalLinks()) && Objects.equals(getDescription(),
        that.getDescription()) && Objects.equals(getTopic(), that.getTopic())
        && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(
        getType(), that.getType()) && Objects.equals(getStartDateTime(),
        that.getStartDateTime()) && Objects.equals(getFinishDateTime(),
        that.getFinishDateTime());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getSeriesTitle(), getAdditionalFiles(), getAdditionalLinks(),
        getDescription(), getTopic(), getLocation(), getType(), getStartDateTime(),
        getFinishDateTime());
  }

  @Override
  public String toString() {
    return "ActiveClass{" +
        "id=" + id +
        ", seriesTitle='" + seriesTitle + '\'' +
        ", additionalFiles=" + additionalFiles +
        ", additionalLinks=" + additionalLinks +
        ", description='" + description + '\'' +
        ", topic='" + topic + '\'' +
        ", location='" + location + '\'' +
        ", type='" + type + '\'' +
        ", startDateTime=" + startDateTime +
        ", finishDateTime=" + finishDateTime +
        '}';
  }
}
