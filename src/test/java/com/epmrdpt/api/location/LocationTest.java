package com.epmrdpt.api.location;

import static com.epmrdpt.api.Endpoints.LOCATIONS_ADDRESSES;
import static com.epmrdpt.api.Endpoints.LOCATIONS_ALL_COUNTRIES;
import static com.epmrdpt.api.Endpoints.LOCATIONS_CITIES_ID_UNIVERSITIES;
import static com.epmrdpt.api.Endpoints.LOCATIONS_COUNTRIES;
import static com.epmrdpt.api.Endpoints.LOCATIONS_CURRENT_USER_LOCATION;
import static com.epmrdpt.api.Endpoints.LOCATIONS_GET_DOMAIN_COUNTRY_ID;
import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "Verify 'Location'",
    groups = {"api_deprecated"})
public class LocationTest {

  private static int id = 1;

  @DataProvider(name = "Data provider with location end points")
  private static Object[][] dataProviderWithLocationEndPoints() {
    return new Object[][]{
        {LOCATIONS_COUNTRIES},
        {LOCATIONS_ALL_COUNTRIES},
        {LOCATIONS_ADDRESSES},
        {String.format(LOCATIONS_CITIES_ID_UNIVERSITIES, id)},
        {LOCATIONS_CURRENT_USER_LOCATION},
        {LOCATIONS_GET_DOMAIN_COUNTRY_ID}
    };
  }

  @Test(dataProvider = "Data provider with location end points")
  public void verifyLocationEndPoint(String endPoint) {
    given()
        .when()
        .get(endPoint)
        .then()
        .statusCode(200);
  }
}
