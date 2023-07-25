package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_LIST;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.events.Event;
import com.epmrdpt.bo.events.EventCreator;
import com.epmrdpt.bo.events.EventLocation;
import com.epmrdpt.framework.properties.ApiProperty;
import com.epmrdpt.framework.properties.ApiPropertyService;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-111628 Verify 'GET /api/event-management/list' endpoint",
    groups = {"api"})
public class GetEventManagementControllerListEndpointApiTest {

  private final int eventId = 127;
  private final int eventManagerId = Integer.parseInt(
      ApiPropertyService.getValueOf(ApiProperty.EVENT_MANAGER_ID));

  @Test(priority = 1)
  public void verifyGetEventsListStatusOk() {
    Event actualEvent = getEventList()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(new TypeRef<List<Event>>() {
        })
        .stream()
        .filter(e -> e.getId() == eventId)
        .findFirst()
        .get();
    assertEquals(actualEvent, getEventForTest(),
        "Actual event is not equal to expected event!");
  }

  @Test(priority = 2)
  public void verifyGetEventsListUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getEventList()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetEventsListForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getEventList()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getEventList() {
    return given()
        .when()
        .get(EVENT_MANAGEMENT_LIST);
  }

  private Event getEventForTest() {
    return new Event(
        eventId,
        "AutoTest_API_Event",
        Arrays.asList(new EventLocation(Arrays.asList("AutoTestCity"), "AutoTestCountry")),
        "2023-07-05T21:00:00Z",
        "English",
        0,
        1,
        new EventCreator(eventManagerId, "UserEvent Manager", false));
  }
}
