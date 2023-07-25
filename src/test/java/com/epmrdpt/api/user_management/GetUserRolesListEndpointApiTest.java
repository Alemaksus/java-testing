package com.epmrdpt.api.user_management;

import static com.epmrdpt.api.Endpoints.USER_MANAGEMENT_ROLES_LIST;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.http.ContentType.JSON;

import com.epmrdpt.bo.user_management.Role.RoleNames;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-103781 Verify 'GET /api/user-management/roles-list' endpoint",
    groups = {"api"})
public class GetUserRolesListEndpointApiTest {

  @Test(priority = 1)
  public void verifyGetRolesListOk() {
    List<String> userRoles = getRolesList()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(JSON)
        .extract()
        .body().jsonPath().get("Name");
    Assert.assertEquals(userRoles,
        Stream.of(RoleNames.values()).map(RoleNames::getRoleName).collect(Collectors.toList()),
        "Role name list doesn't contain all roles!");
  }

  @Test(priority = 2)
  public void verifyGetRolesListUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getRolesList()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetRolesLIstForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getRolesList()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getRolesList() {
    return given()
        .when()
        .get(USER_MANAGEMENT_ROLES_LIST);
  }
}
