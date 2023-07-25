package com.epmrdpt.api.profile;

import static com.epmrdpt.api.Endpoints.PROFILE_CONTACTS_INFO;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.profile.Contacts;
import com.epmrdpt.bo.profile.ProfileDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify 'Profile contacts'",
    groups = {"api_deprecated"})
public class ProfileContactsTest {

  private final int userId = 325944;
  private final String queryParameterUserId = "userId";
  private Contacts originalContacts;
  private Contacts expectedContacts = ProfileDataFactory.getGeneratedContacts();

  @Test(priority = 1)
  public void verifyGetContactsInfo() {
    originalContacts =
        given()
            .queryParam(queryParameterUserId, userId)
            .when()
            .get(PROFILE_CONTACTS_INFO)
            .then()
            .statusCode(200)
            .and()
            .extract()
            .as(Contacts.class);
  }

  @Test(priority = 2)
  public void verifyPostContactsInfo() {
    expectedContacts.withAllowShowingContacts(!originalContacts.getAllowShowingContacts());
    Response response =
        given().queryParam(queryParameterUserId, userId)
            .when()
            .contentType(ContentType.JSON)
            .body(expectedContacts)
            .post(PROFILE_CONTACTS_INFO)
            .then()
            .extract()
            .response();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(response.getStatusCode(), 200, "Incorrect status code!");
    softAssert.assertEquals(response.as(Contacts.class), expectedContacts,
        "New contacts weren't sent to the server!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifyContactsInfoWasChanged() {
    Contacts changedContactsFromEnvironment =
        given()
            .queryParam(queryParameterUserId, userId)
            .when()
            .get(PROFILE_CONTACTS_INFO)
            .then()
            .extract()
            .as(Contacts.class);
    assertEquals(changedContactsFromEnvironment, expectedContacts,
        "New contacts weren't saved!");
  }
}
