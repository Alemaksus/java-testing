package com.epmrdpt.api.graduate_journal;

import static com.epmrdpt.api.Endpoints.*;
import static io.restassured.RestAssured.given;

import com.epmrdpt.bo.graduate_journal_entity.GraduateJournalEntity;
import com.epmrdpt.bo.graduate_journal_entity.GraduateJournalEntityFactory;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

@Test(description = "Verify 'Graduate Journal'",
    groups = {"api_deprecated"})
public class GraduateJournalTest {

  private final int groupId = 2;
  private final String groupParameter = "groupId";
  private final GraduateJournalEntity defaultGraduateJournal = GraduateJournalEntityFactory
      .getDefaultGraduateJournal();

  @Test
  public void verifyGraduateJournal() {
    given()
        .queryParam(groupParameter, groupId)
        .contentType(ContentType.JSON).body(defaultGraduateJournal)
        .when()
        .post(TRAINING_PROCESS_GRADUATE_JOURNAL)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyStatus() {
    given()
        .queryParam(groupParameter, groupId)
        .when()
        .get(TRAINING_PROCESS_GRADUATE_JOURNAL_STATUS)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyExcel() {
    given()
        .queryParam(groupParameter, groupId)
        .when()
        .get(TRAINING_PROCESS_GRADUATE_JOURNAL_EXCEL)
        .then()
        .statusCode(200);
  }

  @Test
  public void verifyStatusColumn() {
    given()
        .queryParam(groupParameter, groupId)
        .contentType(ContentType.JSON).body(defaultGraduateJournal)
        .when()
        .post(TRAINING_PROCESS_GRADUATE_JOURNAL_STATUS_COLUMN)
        .then()
        .statusCode(200);
  }
}
