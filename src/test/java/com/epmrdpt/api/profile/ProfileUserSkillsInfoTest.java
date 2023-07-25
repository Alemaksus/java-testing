package com.epmrdpt.api.profile;

import static com.epmrdpt.api.Endpoints.PROFILE_USER_SKILLS;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.profile.ProfileUserSkillsInfo;
import com.epmrdpt.utils.RandomUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


@Test(description = "Verify 'Profile user skills info'",
    groups = {"api_deprecated"})
public class ProfileUserSkillsInfoTest {

  private final int userId = 1;
  private final String queryParameterUserId = "userId";
  private final int firstUserSkillId = 1;
  private final String firstUserSkillName = "ABAP";
  private final int secondUserSkillId = 12;
  private final String secondUserSkillName = "PL/SQL";
  private ProfileUserSkillsInfo[] userSkillsInfo;

  @Test(priority = 1)
  public void verifyGetUserSkillsInfo() {
    userSkillsInfo = given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_USER_SKILLS)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .and()
        .extract()
        .as(ProfileUserSkillsInfo[].class);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertFalse(userSkillsInfo[0].getSkillId() == 0,
        "'Skills info' does not contain a skill id!");
    softAssert.assertFalse(userSkillsInfo[0].getSkillName().isEmpty(),
        "'Skills info' does not contain a name!");
    softAssert.assertAll();
  }


  @Test(priority = 2)
  public void verifyPostUserSkillsInfo() {
    if (userSkillsInfo[0].getSkillId() == firstUserSkillId) {
      userSkillsInfo[0].setSkillId(secondUserSkillId);
      userSkillsInfo[0].setSkillName(secondUserSkillName);
    }
        else {
      userSkillsInfo[0].setSkillId(firstUserSkillId);
      userSkillsInfo[0].setSkillName(firstUserSkillName);
    }
      userSkillsInfo[0].setSkillLevel(RandomUtils.getRandomNumberInInterval(1, 4));
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .contentType(JSON)
        .body(userSkillsInfo)
        .post(PROFILE_USER_SKILLS)
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test(priority = 3)
  public void verifyUserSkillsInfoWasChanged() {
    ProfileUserSkillsInfo[] changedUserSkillsInfo =
        given()
            .queryParam(queryParameterUserId, userId)
            .when()
            .get(PROFILE_USER_SKILLS)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(ProfileUserSkillsInfo[].class);
    assertEquals(changedUserSkillsInfo, userSkillsInfo,
        "Changed user skills info wasn't saved!");
  }
}
