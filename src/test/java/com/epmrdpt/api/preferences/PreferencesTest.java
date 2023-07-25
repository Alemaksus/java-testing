package com.epmrdpt.api.preferences;

import static com.epmrdpt.api.Endpoints.*;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Preferences'",
    groups = {"api_deprecated"})
public class PreferencesTest {

  @Test
  public void verifyUserData() {
    given()
        .when()
        .get(PREFERENCES_USER_DATA)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyCommonData() {
    given()
        .when()
        .get(PREFERENCES_COMMON_DATA)
        .then()
        .statusCode(200);
  }
}
