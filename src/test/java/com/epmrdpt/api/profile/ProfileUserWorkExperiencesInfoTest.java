package com.epmrdpt.api.profile;

import static com.epmrdpt.api.Endpoints.PROFILE_WORK_EXPERIENCES;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.profile.ProfileUserWorkExperiencesInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "Verify 'Profile user work experiences info'",
    groups = {"api_deprecated"})
public class ProfileUserWorkExperiencesInfoTest {

  private final int userId = 325903;
  private final String queryParameterUserId = "userId";
  private ProfileUserWorkExperiencesInfo[] userWorkExperiencesInfo;


  @Test(priority = 1)
  public void verifyGetUserWorkExperiencesInfo() {
    userWorkExperiencesInfo = given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_WORK_EXPERIENCES)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .and()
        .extract()
        .as(ProfileUserWorkExperiencesInfo[].class);
  }

  @Test(priority = 2)
  public void verifyPostUserWorkExperiencesInfo() {
    userWorkExperiencesInfo[0].setPosition(RandomStringUtils.randomAlphabetic(5, 12));
    userWorkExperiencesInfo[0].setNameCompany(RandomStringUtils.randomAlphabetic(5, 12));
    userWorkExperiencesInfo[0].setAdditionInformation(RandomStringUtils.randomAlphabetic(5, 20));
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .contentType(JSON)
        .body(userWorkExperiencesInfo)
        .post(PROFILE_WORK_EXPERIENCES)
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test(priority = 3)
  public void verifyUserWorkExperiencesInfoWasChanged() {
    ProfileUserWorkExperiencesInfo[] expectedUserWorkExperiencesInfo =
        given()
            .queryParam(queryParameterUserId, userId)
            .when()
            .get(PROFILE_WORK_EXPERIENCES)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(ProfileUserWorkExperiencesInfo[].class);
    assertEquals(expectedUserWorkExperiencesInfo, userWorkExperiencesInfo,
        "Changed user work experience info wasn't saved!");
  }
}
