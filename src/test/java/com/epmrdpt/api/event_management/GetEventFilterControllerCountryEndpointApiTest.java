package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_FILTER_CONTROLLER_COUNTRY;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static java.lang.String.format;

import com.epmrdpt.bo.Country;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-111025 Verify 'GET /api/event-management/filter/countries' endpoint",
    groups = {"api"})

public class GetEventFilterControllerCountryEndpointApiTest {

  private int minExpectedCountryListSize = 2;
  private int minExpectedCityListSize = 1;

  @Test(priority = 1)
  public void verifyThatGetCountryStatusOk() {
    SoftAssert softAssert = new SoftAssert();

    List<Country> actualCountriesList =
        getCountry()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<List<Country>>() {
            });

    softAssert.assertTrue(actualCountriesList.size() >= minExpectedCountryListSize,
        format("The size of the country list is less than %s!", minExpectedCountryListSize));
    softAssert.assertTrue(actualCountriesList.stream()
            .allMatch(country -> country.getId() > 0 && !country.getName().isEmpty()),
        "Country list contains invalid data!");

    Country chosenCountry = actualCountriesList.get(minExpectedCountryListSize - 1);
    softAssert.assertTrue(chosenCountry.getCities().size() >= minExpectedCityListSize,
        format("The size of the city list for the country is less than %s!",
            minExpectedCityListSize));
    softAssert.assertTrue(chosenCountry.getCities().stream()
            .allMatch(city -> city.getId() > 0 && !city.getName().isEmpty()),
        "City list contains invalid data!");

    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyThatGetCountryUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getCountry()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyThatGetCountryForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getCountry()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getCountry() {
    return given()
        .when()
        .get(EVENT_MANAGEMENT_FILTER_CONTROLLER_COUNTRY);
  }
}
