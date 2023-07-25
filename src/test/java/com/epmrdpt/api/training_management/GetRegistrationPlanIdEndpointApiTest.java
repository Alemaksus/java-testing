package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION_PLAN_ID;
import static com.epmrdpt.tokens_storage.TokensStorage.getAdminAuthorizationToken;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_STANDARD_TEMPLATE_FIELD_NAMES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_STANDARD;
import static com.epmrdpt.utils.StringUtils.getListOfStringValues;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.LocaleProperties;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-104692 Verify 'GET /api/training-management/plan/registration/{planId}' endpoint",
    groups = {"api"})

public class GetRegistrationPlanIdEndpointApiTest {

  private final int planId = 2840;
  private final int invalidPlanId = 0;
  private final String incorrectPlanId = "planId";
  private final String expectedTrainingTemplate = LocaleProperties.getValueOf(
      REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_STANDARD);
  private final List<String> expectedTemplateFieldsList = getListOfStringValues(
      LocaleProperties.getValueOf(
          REACT_REGISTRATION_FORM_TAB_SCREEN_STANDARD_TEMPLATE_FIELD_NAMES));
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetRegistrationPlanIdStatusOk() {
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getAdminAuthorizationToken());
    softAssert = new SoftAssert();
    JsonPath jsonPath = getRegistrationPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    String actualTrainingTemplate = jsonPath.get("Template");
    List<String> actualTemplateFieldsList = jsonPath.get("Fields.Field");
    softAssert.assertEquals(actualTrainingTemplate, expectedTrainingTemplate,
        "Actual training template is not equal to expected template!");
    softAssert.assertEquals(actualTemplateFieldsList, expectedTemplateFieldsList,
        "Actual template field names list doesn't equal to expected template field names!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetRegistrationPlanIdBadRequest() {
    getRegistrationPlanId(invalidPlanId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 3)
  public void verifyGetRegistrationPlanIdNotFound() {
    getRegistrationPlanId(incorrectPlanId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 4)
  public void verifyGetRegistrationPlanIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeCookies();
    getRegistrationPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 5)
  public void verifyGetRegistrationPlanIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getRegistrationPlanId(planId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getRegistrationPlanId(Object planId) {
    return given()
        .pathParam("planId", planId)
        .when()
        .get(TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION_PLAN_ID);
  }
}
