package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_USER_DATA;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-110381 Verify 'GET /api/event-management/user/data' endpoint",
    groups = {"api"})
public class GetEventUserControllerUserDataEndpointApiTest {

  private final int userId = 325944;
  private final int invalidUserId = 0;
  private final String expectedFirstName = "AutoTest";
  private final String expectedLastName = "SuperAdmin";
  private final String expectedEmail = "atsafrdpt@gmail.com";
  private final String expectedContactPhone = "+3751111111";
  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetUserDataOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getUserData(userId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    softAssert.assertEquals(jsonPath.get("FirstName"), expectedFirstName,
        "Actual First Name is not equal to expected!");
    softAssert.assertEquals(jsonPath.get("LastName"), expectedLastName,
        "Actual Last Name is not equal to expected!");
    softAssert.assertEquals(jsonPath.get("Email"), expectedEmail,
        "Actual Email is not equal to expected!");
    softAssert.assertEquals(jsonPath.get("ContactPhone"), expectedContactPhone,
        "Actual Contact Phone is not equal to expected!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetUserDataBadRequest() {
    getUserData(invalidUserId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetUserDataUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getUserData(userId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetUserDataForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getUserData(userId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getUserData(int userId) {
    return given()
        .queryParam("userId", userId)
        .when()
        .get(EVENT_MANAGEMENT_USER_DATA);
  }
}
