package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PERMITTED_LOCATIONS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106347 Verify 'GET /api/training-management/plan/general/permitted-locations' endpoint",
    groups = {"api"})
public class GetGeneralTabPermittedLocationsEndpointApiTest {

  private final int expectedCountryId = 272;
  private final int expectedCityId = 711;

  @Test(priority = 1)
  public void verifyGetPermittedLocationsStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    JsonPath jsonPath = getPermittedLocations()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    softAssert.assertTrue(
        jsonPath.getList("CountryId").stream().filter(e -> e != null)
            .anyMatch(e -> e.equals(expectedCountryId)),
        "Actual countryIds list doesn't contain expected country Id!");
    softAssert.assertTrue(
        jsonPath.getList("CityId").stream().filter(e -> e != null)
            .anyMatch(e -> e.equals(expectedCityId)),
        "Actual cityIds list doesn't contain expected city Id!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetPermittedLocationsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getPermittedLocations()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetPermittedLocationsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getPermittedLocations()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getPermittedLocations() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PERMITTED_LOCATIONS);
  }
}
