package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_LANGUAGES;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-110940 Verify 'GET /api/event-management/languages' endpoint",
    groups = {"api"})
public class GetEventManagementControllerLanguagesEndpointApiTest {

  private final List<String> expectedLanguageNamesList = Arrays.asList(
      "English",
      "Русский",
      "Українська"
  );

  @Test(priority = 1)
  public void verifyGetLanguagesStatusOk() {
    List<String> actualLanguageNamesList = getLanguages()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    assertTrue(expectedLanguageNamesList.stream().allMatch(actualLanguageNamesList::contains),
        "Actual Language Names List does not correspond to expected response!");
  }

  @Test(priority = 2)
  public void verifyGetLanguagesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getLanguages()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetLanguagesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getLanguages()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getLanguages() {
    return given()
        .when()
        .get(EVENT_MANAGEMENT_LANGUAGES);
  }
}
