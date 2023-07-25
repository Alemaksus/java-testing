package com.epmrdpt.api.user_management;

import static com.epmrdpt.api.Endpoints.USER_MANAGEMENT_INFO;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user_management.UserInfo;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-103693 Verify 'GET /api/user-management/{userId}/info' endpoint",
    groups = {"api"})
public class GetUserManagementInfoApiTest {

  private final int testUserId = 325944;
  private final int invalidUserId = 0;
  private final String incorrectUserId = "userId";

  @Test(priority = 1)
  public void verifyGetUserInfoOk() {
    UserInfo userInfo = getUserInfo(testUserId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract().body().jsonPath().getObject("UserInfo", UserInfo.class);
    assertEquals(userInfo, getUserInfoForTest(), "UserInfo is not equal to expected response!");
  }

  @Test(priority = 1)
  public void verifyGetUserRolesBadRequest() {
    getUserInfo(invalidUserId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 1)
  public void verifyGetUserRolesNotFound() {
    getUserInfo(incorrectUserId)
        .then()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test(priority = 2)
  public void verifyGetUserRolesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();

    getUserInfo(testUserId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetUserRolesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();

    getUserInfo(testUserId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getUserInfo(Object userId) {
    return given()
        .pathParam("userId", userId)
        .when()
        .get(USER_MANAGEMENT_INFO);
  }

  private UserInfo getUserInfoForTest() {
   return new UserInfo()
       .withId(325944)
       .withFirstName("AutoTest")
       .withLastName("SuperAdmin")
       .withNativeName("PiotrPechenkin")
       .withEmail("atsafrdpt@gmail.com")
       .withPhoneNumber("+3751111111")
       .withCountryName("AutoTestCountry")
       .withCityName("AutoTestCity")
       .withPhotoId(null);
  }
}
