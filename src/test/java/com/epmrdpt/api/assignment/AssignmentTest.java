package com.epmrdpt.api.assignment;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_ASSIGNMENT_GET_HISTORY;
import static io.restassured.RestAssured.given;

import com.epmrdpt.framework.properties.ApiProperty;
import com.epmrdpt.framework.properties.ApiPropertyService;
import org.testng.annotations.Test;

@Test(description = "Verify 'Assignment'",
    groups = {"api_deprecated"})
public class AssignmentTest {

  private final String assignmentId = ApiPropertyService.getValueOf(ApiProperty.ASSIGNMENT_ID);
  private final String taskId = ApiPropertyService.getValueOf(ApiProperty.TASK_ID);

  @Test
  public void verifyAssignment() {
    given()
        .when()
        .queryParam("taskId", taskId)
        .queryParam("assignmentId", assignmentId)
        .get(TRAINING_PROCESS_ASSIGNMENT_GET_HISTORY)
        .then()
        .statusCode(200);
  }
}
