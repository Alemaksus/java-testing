package com.epmrdpt.api.trainer;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_TRAINERS;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


import org.testng.annotations.Test;

@Test(description = "Verify 'Trainer'",
    groups = {"api_deprecated"})
public class TrainerTest {

  private final String groupId = "1";

  @Test
  public void verifySearch() {
    given()
        .when()
        .queryParam("groupIds", groupId)
        .get(TRAINING_PROCESS_TRAINERS)
        .then()
        .statusCode(200)
        .contentType(JSON);
  }
}
