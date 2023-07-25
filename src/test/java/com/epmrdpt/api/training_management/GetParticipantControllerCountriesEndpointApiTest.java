package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS_COUNTRIES;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-105273 Verify 'GET /api/training-management/{planId}/participants/countries' endpoint",
    groups = {"api"})

public class GetParticipantControllerCountriesEndpointApiTest {

  private final int planId = 2858;
  private final String notExistPlanId = "planId";
  private final int invalidPlanId = 0;
  private final String expectedCountryName = "AutoTestCountry";

  @Test(priority = 1)
  public void verifyGetParticipantCountriesStatusOk() {
    List<String> actualCountriesList = getParticipantCountries(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    assertTrue(actualCountriesList.stream().allMatch(o -> o.equals(expectedCountryName)),
        "Actual country names list doesn't contain expected country name!");
  }

  @Test(priority = 1)
  public void verifyGetParticipantCountriesBadRequest() {
    getParticipantCountries(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetParticipantCountriesNotFound() {
    getParticipantCountries(notExistPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetParticipantCountriesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipantCountries(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantCountriesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipantCountries(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipantCountries(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS_COUNTRIES);
  }
}
