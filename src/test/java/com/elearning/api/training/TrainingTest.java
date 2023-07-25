package com.epmrdpt.api.training;

import static com.epmrdpt.api.Endpoints.TRAININGS_ACTIVE;
import static com.epmrdpt.api.Endpoints.TRAININGS_CHECK_STATUS;
import static com.epmrdpt.api.Endpoints.TRAININGS_DISABLED;
import static com.epmrdpt.api.Endpoints.TRAININGS_FREEMIUM;
import static com.epmrdpt.api.Endpoints.TRAININGS_ID_DETAILS_USER;
import static com.epmrdpt.api.Endpoints.TRAININGS_ID_PARTIAL_DETAILS;
import static com.epmrdpt.api.Endpoints.TRAININGS_ID_USER;
import static com.epmrdpt.api.Endpoints.TRAININGS_INTEGRATIONS_INFO;
import static com.epmrdpt.api.Endpoints.TRAININGS_PLAN_FILTER_DATA;
import static com.epmrdpt.api.Endpoints.TRAININGS_RECOMMENDED_PLANS;
import static com.epmrdpt.api.Endpoints.TRAININGS_REGISTERED;
import static com.epmrdpt.api.Endpoints.TRAININGS_STUDENT_STATUSES;
import static io.restassured.RestAssured.given;

import com.epmrdpt.framework.properties.ApiProperty;
import com.epmrdpt.framework.properties.ApiPropertyService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "Verify 'Training'",
    groups = {"api_deprecated"})
public class TrainingTest {

  private static int id = Integer.parseInt(ApiPropertyService.getValueOf(ApiProperty.TRAINING_ID));

  @DataProvider(name = "Provider data for test with training end points")
  private static Object[][] dataProviderWithTrainingEndPoints() {
    return new Object[][]{
        {TRAININGS_REGISTERED},
        {TRAININGS_DISABLED},
        {TRAININGS_ACTIVE},
        {String.format(TRAININGS_ID_USER, id)},
        {String.format(TRAININGS_ID_PARTIAL_DETAILS, id)},
        {String.format(TRAININGS_ID_DETAILS_USER, id)},
        {TRAININGS_RECOMMENDED_PLANS},
    };
  }

  @DataProvider(name = "Provider data for test with required query param 'id' training end points")
  private static Object[][] dataProviderWithRequiredIdQueryEndPoints() {
    return new Object[][]{
        {TRAININGS_STUDENT_STATUSES},
        {TRAININGS_CHECK_STATUS},
        {TRAININGS_INTEGRATIONS_INFO}
    };
  }

  @DataProvider(name = "Provider data with boolean values")
  private static Object[][] dataProviderWithBooleanValues() {
    return new Object[][]{
        {true},
        {false}
    };
  }

  @Test(dataProvider = "Provider data for test with training end points")
  public void verifyTrainingEndPoint(String endPoint) {
    given()
        .when()
        .get(endPoint)
        .then()
        .statusCode(200);
  }

  @Test(dataProvider = "Provider data for test with required query param 'id' training end points")
  public void verifyTrainingEndPointWithIdQueryParam(String endPoint) {
    given()
        .queryParam("planId", id)
        .when()
        .get(endPoint)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyTrainingsFreemium() {
    given()
        .queryParam("id", id)
        .when()
        .get(TRAININGS_FREEMIUM)
        .then()
        .statusCode(200);
  }

  @Test(dataProvider = "Provider data with boolean values")
  public void verifyPlanFilterData(boolean isFullPlanTypeList) {
    given()
        .queryParam("isFullPlanTypeList", isFullPlanTypeList)
        .when()
        .get(TRAININGS_PLAN_FILTER_DATA)
        .then()
        .statusCode(200);
  }
}
