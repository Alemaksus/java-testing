package com.epmrdpt.api.profile;

import static com.epmrdpt.api.Endpoints.PROFILE_EDUCATIONS;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.profile.ProfileEducationsInfo;
import com.epmrdpt.utils.RandomUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "Verify 'Profile Educations Info'",
    groups = {"api_deprecated"})
public class ProfileEducationsInfoTest {

  private final int userId = 1;
  private final String queryParameterUserId = "userId";
  private ProfileEducationsInfo[] educationsInfo;

  @Test(priority = 1)
  public void verifyGetEducationsInfo() {
    educationsInfo = given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_EDUCATIONS)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .and()
        .extract()
        .as(ProfileEducationsInfo[].class);
  }

  @Test(priority = 2)
  public void verifyPostEducationsInfo() {
    educationsInfo[0].setDegree(RandomUtils.getRandomNumberInInterval(1,14));
    educationsInfo[0].setEducationYear(RandomUtils
        .getRandomNumberInInterval(0,10));
    educationsInfo[0].setGraduationYear(RandomUtils
        .getRandomNumberInInterval(2005, 2009));
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .contentType(JSON)
        .body(educationsInfo)
        .post(PROFILE_EDUCATIONS)
        .then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test(priority = 3)
  public void verifyEducationsInfoWasChanged() {
    ProfileEducationsInfo[] changedEducationsInfo
        = given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_EDUCATIONS)
        .then()
        .extract()
        .as(ProfileEducationsInfo[].class);
    assertEquals(changedEducationsInfo, educationsInfo,
        "Changed educations info wasn't saved!");
  }
}
