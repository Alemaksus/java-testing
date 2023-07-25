package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_FILTER_TYPE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_106515 Verify 'GET  /api/training-management/filter/type' endpoint",
    groups = {"api"})
public class GetFilterTypesEndpointApiTest {

  private final List<String> expectedTypeNamesList = Arrays.asList(
      "Training",
      "Mentoring",
      "Workshop",
      "Internship",
      "Laboratory",
      "Self-study",
      "Self-study",
      "Department Affiliate",
      "Educational practice");

  @Test(priority = 1)
  public void verifyGetTypesStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    List<String> actualTypeNamesList =
        getTypes()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get("Types.Name");
    softAssert.assertNotNull(actualTypeNamesList);
    softAssert.assertEquals(expectedTypeNamesList, expectedTypeNamesList,
        "Expected Type names list doesn't contain actual Type names list!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetTypesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getTypes()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetTypesForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getTypes()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getTypes() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_FILTER_TYPE);
  }
}
