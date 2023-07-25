package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_DESCRIPTION_TAB_PLAN_ID_COPY;
import static com.epmrdpt.tokens_storage.TokensStorage.getAdminAuthorizationToken;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.description_tab.DescriptionTabInfo;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_106594 Verify 'GET /api/training-management/plan/description/{planId}/copy' endpoint",
    groups = {"api"})
public class GetDescriptionTabPlanIdCopyEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 0;
  private final String incorrectPlanId = "planId";
  private final List<String> expectedRecommendedTrainingList = Arrays.asList(
      ".NET",
      "JavaScript Training");
  private final List<String> expectedLocalizationList = Arrays.asList(
      "en-US",
      "ru-RU",
      "uk-UA");
  private final List<String> expectedDescriptionBlocksTypeList = Arrays.asList(
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
  SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetDescriptionTabPlanIdCopyStatusOk() {
    softAssert = new SoftAssert();
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken",
        getAdminAuthorizationToken());
    DescriptionTabInfo descriptionTabInfo = getDescriptionPlanIdCopy(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(DescriptionTabInfo.class);
    List<String> actualDescriptionBlocksTypeList =
        Arrays.stream(descriptionTabInfo.getDescriptionBlocks()).map(e -> e.getType()).collect(
            Collectors.toList());
    List<String> actualRecommendedTrainingList =
        Arrays.stream(descriptionTabInfo.getRecommendedTrainings()).map(e -> e.getName()).collect(
            Collectors.toList());
    List<String> actualLocalizationList =
        Arrays.stream(descriptionTabInfo.getLocalizedProperties()).map(e -> e.getCulture()).collect(
            Collectors.toList());
    softAssert.assertNotNull(actualLocalizationList);
    softAssert.assertEquals(actualLocalizationList, expectedLocalizationList,
        "Actual Localization List does not equal to expected Recommended Localization List!");
    softAssert.assertNotNull(actualRecommendedTrainingList);
    softAssert.assertEquals(actualRecommendedTrainingList, expectedRecommendedTrainingList,
        "Actual Recommended Training List does not equal to expected Recommended Training List!");
    softAssert.assertNotNull(actualDescriptionBlocksTypeList);
    softAssert.assertEquals(actualDescriptionBlocksTypeList, expectedDescriptionBlocksTypeList,
        "Actual description block type names does not equal to expected description block type names!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetDescriptionTabPlanIdBadRequest() {
    getDescriptionPlanIdCopy(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 3)
  public void verifyGetDescriptionTabPlanIdNotFound() {
    getDescriptionPlanIdCopy(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 4)
  public void verifyGetDescriptionPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getDescriptionPlanIdCopy(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 5)
  public void verifyGetDescriptionPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getDescriptionPlanIdCopy(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getDescriptionPlanIdCopy(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_DESCRIPTION_TAB_PLAN_ID_COPY);
  }
}
