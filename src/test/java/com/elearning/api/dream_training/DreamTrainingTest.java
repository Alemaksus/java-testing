package com.epmrdpt.api.dream_training;

import static com.epmrdpt.api.Endpoints.DREAM_TRAINING;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

@Test(description = "Verify 'Dream Training'",
    groups = {"api_deprecated"})
public class DreamTrainingTest {

  @Test
  public void verifyDreamTraining() {
    given()
        .when()
        .get(DREAM_TRAINING)
        .then()
        .statusCode(200);
  }
}
