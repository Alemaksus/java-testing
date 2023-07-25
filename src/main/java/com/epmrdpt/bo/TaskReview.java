package com.epmrdpt.bo;

public class TaskReview {

  private int mark;
  private String commentForStudent;
  private String commentForTrainers;
  private String studentName;

  public void setMark(int mark) {
    this.mark = mark;
  }

  public void setCommentForStudent(String commentForStudent) {
    this.commentForStudent = commentForStudent;
  }

  public void setCommentForTrainers(String commentForTrainers) {
    this.commentForTrainers = commentForTrainers;
  }

  public int getMark() {
    return mark;
  }

  public String getCommentForStudent() {
    return commentForStudent;
  }

  public String getCommentForTrainers() {
    return commentForTrainers;
  }

  public String getStudentName() {
    return studentName;
  }

  public TaskReview withStudentName(String studentName) {
    this.studentName = studentName;
    return this;
  }

  public TaskReview withCommentForStudent(String commentForStudent) {
    this.commentForStudent = commentForStudent;
    return this;
  }

  public TaskReview withCommentForTrainers(String commentForTrainers) {
    this.commentForTrainers = commentForTrainers;
    return this;
  }
}
