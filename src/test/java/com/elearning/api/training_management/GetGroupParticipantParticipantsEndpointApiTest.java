package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS;
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

@Test(description = "EPMRDPT-106208 Verify 'GET /api/training-management/{planId}/groups/{groupId}/participants' endpoint",
    groups = {"api"})
public class GetGroupParticipantParticipantsEndpointApiTest {

  private final int planId = 2840;
  private final int groupId = 3994;
  private final int invalidGroupId = -1;
  private final int invalidPlanId = 888;

  @DataProvider(name = "Provider data with path param")
  private Object[][] dataProviderPathParam() {
    return new Object[][]{
        {planId, invalidGroupId},
        {invalidPlanId, groupId},
    };
  }

  @Test(priority = 1)
  public void verifyGetParticipantsStatusOk() {
    List<Participant> actualParticipantList = getParticipants(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(new TypeRef<List<Participant>>() {
        });
    Participant expectedParticipant = getParticipantForTest();
    assertTrue(actualParticipantList.stream().anyMatch(o -> o.equals(expectedParticipant)),
        "Participant is not equal to expected response!");
  }

  @Test(priority = 1, dataProvider = "Provider data with path param")
  public void verifyGetParticipantsBadRequest(int planId, int groupId) {
    getParticipants(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetParticipantsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipants(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipants(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipants(int planId, int groupId) {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS);
  }

  private Participant getParticipantForTest() {
    return new Participant(
        "redadi4761@proton.me",
        "+375111000",
        325903,
        "QQ AA",
        false);
  }
}
