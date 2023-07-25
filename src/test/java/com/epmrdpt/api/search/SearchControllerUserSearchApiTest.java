package com.epmrdpt.api.search;

import static com.epmrdpt.api.Endpoints.SEARCH_USER_SEARCH;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user_management.UserInfo;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-110376 Verify 'GET /api/user-search' endpoint",
    groups = {"api"})
public class SearchControllerUserSearchApiTest {

  private final String searchTerm = "atsafrdpt@gmail.com";

  @Test(priority = 1)
  public void verifyGetUserSearchStatusOk() {
    List<UserInfo> userInfoList = getUserSearch(searchTerm)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .getList("Items", UserInfo.class);
    assertTrue(userInfoList.contains(getUserInfoForTest()),
        "Actual search result doesn't contain expected user info!");
  }

  @Test(priority = 2)
  public void verifyGetUserSearchUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getUserSearch(searchTerm)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetUserSearchForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getUserSearch(searchTerm)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getUserSearch(String term) {
    return given()
        .queryParam("term", term)
        .when()
        .get(SEARCH_USER_SEARCH);
  }

  private UserInfo getUserInfoForTest() {
    return new UserInfo()
        .withId(325944)
        .withFirstName("AutoTest")
        .withLastName("SuperAdmin")
        .withEmail("atsafrdpt@gmail.com")
        .withPhoneNumber("+3751111111")
        .withPhoneHome(null)
        .withPhoneWork("3751111111");
  }
}
