package com.epmrdpt.api.messages;

import static com.epmrdpt.api.Endpoints.MESSAGES;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Messages'",
    groups = {"api_deprecated"})
public class MessagesTest {

  @Test
  public void verifyMessages() {
    given()
        .when()
        .get(MESSAGES)
        .then()
        .statusCode(200);
  }
}
