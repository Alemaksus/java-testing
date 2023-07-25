package com.epmrdpt.smoke.training_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_100742_VerifyThatByClickingOnTheYesButtonTheEnglishTestIsReassignedToTheUser",
    groups = {"full", "smoke"})
public class EPMRDPT_100742_VerifyThatByClickingOnTheYesButtonTheEnglishTestIsReassignedToTheUser {

  private static final String TRAINING_NAME = "AutoTestTraining100742";
  private static final String USER_NAME = "test LastName4785";
  private static final String ENGLISH_TEST_STATUS = "Assigned";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndChooseTraining() {
    reactParticipantsTrainingScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickTrainingNameByName(TRAINING_NAME)
        .clickReactParticipantsTab()
        .typeSearchByNameInput(USER_NAME)
        .clickApplyButton()
        .waitSpinnerOfLoadingInvisibility()
        .clickStudentCheckBoxByStudentName(USER_NAME);
  }

  @Test (priority = 1)
  public void checkConfirmActionPopUpDisplayed() {
    reactParticipantsTrainingScreen.clickAssignEnglishTestButton();
    assertTrue(reactParticipantsTrainingScreen.isConfirmActionPopUpDisplayed(),
        "The 'Confirm Action' popup fails to appear");
  }

  @Test (priority = 2)
  public void checkConfirmedPopUpDisplayed() {
    reactParticipantsTrainingScreen.clickYesInEnglishTestPopUpWindow();
    assertTrue(reactParticipantsTrainingScreen.isEnglishTestConfirmedMessageDisplayed(),
        "Confirmed message was not shown");
  }

  @Test (priority = 3)
  public void checkAssignStatusOfEnglishTest() {
    reactParticipantsTrainingScreen.waitSpinnerOfLoadingInvisibility();
    assertEquals(reactParticipantsTrainingScreen.getStudentEnglishTestResultText(USER_NAME),
        ENGLISH_TEST_STATUS, "Assigned status of english test was not displayed");
  }
}
