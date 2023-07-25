package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS_LIST;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.participant.GroupParticipant;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106207 Verify 'GET /api/training-management/{planId}/groups/{groupId}/participants/list' endpoint",
    groups = {"api"})
public class GetGroupParticipantParticipantsListEndpointApiTest {

  private final int planId = 2840;
  private final int groupId = 3994;
  private final int pageNumber = 1;
  private final int itemsPerPage = 2;
  private final List<String> expectedParticipantNameList = Arrays.asList(
      "Student AutoTest",
      "Autotest LearningPageStudent");
  private final int invalidPlanId = 1;
  private final int invalidGroupId = 11;
  private final int invalidPageNumber = 0;
  private final int invalidItemsPerPage = 0;

  @DataProvider(name = "Provider data for test with path and query param")
  private Object[][] dataProviderWithParam() {
    return new Object[][]{
        {invalidPlanId, groupId, pageNumber, itemsPerPage},
        {planId, invalidGroupId, pageNumber, itemsPerPage},
        {planId, groupId, invalidPageNumber, itemsPerPage},
        {planId, groupId, pageNumber, invalidItemsPerPage}
    };
  }

  @Test(priority = 1)
  public void verifyGetParticipantStatusOk() {
    List<GroupParticipant> actualParticipantsList = getParticipantsList(planId, groupId, pageNumber,
        itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .getList("Items", GroupParticipant.class);
    assertEquals(
        actualParticipantsList.stream().map(GroupParticipant::getStudentName)
            .collect(Collectors.toList()),
        expectedParticipantNameList,
        "Actual participant names list is not equal to expected participant names list!");
  }

  @Test(priority = 1, dataProvider = "Provider data for test with path and query param")
  public void verifyGetParticipantBadRequest(int planId, int groupId, int pageNumber,
      int itemsPerPage) {
    getParticipantsList(planId, groupId, pageNumber, itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetParticipantUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getParticipantsList(planId, groupId, pageNumber, itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetParticipantForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getParticipantsList(planId, groupId, pageNumber, itemsPerPage)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getParticipantsList(int planId, int groupId, int pageNumber,
      int itemsPerPage) {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .queryParam("pageNumber", pageNumber)
        .queryParam("itemsPerPage", itemsPerPage)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS_LIST);
  }
}
