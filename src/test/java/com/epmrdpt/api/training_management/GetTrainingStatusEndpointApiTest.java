package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_TRAINING_STATUS_CONTROLLER;
import static com.epmrdpt.tokens_storage.TokensStorage.getAdminAuthorizationToken;
import static com.epmrdpt.tokens_storage.TokensStorage.getStudentAuthorizationToken;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.training_management.TrainingStatus;
import com.epmrdpt.bo.training_management.TrainingStatus.TrainingStatuses;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-103808 Verify 'GET /api/training-management/plan/status/{planId}' endpoint",
    groups = {"api"})
public class GetTrainingStatusEndpointApiTest {

  private final int planId = 2841;
  private final int invalidPlanId = 0;
  private final int numberOfAvailableStatuses = 4;
  private final String trainingStatusDraft = TrainingStatuses.REGISTRATION_OPEN.getTrainingStatus();
  private final String trainingStatusPlanned = TrainingStatuses.REGISTRATION_CLOSED.getTrainingStatus();
  private final String expectedState = "active";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetTrainingStatusOk() {
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getAdminAuthorizationToken());
    softAssert = new SoftAssert();
    List<TrainingStatus> trainingStatuses = getTrainingStatus(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .getList(".", TrainingStatus.class);
    softAssert.assertEquals(
        trainingStatuses.stream().map(el -> el.getStatus()).collect(Collectors.toList()),
        Stream.of(TrainingStatuses.values()).map(TrainingStatuses::getTrainingStatus)
            .collect(Collectors.toList()),
        "Training statuses list doesn't contain all statuses!");
    softAssert.assertEquals(
        trainingStatuses.stream().filter(el -> el.getStatus().equals(trainingStatusDraft))
            .findFirst()
            .get().getState(), expectedState,
        "Training state is not 'active'!");
    softAssert.assertTrue(
        trainingStatuses.stream().filter(el -> el.getStatus().equals(trainingStatusPlanned))
            .findFirst().get().isAvailable(),
        "Training 'IsAvailable' value is not 'true'!");
    softAssert.assertEquals(
        trainingStatuses.stream().filter(el -> el.isAvailable()).count()
        , numberOfAvailableStatuses,
        String.format("Number of 'IsAvailable' value is not equal '%s'!",
            numberOfAvailableStatuses));
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetTrainingStatusBadRequest() {
    getTrainingStatus(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 3)
  public void verifyGetTrainingStatusUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeCookies();
    getTrainingStatus(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 4)
  public void verifyGetTrainingStatusForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getStudentAuthorizationToken());
    getTrainingStatus(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getTrainingStatus(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_TRAINING_STATUS_CONTROLLER);
  }
}
