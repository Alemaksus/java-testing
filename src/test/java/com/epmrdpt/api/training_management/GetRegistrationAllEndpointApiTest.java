package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_All;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_CUSTOM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_DEPARTMENT_AFFILIATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_EDUCATIONAL_PRACTICE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_SIMPLIFIED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_STANDARD;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.LocaleProperties;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-104689 Verify 'GET /api/training-management/plan/registration/all' endpoint",
    groups = {"api"})

public class GetRegistrationAllEndpointApiTest {

  @Test(priority = 1)
  public void verifyGetRegistrationAllStatusOk() {
    List<String> actualRegistrationTemplatesList = getRegistrationAll()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Template");
    Assert.assertEquals(actualRegistrationTemplatesList, getRegistrationTemplatesList(),
        "Actual registration templates list doesn't contain all templates!");
  }

  @Test(priority = 2)
  public void verifyGetRegistrationAllUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getRegistrationAll()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetRegistrationAllForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getRegistrationAll()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getRegistrationAll() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_All);
  }

  private List<String> getRegistrationTemplatesList() {
    List<String> registrationTemplatesList = new ArrayList<>();
    registrationTemplatesList.add(
        LocaleProperties.getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_STANDARD));
    registrationTemplatesList.add(
        LocaleProperties.getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_SIMPLIFIED));
    registrationTemplatesList.add(
        LocaleProperties.getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_CUSTOM));
    registrationTemplatesList.add(
        LocaleProperties.getValueOf(
            REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_DEPARTMENT_AFFILIATE));
    registrationTemplatesList.add(
        LocaleProperties.getValueOf(
            REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_EDUCATIONAL_PRACTICE));
    return registrationTemplatesList;
  }
}
