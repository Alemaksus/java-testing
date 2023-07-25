package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class AdditionalLink {
  @JsonProperty("Url")
  public String url;
  @JsonProperty("Name")
  public String name;

  public AdditionalLink() {
  }

  public AdditionalLink withUrl(String url) {
    this.url = url;
    return this;
  }

  public AdditionalLink withName(String name) {
    this.name = name;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AdditionalLink)) {
      return false;
    }
    AdditionalLink that = (AdditionalLink) o;
    return Objects.equals(url, that.url) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, name);
  }

  @Override
  public String toString() {
    return "AdditionalLink{" +
        "url='" + url + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
