package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_OWNER;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-106344 Verify 'GET /api/training-management/plan/general/owner' endpoint",
    groups = {"api"})
public class GetGeneralTabOwnerEndpointApiTest {

  private final static String MANAGER_TEST = "ManagerTest ManagerTest";

  @Test(priority = 1)
  public void verifyGetOwnerStatusOk() {
    List<String> ownerNamesList =
        getOwner()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get("DisplayName");
    assertTrue(ownerNamesList.stream().anyMatch(el -> el.equals(MANAGER_TEST)),
        String.format("DisplayName='%s' is not present in owner names list!", MANAGER_TEST));
  }

  @Test(priority = 2)
  public void verifyGetOwnerUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getOwner()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetOwnerForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getOwner()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getOwner() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_OWNER);
  }
}
