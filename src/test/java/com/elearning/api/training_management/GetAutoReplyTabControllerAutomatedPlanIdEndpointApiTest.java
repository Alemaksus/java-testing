package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED_PLANID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_107098 Verify 'GET GET /api/training-management/plan/automated/{planId}' endpoint",
    groups = {"api"})
public class GetAutoReplyTabControllerAutomatedPlanIdEndpointApiTest {

  private final int planId = 2040;
  private final int invalidId = 0;
  private final String wrongId = "planId";
  private final List<String> expectedAutoReplyTypeList = Arrays.asList(
      "Registering",
      "Accepted",
      "TryAgain");

  @Test(priority = 1)
  public void verifyGetAutomatedPlanIdOk() {
    List<String> actualAutoReplyTypeList = getAutomatedPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("AutoReplyType");
    assertEquals(actualAutoReplyTypeList, expectedAutoReplyTypeList,
        "Actual 'AutoReplyTypeList' does not correspond to expected 'AutoReplyTypeList'!");
  }

  @Test(priority = 2)
  public void verifyGetAutomatedPlanInvalidId() {
    getAutomatedPlanId(invalidId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 3)
  public void verifyGetAutomatedPlanIdIdNotFound() {
    getAutomatedPlanId(wrongId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 4)
  public void verifyGetAutomatedPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getAutomatedPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 5)
  public void verifyGetAutomatedPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getAutomatedPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getAutomatedPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED_PLANID);
  }
}
