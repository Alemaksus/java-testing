package com.epmrdpt.api.training_process;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_EVENTS_BY_TRAINER;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify events for current user in provided date range",
    groups = {"api_deprecated"})
public class EventsByTrainerTest{

  private static final String START_DATE = "2023-02-01";
  private static final String FINISH_DATE = "2023-02-28";


  @Test
  public void checkResponseCodeAndBody() {
    SoftAssert softAssert = new SoftAssert();
    JsonPath jsonPath = given()
        .queryParam("dateRange.startDate", START_DATE)
        .queryParam("dateRange.finishDate", FINISH_DATE)
        .when()
        .get(TRAINING_PROCESS_EVENTS_BY_TRAINER)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract().body().jsonPath();
    List<String> topics = jsonPath.get("Topic");
    List<String> startDateEvents = jsonPath.get("StartDateTime");
    softAssert.assertTrue(topics.stream().noneMatch(String::isEmpty),
        "Topic event by trainer can't be empty!");
    String expectStartYearMonth = START_DATE.substring(0,7);
    softAssert.assertTrue(startDateEvents.stream().anyMatch(x->x.startsWith(expectStartYearMonth)),
        "Start date event by trainer isn't in the range of valid values!");
    softAssert.assertAll();
  }
}
