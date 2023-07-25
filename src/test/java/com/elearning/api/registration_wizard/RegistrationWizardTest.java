package com.epmrdpt.api.registration_wizard;

import static com.epmrdpt.api.Endpoints.REGISTRATION_WIZARD;
import static com.epmrdpt.api.Endpoints.REGISTRATION_WIZARD_EDUCATION_DATA;
import static com.epmrdpt.api.Endpoints.REGISTRATION_WIZARD_TRAINING_SURVEY;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Registration Wizard'",
    groups = {"api_deprecated"})
public class RegistrationWizardTest {

  private int planId = 2841;

  @Test
  public void verifyTrainingSurvey() {
    given()
        .when()
        .get(REGISTRATION_WIZARD_TRAINING_SURVEY + "/" + planId)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyEducationData() {
    given()
        .when()
        .get(REGISTRATION_WIZARD_EDUCATION_DATA)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyRegistrationWizard() {
    given()
        .queryParam("planId", planId)
        .when()
        .get(REGISTRATION_WIZARD)
        .then()
        .statusCode(200);
  }
}
