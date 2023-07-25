package com.epmrdpt.bo;

public class TrainingForCopy {
  private String name;
  private String skill;
  private String trainingType;
  private String format;
  private String enrollmentType;
  private String pricing;
  private String programLevel;
  private String programLanguage;
  private String country;
  private String city;
  private String trainingOwner;
  private String studentsCount;

  public TrainingForCopy withName(String name) {
    this.name = name;
    return this;
  }

  public TrainingForCopy withSkill(String skill) {
    this.skill = skill;
    return this;
  }

  public TrainingForCopy withType(String type) {
    this.trainingType = type;
    return this;
  }

  public TrainingForCopy withFormat(String format) {
    this.format = format;
    return this;
  }

  public TrainingForCopy withEnrollmentType(String enrollmentType) {
    this.enrollmentType = enrollmentType;
    return this;
  }

  public TrainingForCopy withPricing(String pricing) {
    this.pricing = pricing;
    return this;
  }

  public TrainingForCopy withProgramLanguage(String programLanguage) {
    this.programLanguage = programLanguage;
    return this;
  }

  public TrainingForCopy withProgramLevel(String programLevel) {
    this.programLevel = programLevel;
    return this;
  }

  public TrainingForCopy withCountry(String country) {
    this.country = country;
    return this;
  }

  public TrainingForCopy withCity(String city) {
    this.city = city;
    return this;
  }

  public TrainingForCopy withOwner(String owner) {
    this.trainingOwner = owner;
    return this;
  }

  public TrainingForCopy withStudentsCount(String studentsCount) {
    this.studentsCount = studentsCount;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getSkill() {
    return skill;
  }

  public String getTrainingType() {
    return trainingType;
  }

  public String getFormat() {
    return format;
  }

  public String getEnrollmentType() {
    return enrollmentType;
  }

  public String getPricing() {
    return pricing;
  }

  public String getProgramLevel() {
    return programLevel;
  }

  public String getProgramLanguage() {
    return programLanguage;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getTrainingOwner() {
    return trainingOwner;
  }

  public String getStudentsCount() {
    return studentsCount;
  }

  @Override
  public String toString() {
    return "TrainingForCopy{" +
        "name='" + name + '\'' +
        ", skill='" + skill + '\'' +
        ", trainingType='" + trainingType + '\'' +
        ", format='" + format + '\'' +
        ", enrollmentType='" + enrollmentType + '\'' +
        ", pricing='" + pricing + '\'' +
        ", programLevel='" + programLevel + '\'' +
        ", programLanguage='" + programLanguage + '\'' +
        ", country='" + country + '\'' +
        ", city='" + city + '\'' +
        ", trainingOwner='" + trainingOwner + '\'' +
        ", studentsCount='" + studentsCount + '\'' +
        '}';
  }
}
