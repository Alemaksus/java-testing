package com.epmrdpt.bo.training_center;

public class TrainingCenter {

  private String name;
  private String country;
  private String city;
  private String skill;
  private String description;
  private String imgBase64;
  private String factDescription;
  private int factNumber;
  private String universityName;
  private String universityShortName;
  private String universityDescription;
  private String feedbackAuthorsName;
  private String feedbackJobFunctional;
  private String feedbackDescription;

  public TrainingCenter() {
  }

  public TrainingCenter withName(String name) {
    this.name = name;
    return this;
  }

  public TrainingCenter withCountry(String country) {
    this.country = country;
    return this;
  }

  public TrainingCenter withCity(String city) {
    this.city = city;
    return this;
  }

  public TrainingCenter withSkill(String skill) {
    this.skill = skill;
    return this;
  }

  public TrainingCenter withDescription(String description) {
    this.description = description;
    return this;
  }

  public TrainingCenter withImgBase64(String imgBase64) {
    this.imgBase64 = imgBase64;
    return this;
  }

  public TrainingCenter withFactDescription(String factDescription) {
    this.factDescription = factDescription;
    return this;
  }

  public TrainingCenter withFactNumber(int factNumber) {
    this.factNumber = factNumber;
    return this;
  }

  public TrainingCenter withUniversityName(String universityName) {
    this.universityName = universityName;
    return this;
  }

  public TrainingCenter withUniversityShortName(String universityShortName) {
    this.universityShortName = universityShortName;
    return this;
  }

  public TrainingCenter withUniversityDescription(String universityDescription) {
    this.universityDescription = universityDescription;
    return this;
  }

  public TrainingCenter withFeedbackAuthorsName(String feedbackAuthorsName) {
    this.feedbackAuthorsName = feedbackAuthorsName;
    return this;
  }

  public TrainingCenter withFeedbackJobFunctional(String feedbackJobFunctional) {
    this.feedbackJobFunctional = feedbackJobFunctional;
    return this;
  }

  public TrainingCenter withFeedbackDescription(String feedbackDescription) {
    this.feedbackDescription = feedbackDescription;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getSkill() {
    return skill;
  }

  public String getDescription() {
    return description;
  }

  public String getImgBase64() {
    return imgBase64;
  }

  public String getFactDescription() {
    return factDescription;
  }

  public int getFactNumber() {
    return factNumber;
  }

  public String getUniversityName() {
    return universityName;
  }

  public String getUniversityShortName() {
    return universityShortName;
  }

  public String getUniversityDescription() {
    return universityDescription;
  }

  public String getFeedbackAuthorsName() {
    return feedbackAuthorsName;
  }

  public String getFeedbackJobFunctional() {
    return feedbackJobFunctional;
  }

  public String getFeedbackDescription() {
    return feedbackDescription;
  }

  @Override
  public String toString() {
    return "TrainingCenter{" +
        "name='" + name + '\'' +
        ", country='" + country + '\'' +
        ", city='" + city + '\'' +
        ", skill='" + skill + '\'' +
        ", description='" + description + '\'' +
        ", imgBase64='" + imgBase64 + '\'' +
        ", factDescription='" + factDescription + '\'' +
        ", factNumber=" + factNumber +
        ", universityName='" + universityName + '\'' +
        ", universityShortName='" + universityShortName + '\'' +
        ", universityDescription='" + universityDescription + '\'' +
        ", feedbackAuthorsName='" + feedbackAuthorsName + '\'' +
        ", feedbackJobFunctional='" + feedbackJobFunctional + '\'' +
        ", feedbackDescription='" + feedbackDescription + '\'' +
        '}';
  }
}
