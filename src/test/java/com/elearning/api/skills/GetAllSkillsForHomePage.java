package com.epmrdpt.api.skills;

import static com.epmrdpt.api.Endpoints.SKILLS_SHORT;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertFalse;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-109510 Verify 'GET /api/skills/short' endpoint",
    groups = {"api"})

public class GetAllSkillsForHomePage {

  private String endPoint;
  private boolean isFullList;

  @Factory(dataProvider = "Provider data for test")
  public GetAllSkillsForHomePage(String endPoint, boolean isFullList) {
    this.endPoint = endPoint;
    this.isFullList = isFullList;
  }

  @DataProvider(name = "Provider data for test")
  private static Object[][] dataProviderForTest() {
    return new Object[][]{
        {SKILLS_SHORT, true},
        {SKILLS_SHORT, false}
    };
  }

  @Test
  public void verifyGetSkillsOk() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    List<String> actualSkillsList = getSkills()
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .jsonPath()
        .get();
    assertFalse(actualSkillsList.isEmpty(), "Actual skills list doesn't contain any elements!");
  }

  private Response getSkills() {
    return given()
        .queryParam("isFullList", isFullList)
        .when()
        .get(endPoint);
  }
}
