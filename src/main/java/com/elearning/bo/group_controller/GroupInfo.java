package com.epmrdpt.bo.group_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class GroupInfo{
  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Endless")
  private boolean endless;
  @JsonProperty("IsHidden")
  private boolean isHidden;
  @JsonProperty("StartDate")
  private String  startDate;
  @JsonProperty("EndDate")
  private String  endDate;
  @JsonProperty("CurrentGraduationCount")
  private int currentGraduationCount;
  @JsonProperty("MaxGraduationCount")
  private int maxGraduationCount;
  @JsonProperty("Rooms")
  private ArrayList<Object> rooms;

  public GroupInfo() {
  }

  public GroupInfo(int id, String name, boolean endless, boolean isHidden, String startDate,
      String endDate, int currentGraduationCount, int maxGraduationCount,
      ArrayList<Object> rooms) {
    this.id = id;
    this.name = name;
    this.endless = endless;
    this.isHidden = isHidden;
    this.startDate = startDate;
    this.endDate = endDate;
    this.currentGraduationCount = currentGraduationCount;
    this.maxGraduationCount = maxGraduationCount;
    this.rooms = rooms;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isEndless() {
    return endless;
  }

  public void setEndless(boolean endless) {
    this.endless = endless;
  }

  public boolean isHidden() {
    return isHidden;
  }

  public void setHidden(boolean hidden) {
    isHidden = hidden;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public int getCurrentGraduationCount() {
    return currentGraduationCount;
  }

  public void setCurrentGraduationCount(int currentGraduationCount) {
    this.currentGraduationCount = currentGraduationCount;
  }

  public int getMaxGraduationCount() {
    return maxGraduationCount;
  }

  public void setMaxGraduationCount(int maxGraduationCount) {
    this.maxGraduationCount = maxGraduationCount;
  }

  public ArrayList<Object> getRooms() {
    return rooms;
  }

  public void setRooms(ArrayList<Object> rooms) {
    this.rooms = rooms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GroupInfo groupInfo = (GroupInfo) o;

    if (id != groupInfo.id) {
      return false;
    }
    if (endless != groupInfo.endless) {
      return false;
    }
    if (isHidden != groupInfo.isHidden) {
      return false;
    }
    if (currentGraduationCount != groupInfo.currentGraduationCount) {
      return false;
    }
    if (maxGraduationCount != groupInfo.maxGraduationCount) {
      return false;
    }
    if (!name.equals(groupInfo.name)) {
      return false;
    }
    if (!startDate.equals(groupInfo.startDate)) {
      return false;
    }
    if (!endDate.equals(groupInfo.endDate)) {
      return false;
    }
    return rooms.equals(groupInfo.rooms);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + name.hashCode();
    result = 31 * result + (endless ? 1 : 0);
    result = 31 * result + (isHidden ? 1 : 0);
    result = 31 * result + startDate.hashCode();
    result = 31 * result + endDate.hashCode();
    result = 31 * result + currentGraduationCount;
    result = 31 * result + maxGraduationCount;
    result = 31 * result + rooms.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return
        "{\n"
            + "    \"Id\": " + id + ",\n"
            + "    \"Name\": \"" + name + "\",\n"
            + "    \"Endless\": " + endless + ",\n"
            + "    \"IsHidden\": " + isHidden + ",\n"
            + "    \"StartDate\": \"" + startDate + "\",\n"
            + "    \"EndDate\": \"" + endDate + "\",\n"
            + "    \"CurrentGraduationCount\": " + currentGraduationCount + ",\n"
            + "    \"MaxGraduationCount\": " + maxGraduationCount + ",\n"
            + "    \"Rooms\": " + rooms + "\n"
            + "  }";
  }
}
