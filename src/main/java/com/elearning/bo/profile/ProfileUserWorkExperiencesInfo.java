package com.epmrdpt.bo.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class ProfileUserWorkExperiencesInfo {

  @JsonProperty("Id")
  private int id;

  @JsonProperty("NameCompany")
  private String nameCompany;

  @JsonProperty("Position")
  private String position;

  @JsonProperty("DateStart")
  private String dateStart;

  @JsonProperty("DateEnd")
  private String dateEnd;

  @JsonProperty("AdditionInformation")
  private String additionInformation;

  @JsonProperty("HasWorkExperience")
  private boolean hasWorkExperience;

  @JsonProperty("IsTillNow")
  private boolean isTillNow;

  @JsonProperty("User")
  private String user;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNameCompany() {
    return nameCompany;
  }

  public void setNameCompany(String nameCompany) {
    this.nameCompany = nameCompany;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getDateStart() {
    return dateStart;
  }

  public void setDateStart(String dateStart) {
    this.dateStart = dateStart;
  }

  public String getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(String dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getAdditionInformation() {
    return additionInformation;
  }

  public void setAdditionInformation(String additionInformation) {
    this.additionInformation = additionInformation;
  }

  public boolean isHasWorkExperience() {
    return hasWorkExperience;
  }

  public void setHasWorkExperience(boolean hasWorkExperience) {
    this.hasWorkExperience = hasWorkExperience;
  }

  public boolean isTillNow() {
    return isTillNow;
  }

  public void setTillNow(boolean tillNow) {
    isTillNow = tillNow;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileUserWorkExperiencesInfo profileUserWorkExperiencesInfo = (ProfileUserWorkExperiencesInfo) o;
    return id == profileUserWorkExperiencesInfo.id
        && hasWorkExperience == profileUserWorkExperiencesInfo.hasWorkExperience
        && isTillNow == profileUserWorkExperiencesInfo.isTillNow
        && Objects.equals(nameCompany, profileUserWorkExperiencesInfo.nameCompany)
        && Objects.equals(position, profileUserWorkExperiencesInfo.position)
        && Objects.equals(dateStart, profileUserWorkExperiencesInfo.dateStart)
        && Objects.equals(dateEnd, profileUserWorkExperiencesInfo.dateEnd)
        && Objects.equals(additionInformation, profileUserWorkExperiencesInfo.additionInformation)
        && Objects.equals(user, profileUserWorkExperiencesInfo.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nameCompany, position, dateStart, dateEnd, additionInformation,
        hasWorkExperience, isTillNow, user);
  }
}
