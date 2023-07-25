package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_LOCATION;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106345 Verify 'GET /api/training-management/plan/general/location' endpoint",
    groups = {"api"})
public class GetGeneralTabLocationEndpointApiTest {

  private final static String AUTO_TEST_COUNTRY = "AutoTestCountry";

  @Test(priority = 1)
  public void verifyGetLocationStatusOk() {
    List<String> locationNamesList =
        getLocation()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get("Name");
    assertTrue(locationNamesList.stream().anyMatch(el -> el.equals(AUTO_TEST_COUNTRY)),
        String.format("Location Name='%s' is not present in location names list!",
            AUTO_TEST_COUNTRY));
  }

  @Test(priority = 2)
  public void verifyGetLocationUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getLocation()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetLocationForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getLocation()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getLocation() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_LOCATION);
  }
}
