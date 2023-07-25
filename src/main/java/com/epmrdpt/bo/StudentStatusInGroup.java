package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class StudentStatusInGroup {

  @JsonProperty("Id")
  private String id;
  @JsonProperty("Value")
  private String statusInGroup;

  public StudentStatusInGroup() {
  }

  public StudentStatusInGroup(String id, String statusInGroup) {
    this.id = id;
    this.statusInGroup = statusInGroup;
  }

  public String getId() {
    return id;
  }

  public String getStatusInGroup() {
    return statusInGroup;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setStatusInGroup(String statusInGroup) {
    this.statusInGroup = statusInGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentStatusInGroup that = (StudentStatusInGroup) o;
    return Objects.equals(id, that.id) && Objects.equals(statusInGroup,
        that.statusInGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, statusInGroup);
  }

  @Override
  public String toString() {
    return "StudentStatusInGroup{" +
        "id='" + id + '\'' +
        ", statusInGroup='" + statusInGroup + '\'' +
        '}';
  }
}
