package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PARTICIPANT_STATUS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.ParticipantStatus;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-105040 Verify 'GET /api/training-management/{planId}/participant-statuses' endpoint",
    groups = {"api"})

public class GetParticipantStatusParticipantStatusesEndpointApiTest {

  private final int planId = 1;
  private final int invalidPlanId = 0;
  private final String incorrectPlanId = "planId";
  private final int expectedStudentCount = 21;
  private final int trainingInProgressStatusId = 9;
  private final int rejectedStatusId = 2;
  private final String expectedStatusName = "Training In Progress";

  private final List<String> expectedParticipantStatusesList = Arrays.asList(
      "Accepted for Lab",
      "Accepted for Training",
      "Cancelled",
      "Dismissed from Lab",
      "Dismissed from Training",
      "General Interview Scheduled",
      "Hired",
      "Initial Contact",
      "Lab Finished",
      "Lab in Progress",
      "New",
      "On Hold",
      "Proposed for Technical Interview",
      "Registration is cancelled",
      "Rejected",
      "Technical Interview Scheduled",
      "Training Finished",
      "Training in Progress"
  );

  @Test(priority = 1)
  public void verifyGetParticipantStatusesStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    List<ParticipantStatus> participantStatusesList =
        getParticipantStatuses(planId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(new TypeRef<List<ParticipantStatus>>() {
            });
    softAssert.assertEquals(
        participantStatusesList.stream()
            .filter(el -> el.getStatusId() == trainingInProgressStatusId)
            .findFirst().get().getHiringLinkStatusName(), expectedStatusName,
        "Actual status name isn't equal to expected status name!");
    softAssert.assertEquals(
        participantStatusesList.stream().filter(el -> el.getStatusId() == rejectedStatusId)
            .findFirst().get().getStudentCount(), expectedStudentCount,
        "Actual student count isn't equal to expected student count!");
    softAssert.assertEquals(
        participantStatusesList.stream().map(el -> el.getOriginalName()).sorted()
            .collect(Collectors.toList()), expectedParticipantStatusesList,
        "Participant statuses list doesn't contain all statuses!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetParticipantStatusesBadRequest() {
    getParticipantStatuses(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetParticipantStatusesNotFound() {
    getParticipantStatuses(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetParticipantStatusesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipantStatuses(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantStatusesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipantStatuses(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipantStatuses(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_PARTICIPANT_STATUS);
  }
}
