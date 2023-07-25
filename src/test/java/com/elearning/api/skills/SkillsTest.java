package com.epmrdpt.api.skills;

import static com.epmrdpt.api.Endpoints.SKILLS_EXTENDED;
import static com.epmrdpt.api.Endpoints.SKILLS_SHORT;
import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "Verify 'Skills'",
    groups = {"api_deprecated"})
public class SkillsTest {

  private String endPoint;
  private boolean isFullList;

  @Factory(dataProvider = "Provider data for test")
  public SkillsTest(String endPoint, boolean isFullList) {
    this.endPoint = endPoint;
    this.isFullList = isFullList;
  }

  @DataProvider(name = "Provider data for test")
  private static Object[][] dataProviderForTest() {
    return new Object[][]{
        {SKILLS_SHORT, true},
        {SKILLS_SHORT, false},
        {SKILLS_EXTENDED, true},
        {SKILLS_EXTENDED, false}
    };
  }

  @Test
  public void verifySkills() {
    given()
        .queryParam("isFullList", isFullList)
        .when()
        .get(endPoint)
        .then()
        .statusCode(200);
  }
}
