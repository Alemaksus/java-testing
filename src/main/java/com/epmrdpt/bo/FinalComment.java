package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class FinalComment {

  @JsonProperty("CreatedAt")
  private String createdAt;

  @JsonProperty("CreatedBy")
  private CreatedBy createdBy;

  @JsonProperty("Comments")
  private List<Comment> comments;

  public FinalComment() {
  }

  public FinalComment(String createdAt, CreatedBy createdBy, List<Comment> comments) {
    this.createdAt = createdAt;
    this.createdBy = createdBy;
    this.comments = comments;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public CreatedBy getCreatedBy() {
    return createdBy;
  }

  public List<Comment> getComments() {
    return comments;
  }

  @Override
  public String toString() {
    return "FinalCommentEntity{" +
        "createdAt='" + createdAt + '\'' +
        ", createdBy=" + createdBy +
        ", comments=" + comments +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FinalComment that = (FinalComment) o;
    return createdAt.equals(that.createdAt) && createdBy.equals(that.createdBy) && comments.equals(
        that.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdAt, createdBy, comments);
  }
}
