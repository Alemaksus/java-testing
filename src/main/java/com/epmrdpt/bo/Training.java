package com.epmrdpt.bo;

import java.util.List;
import java.util.Objects;

public class Training {

  private String name;
  private String displayedName;
  private String skill;
  private List<String> additionsSkill;
  private String trainingType;
  private String targetAudience;
  private String minimalEnglishLevel;
  private String automaticReplay;
  private String trainingDetail;
  private String requiredSkills;
  private String trainingProgram;
  private String expectedFrequency;
  private String format;
  private String enrollmentType;
  private String pricing;
  private String programLevel;
  private List<String> programLanguage;
  private List<String>  supervisors;
  private String comment;
  private String metaTagTitle;
  private String metaTagDescription;
  private String metaTagKeyword;
  private String whatIfDoNotMeetRequirementsField;
  private String chooseThisTrainingIfYouField;
  private String howToJoinField;
  private String expectedAudience;
  private String linkToVideoField;
  private boolean technicalRequirements;
  private boolean simplifiedRegistration;
  private boolean useSignature;
  private boolean useDefaultSignature;
  private boolean resultCheckingUseAdditionalSkillCheckBox;
  private boolean resultCheckingShowAdditionalSkillsDescriptionCheckBox;
  private boolean resultCheckingShowSkillsDescriptionCheckBox;
  private boolean resultCheckingUseSkillsDescriptionCheckBox;
  private boolean resultCheckingUseEnglishTestResultCheckBox;
  private boolean resultCheckingAutomaticReplyCheckBox;
  private String price;
  private String tax;
  private String currency;
  private String linkToExternalTraining;
  private String eLearnIntegration;
  private String paidConsultationsField;
  private String recommendedTrainingField;
  private List<String> recommendedTrainingDDLOptions;

  public Training withTrainingType(String trainingType){
    this.trainingType = trainingType;
    return this;
  }

  public Training withSkill(String skill){
    this.skill = skill;
    return this;
  }

  public Training withSkillDisplayToggle(boolean resultCheckingUseSkillsDescriptionCheckBox){
    this.resultCheckingUseSkillsDescriptionCheckBox = resultCheckingUseSkillsDescriptionCheckBox;
    return this;
  }

  public Training withAdditionsSkill(List<String> additionsSkill){
    this.additionsSkill = additionsSkill;
    return this;
  }

  public Training withAdditionsSkillDisplayToggle(
      boolean resultCheckingUseAdditionalSkillCheckBox) {
    this.resultCheckingUseAdditionalSkillCheckBox =
        resultCheckingUseAdditionalSkillCheckBox;
    return this;
  }

  public Training withFormat(String format){
    this.format = format;
    return this;
  }

  public Training withEnrollmentType(String enrollmentType){
    this.enrollmentType = enrollmentType;
    return this;
  }

  public Training withPricing(String pricing){
    this.pricing = pricing;
    return this;
  }

  public Training withPrice(String price){
    this.price = price;
    return this;
  }

  public Training withTax(String tax){
    this.tax = tax;
    return this;
  }

  public Training withCurrency(String currency){
    this.currency = currency;
    return this;
  }

  public Training withProgramLevel(String programLevel){
    this.programLevel = programLevel;
    return this;
  }

  public Training withProgramLanguage(List<String> programLanguage){
    this.programLanguage = programLanguage;
    return this;
  }

  public Training withName(String name){
    this.name = name;
    return this;
  }

  public Training withDisplayedName(String displayedName){
    this.displayedName = displayedName;
    return this;
  }

  public Training withTargetAudience(String targetAudience){
    this.targetAudience = targetAudience;
    return this;
  }

  public Training withSupervisors(List<String>  supervisors){
    this.supervisors = supervisors;
    return this;
  }

  public Training withLinkToExternalTraining(String linkToExternalTraining){
    this.linkToExternalTraining = linkToExternalTraining;
    return this;
  }

  public Training withMetaTagTitle(String metaTagTitle){
    this.metaTagTitle = metaTagTitle;
    return this;
  }

  public Training withMetaTagDescription(String metaTagDescription){
    this.metaTagDescription = metaTagDescription;
    return this;
  }

  public Training withMetaTagKeyword(String metaTagKeyword){
    this.metaTagKeyword = metaTagKeyword;
    return this;
  }

  public Training withExpectedFrequency(String expectedFrequency){
    this.expectedFrequency = expectedFrequency;
    return this;
  }

  public Training withUseEnglishTestResultCheckBox(boolean resultCheckingUseEnglishTestResultCheckBox){
    this.resultCheckingUseEnglishTestResultCheckBox = resultCheckingUseEnglishTestResultCheckBox;
    return this;
  }

