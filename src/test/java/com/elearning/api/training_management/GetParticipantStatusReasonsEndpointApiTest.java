package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PARTICIPANT_STATUS_REASONS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-105041 Verify 'GET /api/training-management/{planId}/participant-statuses/reasons' endpoint",
    groups = {"api"})

public class GetParticipantStatusReasonsEndpointApiTest {

  private final int planId = 1;
  private final String incorrectPlanId = "planId";
  private final int expectedStatusReasonsListSize = 58;
  private final static String CANDIDATE_OVERQUALIFIED_REASON = "Candidate is overqualified";
  private final static String ONBOARDING_FAILED_REASON = "Onboarding failed (NPR cancelled/rejected)";

  @Test(priority = 1)
  public void verifyGetParticipantReasonsStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    List<String> participantStatusReasonsList =
        getParticipantReasons(planId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get("SdReasonName");
    softAssert.assertTrue(
        participantStatusReasonsList.stream()
            .anyMatch(el -> el.equals(CANDIDATE_OVERQUALIFIED_REASON)),
        String.format("Reason='%s' is not present in reasons list!",
            CANDIDATE_OVERQUALIFIED_REASON));
    softAssert.assertTrue(
        participantStatusReasonsList.stream().anyMatch(el -> el.equals(ONBOARDING_FAILED_REASON)),
        String.format("Reason='%s' is not present in reasons list!", ONBOARDING_FAILED_REASON));
    softAssert.assertEquals(
        participantStatusReasonsList.size(), expectedStatusReasonsListSize,
        "Actual reasons list size  is not equal to expected reasons list size!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetParticipantReasonsNotFound() {
    getParticipantReasons(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetParticipantReasonsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipantReasons(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantReasonsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipantReasons(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipantReasons(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_PARTICIPANT_STATUS_REASONS);
  }
}
