package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PARTICIPANT_GROUP_CONTROLLER_PARTICIPANT_GROUPS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-105126 Verify 'GET /api/training-management/{planId}/participant-groups' endpoint",
    groups = {"api"})

public class GetParticipantGroupsEndpointApiTest {

  private final int planId = 2857;
  private final String expectedGroupName = "AutoTestGroupd";
  private final int invalidPlanId = 0;
  private final String incorrectPlanId = "planId";

  @Test(priority = 1)
  public void verifyGetParticipantGroupsStatusOk() {
    List<String> actualGroupNamesList = getParticipantGroupsPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    Assert.assertTrue(actualGroupNamesList.stream().anyMatch(e -> e.equals(expectedGroupName)),
        "Actual group names list doesn't contain expected group name!");
  }

  @Test(priority = 1)
  public void verifyGetParticipantGroupsBadRequest() {
    getParticipantGroupsPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetParticipantGroupsNotFound() {
    getParticipantGroupsPlanId(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetParticipantGroupsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipantGroupsPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantGroupsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipantGroupsPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipantGroupsPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_PARTICIPANT_GROUP_CONTROLLER_PARTICIPANT_GROUPS);
  }
}
