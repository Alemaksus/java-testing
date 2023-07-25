package com.epmrdpt.api.training_process;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_EXTERNAL_TASK_CONTAINER;
import static io.restassured.RestAssured.given;

import com.epmrdpt.bo.ExternalTask;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify External task container",
    groups = {"api_deprecated"})
public class ExternalTaskContainerTest {

  private final int externalTaskId = 9226;
  private final String externalTaskName = "without exp";

  @Test
  public void verifyExternalTaskContainer() {
    SoftAssert softAssert = new SoftAssert();
    ExternalTask externalTask = given()
        .when()
        .get(TRAINING_PROCESS_EXTERNAL_TASK_CONTAINER + "/" + externalTaskId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .extract()
        .as(ExternalTask.class);
    softAssert.assertTrue(externalTask.getId() == externalTaskId,
        "External task's Id isn't equal to the input value!");
    softAssert.assertEquals(externalTask.getName(), externalTaskName,
        "External task's Name isn't equal to the given value!");
    softAssert.assertAll();
  }
}
