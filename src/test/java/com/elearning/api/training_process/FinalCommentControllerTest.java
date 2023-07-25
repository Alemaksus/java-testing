package com.epmrdpt.api.training_process;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_DELETE;
import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_GET;
import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_POST;
import static io.restassured.RestAssured.given;

import com.epmrdpt.bo.Comment;
import com.epmrdpt.bo.FinalComment;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "Verify 'FinalCommentController'",
    groups = {"api_deprecated"})
public class FinalCommentControllerTest {

  private final int groupId = 4020;
  private final int studentId = 325907;
  private final String commentMessage = "API test comment! " + LocalDateTime.now();
  private FinalComment finalComment;

  @Test(priority = 1)
  public void verifyCommentCreation() {
    Comment comment = new Comment(groupId, studentId, commentMessage);
   finalComment = given()
        .contentType(ContentType.JSON)
        .body(comment)
        .when()
        .post(TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_POST)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(FinalComment.class);
    Assert.assertEquals(finalComment.getComments().get(0).getCommentMessage(), commentMessage, "Created comment message is incorrect!");
  }

  @Test(priority = 2)
  public void verifyCommentWasCreated() {
    Assert.assertEquals(getListOfFinalCommentEntities(groupId, studentId).stream()
        .flatMap(commentsInfo -> commentsInfo.getComments().stream())
        .filter(comment -> comment.getCommentMessage().equals(commentMessage))
        .findFirst()
        .get()
        .getCommentMessage(), commentMessage, "Comment wasn't created!");
  }

  @Test(priority = 3)
  public void verifyCommentDeletion() {
   int commentId = finalComment
        .getComments()
        .get(0)
        .getCommentId();
    given()
        .when()
        .delete(TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_DELETE, commentId)
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test(priority = 4)
  public void verifyCommentWasDeleted() {
    Assert.assertFalse(getListOfFinalCommentEntities(groupId, studentId).stream()
            .flatMap(commentInfo -> commentInfo.getComments().stream())
            .anyMatch(comment -> comment.getCommentMessage().equals(commentMessage)),
        "Comment wasn't deleted!");
  }

  private List<FinalComment> getListOfFinalCommentEntities(int groupId, int studentId) {
    return given()
        .queryParam("groupId", groupId)
        .queryParam("studentId", studentId)
        .when()
        .get(TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_GET)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .extract()
        .as(new TypeRef<List<FinalComment>>() {
        });
  }
}
