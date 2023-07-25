package com.epmrdpt.api.group_journal;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_BREADCRUMBS_CONTROLLER;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.GroupJournal;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(description = "Verify 'GroupJournal'",
    groups = {"api_deprecated"})
public class GroupJournalTest {

  private final int groupId = 1;

  @Test
  public void verifyGroupJournal() {
    GroupJournal groupJournal = given()
        .when()
        .get(TRAINING_PROCESS_BREADCRUMBS_CONTROLLER, groupId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .extract()
        .body()
        .as(GroupJournal.class);
    assertEquals(groupJournal.getTrainingId(), groupId, "Group Id isn't equal to the input value!");
    assertFalse(groupJournal.getTrainingName().isEmpty(), "Training name can not be empty");
  }
}
