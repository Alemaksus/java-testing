package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_STUDENT_GROUP_CONTROLLER_STUDENT_GROUPS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106880 Verify 'GET /api/training-management/{planId}/classes/{classId}/student-groups' endpoint",
    groups = {"api"})
public class GetClassStudentGroupControllerStudentsGroupsEndpointApiTest {

  private final int planId = 2857;
  private final int classId = 4022;
  private final int invalidClassId = 12;
  private final int invalidPlanId = 888;
  private final List<String> expectedGroupNamesList = Arrays.asList(
      "Group_1",
      "Group_2");

  @DataProvider(name = "Provider data with path param")
  private Object[][] dataProviderPathParam() {
    return new Object[][]{
        {planId, invalidClassId},
        {invalidPlanId, classId},
    };
  }

  @Test(priority = 1)
  public void verifyGetStudentGroupsStatusOk() {
    List<String> actualGroupNamesList = getStudentGroups(planId, classId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    assertEquals(actualGroupNamesList.stream().sorted().collect(Collectors.toList()),
        expectedGroupNamesList,
        "Actual groups names list is not equal to expected groups names!");
  }

  @Test(priority = 1, dataProvider = "Provider data with path param")
  public void verifyGetStudentGroupsBadRequest(int planId, int groupId) {
    getStudentGroups(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetStudentGroupsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getStudentGroups(planId, classId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetStudentGroupsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getStudentGroups(planId, classId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStudentGroups(int planId, int classId) {
    return given()
        .pathParam("planId", planId)
        .pathParam("classId", classId)
        .when()
        .get(TRAINING_MANAGEMENT_STUDENT_GROUP_CONTROLLER_STUDENT_GROUPS);
  }
}
