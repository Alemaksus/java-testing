package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_CURRENCY;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106316 Verify 'GET /api/training-management/plan/general/currency' endpoint",
    groups = {"api"})
public class GetGeneralTabCurrencyEndpointApiTest {

  private final List<String> expectedCurrencyNamesList = Arrays.asList(
      "BYN",
      "RUB",
      "USD",
      "EUR",
      "PLN",
      "KZT",
      "AMD",
      "GEL",
      "HUF",
      "MXN",
      "COP",
      "NewTest");

  @Test(priority = 1)
  public void verifyGetCurrencyStatusOk() {
    List<String> actualCurrencyNamesList = getCurrency()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    assertTrue(expectedCurrencyNamesList.containsAll(actualCurrencyNamesList),
        "Expected currency names list doesn't contain actual currency names list!");
  }

  @Test(priority = 2)
  public void verifyGetCurrencyUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getCurrency()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetCurrencyForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getCurrency()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getCurrency() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_CURRENCY);
  }
}
