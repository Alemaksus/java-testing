package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainersTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_12981_VerifyStudentCanSeeTheInformationAboutTrainersOnTrainersTab",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_12981_VerifyStudentCanSeeTheInformationAboutTrainersOnTrainersTab {

  private TrainersTabOnLearningPageScreen trainersTabOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainersTabOnLearningPageScreen = new TrainersTabOnLearningPageScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asStudentForLearningPage());
    new HeaderScreen().clickLearningButton();
    trainersTabOnLearningPageScreen.clickTrainersTab();
  }

  @Test
  public void checkTrainersPhotoImageDisplayed() {
    assertTrue(trainersTabOnLearningPageScreen.isTrainersProfileImageDisplayed(),
        "Trainer's photo isn't displayed!");
  }

  @Test
  public void checkTrainersNameDisplayed() {
    assertTrue(trainersTabOnLearningPageScreen.isTrainersNameDisplayed(),
        "Trainer's name isn't displayed!");
  }

  @Test
  public void checkUserCanSeeTrainersPhoneNumber() {
    assertTrue(trainersTabOnLearningPageScreen.isTrainersTabDisplayed(),
        "Trainer's phone number isn't displayed!");
  }

  @Test
  public void checkUserCanSeeTrainersMail() {
    assertTrue(trainersTabOnLearningPageScreen.isTrainersMailDisplayed(),
        "Trainer's mail isn't displayed!");
  }

  @Test
  public void checkUserCanSeeTrainersSkype() {
    assertTrue(trainersTabOnLearningPageScreen.isTrainersSkypeDisplayed(),
        "Trainer's skype isn't displayed!");
  }
}
