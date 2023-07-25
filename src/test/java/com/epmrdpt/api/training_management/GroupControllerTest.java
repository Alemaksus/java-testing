package com.epmrdpt.api.training_management;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID;
import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_CONTROLLER_SUMMARY;
import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static rp.org.apache.http.HttpStatus.SC_OK;

import com.epmrdpt.bo.group_controller.Group;
import com.epmrdpt.bo.group_controller.GroupInfo;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "Verify 'Group Controller'",
    groups = {"api_deprecated"})
public class GroupControllerTest {

  private final String newName = "I learned 4 API requests";
  private final int planId = 3317;
  private int groupId;
  GroupInfo createdGroup = new GroupInfo();
  List<Group> groupsBeforePost = new ArrayList<>();
  List<Group> groupsAfterPost = new ArrayList<>();
  List<Group> groupsAfterDelete = new ArrayList<>();

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void deleteGroupWithTestNameIfExist() {
    checkThatGroupWithSameNameToPutRequestNotExist();
  }

  @Test(priority = 1, description = "Verify GET 'Get groups'")
  public void checkThatTrainingIsExist() {
    groupsBeforePost = given()
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS, planId)
        .then()
        .statusCode(SC_OK)
        .extract().body().jsonPath().getList("Data", Group.class);
  }

  @Test(priority = 2, description = "Verify POST 'Create group'")
  public void checkThatYouCanAddTheGroupToTraining() {
    ValidatableResponse addGroupToTraining = given()
        .when()
        .post(TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS, planId)
        .then()
        .statusCode(SC_OK);

    groupsAfterPost = given()
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS, planId)
        .then()
        .statusCode(SC_OK)
        .extract().body().jsonPath().getList("Data", Group.class);

    assertNotEquals(
        groupsBeforePost,
        groupsAfterPost,
        "Group was not posted!");
  }

  @Test(priority = 3, description = "Verify GET 'Get group by Id'")
  public void checkThatGroupWasPosted() {
    groupId = groupsAfterPost.get(groupsAfterPost.size() - 1).getId();

    createdGroup = given()
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID, planId, groupId)
        .then()
        .statusCode(SC_OK)
        .extract().body().jsonPath().getObject("GroupInfo", GroupInfo.class);
  }

  @Test(priority = 4, description = "Verify PUT 'Update group by Id'")
  public void checkThatYouCanChangeDataOfCreatedGroup() {
    createdGroup.setName(newName);

    ValidatableResponse changeNameOfGroup = given()
        .contentType(JSON)
        .accept(JSON)
        .body(createdGroup)
        .put(TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID, planId, groupId)
        .then()
        .statusCode(SC_OK);

    GroupInfo groupWithChangedName = given()
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID, planId, groupId)
        .then()
        .extract().body().jsonPath().getObject("GroupInfo", GroupInfo.class);

    assertEquals(
        groupWithChangedName.getName(),
        newName,
        "Group name wasn't changed!"
    );
  }

  @Test(priority = 5, description = "Verify GET 'Get hint group by Id'")
  public void verifySummary() {
    ValidatableResponse groupInformation = given()
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_SUMMARY, planId, groupId)
        .then()
        .statusCode(SC_OK);
  }

  @Test(priority = 6, description = "Verify DELETE 'Delete group by Id'")
  public void checkThatYouCanDeleteTheGroup() {
    ValidatableResponse deleteGroup = given()
        .when()
        .delete(TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID, planId, groupId)
        .then()
        .statusCode(SC_OK);

    groupsAfterDelete = given()
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS, planId)
        .then()
        .statusCode(SC_OK)
        .extract().body().jsonPath().getList("Data", Group.class);

    Assert.assertEquals(
        groupsBeforePost,
        groupsAfterDelete,
        "Group was not deleted!");
  }

  private void checkThatGroupWithSameNameToPutRequestNotExist() {
    if (given()
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS, planId)
        .then()
        .statusCode(SC_OK)
        .log().all()
        .extract().body().jsonPath()
        .getList("Data", Group.class)
        .stream().anyMatch(group -> Objects.equals(group.getName(), newName))) {
      for (Group group : given()
          .when()
          .get(TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS, planId)
          .then()
          .statusCode(SC_OK)
          .extract().body().jsonPath()
          .getList("Data", Group.class)) {
        if ((Objects.equals(group.getName(), newName))) {
          int existGroupId = group.getId();
          given()
              .when()
              .delete(TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID, planId, existGroupId)
              .then()
              .statusCode(SC_OK);
        }
      }
    }
  }
}
