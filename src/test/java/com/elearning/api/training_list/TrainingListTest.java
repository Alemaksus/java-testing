package com.epmrdpt.api.training_list;

import static com.epmrdpt.api.Endpoints.TRAININGS_ACTIVE;
import static com.epmrdpt.api.Endpoints.TRAININGS_V1_DETAILS;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.requestSpecification;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.internal.RequestSpecificationImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "Verify Training List", groups = {"api", "full", "smoke"})
public class TrainingListTest {

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupSpecification() {
    requestSpecification = given()
        .relaxedHTTPSValidation()
        .and()
        .baseUri(getEnv());
    RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test(description = "Critical API test! If the test fails, contact Vasili Lysau")
  public void verifyTrainingListPositiveScenario() {
    List<Integer> activeIds =
        given()
            .when()
            .get(TRAININGS_ACTIVE)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(JSON)
            .extract().body().jsonPath().getList("Id", Integer.class)
            .stream().sorted().collect(Collectors.toList());
    ((RequestSpecificationImpl) requestSpecification).removeHeader("Authorization");
    List<Integer> detailsIds =
        given()
            .header("Authorization", "awInternalTest")
            .when()
            .get(TRAININGS_V1_DETAILS)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(JSON)
            .extract().body().jsonPath().getList("Id", Integer.class)
            .stream().sorted().collect(Collectors.toList());

    assertEquals(detailsIds, activeIds, "Responses are not equal!");
  }

  @Test(description = "Critical API test! If the test fails, contact Vasili Lysau")
  public void verifyTrainingsDetailsUnauthorized() {
    given()
        .header("Authorization", "wrongkey")
        .when()
        .get(TRAININGS_V1_DETAILS)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }
}
