package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_UNIVERSITY;
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

@Test(description = "EPMRDPT-106428 Verify 'GET /api/training-management/plan/general/university' endpoint",
    groups = {"api"})
public class GetGeneralTabUniversityEndpointApiTest {

  private final List<String> expectedUniversityNamesList = Arrays.asList(
      "Belarusian State University of Informatics and Radioelectronics",
      "Belarusian State University",
      "Belarusian National Technical University",
      "Belarusian-Russian University",
      "Belarusian State Technological University",
      "Belarusian State University of Transport",
      "Belarusian State Economic University",
      "Brest State A.S.Pushkin University",
      "Sukhoi State technical university of Gomel",
      "Francisk Skorina Gomel State University",
      "Yanka Kupala State University of Grodno",
      "National Technical University of Ukraine \"Igor Sikorsky Kyiv Polytechnic Institute\"",
      "Belarusian State Academy of Telecommunications",
      "Institute of Information Technologies BSUIR",
      "Baranovichi State University",
      "Brest State Technical University",
      "Vitebsk State Technological University",
      "Polessky\u00A0State University");

  @Test(priority = 1)
  public void verifyGetUniversityStatusOk() {
    List<String> actualUniversityNamesList = getUniversity()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    assertTrue(expectedUniversityNamesList.containsAll(actualUniversityNamesList),
        "Expected currency names list doesn't contain actual currency names list!");
  }

  @Test(priority = 2)
  public void verifyGetUniversityUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getUniversity()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetUniversityForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getUniversity()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getUniversity() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_UNIVERSITY);
  }
}
