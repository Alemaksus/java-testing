package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_SURVEY_TESTING_ELEARN;
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

@Test(description = "EPMRDPT-103832 Verify 'GET /api/training-management/plan/survey-testing/elearn' endpoint",
    groups = {"api"})

public class GetSurveyTestingElearnEndpointApiTest {

  private final String elearnOriginalId = "course-v1:test_org+test_course_org+test_course_org";
  private final String elearnName = "course_test";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetSurveyTestingElearnStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getSurveyTestingElearn()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<String> elearnOriginalIdsList = jsonPath.get("OriginalId");
    List<String> elearnNamesList = jsonPath.get("Name");
    softAssert.assertTrue(
        elearnOriginalIdsList.stream().anyMatch(el -> el.equals(elearnOriginalId)),
        String.format("Elearn OriginalId='%s' is not present in OriginalIds!", elearnOriginalId));
    softAssert.assertTrue(
        elearnNamesList.stream().anyMatch(el -> el.equals(elearnName)),
        String.format("Elearn Name='%s' is not present in Names!", elearnName));
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetSurveyTestingElearnUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSurveyTestingElearn()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSurveyTestingElearnForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSurveyTestingElearn()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSurveyTestingElearn() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_SURVEY_TESTING_ELEARN);
  }
}
