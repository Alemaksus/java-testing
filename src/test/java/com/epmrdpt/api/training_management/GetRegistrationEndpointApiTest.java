package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_CUSTOM_TEMPLATE_FIELD_NAMES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_DEPARTMENT_AFFILIATE_TEMPLATE_FIELD_NAMES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_EDUCATIONAL_PRACTICE_TEMPLATE_FIELD_NAMES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_SIMPLIFIED_TEMPLATE_FIELD_NAMES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_STANDARD_TEMPLATE_FIELD_NAMES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_CUSTOM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_DEPARTMENT_AFFILIATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_EDUCATIONAL_PRACTICE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_SIMPLIFIED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_STANDARD;
import static com.epmrdpt.utils.StringUtils.getListOfStringValues;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.LocaleProperties;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-104690 Verify 'GET /api/training-management/plan/registration' endpoint",
    groups = {"api"})

public class GetRegistrationEndpointApiTest {

  private String endPoint;
  private List<String> expectedTemplateNames;

  @Factory(dataProvider = "Data provider with registration endPoints and field names")
  public GetRegistrationEndpointApiTest(String endPoint, List<String> expectedTemplateNames) {
    this.endPoint = endPoint;
    this.expectedTemplateNames = expectedTemplateNames;
  }

  @DataProvider(name = "Data provider with registration endPoints and field names")
  private static Object[][] dataProviderWithLocationEndPoints() {
    return new Object[][]{
        {String.format(TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION,
            LocaleProperties.getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_STANDARD)),
            getListOfStringValues(LocaleProperties.getValueOf(
                REACT_REGISTRATION_FORM_TAB_SCREEN_STANDARD_TEMPLATE_FIELD_NAMES))},
        {String.format(TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION,
            LocaleProperties.getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_SIMPLIFIED)),
            getListOfStringValues(LocaleProperties.getValueOf(
                REACT_REGISTRATION_FORM_TAB_SCREEN_SIMPLIFIED_TEMPLATE_FIELD_NAMES))},
        {String.format(TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION,
            LocaleProperties.getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_CUSTOM)),
            getListOfStringValues(LocaleProperties.getValueOf(
                REACT_REGISTRATION_FORM_TAB_SCREEN_CUSTOM_TEMPLATE_FIELD_NAMES))},
        {String.format(TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION,
            LocaleProperties.getValueOf(
                REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_DEPARTMENT_AFFILIATE)),
            getListOfStringValues(LocaleProperties.getValueOf(
                REACT_REGISTRATION_FORM_TAB_SCREEN_DEPARTMENT_AFFILIATE_TEMPLATE_FIELD_NAMES))},
        {String.format(TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION,
            LocaleProperties.getValueOf(
                REACT_REGISTRATION_FORM_TAB_SCREEN_TEMPLATE_EDUCATIONAL_PRACTICE)),
            getListOfStringValues(LocaleProperties.getValueOf(
                REACT_REGISTRATION_FORM_TAB_SCREEN_EDUCATIONAL_PRACTICE_TEMPLATE_FIELD_NAMES))},
    };
  }

  @Test(priority = 1)
  public void verifyRegistrationStatusOk() {
    List<List<String>> actualFieldTemplateNames = getRegistration(endPoint)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Fields.Field");
    Assert.assertEquals(
        actualFieldTemplateNames.stream().flatMap(e -> e.stream()).collect(Collectors.toList()),
        expectedTemplateNames,
        "Actual template field names list doesn't equal to expected template field names!");
  }

  @Test(priority = 2)
  public void verifyGetRegistrationUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getRegistration(endPoint)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetRegistrationForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getRegistration(endPoint)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getRegistration(String endpoint) {
    return given()
        .when()
        .get(endpoint);
  }
}
