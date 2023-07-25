package com.epmrdpt.api.feedback;

import static com.epmrdpt.api.Endpoints.FEEDBACK_GET_MODEL;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Feedback'",
    groups = {"api_deprecated"})
public class FeedbackTest {

  @Test
  public void verifyGetModel() {
    given()
        .when()
        .get(FEEDBACK_GET_MODEL)
        .then()
        .statusCode(200);
  }
}
