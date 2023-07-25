package com.epmrdpt.bo.task_journal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UpdateVariant {

  @JsonProperty("Number")
  private int number;
  @JsonProperty("AttachedLinks")
  private List<Link> attachedLinks;
  @JsonProperty("AttachedFileIds")
  private List<String> attachedFileIds;

  public UpdateVariant(int number, List<Link> attachedLinks, List<String> attachedFileIds) {
    this.number = number;
    this.attachedLinks = attachedLinks;
    this.attachedFileIds = attachedFileIds;
  }

  public int getNumber() {
    return number;
  }

  public List<Link> getAttachedLinks() {
    return attachedLinks;
  }

  public List<String> getAttachedFileIds() {
    return attachedFileIds;
  }
}
