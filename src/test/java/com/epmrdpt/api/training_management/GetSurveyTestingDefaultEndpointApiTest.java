package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_SURVEY_TESTING_DEFAULT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_FIVE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_FOUR;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_ONE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_SEVEN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_SIX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_THREE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_TWO;
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

@Test(description = "EPMRDPT-103831 Verify 'GET /api/training-management/plan/survey-testing/default' endpoint",
    groups = {"api"})

public class GetSurveyTestingDefaultEndpointApiTest {

  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetSurveyTestingDefaultStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getSurveyTestingDefault()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<List<String>> surveyQuestionsList = jsonPath.get("LocalizedQuestion.Question");
    List<List<List<String>>> surveyOptionsList = jsonPath.get("LocalizedQuestion.Options.Name");
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

  @Test(priority = 2)
  public void verifyGetSurveyTestingDefaultUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSurveyTestingDefault()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSurveyTestingDefaultForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSurveyTestingDefault()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSurveyTestingDefault() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_SURVEY_TESTING_DEFAULT);
  }

  private List<String> getSurveyOptionsList() {
    List<String> surveyOptionsList = new ArrayList<>();
    for (LanguageEnum language : LanguageEnum.values()) {
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_ONE,
              language));
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_TWO,
              language));
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_THREE,
              language));
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_FOUR,
              language));
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_FIVE,
              language));
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_SIX,
              language));
      surveyOptionsList.add(
          LocaleProperties.getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_OPTION_SEVEN,
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
