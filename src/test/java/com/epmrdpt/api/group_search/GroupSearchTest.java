package com.epmrdpt.api.group_search;

import static com.epmrdpt.api.Endpoints.GROUP_SEARCH_FILTER_DATA;
import static com.epmrdpt.api.Endpoints.LOCATIONS_CITIES;
import static com.epmrdpt.services.CountryContainerService.getCountryContainerMap;
import static com.epmrdpt.utils.RandomUtils.getRandomNumber;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import com.epmrdpt.bo.City;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import rp.org.apache.http.HttpStatus;

@Test(description = "Verify 'GroupSearch'",
    groups = {"api_deprecated"})
public class GroupSearchTest {

  @Test
  public void verifyFilterData() {
    given()
        .when()
        .get(GROUP_SEARCH_FILTER_DATA)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(JSON);
  }

  @Test
  public void verifyCities() {
    Map<Integer, String> countryContainerMap = getCountryContainerMap();
    ArrayList<Integer> countryIds = new ArrayList<>(countryContainerMap.keySet());
    int randomIdsCountry = countryIds.get(getRandomNumber(countryIds.size() - 1));
    List<City> actualCityList = new ArrayList<>(Arrays.asList(given()
        .when()
        .pathParam("countryId", randomIdsCountry)
        .get(LOCATIONS_CITIES)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .extract()
        .body()
        .as(City[].class)));
    String expectedCity = Arrays.stream(countryContainerMap.get(randomIdsCountry).split(" "))
        .collect(Collectors.toList()).get(1);
    City randomCityExpected = actualCityList.
        get(getRandomNumber(actualCityList.size() - 1));
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        actualCityList.stream().anyMatch(city -> city.getName().equals(expectedCity)),
        String.format("Wrong City's Name!, 'expected :'%s' , but found %s",
            expectedCity, actualCityList.stream().map(City::getName).sorted()
                .collect(Collectors.toList())));
    softAssert.assertFalse(randomCityExpected.getId() == 0,
        "City's Id == 0");
    softAssert.assertAll();
  }
}
