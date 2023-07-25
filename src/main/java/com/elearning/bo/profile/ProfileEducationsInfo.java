package com.epmrdpt.bo.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class ProfileEducationsInfo {

  @JsonProperty("Id")
  private int id;

  @JsonProperty("EducationCityId")
  private int educationCityId;

  @JsonProperty("EducationCityName")
  private String educationCityName;

  @JsonProperty("EducationCountryId")
  private int educationCountryId;

  @JsonProperty("EducationRegionName")
  private String educationRegionName;

  @JsonProperty("EducationEstablishment")
  private String educationEstablishment;

  @JsonProperty("EducationEstablishmentId")
  private int educationEstablishmentId;

  @JsonProperty("Faculty")
  private String faculty;

  @JsonProperty("FacultyId")
  private int facultyId;

  @JsonProperty("Department")
  private String department;

  @JsonProperty("DepartmentId")
  private int departmentId;

  @JsonProperty("EducationForm")
  private int educationForm;

  @JsonProperty("Degree")
  private int degree;

  @JsonProperty("EducationYear")
  private int educationYear;

  @JsonProperty("GraduationYear")
  private int graduationYear;

  @JsonProperty("AdmissionYear")
  private int admissionYear;

  @JsonProperty("Assignment")
  private int assignment;

  @JsonProperty("IsDistributionCompanyEpam")
  private boolean isDistributionCompanyEpam;

  @JsonProperty("DistributionCompany")
  private String distributionCompany;

  public void setId(int id) {
    this.id = id;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public void setDegree(int degree) {
    this.degree = degree;
  }

  public void setEducationYear(int educationYear) {
    this.educationYear = educationYear;
  }

  public void setGraduationYear(int graduationYear) {
    this.graduationYear = graduationYear;
  }

  public int getAssignment() {
    return assignment;
  }

  public void setAssignment(int assignment) {
    this.assignment = assignment;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileEducationsInfo that = (ProfileEducationsInfo) o;
    return id == that.id && educationCityId == that.educationCityId
        && educationCountryId == that.educationCountryId
        && educationEstablishmentId == that.educationEstablishmentId && facultyId == that.facultyId
        && departmentId == that.departmentId && educationForm == that.educationForm
        && degree == that.degree && educationYear == that.educationYear
        && graduationYear == that.graduationYear && admissionYear == that.admissionYear
        && assignment == that.assignment
        && isDistributionCompanyEpam == that.isDistributionCompanyEpam && Objects.equals(
        educationCityName, that.educationCityName) && Objects.equals(educationRegionName,
        that.educationRegionName) && Objects.equals(educationEstablishment,
        that.educationEstablishment) && Objects.equals(faculty, that.faculty)
        && Objects.equals(department, that.department) && Objects.equals(
        distributionCompany, that.distributionCompany);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, educationCityId, educationCityName, educationCountryId,
        educationRegionName, educationEstablishment, educationEstablishmentId, faculty, facultyId,
        department, departmentId, educationForm, degree, educationYear, graduationYear,
        admissionYear, assignment, isDistributionCompanyEpam, distributionCompany);
  }
}
