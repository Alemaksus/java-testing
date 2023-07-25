package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class AdditionalFile {
  @JsonProperty("Length")
  public int length;
  @JsonProperty("Extension")
  public String extension;
  @JsonProperty("Id")
  public String id;
  @JsonProperty("Name")
  public String name;

  public AdditionalFile() {
  }

  public AdditionalFile withLength(int length) {
    this.length = length;
    return this;
  }

  public AdditionalFile withExtension(String extension) {
    this.extension = extension;
    return this;
  }

  public AdditionalFile withId(String id) {
    this.id = id;
    return this;
  }

  public AdditionalFile withName(String name) {
    this.name = name;
    return this;
  }

  public int getLength() {
    return length;
  }

  public String getExtension() {
    return extension;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AdditionalFile)) {
      return false;
    }
    AdditionalFile that = (AdditionalFile) o;
    return getLength() == that.getLength() && Objects.equals(getExtension(),
        that.getExtension()) && Objects.equals(getId(), that.getId())
        && Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLength(), getExtension(), getId(), getName());
  }

  @Override
  public String toString() {
    return "AdditionalFile{" +
        "length=" + length +
        ", extension='" + extension + '\'' +
        ", id='" + id + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
