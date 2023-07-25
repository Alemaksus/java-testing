package com.epmrdpt.api.skills;

import static com.epmrdpt.api.Endpoints.SKILLS_PICTURES;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.skill_picture.SkillPicture;
import com.epmrdpt.tokens_storage.TokensStorage;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT-109592 Verify 'GET /api/skills/{id}/pictures' endpoint",
    groups = {"api"})
public class GetSkillsControllerPicturesEndpointApiTest {

  private final int id = 6;
  private final int invalidId = 12;

  @Test(priority = 1)
  public void verifyGetSkillPicturesStatusOk() {
    List<SkillPicture> actualSkillPicturesList = getSkillPictures(id)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(new TypeRef<List<SkillPicture>>() {
        });
    assertEquals(actualSkillPicturesList, getSkillPicturesListForTest(),
        "Actual Skill pictures list is not equal to expected response!");
  }

  @Test(priority = 1)
  public void verifyGetSkillPicturesBadRequest() {
    getSkillPictures(invalidId)
        .then()
        .statusCode(HttpStatus.SC_BAD_REQUEST);
  }

  @Test(priority = 2)
  public void verifyGetSkillPicturesUnauthorized() {
    ((RequestSpecificationImpl) requestSpecification).removeHeaders();
    getSkillPictures(id)
        .then()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
  }

  @Test(priority = 3)
  public void verifyGetSkillPicturesIdForbidden() {
    TokensStorage.setUpStudentRestAssuredCredentials();
    getSkillPictures(id)
        .then()
        .statusCode(HttpStatus.SC_FORBIDDEN);
  }

  private Response getSkillPictures(int id) {
    return given()
        .pathParam("id", id)
        .when()
        .get(SKILLS_PICTURES);
  }

  private List<SkillPicture> getSkillPicturesListForTest() {
    List<SkillPicture> skillPictureList = Arrays.asList(
        new SkillPicture(
            53,
            "AutoTestImage.jpg",
            "SkillCoverPicture",
            null,
            getEnv() + "static/skill/6/AutoTestImage.jpg"),
        new SkillPicture(
            54,
            "AutoTestAbstractPainting.jpeg",
            "DescriptionPicture",
            "En",
            getEnv() + "static/skill/6/AutoTestAbstractPainting.jpeg"));
    return skillPictureList;
  }
}
