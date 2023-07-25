package com.epmrdpt.smoke.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13186_VerifyIfTrainingManagerCanSearchParticipantsViaSearchFields",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13186_VerifyIfTrainingManagerCanSearchParticipantsViaSearchFields {

  private final String trainingName = "C++ Gomel";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
  }

  @Test(priority = 1)
  public void checkParticipantsScreenLoading() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager());
    new HeaderScreen()
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitScreenLoaded()
        .clickReactParticipantsTab()
        .waitScreenLoading();
    assertTrue(
        reactParticipantsTrainingScreen
            .isScreenLoaded(),
        "Participants  screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainingManagerCanSearchParticipantsViaSearchFields() {
    SoftAssert softAssert = new SoftAssert();
    reactParticipantsTrainingScreen
        .waitParticipantsTableElementsForVisibility()
        .mouseOverFoundElementsLabel();
    int participantsNameSize = reactParticipantsTrainingScreen
        .getParticipantNamesList()
        .size();
    int randomNameIndex = new Random()
        .nextInt(participantsNameSize) + 1;
    String selectedName = reactParticipantsTrainingScreen
        .getParticipantNameTextByIndex(randomNameIndex);
    reactParticipantsTrainingScreen
        .clickSearchByNameInput()
        .typeSearchByNameInput(selectedName)
        .clickApplyButton()
        .waitScreenLoading();
    softAssert.assertTrue(
        reactParticipantsTrainingScreen
            .isParticipantFindByNameDisplayed(selectedName),
        format("Participant %s  isn't displayed in table after search via 'search by name' field!",
            selectedName));
    softAssert.assertTrue(
        reactParticipantsTrainingScreen
            .getParticipantNames()
            .stream()
            .allMatch(name -> name.contains(selectedName)),
        format(
            "Participant table contains not only the names %s that was input in 'search by name' field!",
            selectedName));
    softAssert.assertAll();
  }
}
