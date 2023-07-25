package com.epmrdpt.bo.hashtag;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Hashtag {

  private Integer id;
  private String name;
  private String urlName;

  public Hashtag withId(Integer id) {
    this.id = id;
    return this;
  }

  public Hashtag withName(String name) {
    this.name = name;
    return this;
  }

  public Hashtag withUrlName(String urlName) {
    this.urlName = urlName;
    return this;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUrlName() {
    return urlName;
  }
}
