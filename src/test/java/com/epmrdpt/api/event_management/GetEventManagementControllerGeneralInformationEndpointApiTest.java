package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_EVENT_ID_GENERAL_INFORMATION;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-110937 Verify 'GET /api/event-management/{eventId}/general-information' endpoint",
    groups = {"api"})
public class GetEventManagementControllerGeneralInformationEndpointApiTest {

  private final int eventId = 72;
  private final int invalidEventId = 0;
  private final String notExistEventId = "eventId";
  private final String expectedEventName = "AutoTestWithSurveyAnswer";
  private final String expectedFormatType = "Online";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetEventIdGeneralInformationOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getEventIdGeneralInformation(eventId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    softAssert.assertEquals(jsonPath.get("EventName"), expectedEventName,
        "Actual Event Name is not equal to expected!");
    softAssert.assertEquals(jsonPath.get("FormatType"), expectedFormatType,
        "Actual Format Type is not equal to expected!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetEventIdGeneralInformationBadRequest() {
    getEventIdGeneralInformation(invalidEventId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetEventIdGeneralInformationNotFound() {
    getEventIdGeneralInformation(notExistEventId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetEventIdGeneralInformationUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getEventIdGeneralInformation(eventId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetEventIdGeneralInformationForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getEventIdGeneralInformation(eventId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getEventIdGeneralInformation(Object eventId) {
    return given()
        .pathParam("eventId", eventId)
        .when()
        .get(EVENT_MANAGEMENT_EVENT_ID_GENERAL_INFORMATION);
  }
}
