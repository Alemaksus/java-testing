package com.epmrdpt.api.user_management;

import static com.epmrdpt.api.Endpoints.USER_MANAGEMENT_FILTER;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.http.ContentType.JSON;

import com.epmrdpt.bo.user_management.Filter;
import com.epmrdpt.bo.user_management.Role.RoleNames;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-102789 Verify 'GET /api/user-management/filter' endpoint",
    groups = {"api"})
public class GetUserFilterEndpointApiTest {

  private final String expectedCountryName = "AutoTestCountry";
  private final int autoTestCountryId = 272;
  private SoftAssert softAssert;


  @Test(priority = 1)
  public void verifyGetFilterOk() {
    softAssert = new SoftAssert();
    Filter userRoles = given()
        .when()
        .get(USER_MANAGEMENT_FILTER)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(JSON)
        .extract()
        .body()
        .as(Filter.class);
    softAssert.assertEquals(userRoles.getCountryNameById(autoTestCountryId), expectedCountryName,
        "Actual country name is not equal to expected!");
    softAssert.assertEquals(userRoles.getRoleNamesList(),
        Stream.of(RoleNames.values()).map(RoleNames::getRoleName).collect(Collectors.toList()),
        "Role name list doesn't contain all roles!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetFilterUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    given()
        .when()
        .get(USER_MANAGEMENT_FILTER)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetFilterForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    given()
        .when()
        .get(USER_MANAGEMENT_FILTER)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }
}
