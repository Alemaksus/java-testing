package com.epmrdpt.api.skills_descriptions;

import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_BY_STRING_ID;
import static com.epmrdpt.bo.skill_information.SkillInformationFactory.getSkillInformationWithFactForCertainLocale;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.LanguageEnum;
import com.epmrdpt.bo.skill_description.SkillDescription;
import com.epmrdpt.bo.skill_description.SkillDescriptionFactory;
import com.epmrdpt.bo.skill_information.SkillInformation;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-107235 Verify 'GET /api/skills-descriptions/{stringId}' endpoint",
    groups = {"api"})
public class GetSkillDescriptionControllerStringIdEndpointApiTest {

  private final String stringId = "Xamarin";
  private final String invalidStringId = "StringId";

  @Test(priority = 1)
  public void verifyGetSkillDescriptionStringIdStatusOk() {
    SkillDescription skillDescription = getSkillDescriptionStringId(stringId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(SkillDescription.class);
    assertEquals(skillDescription, getSkillDescription(),
        "Actual Skill Description is not equal to expected response!");
  }

  @Test(priority = 1)
  public void verifyGetSkillDescriptionStringIdBadRequest() {
    getSkillDescriptionStringId(invalidStringId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetSkillDescriptionStringIdUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSkillDescriptionStringId(stringId)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSkillDescriptionStringIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSkillDescriptionStringId(stringId)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSkillDescriptionStringId(String stringId) {
    return given()
        .pathParam("stringId", stringId)
        .when()
        .get(SKILLS_DESCRIPTIONS_BY_STRING_ID);
  }

  private SkillDescription getSkillDescription() {
    List<SkillInformation> skillInformationList = new ArrayList<>();
    for (LanguageEnum language : LanguageEnum.values()) {
      skillInformationList.add(
          getSkillInformationWithFactForCertainLocale("Title", "AutoTest_Description",
              "AutoTest_FactTitle", "AutoTest_FactDescription", language));
    }
    return SkillDescriptionFactory.getSkillDescription(42, "Xamarin", true, skillInformationList);
  }
}
