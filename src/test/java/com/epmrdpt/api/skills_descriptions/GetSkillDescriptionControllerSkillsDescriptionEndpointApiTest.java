package com.epmrdpt.api.skills_descriptions;

import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.skill_description.SkillDescription;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT-107236 Verify 'GET /api/skills-descriptions' endpoint",
    groups = {"api"})
public class GetSkillDescriptionControllerSkillsDescriptionEndpointApiTest {

  private final int id = 1;
  private final int expectedMinSkillDescriptionsLength = 15;

  @Test(priority = 1)
  public void verifyGetSkillDescriptionStatusOk() {
    SoftAssert softAssert = new SoftAssert();
    SkillDescription[] skillDescriptions = getSkillDescriptions()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(SkillDescription[].class);
    SkillDescription actualSkillDescription = Arrays.stream(skillDescriptions)
        .filter(e -> e.getId() == id).findFirst().get();
    assertEquals(actualSkillDescription, getSkillDescription(),
        "Actual Skill Description is not equal to expected response!");
    softAssert.assertTrue(skillDescriptions.length >= expectedMinSkillDescriptionsLength,
        "Actual skill Description length is not equal or greater than expected Skill Description length!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyGetSkillDescriptionUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSkillDescriptions()
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSkillDescriptionForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSkillDescriptions()
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSkillDescriptions() {
    return given()
        .when()
        .get(SKILLS_DESCRIPTIONS);
  }

  private SkillDescription getSkillDescription() {
    return new SkillDescription()
        .withId(id)
        .withStringId("NET")
        .withName(".NET")
        .withStatus(true);
  }
}
