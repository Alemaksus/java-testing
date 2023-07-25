package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_SURVEY_USER_ANSWERS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_ONE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.LocaleProperties;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-103835 Verify 'GET /api/training-management/survey/user-answers",
    groups = {"api"})

public class GetSurveyUserAnswersEndpointApiTest {

  private final int planId = 2841;
  private final int userId = 325944;
  private final int invalidPlanId = 0;
  private final String expectedOptionDefinition = LocaleProperties.getValueOf(
      REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_ONE);

  @Test(priority = 1)
  public void verifyGetSurveyUserAnswersStatusOk() {
    List<List<String>> actualOptionDefinition = getSurveyUserAnswers(planId, userId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath().get("SurveyAnswerOptions.OptionDefinition");
    assertTrue(actualOptionDefinition.stream().flatMap(e -> e.stream())
            .allMatch(e -> e.equals(expectedOptionDefinition)),
        "Actual user answer is not equal to expected answer!");
  }

  @Test(priority = 1)
  public void verifyGetGetSurveyUserAnswersBadRequest() {
    getSurveyUserAnswers(invalidPlanId, userId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetSurveyUserAnswersUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSurveyUserAnswers(planId, userId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSurveyUserAnswersForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSurveyUserAnswers(planId, userId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSurveyUserAnswers(Object planId, Object userId) {
    return given()
        .queryParam("planId", planId)
        .queryParam("userId", userId)
        .when()
        .get(TRAINING_MANAGEMENT_SURVEY_USER_ANSWERS);
  }
}
