package com.epmrdpt.api.profile;

import static com.epmrdpt.api.Endpoints.PROFILE_PHOTO;
import static com.epmrdpt.api.Endpoints.PROFILE_UPDATE_PHOTO;
import static io.restassured.RestAssured.given;

import java.io.File;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@Test(description = "Verify 'Profile Photo'",
    groups = {"api_deprecated"})
public class ProfilePhotoTest {

  private final int userId = 1;
  private final String queryParameterUserId = "userId";

  @Test(priority = 1)
  public void verifyGetPhoto() {
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_PHOTO)
        .then()
        .statusCode(200);
  }

  @Test(priority = 2)
  public void verifyUpdatePhoto() {
    given()
        .queryParam(queryParameterUserId, userId)
        .multiPart(new File("src/test/testdata/image.jpg"))
        .when()
        .post(PROFILE_UPDATE_PHOTO)
        .then()
        .statusCode(200);
    given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_PHOTO)
        .then()
        .header("content-disposition", "attachment; filename=image.jpg");
  }

  @AfterClass(inheritGroups = false, alwaysRun = true)
  public void undoAllChanges() {
    given()
        .queryParam(queryParameterUserId, userId)
        .multiPart(new File("src/test/testdata/epam.jpg"))
        .when()
        .post(PROFILE_UPDATE_PHOTO);
  }
}