  public Training withMinimalEnglishLevel(String minimalEnglishLevel){
    this.minimalEnglishLevel = minimalEnglishLevel;
    return this;
  }

  public Training withELearnIntegration(String eLearnIntegration){
    this.eLearnIntegration = eLearnIntegration;
    return this;
  }

  public Training withTrainingDetail(String trainingDetail){
    this.trainingDetail = trainingDetail;
    return this;
  }

  public Training withRequiredSkills(String requiredSkills){
    this.requiredSkills = requiredSkills;
    return this;
  }

  public Training withWhatIfDoNotMeetRequirementsField(String whatIfDoNotMeetRequirementsField){
    this.whatIfDoNotMeetRequirementsField = whatIfDoNotMeetRequirementsField;
    return this;
  }

  public Training withChooseThisTrainingIfYouField(String chooseThisTrainingIfYouField){
    this.chooseThisTrainingIfYouField = chooseThisTrainingIfYouField;
    return this;
  }

  public Training withTrainingProgram(String trainingProgram){
    this.trainingProgram = trainingProgram;
    return this;
  }

  public Training withHowToJoinField(String howToJoinField){
    this.howToJoinField = howToJoinField;
    return this;
  }

  public Training withPaidConsultationsField(String paidConsultationsField){
    this.paidConsultationsField = paidConsultationsField;
    return this;
  }

  public Training withLinkToVideoField(String linkToVideoField){
    this.linkToVideoField = linkToVideoField;
    return this;
  }

  public Training withRecommendedTrainingField(String recommendedTrainingField){
    this.recommendedTrainingField = recommendedTrainingField;
    return this;
  }

  public Training withRecommendedTrainingDDLOptions(List<String> recommendedTrainingDDLOptions){
    this.recommendedTrainingDDLOptions = recommendedTrainingDDLOptions;
    return this;
  }

  public String getTrainingType() {
    return trainingType;
  }

  public void setSkill(String Skill) {
    this.skill = Skill;
  }

  public void setAdditionsSkill(List<String> AdditionsSkill) {
    this.additionsSkill = AdditionsSkill;
  }

  public void setName(String Name) {
    this.name = Name;
  }

  public void setDisplayedName(String DisplayedName) {
    this.displayedName = DisplayedName;
  }

  public void setTrainingType(String TrainingType) {
    this.trainingType = TrainingType;
  }

  public void setTargetAudience(String TargetAudience) {
    this.targetAudience = TargetAudience;
  }

  public void setMinimalEnglishLevel(String MinimalEnglishLevel) {
    this.minimalEnglishLevel = MinimalEnglishLevel;
  }

  public void setAutomaticReplay(String AutomaticReplay) {
    this.automaticReplay = AutomaticReplay;
  }

  public void setTrainingDetail(String TrainingDetail) {
    this.trainingDetail = TrainingDetail;
  }

  public void setRequiredSkills(String RequiredSkills) {
    this.requiredSkills = RequiredSkills;
  }

  public void setTrainingProgram(String TrainingProgram) {
    this.trainingProgram = TrainingProgram;
  }

  public void setExpectedFrequency(String ExpectedFrequency) {
    this.expectedFrequency = ExpectedFrequency;
  }

  public void setFormat(String Format) {
    this.format = Format;
  }

  public void setResultCheckingUseAdditionalSkillCheckBox(
      boolean resultCheckingUseAdditionalSkillCheckBox) {
    this.resultCheckingUseAdditionalSkillCheckBox = resultCheckingUseAdditionalSkillCheckBox;
  }

  public void setResultCheckingShowAdditionalSkillsDescriptionCheckBox(
      boolean resultCheckingShowAdditionalSkillsDescriptionCheckBox) {
    this.resultCheckingShowAdditionalSkillsDescriptionCheckBox = resultCheckingShowAdditionalSkillsDescriptionCheckBox;
  }

  public void setResultCheckingShowSkillsDescriptionCheckBox(
      boolean resultCheckingShowSkillsDescriptionCheckBox) {
    this.resultCheckingShowSkillsDescriptionCheckBox = resultCheckingShowSkillsDescriptionCheckBox;
  }

  public void setResultCheckingUseEnglishTestResultCheckBox(
      boolean resultCheckingUseEnglishTestResultCheckBox) {
    this.resultCheckingUseEnglishTestResultCheckBox = resultCheckingUseEnglishTestResultCheckBox;
  }

  public void setResultCheckingAutomaticReplyCheckBox(
      boolean resultCheckingAutomaticReplyCheckBox) {
    this.resultCheckingAutomaticReplyCheckBox = resultCheckingAutomaticReplyCheckBox;
  }

  public void setEnrollmentType(String enrollmentType) {
    this.enrollmentType = enrollmentType;
  }

  public void setPricing(String pricing) {
    this.pricing = pricing;
  }

