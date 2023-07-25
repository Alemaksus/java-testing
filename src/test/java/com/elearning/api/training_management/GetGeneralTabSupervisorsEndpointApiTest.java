package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_SUPERVISORS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106329 Verify 'GET /api/training-management/plan/general/supervisors' endpoint",
    groups = {"api"})
public class GetGeneralTabSupervisorsEndpointApiTest {

  private final int expectedMinSkillsListSize = 87;
  private final static String ALIAKSANDR_LAST_NAME_7 = "Aliaksandr LastName7";
  private final static String NIKOLAY_LAST_NAME_568 = "Nikolay LastName568";

  @Test(priority = 1)
  public void verifyGetSupervisorsStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    List<String> supervisorNamesList =
        getSupervisors()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get("DisplayName");
    softAssert.assertTrue(
        supervisorNamesList.stream().anyMatch(el -> el.equals(ALIAKSANDR_LAST_NAME_7)),
        String.format("DisplayName='%s' is not present in supervisor names list!",
            ALIAKSANDR_LAST_NAME_7));
    softAssert.assertTrue(
        supervisorNamesList.stream().anyMatch(el -> el.equals(NIKOLAY_LAST_NAME_568)),
        String.format("DisplayName='%s' is not present in supervisor names list!",
            NIKOLAY_LAST_NAME_568));
    softAssert.assertEquals(
        supervisorNamesList.size(), expectedMinSkillsListSize,
        "Actual supervisor names list size  is not equal to expected supervisor names list size!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetSupervisorsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSupervisors()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSupervisorsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSupervisors()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSupervisors() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_SUPERVISORS);
  }
}
