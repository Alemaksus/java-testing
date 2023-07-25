package com.epmrdpt.api.training_process;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_EVENTS_FORM_DATA_BY_GROUPS;
import static io.restassured.RestAssured.given;

import com.epmrdpt.bo.events.EventEditForm;
import com.epmrdpt.bo.events.EventTrainer;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "Verify 'ScheduleEventController' by groups",
    groups = {"api_deprecated"})
public class TrainersAndLocationsByGroupsTest {
  private final List<Integer> groupIds = Arrays.asList(4012, 4018, 4021);
  private final String displayedName = "AutoTrainer AutoTrainer";
  private final int id = 325913;
  private final String name = "AutoTrainer AutoTrainer";

  @Test(description = "Gets trainers and locations for the event edit form by group ids.")
  public void verifyScheduleEventController() {
    EventEditForm eventEditForm = given()
        .queryParam("groupIds", groupIds)
        .when()
        .get(TRAINING_PROCESS_EVENTS_FORM_DATA_BY_GROUPS)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .as(EventEditForm.class);
    EventTrainer firstTrainer = eventEditForm.getTrainers().get(0);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(firstTrainer.getDisplayName(), displayedName,
        "Trainer's displayed name is not equal to the input value!");
    softAssert.assertEquals(firstTrainer.getId(), id,
        "Trainer's ID is not equal to the input value!");
    softAssert.assertEquals(firstTrainer.getName(), name,
        "Trainer's name is not equal to the input value!");
    softAssert.assertAll();
  }
}