  public void setProgramLevel(String programLevel) {
    this.programLevel = programLevel;
  }

  public void setProgramLanguage(List<String>  programLanguage) {
    this.programLanguage = programLanguage;
  }

  public void setSupervisors(List<String>  supervisors) {
    this.supervisors = supervisors;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setMetaTagTitle(String megaTagTitle) {
    this.metaTagTitle = megaTagTitle;
  }

  public void setMetaTagDescription(String megaTagDescription) {
    this.metaTagDescription = megaTagDescription;
  }

  public void setMetaTagKeyword(String megaTagKeyword) {
    this.metaTagKeyword = megaTagKeyword;
  }

  public void setWhatIfDoNotMeetRequirementsField(String whatIfDoNotMeetRequirementsField) {
    this.whatIfDoNotMeetRequirementsField = whatIfDoNotMeetRequirementsField;
  }

  public void setChooseThisTrainingIfYouField(String chooseThisTrainingIfYouField) {
    this.chooseThisTrainingIfYouField = chooseThisTrainingIfYouField;
  }

  public void setHowToJoinField(String howToJoinField) {
    this.howToJoinField = howToJoinField;
  }

  public void setExpectedAudience(String expectedAudience) {
    this.expectedAudience = expectedAudience;
  }

  public void setLinkToVideoField(String linkToVideoField) {
    this.linkToVideoField = linkToVideoField;
  }

  public void setTechnicalRequirements(boolean technicalRequirements) {
    this.technicalRequirements = technicalRequirements;
  }

  public void setSimplifiedRegistration(boolean simplifiedRegistration) {
    this.simplifiedRegistration = simplifiedRegistration;
  }

  public void setUseSignature(boolean useSignature) {
    this.useSignature = useSignature;
  }

  public void setUseDefaultSignature(boolean useDefaultSignature) {
    this.useDefaultSignature = useDefaultSignature;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Training training = (Training) o;
    return technicalRequirements == training.technicalRequirements
        && simplifiedRegistration == training.simplifiedRegistration
        && useSignature == training.useSignature
        && useDefaultSignature == training.useDefaultSignature
        && resultCheckingUseAdditionalSkillCheckBox
        == training.resultCheckingUseAdditionalSkillCheckBox
        && resultCheckingShowAdditionalSkillsDescriptionCheckBox
        == training.resultCheckingShowAdditionalSkillsDescriptionCheckBox
        && resultCheckingShowSkillsDescriptionCheckBox
        == training.resultCheckingShowSkillsDescriptionCheckBox
        && resultCheckingUseSkillsDescriptionCheckBox
        == training.resultCheckingUseSkillsDescriptionCheckBox
        && resultCheckingUseEnglishTestResultCheckBox
        == training.resultCheckingUseEnglishTestResultCheckBox
        && resultCheckingAutomaticReplyCheckBox == training.resultCheckingAutomaticReplyCheckBox
        && Objects.equals(name, training.name) && Objects.equals(displayedName,
        training.displayedName) && Objects.equals(skill, training.skill)
        && Objects.equals(additionsSkill, training.additionsSkill)
        && Objects.equals(trainingType, training.trainingType) && Objects.equals(
        targetAudience, training.targetAudience) && Objects.equals(minimalEnglishLevel,
        training.minimalEnglishLevel) && Objects.equals(automaticReplay,
        training.automaticReplay) && Objects.equals(trainingDetail, training.trainingDetail)
        && Objects.equals(requiredSkills, training.requiredSkills)
        && Objects.equals(trainingProgram, training.trainingProgram)
        && Objects.equals(expectedFrequency, training.expectedFrequency)
        && Objects.equals(format, training.format) && Objects.equals(
        enrollmentType, training.enrollmentType) && Objects.equals(pricing,
        training.pricing) && Objects.equals(programLevel, training.programLevel)
        && Objects.equals(programLanguage, training.programLanguage)
        && Objects.equals(supervisors, training.supervisors) && Objects.equals(
        comment, training.comment) && Objects.equals(metaTagTitle, training.metaTagTitle)
        && Objects.equals(metaTagDescription, training.metaTagDescription)
        && Objects.equals(metaTagKeyword, training.metaTagKeyword)
        && Objects.equals(whatIfDoNotMeetRequirementsField,
        training.whatIfDoNotMeetRequirementsField) && Objects.equals(
        chooseThisTrainingIfYouField, training.chooseThisTrainingIfYouField)
        && Objects.equals(howToJoinField, training.howToJoinField)
        && Objects.equals(expectedAudience, training.expectedAudience)
        && Objects.equals(linkToVideoField, training.linkToVideoField)
        && Objects.equals(price, training.price) && Objects.equals(tax,
        training.tax) && Objects.equals(currency, training.currency)
        && Objects.equals(linkToExternalTraining, training.linkToExternalTraining)
        && Objects.equals(eLearnIntegration, training.eLearnIntegration)
        && Objects.equals(paidConsultationsField, training.paidConsultationsField)
        && Objects.equals(recommendedTrainingField, training.recommendedTrainingField)
        && Objects.equals(recommendedTrainingDDLOptions,
        training.recommendedTrainingDDLOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, displayedName, skill, additionsSkill, trainingType, targetAudience,
        minimalEnglishLevel, automaticReplay, trainingDetail, requiredSkills, trainingProgram,
        expectedFrequency, format, enrollmentType, pricing, programLevel, programLanguage,
        supervisors, comment, metaTagTitle, metaTagDescription, metaTagKeyword,
        whatIfDoNotMeetRequirementsField, chooseThisTrainingIfYouField, howToJoinField,
        expectedAudience, linkToVideoField, technicalRequirements, simplifiedRegistration,
        useSignature, useDefaultSignature, resultCheckingUseAdditionalSkillCheckBox,
        resultCheckingShowAdditionalSkillsDescriptionCheckBox,
        resultCheckingShowSkillsDescriptionCheckBox, resultCheckingUseSkillsDescriptionCheckBox,
        resultCheckingUseEnglishTestResultCheckBox, resultCheckingAutomaticReplyCheckBox, price,
        tax,
        currency, linkToExternalTraining, eLearnIntegration, paidConsultationsField,
        recommendedTrainingField, recommendedTrainingDDLOptions);
  }

  @Override
  public String toString() {
    return "Training{" +
        "name='" + name + '\'' +
        ", displayedName='" + displayedName + '\'' +
        ", skill='" + skill + '\'' +
        ", additionsSkill=" + additionsSkill +
        ", trainingType='" + trainingType + '\'' +
        ", targetAudience='" + targetAudience + '\'' +
        ", minimalEnglishLevel='" + minimalEnglishLevel + '\'' +
        ", automaticReplay='" + automaticReplay + '\'' +
        ", trainingDetail='" + trainingDetail + '\'' +
        ", requiredSkills='" + requiredSkills + '\'' +
        ", trainingProgram='" + trainingProgram + '\'' +
        ", expectedFrequency='" + expectedFrequency + '\'' +
        ", format='" + format + '\'' +
        ", enrollmentType='" + enrollmentType + '\'' +
        ", pricing='" + pricing + '\'' +
        ", programLevel='" + programLevel + '\'' +
        ", programLanguage=" + programLanguage +
        ", supervisors=" + supervisors +
        ", comment='" + comment + '\'' +
        ", metaTagTitle='" + metaTagTitle + '\'' +
        ", metaTagDescription='" + metaTagDescription + '\'' +
        ", metaTagKeyword='" + metaTagKeyword + '\'' +
        ", whatIfDoNotMeetRequirementsField='" + whatIfDoNotMeetRequirementsField + '\'' +
        ", chooseThisTrainingIfYouField='" + chooseThisTrainingIfYouField + '\'' +
        ", howToJoinField='" + howToJoinField + '\'' +
        ", expectedAudience='" + expectedAudience + '\'' +
        ", linkToVideoField='" + linkToVideoField + '\'' +
        ", technicalRequirements=" + technicalRequirements +
        ", simplifiedRegistration=" + simplifiedRegistration +
        ", useSignature=" + useSignature +
        ", useDefaultSignature=" + useDefaultSignature +
        ", resultCheckingUseAdditionalSkillCheckBox=" + resultCheckingUseAdditionalSkillCheckBox +
        ", resultCheckingShowAdditionalSkillsDescriptionCheckBox="
        + resultCheckingShowAdditionalSkillsDescriptionCheckBox +
        ", resultCheckingShowSkillsDescriptionCheckBox="
        + resultCheckingShowSkillsDescriptionCheckBox
        +
        ", resultCheckingUseSkillsDescriptionCheckBox=" + resultCheckingUseSkillsDescriptionCheckBox
        +
        ", resultCheckingUseEnglishTestResultCheckBox=" + resultCheckingUseEnglishTestResultCheckBox
        +
        ", resultCheckingAutomaticReplyCheckBox=" + resultCheckingAutomaticReplyCheckBox +
        ", price='" + price + '\'' +
        ", tax='" + tax + '\'' +
        ", currency='" + currency + '\'' +
        ", linkToExternalTraining='" + linkToExternalTraining + '\'' +
        ", eLearnIntegration='" + eLearnIntegration + '\'' +
        ", paidConsultationsField='" + paidConsultationsField + '\'' +
        ", recommendedTrainingField='" + recommendedTrainingField + '\'' +
        ", recommendedTrainingDDLOptions=" + recommendedTrainingDDLOptions +
        '}';
  }
}
