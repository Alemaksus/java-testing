package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_DESCRIPTION_TAB_CONTROLLER_TRAININGS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106559 Verify 'GET /api/training-management/plan/description/trainings' endpoint",
    groups = {"api"})
public class GetDescriptionTabControllerTrainingsEndpointApiTest {

  private final String expectedTrainingName = "AutoTest_WithPaidPricing";
  private final int expectedTrainingId = 2841;

  @Test(priority = 1)
  public void verifyGetTrainingsStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    JsonPath jsonPath =
        getTrainings()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath();
    List<Integer> actualTrainingIdsList = jsonPath.get("Id");
    List<String> actualTrainingNamesList = jsonPath.get("Name");
    softAssert.assertTrue(actualTrainingIdsList.stream().anyMatch(el -> el == expectedTrainingId),
        String.format("Training Id='%s' is not present in training Ids list!", expectedTrainingId));
    softAssert.assertTrue(
        actualTrainingNamesList.stream().anyMatch(el -> el.equals(expectedTrainingName)),
        String.format("Training name='%s' is not present in training names list!",
            expectedTrainingName));
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetTrainingsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getTrainings()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetTrainingsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getTrainings()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getTrainings() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_DESCRIPTION_TAB_CONTROLLER_TRAININGS);
  }
}
