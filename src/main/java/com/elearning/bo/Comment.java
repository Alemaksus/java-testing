package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(Include.NON_EMPTY)
public class Comment {

  @JsonProperty("GroupId")
  private int groupId;

  @JsonProperty("StudentId")
  private int studentId;

  @JsonProperty("Id")
  private int commentId;

  @JsonProperty("CommentFor")
  private String commentFor;

  @JsonProperty("Comment")
  private String commentMessage;

  public Comment() {
  }

  public Comment(int groupId, int studentId, String commentMessage) {
    this.groupId = groupId;
    this.studentId = studentId;
    this.commentMessage = commentMessage;
  }

  public int getCommentId() {
    return commentId;
  }

  public String getCommentFor() {
    return commentFor;
  }

  public String getCommentMessage() {
    return commentMessage;
  }

  @Override
  public String toString() {
    return "Comment{" +
        "commentId=" + commentId +
        ", commentFor='" + commentFor + '\'' +
        ", comment='" + commentMessage + '\'' +
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
    Comment comment = (Comment) o;
    return commentId == comment.commentId && commentFor.equals(comment.commentFor)
        && commentMessage.equals(comment.commentMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId, commentFor, commentMessage);
  }
}
