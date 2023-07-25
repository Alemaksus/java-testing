package com.epmrdpt.bo.task_journal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notice {

  @JsonProperty("NeedNotify")
  private boolean needNotify;
  @JsonProperty("Duration")
  private Duration duration;
  @JsonProperty("Notified")
  private String notified;

  public Notice withNotify(Duration duration, String notified) {
    this.needNotify = true;
    this.duration = duration;
    this.notified = notified;
    return this;
  }

  public boolean isNeedNotify() {
    return needNotify;
  }

  public Duration getDuration() {
    return duration;
  }

  public String getNotified() {
    return notified;
  }
}
