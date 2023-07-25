package com.epmrdpt.smoke.training_management;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13268_VerifyThatTrainingManagerCanViewTrainerInfo",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13268_VerifyThatTrainingManagerCanViewTrainerInfo {

  private String trainingName = "AutoTest Planned Status";
  private String groupName = "Automated Testing 2";
  private String trainerName = "AutoTrainer AutoTrainer";
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13268_VerifyThatTrainingManagerCanViewTrainerInfo(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
  }

  @Test(priority = 1)
  public void checkThatHintWithTrainerInfoDisplayed() {
    new HeaderScreen().clickReactTrainingManagementLink();
    assertTrue(
        new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(trainingName)
            .clickGroupsTabs()
            .waitDataLoading()
            .clickGroupByName(groupName)
            .clickTrainerInTrainersSectionByName(trainerName)
            .isUsersPopUpHintAppeared(),
        "Hint isn't displayed after clicking the name of the trainer!");
  }

  @Test(priority = 2)
  public void checkThatProfilePageOfTrainerLoaded() {
    new ReactGroupDetailsScreen()
        .clickOnUserNameInPopUpHint()
        .switchToLastWindow();
    ProfileScreen profileScreen = new ProfileScreen()
        .waitTrainingInfoDisplayed();
    String actualName = format("%s %s", profileScreen.getUserFirstNameText(),
        profileScreen.getUserLastNameText());
    assertEquals(actualName, trainerName,
        format("Profile page '%s' is not displayed!", trainerName));
  }
}
