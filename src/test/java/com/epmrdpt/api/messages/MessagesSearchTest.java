package com.epmrdpt.api.messages;

import static com.epmrdpt.api.Endpoints.MESSAGES_SEARCH;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

@Test(description = "Verify 'Search'",
    groups = {"api_deprecated"})
public class MessagesSearchTest {

  private final String term = "Response to your application";

  @Test
  public void verifySearch() {
    given()
        .when()
        .queryParam("term", term)
        .get(MESSAGES_SEARCH)
        .then()
        .statusCode(200)
        .contentType(JSON)
        .body("Items", hasSize(greaterThan(0)));
  }
}
