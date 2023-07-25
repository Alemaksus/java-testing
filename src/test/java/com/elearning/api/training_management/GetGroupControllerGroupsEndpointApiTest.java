package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUPS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.group_controller.Group;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106244 Verify 'GET /api/training-management/{planId}/groups' endpoint",
    groups = {"api"})
public class GetGroupControllerGroupsEndpointApiTest {

  private final int planId = 3506;
  private final List<String> expectedGroupNamesList = Arrays.asList(
      "Software Testing 1",
      "Software Testing 2",
      "Software Testing 3");
  private final List<String> expectedStatusesList = Arrays.asList(
      "Finished",
      "Learning",
      "Formation");
  private final String sortColumnGroupName = "GroupName";
  private final String sortColumnStatus = "Status";
  private final String sortDirectionAscending = "Ascending";
  private final String sortDirectionDescending = "Descending";
  private final int invalidPlanId = 888;

  @Test(priority = 1)
  public void verifyGroupsSortedAscendingOfNamesStatusOk() {
    List<Group> actualParticipantsList = getGroupsList(planId, sortColumnGroupName,
        sortDirectionAscending)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .getList("Data", Group.class);
    assertEquals(
        actualParticipantsList.stream().map(Group::getName).collect(Collectors.toList()),
        expectedGroupNamesList,
        "Actual list of groups sorted in ascending order of names is not equal to expected group list!");
  }

  @Test(priority = 1)
  public void verifyGroupsSortedDescendingOfStatusesStatusOk() {
    List<Group> actualParticipantsList = getGroupsList(planId, sortColumnStatus,
        sortDirectionDescending)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .getList("Data", Group.class);
    assertEquals(
        actualParticipantsList.stream().map(Group::getStatus).collect(Collectors.toList()),
        expectedStatusesList,
        "Actual list of groups sorted in descending order of statuses is not equal to expected group list!");
  }

  @Test(priority = 1)
  public void verifyGetGroupsBadRequest() {
    getGroupsList(invalidPlanId, sortColumnStatus, sortDirectionDescending)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetGroupsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getGroupsList(planId, sortColumnStatus, sortDirectionDescending)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetGroupsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getGroupsList(planId, sortColumnStatus, sortDirectionDescending)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getGroupsList(int planId, String sortColumn, String sortDirection) {
    return given()
        .pathParam("planId", planId)
        .queryParam("sortColumn", sortColumn)
        .queryParam("sortDirection", sortDirection)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUPS);
  }
}
