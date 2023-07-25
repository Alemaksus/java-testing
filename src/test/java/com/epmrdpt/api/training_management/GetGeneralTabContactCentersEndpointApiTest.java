package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_CONTACT_CENTERS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106315 Verify 'GET /api/training-management/plan/general/contact-centers' endpoint",
    groups = {"api"})
public class GetGeneralTabContactCentersEndpointApiTest {

  private final List<String> expectedContactCenterNamesList = Arrays.asList(
      "Contact Center BY",
      "Contact Center RU",
      "Contact Center",
      "Contact Center UA",
      "Org Uni Prog Online UA",
      "Org Uni Prog Online RO",
      "Org Uni Prog Online ES",
      "EPAM FrontEndProgramHU",
      "EPAM UpSkill Georgia",
      "Org Uni Prog Online HR",
      "WFA RD Education Support Service",
      "OrgEnglishCommercial",
      "WFA Talent Acquisition Campus Hiring TR",
      "wfatalgdes@epam.com");

  @Test(priority = 1)
  public void verifyGetContactCentersStatusOk() {
    List<String> actualContactCentersNamesList =
        getContactCenters()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get("Name");
    assertTrue(expectedContactCenterNamesList.containsAll(actualContactCentersNamesList),
        "Expected Contact Center names list doesn't contain actual Contact Center names list!");
  }

  @Test(priority = 2)
  public void verifyGetContactCentersUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getContactCenters()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetContactCentersForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getContactCenters()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getContactCenters() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_CONTACT_CENTERS);
  }
}
