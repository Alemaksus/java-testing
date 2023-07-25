package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_TRAINER_CONTROLLER_TRAINERS_FIND;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.Trainer;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Test(description = "EPMRDPT-106180 Verify 'GET /api/training-management/{planId}/groups/{groupId}/trainers/find' endpoint",
    groups = {"api"})
public class GetGroupTrainersFindEndpointApiTest {

  private final int planId = 2840;
  private final int groupId = 3994;
  private final String filterCharSequence = "ManagerTest ManagerTest";
  private final int filterCount = 2;
  private final String invalidGroupId = "groupId";
  private final String invalidPlanId = "planId";
  private final int invalidFilterCharSequence = 0;
  private final String invalidFilterCount = "filterCount";

  @DataProvider(name = "Provider data with filter and path param")
  private Object[][] dataProviderFilterAndPathParam() {
    return new Object[][]{
        {invalidPlanId, groupId, filterCharSequence, filterCount},
        {planId, invalidGroupId, filterCharSequence, filterCount},
        {planId, groupId, invalidFilterCharSequence, filterCount},
        {planId, groupId, filterCharSequence, invalidFilterCount}
    };
  }

  @Test(priority = 1)
  public void verifyGetGroupTrainersStatusOk() {
    List<Trainer> actualParticipantList = getGroupTrainers(planId, groupId, filterCharSequence,
        filterCount)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(new TypeRef<List<Trainer>>() {
        });
    Trainer expectedTrainer = getTrainerForTest();
    assertTrue(actualParticipantList.stream().allMatch(o -> o.equals(expectedTrainer)),
        "Trainer is not equal to expected response!");
  }

  @Test(priority = 1, dataProvider = "Provider data with filter and path param")
  public void verifyGetGroupTrainersBadRequest(Object planId, Object groupId,
      Object filterCharSequence, Object filterCount) {
    getGroupTrainers(planId, groupId, filterCharSequence, filterCount)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetGroupTrainersUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getGroupTrainers(planId, groupId, filterCharSequence, filterCount)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetGroupTrainersForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getGroupTrainers(planId, groupId, filterCharSequence, filterCount)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getGroupTrainers(Object planId, Object groupId, Object filterCharSequence,
      Object filterCount) {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .queryParam("filter.charSequence", filterCharSequence)
        .queryParam("filter.count", filterCount)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_TRAINER_CONTROLLER_TRAINERS_FIND);
  }

  private Trainer getTrainerForTest() {
    return new Trainer(
        "userastrainingmanager@gmail.com",
        "+375321000",
        325910,
        "ManagerTest ManagerTest",
        false);
  }
}
