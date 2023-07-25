package com.epmrdpt.bo;

import static com.epmrdpt.utils.DoubleUtils.roundToTwoDecimalPlaceDouble;

import java.util.List;
import java.util.Objects;

public class Student {

  private int number;
  private String name;
  private List<Integer> attendance;
  private int absence;
  private double averageTaskMark;
  private double averageMark;
  private double averageMarkDependsOnTaskWeight;
  private List<Double> taskMarkList;

  public Student withNumber(int number) {
    this.number = number;
    return this;
  }

  public Student withName(String name) {
    this.name = name;
    return this;
  }

  public Student withAttendance(List<Integer> attendance) {
    this.attendance = attendance;
    return this;
  }

  public Student withAbsence(int absence) {
    this.absence = absence;
    return this;
  }

  public Student withAverageTaskMark(double averageTaskMark) {
    this.averageTaskMark = averageTaskMark;
    return this;
  }

  public Student withAverageMark(double averageMark) {
    this.averageMark = averageMark;
    return this;
  }

  public Student withAverageMarkDependsOnTaskWeight(double averageMarkDependsOnTaskWeight) {
    this.averageMarkDependsOnTaskWeight = averageMarkDependsOnTaskWeight;
    return this;
  }

  public Student withTaskMarksList(List<Double> taskMarsList) {
    this.taskMarkList = taskMarsList;
    return this;
  }

  public int getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }

  public int getAbsence() {
    return absence;
  }

  public List<Integer> getAttendance() {
    return attendance;
  }

  public double getAverageTaskMark() {
    return averageTaskMark;
  }

  public double getAverageMark() {
    return averageMark;
  }

  public double getAverageMarkDependsOnTaskWeight() {
    return averageMarkDependsOnTaskWeight;
  }

  public List<Double> getTaskMarkList() {
    return taskMarkList;
  }

  public Double getAverageTaskMarkFromTaskMarkList(Student student) {
    return roundToTwoDecimalPlaceDouble(student
        .getTaskMarkList()
        .stream()
        .mapToDouble(mark -> mark).sum()
        / student.getTaskMarkList().size());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return number == student.number
        && absence == student.absence
        && Double.compare(student.averageTaskMark, averageTaskMark) == 0
        && Double.compare(student.averageMark, averageMark) == 0
        && Double.compare(student.averageMarkDependsOnTaskWeight,
        averageMarkDependsOnTaskWeight) == 0
        && Objects.equals(name, student.name)
        && Objects.equals(attendance, student.attendance)
        && Objects.equals(taskMarkList, student.taskMarkList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        number,
        name,
        attendance,
        absence,
        averageTaskMark,
        averageMark,
        taskMarkList,
        averageMarkDependsOnTaskWeight);
  }

  @Override
  public String toString() {
    return "Student{" +
        "number=" + number +
        ", name='" + name + '\'' +
        ", attendance=" + attendance +
        ", absence=" + absence +
        ", averageTaskMark=" + averageTaskMark +
        ", averageMark=" + averageMark +
        ", taskMarkList=" + taskMarkList +
        ", averageMarkDependsOnTaskWeight=" + averageMarkDependsOnTaskWeight +
        '}';
  }
}
