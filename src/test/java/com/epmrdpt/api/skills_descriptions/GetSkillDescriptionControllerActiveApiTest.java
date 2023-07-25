package com.epmrdpt.api.skills_descriptions;

import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_ACTIVE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertFalse;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-107234 Verify 'GET /api/skills-descriptions/active' endpoint",
    groups = {"api"})

public class GetSkillDescriptionControllerActiveApiTest {

  @Test
  public void verifyGetActiveOk() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    List<String> actualActiveList = getActive()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get();
    assertFalse(actualActiveList.isEmpty(), "Actual list doesn't contain any elements!");
  }

  private Response getActive() {
    return given()
        .when()
        .get(SKILLS_DESCRIPTIONS_ACTIVE);
  }
}
