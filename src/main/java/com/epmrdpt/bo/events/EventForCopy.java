package com.epmrdpt.bo.events;

import java.util.List;
import java.util.Objects;

public class EventForCopy {

  private String name;
  private String format;
  private List<String> countries;
  private List<String> cities;
  private String eventLanguage;
  private List<String> tags;
  private List<String> skills;
  private String contactEmail;
  private String metaTagTitle;
  private String metaTagDescription;
  private String metaTagKeywords;
  private String detailsBlockText;
  private String joinUsIfYouBlockText;
  private List<EventSpeaker> eventSpeakersList;
  private List<EventWhatsOn> eventWhatsOnList;

  public EventForCopy withName(String name) {
    this.name = name;
    return this;
  }

  public EventForCopy withFormat(String format) {
    this.format = format;
    return this;
  }

  public EventForCopy withCountries(List<String> countries) {
    this.countries = countries;
    return this;
  }

  public EventForCopy withCities(List<String> cities) {
    this.cities = cities;
    return this;
  }

  public EventForCopy withEventLanguage(String eventLanguage) {
    this.eventLanguage = eventLanguage;
    return this;
  }

  public EventForCopy withTags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public EventForCopy withSkills(List<String> skills) {
    this.skills = skills;
    return this;
  }

  public EventForCopy withContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
    return this;
  }

  public EventForCopy withMetaTagTitle(String metaTagTitle) {
    this.metaTagTitle = metaTagTitle;
    return this;
  }

  public EventForCopy withMetaTagDescription(String metaTagDescription) {
    this.metaTagDescription = metaTagDescription;
    return this;
  }

  public EventForCopy withMetaTagKeywords(String metaTagKeyword) {
    this.metaTagKeywords = metaTagKeyword;
    return this;
  }

  public EventForCopy withDetailsBlockText(String detailsBlockText) {
    this.detailsBlockText = detailsBlockText;
    return this;
  }

  public EventForCopy withJoinUsIfYouBlockText(String joinUsIfYouBlockText) {
    this.joinUsIfYouBlockText = joinUsIfYouBlockText;
    return this;
  }

  public EventForCopy withEventSpeakersList(List<EventSpeaker> eventSpeakersList) {
    this.eventSpeakersList = eventSpeakersList;
    return this;
  }

  public EventForCopy withEventWhatsOnList(List<EventWhatsOn> eventWhatsOnList) {
    this.eventWhatsOnList = eventWhatsOnList;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getFormat() {
    return format;
  }

  public List<String> getCountries() {
    return countries;
  }

  public List<String> getCities() {
    return cities;
  }

  public String getEventLanguage() {
    return eventLanguage;
  }

  public List<String> getTags() {
    return tags;
  }

  public List<String> getSkills() {
    return skills;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public String getMetaTagTitle() {
    return metaTagTitle;
  }

  public String getMetaTagDescription() {
    return metaTagDescription;
  }

  public String getMetaTagKeywords() {
    return metaTagKeywords;
  }

  public String getDetailsBlockText() {
    return detailsBlockText;
  }

  public String getJoinUsIfYouBlockText() {
    return joinUsIfYouBlockText;
  }

  public List<EventSpeaker> getEventSpeakersList() {
    return eventSpeakersList;
  }

  public List<EventWhatsOn> getEventWhatsOnList() {
    return eventWhatsOnList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventForCopy that = (EventForCopy) o;
    return Objects.equals(name, that.name) && Objects.equals(format, that.format)
        && Objects.equals(countries, that.countries) && Objects.equals(cities,
        that.cities) && Objects.equals(eventLanguage, that.eventLanguage)
        && Objects.equals(tags, that.tags) && Objects.equals(skills, that.skills)
        && Objects.equals(contactEmail, that.contactEmail) && Objects.equals(
        metaTagTitle, that.metaTagTitle) && Objects.equals(metaTagDescription,
        that.metaTagDescription) && Objects.equals(metaTagKeywords, that.metaTagKeywords)
        && Objects.equals(detailsBlockText, that.detailsBlockText)
        && Objects.equals(joinUsIfYouBlockText, that.joinUsIfYouBlockText)
        && Objects.equals(eventSpeakersList, that.eventSpeakersList)
        && Objects.equals(eventWhatsOnList, that.eventWhatsOnList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, format, countries, cities, eventLanguage, tags, skills, contactEmail,
        metaTagTitle, metaTagDescription, metaTagKeywords, detailsBlockText, joinUsIfYouBlockText,
        eventSpeakersList, eventWhatsOnList);
  }

  @Override
  public String toString() {
    return "EventForCopy{" +
        "name='" + name + '\'' +
        ", format='" + format + '\'' +
        ", countries=" + countries +
        ", cities=" + cities +
        ", eventLanguage='" + eventLanguage + '\'' +
        ", tags=" + tags +
        ", skills=" + skills +
        ", contactEmail='" + contactEmail + '\'' +
        ", metaTagTitle='" + metaTagTitle + '\'' +
        ", metaTagDescription='" + metaTagDescription + '\'' +
        ", metaTagKeywords='" + metaTagKeywords + '\'' +
        ", detailsBlockText='" + detailsBlockText + '\'' +
        ", joinUsIfYouBlockText='" + joinUsIfYouBlockText + '\'' +
        ", eventSpeakersList=" + eventSpeakersList +
        ", eventWhatsOnList=" + eventWhatsOnList +
        '}';
  }
}
