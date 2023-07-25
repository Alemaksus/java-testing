package com.epmrdpt.api.profile;

import static com.epmrdpt.api.Endpoints.PROFILE_BASIC_INFO;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.profile.ProfileBasicInfo;
import com.epmrdpt.bo.profile.ProfileDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify 'Profile Basic Info'",
    groups = {"api_deprecated"})
public class ProfileBasicInfoTest {

  private final int userId = 1;
  private final String queryParameterUserId = "userId";
  private ProfileBasicInfo originalProfile;
  private ProfileBasicInfo expectedProfile = ProfileDataFactory.getGeneratedProfileBasicInfo();

  @Test(priority = 1)
  public void verifyGetBasicInfo() {
    originalProfile = given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_BASIC_INFO)
        .then()
        .statusCode(200)
        .and()
        .extract()
        .as(ProfileBasicInfo.class);
  }

  @Test(priority = 2)
  public void verifyPostBasicInfo() {
    expectedProfile.withId(originalProfile.getUserId());
    expectedProfile.withEmail(originalProfile.getEmail());
    expectedProfile.withCreatedAt(originalProfile.getCreatedAt());
    expectedProfile.withPhotoId(originalProfile.getPhotoId());
    Response response =
        given().queryParam(queryParameterUserId, userId)
            .when()
            .contentType(ContentType.JSON)
            .body(expectedProfile)
            .post(PROFILE_BASIC_INFO)
            .then()
            .extract()
            .response();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(response.getStatusCode(), 200, "Incorrect status code!");
    softAssert.assertEquals(response.as(ProfileBasicInfo.class), expectedProfile,
        "Changed profile basic info wasn't sent to the server!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifyBasicInfoWasChanged() {
    ProfileBasicInfo changedProfileFromEnvironment = given()
        .queryParam(queryParameterUserId, userId)
        .when()
        .get(PROFILE_BASIC_INFO)
        .then()
        .extract()
        .as(ProfileBasicInfo.class);
    assertEquals(changedProfileFromEnvironment, expectedProfile,
        "Changed profile basic info wasn't saved!");
  }
}
