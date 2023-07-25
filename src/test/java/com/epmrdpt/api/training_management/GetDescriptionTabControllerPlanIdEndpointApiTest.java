package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_DESCRIPTION_TAB_CONTROLLER_PLAN_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.description_tab.DescriptionTabInfo;
import com.epmrdpt.bo.description_tab.LocalizedProperty;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106593 Verify 'GET /api/training-management/plan/description/{planId}' endpoint",
    groups = {"api"})
public class GetDescriptionTabControllerPlanIdEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 888;
  private final String notExistPlanId = "PlanId";
  private final List<String> expectedRecommendedTrainingsNamesList = Arrays.asList(
      ".NET",
      "JavaScript Training");
  private final String expectedMetaTagTitle = "AutoTest_MetaTagTitle";
  private final String expectedMetaTagDescription = "AutoTest_MetaTagDescription";
  private final String expectedMetaTagKeywords = "AutoTest_MetaTagKeywords";
  private final String expectedLocation = "AutoTest_ExpectedLocation";
  private final String expectedFrequency = "AutoTest_ExpectedFrequency";
  private final List<String> expectedDescriptionBlockTypeNamesList = Arrays.asList(
      "TrainingDescription",
      "SkillDescription",
      "RequiredSkills",
      "NotMeetRequirements",
      "ChooseTraining",
      "TrainingProgram",
      "HowToJoin",
      "PaidConsultation",
      "VideoLink",
      "RecommendedPlansDescription");
  private final List<String> expectedDescriptionBlockColorsList = Arrays.asList(
      "#ffffff",
      "#ffc000",
      "#ffffff",
      "#ffffff",
      "#cedb56",
      "#008ace",
      "#76cdd8",
      "#ffffff",
      "#ffffff",
      "#ffffff");
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetDescriptionPlanIdStatusOk() {
    softAssert = new SoftAssert();
    DescriptionTabInfo descriptionTabInfo = getDescriptionPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(DescriptionTabInfo.class);
    LocalizedProperty localizedPropertyEnUs = Arrays.stream(
        descriptionTabInfo.getLocalizedProperties()).findFirst().get();
    List<String> actualRecommendedTrainingsNamesList = Arrays.stream(
        descriptionTabInfo.getRecommendedTrainings()).map(e -> e.getName()).collect(
        Collectors.toList());
    List<String> actualDescriptionBlockTypeNamesList = Arrays.stream(
        descriptionTabInfo.getDescriptionBlocks()).map(e -> e.getType()).collect(
        Collectors.toList());
    List<String> actualDescriptionBlockColorsList = Arrays.stream(
        descriptionTabInfo.getDescriptionBlocks()).map(e -> e.getColor()).collect(
        Collectors.toList());
    softAssert.assertEquals(localizedPropertyEnUs.getMetaTitle(), expectedMetaTagTitle,
        "Actual meta tag title is not equal to expected meta tag title!");
    softAssert.assertEquals(localizedPropertyEnUs.getMetaDescription(), expectedMetaTagDescription,
        "Actual meta tag description is not equal to expected meta tag description!");
    softAssert.assertEquals(localizedPropertyEnUs.getMetaKeywords(), expectedMetaTagKeywords,
        "Actual meta tag keywords is not equal to expected meta tag keywords!");
    softAssert.assertEquals(localizedPropertyEnUs.getExpectedLocation(), expectedLocation,
        "Actual location is not equal to expected location!");
    softAssert.assertEquals(localizedPropertyEnUs.getExpectedFrequency(), expectedFrequency,
        "Actual frequency is not equal to expected frequency!");
    softAssert.assertEquals(actualRecommendedTrainingsNamesList,
        expectedRecommendedTrainingsNamesList,
        "Actual recommended trainings names list is not equal to expected recommended trainings names!");
    softAssert.assertEquals(actualDescriptionBlockTypeNamesList,
        expectedDescriptionBlockTypeNamesList,
        "Actual description block type names list is not equal to expected description block type names!");
    softAssert.assertEquals(actualDescriptionBlockColorsList, expectedDescriptionBlockColorsList,
        "Actual description block colors list is not equal to expected description block colors!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetDescriptionPlanIdBadRequest() {
    getDescriptionPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetDescriptionPlanIdNotFound() {
    getDescriptionPlanId(notExistPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetDescriptionPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getDescriptionPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetDescriptionPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getDescriptionPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getDescriptionPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_DESCRIPTION_TAB_CONTROLLER_PLAN_ID);
  }
}
