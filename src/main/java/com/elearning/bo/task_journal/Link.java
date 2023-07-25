package com.epmrdpt.bo.task_journal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

  @JsonProperty("Url")
  private String url;
  @JsonProperty("Name")
  private String name;

  public Link(String url, String name) {
    this.url = url;
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public String getName() {
    return name;
  }
}
