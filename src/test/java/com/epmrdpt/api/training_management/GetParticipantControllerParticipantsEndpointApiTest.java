package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.participant.Participant;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-105272 Verify 'GET /api/training-management/{planId}/participants' endpoint",
    groups = {"api"})

public class GetParticipantControllerParticipantsEndpointApiTest {

  private final int planId = 2840;
  private final String filterCharSequence = "ManagerTest ManagerTest";
  private final int filterCount = 2;
  private final String incorrectPlanId = "planId";
  private final int incorrectFilterCharSequence = 0;
  private final String incorrectFilterCount = "filterCount";

  @DataProvider(name = "Provider data for test with filters")
  private Object[][] dataProviderWithFilters() {
    return new Object[][]{
        {filterCharSequence, incorrectFilterCount},
        {incorrectFilterCharSequence, filterCount},
    };
  }

  @Test(priority = 1)
  public void verifyGetParticipantStatusOk() {
    List<Participant> actualParticipantList = getParticipant(planId, filterCharSequence,
        filterCount)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(new TypeRef<List<Participant>>() {
        });
    Participant expectedParticipant = getParticipantForTest();
    assertTrue(actualParticipantList.stream().allMatch(o -> o.equals(expectedParticipant)),
        "Participant is not equal to expected response!");
  }

  @Test(priority = 1, dataProvider = "Provider data for test with filters")
  public void verifyGetParticipantBadRequest(Object filterCharSequence, Object filterCount) {
    getParticipant(planId, filterCharSequence, filterCount)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetParticipantNotFound() {
    getParticipant(incorrectPlanId, filterCharSequence, filterCount)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetParticipantUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipant(planId, filterCharSequence, filterCount)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipant(planId, filterCharSequence, filterCount)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipant(Object planId, Object filterCharSequence,
      Object filterCount) {
    return given()
        .pathParam("planId", planId)
        .queryParam("filter.charSequence", filterCharSequence)
        .queryParam("filter.count", filterCount)
        .when()
        .get(TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS);
  }

  private Participant getParticipantForTest() {
    return new Participant(
        "userastrainingmanager@gmail.com",
        "+375321000",
        325910,
        "ManagerTest ManagerTest",
        false);
  }
}
