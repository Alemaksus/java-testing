package com.epmrdpt.api.profile;

import static com.epmrdpt.api.Endpoints.PROFILE_APPLICATIONS;
import static com.epmrdpt.api.Endpoints.PROFILE_CURRENT_TESTING;
import static com.epmrdpt.api.Endpoints.PROFILE_EDUCATIONS;
import static com.epmrdpt.api.Endpoints.PROFILE_ENGLISH_INFO;
import static com.epmrdpt.api.Endpoints.PROFILE_ENGLISH_TEST_RESULT;
import static com.epmrdpt.api.Endpoints.PROFILE_STUDENT_GROUPS;
import static com.epmrdpt.api.Endpoints.PROFILE_USER_SKILLS;
import static com.epmrdpt.api.Endpoints.PROFILE_VERIFICATION_EMAIL;
import static com.epmrdpt.api.Endpoints.PROFILE_WORK_EXPERIENCES;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Profile'",
    groups = {"api_deprecated"})
public class ProfileTest {

  private final int userId = 1;
  private final String queryParameterUserId = "userId";
  private final String unknownEmail = "admrrrrrrdpt@gmail.com";
  private final String knownEmail = "UserEmail2@mail.io";

  @Test(priority = 1)
  public void verifyUnknownVerificationEmail() {
    given()
        .queryParam("email", unknownEmail)
        .when()
        .get(PROFILE_VERIFICATION_EMAIL)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyKnownVerificationEmail() {
    given()
        .queryParam("email", knownEmail)
        .when()
        .get(PROFILE_VERIFICATION_EMAIL)
        .then()
        .statusCode(400);
  }

  @Test(priority = 1)
  public void verifyEducations() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_EDUCATIONS)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyWorkExperiences() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_WORK_EXPERIENCES)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyEnglishInfo() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_ENGLISH_INFO)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyResumeInfo() {
    given()
        .queryParam(queryParameterUserId, userId)
        .queryParam("userId", userId)
        .when()
        .get(PROFILE_ENGLISH_TEST_RESULT)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyStudentGroups() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_STUDENT_GROUPS)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyApplications() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_APPLICATIONS)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyCurrentTesting() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_CURRENT_TESTING)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyUserSkills() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_USER_SKILLS)
        .then()
        .statusCode(200);
  }
}
