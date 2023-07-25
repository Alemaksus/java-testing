package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_LAB;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106346 Verify 'GET /api/training-management/plan/general/lab' endpoint",
    groups = {"api"})
public class GetGeneralTabLabEndpointApiTest {

  private final int cityId = 38;
  private final int invalidCityId = -38;

  private final List<String> expectedLabNames = Arrays.asList(
      "Kuibysheva, 13",
      "Brest State Technical University (BrSTU)",
      "Masherova, 6a",
      "Brest State A.S.Pushkin University (BrSU)",
      "Masherova str. 6a",
      "Brest State Medical College (BSMC)",
      "Brest Affiliate \"College of Business and Law\" (Brest CBL)");

  @Test(priority = 1)
  public void verifyGetLabStatusOk() {
    List<String> actualLabNamesList = getLab(cityId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get("Name");
    assertEquals(actualLabNamesList, expectedLabNames,
        "Actual lab names list doesn't equal expected lab names!");
  }

  @Test(priority = 1)
  public void verifyGetLabBadRequest() {
    getLab(invalidCityId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetLabUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getLab(cityId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetLabForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getLab(cityId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getLab(int cityIds) {
    return given()
        .queryParam("cityIds", cityIds)
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_LAB);
  }
}
