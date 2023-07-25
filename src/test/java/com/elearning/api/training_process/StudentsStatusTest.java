package com.epmrdpt.api.training_process;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_STUDENTS_STATUS;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.StudentStatusInGroup;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "Verify 'Students Statuses'",
    groups = {"api_deprecated"})
public class StudentsStatusTest {

  private final int groupId = 4464;
  private final String studentId = "9";
  private final String trainingStatus = "Training in Progress";
  private Set<StudentStatusInGroup> studentsStatuses;

  @BeforeClass
  public void getStudentsStatusesInGroup() {
    studentsStatuses = new HashSet<>(
        given()
            .when()
            .pathParam("groupId", groupId)
            .get(TRAINING_PROCESS_STUDENTS_STATUS)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .getList(".", StudentStatusInGroup.class)
    );
  }

  @Test(priority = 1)
  public void verifyThatThereIsAtLeastOneStudentInStatuses() {
    assertFalse(
        studentsStatuses.isEmpty(),
        "No statuses!"
    );
  }

  @Test(priority = 2)
  public void verifyPresenceOfStudentInGroup() {
    assertTrue(
        studentsStatuses
            .stream()
            .anyMatch(studentStatusInGroup -> studentStatusInGroup.getId().equals(studentId)),
        "The specific student id is not present in statuses!"
    );
  }

  @Test(priority = 3)
  public void verifyPresenceOfStudentWithSpecificStatusInGroup() {
    assertTrue(
        studentsStatuses
            .stream()
            .anyMatch(
                studentStatusInGroup ->
                    studentStatusInGroup.getStatusInGroup().equals(trainingStatus)
            ),
        "The status 'Training in Progress' is not present in statuses!"
    );
  }
}
