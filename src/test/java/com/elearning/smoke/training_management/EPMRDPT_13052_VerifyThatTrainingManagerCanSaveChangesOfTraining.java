package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static java.lang.String.format;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13052_VerifyThatTrainingManagerCanSaveChangesOfTraining",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13052_VerifyThatTrainingManagerCanSaveChangesOfTraining {

  private String trainingName = "AutoTest Planned Status";
  private int randomStringLength = 10;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitScreenLoaded();
  }

  @Test(priority = 1)
  public void checkThatManagerCanReachRequiredTraining() {
    assertEquals(reactDetailTrainingScreen
            .getTrainingTitleText(),
        trainingName,
        format("Training '%s' isn't opened!", trainingName));
  }

  @Test(priority = 2)
  public void checkThatManagerCanMakeChanges() {
    String previousDisplayingName = reactDetailTrainingScreen
        .getDisplayingNameText();
    String randomDisplayingName = randomAlphabetic(randomStringLength);
    reactDetailTrainingScreen
        .typeDisplayingName(randomDisplayingName)
        .clickSaveChangesButton();
    assertNotEquals(previousDisplayingName,
        reactDetailTrainingScreen
            .getDisplayingNameText(),
        format("Changed haven't been saved. Expected new displaying name '%s' but actual '%s'!",
            randomDisplayingName, previousDisplayingName));
  }
}
