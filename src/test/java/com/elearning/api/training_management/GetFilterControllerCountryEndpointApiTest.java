package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_FILTER_CONTROLLER_COUNTRY;
import static com.epmrdpt.bo.Country.getCountryNameById;
import static com.epmrdpt.services.CountryContainerService.getCityNamesListByCountryIdFromCountryContainerMap;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.bo.City;
import com.epmrdpt.bo.Country;
import com.epmrdpt.bo.Country.CountryEnum;
import com.epmrdpt.tokens_storage.TokensStorage;
import com.epmrdpt.utils.RandomUtils;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106516 Verify 'GET /api/training-management/filter/country' endpoint",
    groups = {"api"})
public class GetFilterControllerCountryEndpointApiTest {

  private final String NOT_SPECIFIED_CITY_NAME = "Not specified";

  @Test(priority = 1)
  public void verifyGetCountryStatusOk() {

    int countryId = RandomUtils.getRandomEnumValue(CountryEnum.class).getCountryId();
    String expectedCountryName = getCountryNameById(countryId);
    List<String> expectedCityNamesList = getCityNamesListByCountryIdFromCountryContainerMap(countryId);
    expectedCityNamesList.add(NOT_SPECIFIED_CITY_NAME);
    SoftAssert softAssert = new SoftAssert();
    List<Country> actualCountriesList =
        getCountry()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<List<Country>>() {
            });
    List<String> actualCityNamesList = actualCountriesList.stream()
        .filter(e -> e.getId() == countryId)
        .flatMap(e -> e.getCities().stream()).map(City::getName).collect(Collectors.toList());
    String actualCountryName = actualCountriesList.stream().filter(e -> e.getId() == countryId)
        .findFirst().get().getName();
    softAssert.assertTrue(expectedCityNamesList.containsAll(actualCityNamesList),
        String.format(
            "Actual city names list doesn't equal to expected city names!, 'expected :'%s' , but found %s",
            expectedCityNamesList, actualCityNamesList));
    softAssert.assertEquals(actualCountryName, expectedCountryName,
        "Actual country name list doesn't equal to expected country name!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetCountryUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getCountry()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetCountryForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getCountry()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getCountry() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_FILTER_CONTROLLER_COUNTRY);
  }
}
