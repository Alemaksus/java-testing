package com.epmrdpt.api.user_access;

import static com.epmrdpt.api.Endpoints.USER_ACCESS_HEADER_LINKS;
import static com.epmrdpt.api.Endpoints.USER_ACCESS_USER_ROLE;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'UserAccess'",
    groups = {"api_deprecated"})
public class UserAccessTest {

  @Test
  public void verifyHeaderLinks() {
    given()
        .when()
        .get(USER_ACCESS_HEADER_LINKS)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyUserRole() {
    given()
        .when()
        .get(USER_ACCESS_USER_ROLE)
        .then()
        .statusCode(200);
  }
}
