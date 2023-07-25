package com.epmrdpt.api.department_affiliate;

import static com.epmrdpt.api.Endpoints.DEPARTMENT_AFFILIATE_USER_DATA;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Department Affiliate'",
    groups = {"api_deprecated"})
public class DepartmentAffiliateTest {

  private final int planId = 1;

  @Test
  public void verifyUserData() {
    given()
        .queryParam("planId", planId)
        .when()
        .get(DEPARTMENT_AFFILIATE_USER_DATA)
        .then()
        .statusCode(200);
  }
}
