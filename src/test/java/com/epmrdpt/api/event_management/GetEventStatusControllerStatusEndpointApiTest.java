package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_SURVEY_EVENT_ID_STATUS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-110504 Verify 'GET /api/event-management/{eventId}/status' endpoint",
    groups = {"api"})
public class GetEventStatusControllerStatusEndpointApiTest {

  private final int eventId = 72;
  private final int invalidEventId = 0;
  private final String incorrectEventId = "eventId";
  private final List<String> expectedEventStatusesList = Arrays.asList(
      "Draft",
      "Published",
      "Staffed",
      "Passed",
      "Cancelled");
  private final List<String> expectedEventStatesList = Arrays.asList(
      "Completed",
      "Active",
      "Inactive",
      "Inactive",
      "Inactive");

  @Test(priority = 1)
  public void verifyGetEventStatusesStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    JsonPath jsonPath = getEventStatuses(eventId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<String> actualGroupStatusesList = jsonPath.get("Status");
    List<String> actualGroupStatesList = jsonPath.get("State");
    softAssert.assertEquals(actualGroupStatusesList, expectedEventStatusesList,
        "Actual statuses list isn't equal to expected statuses list!");
    softAssert.assertEquals(actualGroupStatesList, expectedEventStatesList,
        "Actual states list isn't equal to expected states list!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetEventStatusesBadRequest() {
    getEventStatuses(invalidEventId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetEventStatusesNotFound() {
    getEventStatuses(incorrectEventId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetEventStatusesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getEventStatuses(eventId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetEventStatusesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getEventStatuses(eventId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getEventStatuses(Object eventId) {
    return given()
        .pathParam("eventId", eventId)
        .when()
        .get(EVENT_MANAGEMENT_SURVEY_EVENT_ID_STATUS);
  }
}
