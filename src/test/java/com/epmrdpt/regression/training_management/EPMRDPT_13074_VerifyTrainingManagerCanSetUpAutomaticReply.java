package com.epmrdpt.regression.training_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactAutoreplyTabScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13074_VerifyTrainingManagerCanSetUpAutomaticReply",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_13074_VerifyTrainingManagerCanSetUpAutomaticReply {

  private String trainingName = "AutoTest_PlannedStatus";
  private String inputTextForEnglishLocalization = "New program was added/updated";
  private String inputTextForRussianLocalization = "Новая программа была добавлена/обновлена";
  private String inputTextForUkrainianLocalization = "Нова програма тренінгу була додана/ оновлена";
  private ReactAutoreplyTabScreen reactAutoreplyTabScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void autoreplyTabScreenInitializationAndFillingFieldInDifferentLocalizations() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactAutoreplyTabScreen = new ReactAutoreplyTabScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(
        UserFactory.asTrainingManager());
    new HeaderScreen().clickReactTrainingManagementLink();
    reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(trainingName);
    reactAutoreplyTabScreen
        .clickAutoreplyTabFromTrainingScreen()
        .waitScreenLoading();
    reactDetailTrainingScreen = new ReactDetailTrainingScreen()
        .waitAllSpinnersAreNotDisplayed();
    if (!reactDetailTrainingScreen.isEnglishLanguageButtonDisplayedNoWait()) {
      reactDetailTrainingScreen
          .clickSelectLanguageButton()
          .clickEnglishLanguageButton();
    }
    if (!reactDetailTrainingScreen.isRussianLanguageButtonDisplayedNoWait()) {
      reactDetailTrainingScreen
          .clickSelectLanguageButton()
          .clickRussianLanguageButton();
    }
    if (!reactDetailTrainingScreen.isUkrainianLanguageButtonDisplayedNoWait()) {
      reactDetailTrainingScreen
          .clickSelectLanguageButton()
          .clickUkrainianLanguageButton();
    }
    reactTrainingManagementService.checkStatusEditCheckBoxAndSwitchOnIfUnchecked();
    reactDetailTrainingScreen.clickEnglishLanguageButton();
    reactAutoreplyTabScreen
        .clearAutoreplyInput()
        .typeTextInAutoreplyInput(inputTextForEnglishLocalization);
    reactDetailTrainingScreen.clickRussianLanguageButton();
    reactAutoreplyTabScreen.typeTextInAutoreplyInput(inputTextForRussianLocalization);
    reactDetailTrainingScreen.clickUkrainianLanguageButton();
    reactAutoreplyTabScreen.typeTextInAutoreplyInput(inputTextForUkrainianLocalization);
    reactDetailTrainingScreen.waitSaveChangesButtonEnabled().clickSaveChangesButton();
  }

  @Test(priority = 1)
  public void checkThatChangesMadeAndConfirmationOfSavingChangesIsDisplayed() {
    assertTrue(reactDetailTrainingScreen.isConfirmationOfSavingChangesIsDisplayed(),
        "Confirmation did not appear, changes not saved!");
  }

  @Test(priority = 2)
  public void checkThatTrainingManagerCanSetUpAutoreplyInEnglishLocalization() {
    reactDetailTrainingScreen.clickEnglishLanguageButton();
    assertEquals(reactAutoreplyTabScreen.getTextFromAutoreplyInput(),
        inputTextForEnglishLocalization,
        "Training Manager can't set up Autoreply in English localization!");
  }

  @Test(priority = 3)
  public void checkThatTrainingManagerCanSetUpAutoreplyInRussianLocalization() {
    reactDetailTrainingScreen.clickRussianLanguageButton();
    assertEquals(reactAutoreplyTabScreen.getTextFromAutoreplyInput(),
        inputTextForRussianLocalization,
        "Training Manager can't set up Autoreply in Russian localization!");
  }

  @Test(priority = 4)
  public void checkThatTrainingManagerCanSetUpAutoreplyInUkrainianLocalization() {
    reactDetailTrainingScreen.clickUkrainianLanguageButton();
    assertEquals(reactAutoreplyTabScreen.getTextFromAutoreplyInput(),
        inputTextForUkrainianLocalization,
        "Training Manager can't set up Autoreply in Ukrainian localization!");
  }
}
