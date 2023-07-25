package com.epmrdpt.api.student_learning;

import static com.epmrdpt.api.Endpoints.STUDENT_LEARNING_EVENTS;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Events'",
    groups = {"api_deprecated"})
public class StudentLearningTest {

  @Test
  public void verifyEvents() {
    given()
        .when()
        .get(STUDENT_LEARNING_EVENTS)
        .then()
        .statusCode(200);
  }
}
