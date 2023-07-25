package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PLAN_CONTROLLER_PLANS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-104857 Verify 'GET /api/training-management/plans' endpoint",
    groups = {"api"})

public class GetPlanControllerPlansEndpointApiTest {

  private final String expectedTrainingName = "Automated Testing";

  @Test(priority = 1)
  public void verifyGetPlanControllerPlansStatusOk() {
    List<String> actualTrainingNamesList = getPlanControllerPlans()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    Assert.assertTrue(
        actualTrainingNamesList.stream()
            .anyMatch(e -> e.equals(expectedTrainingName)),
        "Actual training names list doesn't contain expected training name!");
  }

  @Test(priority = 2)
  public void verifyGetPlanControllerPlansUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getPlanControllerPlans()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetPlanControllerPlansForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getPlanControllerPlans()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getPlanControllerPlans() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_PLAN_CONTROLLER_PLANS);
  }
}
