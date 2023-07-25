package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_MENTOR_CONTROLLER_GROUP_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106176 Verify 'GET /api/training-management/mentor/{groupId}' endpoint",
    groups = {"api"})
public class GetMentorGroupIdEndpointApiTest {

  private final int groupId = 3994;
  private final String notExistGroupId = "groupId";
  private final int invalidGroupId = 0;
  private final String expectedReviewerName = "AutoTrainer AutoTrainer";
  private final String expectedStudentName = "Student AutoTest";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetReviewerStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getReviewer(groupId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<String> actualReviewerNamesList = jsonPath.get("Mentor.DisplayName");
    List<List<String>> actualStudentsNamesList = jsonPath.get("Students.DisplayName");
    softAssert.assertTrue(
        actualReviewerNamesList.stream().allMatch(e -> e.equals(expectedReviewerName)),
        "Actual reviewer names list doesn't contain expected reviewer name!");
    softAssert.assertTrue(actualStudentsNamesList.stream().flatMap(e -> e.stream())
            .allMatch(e -> e.equals(expectedStudentName)),
        "Actual students names list doesn't contain expected student name!");
  }

  @Test(priority = 1)
  public void verifyGetReviewerBadRequest() {
    getReviewer(invalidGroupId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetReviewerNotFound() {
    getReviewer(notExistGroupId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetReviewerUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getReviewer(groupId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetReviewerForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getReviewer(groupId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getReviewer(Object groupId) {
    return given()
        .pathParam("groupId", groupId)
        .when()
        .get(TRAINING_MANAGEMENT_MENTOR_CONTROLLER_GROUP_ID);
  }
}
