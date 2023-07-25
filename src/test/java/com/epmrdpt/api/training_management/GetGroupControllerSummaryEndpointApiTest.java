package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_CONTROLLER_SUMMARY;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106246 Verify 'GET /api/training-management/{planId}/groups/{groupId}/summary' endpoint",
    groups = {"api"})
public class GetGroupControllerSummaryEndpointApiTest {

  private final int planId = 2857;
  private final int groupId = 4022;
  private final String expectedGroupName = "AutoTestGroupd";
  private final String expectedTrainerName = "AutoTrainer AutoTrainer";
  private final int incorrectGroupId = 12;

  @Test(priority = 1)
  public void verifyGetGroupsSummaryStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    JsonPath jsonPath = getGroupsSummary(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    softAssert.assertTrue(
        jsonPath.getList("StudentGroups.Name").stream().anyMatch(e -> e.equals(expectedGroupName)),
        "Actual group name list doesn't contain expected group name!");
    softAssert.assertTrue(
        jsonPath.getList("Trainers.Name").stream().anyMatch(e -> e.equals(expectedTrainerName)),
        "Actual trainer name list doesn't contain expected trainer name!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetGroupsSummaryBadRequest() {
    getGroupsSummary(planId, incorrectGroupId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetGroupsSummaryUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getGroupsSummary(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetGroupsSummaryForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getGroupsSummary(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getGroupsSummary(int planId, int groupId) {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_SUMMARY);
  }
}
