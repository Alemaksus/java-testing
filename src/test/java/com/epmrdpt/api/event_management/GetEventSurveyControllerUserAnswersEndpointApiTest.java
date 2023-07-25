package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_SURVEY_USER_ANSWERS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-110389 Verify 'GET /api/event-management/survey/user-answers' endpoint",
    groups = {"api"})
public class GetEventSurveyControllerUserAnswersEndpointApiTest {

  private final int eventId = 72;
  private final int userId = 325944;
  private final String expectedUserAnswer = "\"Self-search on the Internet\"";
  private final int invalidUserId = 0;
  private final int invalidEventId = 0;

  @DataProvider(name = "Provider data for test with query param")
  private Object[][] dataProviderWithParam() {
    return new Object[][]{
        {invalidEventId, userId},
        {eventId, invalidUserId},
    };
  }

  @Test(priority = 1)
  public void verifyGetUserAnswerOk() {
    String actualUserAnswer = getUserAnswer(eventId, userId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .asString();
    assertEquals(actualUserAnswer, expectedUserAnswer,
        "User answer is not equal to expected response!");
  }

  @Test(priority = 1, dataProvider = "Provider data for test with query param")
  public void verifyGetUserAnswerBadRequest(int eventId, int userId) {
    getUserAnswer(eventId, userId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetUserAnswerUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();

    getUserAnswer(eventId, userId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetUserAnswerForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();

    getUserAnswer(eventId, userId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getUserAnswer(int eventId, int userId) {
    return given()
        .queryParam("eventId", eventId)
        .queryParam("userId", userId)
        .when()
        .get(EVENT_MANAGEMENT_SURVEY_USER_ANSWERS);
  }
}
