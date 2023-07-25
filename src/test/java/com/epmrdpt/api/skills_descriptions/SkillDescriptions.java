package com.epmrdpt.api.skills_descriptions;

import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS;
import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_ACTIVE;
import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_BY_STRING_ID;
import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_BY_ID_GET_LOCALIZED;
import static com.epmrdpt.api.Endpoints.SKILLS_DESCRIPTIONS_BY_ID_IS_LOCALIZED;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.LanguageEnum;
import com.epmrdpt.bo.skill_description.SkillDescription;
import com.epmrdpt.bo.skill_description.SkillDescriptionFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.openqa.selenium.json.Json;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify 'SkillDescriptionCreationDeletionTest'",
    groups = {"api_deprecated"})
public class SkillDescriptions {

  private final int skillId = 53;
  private final String skillStringId = "forDeletion";
  private final String skillName = "forDeletion";
  private final String skillIdParameter = "Id";
  private final String skillStringIdParameter = "StringId";
  private final SkillDescription expectedSkillDescription = SkillDescriptionFactory
      .getGeneratedSkillDescription(skillId, skillName, true);
  private String currentLocale = System.getProperty("locale");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void checkIfSkillExistsAndRemoveIfNeeded(){
    int statusCode =
    given()
        .when()
        .get(SKILLS_DESCRIPTIONS_BY_STRING_ID, skillStringId)
        .getStatusCode();
    if(statusCode == 200) {
      given()
          .queryParam(skillIdParameter, skillId)
          .when()
          .delete(SKILLS_DESCRIPTIONS)
          .then()
          .statusCode(200);
    }
  }

  @Test(priority = 1)
  public void verifyActiveSkillDescriptions(){
    SoftAssert softAssert = new SoftAssert();
    Response response =
        given()
            .when()
            .get(SKILLS_DESCRIPTIONS_ACTIVE);
    softAssert.assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
    Integer[] activeSkillsId =
        response
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .as(Integer[].class);
    softAssert.assertNotEquals(activeSkillsId.length
        ,0,"Unexpected length of active skill list!");
  }

  @Test(priority = 2)
  public void verifyAllSkillDescriptions(){
    SoftAssert softAssert = new SoftAssert();
    Response response =
        given()
            .when()
            .get(SKILLS_DESCRIPTIONS);
    softAssert.assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
    SkillDescription[] allSkillDescriptions =
        response
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .as(SkillDescription[].class);
    System.out.println(new Json().toJson(allSkillDescriptions[0]));
    softAssert.assertNotEquals(allSkillDescriptions.length
        ,0,"Unexpected length of all skill description list!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifySkillDescriptionCreation(){
    Response response =
    given()
        .contentType(ContentType.JSON)
        .body(expectedSkillDescription)
        .when()
        .post(SKILLS_DESCRIPTIONS);
    assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
  }

  @Test(priority = 4)
  public void verifySkillDescriptionInfo(){
    SoftAssert softAssert = new SoftAssert();
    Response response =
        given()
            .when()
            .get(SKILLS_DESCRIPTIONS_BY_STRING_ID, skillStringId);
    softAssert.assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
    SkillDescription createdSkillDescription =
        response
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .as(SkillDescription.class);
    softAssert.assertEquals(createdSkillDescription
        ,expectedSkillDescription,
        "New skill description wasn't sent to the server!");
  }
  @Test(priority = 5)
  public void verifySkillDescriptionLocalizedInfo(){
    SoftAssert softAssert = new SoftAssert();
    SkillDescription expectedLocalizedSkillDescription = SkillDescriptionFactory
        .getSkillsDescriptionInGivenLocale(expectedSkillDescription,
        LanguageEnum.getLanguageEnumByLocaleName(currentLocale));
    Response response =
        given()
            .queryParam(skillStringIdParameter, skillStringId)
            .when()
            .get(SKILLS_DESCRIPTIONS_BY_ID_GET_LOCALIZED);
    softAssert.assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
    SkillDescription createdLocalizedSkillDescription =
        response
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .as(SkillDescription.class);
    softAssert.assertEquals(createdLocalizedSkillDescription
        ,expectedLocalizedSkillDescription,
        "New skill description doesn't have any localized info in the server!");
  }

  @Test(priority = 6)
  public void verifySkillDescriptionIsLocalized(){
    SoftAssert softAssert = new SoftAssert();
    Response response =
        given()
            .queryParam(skillStringIdParameter, skillStringId)
            .when()
            .get(SKILLS_DESCRIPTIONS_BY_ID_IS_LOCALIZED);
    softAssert.assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
    boolean isLocalized =
        response
            .getBody()
            .as(boolean.class);
    softAssert.assertEquals(isLocalized,true,
        "New skill description isn't localized!");
  }

  @Test(priority = 7)
  public void verifySkillDescriptionUpdate(){
    SoftAssert softAssert = new SoftAssert();
    SkillDescriptionFactory.editSkillsDescription(expectedSkillDescription);
    Response response =
    given()
        .contentType(ContentType.JSON)
        .body(expectedSkillDescription)
        .when()
        .put(SKILLS_DESCRIPTIONS);
    softAssert.assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
    SkillDescription changedSkillDescription =
    given()
        .when()
        .get(SKILLS_DESCRIPTIONS_BY_STRING_ID, skillStringId)
        .then()
        .contentType(ContentType.JSON)
        .extract()
        .as(SkillDescription.class);
    softAssert.assertEquals(changedSkillDescription
        ,expectedSkillDescription,
        "Skill description wasn't updated!");
  }

  @Test(priority = 8)
  public void verifySkillDescriptionDeletion(){
    SoftAssert softAssert = new SoftAssert();
    Response response =
        given()
            .queryParam(skillIdParameter, skillId)
            .when()
            .delete(SKILLS_DESCRIPTIONS);
    softAssert.assertEquals(response.getStatusCode(),200
        ,"Incorrect status code!");
    softAssert.assertEquals(
        given()
        .when()
        .get(SKILLS_DESCRIPTIONS_BY_STRING_ID, skillStringId)
        .getStatusCode(),404,
        "Skill description wasn't deleted!");
  }
}
