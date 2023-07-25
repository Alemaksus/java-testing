package com.epmrdpt.bo.description_tab;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DescriptionBlock {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("Color")
  private String color;
  @JsonProperty("HeaderColor")
  private String headerColor;
  @JsonProperty("IsHidden")
  private boolean hidden;
  @JsonProperty("OrderNumber")
  private int orderNumber;
  @JsonProperty("Type")
  private String type;
  @JsonProperty("Contents")
  private List<Content> contents;
  @JsonProperty("Headers")
  private List<Header> headers;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Header {

  @JsonProperty("Culture")
  private String culture;
  @JsonProperty("Value")
  private String value;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Content {

  @JsonProperty("Culture")
  private String culture;
  @JsonProperty("Value")
  private String value;
}
