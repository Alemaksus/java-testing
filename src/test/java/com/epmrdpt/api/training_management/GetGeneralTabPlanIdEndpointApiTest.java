package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PLAN_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106325 Verify 'GET /api/training-management/plan/general/{planId}' endpoint",
    groups = {"api"})
public class GetGeneralTabPlanIdEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 888;
  private final String notExistPlanId = "PlanId";
  private final int expectedTypeId = 1;
  private final String expectedName = "AutoTest_LearningStudentsWorkflow";
  private final String expectedOwner = "ManagerTest ManagerTest";
  private final int expectedSkillId = 4;
  private final String expectedEnrollmentType = "SelfEnrollment";
  private final String expectedProgramLanguage = "English";
  private final String expectedTrainingStartDate = "2020-11-11T00:00:00Z";
  private final String expectedPricingType = "Free";
  private final String expectedFormat = "Online";
  private final String expectedLevel= "Intermediate";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetGeneralPlanIdStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getGeneralPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    softAssert.assertEquals(jsonPath.getInt("TypeId"), expectedTypeId,
        "Actual training type Id is not equal to expected type Id!");
    softAssert.assertEquals(jsonPath.getString("Name"), expectedName,
        "Actual training name is not equal to expected name!");
    softAssert.assertEquals(jsonPath.getString("Owner.DisplayName"), expectedOwner,
        "Actual training owner is not equal to expected owner!");
    softAssert.assertEquals(jsonPath.getInt("SkillId"), expectedSkillId,
        "Actual training skill Id is not equal to expected skill Id!");
    softAssert.assertEquals(jsonPath.getString("EnrollmentType"), expectedEnrollmentType,
        "Actual training enrollment type is not equal to expected enrollment typ!");
    softAssert.assertTrue(jsonPath.getList("ProgramLanguages").contains(expectedProgramLanguage),
        "Actual program language list doesn't contain expected program language!");
    softAssert.assertEquals(jsonPath.getString("Start"), expectedTrainingStartDate,
        "Actual training start date is not equal to expected start date!");
    softAssert.assertEquals(jsonPath.getString("PricingType"), expectedPricingType,
        "Actual training pricing type is not equal to expected pricing type!");
    softAssert.assertEquals(jsonPath.getString("Format"), expectedFormat,
        "Actual training format is not equal to expected format!");
    softAssert.assertEquals(jsonPath.getString("Level"), expectedLevel,
        "Actual training level is not equal to expected level!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetGeneralPlanIdBadRequest() {
    getGeneralPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetGeneralPlanIdNotFound() {
    getGeneralPlanId(notExistPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetGeneralPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getGeneralPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetGeneralPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getGeneralPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getGeneralPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PLAN_ID);
  }
}
