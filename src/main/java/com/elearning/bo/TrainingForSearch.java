package com.epmrdpt.bo;

public class TrainingForSearch {
  private String countryName;
  private String cityName;
  private String duration;
  private String type;
  private String status;
  private String format;
  private String language;
  private String skill;
  private String price;
  private String level;

  public TrainingForSearch(String countryName, String cityName, String duration,
      String type, String status, String format) {
    this.countryName = countryName;
    this.cityName = cityName;
    this.duration = duration;
    this.type = type;
    this.status = status;
    this.format = format;
  }

  public TrainingForSearch(String countryName, String cityName,
      String type, String format, String language, String skill, String price, String level) {
    this.countryName = countryName;
    this.cityName = cityName;
    this.type = type;
    this.format = format;
    this.language = language;
    this.skill = skill;
    this.price = price;
    this.level = level;
  }

  public String getCountryName() {
    return countryName;
  }

  public String getCityName() {
    return cityName;
  }

  public String getDuration() {
    return duration;
  }

  public String getType() {
    return type;
  }

  public String getStatus() {
    return status;
  }

  public String getFormat() {
    return format;
  }

  public String getLanguage() {
    return language;
  }

  public String getSkill() {
    return skill;
  }

  public String getPrice() {
    return price;
  }

  public String getLevel() {
    return level;
  }
}
