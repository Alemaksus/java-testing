package com.epmrdpt.api.group_participant;

import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS;
import static com.epmrdpt.api.Endpoints.TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS_LIST;
import static io.restassured.RestAssured.given;

import com.epmrdpt.bo.participant.GroupParticipant;
import com.epmrdpt.bo.participant.Participant;
import com.epmrdpt.bo.participant.ParticipantDate;
import com.epmrdpt.utils.RandomUtils;
import io.restassured.http.ContentType;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify 'GroupParticipant'",
    groups = {"api_deprecated"})
public class GroupParticipantTest {

  private final String pageNumberQueryParameter = "pageNumber";
  private final String itemsPerPageQueryParameter = "itemsPerPage";
  private final int pageNumber = 1;
  private final int itemsPerPage = 10;

  private final int planId = 2860;
  private final int groupId = 4032;
  private List<Participant> participantsAvailableForAddingToGroupBeforeAdding;
  private Participant participant;

  @Test(priority = 1)
  public void verifyUserCanGetParticipantsListThatCanBeAddedToGroup() {
    participantsAvailableForAddingToGroupBeforeAdding = getCurrentParticipantsForPost();
    Assert.assertFalse(participantsAvailableForAddingToGroupBeforeAdding.isEmpty(),
        "List of participants available for adding to group is empty!");
  }

  @Test(priority = 2)
  public void verifyThatUserCanAddParticipantToGroup() {
    int randomParticipantIndex = RandomUtils
        .getRandomNumber(participantsAvailableForAddingToGroupBeforeAdding.size());
    List<GroupParticipant> participantsInGroupBeforeAdding = getCurrentParticipantsInGroup();
    participant = participantsAvailableForAddingToGroupBeforeAdding
        .get(randomParticipantIndex);
    given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .body(Arrays.asList(participant))
        .contentType(ContentType.JSON)
        .when()
        .post(TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS)
        .then()
        .statusCode(HttpStatus.SC_OK);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(participantsInGroupBeforeAdding.stream()
            .noneMatch(p -> p.getId() == participant.getId()),
        "List of participants before adding to group still contains current participant!");
    softAssert.assertTrue((getCurrentParticipantsInGroup().stream()
            .anyMatch(p -> p.getId() == participant.getId())),
        "Participant wasn't added to the group!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifyThatUserCanChangeAssignmentDateTime() {
    String localDateTime = getLocalUTCTime();
    ParticipantDate dateToBeChangedTo = new ParticipantDate(participant.getId(), localDateTime);

    given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .body(Arrays.asList(dateToBeChangedTo))
        .contentType(ContentType.JSON)
        .when()
        .put(TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS)
        .then()
        .statusCode(HttpStatus.SC_OK);
    String shortDate = localDateTime.substring(0, localDateTime.indexOf('.'));
    Assert.assertTrue(
        getCurrentParticipantsInGroup().stream().anyMatch(p -> (p.getManualAssignmentDateTime()
            .contains(shortDate))), "Join date wasn't changed!");
  }

  @Test(priority = 4)
  public void checkUserCanDeleteParticipantFromGroup() {
    given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .body(Arrays.asList(participant.getId()))
        .contentType(ContentType.JSON)
        .when()
        .delete(TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS)
        .then()
        .statusCode(HttpStatus.SC_OK);

    List<GroupParticipant> groupParticipantsAfterUserDelete = getCurrentParticipantsInGroup();
    List<Participant> currentParticipantsAvailableForAddingToGroup = getCurrentParticipantsForPost();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(groupParticipantsAfterUserDelete.stream()
            .noneMatch(p -> p.getId() == participant.getId()),
        "Participant wasn't deleted from group!");
    softAssert.assertTrue(currentParticipantsAvailableForAddingToGroup.stream()
            .anyMatch(p -> p.getId() == participant.getId()),
        "List of participants available for adding to group doesn't contain current participant!");
    softAssert.assertAll();
  }

  private String getLocalUTCTime() {
    Instant now = Instant.now();
    return now.toString();
  }

  private List<GroupParticipant> getCurrentParticipantsInGroup() {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .queryParams(pageNumberQueryParameter, pageNumber)
        .queryParams(itemsPerPageQueryParameter, itemsPerPage)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS_LIST)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .extract().body().jsonPath().getList("Items", GroupParticipant.class);
  }

  private List<Participant> getCurrentParticipantsForPost() {
    return given()
        .pathParam("planId", planId)
        .pathParam("groupId", groupId)
        .when()
        .get(TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract().body().jsonPath().getList("$", Participant.class);
  }
}
