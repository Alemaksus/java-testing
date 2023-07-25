package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_SKILLS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106314 Verify 'GET /api/training-management/plan/general/skills' endpoint",
    groups = {"api"})

public class GetGeneralTabSkillsEndpointApiTest {

  private final int expectedMinSkillsListSize = 29;
  private final static String DOT_NET_SKILL = ".NET";
  private final static String XAMARIN_SKILL = "Xamarin";

  @Test(priority = 1)
  public void verifyGetSkillsStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    List<String> skillNamesList =
        getSkills()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .get("Name");
    softAssert.assertTrue(
        skillNamesList.stream().anyMatch(el -> el.equals(DOT_NET_SKILL)),
        String.format("Skill='%s' is not present in skill names list!", DOT_NET_SKILL));
    softAssert.assertTrue(
        skillNamesList.stream().anyMatch(el -> el.equals(XAMARIN_SKILL)),
        String.format("Skill='%s' is not present in skill names list!", XAMARIN_SKILL));
    softAssert.assertTrue(
        skillNamesList.size() >= expectedMinSkillsListSize,
        "Actual skill names list size  is not equal or greater than expected skill names list size!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetSkillsUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSkills()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSkillsForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSkills()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSkills() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_SKILLS);
  }
}
