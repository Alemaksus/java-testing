package com.epmrdpt.bo;

import java.time.LocalDate;

public class NewsItem {

  private String category;
  private String status;
  private LocalDate dateOfPublication;
  private String titleInEnglish;
  private String introductionInEnglish;
  private String descriptionInEnglish;
  private String titleInRussian;
  private String introductionInRussian;
  private String descriptionInRussian;
  private String titleInUkrainian;
  private String introductionInUkrainian;
  private String descriptionInUkrainian;
  private String linkToVideo;

  public void setCategory(String category) {
    this.category = category;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setDateOfPublication(LocalDate dateOfPublication) {
    this.dateOfPublication = dateOfPublication;
  }

  public void setTitleInEnglish(String titleInEnglish) {
    this.titleInEnglish = titleInEnglish;
  }

  public void setIntroductionInEnglish(String introductionInEnglish) {
    this.introductionInEnglish = introductionInEnglish;
  }

  public void setDescriptionInEnglish(String descriptionInEnglish) {
    this.descriptionInEnglish = descriptionInEnglish;
  }

  public void setTitleInRussian(String titleInRussian) {
    this.titleInRussian = titleInRussian;
  }

  public void setIntroductionInRussian(String introductionInRussian) {
    this.introductionInRussian = introductionInRussian;
  }

  public void setDescriptionInRussian(String descriptionInRussian) {
    this.descriptionInRussian = descriptionInRussian;
  }

  public void setTitleInUkrainian(String titleInUkrainian) {
    this.titleInUkrainian = titleInUkrainian;
  }

  public void setIntroductionInUkrainian(String introductionInUkrainian) {
    this.introductionInUkrainian = introductionInUkrainian;
  }

  public void setDescriptionInUkrainian(String descriptionInUkrainian) {
    this.descriptionInUkrainian = descriptionInUkrainian;
  }

  public void setLinkToVideo(String linkToVideo) {
    this.linkToVideo = linkToVideo;
  }

  public void setAllNewsItemData(String category,
      String status,
      LocalDate dateOfPublication,
      String titleInEnglish,
      String introductionInEnglish,
      String descriptionInEnglish,
      String titleInRussian,
      String introductionInRussian,
      String descriptionInRussian,
      String titleInUkrainian,
      String introductionInUkrainian,
      String descriptionInUkrainian) {
    this.category = category;
    this.status = status;
    this.dateOfPublication = dateOfPublication;
    this.titleInEnglish = titleInEnglish;
    this.introductionInEnglish = introductionInEnglish;
    this.descriptionInEnglish = descriptionInEnglish;
    this.titleInRussian = titleInRussian;
    this.introductionInRussian = introductionInRussian;
    this.descriptionInRussian = descriptionInRussian;
    this.titleInUkrainian = titleInUkrainian;
    this.introductionInUkrainian = introductionInUkrainian;
    this.descriptionInUkrainian = descriptionInUkrainian;
  }

  public String getCategory() {
    return category;
  }

  public String getStatus() {
    return status;
  }

  public LocalDate getDateOfPublication() {
    return dateOfPublication;
  }

  public String getTitleInEnglish() {
    return titleInEnglish;
  }

  public String getIntroductionInEnglish() {
    return introductionInEnglish;
  }

  public String getDescriptionInEnglish() {
    return descriptionInEnglish;
  }

  public String getTitleInRussian() {
    return titleInRussian;
  }

  public String getIntroductionInRussian() {
    return introductionInRussian;
  }

  public String getDescriptionInRussian() {
    return descriptionInRussian;
  }

  public String getTitleInUkrainian() {
    return titleInUkrainian;
  }

  public String getIntroductionInUkrainian() {
    return introductionInUkrainian;
  }

  public String getDescriptionInUkrainian() {
    return descriptionInUkrainian;
  }

  public String getLinkToVideo() {
    return linkToVideo;
  }
}
