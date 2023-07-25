package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainersTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13255_VerifyUserHasAccessToTrainersTab",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_13255_VerifyUserHasAccessToTrainersTab {

  private HeaderScreen headerScreen;
  private TrainersTabOnLearningPageScreen trainersTabOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    trainersTabOnLearningPageScreen = new TrainersTabOnLearningPageScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asStudentForLearningPage());
  }

  @Test(priority = 1)
  public void checkTrainersTabDisplayed() {
    headerScreen.clickLearningButton();
    assertTrue(trainersTabOnLearningPageScreen.clickTrainersTab().isScreenLoaded(),
        "Trainers page isn't displayed!");
  }

  @Test(priority = 2)
  public void checkGroupNamesContentDisplayed() {
    assertTrue(trainersTabOnLearningPageScreen.isGroupNameDisplayed(),
        "Group names isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTrainersCardsDisplayed() {
    assertTrue(trainersTabOnLearningPageScreen.isTrainersCardDisplayed(),
        "Trainer's cards isn't displayed!");
  }
}
