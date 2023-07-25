package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Image {

  @JsonProperty("MimeType")
  private String mimeType;
  @JsonProperty("ContentBase64")
  private String contentBase64;

  public Image withMimeType(String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  public Image withContentBase64(String contentBase64) {
    this.contentBase64 = contentBase64;
    return this;
  }

  public String getMimeType() {
    return mimeType;
  }

  public String getContentBase64() {
    return contentBase64;
  }
}
