package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_TRAINER_CONTROLLER_TRAINERS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106179 Verify 'GET /api/training-management/{planId}/groups/{groupId}/trainers' endpoint",
    groups = {"api"})
public class GetGroupTrainersEndpointApiTest {

  private final int planId = 2840;
  private final int groupId = 3994;
  private final String expectedTrainerName = "AutoTrainer AutoTrainer";
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
  public void verifyGetGroupTrainersStatusOk() {
    List<String> actualTrainerNamesList = getGroupTrainers(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("DisplayName");
    assertTrue(actualTrainerNamesList.stream()
            .allMatch(e -> e.equals(expectedTrainerName)),
        "Actual trainer names list doesn't contain expected trainer name!");
  }

  @Test(priority = 1, dataProvider = "Provider data with path param")
  public void verifyGetGroupTrainersBadRequest(int planId, int groupId) {
    getGroupTrainers(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetGroupTrainersUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getGroupTrainers(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetGroupTrainersForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getGroupTrainers(planId, groupId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getGroupTrainers(int planId, int groupId) {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_TRAINER_CONTROLLER_TRAINERS);
  }
}
