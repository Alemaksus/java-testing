package com.epmrdpt.bo.task_journal;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Duration {

  @JsonProperty("Value")
  private int value;
  @JsonProperty("Unit")
  private Unit unit;

  @JsonFormat(shape = STRING)
  public enum Unit {
    DAY, HOUR
  }

  public Duration(int value, Unit unit) {
    this.value = value;
    this.unit = unit;
  }

  public int getValue() {
    return value;
  }

  public Unit getUnit() {
    return unit;
  }
}
