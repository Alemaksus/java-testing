package com.epmrdpt.api.skills_descriptions;

import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_BY_ID_GET_LOCALIZED;
import static com.epmrdpt.bo.LanguageEnum.getLanguageEnumByLocaleName;
import static com.epmrdpt.bo.skill_description.SkillDescriptionFactory.getSkillDescriptionWithLocalized;
import static com.epmrdpt.bo.skill_information.SkillInformationFactory.getSkillInformationWithFactForCertainLocale;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.skill_description.SkillDescription;
import com.epmrdpt.bo.skill_information.SkillInformation;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-109497 Verify 'GET /api/skills-descriptions/get-localized' endpoint",
    groups = {"api"})
public class GetSkillDescriptionControllerLocalizedEndpointApiTest {

  private final String stringId = "Xamarin";
  private final String invalidStringId = "StringId";
  private String currentLocale = System.getProperty("locale");

  @Test(priority = 1)
  public void verifyGetSkillDescriptionLocalizedStatusOk() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    SkillDescription skillDescription = getSkillDescriptionLocalized(stringId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(SkillDescription.class);
    assertEquals(skillDescription, getSkillDescription(),
        "Actual Skill Description localized is not equal to expected response!");
  }

  @Test(priority = 2)
  public void verifyGetSkillDescriptionLocalizedBadRequest() {
    getSkillDescriptionLocalized(invalidStringId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  private Response getSkillDescriptionLocalized(String stringId) {
    return given()
        .queryParam("stringId", stringId)
        .when()
        .get(SKILLS_DESCRIPTIONS_BY_ID_GET_LOCALIZED);
  }

  private SkillDescription getSkillDescription() {
    SkillInformation skillInformation = getSkillInformationWithFactForCertainLocale("Title",
        "AutoTest_Description", "AutoTest_FactTitle",
        "AutoTest_FactDescription", getLanguageEnumByLocaleName(currentLocale));
    return getSkillDescriptionWithLocalized(42, "Xamarin", true, skillInformation);
  }
}
