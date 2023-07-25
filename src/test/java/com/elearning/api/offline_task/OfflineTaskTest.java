package com.epmrdpt.api.offline_task;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_OFFLINE_TASKS;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import org.testng.annotations.Test;

@Test(description = "Verify 'OfflineTask'",
    groups = {"api_deprecated"})
public class OfflineTaskTest {

  private final String offlineTaskId = "1";

  @Test
  public void verifyOfflineTask() {
    given()
        .when()
        .get(TRAINING_PROCESS_OFFLINE_TASKS + "/" + offlineTaskId)
        .then()
        .statusCode(200)
        .contentType(JSON);
  }
}
