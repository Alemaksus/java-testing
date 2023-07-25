package com.epmrdpt.api.training_process;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_EVENTS_CALENDAR_DATES;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "Verify 'Calendar-dates'",
    groups = {"api_deprecated"})
public class CalendarDatesTest {

  private static final Integer YEAR = 2023;
  private static final Integer MONTH = 02;
  private static final Integer DATE_OF_EVENT = 16;

  @Test(priority = 1)
  public void verifyEventsIntoCalendarDates() {
    List<Integer> calendarDates =
        given()
            .queryParam("dateMonth.year", YEAR)
            .queryParam("dateMonth.month", MONTH)
            .when()
            .get(TRAINING_PROCESS_EVENTS_CALENDAR_DATES)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get();
    assertTrue(calendarDates.stream().anyMatch(x -> x.equals(DATE_OF_EVENT)),
        "Events were not found");
  }
}
