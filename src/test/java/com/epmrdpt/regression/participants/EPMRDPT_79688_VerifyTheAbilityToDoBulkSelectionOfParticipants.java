package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;


import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79688_VerifyTheAbilityToDoBulkSelectionOfParticipants",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_79688_VerifyTheAbilityToDoBulkSelectionOfParticipants {

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private String trainingName = "AutoTest_RegistrationOpen_GroupWithStudent";

  @BeforeClass
  public void loginAndGoToParticipantsPage() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(asTrainingManager());
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test
  public void verifyTheAbilityToDoBulkSelectionOfParticipants() {
    reactParticipantsTrainingScreen.selectAllParticipantsInTable();
    SoftAssert softAssert = new SoftAssert();
    softAssert
        .assertEquals(reactParticipantsTrainingScreen.getNumberOfSelectedParticipantsFromLabel(),
            reactParticipantsTrainingScreen.getNumberOfSelectedCheckboxesInTable(),
            "Number of 'Participants selected' isn't equal to the number of checked checkboxes!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.areAllStudentsCheckboxesChecked(),
        "Not all students checkboxes are checked!");
    softAssert.assertAll();
  }
}
