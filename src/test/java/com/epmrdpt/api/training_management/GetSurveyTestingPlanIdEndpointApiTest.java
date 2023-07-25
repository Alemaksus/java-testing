package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_SURVEY_TESTING_PLAN_ID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_ONE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_SIX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_QUESTION;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.LanguageEnum;
import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.LocaleProperties;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-103833 Verify 'GET /api/training-management/plan/survey-testing/{planId}",
    groups = {"api"})

public class GetSurveyTestingPlanIdEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 0;
  private final String incorrectPlanId = "planId";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetSurveyTestingPlanIdStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getSurveyTestingPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<List<String>> surveyQuestionsList = jsonPath.get(
        "Survey.Questions.LocalizedQuestion.Question");
    List<List<List<String>>> surveyOptionsList = jsonPath.get(
        "Survey.Questions.LocalizedQuestion.Options.Name");
    softAssert.assertEquals(
        surveyQuestionsList.stream().flatMap(el -> el.stream()).collect(Collectors.toList()),
        getSurveyQuestionsList(),
        "Survey question list doesn't contain all questions!");
    softAssert.assertEquals(
        surveyOptionsList.stream().flatMap(el -> el.stream()).flatMap(el -> el.stream())
            .collect(Collectors.toList()), getSurveyOptionsList(),
        "Survey options list doesn't contain all options!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetGetSurveyTestingPlanIdBadRequest() {
    getSurveyTestingPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetSurveyTestingPlanIdNotFound() {
    getSurveyTestingPlanId(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetSurveyTestingPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSurveyTestingPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSurveyTestingPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSurveyTestingPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSurveyTestingPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_SURVEY_TESTING_PLAN_ID);
  }

  private List<String> getSurveyOptionsList() {
    List<String> surveyOptionsList = new ArrayList<>();
    for (LanguageEnum language : LanguageEnum.values()) {
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_ONE,
              language));
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_SIX,
              language));
    }
    return surveyOptionsList;
  }

  private List<String> getSurveyQuestionsList() {
    List<String> surveyQuestionsList = new ArrayList<>();
    for (LanguageEnum language : LanguageEnum.values()) {
      surveyQuestionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_QUESTION,
              language));
    }
    return surveyQuestionsList;
  }
}
