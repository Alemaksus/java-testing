package com.epmrdpt.api.applications;

import static com.epmrdpt.api.Endpoints.APPLICATIONS;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Applications'",
    groups = {"api_deprecated"})
public class ApplicationsTest {

  @Test
  public void verifyApplications() {
    given()
        .when()
        .get(APPLICATIONS)
        .then()
        .statusCode(200);
  }
}
