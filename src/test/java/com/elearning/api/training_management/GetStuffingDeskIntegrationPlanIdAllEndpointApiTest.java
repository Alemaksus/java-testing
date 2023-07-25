package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_STUFFING_DESK_INTEGRATION_CONTROLLER_PLAN_ID_All;
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

@Test(description = "EPMRDPT-103837 Verify 'GET /api/training-management/plan/sd/{planId}/all",
    groups = {"api"})
public class GetStuffingDeskIntegrationPlanIdAllEndpointApiTest {


  private final int planId = 2840;
  private final int invalidPlanId = 0;
  private final String expectedTrainingPoolCode = "TC-US-TESTJA";

  @Test(priority = 1)
  public void verifyGetStuffingDeskIntegrationPlanIdAllStatusOk() {
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getAdminAuthorizationToken());
    List<String> actualTrainingPoolCodesList = getStuffingDeskIntegrationPlanIdAll(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Requisitions.Code");
    Assert.assertTrue(
        actualTrainingPoolCodesList.stream().anyMatch(e -> e.equals(expectedTrainingPoolCode)),
        "Actual training Pool Codes List doesn't contain expected pool code!");
  }

  @Test(priority = 2)
  public void verifyGetStuffingDeskIntegrationPlanIdAllNotFound() {
    getStuffingDeskIntegrationPlanIdAll(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 3)
  public void verifyGetStuffingDeskIntegrationPlanIdAllUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeCookies();
    getStuffingDeskIntegrationPlanIdAll(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 4)
  public void verifyGetStuffingDeskIntegrationPlanIdAllForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getStudentAuthorizationToken());
    getStuffingDeskIntegrationPlanIdAll(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStuffingDeskIntegrationPlanIdAll(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_STUFFING_DESK_INTEGRATION_CONTROLLER_PLAN_ID_All);
  }
}
