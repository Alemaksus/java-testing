package com.epmrdpt.api.news;

import static com.epmrdpt.api.Endpoints.NEWS_GET_ALL;
import static com.epmrdpt.api.Endpoints.NEWS_GET_ALL_NEWS_CATEGORIES;
import static com.epmrdpt.api.Endpoints.NEWS_GET_AUTHORS;
import static com.epmrdpt.api.Endpoints.NEWS_GET_CATEGORIES_WITH_NEWS;
import static com.epmrdpt.api.Endpoints.NEWS_GET_IMAGE_NAMES;
import static com.epmrdpt.api.Endpoints.NEWS_GET_NEWS_ID;
import static com.epmrdpt.api.Endpoints.NEWS_GET_NEWS_IMAGE_ID_IMAGE_NAME;
import static com.epmrdpt.api.Endpoints.NEWS_ID_LIGHT;
import static com.epmrdpt.api.Endpoints.NEWS_PREVIEW;
import static io.restassured.RestAssured.given;

import com.epmrdpt.framework.properties.ApiProperty;
import com.epmrdpt.framework.properties.ApiPropertyService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "Verify 'News'",
    groups = {"api_deprecated"})
public class NewsTest {

  private static int id = Integer.parseInt(ApiPropertyService.getValueOf(ApiProperty.NEWS_ID));
  private static String imageName = ApiPropertyService.getValueOf(ApiProperty.NEWS_IMAGE_NAME);

  @DataProvider(name = "Data provider with news end points")
  private static Object[][] dataProviderWithNewsEndPoints() {
    return new Object[][]{
        {NEWS_PREVIEW},
        {String.format(NEWS_ID_LIGHT, id)},
        {NEWS_GET_ALL},
        {NEWS_GET_AUTHORS},
        {NEWS_GET_CATEGORIES_WITH_NEWS},
        {NEWS_GET_ALL_NEWS_CATEGORIES},
        {String.format(NEWS_GET_NEWS_ID, id)},
        {String.format(NEWS_GET_NEWS_IMAGE_ID_IMAGE_NAME, id, imageName)},
        {NEWS_GET_IMAGE_NAMES + "?newsId=" + id}
    };
  }

  @Test(dataProvider = "Data provider with news end points")
  public void verifyNewsEndPoint(String endPoint) {
    given()
        .when()
        .get(endPoint)
        .then()
        .statusCode(200);
  }
}
