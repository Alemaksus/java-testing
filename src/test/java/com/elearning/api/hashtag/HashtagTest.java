package com.epmrdpt.api.hashtag;

import static com.epmrdpt.api.Endpoints.HASHTAGS_DELETE;
import static com.epmrdpt.api.Endpoints.HASHTAGS_GET_ALL;
import static com.epmrdpt.api.Endpoints.HASHTAGS_GET_ALL_WITH_NEWS;
import static com.epmrdpt.api.Endpoints.HASHTAGS_CREATE;
import static com.epmrdpt.api.Endpoints.HASHTAGS_CREATE_MANY;
import static io.restassured.RestAssured.given;

import com.epmrdpt.bo.hashtag.HashtagFactory;
import com.epmrdpt.bo.hashtag.Hashtag;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "Verify 'Hashtag'",
    groups = {"api_deprecated"})
public class HashtagTest {

  private String hashtagParameter = "hashtagName";
  private static String hashtagName = "#AUTOTESTHASHTAGTODELETE";
  private static String firstHashtagName = hashtagName + "1";
  private static String secondHashtagName = hashtagName + "2";
  private Hashtag hashtagWithName = HashtagFactory
      .getHashtagWithName(hashtagName);
  private Hashtag[] twoHashtagsWithName = HashtagFactory
      .getTwoHashtagWithName(firstHashtagName, secondHashtagName);

  @Test(priority = 1)
  public void verifyGetAll() {
    given()
        .when()
        .get(HASHTAGS_GET_ALL)
        .then()
        .statusCode(200);
  }

  @Test(priority = 1)
  public void verifyGetAllWithNews() {
    given()
        .when()
        .get(HASHTAGS_GET_ALL_WITH_NEWS)
        .then()
        .statusCode(200);
  }

  @Test(priority = 2)
  public void verifyHashtagCreation() {
    given()
        .contentType(ContentType.JSON).body(hashtagWithName)
        .when()
        .post(HASHTAGS_CREATE)
        .then()
        .statusCode(200);
  }

  @Test(priority = 2)
  public void verifyMultipleHashtagCreation() {
    given()
        .contentType(ContentType.JSON).body(twoHashtagsWithName)
        .when()
        .post(HASHTAGS_CREATE_MANY)
        .then()
        .statusCode(200);
  }

  @Test(dataProvider = "Provider data for hashtags", priority = 3)
  public void verifyHashtagDeletion(String hashtag) {
    given()
        .queryParam(hashtagParameter, hashtag)
        .when()
        .delete(HASHTAGS_DELETE)
        .then()
        .statusCode(200);
  }

  @DataProvider(name = "Provider data for hashtags")
  private static Object[][] dataProviderWithHashtags() {
    return new Object[][]{
        {hashtagName},
        {firstHashtagName},
        {secondHashtagName}
    };
  }
}
