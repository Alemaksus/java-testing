package com.epmrdpt.regression.training_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ParticipantsTrainingScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_24596_VerifyThatTheNameLinkOnTheParticipantsPageRedirectsToTheQuestionaryPage",
    groups = {"full", "regression", "manager", "deprecated"})
public class EPMRDPT_24596_VerifyThatTheNameLinkOnTheParticipantsPageRedirectsToTheQuestionaryPage {

  private final String TRAINING_NAME = "AutoTest_WithParticipants";
  private final String STUDENT_NAME = "QQ AA";
  private ParticipantsTrainingScreen participantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    participantsTrainingScreen = new ParticipantsTrainingScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asTrainingManager());
  }

  @Test(priority = 1)
  public void checkTrainingManagerScreenLoading() {
    assertTrue(
        new HeaderScreen().clickReactTrainingManagementLink().isScreenLoaded(),
        "Training manager screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkParticipantsScreenLoading() {
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING_NAME);
    new ReactDetailTrainingScreen()
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility();
    assertTrue(participantsTrainingScreen.isScreenLoaded(),
        "Participant screen isn't loaded!");
  }

  @Test(priority = 3)
  public void checkThatParticipantPresentInTraining() {
    assertTrue(
        participantsTrainingScreen
            .isParticipantFindByNameDisplayed(STUDENT_NAME),
        String.format(
            "Student %s isn't displayed in participant table!",
            STUDENT_NAME));
  }

  @Test(priority = 4)
  public void checkThatQuestionaryPageIsOppened() {
    assertTrue(participantsTrainingScreen.clickParticipantByName(STUDENT_NAME).isScreenLoaded(),
        "Questionary screen isn't loaded!");
  }
}
