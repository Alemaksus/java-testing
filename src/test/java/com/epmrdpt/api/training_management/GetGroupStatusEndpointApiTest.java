package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_STATUS_CONTROLLER_STATUSES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FINISHED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FORMATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_LEARNING;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.LocaleProperties;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106203 Verify 'GET /api/training-management/{planId}/groups/{groupId}/status' endpoint",
    groups = {"api"})
public class GetGroupStatusEndpointApiTest {

  private final int planId = 2840;
  private final int groupId = 3994;
  private final int invalidGroupId = 0;
  private final List<String> expectedGroupStatusesList = Arrays.asList(
      LocaleProperties.getValueOf(TRAINING_MANAGER_STATUS_FORMATION),
      LocaleProperties.getValueOf(TRAINING_MANAGER_STATUS_LEARNING),
      LocaleProperties.getValueOf(TRAINING_MANAGER_STATUS_FINISHED));
  private final List<String> expectedGroupStatesList = Arrays.asList(
      "Completed",
      "Active",
      "Inactive");

  @Test(priority = 1)
  public void verifyGetGroupStatusesStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    JsonPath jsonPath = getGroupStatuses(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<String> actualGroupStatusesList = jsonPath.get("Status");
    List<String> actualGroupStatesList = jsonPath.get("State");
    softAssert.assertEquals(actualGroupStatusesList, expectedGroupStatusesList,
        "Actual statuses list isn't equal to expected statuses list!");
    softAssert.assertEquals(actualGroupStatesList, expectedGroupStatesList,
        "Actual states list isn't equal to expected states list!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetGroupStatusesBadRequest() {
    getGroupStatuses(planId, invalidGroupId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetGroupStatusesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getGroupStatuses(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetGroupStatusesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getGroupStatuses(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getGroupStatuses(int planId, int groupId) {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_STATUS_CONTROLLER_STATUSES);
  }
}
