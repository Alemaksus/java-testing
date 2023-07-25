package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_STUFFING_DESK_INTEGRATION_CONTROLLER_PLAN_ID;
import static com.epmrdpt.tokens_storage.TokensStorage.getAdminAuthorizationToken;
import static com.epmrdpt.tokens_storage.TokensStorage.getStudentAuthorizationToken;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-103836 Verify 'GET /api/training-management/plan/sd/{planId}",
    groups = {"api"})
public class GetStuffingDeskIntegrationPlanIdEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 0;
  private final String incorrectPlanId = "planId";
  private final String expectedTrainingPoolCode = "TC-XX-TEST22";

  @Test(priority = 1)
  public void verifyGetStuffingDeskIntegrationPlanIdStatusOk() {
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getAdminAuthorizationToken());
    List<String> actualTrainingPoolCode = getStuffingDeskIntegrationPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Requisitions.Code");
    Assert.assertTrue(
        actualTrainingPoolCode.stream().allMatch(e -> e.equals(expectedTrainingPoolCode)),
        "Actual training Pool Code is not equal to expected pool code!");
  }

  @Test(priority = 2)
  public void verifyGetGetStuffingDeskIntegrationPlanIdBadRequest() {
    getStuffingDeskIntegrationPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 3)
  public void verifyGetStuffingDeskIntegrationPlanIdNotFound() {
    getStuffingDeskIntegrationPlanId(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 4)
  public void verifyGetStuffingDeskIntegrationPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeCookies();
    getStuffingDeskIntegrationPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 5)
  public void verifyGetStuffingDeskIntegrationPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getStudentAuthorizationToken());
    getStuffingDeskIntegrationPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStuffingDeskIntegrationPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_STUFFING_DESK_INTEGRATION_CONTROLLER_PLAN_ID);
  }
}
