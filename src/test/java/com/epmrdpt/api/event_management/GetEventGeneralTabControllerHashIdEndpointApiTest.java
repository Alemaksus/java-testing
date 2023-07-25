package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_GENERAL_HASH_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-111648 Verify 'GET /api/event-management/{eventId}/general/hash-id' endpoint",
    groups = {"api"})
public class GetEventGeneralTabControllerHashIdEndpointApiTest {

  private final int planId = 127;
  private final String notExistEventId = "eventId";
  private final int invalidEventId = 0;
  private final String expectedEventHashId = "\"MTI3PD5GQkNDMzNFRkI3Qzg0OUUzMTNDRjBDODZDNkVBMTk2MDg2MzI0ODk4QzFDMjE4REI3N0U3RUFGM0RFQzExOTk0\"";

  @Test(priority = 1)
  public void verifyGetHashIdStatusOk() {
    String actualEventHashId = getHashId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .asString();
    assertEquals(actualEventHashId, expectedEventHashId,
        "Actual hashId isn't equal to expected hashId!");
  }

  @Test(priority = 1)
  public void verifyGetHashIdBadRequest() {
    getHashId(invalidEventId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetHashIdNotFound() {
    getHashId(notExistEventId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetHashIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getHashId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetHashIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getHashId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getHashId(Object eventId) {
    return given()
        .pathParam("eventId", eventId)
        .when()
        .get(EVENT_MANAGEMENT_GENERAL_HASH_ID);
  }
}
