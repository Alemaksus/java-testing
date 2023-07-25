package com.epmrdpt.api.training_process;

import com.epmrdpt.bo.TrainerGroup;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epmrdpt.api.Endpoints.*;
import static io.restassured.RestAssured.given;

@Test(description = "Verify 'Trainers groups'",
        groups = {"api_deprecated"})

public class TrainerGroupsTest {
    private static int trainerId = 1;
    private static int trainingPlanId = 1;

    @Test
    public void verifySearch() {
        TrainerGroup[] trainerGroup = given()
                .when()
                .get(TRAINING_PROCESS_GROUPS, trainerId, trainingPlanId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .as(TrainerGroup[].class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(trainerGroup.length,0,
                "Unexpected length of all skill description list!");
        softAssert.assertEquals(trainerGroup[0].getId(), trainerId,
                "Group Id isn't equal to the input value!");
        softAssert.assertFalse(trainerGroup[0].getName().isEmpty(),
                "Trainer group does not contain a name");
        softAssert.assertFalse(trainerGroup[0].getStartTrainingDate().isEmpty(),
                "Trainer group does not contain start training date");
        softAssert.assertFalse(trainerGroup[0].getFinishTrainingDate().isEmpty(),
                "Trainer group does not contain finish training date");
        softAssert.assertAll();
    }
}
