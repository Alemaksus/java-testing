package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PLAN_CONTROLLER_PLAN_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-105858 Verify 'GET /api/training-management/plans/{planId}' endpoint",
    groups = {"api"})
public class GetPlanControllerPlanIdEndpointApiTest {

  private final int planId = 2040;
  private final int invalidPlanId = 0;
  private final String expectedTrainingName = "Automated Testing";

  @Test(priority = 1)
  public void verifyGetPlanControllerPlanIdStatusOk() {
    String actualTrainingName = getPlanControllerPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    Assert.assertEquals(
        actualTrainingName,expectedTrainingName,
        "Actual training name is not equal to expected training name!");
  }

  @Test(priority = 1)
  public void verifyGetPlanControllerPlanIdBadRequest() {
    getPlanControllerPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetPlanControllerPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getPlanControllerPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetPlanControllerPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getPlanControllerPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getPlanControllerPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_PLAN_CONTROLLER_PLAN_ID);
  }
}
