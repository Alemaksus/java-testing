package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_FILTER_CONTROLLER_STATUS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_110504 Verify 'GET /api/event-management/filter/statuses' endpoint",
    groups = {"api"})
public class GetEventFilterStatusesEndpointApiTest {

  private final List<String> expectedStatusNamesList = Arrays.asList(
      "Draft",
      "Published",
      "Staffed",
      "Passed",
      "Cancelled");

  @Test(priority = 1)
  public void verifyGetStatusStateIsOk() {
    SoftAssert softAssert = new SoftAssert();
    List<String> actualStatusNamesList =
        getStatus()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .getList("Name");
    softAssert.assertNotNull(actualStatusNamesList, "The list of actual Status names is null!");
    if (actualStatusNamesList != null) {
      Collections.sort(actualStatusNamesList);
      Collections.sort(expectedStatusNamesList);
      softAssert.assertEquals(actualStatusNamesList, expectedStatusNamesList,
          "The list of actual Status names does not match the expected Status names list!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetStatusUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getStatus()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetStatusForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getStatus()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStatus() {
    return given()
        .when()
        .get(EVENT_MANAGEMENT_FILTER_CONTROLLER_STATUS);
  }
}
