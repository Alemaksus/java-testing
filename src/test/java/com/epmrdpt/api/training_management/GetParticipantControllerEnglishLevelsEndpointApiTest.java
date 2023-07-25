package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS_ENGLISH_LEVELS;
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

@Test(description = "EPMRDPT-105274 Verify 'GET /api/training-management/{planId}/participants/english-levels' endpoint",
    groups = {"api"})

public class GetParticipantControllerEnglishLevelsEndpointApiTest {

  private final int planId = 2840;
  private final String notExistPlanId = "planId";
  private final int invalidPlanId = 0;

  private final List<String> expectedEnglishLevels = Arrays.asList(
      "Unknown",
      "Elementary",
      "ElementaryPlus",
      "PreIntermediate",
      "PreIntermediatePlus",
      "Intermediate",
      "IntermediatePlus",
      "UpperIntermediate",
      "UpperIntermediatePlus",
      "Advanced",
      "AdvancedPlus",
      "PreNativeSpeaker",
      "NativeSpeaker");

  @Test(priority = 1)
  public void verifyGetParticipantEnglishLevelsStatusOk() {
    List<String> actualCountriesList = getParticipantEnglishLevels(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get();
    assertEquals(actualCountriesList, expectedEnglishLevels,
        "Actual English levels list doesn't equal expected English levels!");
  }

  @Test(priority = 1)
  public void verifyGetParticipantEnglishLevelsBadRequest() {
    getParticipantEnglishLevels(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetParticipantEnglishLevelsNotFound() {
    getParticipantEnglishLevels(notExistPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetParticipantEnglishLevelsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipantEnglishLevels(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantEnglishLevelsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipantEnglishLevels(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipantEnglishLevels(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS_ENGLISH_LEVELS);
  }
}
