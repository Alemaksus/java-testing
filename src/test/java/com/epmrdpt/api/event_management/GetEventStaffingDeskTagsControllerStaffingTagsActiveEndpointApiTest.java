package com.epmrdpt.api.event_management;

import static com.epmrdpt.api.Endpoints.EVENT_MANAGEMENT_STAFFING_TAGS_ACTIVE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-110917 Verify 'GET /api/event-management/staffing-tags/active' endpoint",
    groups = {"api"})
public class GetEventStaffingDeskTagsControllerStaffingTagsActiveEndpointApiTest {

  @Test(priority = 1)
  public void verifyGetStaffingTagsActiveOk() {
    List<String> actualStaffingTagsActiveList = getStaffingTagsActive()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get();
    assertFalse(actualStaffingTagsActiveList.isEmpty(),
        "Actual list doesn't contain any elements!");
  }

  @Test(priority = 2)
  public void verifyGetStaffingTagsActiveUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getStaffingTagsActive()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetStaffingTagsActiveForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getStaffingTagsActive()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getStaffingTagsActive() {
    return given()
        .when()
        .get(EVENT_MANAGEMENT_STAFFING_TAGS_ACTIVE);
  }
}
