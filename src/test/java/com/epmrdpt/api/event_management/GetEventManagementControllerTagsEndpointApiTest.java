package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_TAGS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-111004 Verify 'GET /api/event-management/tags' endpoint",
    groups = {"api"})
public class GetEventManagementControllerTagsEndpointApiTest {

  private final String expectedTagName = "#AutoTest";

  @Test(priority = 1)
  public void verifyGetTagsStatusOk() {
    List<String> actualLanguageNamesList = getTags()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    assertTrue(actualLanguageNamesList.contains(expectedTagName),
        "Actual Language Names List does not correspond to expected response!");
  }

  @Test(priority = 2)
  public void verifyGetTagsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getTags()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetTagsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getTags()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getTags() {
    return given()
        .when()
        .get(EVENT_MANAGEMENT_TAGS);
  }
}
