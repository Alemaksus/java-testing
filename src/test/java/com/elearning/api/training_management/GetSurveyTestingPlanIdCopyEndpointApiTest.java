package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_SURVEY_TESTING_PLAN_ID_COPY;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-103834 Verify 'GET /api/training-management/plan/survey-testing/{planId}/copy",
    groups = {"api"})
public class GetSurveyTestingPlanIdCopyEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 0;
  private final String incorrectPlanId = "planId";
  private final String expectedElearnName = "course_test";
  private final String expectedEnglishLevel = "Intermediate";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetSurveyTestingPlanIdCopyStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getSurveyTestingPlanIdCopy(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    String englishLevel = jsonPath.get("English.EnglishLevel");
    String elearnName = jsonPath.get("Elearn.Name");
    softAssert.assertEquals(englishLevel, expectedEnglishLevel,
        "English level is not equal to expected level!");
    softAssert.assertEquals(elearnName, expectedElearnName,
        "Elearn name is not equal to expected name!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetGetSurveyTestingPlanIdCopyBadRequest() {
    getSurveyTestingPlanIdCopy(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetSurveyTestingPlanIdCopyNotFound() {
    getSurveyTestingPlanIdCopy(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetSurveyTestingPlanIdCopyUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSurveyTestingPlanIdCopy(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSurveyTestingPlanIdCopyForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSurveyTestingPlanIdCopy(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSurveyTestingPlanIdCopy(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_SURVEY_TESTING_PLAN_ID_COPY);
  }
}
