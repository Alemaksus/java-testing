package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactIntegrationTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description =
    "EPMRDPT_77665_VerifyThatLinkByCodeButtonIsActiveAndNoHintIsDisplayedIfTheTrainingIsSaved",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_77665_VerifyThatLinkByCodeButtonIsActiveAndNoHintIsDisplayedIfTheTrainingIsSaved {

  private String trainingName = "AutoTest_DraftStatus";
  private ReactIntegrationTabScreen reactIntegrationTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupIntegrationTab() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactIntegrationTabScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickIntegrationTabFromTrainingScreen();
  }

  @Test
  public void checkSaveTrainingHintIsNotDisplayed() {
    Assert.assertFalse(reactIntegrationTabScreen.isSaveTrainingHintDisplayed(),
        "'Save training before linking pool container' hint is displayed!");
  }

  @Test
  public void checkLinkByCodeButtonIsActive() {
    Assert.assertTrue(reactIntegrationTabScreen.isLinkedByCodeButtonActive(),
        "'Link by Code' button isn't active!");
  }
}
