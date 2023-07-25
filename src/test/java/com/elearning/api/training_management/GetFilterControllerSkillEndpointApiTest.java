package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_FILTER_CONTROLLER_SKILL;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-106517 Verify 'GET /api/training-management/filter/skill' endpoint",
    groups = {"api"})
public class GetFilterControllerSkillEndpointApiTest {

  private final int expectedMinSkillsListSize = 39;
  private final static String DOT_NET_SKILL = ".NET";
  private final static String XAMARIN_SKILL = "Xamarin";

  @Test(priority = 1)
  public void verifyGetSkillStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    List<String> skillNamesList =
        getSkill()
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
    softAssert.assertTrue(skillNamesList.size() >= expectedMinSkillsListSize,
        "Actual skill names list size is not equal or greater than expected skill names list size!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetSkillUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSkill()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSkillForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSkill()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSkill() {
    return given()
        .when()
        .get(TRAINING_MANAGEMENT_FILTER_CONTROLLER_SKILL);
  }
}
