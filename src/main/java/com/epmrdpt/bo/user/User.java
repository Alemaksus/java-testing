package com.epmrdpt.bo.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String cityOfResidence;
  private List<String> cityOfStudy = new ArrayList<>();
  private List<String> educationalEstablishment = new ArrayList<>();
  private List<String> faculty = new ArrayList<>();
  private List<String> department = new ArrayList<>();
  private List<String> educationForm = new ArrayList<>();
  private List<String> degreeInformation = new ArrayList<>();
  private List<String> universityCourse = new ArrayList<>();
  private List<String> graduationYear = new ArrayList<>();
  private String englishLevel;
  private int phoneNumber;
  private String nativeName;
  private List<String> companyName = new ArrayList<>();
  private List<LocalDate> startDate = new ArrayList<>();
  private List<String> position = new ArrayList<>();
  private List<LocalDate> endDate = new ArrayList<>();
  private String username;
  private String password;
  private String email;
  private List<String> assignment = new ArrayList<>();
  private String socialNetwork;
  private String preferredMethodOfCommunication;
  private String additionalInformationFromEducationSectionForm;
  private String professionalActivitiesFromEducationSectionForm;
  private List<Boolean> tillNowCheckBox = new ArrayList<>();
  private List<String> additionalInformationFromWorkExperienceSectionForm = new ArrayList<>();

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User() {
  }

  public void setUserName(String userName) {
    this.username = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public void setCityOfResidence(String cityOfResidence) {
    this.cityOfResidence = cityOfResidence;
  }

  public String getCityOfResidence() {
    return cityOfResidence;
  }

  public void setEducationalEstablishment(String educationalEstablishment) {
    this.educationalEstablishment.add(educationalEstablishment);
  }

  public String getEducationalEstablishment(int index) {
    return educationalEstablishment.get(index);
  }

  public void setCityOfStudy(String cityOfStudy) {
    this.cityOfStudy.add(cityOfStudy);
  }

  public String getCityOfStudy(int index) {
    return cityOfStudy.get(index);
  }

  public void setFaculty(String faculty) {
    this.faculty.add(faculty);
  }

  public String getFaculty(int index) {
    return faculty.get(index);
  }

  public void setDepartment(String department) {
    this.department.add(department);
  }

  public String getDepartment(int index) {
    return department.get(index);
  }

  public void setEducationForm(String educationForm) {
    this.educationForm.add(educationForm);
  }

  public String getEducationForm(int index) {
    return educationForm.get(index);
  }

  public void setDegreeInformation(String degreeInformation) {
    this.degreeInformation.add(degreeInformation);
  }

  public String getDegreeInformation(int index) {
    return degreeInformation.get(index);
  }

  public void setUniversityCourseYear(String year) {
    this.universityCourse.add(year);
  }

  public String getUniversityCourseYear(int index) {
    return universityCourse.get(index);
  }

  public void setGraduationYear(String graduationYear) {
    this.graduationYear.add(graduationYear);
  }

  public String getGraduationYear(int index) {
    return graduationYear.get(index);
  }

  public void setEnglishLevel(String englishLevel) {
    this.englishLevel = englishLevel;
  }

  public String getEnglishLevel() {
    return englishLevel;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setAssignment(String assignment) {
    this.assignment.add(assignment);
  }

  public void setNativeName(String nativeName) {
    this.nativeName = nativeName;
  }

  public void setCompanyName(String companyName) {
    this.companyName.add(companyName);
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate.add(startDate);
  }

  public void setPosition(String position) {
    this.position.add(position);
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate.add(endDate);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getBirthDateAsString() {
    return birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public String getPhoneNumberAsString() {
    return String.valueOf(phoneNumber);
  }

  public String getNativeName() {
    return nativeName;
  }



  public String getCompanyName() {
    return getCompanyNameByIndex(0);
  }

  public String getCompanyNameByIndex(int companyIndex) {
    return companyName.get(companyIndex);
  }

  public LocalDate getStartDateByIndex(int startDateIndex) {
    return startDate.get(startDateIndex);
  }

  public LocalDate getStartDate() {
    return getStartDateByIndex(0);
  }

  public String getPositionByIndex(int positionIndex) {
    return position.get(positionIndex);
  }

  public String getPosition() {
    return getPositionByIndex(0);
  }

  public LocalDate getEndDateByIndex(int endDateIndex) {
    return endDate.get(endDateIndex);
  }

  public LocalDate getEndDate() {
    return getEndDateByIndex(0);
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public String getAssignmentByIndex(int assignmentIndex) {
    return assignment.get(assignmentIndex);
  }

  public String getAssignment() {
    return getAssignmentByIndex(0);
  }

  public String getSocialNetwork() {
    return socialNetwork;
  }

  public void setSocialNetwork(String socialNetwork) {
    this.socialNetwork = socialNetwork;
  }

  public String getPreferredMethodOfCommunication() {
    return preferredMethodOfCommunication;
  }

  public void setPreferredMethodOfCommunication(String preferredMethodOfCommunication) {
    this.preferredMethodOfCommunication = preferredMethodOfCommunication;
  }

  public String getAdditionalInformationFromEducationSectionForm() {
    return additionalInformationFromEducationSectionForm;
  }

  public void setAdditionalInformationFromEducationSectionForm(
      String additionalInformationFromEducationSectionForm) {
    this.additionalInformationFromEducationSectionForm = additionalInformationFromEducationSectionForm;
  }

  public String getProfessionalActivitiesFromEducationSectionForm() {
    return professionalActivitiesFromEducationSectionForm;
  }

  public void setProfessionalActivitiesFromEducationSectionForm(
      String professionalActivitiesFromEducationSectionForm) {
    this.professionalActivitiesFromEducationSectionForm = professionalActivitiesFromEducationSectionForm;
  }

  public String getAdditionalInformationFromWorkExperienceSectionFormByIndex(int additionalInformationIndex) {
    return additionalInformationFromWorkExperienceSectionForm.get(additionalInformationIndex);
  }

  public String getAdditionalInformationFromWorkExperienceSectionForm() {
    return getAdditionalInformationFromWorkExperienceSectionFormByIndex(0);
  }

  public void setAdditionalInformationFromWorkExperienceSectionForm(
      String additionalInformationFromWorkExperienceSectionForm) {
    this.additionalInformationFromWorkExperienceSectionForm
        .add(additionalInformationFromWorkExperienceSectionForm);
  }

  public boolean getTillNowCheckBoxByIndex(int tillNowIndex) {
    return tillNowCheckBox.get(tillNowIndex);
  }

  public boolean getTillNowCheckBox() {
    return getTillNowCheckBoxByIndex(0);
  }

  public void setTillNowCheckBox(boolean isTillNowCheckBox) {
    this.tillNowCheckBox.add(isTillNowCheckBox);
  }

  public int getCitiesOfStudyQuantity() {
    return cityOfStudy.size();
  }

  public int getCompanyNamesQuantity() {
    return companyName.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return phoneNumber == user.phoneNumber &&
        Objects.equals(firstName, user.firstName) &&
        Objects.equals(lastName, user.lastName) &&
        Objects.equals(birthDate, user.birthDate) &&
        Objects.equals(cityOfResidence, user.cityOfResidence) &&
        Objects.equals(cityOfStudy, user.cityOfStudy) &&
        Objects.equals(educationalEstablishment, user.educationalEstablishment) &&
        Objects.equals(faculty, user.faculty) &&
        Objects.equals(department, user.department) &&
        Objects.equals(educationForm, user.educationForm) &&
        Objects.equals(degreeInformation, user.degreeInformation) &&
        Objects.equals(universityCourse, user.universityCourse) &&
        Objects.equals(graduationYear, user.graduationYear) &&
        Objects.equals(englishLevel, user.englishLevel) &&
        Objects.equals(nativeName, user.nativeName) &&
        Objects.equals(companyName, user.companyName) &&
        Objects.equals(startDate, user.startDate) &&
        Objects.equals(position, user.position) &&
        Objects.equals(endDate, user.endDate) &&
        Objects.equals(assignment, user.assignment) &&
        Objects.equals(socialNetwork, user.socialNetwork) &&
        Objects.equals(preferredMethodOfCommunication, user.preferredMethodOfCommunication)
        &&
        Objects.equals(additionalInformationFromEducationSectionForm,
            user.additionalInformationFromEducationSectionForm) &&
        Objects.equals(professionalActivitiesFromEducationSectionForm,
            user.professionalActivitiesFromEducationSectionForm) &&
        Objects.equals(tillNowCheckBox, user.tillNowCheckBox) &&
        Objects.equals(additionalInformationFromWorkExperienceSectionForm,
            user.additionalInformationFromWorkExperienceSectionForm);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(firstName, lastName, birthDate, cityOfResidence, cityOfStudy,
            educationalEstablishment, faculty, department, educationForm, degreeInformation,
            universityCourse, graduationYear, englishLevel, phoneNumber, nativeName, companyName,
            startDate, position, endDate, assignment, socialNetwork, preferredMethodOfCommunication,
            additionalInformationFromEducationSectionForm,
            professionalActivitiesFromEducationSectionForm, tillNowCheckBox,
            additionalInformationFromWorkExperienceSectionForm);
  }

  @Override
  public String toString() {
    return "User{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", birthDate=" + birthDate +
        ", cityOfResidence='" + cityOfResidence + '\'' +
        ", cityOfStudy=" + cityOfStudy +
        ", educationalEstablishment=" + educationalEstablishment +
        ", faculty=" + faculty +
        ", department=" + department +
        ", educationForm=" + educationForm +
        ", degreeInformation=" + degreeInformation +
        ", universityCourse=" + universityCourse +
        ", graduationYear=" + graduationYear +
        ", englishLevel='" + englishLevel + '\'' +
        ", phoneNumber=" + phoneNumber +
        ", nativeName='" + nativeName + '\'' +
        ", companyName=" + companyName +
        ", startDate=" + startDate +
        ", position=" + position +
        ", endDate=" + endDate +
        ", assignment=" + assignment +
        ", socialNetwork='" + socialNetwork + '\'' +
        ", preferredMethodOfCommunication='" + preferredMethodOfCommunication + '\'' +
        ", additionalInformationFromEducationSectionForm='"
        + additionalInformationFromEducationSectionForm + '\'' +
        ", professionalActivitiesFromEducationSectionForm='"
        + professionalActivitiesFromEducationSectionForm + '\'' +
        ", tillNowCheckBox=" + tillNowCheckBox +
        ", additionalInformationFromWorkExperienceSectionForm="
        + additionalInformationFromWorkExperienceSectionForm +
        '}';
  }
}
