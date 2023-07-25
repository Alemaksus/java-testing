package com.epmrdpt.api.training_process;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_TASK_JOURNAL_OFFLINE_TASKS_MARK_FORM;
import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_TASK_JOURNAL_TASK_COMMENTS;
import static com.epmrdpt.framework.properties.ApiPropertyService.getValueOf;
import static io.restassured.RestAssured.given;

import com.epmrdpt.bo.task_journal.OfflineTaskMarkForm;
import com.epmrdpt.framework.properties.ApiProperty;
import org.apache.commons.lang3.Range;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify 'TaskJournalController'",
    groups = {"api_deprecated"})
public class TaskJournalControllerTest {

  private final int offlineTaskId = Integer.parseInt(
      getValueOf(ApiProperty.TASK_JOURNAL_CONTROLLER_OFFLINE_TASK_ID));
  private final int studentId = 325903;
  private String comment = "Auto_comment";

  @Test(description = "Get offline task mark form info")
  public void verifyTaskJournalController() {
    OfflineTaskMarkForm offlineTaskMarkForm = given()
        .pathParam("offlineTaskId", offlineTaskId)
        .queryParam("studentId", studentId)
        .when()
        .get(TRAINING_PROCESS_TASK_JOURNAL_OFFLINE_TASKS_MARK_FORM)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .as(OfflineTaskMarkForm.class);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(offlineTaskMarkForm.getTaskId(), offlineTaskId,
        "Offline task's Id isn't equal to the input value!");
    softAssert.assertEquals(offlineTaskMarkForm.getStudentId(), studentId,
        "Student's Id isn't equal to the input value!");
    softAssert.assertTrue(Range.between(1, 200).contains(offlineTaskMarkForm.getMaxMark()),
        "Offline task's max mark is not in the range of valid values!");
    softAssert.assertAll();
  }

  @Test(description = "Get task comment of student")
  public void verifyTaskJournalControllerTaskComment() {
    given()
        .queryParam("taskId", offlineTaskId)
        .queryParam("studentId", studentId)
        .when()
        .get(TRAINING_PROCESS_TASK_JOURNAL_TASK_COMMENTS)
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .body("Comments[0].Comment", Matchers.contains(comment));
  }
}
