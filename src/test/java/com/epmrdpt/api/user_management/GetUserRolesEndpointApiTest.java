package com.epmrdpt.api.user_management;

import static com.epmrdpt.api.Endpoints.USER_MANAGEMENT_ROLES;
import static com.epmrdpt.utils.StringUtils.getJsonStringFromObject;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user_management.UserRole;
import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.framework.properties.ApiProperty;
import com.epmrdpt.framework.properties.ApiPropertyService;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-99947 Verify 'GET /api/user-management/{userId}/roles' endpoint",
    groups = {"api"})
public class GetUserRolesEndpointApiTest {

  private final int testUserId = 325944;

  @Test(priority = 1)
  public void verifyGetUserRolesOk() {
    String expectedResponseBody =
        ApiPropertyService.getValueOf(ApiProperty.RESPONSE_USER_MANAGEMENT_GET_USER_ROLES);

    List<UserRole> userRoles = getUserRoles(testUserId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract().body().as(new TypeRef<List<UserRole>>() {
        })
        .stream().sorted().collect(Collectors.toList());

    assertEquals(getJsonStringFromObject(userRoles), expectedResponseBody,
        "Actual response is not equal to expected response!");
  }

  @Test(priority = 1)
  public void verifyGetUserRolesBadRequest() {
    getUserRoles(-5)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetUserRolesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();

    getUserRoles(testUserId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetUserRolesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();

    getUserRoles(testUserId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  @Test(priority = 1)
  public void verifyGetUserRolesNotFound() {
    getUserRoles("userId")
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  private Response getUserRoles(Object userId) {
    return given()
        .pathParam("userId", userId)
        .when()
        .get(USER_MANAGEMENT_ROLES);
  }
}
