package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDescriptionTabScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_88042_VerifyTheBlockIsNotDisplayedOnTrainingDetailsPageAfterClickingOnVisibilityIcon",
    groups = {"full", "regression"})
public class EPMRDPT_88042_VerifyTheBlockIsNotDisplayedOnTrainingDetailsPageAfterClickingOnVisibilityIcon {

  private static final String TRAINING_NAME = "VM examenator";
  private ReactDescriptionTabScreen reactDescriptionTabScreen;

  @BeforeClass
  public void openTrainingDescriptionTab() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
  }

  @Test(priority = 1)
  public void checkTextBlockHidden() {
    reactDescriptionTabScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickDescriptionTabFromTrainingScreen()
        .waitAllSpinnersAreNotDisplayed()
        .clickDetailsBlockEyeButton()
        .clickSaveChangesButton();
    assertTrue(reactDescriptionTabScreen.isChangesSaved(),
        "'Details' Text Block fails to become hidden!");
  }

  @Test(priority = 2)
  public void checkTextBlockNotDisplayedInTrainingDescriptionPage() {
    reactDescriptionTabScreen.clickTrainingListTab();
    new TrainingListScreen().chooseTrainingCardByName(TRAINING_NAME);
    assertFalse(new TrainingDescriptionScreen().isDetailsBlockDisplayed(),
        "Text Block is visible on the Description Page!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void rollBackChanges(Method method) {
    if (method.getName().equals("checkTextBlockNotDisplayedInTrainingDescriptionPage")) {
      new LoginService().logout()
          .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
          .clickReactTrainingManagementLink()
          .waitAllTrainingNameDisplayed()
          .typeTrainingName(TRAINING_NAME)
          .clickApplyButton()
          .clickTrainingNameByName(TRAINING_NAME)
          .clickDescriptionTabFromTrainingScreen()
          .waitAllSpinnersAreNotDisplayed()
          .clickDetailsBlockEyeButton()
          .waitAllSpinnersAreNotDisplayed()
          .clickSaveChangesButton()
          .isChangesSaved();
    }
  }
}
