package com.epmrdpt.api.assignment_container;

import static com.epmrdpt.api.Endpoints.ASSIGNMENT_CONTAINERS_ACTIVE_LIST;
import static com.epmrdpt.api.Endpoints.ASSIGNMENT_CONTAINERS_ALL_LIST;
import static com.epmrdpt.api.Endpoints.ASSIGNMENT_CONTAINER_BY_ID;
import static com.epmrdpt.api.Endpoints.ASSIGNMENT_CONTAINER_ACTIVE;
import static com.epmrdpt.api.Endpoints.ASSIGNMENT_CONTAINER_LINK;
import static com.epmrdpt.api.Endpoints.ASSIGNMENT_CONTAINER_TAKE_TEST;
import static io.restassured.RestAssured.given;

import com.epmrdpt.framework.properties.ApiProperty;
import com.epmrdpt.framework.properties.ApiPropertyService;
import org.testng.annotations.Test;

@Test(description = "Verify 'Assignment Container'",
    groups = {"api_deprecated"})
public class AssignmentContainerTest {
  private final String containerId = ApiPropertyService.getValueOf(ApiProperty.ASSIGNMENT_CONTAINER_ID);
  private final String planOwnerId = ApiPropertyService.getValueOf(ApiProperty.PLAN_OWNER_ID);
  private final String takeTestId = ApiPropertyService.getValueOf(ApiProperty.TAKE_TEST_ID);

  @Test(priority = 1)
  public void verifyAssignmentContainersAll() {
    given()
        .when()
        .get(ASSIGNMENT_CONTAINERS_ALL_LIST)
        .then()
        .statusCode(200);
  }

  @Test(priority = 2)
  public void verifyActiveAssignmentContainersActive() {
    given()
        .when()
        .get(ASSIGNMENT_CONTAINERS_ACTIVE_LIST)
        .then()
        .statusCode(200);
  }

  @Test(priority = 3)
  public void verifyAssignmentContainer() {
    given()
        .when()
        .get(ASSIGNMENT_CONTAINER_BY_ID,containerId)
        .then()
        .statusCode(200);
  }

  @Test(priority = 4)
  public void verifyActiveAssignmentContainer() {
    given()
        .when()
        .get(ASSIGNMENT_CONTAINER_ACTIVE,containerId)
        .then()
        .statusCode(200);
  }

  @Test(priority = 5)
  public void verifyLink() {
    given()
        .when()
        .given()
        .get(ASSIGNMENT_CONTAINER_LINK, containerId, planOwnerId)
        .then()
        .statusCode(200);
  }

 @Test(priority = 6)
 public void verifyTakeTest(){
   given()
      .when()
      .get(ASSIGNMENT_CONTAINER_TAKE_TEST,takeTestId)
      .then()
      .statusCode(200);
 }
}
