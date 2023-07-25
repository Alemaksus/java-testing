package com.epmrdpt.api.training_center;

import static com.epmrdpt.api.Endpoints.TRAINING_CENTER_MANAGEMENT;
import static com.epmrdpt.api.Endpoints.TRAINING_CENTER_MANAGEMENT_CENTERS_LIGHT;
import static com.epmrdpt.api.Endpoints.TRAINING_CENTER_MANAGEMENT_COUNTRIES_WITH_CITIES;
import static com.epmrdpt.api.Endpoints.TRAINING_CENTER_MANAGEMENT_IMAGE;
import static com.epmrdpt.api.Endpoints.TRAINING_CENTER_MANAGEMENT_PUBLISHED_CENTERS;
import static com.epmrdpt.api.Endpoints.TRAINING_CENTER_MANAGEMENT_STATUSES;
import static io.restassured.RestAssured.given;

import com.epmrdpt.framework.properties.ApiPropertyService;
import com.epmrdpt.framework.properties.ApiProperty;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "Verify 'TrainingCenter'",
    groups = {"api_deprecated"})
public class TrainingCenterTest {

  private static int id = Integer.parseInt(ApiPropertyService.getValueOf(ApiProperty.TRAINING_CENTER_ID));

  @DataProvider(name = "Data provider with training center end points")
  private static Object[][] dataProviderWithTrainingCenterEndPoints() {
    return new Object[][]{
        {TRAINING_CENTER_MANAGEMENT_STATUSES},
        {TRAINING_CENTER_MANAGEMENT_PUBLISHED_CENTERS},
        {TRAINING_CENTER_MANAGEMENT_CENTERS_LIGHT},
        {TRAINING_CENTER_MANAGEMENT_COUNTRIES_WITH_CITIES},
        {TRAINING_CENTER_MANAGEMENT + "/" + id},
        {String.format(TRAINING_CENTER_MANAGEMENT_IMAGE, id)}
    };
  }

  @Test(dataProvider = "Data provider with training center end points")
  public void verifyTrainingCenterEndPoint(String endPoint) {
    given()
        .when()
        .get(endPoint)
        .then()
        .statusCode(200);
  }
}
