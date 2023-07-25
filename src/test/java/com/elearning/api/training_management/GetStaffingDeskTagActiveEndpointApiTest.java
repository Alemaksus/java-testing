package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_STUFFING_DESK_TAG_CONTROLLER_ACTIVE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-104562 Verify 'GET /api/training-management/staffing-tag/active' endpoint",
    groups = {"api"})

public class GetStaffingDeskTagActiveEndpointApiTest {

  private final String expectedStuffingDeskTagName = "Automation Testers";

  @Test(priority = 1)
  public void verifyGetStuffingDeskIntegrationPlanIdStatusOk() {
    List<String> actualStuffingDeskTagNamesList = getStuffingDeskTagActive()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    Assert.assertTrue(
        actualStuffingDeskTagNamesList.stream()
            .anyMatch(e -> e.equals(expectedStuffingDeskTagName)),
        "Actual stuffing desk tag names list doesn't contain expected tag name!");
  }

  @Test(priority = 2)
  public void verifyGetStuffingDeskIntegrationPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getStuffingDeskTagActive()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetStuffingDeskIntegrationPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getStuffingDeskTagActive()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStuffingDeskTagActive() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_STUFFING_DESK_TAG_CONTROLLER_ACTIVE);
  }
}
