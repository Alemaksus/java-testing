package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_STAFFING_TAGS_EVENT_ID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-110576 Verify 'GET /api/event-management/staffing-tags/{eventId}' endpoint",
    groups = {"api"})
public class GetEventStaffingDeskTagsControllerStaffingTagsEndpointApiTest {

  private final int eventId = 72;
  private final int invalidEventId = 0;
  private final int expectedTagId = 577;
  private final String expectedTagName = "BY_Minsk_MC_AUTOTEST_2018-08-30";

  private SoftAssert softAssert;

  @Test(priority = 1)
  public void verifyGetStuffingTagsEventIdStatusOk() {
    softAssert = new SoftAssert();
    JsonPath jsonPath = getStuffingTagsEventId(eventId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath();
    List<Integer> actualTagIdsList = jsonPath.get("TagId");
    List<String> actualTagNamesList = jsonPath.get("TagName");
    softAssert.assertTrue(actualTagIdsList.stream().anyMatch(el -> el == expectedTagId),
        String.format("Tag Id='%s' is not present in Tag Ids list!", expectedTagId));
    softAssert.assertTrue(
        actualTagNamesList.stream().anyMatch(el -> el.equals(expectedTagName)),
        String.format("Tag Name='%s' is not present in Tag Names list!",
            expectedTagName));
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void verifyGetStuffingTagsEventIdBadRequest() {
    getStuffingTagsEventId(invalidEventId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetStuffingTagsEventIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getStuffingTagsEventId(eventId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetStuffingTagsEventIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getStuffingTagsEventId(eventId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStuffingTagsEventId(int eventId) {
    return given()
        .pathParam("eventId", eventId)
        .when()
        .get(EVENT_MANAGEMENT_STAFFING_TAGS_EVENT_ID);
  }
}
