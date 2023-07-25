package com.epmrdpt.bo.task_journal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class OfflineTaskMarkForm {

  @JsonProperty("TaskId")
  private int taskId;
  @JsonProperty("StudentId")
  private int studentId;
  @JsonProperty("Mark")
  private int mark;
  @JsonProperty("MaxMark")
  private int maxMark;
  @JsonProperty("IsFractionalMark")
  private boolean isFractionalMark;

  public OfflineTaskMarkForm() {
  }

  public OfflineTaskMarkForm(int taskId, int studentId, int mark, int maxMark,
      boolean isFractionalMark) {
    this.taskId = taskId;
    this.studentId = studentId;
    this.mark = mark;
    this.maxMark = maxMark;
    this.isFractionalMark = isFractionalMark;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  public int getMaxMark() {
    return maxMark;
  }

  public void setMaxMark(int maxMark) {
    this.maxMark = maxMark;
  }

  public boolean isFractionalMark() {
    return isFractionalMark;
  }

  public void setFractionalMark(boolean fractionalMark) {
    isFractionalMark = fractionalMark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OfflineTaskMarkForm that = (OfflineTaskMarkForm) o;
    return taskId == that.taskId && studentId == that.studentId && mark == that.mark
        && maxMark == that.maxMark && isFractionalMark == that.isFractionalMark;
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, studentId, mark, maxMark, isFractionalMark);
  }
}
