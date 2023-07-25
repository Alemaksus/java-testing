package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PLAN_ID_PUBLIC_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106514 Verify 'GET /api/training-management/plan/general/{planId}/public-id' endpoint",
    groups = {"api"})
public class GetGeneralTabPublicIdEndpointApiTest {

  private final int planId = 2860;
  private final String notExistPlanId = "groupId";
  private final int invalidPlanId = -5;
  private final String expectedPublicId = "\"Mjg2MDwPm19jwNzMyNjhDREI0QTZDODRDNDgyMzAyNDE4NUFGOEJBODExMTlEQjI2RUQ5MDcwMUQ1MzU5NDc2QjExNzg5MDJBNQfN17j5fN17j5\"";

  @Test(priority = 1)
  public void verifyGetPlanIdStatusOk() {
    String actualPublicId = getPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .asString();
    assertEquals(actualPublicId, expectedPublicId,
        "Actual publicId isn't equal to expected publicId!");
  }

  @Test(priority = 1)
  public void verifyGetPlanIdBadRequest() {
    getPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetPlanIdNotFound() {
    getPlanId(notExistPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PLAN_ID_PUBLIC_ID);
  }
}
