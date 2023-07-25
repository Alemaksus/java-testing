package com.epmrdpt.api.skills_descriptions;

import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_BY_ID_IS_LOCALIZED;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-109498 Verify 'GET /api/skills-descriptions/is-localized' endpoint",
    groups = {"api"})
public class GetSkillDescriptionControllerIsLocalizedEndpointApiTest {

  private final String stringId = "Ruby";
  private final String invalidStringId = "StringId";

  @DataProvider(name = "Provider data with localized")
  private Object[][] dataProviderLocalized() {
    return new Object[][]{
        {"en-US", true},
        {"ru-RU", false},
        {"uk-UA", false},
    };
  }

  @Test(priority = 1, dataProvider = "Provider data with localized")
  public void verifyGetSkillDescriptionIsLocalizedStatusOk(String userCurrentLocale,
      boolean expectedSkillDescriptionLocalized) {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    ((RequestSpecificationImpl) requestSpecification).replaceCookie("UserCurrentLocale",
        userCurrentLocale);
    boolean actualSkillDescriptionLocalized = getSkillDescriptionIsLocalized(stringId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(boolean.class);
    assertEquals(actualSkillDescriptionLocalized, expectedSkillDescriptionLocalized,
        "Actual Skill Description Localized is not equal to expected response!");
  }

  @Test(priority = 2)
  public void verifyGetSkillDescriptionIsLocalizedBadRequest() {
    getSkillDescriptionIsLocalized(invalidStringId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  private Response getSkillDescriptionIsLocalized(String stringId) {
    return given()
        .queryParam("stringId", stringId)
        .when()
        .get(SKILLS_DESCRIPTIONS_BY_ID_IS_LOCALIZED);
  }
}
