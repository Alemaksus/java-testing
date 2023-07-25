package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_STUFFING_DESK_TAG_PLAN_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-104563 Verify 'GET /api/training-management/staffing-tag/{planId}' endpoint",
    groups = {"api"})
public class GetStuffingDeskTagPlanIdEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 0;
  private final String expectedTagName = "Automation Testers";

  @Test(priority = 1)
  public void verifyGetStuffingDeskIntegrationPlanIdStatusOk() {
    List<String> actualTagName = getStuffingDeskTagPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("TagName");
    Assert.assertTrue(
        actualTagName.stream().allMatch(e -> e.equals(expectedTagName)),
        "Actual tag name is not equal to expected tag name!");
  }

  @Test(priority = 1)
  public void verifyGetStuffingDeskIntegrationPlanIdBadRequest() {
    getStuffingDeskTagPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetStuffingDeskIntegrationPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getStuffingDeskTagPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetStuffingDeskIntegrationPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getStuffingDeskTagPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStuffingDeskTagPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_STUFFING_DESK_TAG_PLAN_ID);
  }
}
